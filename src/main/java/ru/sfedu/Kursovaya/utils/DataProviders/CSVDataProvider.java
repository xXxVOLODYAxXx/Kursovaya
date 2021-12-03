package ru.sfedu.Kursovaya.utils.DataProviders;

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
import ru.sfedu.Kursovaya.utils.OtherUtils.BeansGenerator;
import ru.sfedu.Kursovaya.utils.OtherUtils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

public class CSVDataProvider extends AbstractDataProvider {
    public CSVDataProvider() throws IOException {}
    private final String PATHTOCSV= ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_CSV);
    private final String CSVEXTENSION=ConfigurationUtil.getConfigurationEntry(Constants.CSV_FILE_EXTENSION);
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class);
    MongoDBDataProvider mongoDBDataProvider=new MongoDBDataProvider();
    /**ЧИТАЕМ и ПИШЕМ*/
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
    public void initDataSource(String string) throws IOException {
        string = PATHTOCSV+string+CSVEXTENSION;
        File file = new File(string);
        if (!file.exists()) {
            Path dirPath = Paths.get(PATHTOCSV);
            Files.createDirectories(dirPath);
            file.createNewFile();
        }
    }
    private void writeUnits (List<Unit> ulist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter(Constants.FILE_NAME_UNIT);
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<Unit>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(ulist);
        this.close();
    }
    private void writeBuildings (List<Building> blist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter(Constants.FILE_NAME_BUILDING);
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<Building>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(blist);
        this.close();
    }
    private void writePlayerPlanets (List<PlayerPlanet> pplist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter(Constants.FILE_NAME_PLAYER_PLANET);
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<PlayerPlanet>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(pplist);
        this.close();
    }
    private void writeEnemyPlanets (List<EnemyPlanet> eplist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter(Constants.FILE_NAME_ENEMY_PLANET);
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<EnemyPlanet>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(eplist);
        this.close();
    }
    private void writeArmy (List<Army> alist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter(Constants.FILE_NAME_ARMY);
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<Army>(this.writer).withApplyQuotesToAll(false).build();
        log.info(alist);
        statefulBeanToCSV.write(alist);
        this.close();
    }
    private void writeResources (List<Resources> rlist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter(Constants.FILE_NAME_RESOURCES);
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<Army>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(rlist);
        this.close();
    }
    private void writeGame (List<Game> glist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter(Constants.FILE_NAME_GAME);
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<Army>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(glist);
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

        String methodName=getMethodName();
        String className=getClassName();
        List<Unit> unitList=getUnitList();
        unitList.add(unit);
        unitList=sortUnitList(unitList);
        this.writeUnits(unitList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_TEST_SERVER);
    }
    public Unit getUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Unit> unitList=getUnitList();
        Unit unit=unitList.stream().filter(x-> id.equals(x.getUnitId())).findAny().orElse(null);
        if(unit==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("ERROR:Unit does not exist");
            return unit;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_TEST_SERVER);
            return unit;
        }
    }
    public void deleteUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Unit> unitList=getUnitList();
        try {
            Unit unit = unitList.stream().filter(x-> id.equals(x.getUnitId())).findAny().get();
            unitList = unitList.stream().filter(x-> !id.equals(x.getUnitId())).collect(Collectors.toList());
            this.writeUnits(unitList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void clearUnits() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Unit> unitList=getUnitList();
        unitList=unitList.stream().filter(x->null==x.getUnitId()).collect(Collectors.toList());
        this.writeUnits(unitList);
    }
    public void updateUnitById(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Unit> unitList=getUnitList();
        try{
            unit.getUnitId().equals(unitList.stream().filter(x -> unit.getUnitId().equals(x.getUnitId())).findFirst().get().getUnitId());
            unitList=unitList.stream().filter(x-> !unit.getUnitId().equals(x.getUnitId())).collect(Collectors.toList());
            writeUnits(unitList);
            unitList.add(unit);
            unitList=sortUnitList(unitList);
            writeUnits(unitList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("Unit does not exist");
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

        String methodName=getMethodName();
        String className=getClassName();
        List<Building> buildingList=getBuildingList();
        buildingList.add(building);
        buildingList=sortBuildingList(buildingList);
        this.writeBuildings(buildingList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_TEST_SERVER);
    }
    public Building getBuildingById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Building> buildingList=getBuildingList();
        Building building=buildingList.stream().filter(x-> id.equals(x.getBuildingId())).findAny().orElse(null);
        if(building==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("ERROR:Building does not exist");
            return building;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_TEST_SERVER);
            return building;
        }
    }
    public void deleteBuildingById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Building> buildingList=getBuildingList();
        try {
            Building building = buildingList.stream().filter(x-> id.equals(x.getBuildingId())).findAny().get();
            buildingList = buildingList.stream().filter(x-> !id.equals(x.getBuildingId())).collect(Collectors.toList());
            this.writeBuildings(buildingList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void clearBuildings() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Building> buildingList=getBuildingList();
        buildingList=buildingList.stream().filter(x->null==x.getBuildingId()).collect(Collectors.toList());
        this.writeBuildings(buildingList);
    }
    public void updateBuildingById(Building building) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Building> buildingList=getBuildingList();
        try{
            building.getBuildingId().equals(buildingList.stream().filter(x -> building.getBuildingId().equals(x.getBuildingId())).findFirst().get().getBuildingId());
            buildingList=buildingList.stream().filter(x-> !building.getBuildingId().equals(x.getBuildingId())).collect(Collectors.toList());
            writeBuildings(buildingList);
            buildingList.add(building);
            buildingList=sortBuildingList(buildingList);
            writeBuildings(buildingList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("Building does not exist");
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

        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetlist=getPlayerPlanetList();
        playerPlanetlist.add(playerPlanet);
        playerPlanetlist=sortPlayerPlanetList(playerPlanetlist);
        this.writePlayerPlanets(playerPlanetlist);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
    }
    public PlayerPlanet getPlayerPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetlist=getPlayerPlanetList();
        PlayerPlanet playerPlanet=playerPlanetlist.stream().filter(x-> id.equals(x.getPlanetId())).findAny().orElse(null);
        if(playerPlanet==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("ERROR:PlayerPlanet does not exist");
            return playerPlanet;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
            return playerPlanet;
        }
    }
    public void deletePlayerPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetlist=getPlayerPlanetList();
        try {
            PlayerPlanet playerPlanet = playerPlanetlist.stream().filter(x-> id.equals(x.getPlanetId())).findAny().get();
            playerPlanetlist = playerPlanetlist.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
            this.writePlayerPlanets(playerPlanetlist);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void clearPlayerPlanets() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<PlayerPlanet> playerPlanetlist=getPlayerPlanetList();
        playerPlanetlist=playerPlanetlist.stream().filter(x->null==x.getPlanetId()).collect(Collectors.toList());
        this.writePlayerPlanets(playerPlanetlist);
    }
    public void updatePlayerPlanetById(PlayerPlanet playerPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetlist=getPlayerPlanetList();
        try{
            playerPlanet.getPlanetId().equals(playerPlanetlist.stream().filter(x -> playerPlanet.getPlanetId().equals(x.getPlanetId())).findFirst().get().getPlanetId());
            playerPlanetlist=playerPlanetlist.stream().filter(x-> !playerPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            writePlayerPlanets(playerPlanetlist);
            playerPlanetlist.add(playerPlanet);
            playerPlanetlist=sortPlayerPlanetList(playerPlanetlist);
            writePlayerPlanets(playerPlanetlist);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("PlayerPlanet does not exist");
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

        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetlist=getEnemyPlanetList();
        enemyPlanetlist.add(enemyPlanet);
        enemyPlanetlist=sortEnemyPlanetList(enemyPlanetlist);
        this.writeEnemyPlanets(enemyPlanetlist);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
    }
    public EnemyPlanet getEnemyPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetlist=getEnemyPlanetList();
        EnemyPlanet enemyPlanet=enemyPlanetlist.stream().filter(x-> id.equals(x.getPlanetId())).findAny().orElse(null);
        if(enemyPlanet==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("ERROR:EnemyPlanet does not exist");
            return enemyPlanet;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
            return enemyPlanet;
        }
    }
    public void deleteEnemyPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetlist=getEnemyPlanetList();
        try {
            EnemyPlanet enemyPlanet = enemyPlanetlist.stream().filter(x-> id.equals(x.getPlanetId())).findAny().get();
            enemyPlanetlist = enemyPlanetlist.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
            this.writeEnemyPlanets(enemyPlanetlist);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void clearEnemyPlanets() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<EnemyPlanet> enemyPlanetlist=getEnemyPlanetList();
        enemyPlanetlist=enemyPlanetlist.stream().filter(x->null==x.getPlanetId()).collect(Collectors.toList());
        this.writeEnemyPlanets(enemyPlanetlist);
    }
    public void updateEnemyPlanetById(EnemyPlanet enemyPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetlist=getEnemyPlanetList();
        try{
            enemyPlanet.getPlanetId().equals(enemyPlanetlist.stream().filter(x -> enemyPlanet.getPlanetId().equals(x.getPlanetId())).findFirst().get().getPlanetId());
            enemyPlanetlist=enemyPlanetlist.stream().filter(x-> !enemyPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            writeEnemyPlanets(enemyPlanetlist);
            enemyPlanetlist.add(enemyPlanet);
            enemyPlanetlist=sortEnemyPlanetList(enemyPlanetlist);
            writeEnemyPlanets(enemyPlanetlist);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("EnemyPlanet does not exist");
        }
    }
    /**ARMY*/
    public List<Army> getArmyList() throws IOException {
        this.initReader(Constants.FILE_NAME_ARMY);
        CsvToBean<Army> csvToBean=new CsvToBeanBuilder<Army>(this.reader).withType(Army.class).build();
        List<Army> armylist=csvToBean.parse();
        armylist=sortArmyList(armylist);
        return armylist;
    }
    public void createArmy(Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armylist=getArmyList();
        log.info(armylist);
        armylist.add(army);
        armylist=sortArmyList(armylist);
        writeArmy(armylist);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_TEST_SERVER);
    }
    public Army getArmyById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armylist=getArmyList();
        Army army=armylist.stream().filter(x-> id.equals(x.getArmyId())).findAny().orElse(null);
        if(army==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("ERROR:Army does not exist");
            return army;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_TEST_SERVER);
            return army;
        }
    }
    public void deleteArmyById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armylist=getArmyList();
        try {
            Army army = armylist.stream().filter(x-> id.equals(x.getArmyId())).findAny().get();
            armylist = armylist.stream().filter(x-> !id.equals(x.getArmyId())).collect(Collectors.toList());
            this.writeArmy(armylist);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void clearArmys() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Army> armylist=getArmyList();
        armylist=armylist.stream().filter(x->null==x.getArmyId()).collect(Collectors.toList());
        this.writeArmy(armylist);
    }
    public void updateArmyById(Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armylist=getArmyList();
        try{
            army.getArmyId().equals(armylist.stream().filter(x -> army.getArmyId().equals(x.getArmyId())).findFirst().get().getArmyId());
            armylist=armylist.stream().filter(x-> !army.getArmyId().equals(x.getArmyId())).collect(Collectors.toList());
            writeArmy(armylist);
            armylist.add(army);
            armylist=sortArmyList(armylist);
            writeArmy(armylist);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("Army does not exist");
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

        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=getResourcesList();
        resourcesList.add(resources);
        resourcesList=sortResourcesList(resourcesList);
        this.writeResources(resourcesList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_TEST_SERVER);
    }
    public Resources getResourcesById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=getResourcesList();
        Resources resources=resourcesList.stream().filter(x-> id.equals(x.getResourcesId())).findAny().orElse(null);
        if(resources==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("ERROR:Resources does not exist");
            return resources;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_TEST_SERVER);
            return resources;
        }
    }
    public void deleteResourcesById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=getResourcesList();
        try {
            Resources resources = resourcesList.stream().filter(x-> id.equals(x.getResourcesId())).findAny().get();
            resourcesList = resourcesList.stream().filter(x-> !id.equals(x.getResourcesId())).collect(Collectors.toList());
            this.writeResources(resourcesList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
        catch (NoSuchElementException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void clearResourcess() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Resources> resourcesList=getResourcesList();
        resourcesList=resourcesList.stream().filter(x->null==x.getResourcesId()).collect(Collectors.toList());
        this.writeResources(resourcesList);
    }
    public void updateResourcesById(Resources resources) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=getResourcesList();
        try{
            resources.getResourcesId().equals(resourcesList.stream().filter(x -> resources.getResourcesId().equals(x.getResourcesId())).findFirst().get().getResourcesId());
            resourcesList=resourcesList.stream().filter(x-> !resources.getResourcesId().equals(x.getResourcesId())).collect(Collectors.toList());
            writeResources(resourcesList);
            resourcesList.add(resources);
            resourcesList=sortResourcesList(resourcesList);
            writeResources(resourcesList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("Resources does not exist");
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

        String methodName=getMethodName();
        String className=getClassName();
        List<Game> gameList=getGameList();
        gameList.add(game);
        gameList=sortGameList(gameList);
        this.writeGame(gameList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_TEST_SERVER);
    }
    public Game getGameById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Game> gameList=getGameList();
        Game game=gameList.stream().filter(x-> id.equals(x.getGameId())).findAny().orElse(null);
        if(game==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.error("Game does not exist");
            return game;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_TEST_SERVER);
            return game;
        }
    }
    public void deleteGameById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Game> gameList=getGameList();
        try {
            Game game = gameList.stream().filter(x-> id.equals(x.getGameId())).findAny().get();
            gameList = gameList.stream().filter(x-> !id.equals(x.getGameId())).collect(Collectors.toList());
            this.writeGame(gameList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
        }
    }
    public void clearGames() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Game> gameList=getGameList();
        gameList=gameList.stream().filter(x->null==x.getGameId()).collect(Collectors.toList());
        this.writeGame(gameList);
    }
    public void updateGameById(Game game) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Game> gameList=getGameList();
        try{
            game.getGameId().equals(gameList.stream().filter(x -> game.getGameId().equals(x.getGameId())).findFirst().get().getGameId());
            gameList=gameList.stream().filter(x-> !game.getGameId().equals(x.getGameId())).collect(Collectors.toList());
            writeGame(gameList);
            gameList.add(game);
            gameList=sortGameList(gameList);
            writeGame(gameList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_TEST_SERVER);
        } catch (NoSuchElementException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_TEST_SERVER);
            log.info("Game does not exist");
        }
    }

    /**CRUD
     * CORE*/
    @Override
    public Game createUniverse(Game game,Resources resources,Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        if (game.getGameId()==null || army.getArmyId() == null || resources.getResourcesId() == null){
            log.info("Wrong parameters");
            return null;
        } else {
        createGame(game);
        createResources(resources);
        createArmy(army);
        log.info("Your resources:");
        log.info("Food:"+game.getResources().getFood());
        log.info("Metal:"+game.getResources().getMetal());
        log.info("Gold:"+game.getResources().getGold());
        log.info("Your army power:");
        log.info("Health:"+game.getResources().getArmy().getArmyInfo().getArmyHealthPoints()+" Attack:"+game.getResources().getArmy().getArmyInfo().getArmyAttackPoints());
        log.info("Your planets:");
        game.getPlayerPlanetList().forEach(x->{
            log.info("Id:"+x.getPlanetId()+" Planet name:"+x.getPlanetName()+" Building limit:"+x.getBuildingLimit());
        });
        log.info("Enemy planets:");
        game.getEnemyPlanetList().forEach(x->{
            log.info("Id:"+x.getPlanetId()+" Planet name:"+x.getPlanetName());
        });
        return game;
        }
    }
    @Override
    public Boolean deleteUniverse(Long gameId) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        try {
            getGameById(gameId).getGameId();
            deleteGameById(gameId);
            deleteArmyById(gameId);
            deleteResourcesById(gameId);
            log.info("Universe deleted");
            return true;
        } catch (NullPointerException e){
            log.error("Nothing to delete");
            return false;
        }
    }
    @Override
    public EnemyPlanet getEnemyPower(Long planetId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            return getGameById(gameId)
                    .getEnemyPlanetList()
                    .get(Math.toIntExact(planetId)-1);
        } catch (NullPointerException e){
            log.info("You conquered all planets");
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
        }catch (NullPointerException e){
            log.info("Your army is dead");
            return null;
        }
    }
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
                    log.info("Game over");
                    return result = false;
                }}
            List<EnemyPlanet> enemyPlanetList = game.getEnemyPlanetList();
            enemyPlanetList.remove(Math.toIntExact(planetId)-1);
            game.setEnemyPlanetList(enemyPlanetList);
            PlayerPlanet playerPlanet = new PlayerPlanet();
            playerPlanet.setPlanetId(enemyPlanet.getPlanetId());
            playerPlanet.setPlanetName(enemyPlanet.getPlanetName());
            playerPlanet.setPlanetType("PLAYER");
            playerPlanet.setBuildingLimit(10);
            List<PlayerPlanet> playerPlanetList = game.getPlayerPlanetList();
            playerPlanetList.add(playerPlanet);
            game.setPlayerPlanetList(playerPlanetList);
            updateArmyById(game
                    .getResources()
                    .getArmy());
            updateResourcesById(game
                    .getResources());
            updateGameById(game);
            result=true;
        }catch (NullPointerException e){
            log.info("EnemyPlanet does not exist");
            result=false;
        } finally {
            return result;
        }


    }
    @Override
    public Game hireUnit(Long unitId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Game game = getGameById(gameId);
        Unit unit = getUnitById(unitId);
        try {game
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
        }catch (NullPointerException e){
            log.error("Wrong parameters");
        }finally {
            return game;
        }
    }
    @Override
    public List<Building> getBuildingsInfo(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            return getGameById(gameId)
                    .getResources()
                    .getBuildingList();
        }catch (NullPointerException e){
            return null;
        }

    }
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
            log.info("BuildingList is empty");
        }

        return game;
    }
    @Override
    public Game manageResources(Long gameId,int operation,Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Game game=new Game();
        try {
        switch (operation) {
            case (2) -> game = addBuilding(id, gameId);
            case (3) -> game = removeBuilding(id, gameId);
            case (4) -> game = hireUnit(id, gameId);
            default -> log.error("Wrong id");
        }
            updateGameById(game);
            updateResourcesById(game.getResources());
            updateArmyById(game.getResources().getArmy());
        } catch (NullPointerException e){
            log.error("Game does not exist");
        }
        return game;
    }
    @Override
    public Game manageResources(Long gameId,int operation) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        Game game=new Game();
        if (operation == 1) {
            log.info(getBuildingsInfo(gameId));
        } else {
            log.error("Wrong operation");
        }
        return game;
    }
}

