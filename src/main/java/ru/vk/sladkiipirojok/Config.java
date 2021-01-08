package ru.vk.sladkiipirojok;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Config {
    public static final String API_GIF_LINK = "https://api.giphy.com/v1/gifs";
    public static final String API_RATE_LINK = "https://openexchangerates.org/api";
    @Value("${api.gif.key}")
    private String apiGifKey;
    @Value("${api.rate.key}")
    private String apiRateKey;
    @Value("${base.currency}")
    private String baseCurrency;
    @Value("${api.gif.rich}")
    private String apiGifRich;
    @Value("${api.gif.broke}")
    private String apiGifBroke;
}
