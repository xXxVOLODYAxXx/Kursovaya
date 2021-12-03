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

class CSVDataProviderCoreTest extends BaseTest {

    public CSVDataProviderCoreTest() throws JAXBException, IOException {
    }

    @Test
    void createUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        c.deleteUniverse(1L);
    }
    @Test
    void createUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.createUniverse(game,resources,army);
    }
    @Test
    void deleteUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
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
        c.deleteUniverse(1L);
    }
    @Test
    void deleteUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.deleteUniverse(1L);
        c.deleteUniverse(1L);
    }
    @Test
    void getEnemyPowerSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        readEnemyPlanet(c.getEnemyPower(1L,1L));
        c.deleteUniverse(1L);
    }
    @Test
    void getEnemyPowerFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.deleteUniverse(1L);
        readEnemyPlanet(c.getEnemyPower(1L,1L));
    }
    @Test
    void getArmyPowerSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        readArmyInfo(c.getArmyPower(1L));
        c.deleteUniverse(1L);
    }
    @Test
    void getArmyPowerFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        readArmyInfo(c.getArmyPower(2L));
    }
    @Test
    void attackPlanetSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        log.info(c.attackPlanet(1L,1L));
        c.deleteUniverse(1L);
    }
    @Test
    void attackPlanetFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        log.info(c.attackPlanet(1L,2L));
    }
    @Test
    void hireUnitSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(c.hireUnit(1L,1L));
        Assertions.assertFalse(game.getResources().getArmy().getUnits().equals(c.hireUnit(1L, 1L).getResources().getArmy().getUnits()) );
        c.deleteUniverse(1L);
    }
    @Test
    void hireUnitFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.deleteUniverse(1L);
        readGame(c.hireUnit(1L,1L));
    }
    @Test
    void getBuildingsInfoSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        log.info(c.getBuildingsInfo(1L));
        c.deleteUniverse(1L);
    }
    @Test
    void getBuildingsInfoFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.deleteUniverse(1L);
        log.info(c.getBuildingsInfo(1L));
    }
    @Test
    void addBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(c.addBuilding(1L,1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(c.addBuilding(1L, 1L).getResources().getBuildingList()) );
        c.deleteUniverse(1L);
    }
    @Test
    void addBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.deleteUniverse(1L);
        readGame(c.addBuilding(1L,1L));
    }
    @Test
    void removeBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(c.addBuilding(1L, 1L).getResources().getBuildingList()) );
        Assertions.assertFalse(game.getResources().getBuildingList().equals(c.removeBuilding(1L, 1L).getResources().getBuildingList()) );
        c.deleteUniverse(1L);
    }
    @Test
    void removeBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.deleteUniverse(1L);
        log.info(c.removeBuilding(1L,1L));
    }
    @Test
    void manageResources1Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(c.manageResources(1L,1));
    }
    @Test
    void manageResources1Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        log.info(c.manageResources(1L,1));
        c.deleteUniverse(1L);
    }
    @Test
    void testManageResources2Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(c.manageResources(1L,2,1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(c.manageResources(1L,2,1L).getResources().getBuildingList()) );
        c.deleteUniverse(1L);
    }
    @Test
    void testManageResources2Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.deleteUniverse(1L);
        readGame(c.manageResources(1L,2,1L));
    }
    @Test
    void testManageResources3Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(c.manageResources(1L,2,1L).getResources().getBuildingList()) );
        Assertions.assertTrue(game.getResources().getBuildingList().equals(c.manageResources(1L,3,1L).getResources().getBuildingList()) );
        c.deleteUniverse(1L);
    }
    @Test
    void testManageResources3Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.deleteUniverse(1L);
        log.info(c.manageResources(1L,3,1L));
    }
    @Test
    void testManageResources4Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        game=initGame();
        resources=initResources();
        army=initArmy();
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(c.manageResources(1L,4,1L));
        Assertions.assertFalse(game.getResources().getArmy().getUnits().equals(c.manageResources(1L,4,1L).getResources().getArmy().getUnits()) );
        c.deleteUniverse(1L);
    }
    @Test
    void testManageResources4Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.deleteUniverse(1L);
        log.info(c.manageResources(1L,4,1L));
    }
}