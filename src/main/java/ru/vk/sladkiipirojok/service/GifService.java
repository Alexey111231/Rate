package ru.vk.sladkiipirojok.service;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vk.sladkiipirojok.Config;
import ru.vk.sladkiipirojok.ParserUtil;
import ru.vk.sladkiipirojok.feignclients.FeignGifClient;
import ru.vk.sladkiipirojok.feignclients.FeignMoneyClient;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
public class GifService {
    public final FeignGifClient gifClient;
    public final FeignMoneyClient moneyClient;
    public final Config configuration;

    @Autowired
    public GifService(FeignGifClient gifClient, FeignMoneyClient moneyClient, Config configuration) {
        this.gifClient = gifClient;
        this.moneyClient = moneyClient;
        this.configuration = configuration;
    }

    public final String getGif(String currency_id) throws ParseException{
        return "<img src=\"" + ParserUtil.getGifURL(getGifJSON(currency_id)) + "\"/>";
    }

    private String getGifJSON(String currency_id) throws ParseException {
        ResponseEntity<String> response = gifClient.getRandGif(configuration.getApiGifKey(),
                isRich(currency_id)? configuration.getApiGifRich():configuration.getApiGifBroke(),
                (int) Math.floor(Math.random()*100), 1);
        if(!response.getStatusCode().equals(HttpStatus.OK))
            throw new IllegalStateException("The remote server error");

        return response.getBody();
    }

    private boolean isRich(String currency_id) throws ParseException {
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
}
