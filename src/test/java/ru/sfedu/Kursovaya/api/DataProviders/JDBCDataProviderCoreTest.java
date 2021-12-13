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

import static org.junit.jupiter.api.Assertions.*;

class JDBCDataProviderCoreTest extends BaseTest {

    public JDBCDataProviderCoreTest() throws IOException, JAXBException {
        super();
    }
    @Test
    void createUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        assertEquals(game, j.getGameById(game.getGameId()));
        assertEquals(army, j.getArmyById(army.getArmyId()));
        assertEquals(resources, j.getResourcesById(resources.getResourcesId()));

    }
    @Test
    void createUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        Assertions.assertNull(j.createUniverse(game,resources,army));
    }
    @Test
    void deleteUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        j.deleteUniverse(1L);
    }
    @Test
    void deleteUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        j.deleteUniverse(1L);
        j.deleteUniverse(1L);
    }
    @Test
    void getEnemyPowerSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        readEnemyPlanet(j.getEnemyPower(1L,1L));
    }
    @Test
    void getEnemyPowerFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.getEnemyPower(1L,1L));
    }
    @Test
    void getArmyPowerSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        readArmyInfo(j.getArmyPower(1L));
    }
    @Test
    void getArmyPowerFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Assertions.assertNull(j.getArmyPower(2L));
    }
    @Test
    void attackPlanetSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        log.info(j.attackPlanet(1L,1L));

    }
    @Test
    void attackPlanetFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        Assertions.assertNull(j.attackPlanet(1L,1L));
    }
    @Test
    void hireUnitSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertEquals(game, j.getGameById(game.getGameId()));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(j.hireUnit(1L,1L));
        Assertions.assertFalse(game.getResources().getArmy().getUnits().equals(j.hireUnit(1L, 1L).getResources().getArmy().getUnits()) );
    }
    @Test
    void hireUnitFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.hireUnit(1L,1L).getGameId());
    }
    @Test
    void getBuildingsInfoSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        log.info(j.getBuildingsInfo(1L));
        j.deleteUniverse(1L);
    }
    @Test
    void getBuildingsInfoFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.getBuildingsInfo(1L));
    }
    @Test
    void addBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(j.addBuilding(1L,1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(j.addBuilding(1L, 1L).getResources().getBuildingList()) );
        j.deleteUniverse(1L);
    }
    @Test
    void addBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.addBuilding(1L,1L));
    }
    @Test
    void removeBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(j.addBuilding(1L, 1L).getResources().getBuildingList()) );
        Assertions.assertFalse(game.getResources().getBuildingList().equals(j.removeBuilding(1L, 1L).getResources().getBuildingList()) );
        j.deleteUniverse(1L);
    }
    @Test
    void removeBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.removeBuilding(1L,1L).getGameId());
    }
    @Test
    void manageResources1Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info(j.manageResources(1L,1));
    }
    @Test
    void manageResources1Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        log.info(j.manageResources(1L,1));
        j.deleteUniverse(1L);
    }
    @Test
    void testManageResources2Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(j.manageResources(1L,2,1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(j.manageResources(1L,2,1L).getResources().getBuildingList()) );
        j.deleteUniverse(1L);
    }
    @Test
    void testManageResources2Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.manageResources(1L,2,1L));
    }
    @Test
    void testManageResources3Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        assertNotEquals(game.getResources().getBuildingList(), j.manageResources(1L, 2, 1L).getResources().getBuildingList());
        j.manageResources(1L, 3, 1L).getResources().getBuildingList();
        assertEquals(game.getResources().getBuildingList(),j.getGameById(1L).getResources().getBuildingList() );
        j.deleteUniverse(1L);
    }
    @Test
    void testManageResources3Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.manageResources(1L,3,1L).getGameId());
    }
    @Test
    void testManageResources4Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        j.deleteUniverse(1L);
        j.deletePlayerPlanet(playerPlanet);
        j.deleteEnemyPlanet(enemyPlanet);
        j.deleteBuilding(building);
        j.deleteUnit(unit);
        j.insertUnit(unit);
        j.insertBuilding(building);
        j.insertEnemyPlanet(enemyPlanet);
        j.insertPlayerPlanet(playerPlanet);
        j.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(j.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(j.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(j.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(j.manageResources(1L,4,1L));
        Assertions.assertFalse(game.getResources().getArmy().getUnits().equals(j.manageResources(1L,4,1L).getResources().getArmy().getUnits()) );
        j.deleteUniverse(1L);
    }
    @Test
    void testManageResources4Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        j.deleteUniverse(1L);
        Assertions.assertNull(j.manageResources(1L,4,1L).getGameId());
    }
}