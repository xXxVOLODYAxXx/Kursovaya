package ru.sfedu.Kursovaya.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.utils.DataProviders.AbstractDataProvider;
import ru.sfedu.Kursovaya.utils.DataProviders.CSVDataProvider;
import ru.sfedu.Kursovaya.utils.DataProviders.JDBCDataProvider;
import ru.sfedu.Kursovaya.utils.DataProviders.XMLDataProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {
    public BaseTest() throws JAXBException, IOException {}

    Unit unit = new Unit();
    Building building = new Building();
    PlayerPlanet playerPlanet = new PlayerPlanet();
    EnemyPlanet enemyPlanet = new EnemyPlanet();
    HistoryContent historyContent = new HistoryContent();
    Army army=new Army();
    Game game=new Game();
    Resources resources=new Resources();
    List<Building> buildingList=new ArrayList<Building>();
    List<Unit> unitList=new ArrayList<Unit>();
    List<EnemyPlanet> enemyPlanetList=new ArrayList<EnemyPlanet>();
    List<PlayerPlanet> playerPlanetList=new ArrayList<PlayerPlanet>();
    ArmyInfo armyInfo=new ArmyInfo();
    AbstractDataProvider abstractDataProvider=new AbstractDataProvider();
    XMLList xl = new XMLList();
    XMLDataProvider x = new XMLDataProvider();
    CSVDataProvider c = new CSVDataProvider();

    public JDBCDataProvider j = new JDBCDataProvider();
    Logger log = LogManager.getLogger(CSVDataProvider.class);


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
        unit.setUnitId(1L);
        unit.setUnitType("MELEE");
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
    public Army initArmy(){
        army.setArmyId(1L);
        unitList.add(initUnit());
        unitList.add(initUnit());
        armyInfo.setArmyHealthPoints(10);
        armyInfo.setArmyHealthPoints(10);
        army.setArmyInfo(armyInfo);
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
        enemyPlanetList.add(initEnemyPlanet());
        game.setEnemyPlanetList(enemyPlanetList);
        playerPlanetList.add(initPlayerPlanet());
        playerPlanetList.add(initPlayerPlanet());
        game.setPlayerPlanetList(playerPlanetList);
        game.setResources(initResources());
        return game;
    }
    @Test
    public void readUnit(){
        initUnit();
        System.out.println(unit.getUnitId());
        System.out.println(unit.getUnitType());
        System.out.println(unit.getUnitAttackPoints());
        System.out.println(unit.getUnitHealthPoints());
        System.out.println(unit.getFoodRequired());
        System.out.println(unit.getGoldRequired());
        System.out.println(unit.getMetalRequired());
    }


}
