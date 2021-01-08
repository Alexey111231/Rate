package ru.vk.sladkiipirojok.controllers;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vk.sladkiipirojok.service.GifService;

@RequestMapping("/")
@RestController
public final class MoneyRateController {

    private final GifService gifService;

    @Autowired
    public MoneyRateController(GifService gifService) {
        this.gifService = gifService;
    }

    @GetMapping("rand-gif")
    String getGif(@RequestParam String currency_id) throws ParseException {
      return gifService.getGif(currency_id);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<String> parseException(ParseException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> currencyError(IllegalArgumentException exception){
        return  new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> remoteException(IllegalStateException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
