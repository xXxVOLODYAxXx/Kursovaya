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
    XMLList xl = new XMLList();
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
        enemyPlanet.setPlanetId(2L);
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
        log.info(unit.getUnitId());
        log.info(unit.getUnitType());
        log.info(unit.getUnitAttackPoints());
        log.info(unit.getUnitHealthPoints());
        log.info(unit.getFoodRequired());
        log.info(unit.getGoldRequired());
        log.info(unit.getMetalRequired());
    }
    @Test
    public void readBuilding(){
        initBuilding();
        log.info(building.getBuildingId());
        log.info(building.getBuildingType());
        log.info(building.getFoodRequired());
        log.info(building.getGoldRequired());
        log.info(building.getMetalRequired());
        log.info(building.getFoodBuff());
        log.info(building.getGoldBuff());
        log.info(building.getMetalBuff());
    }
    @Test
    public void readEnemyPlanet(){
        initEnemyPlanet();
        log.info(enemyPlanet.getPlanetId());
        log.info(enemyPlanet.getPlanetName());
        log.info(enemyPlanet.getPlanetType());
        log.info(enemyPlanet.getEnemyAttackPoints());
        log.info(enemyPlanet.getEnemyHealthPoints());
    }

    public void readEnemyPlanet(EnemyPlanet enemyPlanet){
        try {
            log.info(enemyPlanet.getPlanetName());
            log.info(enemyPlanet.getPlanetType());
            log.info(enemyPlanet.getEnemyAttackPoints());
            log.info(enemyPlanet.getEnemyHealthPoints());
            log.info(enemyPlanet.getPlanetId());
        } catch (NullPointerException e) {}

    }
    @Test
    public void readPlayerPlanet(){
        initPlayerPlanet();
        log.info(playerPlanet.getPlanetId());
        log.info(playerPlanet.getPlanetName());
        log.info(playerPlanet.getPlanetType());
        log.info(playerPlanet.getBuildingLimit());
    }
    @Test
    public void readArmyInfo(){
        initArmyInfo();
        log.info(armyInfo.getArmyAttackPoints());
        log.info(armyInfo.getArmyHealthPoints());
    }

    public void readArmyInfo(ArmyInfo armyInfo){
        try {
            log.info(armyInfo.getArmyAttackPoints());
            log.info(armyInfo.getArmyHealthPoints());
        }catch (NullPointerException | ParameterResolutionException ignored){}

    }
    @Test
    public void readArmy(){
        initArmy();
        log.info(army.getArmyId());
        log.info(army.getArmyInfo());
        log.info(army.getUnits());
    }

    public void readArmy(Army army){
        log.info("ARMY");
        log.info(army.getArmyId());
        log.info(army.getArmyInfo());
        log.info(army.getUnits());
    }
    @Test
    public void readResources(){
        initResources();
        log.info(resources.getResourcesId());
        log.info(resources.getArmy());
        log.info(resources.getBuildingList());
        log.info(resources.getFood());
        log.info(resources.getGold());
        log.info(resources.getMetal());
        log.info(resources.getOperation());
    }

    public void readResources(Resources resources){
        try {
        log.info("RESOURCES");
        log.info(resources.getResourcesId());
        log.info(resources.getArmy());
        log.info(resources.getBuildingList());
        log.info(resources.getFood());
        log.info(resources.getGold());
        log.info(resources.getMetal());
        log.info(resources.getOperation());
        } catch (NullPointerException | ParameterResolutionException ignored){}
    }
    @Test
    public void readGame(){
        initGame();
        log.info(game.getGameId());
        log.info(game.getGameName());
        log.info(game.getEnemyPlanetList());
        log.info(game.getPlayerPlanetList());
        log.info(game.getResources());
    }

    public void readGame(Game game){
        try {
            log.info("GAME");
            log.info(game.getGameId());
            log.info(game.getGameName());
            log.info(game.getEnemyPlanetList());
            log.info(game.getPlayerPlanetList());
            log.info(game.getResources());
        } catch (NullPointerException | ParameterResolutionException ignored){}

    }



}
