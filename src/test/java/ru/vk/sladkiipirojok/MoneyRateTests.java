package ru.vk.sladkiipirojok;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vk.sladkiipirojok.feignclients.FeignGifClient;
import ru.vk.sladkiipirojok.feignclients.FeignMoneyClient;
import ru.vk.sladkiipirojok.service.GifService;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyRateTests {

    @MockBean
    FeignGifClient gifClient;
    @MockBean
    FeignMoneyClient moneyClient;
    @Autowired
    Config configuration;

    @Before
    public void before(){
        Mockito.when(moneyClient
                .getLatest(any(),any()))
                .thenReturn(new ResponseEntity<>(ServiceTestData.MONEY_API_LATEST_BODY, HttpStatus.OK));

        Mockito.when(moneyClient
                .getByDate(any(), anyString(), any())).
                thenReturn(new ResponseEntity<>(ServiceTestData.MONEY_API_YESTERDAY_BODY, HttpStatus.OK));

        Mockito.when(moneyClient.getAllCurrencies()).thenReturn(new ResponseEntity<>(ServiceTestData.MONEY_API_ALL_CURRENCY_BODY,
                HttpStatus.OK));

    }
    @SneakyThrows
    @Test
    public void correctBrokeTest(){
        Mockito.when(gifClient.getRandGif(any(), eq(configuration.getApiGifBroke()), anyInt(), eq(1)))
                .thenReturn(new ResponseEntity<>(ServiceTestData.GIF_API_RESPONSE_BODY, HttpStatus.OK));

        GifService gifService = new GifService(gifClient, moneyClient, configuration);

        Assert.assertEquals(gifService.getGif("AED"), ServiceTestData.GIF_SERVICE_RESULT );
    }

    @SneakyThrows
    @Test
    public void correctRichTest(){
        Mockito.when(gifClient.getRandGif(any(), eq(configuration.getApiGifRich()), anyInt(), eq(1)))
                .thenReturn(new ResponseEntity<>(ServiceTestData.GIF_API_RESPONSE_BODY, HttpStatus.OK));

        GifService gifService = new GifService(gifClient, moneyClient, configuration);

        Assert.assertEquals(gifService.getGif("ZMW"), ServiceTestData.GIF_SERVICE_RESULT);
    }

    @SneakyThrows
    @Test
    public void remoteErrorTest(){
        Mockito.when(gifClient.getRandGif(any(), eq(configuration.getApiGifRich()), anyInt(), eq(1)))
                .thenReturn(new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR));

        GifService gifService = new GifService(gifClient, moneyClient, configuration);

        IllegalStateException ise = Assert.assertThrows(IllegalStateException.class,
                () -> gifService.getGif("ZMW"));

        Assert.assertFalse(ise.getMessage().isEmpty());
    }

    @SneakyThrows
    @Test
    public void invalidCurrencyTest(){
        Mockito.when(gifClient.getRandGif(any(), eq(configuration.getApiGifRich()), anyInt(), eq(1)))
                .thenReturn(new ResponseEntity<>(ServiceTestData.GIF_API_RESPONSE_BODY, HttpStatus.OK));

        GifService gifService = new GifService(gifClient, moneyClient, configuration);

        IllegalArgumentException iae = Assert.assertThrows(IllegalArgumentException.class,
                ()->gifService.getGif("ZMWD"));

        Assert.assertFalse(iae.getMessage().isEmpty());
    }
}
