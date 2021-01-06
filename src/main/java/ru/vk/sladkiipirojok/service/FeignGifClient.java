package ru.vk.sladkiipirojok.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rand-gif", url = "https://api.giphy.com/v1/gifs")
@Service
public interface FeignGifClient {
    @RequestMapping("/search")
    String getRandGif(@RequestParam(name = "api_key") String apiKey,
                      @RequestParam(name = "q") String requestGif,
                      @RequestParam(name = "offset") int offset,
                      @RequestParam(name = "limit") int limit);
}
