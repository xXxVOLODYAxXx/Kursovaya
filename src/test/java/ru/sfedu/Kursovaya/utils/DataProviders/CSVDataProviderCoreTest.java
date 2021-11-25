package ru.sfedu.Kursovaya.utils.DataProviders;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;
import ru.sfedu.Kursovaya.Beans.Army;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.Beans.Game;
import ru.sfedu.Kursovaya.Beans.Resources;
import ru.sfedu.Kursovaya.utils.BaseTest;
import ru.sfedu.Kursovaya.utils.Constants;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVDataProviderCoreTest extends BaseTest {

    public CSVDataProviderCoreTest() throws JAXBException, IOException {
    }

    @Test
    void createUniverse() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        Long id = 1L;
        Game game = new Game();
        Resources resources = new Resources();
        Army army = new Army();
        ArmyInfo armyInfo = new ArmyInfo();
        armyInfo.setArmyAttackPoints(Constants.DEFAULT_ARMY_ATTACK_POINTS);
        armyInfo.setArmyHealthPoints(Constants.DEFAULT_ARMY_HEALTH_POINTS);
        army.setArmyId(id);
        army.setArmyInfo(armyInfo);
        resources.setResourcesId(id);
        resources.setFood(Constants.DEFAULT_FOOD);
        resources.setGold(Constants.DEFAULT_GOLD);
        resources.setMetal(Constants.DEFAULT_METAL);
        resources.setArmy(army);
        game.setGameId(id);
        game.setResources(resources);
        game.setPlayerPlanetList(c.getPlayerPlanetList());
        game.setEnemyPlanetList(c.getEnemyPlanetList());
        c.createUniverse(game,resources,army);
    }

    @Test
    void deleteUniverse() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.deleteUniverse(1L);
    }

    @Test
    void getEnemyPower() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        readEnemyPlanet(c.getEnemyPower(1L,1L));
    }

    @Test
    void getArmyPower() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        readArmyInfo(c.getArmyPower(2L));
    }

    @Test
    void attackPlanet() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        log.info(c.attackPlanet(1L,2L));
    }

    @Test
    void hireUnit() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        readGame(c.hireUnit(1L,2L));
    }

    @Test
    void getBuildingsInfo() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(c.getBuildingsInfo(2L));
    }

    @Test
    void addBuilding() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        readGame(c.addBuilding(1L,2L));
    }

    @Test
    void removeBuilding() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(c.removeBuilding(1L,2L));
    }

    @Test
    void manageResources() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(c.manageResources(2L,1));
    }

    @Test
    void testManageResources2() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(c.manageResources(2L,2,1L));
    }
    @Test
    void testManageResources3() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(c.manageResources(2L,3,1L));
    }
    @Test
    void testManageResources4() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(c.manageResources(2L,4,1L));
    }
}