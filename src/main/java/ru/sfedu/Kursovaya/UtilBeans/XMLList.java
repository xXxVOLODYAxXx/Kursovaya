package ru.sfedu.Kursovaya.UtilBeans;

import ru.sfedu.Kursovaya.Beans.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLList {
    @XmlElement(name="Unit")
    private List<Unit> unitList=null;
    @XmlElement(name="Building")
    private List<Building> buildingList=null;
    @XmlElement(name="PlayerPlanet")
    private List<PlayerPlanet> playerPlanetList=null;
    @XmlElement(name="EnemyPlanet")
    private List<EnemyPlanet> enemyPlanetList=null;
    @XmlElement(name="Army")
    private List<Army> armyList=null;
    @XmlElement(name="Resources")
    private List<Resources> resourcesList=null;
    @XmlElement(name="Game")
    private List<Game> gameList=null;

    public List<Game> getGameList() {
        return this.gameList;
    }
    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }
    public List<Resources> getResourcesList() {
        return this.resourcesList;
    }
    public void setResourcesList(List<Resources> resourcesList) {
        this.resourcesList = resourcesList;
    }
    public List<Army> getArmyList() {
        return this.armyList;
    }
    public void setArmyList(List<Army> armyList) {
        this.armyList = armyList;
    }
    public List<EnemyPlanet> getEnemyPlanetList() {
        return this.enemyPlanetList;
    }
    public void setEnemyPlanetList(List<EnemyPlanet> enemyPlanetList) {
        this.enemyPlanetList = enemyPlanetList;
    }
    public List<PlayerPlanet> getPlayerPlanetList() {
        return this.playerPlanetList;
    }
    public void setPlayerPlanetList(List<PlayerPlanet> playerPlanetList) {
        this.playerPlanetList = playerPlanetList;
    }
    public List<Unit> getUnits(){
        return unitList;
    }
    public List<Building> getBuildings(){
        return buildingList;
    }
    public void setUnits(List<Unit> unitList){
        this.unitList=unitList;
    }
    public void setBuildings(List<Building> buildingList){
        this.buildingList=buildingList;
    }
}
