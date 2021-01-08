package ru.vk.sladkiipirojok.controllers;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import ru.vk.sladkiipirojok.Config;
import ru.vk.sladkiipirojok.ParserUtil;
import ru.vk.sladkiipirojok.feignclients.FeignGifClient;
import ru.vk.sladkiipirojok.feignclients.FeignMoneyClient;
import java.time.LocalDate;
import java.time.ZoneOffset;

@RequestMapping("/")
@RestController
public final class MoneyRateController {
    public final FeignGifClient gifClient;
    public final  FeignMoneyClient moneyClient;
    public final Config configuration;

    @Autowired
    public MoneyRateController(FeignGifClient gifClient, FeignMoneyClient moneyClient, Config configuration) {
        this.gifClient = gifClient;
        this.moneyClient = moneyClient;
        this.configuration = configuration;
    }

    @GetMapping("rand-gif")
    String getGif(@RequestParam String currency_id) throws ParseException {
        ResponseEntity<String> response = gifClient.getRandGif(configuration.getApiGifKey(),
                isRich(currency_id)? configuration.getApiGifRich():configuration.getApiGifBroke(),
                (int) Math.floor(Math.random()*100), 1);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new IllegalStateException("The remote server error");

            return "<img src=\"" +
                    ParserUtil.getGifURL(response.getBody()) + "\"/>";
    }

    boolean isRich(String currency_id) throws ParseException {
        ResponseEntity<String> responseCurent = moneyClient
                .getLatest(configuration.getApiRateKey(),configuration.getBaseCurrency());

        ResponseEntity<String> responseYesterday = moneyClient
                .getByDate(configuration.getApiRateKey(),
                LocalDate.now(ZoneOffset.UTC).minusDays(1).toString(), configuration.getBaseCurrency());

        ResponseEntity<String> responseAllCurrency = moneyClient.getAllCurrencies();

        if(!responseCurent.getStatusCode().equals(HttpStatus.OK)
                || !responseYesterday.getStatusCode().equals(HttpStatus.OK)
                || !responseAllCurrency.getStatusCode().equals(HttpStatus.OK))
            throw new IllegalStateException("The remote server error");

        if(!currency_id.matches("[A-Z]{3}") || !responseAllCurrency.getBody().contains(currency_id))
            throw new IllegalArgumentException("Error currency id");

        Double curentCurrency =  ParserUtil.getRate(responseCurent.getBody(), currency_id);
        Double yesterdayCurrency =  ParserUtil.getRate(responseYesterday.getBody(), currency_id);
        return  curentCurrency > yesterdayCurrency;
    }

    @ExceptionHandler(ParseException.class)
    public ModelAndView parseException(ParseException exception) {
        return createErrorJSON(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView currencyError(IllegalArgumentException exception){
        return createErrorJSON(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ModelAndView remoteException(IllegalStateException exception) {
        return createErrorJSON(exception, HttpStatus.SERVICE_UNAVAILABLE);
    }

    private ModelAndView createErrorJSON(Exception exception, HttpStatus status){
        ModelAndView errorView = new ModelAndView(new MappingJackson2JsonView());
        errorView.setStatus(status);
        errorView.addObject("httpstatus", status);
        errorView.addObject("error", exception.getMessage());
        return errorView;
    }
}
