package ru.sfedu.Kursovaya.api.DataProviders;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.h2.jdbc.JdbcSQLNonTransientConnectionException;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.utils.OtherUtils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;
import ru.sfedu.Kursovaya.utils.OtherUtils.JDBCH2Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JDBCDataProvider extends AbstractDataProvider{
    private static final Logger log = LogManager.getLogger(JDBCDataProvider.class);
    MongoDBDataProvider mongoDBDataProvider=new MongoDBDataProvider();
    private Connection connection=null;
    private Statement statement=null;
    private final String PATH_TO_SQL= ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_SQL);

    public JDBCDataProvider() throws IOException {
    }

    private String getClassName(){
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }
    private String getMethodName(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    private void initConnection() throws SQLException {
        connection = JDBCH2Utils.getConnection();
        statement = connection.createStatement();
    }
    private void closeConnection() throws SQLException {
        connection.close();
        statement.close();
    }
    public void initDataSource() throws IOException, SQLException {
        File file=new File(PATH_TO_SQL);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fileInputStream.read(data);
        fileInputStream.close();
        String query = new String(data, StandardCharsets.UTF_8);
        initConnection();
        //log.debug(query);
        statement.executeUpdate(query);
        closeConnection();
    }
    public void dropTables() throws SQLException {
        initConnection();
        statement.executeUpdate(Constants.DROP_ALL_TABLES);
        closeConnection();
    }
    public void dropGamePlanets() throws SQLException {
        initConnection();
        statement.executeUpdate(Constants.DROP_ALL_GAME_PLANETS);
        closeConnection();
    }
    public void dropResourcesBuilding() throws SQLException {
        initConnection();
        statement.executeUpdate(Constants.DROP_RESOURCES_BUILDINGS);
        closeConnection();
    }
    public void dropArmyUnits() throws SQLException {
        initConnection();
        statement.executeUpdate(Constants.DROP_ARMY_UNITS);
        closeConnection();
    }
    public void dropDefault() throws SQLException {
        initConnection();
        statement.executeUpdate(Constants.DROP_DEFAULT);
        closeConnection();
    }
    public List<Unit> getUnitListFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Unit> unitList=new ArrayList<>();
        while (resultSet.next()){
            Unit unit=new Unit();
            unit.setUnitId(resultSet.getLong(1));
            unit.setUnitType(resultSet.getString(2));
            unit.setUnitAttackPoints(resultSet.getInt(3));
            unit.setUnitHealthPoints(resultSet.getInt(4));
            unit.setGoldRequired(resultSet.getInt(5));
            unit.setMetalRequired(resultSet.getInt(6));
            unit.setFoodRequired(resultSet.getInt(7));
            unitList.add(unit);
        }
        closeConnection();
        return unitList;
    }
    public List<Building> getBuildingListFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Building> buildingList = new ArrayList<>();
        while (resultSet.next()){
            Building building = new Building();
            building.setBuildingId(resultSet.getLong(1));
            building.setBuildingType(resultSet.getString(2));
            building.setFoodBuff(resultSet.getInt(3));
            building.setMetalBuff(resultSet.getInt(4));
            building.setGoldBuff(resultSet.getInt(5));
            building.setFoodRequired(resultSet.getInt(6));
            building.setMetalRequired(resultSet.getInt(7));
            building.setGoldRequired(resultSet.getInt(8));
            buildingList.add(building);
        }
        closeConnection();
        return buildingList;
    }
    public List<EnemyPlanet> getEnemyPlanetListFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<EnemyPlanet> enemyPlanetList = new ArrayList<>();
        while (resultSet.next()){
            EnemyPlanet enemyPlanet = new EnemyPlanet();
            enemyPlanet.setPlanetId(resultSet.getLong(1));
            enemyPlanet.setPlanetName(resultSet.getString(2));
            enemyPlanet.setPlanetType(resultSet.getString(3));
            enemyPlanet.setEnemyHealthPoints(resultSet.getInt(4));
            enemyPlanet.setEnemyAttackPoints(resultSet.getInt(5));
            enemyPlanetList.add(enemyPlanet);
        }
        closeConnection();
        return enemyPlanetList;
    }
    public List<PlayerPlanet> getPlayerPlanetListFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<PlayerPlanet> playerPlanetList = new ArrayList<>();
        while (resultSet.next()){
            PlayerPlanet playerPlanet = new PlayerPlanet();
            playerPlanet.setPlanetId(resultSet.getLong(1));
            playerPlanet.setPlanetName(resultSet.getString(2));
            playerPlanet.setPlanetType(resultSet.getString(3));
            playerPlanet.setBuildingLimit(resultSet.getInt(4));
            playerPlanetList.add(playerPlanet);
        }
        closeConnection();
        return playerPlanetList;
    }
    public List<Army> getArmyListFromResultSet(ResultSet resultSet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        List<Army> armyList = new ArrayList<>();
        while (resultSet.next()){
            Army army = new Army();
            army.setArmyId(resultSet.getLong(1));
            army.setUnits(getArmyUnitById(resultSet.getLong(1)));
            army.setArmyInfo(getArmyInfoId(resultSet.getLong(1)));
            armyList.add(army);
        }
        closeConnection();
        return armyList;
    }
    public List<Resources> getResourcesListFromResultSet(ResultSet resultSet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        List<Resources> resourcesList = new ArrayList<>();
        while (resultSet.next()){
            Resources resources=new Resources();
            resources.setResourcesId(resultSet.getLong(1));
            resources.setFood(resultSet.getInt(2));
            resources.setMetal(resultSet.getInt(3));
            resources.setGold(resultSet.getInt(4));
            resources.setArmy(getArmyById(resultSet.getLong(5)));
            resources.setBuildingList(getResourcesBuildingById(resultSet.getLong(1)));
            resources.setOperation(6);
            resourcesList.add(resources);
        }
        closeConnection();
        return resourcesList;
    }
    public List<Game> getGameListFromResultSet(ResultSet resultSet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        List<Game> gameList = new ArrayList<>();
        while (resultSet.next()){
            Game game=new Game();
            game.setGameId(resultSet.getLong(1));
            game.setGameName(resultSet.getString(2));
            game.setEnemyPlanetList(getGameEnemyPlanetById(resultSet.getLong(1)));
            game.setPlayerPlanetList(getGamePlayerPlanetById(resultSet.getLong(1)));
            game.setResources(getResourcesById(resultSet.getLong(3)));
        }
        closeConnection();
        return gameList;
    }

    public List<Unit> getUnitList() throws SQLException, IOException {
        initDataSource();
        initConnection();
        ResultSet rs = statement.executeQuery(Constants.SELECT_UNIT);
        List<Unit> unitList=getUnitListFromResultSet(rs);
        closeConnection();
        return unitList;
    }
    public List<Building> getBuildingList() throws SQLException, IOException {
        initDataSource();
        initConnection();
        ResultSet rs = statement.executeQuery(Constants.SELECT_BUILDING);
        List<Building> buildingList=getBuildingListFromResultSet(rs);
        closeConnection();
        return buildingList;
    }
    public List<EnemyPlanet> getEnemyPlanetList() throws SQLException, IOException {
        initDataSource();
        initConnection();
        ResultSet rs = statement.executeQuery(Constants.SELECT_ENEMY_PLANET);
        List<EnemyPlanet> enemyPlanetList = getEnemyPlanetListFromResultSet(rs);
        closeConnection();
        return enemyPlanetList;
    }
    public List<PlayerPlanet> getPlayerPlanetList() throws SQLException, IOException {
        initDataSource();
        initConnection();
        ResultSet rs = statement.executeQuery(Constants.SELECT_PLAYER_PLANET);
        List<PlayerPlanet> playerPlanetList = getPlayerPlanetListFromResultSet(rs);
        closeConnection();
        return playerPlanetList;
    }
    public List<Army> getArmyList() throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        ResultSet rs = statement.executeQuery(Constants.SELECT_ARMY);
        List<Army> armyList = getArmyListFromResultSet(rs);
        closeConnection();
        return armyList;
    }
    public List<Resources> getResourcesList() throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        ResultSet rs = statement.executeQuery(Constants.SELECT_RESOURCES);
        List<Resources> resources=getResourcesListFromResultSet(rs);
        closeConnection();
        return resources;
    }
    public List<Game> getGameList() throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        ResultSet rs = statement.executeQuery(Constants.SELECT_GAME);
        List<Game> gameList=getGameListFromResultSet(rs);
        closeConnection();
        return gameList;
    }

    public ArmyInfo getArmyInfoId(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_ARMY_INFO_BY_ID,id));
        ArmyInfo armyInfo = new ArmyInfo();
        if (resultSet.next()){
            armyInfo.setArmyHealthPoints(resultSet.getInt(2));
            armyInfo.setArmyAttackPoints(resultSet.getInt(3));
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(armyInfo, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        }

        closeConnection();
        return armyInfo;
    }
    public List<Unit> getArmyUnitById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        List<Unit> unitList=new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_ARMY_UNIT_BY_ID,id));
        while (resultSet.next()){
            int count=resultSet.getInt(3);
            while (count>0){
                unitList.add(getUnitById(resultSet.getLong(2)));
                count--;
            }
        }
        closeConnection();
        return unitList;
    }
    public Army getArmyById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_ARMY_BY_ID,id));
        Army army = new Army();
        if(resultSet.next()){
            Long armyId=resultSet.getLong(1);
            army.setArmyId(armyId);
            army.setUnits(getArmyUnitById(armyId));
            army.setArmyInfo(getArmyInfoId(armyId));
        }
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(army, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
        return army;
    }
    public List<Building> getResourcesBuildingById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        List<Building> buildingList=new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_RESOURCES_BUILDING_BY_ID,id));
        while (resultSet.next()){
            int count=resultSet.getInt(3);
            //log.info(count);
            while (count>0){
                buildingList.add(getBuildingById(resultSet.getLong(2)));
                count--;
            }
        }
        closeConnection();
        return buildingList;
    }
    public Resources getResourcesById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_RESOURCES_BY_ID,id));
        Resources resources=new Resources();
        if(resultSet.next()){
            resources.setResourcesId(resultSet.getLong(1));
            resources.setFood(resultSet.getInt(2));
            resources.setMetal(resultSet.getInt(3));
            resources.setGold(resultSet.getInt(4));
            resources.setArmy(getArmyById(resultSet.getLong(1)));
            resources.setBuildingList(getResourcesBuildingById(resultSet.getLong(1)));
            resources.setOperation(resultSet.getInt(6));
        }
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
        return resources;
    }
    public EnemyPlanet getEnemyPlanetById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_ENEMY_PLANET_BY_ID,id));
        EnemyPlanet enemyPlanet = new EnemyPlanet();
        if(resultSet.next()){
            enemyPlanet.setPlanetId(resultSet.getLong(1));
            enemyPlanet.setPlanetName(resultSet.getString(2));
            enemyPlanet.setPlanetType(resultSet.getString(3));
            enemyPlanet.setEnemyHealthPoints(resultSet.getInt(4));
            enemyPlanet.setEnemyAttackPoints(resultSet.getInt(5));
        }
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
        return enemyPlanet;
    }
    public List<EnemyPlanet> getGameEnemyPlanetById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        List<EnemyPlanet> enemyPlanetlist=new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_GAME_ENEMY_PLANET_BY_ID,id));
        while (resultSet.next()){
            enemyPlanetlist.add(getEnemyPlanetById(resultSet.getLong(2)));
        }
        closeConnection();
        return enemyPlanetlist;
    }
    public PlayerPlanet getPlayerPlanetById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_PLAYER_PLANET_BY_ID,id));
        PlayerPlanet playerPlanet = new PlayerPlanet();
        if(resultSet.next()){
            playerPlanet.setPlanetId(resultSet.getLong(1));
            playerPlanet.setPlanetName(resultSet.getString(2));
            playerPlanet.setPlanetType(resultSet.getString(3));
            playerPlanet.setBuildingLimit(resultSet.getInt(4));
        }
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
        return playerPlanet;
    }
    public List<PlayerPlanet> getGamePlayerPlanetById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        List<PlayerPlanet> playerPlanetList=new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_GAME_PLAYER_PLANET_BY_ID,id));
        while (resultSet.next()){
            playerPlanetList.add(getPlayerPlanetById(resultSet.getLong(2)));
        }
        closeConnection();
        return playerPlanetList;
    }
    public Game getGameById(Long id) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        ResultSet resultSet = statement.executeQuery(String.format(Constants.SELECT_GAME_BY_ID,id));
        Game game=new Game();
        if (resultSet.next()){
            game.setGameId(resultSet.getLong(1));
            game.setGameName(resultSet.getString(2));
            game.setEnemyPlanetList(getGameEnemyPlanetById(resultSet.getLong(1)));
            game.setPlayerPlanetList(getGamePlayerPlanetById(resultSet.getLong(1)));
            game.setResources(getResourcesById(resultSet.getLong(1)));
        }
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(game, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
        return game;
    }

    public void insertUnit(Unit unit) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        try {
            statement.executeUpdate(String.format(Constants.CREATE_UNIT,
                    unit.getUnitId(),
                    unit.getUnitType(),
                    unit.getUnitAttackPoints(),
                    unit.getUnitHealthPoints(),
                    unit.getGoldRequired(),
                    unit.getFoodRequired(),
                    unit.getMetalRequired()));
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        } catch (JdbcSQLIntegrityConstraintViolationException e){

        } finally {
            closeConnection();
        }


    }
    public void insertBuilding(Building building) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        try {
        statement.executeUpdate(String.format(Constants.CREATE_BUILDING,
                building.getBuildingId(),
                building.getBuildingType(),
                building.getFoodBuff(),
                building.getMetalBuff(),
                building.getGoldBuff(),
                building.getFoodRequired(),
                building.getMetalRequired(),
                building.getGoldRequired()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(building, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        } catch (JdbcSQLIntegrityConstraintViolationException e){

        } finally {
            closeConnection();
        }
    }
    public void insertEnemyPlanet(EnemyPlanet enemyPlanet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        try {
        statement.executeUpdate(String.format(Constants.CREATE_ENEMY_PLANET,
                enemyPlanet.getPlanetId(),
                enemyPlanet.getPlanetName(),
                enemyPlanet.getPlanetType(),
                enemyPlanet.getEnemyHealthPoints(),
                enemyPlanet.getEnemyAttackPoints()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
    } catch (JdbcSQLIntegrityConstraintViolationException e){

    } finally {
        closeConnection();
    }
    }
    public void insertPlayerPlanet(PlayerPlanet playerPlanet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        try {
            statement.executeUpdate(String.format(Constants.CREATE_PLAYER_PLANET,
                    playerPlanet.getPlanetId(),
                    playerPlanet.getPlanetName(),
                    playerPlanet.getPlanetType(),
                    playerPlanet.getBuildingLimit()));
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        } catch (JdbcSQLIntegrityConstraintViolationException e){
        } finally {
        closeConnection();
        }
    }
    public void insertArmyInfo(ArmyInfo armyinfo, Long armyId) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.CREATE_ARMY_INFO,
                armyId,
                armyinfo.getArmyHealthPoints(),
                armyinfo.getArmyAttackPoints()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(armyinfo, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void insertArmyUnit(List<Unit> armyUnitList,Long armyId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Unit> unitList=getUnitList();
        initConnection();
        unitList.forEach(n->{
            try {
                int count= Collections.frequency(armyUnitList.stream().map(Unit::getUnitId).collect(Collectors.toList()), n.getUnitId());
                statement.executeUpdate(String.format(Constants.CREATE_ARMY_UNIT,
                        armyId,
                        n.getUnitId(),
                        count));
            } catch (SQLException | NullPointerException e) {}});
        closeConnection();
    }
    public void insertArmy(Army army) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        try {
            statement.executeUpdate(String.format(Constants.CREATE_ARMY,army.getArmyId(),army.getArmyId()));
            insertArmyInfo(army.getArmyInfo(), army.getArmyId());
            insertArmyUnit(army.getUnits(),army.getArmyId());
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        } catch (JdbcSQLIntegrityConstraintViolationException | NullPointerException e){

        } finally {
            closeConnection();
        }


    }
    public void insertResourcesBuilding(List<Building> resourcesBuildingList,Long resourcesId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Building> buildingList=getBuildingList();
        initConnection();
        buildingList.forEach(n->{
            try {
                int count= Collections.frequency(resourcesBuildingList.stream().map(Building::getBuildingId).collect(Collectors.toList()), n.getBuildingId());
                statement.executeUpdate(String.format(Constants.CREATE_RESOURCES_BUILDING,
                        resourcesId,
                        n.getBuildingId(),
                        count));
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }});
        closeConnection();
    }
    public void insertResources(Resources resources) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        try {
            statement.executeUpdate(String.format(Constants.CREATE_RESOURCES,
                    resources.getResourcesId(),
                    resources.getFood(),
                    resources.getMetal(),
                    resources.getGold(),
                    resources.getOperation(),
                    resources.getResourcesId()));
            insertResourcesBuilding(resources.getBuildingList(),resources.getResourcesId());
            insertArmy(resources.getArmy());
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        } catch (JdbcSQLIntegrityConstraintViolationException e){

        } finally {
            closeConnection();
        }


    }
    public void insertGameEnemyPlanet(List<EnemyPlanet> gameEnemyPlanetList,Long gameId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        List<Long> planetIds = enemyPlanetList.stream().map(Planet::getPlanetId).collect(Collectors.toList());
        List<EnemyPlanet> filteredEnemyPlanets = gameEnemyPlanetList.stream().filter(p -> planetIds.contains(p.getPlanetId())).collect(Collectors.toList());
        filteredEnemyPlanets.forEach(n->{
            try {
                initConnection();
                statement.executeUpdate(String.format(Constants.CREATE_GAME_ENEMY_PLANET,
                        gameId,
                        n.getPlanetId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void insertGamePlayerPlanet(List<PlayerPlanet> gamePlayerPlanetList,Long gameId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        List<Long> planetIds = playerPlanetList.stream().map(Planet::getPlanetId).collect(Collectors.toList());
        List<PlayerPlanet> filteredEnemyPlanets = gamePlayerPlanetList.stream().filter(p -> planetIds.contains(p.getPlanetId())).collect(Collectors.toList());
        filteredEnemyPlanets.forEach(n->{
            try {
                initConnection();
                statement.executeUpdate(String.format(Constants.CREATE_GAME_PLAYER_PLANET,
                        gameId,
                        n.getPlanetId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void insertGame(Game game) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        try {
            statement.executeUpdate(String.format(Constants.CREATE_GAME,
                    game.getGameId(),
                    game.getGameName()));
            insertResources(game.getResources());
            insertGameEnemyPlanet(game.getEnemyPlanetList(),game.getGameId());
            insertGamePlayerPlanet(game.getPlayerPlanetList(),game.getGameId());
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        } catch (JdbcSQLIntegrityConstraintViolationException e) {

        } finally {
            closeConnection();
        }
    }

    public void deleteUnit(Unit unit) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.DELETE_UNIT,unit.getUnitId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void deleteBuilding(Building building) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.DELETE_BUILDING,building.getBuildingId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(building, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void deleteEnemyPlanet(EnemyPlanet enemyPlanet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.DELETE_ENEMY_PLANET,enemyPlanet.getPlanetId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void deletePlayerPlanet(PlayerPlanet playerPlanet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.DELETE_PLAYER_PLANET,playerPlanet.getPlanetId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void deleteArmy(Army army) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.DELETE_ARMY,army.getArmyId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(army, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void deleteResources(Resources resources) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.DELETE_RESOURCES,resources.getResourcesId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void deleteGame(Game game) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.DELETE_GAME,game.getGameId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(game, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void deleteGamePlayerPlanets(List<PlayerPlanet> gamePlayerPlanetList,Long gameId) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        initDataSource();
        initConnection();
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        List<Long> planetIds = playerPlanetList.stream().map(Planet::getPlanetId).collect(Collectors.toList());
        List<PlayerPlanet> filteredEnemyPlanets = gamePlayerPlanetList.stream().filter(p -> planetIds.contains(p.getPlanetId())).collect(Collectors.toList());
        filteredEnemyPlanets.forEach(n->{
            try {
                initConnection();
                statement.executeUpdate(String.format(Constants.DELETE_GAME_PLAYER_PLANETS,
                        gameId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void deleteGameEnemyPlanet(List<EnemyPlanet> gameEnemyPlanetList,Long gameId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        List<Long> planetIds = enemyPlanetList.stream().map(Planet::getPlanetId).collect(Collectors.toList());
        List<EnemyPlanet> filteredEnemyPlanets = gameEnemyPlanetList.stream().filter(p -> planetIds.contains(p.getPlanetId())).collect(Collectors.toList());
        filteredEnemyPlanets.forEach(n->{
            try {
                initConnection();
                statement.executeUpdate(String.format(Constants.DELETE_GAME_ENEMY_PLANETS,
                        gameId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void deleteResourcesBuildings(List<Building> resourcesBuildingList,Long resourcesId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Building> buildingList=getBuildingList();
        List<Long> buildingIds = buildingList.stream().map(Building::getBuildingId).collect(Collectors.toList());
        List<Building> filteredBuildings = resourcesBuildingList.stream().filter(p -> buildingIds.contains(p.getBuildingId())).collect(Collectors.toList());
        filteredBuildings.forEach(n->{
            try {
                initConnection();
                statement.executeUpdate(String.format(Constants.DELETE_RESOURCES_BUILDING,
                        resourcesId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void deleteArmyUnits(List<Unit> armyUnitsList,Long armyId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Long> unitIds = armyUnitsList.stream().map(Unit::getUnitId).collect(Collectors.toList());
        unitIds.forEach(n->{
            try {
                initConnection();
                //log.info("fsdfsdfsdfsdf"+armyId);
                statement.executeUpdate(String.format(Constants.DELETE_ARMY_UNIT,
                        armyId));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void updateUnit(Unit unit) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.UPDATE_UNIT_BY_ID,
                unit.getUnitType(),
                unit.getUnitAttackPoints(),
                unit.getUnitHealthPoints(),
                unit.getGoldRequired(),
                unit.getFoodRequired(),
                unit.getMetalRequired(),
                unit.getUnitId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void updateBuilding(Building building) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.UPDATE_BUILDING,
                building.getBuildingType(),
                building.getFoodBuff(),
                building.getMetalBuff(),
                building.getGoldBuff(),
                building.getFoodRequired(),
                building.getMetalRequired(),
                building.getGoldRequired(),
                building.getBuildingId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(building, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void updateEnemyPlanet(EnemyPlanet enemyPlanet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.UPDATE_ENEMY_PLANET,
                enemyPlanet.getPlanetName(),
                enemyPlanet.getPlanetType(),
                enemyPlanet.getEnemyHealthPoints(),
                enemyPlanet.getEnemyAttackPoints(),
                enemyPlanet.getPlanetId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void updatePlayerPlanet(PlayerPlanet playerPlanet) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.UPDATE_PLAYER_PLANET,
                playerPlanet.getPlanetName(),
                playerPlanet.getPlanetType(),
                playerPlanet.getBuildingLimit(),
                playerPlanet.getPlanetId()));
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void updateArmyInfo(ArmyInfo armyinfo, Long armyId) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.UPDATE_ARMY_INFO,
                armyinfo.getArmyHealthPoints(),
                armyinfo.getArmyAttackPoints(),
                armyId));
        closeConnection();
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(armyinfo, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public void updateArmyUnit(List<Unit> armyUnitList,Long armyId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Unit> unitList=getUnitList();
        unitList.forEach(n->{
            try {
                int count= Collections.frequency(armyUnitList.stream().map(Unit::getUnitId).collect(Collectors.toList()), n.getUnitId());
                //log.info(count);
                initConnection();
                statement.executeUpdate(String.format(Constants.UPDATE_ARMY_UNIT,
                        n.getUnitId(),
                        count,
                        armyId
                ));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void updateArmy(Army army) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        updateArmyInfo(army.getArmyInfo(), army.getArmyId());
        //dropArmyUnits();
        deleteArmyUnits(army.getUnits(),army.getArmyId());
        insertArmyUnit(army.getUnits(),army.getArmyId());
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(army, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void updateResourcesBuilding(List<Building> resourcesBuildingList,Long resourcesId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<Building> buildingList=getBuildingList();
        buildingList.forEach(n->{
            try {
                int count= Collections.frequency(resourcesBuildingList.stream().map(Building::getBuildingId).collect(Collectors.toList()), n.getBuildingId());
                initConnection();
                statement.executeUpdate(String.format(Constants.CREATE_RESOURCES_BUILDING,
                        resourcesId,
                        n.getBuildingId(),
                        count));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void updateResources(Resources resources) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.UPDATE_RESOURCES,
                resources.getFood(),
                resources.getMetal(),
                resources.getGold(),
                resources.getOperation(),
                resources.getResourcesId(),
                resources.getResourcesId()
        ));
        //dropResourcesBuilding();
        deleteResourcesBuildings(resources.getBuildingList(),resources.getResourcesId());
        insertResourcesBuilding(resources.getBuildingList(),resources.getResourcesId());
        updateArmy(resources.getArmy());
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    public void updateGameEnemyPlanet(List<EnemyPlanet> gameEnemyPlanetList,Long gameId) throws SQLException, IOException {
        initDataSource();
        initConnection();
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        List<Long> planetIds = enemyPlanetList.stream().map(Planet::getPlanetId).collect(Collectors.toList());
        List<EnemyPlanet> filteredEnemyPlanets = gameEnemyPlanetList.stream().filter(p -> planetIds.contains(p.getPlanetId())).collect(Collectors.toList());
        filteredEnemyPlanets.forEach(n->{
            try {
                initConnection();
                statement.executeUpdate(String.format(Constants.UPDATE_GAME_ENEMY_PLANET,
                        n.getPlanetId(),
                        gameId
                ));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void updateGamePlayerPlanet(List<PlayerPlanet> gamePlayerPlanetList,Long gameId) throws SQLException, IOException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        List<Long> planetIds = playerPlanetList.stream().map(Planet::getPlanetId).collect(Collectors.toList());
        List<PlayerPlanet> filteredEnemyPlanets = gamePlayerPlanetList.stream().filter(p -> planetIds.contains(p.getPlanetId())).collect(Collectors.toList());
        filteredEnemyPlanets.forEach(n->{
            try {
                initConnection();
                statement.executeUpdate(String.format(Constants.UPDATE_GAME_PLAYER_PLANET,
                        n.getPlanetId(),
                        gameId
                ));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        closeConnection();
    }
    public void updateGame(Game game) throws SQLException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        initDataSource();
        initConnection();
        statement.executeUpdate(String.format(Constants.UPDATE_GAME,
                game.getGameName(),
                game.getGameId()
        ));
        updateResources(game.getResources());
        //dropGamePlanets();
        deleteGamePlayerPlanets(game.getPlayerPlanetList(),game.getGameId());
        deleteGameEnemyPlanet(game.getEnemyPlanetList(),game.getGameId());
        insertGameEnemyPlanet(game.getEnemyPlanetList(),game.getGameId());
        insertGamePlayerPlanet(game.getPlayerPlanetList(),game.getGameId());
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(game, Constants.ARMYINFO, className, methodName), Constants.MONGODB_TEST_SERVER);
        closeConnection();
    }
    /**CRUD
     * CORE*/
    @Override
    public Game createUniverse(Game game,Resources resources,Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        if (game.getGameId()==null || army.getArmyId() == null || resources.getResourcesId() == null){
            log.info(Constants.WRONG_PARAMETER);
            return null;
        } else {
            insertGame(game);
            insertResources(resources);
            insertArmy(army);
            return game;
        }
    }
    @Override
    public Boolean deleteUniverse(Long gameId) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        try {
            Game game=getGameById(gameId);
            game.getGameId();
            deleteGame(game);
            deleteArmy(game.getResources().getArmy());
            deleteResources(game.getResources());
            log.info(Constants.UNIVERSE_DELETED);
            return true;
        } catch (NullPointerException | SQLException e){
            log.error(Constants.WRONG_PARAMETER);
            return false;
        }
    }
    @Override
    public EnemyPlanet getEnemyPower(Long planetId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            return getGameById(gameId)
                    .getEnemyPlanetList()
                    .get(Math.toIntExact(planetId)-1);
        } catch (NullPointerException | SQLException e){
            log.info(Constants.WRONG_PARAMETER);
            return null;
        }
    }
    @Override
    public ArmyInfo getArmyPower(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            return getGameById(gameId)
                    .getResources()
                    .getArmy()
                    .getArmyInfo();
        }catch (NullPointerException | SQLException e){
            log.info(Constants.YOUR_ARMY_IS_DEAD);
            return null;
        }
    }
    @Override
    public Boolean attackPlanet(Long planetId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        Boolean result = null;
        Game game = getGameById(gameId);
        ArmyInfo armyInfo = getArmyPower(gameId);
        EnemyPlanet enemyPlanet = getEnemyPower(planetId,gameId);
        try {
            while (enemyPlanet.getEnemyHealthPoints() > 0){
                if (armyInfo.getArmyHealthPoints() > 0){
                    armyInfo.setArmyHealthPoints(armyInfo.getArmyHealthPoints() - enemyPlanet.getEnemyAttackPoints());
                    enemyPlanet.setEnemyHealthPoints(enemyPlanet.getEnemyHealthPoints()-armyInfo.getArmyAttackPoints());
                } else {
                    deleteUniverse(gameId);
                    log.info(Constants.GAME_OVER);
                    return result = false;
                }}
            List<EnemyPlanet> enemyPlanetList = game.getEnemyPlanetList();
            enemyPlanetList.remove(Math.toIntExact(planetId)-1);
            PlayerPlanet playerPlanet = new PlayerPlanet();
            playerPlanet.setPlanetId(enemyPlanet.getPlanetId());
            playerPlanet.setPlanetName(enemyPlanet.getPlanetName());
            playerPlanet.setPlanetType(Constants.PLAYER);
            playerPlanet.setBuildingLimit(Constants.DEFAULT_BUILDING_LIMIT);
            List<PlayerPlanet> playerPlanetList = game.getPlayerPlanetList();
            playerPlanetList.add(playerPlanet);
            game.setEnemyPlanetList(enemyPlanetList);
            game.setPlayerPlanetList(playerPlanetList);
            updateGame(game);
            log.info(Constants.VICTORY);
            log.info(Constants.YOUR_RESOURCES);
            log.info(Constants.FOOD+game.getResources().getFood());
            log.info(Constants.METAL+game.getResources().getMetal());
            log.info(Constants.GOLD+game.getResources().getGold());
            log.info(Constants.YOUR_ARMY_POWER);
            log.info(Constants.HEALTH+game.getResources().getArmy().getArmyInfo().getArmyHealthPoints()+Constants.ATTACK+game.getResources().getArmy().getArmyInfo().getArmyAttackPoints());
            log.info(Constants.YOUR_PLANETS);
            game.getPlayerPlanetList().forEach(x->{
                log.info(Constants.ID+x.getPlanetId()+Constants.PLANET_NAME+x.getPlanetName()+Constants.BUILDING_LIMIT+x.getBuildingLimit());
            });
            log.info(Constants.ENEMY_PLANETS);
            game.getEnemyPlanetList().forEach(x->{
                log.info(Constants.ID+x.getPlanetId()+Constants.PLANET_NAME+x.getPlanetName());
            });
            result=true;
        }catch (NullPointerException e){
            log.info(Constants.ENEMY_PLANET+Constants.DO_NOT_EXIST);
            result=false;
        } finally {
            return result;
        }
    }
    @Override
    public Game hireUnit(Long unitId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        Game game = getGameById(gameId);
        Unit unit = getUnitById(unitId);
        //log.info(unit);
        try {
            game
                    .getResources()
                    .getArmy()
                    .getArmyInfo()
                    .setArmyHealthPoints(game
                            .getResources()
                            .getArmy()
                            .getArmyInfo()
                            .getArmyHealthPoints() + unit.getUnitHealthPoints());
            game
                    .getResources()
                    .getArmy()
                    .getArmyInfo()
                    .setArmyAttackPoints(game
                            .getResources()
                            .getArmy()
                            .getArmyInfo()
                            .getArmyAttackPoints() + unit.getUnitAttackPoints());
            game
                    .getResources()
                    .getArmy()
                    .getUnits().add(unit);
            game
                    .getResources()
                    .setGold(game
                            .getResources()
                            .getGold()-unit.getGoldRequired());
            game
                    .getResources()
                    .setFood(game
                            .getResources()
                            .getFood()-unit.getFoodRequired());
            game
                    .getResources()
                    .setMetal(game
                            .getResources()
                            .getMetal()-unit.getMetalRequired());
        }catch (NullPointerException e){
            log.error(Constants.WRONG_PARAMETER);
        }finally {
            return game;
        }
    }
    @Override
    public List<Building> getBuildingsInfo(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {return getGameById(gameId)
                .getResources()
                .getBuildingList();
        }catch (NullPointerException | SQLException e){
            return null;
        }

    }
    @Override
    public Game addBuilding(Long buildingId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        Game game=getGameById(gameId);
        if (game.getGameId()==null){
            return null;
        } else {
            Building building=getBuildingById(buildingId);
            if (building==null){
                return game;
            } else {
                game
                        .getResources()
                        .getBuildingList().add(building);
                game
                        .getResources()
                        .setGold(game
                                .getResources()
                                .getGold()-building.getGoldRequired());
                game
                        .getResources()
                        .setGold(game
                                .getResources()
                                .getGold()+building.getGoldBuff());
                game
                        .getResources()
                        .setMetal(game
                                .getResources()
                                .getMetal()-building.getMetalRequired());
                game
                        .getResources()
                        .setMetal(game
                                .getResources()
                                .getMetal()+building.getMetalBuff());
                game
                        .getResources()
                        .setFood(game
                                .getResources()
                                .getFood()-building.getFoodRequired());
                game
                        .getResources()
                        .setFood(game
                                .getResources()
                                .getFood()+building.getFoodBuff());
                return game;
            }}
    }
    @Override
    public Game removeBuilding(Long buildingId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        Game game = getGameById(gameId);
        try {
            List<Building> buildingList = game
                    .getResources()
                    .getBuildingList();

            buildingList.remove(Math.toIntExact(buildingId)-1);
            game
                    .getResources()
                    .setBuildingList(buildingList);
        } catch (IndexOutOfBoundsException | NullPointerException e){
            log.debug(Constants.BUILDING_LIST+Constants.IS_EMPTY);
        }

        return game;
    }
    @Override
    public Game manageResources(Long gameId,int operation,Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        Game game=getGameById(gameId);
        try {
            switch (operation) {
                case (2) -> game = addBuilding(id, gameId);
                case (3) -> game = removeBuilding(id, gameId);
                case (4) -> game = hireUnit(id, gameId);
                default -> log.error(Constants.WRONG_PARAMETER);
            }
            updateGame(game);
            updateResources(game.getResources());
            updateArmy(game.getResources().getArmy());
            log.info(Constants.YOUR_RESOURCES);
            log.info(Constants.FOOD+game.getResources().getFood());
            log.info(Constants.METAL+game.getResources().getMetal());
            log.info(Constants.GOLD+game.getResources().getGold());
            log.info(Constants.YOUR_ARMY_POWER);
            log.info(Constants.HEALTH+game.getResources().getArmy().getArmyInfo().getArmyHealthPoints()+Constants.ATTACK+game.getResources().getArmy().getArmyInfo().getArmyAttackPoints());
            log.info(Constants.YOUR_PLANETS);
            game.getPlayerPlanetList().forEach(x->{
                log.info(Constants.ID+x.getPlanetId()+Constants.PLANET_NAME+x.getPlanetName()+Constants.BUILDING_LIMIT+x.getBuildingLimit());
            });
            log.info(Constants.ENEMY_PLANETS);
            game.getEnemyPlanetList().forEach(x->{
                log.info(Constants.ID+x.getPlanetId()+Constants.PLANET_NAME+x.getPlanetName());
            });
        } catch (NullPointerException | SQLException e){
            log.error(Constants.GAME+Constants.DO_NOT_EXIST);
        }

        return game;
    }
    @Override
    public Game manageResources(Long gameId,int operation) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        Game game=getGameById(gameId);
        if (operation == 1) {
            log.info(getBuildingsInfo(gameId));
        } else {
            log.error(Constants.WRONG_PARAMETER);
        }
        return game;
    }
    @Override
    public Building getBuildingById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Building> buildingList = getBuildingList();
        Building building=buildingList.stream()
                .filter(x-> id.equals(x.getBuildingId()))
                .findAny()
                .orElse(null);
        if(building == null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.BUILDING+Constants.DO_NOT_EXIST);
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        return building;
    }
    @Override
    public Unit getUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, SQLException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Unit> unitList = getUnitList();
        Unit unit=unitList.stream()
                .filter(x-> id.equals(x.getUnitId()))
                .findAny()
                .orElse(null);
        if(unit == null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.UNIT+Constants.DO_NOT_EXIST);
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        return unit;
    }
}
