package ru.sfedu.Kursovaya.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.UtilBeans.HistoryContent;
import ru.sfedu.Kursovaya.UtilBeans.XMLList;
import ru.sfedu.Kursovaya.api.DataProviders.CSVDataProvider;
import ru.sfedu.Kursovaya.api.DataProviders.JDBCDataProvider;
import ru.sfedu.Kursovaya.api.DataProviders.XMLDataProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {
    public BaseTest() throws JAXBException, IOException {}

    public Unit unit = new Unit();
    public Building building = new Building();
    public PlayerPlanet playerPlanet = new PlayerPlanet();
    public EnemyPlanet enemyPlanet = new EnemyPlanet();
    public HistoryContent historyContent = new HistoryContent();
    public ArmyInfo armyInfo=new ArmyInfo();
    public Army army=new Army();
    public Resources resources=new Resources();
    public Game game=new Game();
    public List<Building> buildingList=new ArrayList<Building>();
    public List<Unit> unitList=new ArrayList<Unit>();
    public List<EnemyPlanet> enemyPlanetList=new ArrayList<EnemyPlanet>();
    public List<PlayerPlanet> playerPlanetList=new ArrayList<PlayerPlanet>();
    public XMLDataProvider x = new XMLDataProvider();
    public CSVDataProvider c = new CSVDataProvider();
    public JDBCDataProvider j = new JDBCDataProvider();
    public Logger log = LogManager.getLogger(CSVDataProvider.class);


    public HistoryContent initHistoryContent(){
        historyContent.setActor("dadad");
        historyContent.setClassName("dfdssfds");
        historyContent.setCreatedDate("dfgdsfgdf");
        historyContent.setId(1L);
        historyContent.setMethodName("dgdfgdf");
        historyContent.setStatus("fgfdgd");
        Map<String,Object> object = new HashMap<>();
        object.put("Unit",initUnit());
        historyContent.setObject(object);

        return historyContent;
    }
    public Unit initUnit(){
        unit.setUnitId(3L);
        unit.setUnitType("MARKSMAN");
        unit.setUnitAttackPoints(2);
        unit.setUnitHealthPoints(1);
        unit.setFoodRequired(1);
        unit.setMetalRequired(1);
        unit.setGoldRequired(1);
        return unit;
    }
    public Building initBuilding(){
        building.setBuildingId(1L);
        building.setBuildingType("FARM");
        building.setFoodBuff(10);
        building.setGoldBuff(0);
        building.setMetalBuff(0);
        building.setFoodRequired(0);
        building.setGoldRequired(5);
        building.setMetalRequired(5);
        return building;
    }
    public PlayerPlanet initPlayerPlanet(){
        playerPlanet.setPlanetId(1L);
        playerPlanet.setPlanetName("A");
        playerPlanet.setBuildingLimit(10);
        playerPlanet.setPlanetType("PLAYER");
        return playerPlanet;
    }
    public EnemyPlanet initEnemyPlanet(){
        enemyPlanet.setPlanetId(1L);
        enemyPlanet.setPlanetName("B");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(50);
        enemyPlanet.setEnemyHealthPoints(50);
        return enemyPlanet;
    }
    public ArmyInfo initArmyInfo(){
        armyInfo.setArmyAttackPoints(100);
        armyInfo.setArmyHealthPoints(100);
        return armyInfo;
    }
    public Army initArmy(){
        army.setArmyId(1L);
        unitList.add(initUnit());
        unitList.add(initUnit());
        army.setArmyInfo(initArmyInfo());
        army.setUnits(unitList);
        return army;
    }
    public Resources initResources(){
        resources.setResourcesId(1L);
        resources.setArmy(initArmy());
        resources.setFood(10);
        resources.setGold(10);
        resources.setMetal(10);
        resources.setOperation(1);
        buildingList.add(initBuilding());
        buildingList.add(initBuilding());
        resources.setBuildingList(buildingList);
        return resources;
    }
    public Game initGame(){
        game.setGameId(1L);
        game.setGameName("A");
        enemyPlanetList.add(initEnemyPlanet());
        game.setEnemyPlanetList(enemyPlanetList);
        playerPlanetList.add(initPlayerPlanet());
        game.setPlayerPlanetList(playerPlanetList);
        game.setResources(initResources());
        return game;
    }
    @Test
    public void readUnit(){
        initUnit();
        log.debug(unit.getUnitId());
        log.debug(unit.getUnitType());
        log.debug(unit.getUnitAttackPoints());
        log.debug(unit.getUnitHealthPoints());
        log.debug(unit.getFoodRequired());
        log.debug(unit.getGoldRequired());
        log.debug(unit.getMetalRequired());
    }
    @Test
    public void readBuilding(){
        initBuilding();
        log.debug(building.getBuildingId());
        log.debug(building.getBuildingType());
        log.debug(building.getFoodRequired());
        log.debug(building.getGoldRequired());
        log.debug(building.getMetalRequired());
        log.debug(building.getFoodBuff());
        log.debug(building.getGoldBuff());
        log.debug(building.getMetalBuff());
    }
    @Test
    public void readEnemyPlanet(){
        initEnemyPlanet();
        log.debug(enemyPlanet.getPlanetId());
        log.debug(enemyPlanet.getPlanetName());
        log.debug(enemyPlanet.getPlanetType());
        log.debug(enemyPlanet.getEnemyAttackPoints());
        log.debug(enemyPlanet.getEnemyHealthPoints());
    }

    public void readEnemyPlanet(EnemyPlanet enemyPlanet){
        try {
            log.debug(enemyPlanet.getPlanetName());
            log.debug(enemyPlanet.getPlanetType());
            log.debug(enemyPlanet.getEnemyAttackPoints());
            log.debug(enemyPlanet.getEnemyHealthPoints());
            log.debug(enemyPlanet.getPlanetId());
        } catch (NullPointerException e) {}

    }
    @Test
    public void readPlayerPlanet(){
        initPlayerPlanet();
        log.debug(playerPlanet.getPlanetId());
        log.debug(playerPlanet.getPlanetName());
        log.debug(playerPlanet.getPlanetType());
        log.debug(playerPlanet.getBuildingLimit());
    }
    @Test
    public void readArmyInfo(){
        initArmyInfo();
        log.debug(armyInfo.getArmyAttackPoints());
        log.debug(armyInfo.getArmyHealthPoints());
    }

    public void readArmyInfo(ArmyInfo armyInfo){
        try {
            log.debug(armyInfo.getArmyAttackPoints());
            log.debug(armyInfo.getArmyHealthPoints());
        }catch (NullPointerException | ParameterResolutionException ignored){}

    }
    @Test
    public void readArmy(){
        initArmy();
        log.debug(army.getArmyId());
        log.debug(army.getArmyInfo());
        log.debug(army.getUnits());
    }

    public void readArmy(Army army){
        log.debug("ARMY");
        log.debug(army.getArmyId());
        log.debug(army.getArmyInfo());
        log.debug(army.getUnits());
    }
    @Test
    public void readResources(){
        initResources();
        log.debug(resources.getResourcesId());
        log.debug(resources.getArmy());
        log.debug(resources.getBuildingList());
        log.debug(resources.getFood());
        log.debug(resources.getGold());
        log.debug(resources.getMetal());
        log.debug(resources.getOperation());
    }

    public void readResources(Resources resources){
        try {
        log.debug("RESOURCES");
        log.debug(resources.getResourcesId());
        log.debug(resources.getArmy());
        log.debug(resources.getBuildingList());
        log.debug(resources.getFood());
        log.debug(resources.getGold());
        log.debug(resources.getMetal());
        log.debug(resources.getOperation());
        } catch (NullPointerException | ParameterResolutionException ignored){}
    }
    @Test
    public void readGame(){
        initGame();
        log.debug(game.getGameId());
        log.debug(game.getGameName());
        log.debug(game.getEnemyPlanetList());
        log.debug(game.getPlayerPlanetList());
        log.debug(game.getResources());
    }

    public void readGame(Game game){
        try {
            log.debug("GAME");
            log.debug(game.getGameId());
            log.debug(game.getGameName());
            log.debug(game.getEnemyPlanetList());
            log.debug(game.getPlayerPlanetList());
            log.debug(game.getResources());
        } catch (NullPointerException | ParameterResolutionException ignored){}

    }



}
