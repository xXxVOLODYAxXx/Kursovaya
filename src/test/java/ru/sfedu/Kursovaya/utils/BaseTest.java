package ru.sfedu.Kursovaya.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.Kursovaya.Beans.Unit;
import ru.sfedu.Kursovaya.Beans.XMLList;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class BaseTest {
    public BaseTest() throws JAXBException, IOException {}

    Unit unit=new Unit();
    XMLList xl=new XMLList();
    XMLDataProvider x=new XMLDataProvider();
    CSVDataProvider c=new CSVDataProvider();

    public JDBCDataProvider j=new JDBCDataProvider();
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


    public Unit initializeUnit(){
        unit.setUnitId(1L);
        unit.setUnitType("MELEE");
        unit.setUnitAttackPoints(2);
        unit.setUnitHealthPoints(1);
        unit.setFoodRequired(1);
        unit.setMetalRequired(1);
        unit.setGoldRequired(1);
        return unit;
    }

    @Test
    public void readUnit(){
        initializeUnit();
        System.out.println(unit.getUnitId());
        System.out.println(unit.getUnitType());
        System.out.println(unit.getUnitAttackPoints());
        System.out.println(unit.getUnitHealthPoints());
        System.out.println(unit.getFoodRequired());
        System.out.println(unit.getGoldRequired());
        System.out.println(unit.getMetalRequired());
    }


}
