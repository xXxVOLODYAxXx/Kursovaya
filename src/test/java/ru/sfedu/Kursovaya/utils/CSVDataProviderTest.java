package ru.sfedu.Kursovaya.utils;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

class CSVDataProviderTest extends BaseTest {


    CSVDataProviderTest() throws IOException, JAXBException {}

    @Test
    void getUnitList() throws IOException {
        System.out.println(c.getUnitList());
    }

    @Test
    void createUnit() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.createUnit(initUnit());
        Assertions.assertTrue(unit.equals(c.getUnitById(unit.getUnitId())));
        Assertions.assertNotEquals(initUnit(), null);
    }

    @Test
    void deleteUnitById() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.deleteUnitById(4L);
    }
    @Test
    void clearUnits() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.clearUnits();
    }
    @Test
    void getUnitById() throws IOException {
        System.out.println(c.getUnitById(3L));
    }

    @Test
    void updateUnitById() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.updateUnitById(initUnit());
    }
    @Test
    void getBuildingList() throws IOException {
        log.info(c.getBuildingList());
    }
    @Test
    void createArmy() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.createArmy(initArmy());
    }
    @Test
    void getArmyList() throws IOException {
        log.info(c.getArmyList());
    }
}