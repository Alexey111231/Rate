package ru.vk.sladkiipirojok.service;

import org.springframework.stereotype.Service;
import ru.vk.sladkiipirojok.Application;

import java.io.IOException;
import java.util.Properties;

@Service
public class PropertiesService {
    Properties config = new Properties();

    public PropertiesService() {
        try {
            config.load(Application.class.getResource("rate-controller-config.properties").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGifApiKey(){
        return config.getProperty("api.gif.key");
    }

    public String getRateApiKey(){
        return config.getProperty("api.rate.key");
    }

    public String getBaseCurrency(){
        return config.getProperty("base.currency");
    }
    public String getRichGif(){
        return config.getProperty("gif.rich");
    }

    public String getBrokeGif(){
        return config.getProperty("gif.broke");
    }
    public int getTimeZoneDif(){
        return Integer.parseInt(config.getProperty("timezone"));
    }
}
