package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    GeoServiceImpl geoService = new GeoServiceImpl();
    String ipRUS = "172.21";
    String ipENG = "96.21";

    @Test
    void testRusLocation() {
        Location checkLocation = new Location("Kazan", Country.RUSSIA, null, 0);
        Location expectedLocation = geoService.byIp(ipRUS);
        Assertions.assertEquals(expectedLocation.getCountry(), checkLocation.getCountry());
    }
    @Test
    void testEngLocation() {
        Location checkLocation = new Location("Vashington", Country.USA, null,0);
        Location expectedLocation = geoService.byIp(ipENG);
        Assertions.assertEquals(expectedLocation.getCountry(), checkLocation.getCountry());
    }
}