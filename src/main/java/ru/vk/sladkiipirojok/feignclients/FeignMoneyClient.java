package ru.vk.sladkiipirojok.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vk.sladkiipirojok.Config;

@FeignClient(name = "money-rate", url = Config.API_RATE_LINK)
@Service
public interface FeignMoneyClient {
    @RequestMapping("/latest.json")
    ResponseEntity<String> getLatest(@RequestParam(name = "app_id") String appId,
                                    @RequestParam(name = "base") String base);

    @RequestMapping("/historical/{date}.json")
    ResponseEntity<String> getByDate(@RequestParam(name = "app_id") String appId,
                     @PathVariable(name = "date") String date,
                     @RequestParam(name = "base") String base);

    @RequestMapping("/currencies.json")
    ResponseEntity<String> getAllCurrencies();
}
