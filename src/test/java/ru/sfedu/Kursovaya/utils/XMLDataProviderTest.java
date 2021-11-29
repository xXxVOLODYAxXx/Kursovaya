package ru.sfedu.Kursovaya.utils;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
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
    void createUnit() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.createUnit(initUnit());
        Assertions.assertEquals(unit,x.getUnitById(unit.getUnitId()));
        unit.setFoodRequired(6);
        Assertions.assertNotEquals(unit, x.getUnitById(unit.getUnitId()));
    }

    @Test
    void getUnitById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        System.out.println(x.getUnitById(1L));
        Assertions.assertEquals(x.getUnitById(1L),initUnit());
        assertNull(x.getUnitById(6L));
    }

    @Test
    void deleteUnitById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteUnitById(1L);
    }

    @Test
    void updateUnitById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.updateUnitById(initUnit());
    }

    @Test
    void createBuilding() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.createBuilding(initBuilding());
    }

    @Test
    void getBuildingById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        System.out.println(x.getBuildingById(1L));
    }

    @Test
    void deleteBuildingById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteBuildingById(1L);
    }

    @Test
    void createPlayerPlanet() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.createPlayerPlanet(initPlayerPlanet());
    }

    @Test
    void getPlayerPlanetById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        System.out.println(x.getPlayerPlanetById(1L));
    }

    @Test
    void deletePlayerPlanetById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deletePlayerPlanetById(1L);
    }
    @Test
    void createEnemyPlanet() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.createEnemyPlanet(initEnemyPlanet());
    }

    @Test
    void getEnemyPlanetById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        System.out.println(x.getEnemyPlanetById(1L).getPlanetId());
    }

    @Test
    void deleteEnemyPlanetById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteEnemyPlanetById(1L);
    }
    @Test
    void createArmy() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.createArmy(initArmy());
    }

    @Test
    void getArmyById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        System.out.println(x.getArmyById(1L));
    }

    @Test
    void deleteArmyById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteArmyById(1L);
    }
    @Test
    void createResources() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.createResources(initResources());
    }

    @Test
    void getResourcesById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        System.out.println(x.getResourcesById(1L));
    }

    @Test
    void deleteResourcesById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteResourcesById(1L);
    }
    @Test
    void createGame() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.createGame(initGame());
    }

    @Test
    void getGameById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        System.out.println(x.getGameById(1L));
    }

    @Test
    void deleteGameById() throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteGameById(1L);
    }
}