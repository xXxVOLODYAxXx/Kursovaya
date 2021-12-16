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
    void createUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        assertEquals(game, x.getGameById(game.getGameId()));
        assertEquals(army, x.getArmyById(army.getArmyId()));
        assertEquals(resources, x.getResourcesById(resources.getResourcesId()));

    }
    @Test
    void createUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        Assertions.assertNull(x.createUniverse(game,resources,army));
    }
    @Test
    void deleteUniverseSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        x.deleteUniverse(1L);
        Assertions.assertNull(x.getGameById(1L));
    }
    @Test
    void deleteUniverseFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, JAXBException {
        x.deleteUniverse(1L);
        x.deleteUniverse(1L);
        Assertions.assertNull(x.getGameById(1L));
    }
    @Test
    void getEnemyPowerSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(enemyPlanet.equals(x.getEnemyPower(1L,1L)));
    }
    @Test
    void getEnemyPowerFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.getEnemyPower(1L,1L));
    }
    @Test
    void getArmyPowerSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(game.getResources().getArmy().getArmyInfo().equals(x.getArmyPower(1L)));
    }
    @Test
    void getArmyPowerFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Assertions.assertNull(x.getArmyPower(2L));
    }
    @Test
    void attackPlanetSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(x.attackPlanet(1L,1L));

    }
    @Test
    void attackPlanetFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertFalse(x.attackPlanet(1L,1L));
    }
    @Test
    void hireUnitSuccess() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertEquals(game, x.getGameById(game.getGameId()));
        Assertions.assertEquals(army, x.getArmyById(army.getArmyId()));
        Assertions.assertEquals(resources, x.getResourcesById(resources.getResourcesId()));
        readGame(game);
        readGame(x.hireUnit(1L,1L));
        assertNotEquals(game.getResources().getArmy().getUnits(), x.hireUnit(3L, 1L).getResources().getArmy().getUnits());
    }
    @Test
    void hireUnitFault() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.hireUnit(1L,1L));
    }
    @Test
    void getBuildingsInfoSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        Assertions.assertTrue(game.getResources().getBuildingList().equals(x.getBuildingsInfo(1L)));
        x.deleteUniverse(1L);
    }
    @Test
    void getBuildingsInfoFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.getBuildingsInfo(1L));
    }
    @Test
    void addBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(x.addBuilding(1L,1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.addBuilding(1L, 1L).getResources().getBuildingList()) );
        x.deleteUniverse(1L);
    }
    @Test
    void addBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.addBuilding(1L,1L));
    }
    @Test
    void removeBuildingSuccess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.addBuilding(1L, 1L).getResources().getBuildingList()) );
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.removeBuilding(1L, 1L).getResources().getBuildingList()) );
        x.deleteUniverse(1L);
    }
    @Test
    void removeBuildingFault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.removeBuilding(1L,1L));
    }
    @Test
    void manageResources1Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.manageResources(1L,1));

    }
    @Test
    void manageResources1Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        assertEquals(game.getResources().getBuildingList(), x.manageResources(1L, 1).getResources().getBuildingList());
        x.deleteUniverse(1L);
    }
    @Test
    void testManageResources2Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(x.manageResources(1L,2,1L));
        Assertions.assertFalse(game.getResources().getBuildingList().equals(x.manageResources(1L,2,1L).getResources().getBuildingList()) );
        x.deleteUniverse(1L);
    }
    @Test
    void testManageResources2Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.manageResources(1L,2,1L));
    }
    @Test
    void testManageResources3Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        assertNotEquals(game.getResources().getBuildingList(), x.manageResources(1L, 2, 1L).getResources().getBuildingList());
        x.manageResources(1L, 3, 1L).getResources().getBuildingList();
        assertEquals(game.getResources().getBuildingList(),x.getGameById(1L).getResources().getBuildingList() );
        x.deleteUniverse(1L);
    }
    @Test
    void testManageResources3Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.manageResources(1L,3,1L));
    }
    @Test
    void testManageResources4Success() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        game=initGame();
        unit=initUnit();
        building=initBuilding();
        playerPlanet=initPlayerPlanet();
        enemyPlanet=initEnemyPlanet();
        x.deleteUniverse(1L);
        x.deletePlayerPlanetById(playerPlanet.getPlanetId());
        x.deleteEnemyPlanetById(enemyPlanet.getPlanetId());
        x.deleteBuildingById(building.getBuildingId());
        x.deleteUnitById(unit.getUnitId());
        x.createUnit(unit);
        x.createBuilding(building);
        x.createEnemyPlanet(enemyPlanet);
        x.createPlayerPlanet(playerPlanet);
        x.createUniverse(game,resources,army);
        Assertions.assertTrue(game.equals(x.getGameById(game.getGameId())));
        Assertions.assertTrue(army.equals(x.getArmyById(army.getArmyId())));
        Assertions.assertTrue(resources.equals(x.getResourcesById(resources.getResourcesId())));
        readGame(game);
        readGame(x.manageResources(1L,4,1L));
        Assertions.assertFalse(game.getResources().getArmy().getUnits().equals(x.manageResources(1L,4,3L).getResources().getArmy().getUnits()) );
        x.deleteUniverse(1L);
    }
    @Test
    void testManageResources4Fault() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException, JAXBException {
        x.deleteUniverse(1L);
        Assertions.assertNull(x.manageResources(1L,4,1L));
    }
}
