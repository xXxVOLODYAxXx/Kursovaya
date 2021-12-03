package ru.sfedu.Kursovaya.utils.OtherUtils;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.utils.DataProviders.CSVDataProvider;

import java.io.IOException;

public class BeansGenerator {
    private static final Logger log = LogManager.getLogger(BeansGenerator.class);
    CSVDataProvider csvdp=new CSVDataProvider();

    public BeansGenerator() throws IOException {
    }
    public Unit generateMarksman(){
        Unit unit=new Unit();
        unit.setUnitId(1L);
        unit.setUnitType("Marksman");
        unit.setUnitAttackPoints(5);
        unit.setUnitHealthPoints(2);
        unit.setFoodRequired(2);
        unit.setMetalRequired(2);
        unit.setGoldRequired(2);
        return unit;
    }
    public Unit generateMelee(){
        Unit unit=new Unit();
        unit.setUnitId(2L);
        unit.setUnitType("Melee");
        unit.setUnitAttackPoints(2);
        unit.setUnitHealthPoints(5);
        unit.setFoodRequired(2);
        unit.setMetalRequired(2);
        unit.setGoldRequired(2);
        return unit;
    }
    public Unit generateTank(){
        Unit unit=new Unit();
        unit.setUnitId(3L);
        unit.setUnitType("Tank");
        unit.setUnitAttackPoints(10);
        unit.setUnitHealthPoints(10);
        unit.setFoodRequired(5);
        unit.setMetalRequired(5);
        unit.setGoldRequired(5);
        return unit;
    }
    public Building generateFarm(){
        Building building=new Building();
        building.setBuildingId(1L);
        building.setBuildingType("Farm");
        building.setGoldRequired(5);
        building.setMetalRequired(0);
        building.setFoodRequired(0);
        building.setGoldBuff(0);
        building.setMetalBuff(0);
        building.setFoodBuff(10);
        return building;
    }
    public Building generateBank(){
        Building building=new Building();
        building.setBuildingId(1L);
        building.setBuildingType("Bank");
        building.setGoldRequired(5);
        building.setMetalRequired(5);
        building.setFoodRequired(0);
        building.setGoldBuff(20);
        building.setMetalBuff(0);
        building.setFoodBuff(0);
        return building;
    }
    public Building generateFactory(){
        Building building=new Building();
        building.setBuildingId(1L);
        building.setBuildingType("Factory");
        building.setGoldRequired(5);
        building.setMetalRequired(5);
        building.setFoodRequired(0);
        building.setGoldBuff(0);
        building.setMetalBuff(20);
        building.setFoodBuff(0);
        return building;
    }
    public PlayerPlanet generatePlayerPlanet(){
        PlayerPlanet playerPlanet=new PlayerPlanet();
        playerPlanet.setPlanetId(0L);
        playerPlanet.setPlanetName("Alpha");
        playerPlanet.setBuildingLimit(10);
        playerPlanet.setPlanetType("PLAYER");
        return playerPlanet;
    }
    public EnemyPlanet generateEnemyPlanet1(){
        EnemyPlanet enemyPlanet=new EnemyPlanet();
        enemyPlanet.setPlanetId(1L);
        enemyPlanet.setPlanetName("Beta");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(50);
        enemyPlanet.setEnemyHealthPoints(50);
        return enemyPlanet;
    }
    public EnemyPlanet generateEnemyPlanet2(){
        EnemyPlanet enemyPlanet=new EnemyPlanet();
        enemyPlanet.setPlanetId(2L);
        enemyPlanet.setPlanetName("Gamma");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(100);
        enemyPlanet.setEnemyHealthPoints(100);
        return enemyPlanet;
    }
    public EnemyPlanet generateEnemyPlanet3(){
        EnemyPlanet enemyPlanet=new EnemyPlanet();
        enemyPlanet.setPlanetId(3L);
        enemyPlanet.setPlanetName("Delta");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(150);
        enemyPlanet.setEnemyHealthPoints(150);
        return enemyPlanet;
    }
    public EnemyPlanet generateEnemyPlanet4(){
        EnemyPlanet enemyPlanet=new EnemyPlanet();
        enemyPlanet.setPlanetId(4L);
        enemyPlanet.setPlanetName("Sigma");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(200);
        enemyPlanet.setEnemyHealthPoints(200);
        return enemyPlanet;
    }
    public ArmyInfo generateArmyInfo(){
        ArmyInfo armyInfo=new ArmyInfo();
        armyInfo.setArmyAttackPoints(75);
        armyInfo.setArmyHealthPoints(75);
        return armyInfo;
    }
    public void defaultPreset(Long id,Army army,Resources resources,Game game) throws IOException {
        army.setArmyId(id);
        army.setArmyInfo(generateArmyInfo());
        resources.setResourcesId(id);
        resources.setArmy(army);
        resources.setMetal(Constants.DEFAULT_METAL);
        resources.setGold(Constants.DEFAULT_GOLD);
        resources.setFood(Constants.DEFAULT_FOOD);
        game.setGameId(id);
        game.setGameName(Constants.FILE_NAME_GAME);
        game.setResources(resources);
        game.setPlayerPlanetList(csvdp.getPlayerPlanetList());
        game.setEnemyPlanetList(csvdp.getEnemyPlanetList());
    }
    public void defaultBeans() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        csvdp.deleteUnitById(generateMelee().getUnitId());
        csvdp.deleteUnitById(generateMarksman().getUnitId());
        csvdp.deleteUnitById(generateTank().getUnitId());
        csvdp.deleteBuildingById(generateFarm().getBuildingId());
        csvdp.deleteBuildingById(generateBank().getBuildingId());
        csvdp.deleteBuildingById(generateFactory().getBuildingId());
        csvdp.deletePlayerPlanetById(generatePlayerPlanet().getPlanetId());
        csvdp.deleteEnemyPlanetById(generateEnemyPlanet1().getPlanetId());
        csvdp.deleteEnemyPlanetById(generateEnemyPlanet2().getPlanetId());
        csvdp.deleteEnemyPlanetById(generateEnemyPlanet3().getPlanetId());
        csvdp.deleteEnemyPlanetById(generateEnemyPlanet4().getPlanetId());
        csvdp.createUnit(generateMelee());
        csvdp.createUnit(generateMarksman());
        csvdp.createUnit(generateTank());
        csvdp.createBuilding(generateFarm());
        csvdp.createBuilding(generateBank());
        csvdp.createBuilding(generateFactory());
        csvdp.createEnemyPlanet(generateEnemyPlanet1());
        csvdp.createEnemyPlanet(generateEnemyPlanet2());
        csvdp.createEnemyPlanet(generateEnemyPlanet3());
        csvdp.createEnemyPlanet(generateEnemyPlanet4());
        csvdp.createPlayerPlanet(generatePlayerPlanet());
    }
}
