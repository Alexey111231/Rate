package ru.vk.sladkiipirojok.controllers;

import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vk.sladkiipirojok.ParserUtil;
import ru.vk.sladkiipirojok.service.FeignGifClient;
import ru.vk.sladkiipirojok.service.FeignMoneyClient;
import ru.vk.sladkiipirojok.service.PropertiesService;

import java.util.Properties;

@RequestMapping("/")
@RestController
@AllArgsConstructor
public final class MoneyRateController {
    @Autowired
    public final FeignGifClient gifClient;
    @Autowired
    public final  FeignMoneyClient moneyClient;
    @Autowired
    public final PropertiesService configuration;
    @GetMapping("rand-gif")
    String getGif(@RequestParam String currency_id) throws ParseException {
            return "<img src=\"" +
                    ParserUtil.getGifURL(gifClient.getRandGif(configuration.getGifApiKey(),
                    isRich(currency_id)? configuration.getRichGif():configuration.getBrokeGif(),
                    (int) Math.floor(Math.random()*100), 1)) + "\"/>";
    }

    boolean isRich(String currency_id) throws ParseException {
        return ParserUtil.getRate(moneyClient.getLatest(configuration.getRateApiKey(),configuration.getBaseCurrency()), currency_id) >
                ParserUtil.getRate(moneyClient.getByDate(configuration.getRateApiKey(),
                        "2014-12-23", configuration.getBaseCurrency()),
                        currency_id);
    }
}
