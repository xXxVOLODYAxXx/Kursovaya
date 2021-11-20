package ru.sfedu.Kursovaya.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMLDataProviderTest extends BaseTest {
    XMLDataProviderTest() throws JAXBException, IOException {}
    @Test
    void getUnitList() throws JAXBException, IOException {
        System.out.println(x.getUnitList());
    }

    @Test
    void createUnit() throws JAXBException, IOException {
        x.createUnit(initializeUnit());
        Assertions.assertEquals(unit,x.getUnitById(unit.getUnitId()));
        unit.setFoodRequired(6);
        Assertions.assertNotEquals(unit, x.getUnitById(unit.getUnitId()));
    }

    @Test
    void getUnitById() throws JAXBException {
        System.out.println(x.getUnitById(5L));
        Assertions.assertEquals(x.getUnitById(5L),initializeUnit());
        assertNull(x.getUnitById(6L));
    }

    @Test
    void deleteUnitById() throws JAXBException {
        x.deleteUnitById(1L);
    }

    @Test
    void updateUnitById() throws JAXBException, IOException {
        x.updateUnitById(initializeUnit());
    }
}