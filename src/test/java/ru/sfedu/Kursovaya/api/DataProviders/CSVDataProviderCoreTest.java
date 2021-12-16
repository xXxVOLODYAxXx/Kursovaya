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

class CSVDataProviderCoreTest extends BaseTest {

    public CSVDataProviderCoreTest() throws JAXBException, IOException {
    }

    @Test
    void createUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        assertEquals(game, c.getGameById(game.getGameId()));
        assertEquals(army, c.getArmyById(army.getArmyId()));
        assertEquals(resources, c.getResourcesById(resources.getResourcesId()));

    }
    @Test
    void createUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        Assertions.assertNull(c.createUniverse(game,resources,army));
    }
    @Test
    void deleteUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        c.deleteUniverse(1L);
        Assertions.assertNull(c.getGameById(1L));
    }
    @Test
    void deleteUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.deleteUniverse(1L);
        c.deleteUniverse(1L);
        Assertions.assertNull(c.getGameById(1L));
    }
    @Test
    void getEnemyPowerSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(enemyPlanet.equals(c.getEnemyPower(1L,1L)));
    }
    @Test
    void getEnemyPowerFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.getEnemyPower(1L,1L));
    }
    @Test
    void getArmyPowerSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(game.getResources().getArmy().getArmyInfo().equals(c.getArmyPower(1L)));
    }
    @Test
    void getArmyPowerFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Assertions.assertNull(c.getArmyPower(2L));
    }
    @Test
    void attackPlanetSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(c.attackPlanet(1L,1L));

    }
    @Test
    void attackPlanetFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertFalse(c.attackPlanet(1L,1L));
    }
    @Test
    void hireUnitSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertEquals(game, c.getGameById(game.getGameId()));
        Assertions.assertEquals(army, c.getArmyById(army.getArmyId()));
        Assertions.assertEquals(resources, c.getResourcesById(resources.getResourcesId()));
        readGame(game);
        readGame(c.hireUnit(1L,1L));
        assertNotEquals(game.getResources().getArmy().getUnits(), c.hireUnit(3L, 1L).getResources().getArmy().getUnits());
    }
    @Test
    void hireUnitFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.hireUnit(1L,1L));
    }
    @Test
    void getBuildingsInfoSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(game.getResources().getBuildingList().equals(c.getBuildingsInfo(1L)));
        c.deleteUniverse(1L);
    }
    @Test
    void getBuildingsInfoFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.getBuildingsInfo(1L));
    }
    @Test
    void addBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
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
    void addBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.addBuilding(1L,1L));
    }
    @Test
    void removeBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(c.addBuilding(1L, 1L).getResources().getBuildingList()) );
        Assertions.assertFalse(game.getResources().getBuildingList().equals(c.removeBuilding(1L, 1L).getResources().getBuildingList()) );
        c.deleteUniverse(1L);
    }
    @Test
    void removeBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.removeBuilding(1L,1L));
    }
    @Test
    void manageResources1Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.manageResources(1L,1));

    }
    @Test
    void manageResources1Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        assertEquals(game.getResources().getBuildingList(), c.manageResources(1L, 1).getResources().getBuildingList());
        c.deleteUniverse(1L);
    }
    @Test
    void testManageResources2Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
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
    void testManageResources2Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.manageResources(1L,2,1L));
    }
    @Test
    void testManageResources3Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        assertNotEquals(game.getResources().getBuildingList(), c.manageResources(1L, 2, 1L).getResources().getBuildingList());
        c.manageResources(1L, 3, 1L).getResources().getBuildingList();
        assertEquals(game.getResources().getBuildingList(),c.getGameById(1L).getResources().getBuildingList() );
        c.deleteUniverse(1L);
    }
    @Test
    void testManageResources3Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.manageResources(1L,3,1L));
    }
    @Test
    void testManageResources4Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        c.deleteUniverse(1L);
        c.deletePlayerPlanetById(playerPlanet.getPlanetId());
        c.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        c.deleteBuildingById(building.getBuildingId());
        c.deleteUnitById(unit.getUnitId());
        c.createUnit(unit);
        c.createBuilding(building);
        c.createEnemyPlanet(enemyPlanet);
        c.createPlayerPlanet(playerPlanet);
        c.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(c.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(c.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(c.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(c.manageResources(1L,4,1L));
        Assertions.assertFalse(game.getResources().getArmy().getUnits().equals(c.manageResources(1L,4,3L).getResources().getArmy().getUnits()) );
        c.deleteUniverse(1L);
    }
    @Test
    void testManageResources4Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        c.deleteUniverse(1L);
        Assertions.assertNull(c.manageResources(1L,4,3L));
    }
}