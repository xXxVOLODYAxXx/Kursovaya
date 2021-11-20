package ru.sfedu.Kursovaya.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.Kursovaya.Beans.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    public BaseTest() throws JAXBException, IOException {}

    Unit unit = new Unit();
    Building building = new Building();
    PlayerPlanet playerPlanet = new PlayerPlanet();
    EnemyPlanet enemyPlanet = new EnemyPlanet();
    HistoryContent historyContent = new HistoryContent();
    XMLList xl = new XMLList();
    XMLDataProvider x = new XMLDataProvider();
    CSVDataProvider c = new CSVDataProvider();

    public JDBCDataProvider j = new JDBCDataProvider();
    Logger log = LogManager.getLogger(CSVDataProvider.class);
    String createTableSQL = "create table users (\r\n"
            + "  id  int(3) primary key,\r\n"
            + "  name varchar(20),\r\n"
            + "  email varchar(20),\r\n"
            + "  country varchar(20),\r\n"
            + "  password varchar(20)\r\n"
            + "  );";
    String INSERT_USERS_SQL = "INSERT INTO users"
            + "  (id, name, email, country, password) VALUES "
            + " (3, ?, ?, ?, ?);";

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
        playerPlanet.setType("PLAYER");
        return playerPlanet;
    }
    public EnemyPlanet initEnemyPlanet(){
        enemyPlanet.setPlanetId(1L);
        enemyPlanet.setPlanetName("B");
        enemyPlanet.setType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(50);
        enemyPlanet.setEnemyHealthPoints(50);
        return enemyPlanet;
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
