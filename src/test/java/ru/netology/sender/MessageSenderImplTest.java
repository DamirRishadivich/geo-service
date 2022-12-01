package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {



    @Test
    public void sendTest_RUS() {
        String ip = "172.0.0.0";
        Map<String,String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        String check = messageSender.send(map);

        Assertions.assertEquals("Добро пожаловать", check);
    }

    @Test
    public void sendTest_USA() {
        String ip = "96.172";
        Map<String,String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER,ip);

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip)).
                thenReturn(new Location("Vashington", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA)).
                thenReturn("welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String check = messageSender.send(map);
        Assertions.assertEquals("welcome", check);
    }

    @Test
    public void sendTest_null() {
        String ip = "144.31.31";
        HashMap map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER,ip);
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("Kazan", null,null,0));
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(null)).thenReturn(null);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String check = messageSender.send(map);
        Assertions.assertEquals(null, check);
    }

    @Test
    public void sendTest_with_spy() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService,localizationService);
        String ipRUS = "172.168";

        Map<String,String> mapRUS = new HashMap<>();
        mapRUS.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipRUS);

        String rusText = messageSender.send(mapRUS);
        Assertions.assertEquals("Добро пожаловать", rusText);
    }
}