package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void test_locale_RUS() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать", expected);
    }
    @Test
    void test_locale_ENG() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = localizationService.locale(Country.USA);
        Assertions.assertEquals("welcome", expected);
    }
}