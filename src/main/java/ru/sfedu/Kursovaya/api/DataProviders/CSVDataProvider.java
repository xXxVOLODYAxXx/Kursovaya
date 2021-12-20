package ru.sfedu.Kursovaya.api.DataProviders;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.sfedu.Kursovaya.Beans.*;

import java.util.stream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.utils.OtherUtils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

public class CSVDataProvider extends AbstractDataProvider {


    public CSVDataProvider() throws IOException {}
    private final String PATHTOCSV= ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_CSV);
    private final String CSVEXTENSION=ConfigurationUtil.getConfigurationEntry(Constants.CSV_FILE_EXTENSION);
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class);
    MongoDBDataProvider mongoDBDataProvider=new MongoDBDataProvider();
    private CSVReader reader;
    private CSVWriter writer;
    private void initReader(String string) throws IOException {
        initDataSource(string);
        this.reader=new CSVReader(new FileReader(PATHTOCSV+string+CSVEXTENSION));
    }
    private void initWriter(String string) throws IOException {
        initDataSource(string);
        this.writer=new CSVWriter(new FileWriter(PATHTOCSV+string+CSVEXTENSION));
    }
    private void close() throws IOException {
        if (reader!=null){this.reader.close();}
        if(writer!=null){this.writer.close();}
    }
    private void initDataSource(String string) throws IOException {
        string = PATHTOCSV+string+CSVEXTENSION;
        File file = new File(string);
        if (!file.exists()) {
            Path dirPath = Paths.get(PATHTOCSV);
            Files.createDirectories(dirPath);
            file.createNewFile();
        }
    }

    private <T> void  write(List<T> list,String string) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        this.initWriter(string);
        StatefulBeanToCsv<T> statefulBeanToCSV=new StatefulBeanToCsvBuilder<T>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(list);
        this.close();
    }

    private List<Unit> sortUnitList(List<Unit> unitList) {
        unitList=unitList.stream().sorted((o1, o2)->o1.getUnitId().compareTo(o2.getUnitId())).collect(Collectors.toList());
        return unitList;
    }
    private List<Building> sortBuildingList(List<Building> buildingList) {
        buildingList=buildingList.stream().sorted((o1, o2)->o1.getBuildingId().compareTo(o2.getBuildingId())).collect(Collectors.toList());
        return buildingList;
    }
    private List<PlayerPlanet> sortPlayerPlanetList(List<PlayerPlanet> pplist) {
        pplist=pplist.stream().sorted((o1, o2)->o1.getPlanetId().compareTo(o2.getPlanetId())).collect(Collectors.toList());
        return pplist;
    }
    private List<EnemyPlanet> sortEnemyPlanetList(List<EnemyPlanet> eplist) {
        eplist=eplist.stream().sorted((o1, o2)->o1.getPlanetId().compareTo(o2.getPlanetId())).collect(Collectors.toList());
        return eplist;
    }
    private List<Army> sortArmyList(List<Army> alist) {
        alist=alist.stream().sorted((o1, o2)->o1.getArmyId().compareTo(o2.getArmyId())).collect(Collectors.toList());
        return alist;
    }
    private List<Resources> sortResourcesList(List<Resources> rlist) {
        try {
            rlist=rlist.stream().sorted((o1, o2)->o1.getResourcesId().compareTo(o2.getResourcesId())).collect(Collectors.toList());
        } catch (NullPointerException e){} finally {
            return rlist;
        }
    }
    private List<Game> sortGameList(List<Game> glist) {
        try {
            glist=glist.stream().sorted((o1, o2)->o1.getGameId().compareTo(o2.getGameId())).collect(Collectors.toList());
        } catch (NullPointerException e){} finally {
            return glist;
        }
    }

    private String getClassName(){
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }
    private String getMethodName(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
    /**CRUD
     * UNIT*/
    public List<Unit> getUnitList() throws IOException {
        this.initReader(Constants.FILE_NAME_UNIT);
        CsvToBean<Unit> csvToBean=new CsvToBeanBuilder<Unit>(this.reader).withType(Unit.class).build();
        List<Unit> unitlist=csvToBean.parse();
        unitlist=sortUnitList(unitlist);
        return unitlist;
    }
    public void createUnit(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Unit> unitList = getUnitList();
        unitList.add(unit);
        unitList = sortUnitList(unitList);
        this.write(unitList,Constants.FILE_NAME_UNIT);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit, Constants.UNIT, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public void deleteUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Unit> unitList = getUnitList();
        try {
            Unit unit = unitList.stream().filter(x-> id.equals(x.getUnitId())).findAny().get();
            unitList = unitList.stream().filter(x-> !id.equals(x.getUnitId())).collect(Collectors.toList());
            this.write(unitList,Constants.FILE_NAME_UNIT);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(Constants.UNIT+Constants.DO_NOT_EXIST);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void updateUnitById(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Unit> unitList = getUnitList();
        try{
            unit.getUnitId()
                    .equals(unitList
                            .stream()
                            .filter(x -> unit.getUnitId().equals(x.getUnitId()))
                    .findFirst()
                    .get()
                    .getUnitId());
            unitList = unitList.stream().filter(x-> !unit.getUnitId().equals(x.getUnitId())).collect(Collectors.toList());
            write(unitList,Constants.FILE_NAME_UNIT);
            unitList.add(unit);
            unitList = sortUnitList(unitList);
            write(unitList,Constants.FILE_NAME_UNIT);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.UNIT+Constants.DO_NOT_EXIST);
        }
    }
    /**BUILDING*/
    public List<Building> getBuildingList() throws IOException {
        this.initReader(Constants.FILE_NAME_BUILDING);
        CsvToBean<Building> csvToBean=new CsvToBeanBuilder<Building>(this.reader).withType(Building.class).build();
        List<Building> buildingList=csvToBean.parse();
        buildingList=sortBuildingList(buildingList);
        return buildingList;
    }
    public void createBuilding(Building building) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Building> buildingList = getBuildingList();
        buildingList.add(building);
        buildingList = sortBuildingList(buildingList);
        this.write(buildingList,Constants.FILE_NAME_BUILDING);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(building, Constants.BUILDING, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public void deleteBuildingById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Building> buildingList = getBuildingList();
        try {
            Building building = buildingList.stream().filter(x-> id.equals(x.getBuildingId())).findAny().get();
            buildingList = buildingList.stream().filter(x-> !id.equals(x.getBuildingId())).collect(Collectors.toList());
            this.write(buildingList,Constants.FILE_NAME_BUILDING);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(Constants.BUILDING+Constants.DO_NOT_EXIST);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void updateBuildingById(Building building) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Building> buildingList = getBuildingList();
        try{
            building.getBuildingId()
                    .equals(buildingList
                            .stream()
                            .filter(x -> building.getBuildingId().equals(x.getBuildingId()))
                            .findFirst()
                            .get()
                            .getBuildingId());
            buildingList = buildingList.stream().filter(x-> !building.getBuildingId().equals(x.getBuildingId())).collect(Collectors.toList());
            write(buildingList,Constants.FILE_NAME_BUILDING);
            buildingList.add(building);
            buildingList = sortBuildingList(buildingList);
            write(buildingList,Constants.FILE_NAME_BUILDING);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.BUILDING+Constants.DO_NOT_EXIST);
        }
    }
    /**PLAYERPLANET*/
    public List<PlayerPlanet> getPlayerPlanetList() throws IOException {
        this.initReader(Constants.FILE_NAME_PLAYER_PLANET);
        CsvToBean<PlayerPlanet> csvToBean=new CsvToBeanBuilder<PlayerPlanet>(this.reader).withType(PlayerPlanet.class).build();
        List<PlayerPlanet> playerPlanetList=csvToBean.parse();
        playerPlanetList=sortPlayerPlanetList(playerPlanetList);
        return playerPlanetList;
    }
    public void createPlayerPlanet(PlayerPlanet playerPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<PlayerPlanet> playerPlanetList = getPlayerPlanetList();
        playerPlanetList.add(playerPlanet);
        playerPlanetList = sortPlayerPlanetList(playerPlanetList);
        this.write(playerPlanetList,Constants.FILE_NAME_PLAYER_PLANET);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet, Constants.PLAYER_PLANET, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public PlayerPlanet getPlayerPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<PlayerPlanet> playerPlanetList = getPlayerPlanetList();
        PlayerPlanet playerPlanet=playerPlanetList.stream()
                .filter(x-> id.equals(x.getPlanetId()))
                .findAny()
                .orElse(null);
        if(playerPlanet == null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.PLAYER_PLANET+Constants.DO_NOT_EXIST);
            return playerPlanet;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
            return playerPlanet;
        }
    }
    public void deletePlayerPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<PlayerPlanet> playerPlanetList = getPlayerPlanetList();
        try {
            PlayerPlanet playerPlanet = playerPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().get();
            playerPlanetList = playerPlanetList.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
            this.write(playerPlanetList,Constants.FILE_NAME_PLAYER_PLANET);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(Constants.PLAYER_PLANET+Constants.DO_NOT_EXIST);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void updatePlayerPlanetById(PlayerPlanet playerPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<PlayerPlanet> playerPlanetList = getPlayerPlanetList();
        try{
            playerPlanet.getPlanetId()
                    .equals(playerPlanetList
                            .stream()
                            .filter(x -> playerPlanet.getPlanetId().equals(x.getPlanetId()))
                            .findFirst()
                            .get()
                            .getPlanetId());
            playerPlanetList = playerPlanetList.stream().filter(x-> !playerPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            write(playerPlanetList,Constants.FILE_NAME_PLAYER_PLANET);
            playerPlanetList.add(playerPlanet);
            playerPlanetList = sortPlayerPlanetList(playerPlanetList);
            write(playerPlanetList,Constants.FILE_NAME_PLAYER_PLANET);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.PLAYER_PLANET+Constants.DO_NOT_EXIST);
        }
    }
    /**ENEMYPLANET*/
    public List<EnemyPlanet> getEnemyPlanetList() throws IOException {
        this.initReader(Constants.FILE_NAME_ENEMY_PLANET);
        CsvToBean<EnemyPlanet> csvToBean=new CsvToBeanBuilder<EnemyPlanet>(this.reader).withType(EnemyPlanet.class).build();
        List<EnemyPlanet> enemyPlanetList=csvToBean.parse();
        enemyPlanetList=sortEnemyPlanetList(enemyPlanetList);
        return enemyPlanetList;
    }
    public void createEnemyPlanet(EnemyPlanet enemyPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<EnemyPlanet> enemyPlanetList = getEnemyPlanetList();
        enemyPlanetList.add(enemyPlanet);
        enemyPlanetList = sortEnemyPlanetList(enemyPlanetList);
        this.write(enemyPlanetList,Constants.FILE_NAME_ENEMY_PLANET);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet, Constants.ENEMY_PLANET, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public EnemyPlanet getEnemyPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<EnemyPlanet> enemyPlanetList = getEnemyPlanetList();
        EnemyPlanet enemyPlanet=enemyPlanetList.stream()
                .filter(x-> id.equals(x.getPlanetId()))
                .findAny()
                .orElse(null);
        if(enemyPlanet == null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.ENEMY_PLANET+Constants.DO_NOT_EXIST);
            return enemyPlanet;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
            return enemyPlanet;
        }
    }
    public void deleteEnemyPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<EnemyPlanet> enemyPlanetList = getEnemyPlanetList();
        try {
            EnemyPlanet enemyPlanet = enemyPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().get();
            enemyPlanetList = enemyPlanetList.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
            this.write(enemyPlanetList,Constants.FILE_NAME_ENEMY_PLANET);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(Constants.ENEMY_PLANET+Constants.DO_NOT_EXIST);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void updateEnemyPlanetById(EnemyPlanet enemyPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<EnemyPlanet> enemyPlanetList = getEnemyPlanetList();
        try{
            enemyPlanet.getPlanetId()
                    .equals(enemyPlanetList
                            .stream()
                            .filter(x -> enemyPlanet.getPlanetId().equals(x.getPlanetId()))
                            .findFirst()
                            .get()
                            .getPlanetId());
            enemyPlanetList = enemyPlanetList.stream().filter(x-> !enemyPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            write(enemyPlanetList,Constants.FILE_NAME_ENEMY_PLANET);
            enemyPlanetList.add(enemyPlanet);
            enemyPlanetList = sortEnemyPlanetList(enemyPlanetList);
            write(enemyPlanetList,Constants.FILE_NAME_ENEMY_PLANET);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.ENEMY_PLANET+Constants.DO_NOT_EXIST);
        }
    }
    /**ARMY*/
    public List<Army> getArmyList() throws IOException {
        this.initReader(Constants.FILE_NAME_ARMY);
        CsvToBean<Army> csvToBean=new CsvToBeanBuilder<Army>(this.reader).withType(Army.class).build();
        List<Army> armyList=csvToBean.parse();
        armyList=sortArmyList(armyList);
        return armyList;
    }
    public void createArmy(Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Army> armyList = getArmyList();
        armyList.add(army);
        armyList = sortArmyList(armyList);
        this.write(armyList,Constants.FILE_NAME_ARMY);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(army, Constants.ARMY, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public Army getArmyById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Army> armyList = getArmyList();
        Army army=armyList.stream()
                .filter(x-> id.equals(x.getArmyId()))
                .findAny()
                .orElse(null);
        if(army == null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.ARMY+Constants.DO_NOT_EXIST);
            return army;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_TEST_SERVER);
            return army;
        }
    }
    public void deleteArmyById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Army> armyList = getArmyList();
        try {
            Army army = armyList.stream().filter(x-> id.equals(x.getArmyId())).findAny().get();
            armyList = armyList.stream().filter(x-> !id.equals(x.getArmyId())).collect(Collectors.toList());
            this.write(armyList,Constants.FILE_NAME_ARMY);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(Constants.ARMY+Constants.DO_NOT_EXIST);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void updateArmyById(Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Army> armyList = getArmyList();
        try{
            army.getArmyId()
                    .equals(armyList
                            .stream()
                            .filter(x -> army.getArmyId().equals(x.getArmyId()))
                            .findFirst()
                            .get()
                            .getArmyId());
            armyList = armyList.stream().filter(x-> !army.getArmyId().equals(x.getArmyId())).collect(Collectors.toList());
            write(armyList,Constants.FILE_NAME_ARMY);
            armyList.add(army);
            armyList = sortArmyList(armyList);
            write(armyList,Constants.FILE_NAME_ARMY);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.ARMY+Constants.DO_NOT_EXIST);
        }
    }
    /**RESOURCES*/
    public List<Resources> getResourcesList() throws IOException {
        this.initReader(Constants.FILE_NAME_RESOURCES);
        CsvToBean<Resources> csvToBean=new CsvToBeanBuilder<Resources>(this.reader).withType(Resources.class).build();
        List<Resources> resourcesList=csvToBean.parse();
        resourcesList=sortResourcesList(resourcesList);
        return resourcesList;
    }
    public void createResources(Resources resources) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Resources> resourcesList = getResourcesList();
        resourcesList.add(resources);
        resourcesList = sortResourcesList(resourcesList);
        this.write(resourcesList,Constants.FILE_NAME_RESOURCES);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources, Constants.RESOURCES, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public Resources getResourcesById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Resources> resourcesList = getResourcesList();
        Resources resources=resourcesList.stream()
                .filter(x-> id.equals(x.getResourcesId()))
                .findAny()
                .orElse(null);
        if(resources == null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.RESOURCES+Constants.DO_NOT_EXIST);
            return resources;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_TEST_SERVER);
            return resources;
        }
    }
    public void deleteResourcesById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Resources> resourcesList = getResourcesList();
        try {
            Resources resources = resourcesList.stream().filter(x-> id.equals(x.getResourcesId())).findAny().get();
            resourcesList = resourcesList.stream().filter(x-> !id.equals(x.getResourcesId())).collect(Collectors.toList());
            this.write(resourcesList,Constants.FILE_NAME_RESOURCES);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(Constants.RESOURCES+Constants.DO_NOT_EXIST);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void updateResourcesById(Resources resources) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Resources> resourcesList = getResourcesList();
        try{
            resources.getResourcesId()
                    .equals(resourcesList
                            .stream()
                            .filter(x -> resources.getResourcesId().equals(x.getResourcesId()))
                            .findFirst()
                            .get()
                            .getResourcesId());
            resourcesList = resourcesList.stream().filter(x-> !resources.getResourcesId().equals(x.getResourcesId())).collect(Collectors.toList());
            write(resourcesList,Constants.FILE_NAME_RESOURCES);
            resourcesList.add(resources);
            resourcesList = sortResourcesList(resourcesList);
            write(resourcesList,Constants.FILE_NAME_RESOURCES);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.RESOURCES+Constants.DO_NOT_EXIST);
        }
    }
    /**GAME*/
    public List<Game> getGameList() throws IOException {
        this.initReader(Constants.FILE_NAME_GAME);
        CsvToBean<Game> csvToBean=new CsvToBeanBuilder<Game>(this.reader).withType(Game.class).build();
        List<Game> gameList=csvToBean.parse();
        gameList=sortGameList(gameList);
        return gameList;
    }
    public void createGame(Game game) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Game> gameList = getGameList();
        gameList.add(game);
        gameList = sortGameList(gameList);
        this.write(gameList,Constants.FILE_NAME_GAME);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(game, Constants.GAME, className, methodName), Constants.MONGODB_TEST_SERVER);
    }
    public Game getGameById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Game> gameList = getGameList();
        Game game=gameList.stream()
                .filter(x-> id.equals(x.getGameId()))
                .findAny()
                .orElse(null);
        if(game == null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.GAME+Constants.DO_NOT_EXIST);
            return game;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_TEST_SERVER);
            return game;
        }
    }
    public void deleteGameById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Game> gameList = getGameList();
        try {
            Game game = gameList.stream().filter(x-> id.equals(x.getGameId())).findAny().get();
            gameList = gameList.stream().filter(x-> !id.equals(x.getGameId())).collect(Collectors.toList());
            this.write(gameList,Constants.FILE_NAME_GAME);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(Constants.GAME+Constants.DO_NOT_EXIST);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void updateGameById(Game game) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName = getMethodName();
        String className = getClassName();
        List<Game> gameList = getGameList();
        try{
            game.getGameId().equals(gameList.stream().filter(x -> game.getGameId().equals(x.getGameId())).findFirst().get().getGameId());
            gameList = gameList.stream().filter(x-> !game.getGameId().equals(x.getGameId())).collect(Collectors.toList());
            write(gameList,Constants.FILE_NAME_GAME);
            gameList.add(game);
            gameList = sortGameList(gameList);
            write(gameList,Constants.FILE_NAME_GAME);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info(Constants.GAME+Constants.DO_NOT_EXIST);
        }
    }
    /**CRUD
     * CORE*/
    /**
     * Создать игру,ресурсы,армию
     * Create game,resources,army
     * @param game Game
     * @param resources Resources
     * @param army Army
     * @return Game
     */
    @Override
    public Game createUniverse(Game game,Resources resources,Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        if (game.getGameId()==null || army.getArmyId() == null || resources.getResourcesId() == null){
            log.info(Constants.WRONG_PARAMETER);
            return null;
        } else {
        createGame(game);
        createResources(resources);
        createArmy(army);
        return game;
        }
    }
    /**
     * Удалить игру,ресурсы,армию
     * Remove game,resources,army
     * @param gameId Long
     * @return Boolean
     */
    @Override
    public Boolean deleteUniverse(Long gameId) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        try {
            getGameById(gameId).getGameId();
            deleteGameById(gameId);
            deleteArmyById(gameId);
            deleteResourcesById(gameId);
            log.info(Constants.UNIVERSE_DELETED);
            return true;
        } catch (NullPointerException e){
            log.error(Constants.WRONG_PARAMETER);
            return false;
        }
    }
    /**
     * Получить вражескую планету по id
     * Get enemy planet by id
     * @param planetId Long
     * @param gameId Long
     * @return EnemyPlanet
     */
    @Override
    public EnemyPlanet getEnemyPower(Long planetId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            return getGameById(gameId)
                    .getEnemyPlanetList()
                    .get(Math.toIntExact(planetId)-1);
        } catch (NullPointerException e){
            log.info(Constants.WRONG_PARAMETER);
            return null;
        }
    }
    /**
     * Получить информацию об армии по id
     * Get army info by id
     * @param gameId Long
     * @return ArmyInfo
     */
    @Override
    public ArmyInfo getArmyPower(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            return getGameById(gameId)
                    .getResources()
                    .getArmy()
                    .getArmyInfo();
        }catch (NullPointerException e){
            log.info(Constants.YOUR_ARMY_IS_DEAD);
            return null;
        }
    }
    /**
     * Атаковать планету по id
     * Если армия умирает:вы проиграете и данные быдут удалены
     * Если выиграете:вражеская планета станет вашей
     * Attack planet by id
     * if your army dies: you will lose and all data will be deleted
     * if you win: enemy planet will become yours
     * @param planetId Long
     * @param gameId Long
     * @return Boolean
     */
    @Override
    public Boolean attackPlanet(Long planetId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
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
            game.setEnemyPlanetList(enemyPlanetList);
            PlayerPlanet playerPlanet = new PlayerPlanet();
            playerPlanet.setPlanetId(enemyPlanet.getPlanetId());
            playerPlanet.setPlanetName(enemyPlanet.getPlanetName());
            playerPlanet.setPlanetType(Constants.PLAYER);
            playerPlanet.setBuildingLimit(Constants.DEFAULT_BUILDING_LIMIT);
            List<PlayerPlanet> playerPlanetList = game.getPlayerPlanetList();
            playerPlanetList.add(playerPlanet);
            game.setPlayerPlanetList(playerPlanetList);
            updateArmyById(game
                    .getResources()
                    .getArmy());
            updateResourcesById(game
                    .getResources());
            updateGameById(game);
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
    /**
     * Добавить юнита в список юнитов
     * Add unit to army unit list
     * @param unitId Long
     * @param gameId Long
     * @return Game
     */
    @Override
    public Game hireUnit(Long unitId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Game game = getGameById(gameId);
        Unit unit = getUnitById(unitId);
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
            List<Unit> unitList = game
                    .getResources()
                    .getArmy()
                    .getUnits();
            unitList.add(unit);
            game
                .getResources()
                .getArmy()
                .setUnits(unitList);
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
    /**
     * Получчить список зданий
     * Get building list
     * @param gameId Long
     * @return List<Building>
     */
    @Override
    public List<Building> getBuildingsInfo(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {return getGameById(gameId)
                    .getResources()
                    .getBuildingList();
        }catch (NullPointerException e){
            return null;
        }

    }
    /**
     * Добавить здание в список зданий
     * Add building to resources building list
     * @param buildingId Long
     * @param gameId Long
     * @return Game
     */
    @Override
    public Game addBuilding(Long buildingId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Game game=getGameById(gameId);
        if (game==null){
            return null;
        } else {
        Building building=getBuildingById(buildingId);
        if (building==null){
            return game;
        } else {
        List<Building> buildingList=game.getResources().getBuildingList();
        buildingList.add(building);
        game
                .getResources()
                .setBuildingList(buildingList);
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
    /**
     * Убрать здание из скписка зданий
     * Remove building from resources building list
     * @param buildingId long
     * @param gameId Long
     * @return Game
     */
    @Override
    public Game removeBuilding(Long buildingId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
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
    /**
     * Если
     * operation==2 -> Добавить здание в список зданий
     * operation==3 -> Убрать здание из скписка зданий
     * operation==4 -> Добавить юнита в список юнитов
     * И обновить игру,ресурсы,армию
     * If
     * operation==2 -> Add building to resources building list
     * operation==3 -> Remove building from resources building list
     * operation==4 -> Add unit to army unit list
     * And update game,resources,army
     * @param gameId Long
     * @param operation int
     * @param id Long
     * @return Game
     */
    @Override
    public Game manageResources(Long gameId,int operation,Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Game game=getGameById(gameId);
        try {
        switch (operation) {
            case (2) -> game = addBuilding(id, gameId);
            case (3) -> game = removeBuilding(id, gameId);
            case (4) -> game = hireUnit(id, gameId);
            default -> log.error(Constants.WRONG_PARAMETER);
        }
            updateGameById(game);
            updateResourcesById(game.getResources());
            updateArmyById(game.getResources().getArmy());
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
        } catch (NullPointerException e){
            log.error(Constants.GAME+Constants.DO_NOT_EXIST);
        }

        return game;
    }
    /**
     * Если
     * operation==1 -> Получчить список зданий
     * If
     * operation==1 -> Get building list
     * @param gameId Long
     * @param operation int
     * @return Game
     */
    @Override
    public Game manageResources(Long gameId,int operation) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Game game=getGameById(gameId);
        if (operation == 1) {
            log.info(getBuildingsInfo(gameId));
        } else {
            log.error(Constants.WRONG_PARAMETER);
        }
        return game;
    }
    /**
     * Получить здание по id
     * Get building by id
     * @param id Long
     * @return Game
     */
    @Override
    public Building getBuildingById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
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
    /**
     * Получить юнита по id
     * Get unit by id
     * @param id Long
     * @return Game
     */
    @Override
    public Unit getUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
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

