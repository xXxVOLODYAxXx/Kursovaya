package ru.sfedu.Kursovaya.api.DataProviders;

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
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class XMLDataProviderCoreTest extends BaseTest {

    public XMLDataProviderCoreTest() throws JAXBException, IOException {
    }

    @Test
    void createUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        x.deleteUniverse(1L);
    }

    @Test
    void createUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        x.createUniverse(game, resources, army);
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
        x.createUniverse(game, resources, army);
        x.deleteUniverse(1L);
    }

    @Test
    void deleteUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        x.deleteUniverse(1L);
        x.deleteUniverse(1L);
    }

    @Test
    void getEnemyPowerSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readEnemyPlanet(x.getEnemyPower(1L, 1L));
        x.deleteUniverse(1L);
    }

    @Test
    void getEnemyPowerFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        x.deleteUniverse(1L);
        readEnemyPlanet(x.getEnemyPower(1L, 1L));
    }

    @Test
    void getArmyPowerSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readArmyInfo(x.getArmyPower(1L));
        x.deleteUniverse(1L);
    }

    @Test
    void getArmyPowerFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        readArmyInfo(x.getArmyPower(2L));
    }

    @Test
    void attackPlanetSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        log.info(x.attackPlanet(1L, 1L));
        x.deleteUniverse(1L);
    }

    @Test
    void attackPlanetFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        log.info(x.attackPlanet(1L, 2L));
    }

    @Test
    void hireUnitSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(x.hireUnit(1L, 1L));
        assertEquals(game.getResources().getArmy().getUnits(), x.hireUnit(1L, 1L).getResources().getArmy().getUnits());
        x.deleteUniverse(1L);
    }

    @Test
    void hireUnitFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        x.deleteUniverse(1L);
        readGame(x.hireUnit(1L, 1L));
    }

    @Test
    void getBuildingsInfoSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        log.info(x.getBuildingsInfo(1L));
        x.deleteUniverse(1L);
    }

    @Test
    void getBuildingsInfoFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteUniverse(1L);
        log.info(x.getBuildingsInfo(1L));
    }

    @Test
    void addBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(x.addBuilding(1L, 1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.addBuilding(1L, 1L).getResources().getBuildingList()));
        x.deleteUniverse(1L);
    }

    @Test
    void addBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        x.deleteUniverse(1L);
        readGame(x.addBuilding(1L, 1L));
    }

    @Test
    void removeBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.addBuilding(1L, 1L).getResources().getBuildingList()));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.removeBuilding(1L, 1L).getResources().getBuildingList()));
        x.deleteUniverse(1L);
    }

    @Test
    void removeBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        x.deleteUniverse(1L);
        log.info(x.removeBuilding(1L, 1L));
    }

    @Test
    void manageResources1Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(x.manageResources(1L, 1));
    }

    @Test
    void manageResources1Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        log.info(x.manageResources(1L, 1));
        x.deleteUniverse(1L);
    }

    @Test
    void testManageResources2Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(x.manageResources(1L, 2, 1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.manageResources(1L, 2, 1L).getResources().getBuildingList()));
        x.deleteUniverse(1L);
    }

    @Test
    void testManageResources2Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        x.deleteUniverse(1L);
        readGame(x.manageResources(1L, 2, 1L));
    }

    @Test
    void testManageResources3Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.manageResources(1L, 2, 1L).getResources().getBuildingList()));
        Assertions.assertTrue(game.getResources().getBuildingList().equals(x.manageResources(1L, 3, 1L).getResources().getBuildingList()));
        x.deleteUniverse(1L);
    }

    @Test
    void testManageResources3Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        x.deleteUniverse(1L);
        log.info(x.manageResources(1L, 3, 1L));
    }

    @Test
    void testManageResources4Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        game = initGame();
        resources = initResources();
        army = initArmy();
        x.createUniverse(game, resources, army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(x.manageResources(1L, 4, 1L));
        assertEquals(game.getResources().getArmy().getUnits(), x.manageResources(1L, 4, 1L).getResources().getArmy().getUnits());
        x.deleteUniverse(1L);
    }

    @Test
    void testManageResources4Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        x.deleteUniverse(1L);
        log.info(x.manageResources(1L, 4, 1L));
    }
}
