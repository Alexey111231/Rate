package ru.vk.sladkiipirojok.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "money-rate", url = "https://openexchangerates.org/api")
@Service
public interface FeignMoneyClient {
    @RequestMapping("/latest.json")
    String getLatest(@RequestParam(name = "app_id") String appId,
                     @RequestParam(name = "base") String base);

    @RequestMapping("/historical/{date}.json")
    String getByDate(@RequestParam(name = "app_id") String appId,
                     @PathVariable(name = "date") String date,
                     @RequestParam(name = "base") String base);
}
