package ru.sfedu.Kursovaya.api.DataProviders;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.UtilBeans.XMLList;
import ru.sfedu.Kursovaya.utils.OtherUtils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

import javax.xml.bind.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class XMLDataProvider extends AbstractDataProvider {

    

    public XMLDataProvider() throws IOException, JAXBException {}
    MongoDBDataProvider mongoDBDataProvider=new MongoDBDataProvider();
    private FileReader fileReader=null;
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class);
    private final JAXBContext jaxbContext = JAXBContext.newInstance(XMLList.class);
    private final Marshaller Marshaller = jaxbContext.createMarshaller();
    private final Unmarshaller Unmarshaller = jaxbContext.createUnmarshaller();
    private final String PATH_TO_XML= ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_XML);
    private final String XML_FILE_EXTENSION=ConfigurationUtil.getConfigurationEntry(Constants.XML_FILE_EXTENSION);

    private void initDataSource(String string) throws IOException, JAXBException {
        String str=string;
        string = PATH_TO_XML + string  +XML_FILE_EXTENSION;
        File file = new File(string);
        if (!file.exists()) {
            XMLList xmlList = new XMLList();
            Path dirPath = Paths.get(PATH_TO_XML);
            Files.createDirectories(dirPath);
            file.createNewFile();
            Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Marshaller.marshal(xmlList,initFile(str));
        }
    }
    private void initReader (String string) throws IOException, JAXBException {
        initDataSource(string);
        this.fileReader=new FileReader(PATH_TO_XML+string+XML_FILE_EXTENSION);
    }
    private File initFile(String string) throws IOException, JAXBException {
        initDataSource(string);
        String PATH= PATH_TO_XML+string+XML_FILE_EXTENSION;
        return new File(PATH);
    }
    private void closeReader () throws IOException {
        fileReader.close();
    }

    private String getClassName(){
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }
    private String getMethodName(){
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
    private void unitsToXML(List<Unit> ulist) throws JAXBException, IOException {
        initReader(Constants.FILE_NAME_UNIT);
        XMLList unitList=new XMLList();
        unitList.setUnits(ulist);
        Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Marshaller.marshal(unitList,initFile(Constants.FILE_NAME_UNIT));
        closeReader();
    }
    private List<Unit> unitsFromXML() throws JAXBException, IOException {
        XMLList unitList=(XMLList) Unmarshaller.unmarshal(initFile(Constants.FILE_NAME_UNIT));
        return unitList.getUnits();
    }
    private List<Unit> sortUnitList(List<Unit> unitList) throws IOException {
        unitList=unitList.stream().sorted((o1, o2)->o1.getUnitId().compareTo(o2.getUnitId())).collect(Collectors.toList());
        return unitList;
    }
    private void buildingsToXML(List<Building> blist) throws JAXBException, IOException {
        initReader(Constants.FILE_NAME_BUILDING);
        XMLList buildingList=new XMLList();
        buildingList.setBuildings(blist);
        Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Marshaller.marshal(buildingList,initFile(Constants.FILE_NAME_BUILDING));
        closeReader();
    }
    private List<Building> buildingsFromXML() throws JAXBException, IOException {
        XMLList buildingList=(XMLList) Unmarshaller.unmarshal(initFile(Constants.FILE_NAME_BUILDING));
        return buildingList.getBuildings();
    }
    private List<Building> sortBuildingList(List<Building> buildingList) throws IOException {
        buildingList=buildingList.stream().sorted((o1, o2)->o1.getBuildingId().compareTo(o2.getBuildingId())).collect(Collectors.toList());
        return buildingList;
    }
    private void playerPlanetToXML(List<PlayerPlanet> pplist) throws JAXBException, IOException {
        initReader(Constants.FILE_NAME_PLAYER_PLANET);
        XMLList playerPlanetList=new XMLList();
        playerPlanetList.setPlayerPlanetList(pplist);
        Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Marshaller.marshal(playerPlanetList,initFile(Constants.FILE_NAME_PLAYER_PLANET));
        closeReader();
    }
    private List<PlayerPlanet> playerPlanetFromXML() throws JAXBException, IOException {
        XMLList playerPlanetList=(XMLList) Unmarshaller.unmarshal(initFile(Constants.FILE_NAME_PLAYER_PLANET));
        return playerPlanetList.getPlayerPlanetList();
    }
    private List<PlayerPlanet> sortPlayerPlanetList(List<PlayerPlanet> playerPlanetList) throws IOException {
        playerPlanetList=playerPlanetList.stream().sorted((o1, o2)->o1.getPlanetId().compareTo(o2.getPlanetId())).collect(Collectors.toList());
        return playerPlanetList;
    }
    private void enemyPlanetToXML(List<EnemyPlanet> eplist) throws JAXBException, IOException {
        initReader(Constants.FILE_NAME_ENEMY_PLANET);
        XMLList enemyPlanetList=new XMLList();
        enemyPlanetList.setEnemyPlanetList(eplist);
        Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Marshaller.marshal(enemyPlanetList,initFile(Constants.FILE_NAME_ENEMY_PLANET));
        closeReader();
    }
    private List<EnemyPlanet> enemyPlanetFromXML() throws JAXBException, IOException {
        XMLList enemyPlanetList=(XMLList) Unmarshaller.unmarshal(initFile(Constants.FILE_NAME_ENEMY_PLANET));
        return enemyPlanetList.getEnemyPlanetList();
    }
    private List<EnemyPlanet> sortEnemyPlanetList(List<EnemyPlanet> enemyPlanetList) throws IOException {
        enemyPlanetList=enemyPlanetList.stream().sorted((o1, o2)->o1.getPlanetId().compareTo(o2.getPlanetId())).collect(Collectors.toList());
        return enemyPlanetList;
    }
    private void armyToXML(List<Army> alist) throws JAXBException, IOException {
        initReader(Constants.FILE_NAME_ARMY);
        XMLList armyList=new XMLList();
        armyList.setArmyList(alist);
        Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Marshaller.marshal(armyList,initFile(Constants.FILE_NAME_ARMY));
        closeReader();
    }
    private List<Army> armyFromXML() throws JAXBException, IOException {
        XMLList armyList=(XMLList) Unmarshaller.unmarshal(initFile(Constants.FILE_NAME_ARMY));
        return armyList.getArmyList();
    }
    private List<Army> sortArmyList(List<Army> armyList) throws IOException {
        armyList=armyList.stream().sorted((o1, o2)->o1.getArmyId().compareTo(o2.getArmyId())).collect(Collectors.toList());
        return armyList;
    }
    private void resourcesToXML(List<Resources> rlist) throws JAXBException, IOException {
        initReader(Constants.FILE_NAME_RESOURCES);
        XMLList resourcesList=new XMLList();
        resourcesList.setResourcesList(rlist);
        Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Marshaller.marshal(resourcesList,initFile(Constants.FILE_NAME_RESOURCES));
        closeReader();
    }
    private List<Resources> resourcesFromXML() throws JAXBException, IOException {
        XMLList resourcesList=(XMLList) Unmarshaller.unmarshal(initFile(Constants.FILE_NAME_RESOURCES));
        return resourcesList.getResourcesList();
    }
    private List<Resources> sortResourcesList(List<Resources> resourcesList) throws IOException {
        resourcesList=resourcesList.stream().sorted((o1, o2)->o1.getResourcesId().compareTo(o2.getResourcesId())).collect(Collectors.toList());
        return resourcesList;
    }
    private void gameToXML(List<Game> glist) throws JAXBException, IOException {
        initReader(Constants.FILE_NAME_GAME);
        XMLList gameList=new XMLList();
        gameList.setGameList(glist);
        Marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Marshaller.marshal(gameList,initFile(Constants.FILE_NAME_GAME));
        closeReader();
    }
    private List<Game> gameFromXML() throws JAXBException, IOException {
        XMLList gameList=(XMLList) Unmarshaller.unmarshal(initFile(Constants.FILE_NAME_GAME));
        return gameList.getGameList();
    }
    private List<Game> sortGameList(List<Game> gameList) throws IOException {
        gameList=gameList.stream().sorted((o1, o2)->o1.getGameId().compareTo(o2.getGameId())).collect(Collectors.toList());
        return gameList;
    }
    /**CRUD
     * UNIT*/
    public List<Unit> getUnitList() throws JAXBException, IOException {
        List<Unit> unitList=unitsFromXML();
        unitList=sortUnitList(unitList);
        return unitList;
    }
    public void createUnit(Unit unit) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Unit> unitList=new ArrayList<>();
        if (unitsFromXML()==null){
            log.info("UnitList in null");
        } else {
            unitList=unitsFromXML();
        }
        unitList.add(unit);
        unitList=sortUnitList(unitList);
        unitsToXML(unitList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
    }
    public void deleteUnitById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Unit> unitList=unitsFromXML();
        try {
            Unit unit = unitList.stream().filter(x-> id.equals(x.getUnitId())).findAny().get();
            unitList = unitList.stream().filter(x-> !id.equals(x.getUnitId())).collect(Collectors.toList());
            unitsToXML(unitList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
        catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | NullPointerException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
    }
    public void updateUnitById(Unit unit) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Unit> unitList=unitsFromXML();
        try{
            unit.getUnitId().equals(unitList.stream().filter(x -> unit.getUnitId().equals(x.getUnitId())).findFirst().get().getUnitId());
            unitList=unitList.stream().filter(x-> !unit.getUnitId().equals(x.getUnitId())).collect(Collectors.toList());
            unitsToXML(unitList);
            unitList.add(unit);
            unitList=sortUnitList(unitList);
            unitsToXML(unitList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(unit,Constants.UNIT,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        } catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("Unit does not exist");
        }
    }
    /**BUILDING*/
    public List<Building> getBuildingList() throws JAXBException, IOException {
        List<Building> buildingList=buildingsFromXML();
        buildingList=sortBuildingList(buildingList);
        return buildingList;
    }
    public void createBuilding(Building building) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Building> buildingList=new ArrayList<>();
        if (buildingsFromXML()==null){
            log.info("BuildingList in null");
        } else {
            buildingList=buildingsFromXML();
        }
        buildingList.add(building);
        buildingList=sortBuildingList(buildingList);
        buildingsToXML(buildingList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
    }
    public void deleteBuildingById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Building> buildingList=buildingsFromXML();
        try {
            Building building = buildingList.stream().filter(x-> id.equals(x.getBuildingId())).findAny().get();
            buildingList = buildingList.stream().filter(x-> !id.equals(x.getBuildingId())).collect(Collectors.toList());
            buildingsToXML(buildingList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
        catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | NullPointerException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
    }
    public void updateBuildingById(Building building) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Building> buildingList=buildingsFromXML();
        try{
            building.getBuildingId().equals(buildingList.stream().filter(x -> building.getBuildingId().equals(x.getBuildingId())).findFirst().get().getBuildingId());
            buildingList=buildingList.stream().filter(x-> !building.getBuildingId().equals(x.getBuildingId())).collect(Collectors.toList());
            buildingsToXML(buildingList);
            buildingList.add(building);
            buildingList=sortBuildingList(buildingList);
            buildingsToXML(buildingList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(building,Constants.BUILDING,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        } catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("Building does not exist");
        }
    }
    /**PLAYERPLANET*/
    public List<PlayerPlanet> getPlayerPlanetList() throws JAXBException, IOException {
        List<PlayerPlanet> playerPlanetList=playerPlanetFromXML();
        playerPlanetList=sortPlayerPlanetList(playerPlanetList);
        return playerPlanetList;
    }
    public void createPlayerPlanet(PlayerPlanet playerPlanet) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetList=new ArrayList<>();
        if (playerPlanetFromXML()==null){
            log.info("PlayerPlanetList in null");
        } else {
            playerPlanetList=playerPlanetFromXML();
        }
        playerPlanetList.add(playerPlanet);
        playerPlanetList=sortPlayerPlanetList(playerPlanetList);
        playerPlanetToXML(playerPlanetList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
    }
    public PlayerPlanet getPlayerPlanetById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetList=playerPlanetFromXML();
        PlayerPlanet playerPlanet=playerPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().orElse(null);
        if(playerPlanet==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("ERROR:PlayerPlanet does not exist");
            return playerPlanet;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            return playerPlanet;
        }
    }
    public void deletePlayerPlanetById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetList=playerPlanetFromXML();
        try {
            PlayerPlanet playerPlanet = playerPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().get();
            playerPlanetList = playerPlanetList.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
            playerPlanetToXML(playerPlanetList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
        catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | NullPointerException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
    }
    public void updatePlayerPlanetById(PlayerPlanet playerPlanet) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<PlayerPlanet> playerPlanetList=playerPlanetFromXML();
        try{
            playerPlanet.getPlanetId().equals(playerPlanetList.stream().filter(x -> playerPlanet.getPlanetId().equals(x.getPlanetId())).findFirst().get().getPlanetId());
            playerPlanetList=playerPlanetList.stream().filter(x-> !playerPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            playerPlanetToXML(playerPlanetList);
            playerPlanetList.add(playerPlanet);
            playerPlanetList=sortPlayerPlanetList(playerPlanetList);
            playerPlanetToXML(playerPlanetList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(playerPlanet,Constants.PLAYER_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        } catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("PlayerPlanet does not exist");
        }
    }
    /**ENEMYPLANET*/
    public List<EnemyPlanet> getEnemyPlanetList() throws JAXBException, IOException {
        List<EnemyPlanet> enemyPlanetList=enemyPlanetFromXML();
        enemyPlanetList=sortEnemyPlanetList(enemyPlanetList);
        return enemyPlanetList;
    }
    public void createEnemyPlanet(EnemyPlanet enemyPlanet) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetList=new ArrayList<>();
        if (enemyPlanetFromXML()==null){
            log.info("EnemyPlanetList in null");
        } else {
            enemyPlanetList=enemyPlanetFromXML();
        }
        enemyPlanetList.add(enemyPlanet);
        enemyPlanetList=sortEnemyPlanetList(enemyPlanetList);
        enemyPlanetToXML(enemyPlanetList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
    }
    public EnemyPlanet getEnemyPlanetById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetList=enemyPlanetFromXML();
        EnemyPlanet enemyPlanet=enemyPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().orElse(null);
        if(enemyPlanet==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("ERROR:EnemyPlanet does not exist");
            return enemyPlanet;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            return enemyPlanet;
        }
    }
    public void deleteEnemyPlanetById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetList=enemyPlanetFromXML();
        try {
            EnemyPlanet enemyPlanet = enemyPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().get();
            enemyPlanetList = enemyPlanetList.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
            enemyPlanetToXML(enemyPlanetList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
        catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | NullPointerException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
    }
    public void updateEnemyPlanetById(EnemyPlanet enemyPlanet) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<EnemyPlanet> enemyPlanetList=enemyPlanetFromXML();
        try{
            enemyPlanet.getPlanetId().equals(enemyPlanetList.stream().filter(x -> enemyPlanet.getPlanetId().equals(x.getPlanetId())).findFirst().get().getPlanetId());
            enemyPlanetList=enemyPlanetList.stream().filter(x-> !enemyPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            enemyPlanetToXML(enemyPlanetList);
            enemyPlanetList.add(enemyPlanet);
            enemyPlanetList=sortEnemyPlanetList(enemyPlanetList);
            enemyPlanetToXML(enemyPlanetList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(enemyPlanet,Constants.ENEMY_PLANET,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        } catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("EnemyPlanet does not exist");
        }
    }
    /**ARMY*/
    public List<Army> getArmyList() throws JAXBException, IOException {
        List<Army> armyList=armyFromXML();
        armyList=sortArmyList(armyList);
        return armyList;
    }
    public void createArmy(Army army) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armyList=new ArrayList<>();
        if (armyFromXML()==null){
            log.info("ArmyList in null");
        } else {
            armyList=armyFromXML();
        }
        armyList.add(army);
        armyList=sortArmyList(armyList);
        armyToXML(armyList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
    }
    public Army getArmyById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armyList=armyFromXML();
        Army army=armyList.stream().filter(x-> id.equals(x.getArmyId())).findAny().orElse(null);
        if(army==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("ERROR:Army does not exist");
            return army;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            return army;
        }
    }
    public void deleteArmyById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armyList=armyFromXML();
        try {
            Army army = armyList.stream().filter(x-> id.equals(x.getArmyId())).findAny().get();
            armyList = armyList.stream().filter(x-> !id.equals(x.getArmyId())).collect(Collectors.toList());
            armyToXML(armyList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
        catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
    }
    public void updateArmyById(Army army) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Army> armyList=armyFromXML();
        try{
            army.getArmyId().equals(armyList.stream().filter(x -> army.getArmyId().equals(x.getArmyId())).findFirst().get().getArmyId());
            armyList=armyList.stream().filter(x-> !army.getArmyId().equals(x.getArmyId())).collect(Collectors.toList());
            armyToXML(armyList);
            armyList.add(army);
            armyList=sortArmyList(armyList);
            armyToXML(armyList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(army,Constants.ARMY,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        } catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("Army does not exist");
        }
    }
    /**RESOURCES*/
    public List<Resources> getResourcesList() throws JAXBException, IOException {
        List<Resources> resourcesList=resourcesFromXML();
        resourcesList=sortResourcesList(resourcesList);
        return resourcesList;
    }
    public void createResources(Resources resources) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=new ArrayList<>();
        if (resourcesFromXML()==null){
            log.info("ResourcesList in null");
        } else {
            resourcesList=resourcesFromXML();
        }
        resourcesList.add(resources);
        resourcesList=sortResourcesList(resourcesList);
        resourcesToXML(resourcesList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
    }
    public Resources getResourcesById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=resourcesFromXML();
        Resources resources=resourcesList.stream().filter(x-> id.equals(x.getResourcesId())).findAny().orElse(null);
        if(resources==null){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("ERROR:Resources does not exist");
            return resources;
        } else {
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            return resources;
        }
    }
    public void deleteResourcesById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=resourcesFromXML();
        try {
            Resources resources = resourcesList.stream().filter(x-> id.equals(x.getResourcesId())).findAny().get();
            resourcesList = resourcesList.stream().filter(x-> !id.equals(x.getResourcesId())).collect(Collectors.toList());
            resourcesToXML(resourcesList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
        catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
    }
    public void updateResourcesById(Resources resources) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Resources> resourcesList=resourcesFromXML();
        try{
            resources.getResourcesId().equals(resourcesList.stream().filter(x -> resources.getResourcesId().equals(x.getResourcesId())).findFirst().get().getResourcesId());
            resourcesList=resourcesList.stream().filter(x-> !resources.getResourcesId().equals(x.getResourcesId())).collect(Collectors.toList());
            resourcesToXML(resourcesList);
            resourcesList.add(resources);
            resourcesList=sortResourcesList(resourcesList);
            resourcesToXML(resourcesList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(resources,Constants.RESOURCES,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        } catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("Resources does not exist");
        }
    }
    /**GAME*/
    public List<Game> getGameList() throws JAXBException, IOException {
        List<Game> gameList=gameFromXML();
        gameList=sortGameList(gameList);
        return gameList;
    }
    public void createGame(Game game) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Game> gameList=new ArrayList<>();
        if (gameFromXML()==null){
            log.info("GameList in null");
        } else {
            gameList=gameFromXML();
        }
        gameList.add(game);
        gameList=sortGameList(gameList);
        gameToXML(gameList);
        saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
    }
    public Game getGameById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        try {
            List<Game> gameList=gameFromXML();
            Game game=gameList.stream().filter(x-> id.equals(x.getGameId())).findAny().orElse(null);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            return game;
        }catch (NullPointerException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("ERROR:Game does not exist");
            return null;
        }
    }
    public void deleteGameById(Long id) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Game> gameList=gameFromXML();
        try {
            Game game = gameList.stream().filter(x-> id.equals(x.getGameId())).findAny().get();
            gameList = gameList.stream().filter(x-> !id.equals(x.getGameId())).collect(Collectors.toList());
            gameToXML(gameList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
        catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            log.info(e);
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        }
    }
    public void updateGameById(Game game) throws JAXBException, IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String methodName=getMethodName();
        String className=getClassName();
        List<Game> gameList=gameFromXML();
        try{
            game.getGameId().equals(gameList.stream().filter(x -> game.getGameId().equals(x.getGameId())).findFirst().get().getGameId());
            gameList=gameList.stream().filter(x-> !game.getGameId().equals(x.getGameId())).collect(Collectors.toList());
            gameToXML(gameList);
            gameList.add(game);
            gameList=sortGameList(gameList);
            gameToXML(gameList);
            saveToLog(mongoDBDataProvider.initHistoryContentTrue(game,Constants.GAME,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
        } catch (NoSuchElementException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            saveToLog(mongoDBDataProvider.initHistoryContentFalse(Constants.NULL,className,methodName),Constants.MONGODB_XML_TEST_SERVER);
            log.info("Game does not exist");
        }
    }
    /**CRUD
     * CORE*/
    @Override
    public Game createUniverse(Game game,Resources resources,Army army) throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
        if (game.getGameId()==null || army.getArmyId() == null || resources.getResourcesId() == null){
            log.info(Constants.WRONG_PARAMETER);
            return null;
        } else {
            createGame(game);
            createResources(resources);
            createArmy(army);
            /**
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
             */
            return game;
        }
    }
    @Override
    public Boolean deleteUniverse(Long gameId)  {
        try {
            getGameById(gameId).getGameId();
            deleteGameById(gameId);
            deleteArmyById(gameId);
            deleteResourcesById(gameId);
            log.info(Constants.UNIVERSE_DELETED);
            return true;
        } catch (NullPointerException | JAXBException | IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            log.error(Constants.WRONG_PARAMETER);
            return false;
        }
    }
    @Override
    public EnemyPlanet getEnemyPower(Long planetId,Long gameId) {
        try {
            return getGameById(gameId)
                    .getEnemyPlanetList()
                    .get(Math.toIntExact(planetId)-1);
        } catch (NullPointerException | JAXBException | IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e){
            log.info(Constants.WRONG_PARAMETER);
            return null;
        }
    }
    @Override
    public ArmyInfo getArmyPower(Long gameId) throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
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
    @Override
    public Boolean attackPlanet(Long planetId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
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
            result=true;
        }catch (NullPointerException e){
            log.info(Constants.ENEMY_PLANET+Constants.DO_NOT_EXIST);
            result=false;
        } finally {
            return result;
        }


    }
    @Override
    public Game hireUnit(Long unitId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
        Game game = getGameById(gameId);
        try {
        Unit unit = getUnitById(unitId);

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
    @Override
    public List<Building> getBuildingsInfo(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            return getGameById(gameId)
                    .getResources()
                    .getBuildingList();
        }catch (NullPointerException | JAXBException e){
            return null;
        }

    }
    @Override
    public Game addBuilding(Long buildingId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
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
    public Game removeBuilding(Long buildingId,Long gameId) throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
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
    public Game manageResources(Long gameId,int operation,Long id) throws CsvRequiredFieldEmptyException, JAXBException, CsvDataTypeMismatchException, IOException {
        Game game=new Game();
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
        } catch (NullPointerException e){
            log.error(Constants.GAME+Constants.DO_NOT_EXIST);
        }
        return game;
    }
    @Override
    public Game manageResources(Long gameId,int operation) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        Game game=new Game();
        if (operation == 1) {
            log.info(getBuildingsInfo(gameId));
        } else {
            log.error(Constants.WRONG_PARAMETER);
        }
        return game;
    }
    @Override
    public Building getBuildingById(Long id) throws JAXBException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
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
    public Unit getUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException {
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
