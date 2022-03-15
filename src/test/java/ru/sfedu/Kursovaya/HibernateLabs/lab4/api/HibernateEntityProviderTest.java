package ru.sfedu.Kursovaya.HibernateLabs.lab4.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ru.sfedu.Kursovaya.HibernateLabs.lab4.models.HibernateArmy;
import ru.sfedu.Kursovaya.HibernateLabs.lab4.models.HibernateUnit;

import java.io.IOException;
import java.util.*;



public class HibernateEntityProviderTest {

    private Logger log = LogManager.getLogger(HibernateEntityProviderTest.class);
    private HibernateEntityProvider dp = new HibernateEntityProvider();

    @Test
    public void createHibernateArmy() throws IOException {
        Map<String, String> unitType = new HashMap<>();
        unitType.put("Marksman", "Marksman");
        unitType.put("Tank", "Tank");

        List<String> units = new ArrayList<>();
        units.add("Marksman");
        units.add("Tank");

        Set<String> tags = new HashSet<>();
        tags.add("UnitType:Marksman");
        tags.add("UnitType:Tank");

        Set<HibernateUnit> unitSet = new HashSet<>();
        HibernateUnit unit = new HibernateUnit();
        unit.setUnitId(5L);
        unit.setUnitType("Marksman");
        unitSet.add(unit);

        HibernateArmy army = new HibernateArmy();
        army.setArmyHealthPoints(45);
        army.setTags(tags);
        army.setUnits(units);
        army.setHibernateUnit(unitType);
        army.setUnitSet(unitSet);
        dp.createHibernateArmy(army);
    }

    @Test
    public void getHibernateArmys() throws IOException {
        List<HibernateArmy> armyList = dp.getHibernateArmys();
        log.info(armyList);
    }

    @Test
    public void getHibernateArmyById() throws IOException {
        Long id = 1L;
        HibernateArmy army = dp.getHibernateArmyById(id);
        log.info(army);
    }

    @Test
    public void updateHibernateArmy() throws IOException {
        Map<String, String> unitType = new HashMap<>();
        unitType.put("Marksman", "Marksman");
        unitType.put("Tank", "Tank");

        List<String> units = new ArrayList<>();
        units.add("Marksman");
        units.add("Tank");

        Set<String> tags = new HashSet<>();
        tags.add("UnitType:Marksman");
        tags.add("UnitType:Tank");

        Set<HibernateUnit> unitSet = new HashSet<>();
        HibernateUnit unit = new HibernateUnit();
        unit.setUnitId(5L);
        unit.setUnitType("Marksman");
        unitSet.add(unit);

        HibernateArmy army = new HibernateArmy();
        army.setArmyHealthPoints(45);
        army.setTags(tags);
        army.setUnits(units);
        army.setHibernateUnit(unitType);
        army.setUnitSet(unitSet);
        dp.createHibernateArmy(army);
        army.setArmyHealthPoints(600);
        army = dp.updateHibernateArmy(army);
        log.info(army);
    }
    @Test
    public void deleteHibernateArmy() throws IOException {
        Long id = 2L;
        Boolean isDeleted = dp.deleteHibernateArmy(id);
        log.info(isDeleted);
    }


}