package ru.sfedu.Kursovaya.utils.DataProviders;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sfedu.Kursovaya.Beans.Army;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.Beans.Game;
import ru.sfedu.Kursovaya.Beans.Resources;
import ru.sfedu.Kursovaya.utils.BaseTest;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

import javax.xml.bind.JAXBException;
import java.io.IOException;

class XMLDataProviderCoreTest extends BaseTest {

    public XMLDataProviderCoreTest() throws JAXBException, IOException {
    }

    @Test
    void createUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
    }
    @Test
    void createUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        x.createUniverse(game,resources,army);
    }
    @Test
    void deleteUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
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
        game.setPlayerPlanetList(x.getPlayerPlanetList());
        game.setEnemyPlanetList(x.getEnemyPlanetList());
        x.createUniverse(game,resources,army);
        x.deleteUniverse(1L);
    }
    @Test
    void deleteUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        x.deleteUniverse(1L);
    }

    @Test
    void getEnemyPower() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        readEnemyPlanet(x.getEnemyPower(1L,1L));
    }

    @Test
    void getArmyPower() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        readArmyInfo(x.getArmyPower(2L));
    }

    @Test
    void attackPlanet() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        log.info(x.attackPlanet(1L,2L));
    }

    @Test
    void hireUnit() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        readGame(x.hireUnit(1L,2L));
    }

    @Test
    void getBuildingsInfo() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(x.getBuildingsInfo(2L));
    }

    @Test
    void addBuilding() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        readGame(x.addBuilding(1L,2L));
    }

    @Test
    void removeBuilding() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        log.info(x.removeBuilding(1L,2L));
    }

    @Test
    void manageResources() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(x.manageResources(2L,1));
    }

    @Test
    void testManageResources2() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(x.manageResources(2L,2,1L));
    }
    @Test
    void testManageResources3() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(x.manageResources(2L,3,1L));
    }
    @Test
    void testManageResources4() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(x.manageResources(2L,4,1L));
    }
}