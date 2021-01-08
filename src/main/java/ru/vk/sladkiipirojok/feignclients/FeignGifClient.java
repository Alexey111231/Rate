package ru.vk.sladkiipirojok.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vk.sladkiipirojok.Config;

@FeignClient(name = "rand-gif", url = Config.API_GIF_LINK)
@Service
public interface FeignGifClient {
    @RequestMapping("/search")
    ResponseEntity<String> getRandGif(@RequestParam(name = "api_key") String apiKey,
                              @RequestParam(name = "q") String requestGif,
                              @RequestParam(name = "offset") int offset,
                              @RequestParam(name = "limit") int limit);
}
