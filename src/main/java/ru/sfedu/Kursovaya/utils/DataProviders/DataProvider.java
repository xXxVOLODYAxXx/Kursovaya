package ru.sfedu.Kursovaya.utils.DataProviders;

import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.Beans.Building;
import ru.sfedu.Kursovaya.Beans.EnemyPlanet;
import ru.sfedu.Kursovaya.Beans.Game;

import java.util.List;

public interface DataProvider {
    public Game createUniverse(Long id) throws Exception;
    public Boolean deleteUniverse(Long id) throws Exception;
    public EnemyPlanet getEnemyPower(Long planetId, Long gameId) throws Exception;
    public ArmyInfo getArmyPower(Long gameId) throws Exception;
    public Boolean attackPlanet(Long enemyPlanetId,Long gameId) throws Exception;
    public Game hireUnit(Long unitId,Long gameId) throws Exception;
    public List<Building> getBuildingsInfo(Long gameId) throws Exception;
    public Game addBuilding(Long buildingId,Long gameId) throws Exception;
    public Game removeBuilding(Long buildingId,Long gameId) throws Exception;
    public Game manageResources(Long gameId,int operation,Long id) throws Exception;
    public Game manageResources(Long gameId,int operation) throws Exception;
}
