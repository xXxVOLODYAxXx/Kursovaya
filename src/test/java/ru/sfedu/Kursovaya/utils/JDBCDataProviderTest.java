package ru.sfedu.Kursovaya.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

class JDBCDataProviderTest extends BaseTest {

    public JDBCDataProviderTest() throws JAXBException, IOException {
    }

    /**@Test
    void createTable() throws SQLException {
        j.createTable();
    }*/
    @Test
    void insertRecord() throws SQLException {
        j.insertUnit(initUnit());
        Assertions.assertTrue(j.readUnitById(1L).equals(initUnit()));
    }
    @Test
    void readJDBCUnit() throws SQLException {
        log.info(j.readUnitById(1L));
        Assertions.assertTrue(j.readUnitById(1L).equals(initUnit()));
    }
    @Test
    void updateUnit() throws SQLException {
        Assertions.assertTrue(j.readUnitById(1L).equals(initUnit()));
        unit.setUnitAttackPoints(100);
        j.updateUnitById(unit);
        Assertions.assertTrue(j.readUnitById(1L).equals(unit));
        Assertions.assertFalse(j.readUnitById(1L).equals(initUnit()));
    }
    @Test
    void deleteUnit() throws SQLException {
        j.insertUnit(initUnit());
        j.deleteUnitById(1L);
    }

}