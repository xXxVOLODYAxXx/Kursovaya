package ru.sfedu.Kursovaya.utils.OtherUtils;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.api.DataProviders.CSVDataProvider;
import ru.sfedu.Kursovaya.api.DataProviders.JDBCDataProvider;
import ru.sfedu.Kursovaya.api.DataProviders.XMLDataProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeansGenerator {
    private static final Logger log = LogManager.getLogger(BeansGenerator.class);
    CSVDataProvider csvdp=new CSVDataProvider();
    JDBCDataProvider jdbcDataProvider=new JDBCDataProvider();
    XMLDataProvider xmlDataProvider=new XMLDataProvider();

    public BeansGenerator() throws IOException, JAXBException {
    }
    public Unit generateMarksman() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
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
    public Unit generateMelee() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
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
    public Unit generateTank() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
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
    public Building generateFarm() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
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
    public Building generateBank() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        Building building=new Building();
        building.setBuildingId(2L);
        building.setBuildingType("Bank");
        building.setGoldRequired(5);
        building.setMetalRequired(5);
        building.setFoodRequired(0);
        building.setGoldBuff(20);
        building.setMetalBuff(0);
        building.setFoodBuff(0);

        return building;
    }
    public Building generateFactory() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        Building building=new Building();
        building.setBuildingId(3L);
        building.setBuildingType("Factory");
        building.setGoldRequired(5);
        building.setMetalRequired(5);
        building.setFoodRequired(0);
        building.setGoldBuff(0);
        building.setMetalBuff(20);
        building.setFoodBuff(0);

        return building;
    }
    public PlayerPlanet generatePlayerPlanet() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        PlayerPlanet playerPlanet=new PlayerPlanet();
        playerPlanet.setPlanetId(0L);
        playerPlanet.setPlanetName("Alpha");
        playerPlanet.setBuildingLimit(10);
        playerPlanet.setPlanetType("PLAYER");

        return playerPlanet;
    }
    public PlayerPlanet generatePlayerPlanet1() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        PlayerPlanet enemyPlanet=new PlayerPlanet();
        enemyPlanet.setPlanetId(1L);
        enemyPlanet.setPlanetName("Beta");
        enemyPlanet.setPlanetType("PLAYER");
        enemyPlanet.setBuildingLimit(10);

        return enemyPlanet;
    }
    public PlayerPlanet generatePlayerPlanet2() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        PlayerPlanet enemyPlanet=new PlayerPlanet();
        enemyPlanet.setPlanetId(2L);
        enemyPlanet.setPlanetName("Gamma");
        enemyPlanet.setPlanetType("PLAYER");
        enemyPlanet.setBuildingLimit(10);

        return enemyPlanet;
    }
    public PlayerPlanet generatePlayerPlanet3() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        PlayerPlanet enemyPlanet=new PlayerPlanet();
        enemyPlanet.setPlanetId(3L);
        enemyPlanet.setPlanetName("Delta");
        enemyPlanet.setPlanetType("PLAYER");
        enemyPlanet.setBuildingLimit(10);

        return enemyPlanet;
    }
    public PlayerPlanet generatePlayerPlanet4() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        PlayerPlanet enemyPlanet=new PlayerPlanet();
        enemyPlanet.setPlanetId(4L);
        enemyPlanet.setPlanetName("Sigma");
        enemyPlanet.setPlanetType("PLAYER");
        enemyPlanet.setBuildingLimit(10);

        return enemyPlanet;
    }
    public EnemyPlanet generateEnemyPlanet1() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        EnemyPlanet enemyPlanet=new EnemyPlanet();
        enemyPlanet.setPlanetId(1L);
        enemyPlanet.setPlanetName("Beta");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(50);
        enemyPlanet.setEnemyHealthPoints(50);
        return enemyPlanet;
    }
    public EnemyPlanet generateEnemyPlanet2() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        EnemyPlanet enemyPlanet=new EnemyPlanet();
        enemyPlanet.setPlanetId(2L);
        enemyPlanet.setPlanetName("Gamma");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(100);
        enemyPlanet.setEnemyHealthPoints(100);
        return enemyPlanet;
    }
    public EnemyPlanet generateEnemyPlanet3() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
        EnemyPlanet enemyPlanet=new EnemyPlanet();
        enemyPlanet.setPlanetId(3L);
        enemyPlanet.setPlanetName("Delta");
        enemyPlanet.setPlanetType("ENEMY");
        enemyPlanet.setEnemyAttackPoints(150);
        enemyPlanet.setEnemyHealthPoints(150);
        return enemyPlanet;
    }
    public EnemyPlanet generateEnemyPlanet4() throws CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException, IOException, JAXBException {
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
    public Game defaultPresetXML(Long id,Army army,Resources resources,Game game) throws IOException, SQLException, JAXBException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
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
        game.setPlayerPlanetList(xmlDataProvider.getPlayerPlanetList());
        game.setEnemyPlanetList(xmlDataProvider.getEnemyPlanetList());
        return game;
    }
    public Game defaultPresetJDBC(Long id,Army army,Resources resources,Game game) throws IOException, SQLException, CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException {
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
        List<PlayerPlanet> playerPlanetList=new ArrayList<>();
        playerPlanetList.add(generatePlayerPlanet());
        List<EnemyPlanet> enemyPlanetList=new ArrayList<>();
        enemyPlanetList.add(generateEnemyPlanet1());
        enemyPlanetList.add(generateEnemyPlanet2());
        enemyPlanetList.add(generateEnemyPlanet3());
        enemyPlanetList.add(generateEnemyPlanet4());
        game.setPlayerPlanetList(playerPlanetList);
        game.setEnemyPlanetList(enemyPlanetList);
        return game;
    }
    public Game defaultPresetCSV(Long id,Army army,Resources resources,Game game) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
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
        return game;
    }
    public void defaultBeansCSV() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
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
    public void defaultBeansXML() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        xmlDataProvider.deleteUnitById(generateMelee().getUnitId());
        xmlDataProvider.deleteUnitById(generateMarksman().getUnitId());
        xmlDataProvider.deleteUnitById(generateTank().getUnitId());
        xmlDataProvider.deleteBuildingById(generateFarm().getBuildingId());
        xmlDataProvider.deleteBuildingById(generateBank().getBuildingId());
        xmlDataProvider.deleteBuildingById(generateFactory().getBuildingId());
        xmlDataProvider.deletePlayerPlanetById(generatePlayerPlanet().getPlanetId());
        xmlDataProvider.deleteEnemyPlanetById(generateEnemyPlanet1().getPlanetId());
        xmlDataProvider.deleteEnemyPlanetById(generateEnemyPlanet2().getPlanetId());
        xmlDataProvider.deleteEnemyPlanetById(generateEnemyPlanet3().getPlanetId());
        xmlDataProvider.deleteEnemyPlanetById(generateEnemyPlanet4().getPlanetId());
        xmlDataProvider.createUnit(generateMelee());
        xmlDataProvider.createUnit(generateMarksman());
        xmlDataProvider.createUnit(generateTank());
        xmlDataProvider.createBuilding(generateFarm());
        xmlDataProvider.createBuilding(generateBank());
        xmlDataProvider.createBuilding(generateFactory());
        xmlDataProvider.createEnemyPlanet(generateEnemyPlanet1());
        xmlDataProvider.createEnemyPlanet(generateEnemyPlanet2());
        xmlDataProvider.createEnemyPlanet(generateEnemyPlanet3());
        xmlDataProvider.createEnemyPlanet(generateEnemyPlanet4());
        xmlDataProvider.createPlayerPlanet(generatePlayerPlanet());
    }
    public void defaultBeansJDBC() throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException, SQLException, JAXBException {
        try {jdbcDataProvider.dropDefault();} catch (JdbcSQLSyntaxErrorException e){}
        jdbcDataProvider.insertUnit(generateMelee());
        jdbcDataProvider.insertUnit(generateMarksman());
        jdbcDataProvider.insertUnit(generateTank());
        jdbcDataProvider.insertBuilding(generateFarm());
        jdbcDataProvider.insertBuilding(generateBank());
        jdbcDataProvider.insertBuilding(generateFactory());
        jdbcDataProvider.insertEnemyPlanet(generateEnemyPlanet1());
        jdbcDataProvider.insertEnemyPlanet(generateEnemyPlanet2());
        jdbcDataProvider.insertEnemyPlanet(generateEnemyPlanet3());
        jdbcDataProvider.insertEnemyPlanet(generateEnemyPlanet4());
        jdbcDataProvider.insertPlayerPlanet(generatePlayerPlanet());
        jdbcDataProvider.insertPlayerPlanet(generatePlayerPlanet1());
        jdbcDataProvider.insertPlayerPlanet(generatePlayerPlanet2());
        jdbcDataProvider.insertPlayerPlanet(generatePlayerPlanet3());
        jdbcDataProvider.insertPlayerPlanet(generatePlayerPlanet4());
    }
}
