package ru.sfedu.Kursovaya.utils;

import java.io.*;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.sfedu.Kursovaya.Beans.Building;
import ru.sfedu.Kursovaya.Beans.EnemyPlanet;
import ru.sfedu.Kursovaya.Beans.PlayerPlanet;
import ru.sfedu.Kursovaya.Beans.Unit;
import java.util.stream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CSVDataProvider {
    public CSVDataProvider() throws IOException {}
    private final String PATHTOCSV= ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_CSV);
    private final String CSVEXTENSION=ConfigurationUtil.getConfigurationEntry(Constants.CSV_FILE_EXTENSION);
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class);

    /**ЧИТАЕМ и ПИШЕМ*/
    private CSVReader reader;
    private CSVWriter writer;
    private void initReader(String string) throws FileNotFoundException {
        this.reader=new CSVReader(new FileReader(PATHTOCSV+string+CSVEXTENSION));
    }
    private void initWriter(String string) throws IOException {
        this.writer=new CSVWriter(new FileWriter(PATHTOCSV+string+CSVEXTENSION));
    }
    private void close() throws IOException {
        if (reader!=null){this.reader.close();}
        if(writer!=null){this.writer.close();}
    }

    private void writeUnits (List<Unit> ulist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter("Units");
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<Unit>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(ulist);
        this.close();
    }
    private void writeBuildings (List<Building> blist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter("Buildings");
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<Building>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(blist);
        this.close();
    }
    private void writePlayerPlanets (List<PlayerPlanet> pplist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter("PlayerPlanet");
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<PlayerPlanet>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(pplist);
        this.close();
    }
    private void writeEnemyPlanets (List<EnemyPlanet> eplist) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException {
        this.initWriter("EnemyPlanet");
        StatefulBeanToCsv statefulBeanToCSV=new StatefulBeanToCsvBuilder<EnemyPlanet>(this.writer).withApplyQuotesToAll(false).build();
        statefulBeanToCSV.write(eplist);
        this.close();
    }
    private List<Unit> sortUnitList(List<Unit> unitList) throws IOException {
        unitList=unitList.stream().sorted((o1, o2)->o1.getUnitId().compareTo(o2.getUnitId())).collect(Collectors.toList());
        return unitList;
    }
    private List<Building> sortBuildingList(List<Building> buildingList) throws IOException {
        buildingList=buildingList.stream().sorted((o1, o2)->o1.getBuildingId().compareTo(o2.getBuildingId())).collect(Collectors.toList());
        return buildingList;
    }
    private List<PlayerPlanet> sortPlayerPlanetList(List<PlayerPlanet> pplist) throws IOException {
        pplist=pplist.stream().sorted((o1, o2)->o1.getPlanetId().compareTo(o2.getPlanetId())).collect(Collectors.toList());
        return pplist;
    }
    private List<EnemyPlanet> sortEnemyPlanetList(List<EnemyPlanet> eplist) throws IOException {
        eplist=eplist.stream().sorted((o1, o2)->o1.getPlanetId().compareTo(o2.getPlanetId())).collect(Collectors.toList());
        return eplist;
    }

    /**UNIT*/
    public List<Unit> getUnitList() throws IOException {
        this.initReader("Units");
        CsvToBean<Unit> csvToBean=new CsvToBeanBuilder<Unit>(this.reader).withType(Unit.class).build();
        List<Unit> unitlist=csvToBean.parse();
        unitlist=sortUnitList(unitlist);
        return unitlist;
    }
    public void createUnit(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Unit> unitList=getUnitList();
        unitList.add(unit);
        unitList=sortUnitList(unitList);
        this.writeUnits(unitList);
    }
    public Unit getUnitById(Long id) throws IOException {
        List<Unit> unitList=getUnitList();
        Unit unit=unitList.stream().filter(x-> id.equals(x.getUnitId())).findAny().orElse(null);
        if(unit==null){
            log.info("ERROR:Unit does not exist");
            return unit;
        } else {
            return unit;
        }
    }
    public void deleteUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Unit> unitList=getUnitList();
        unitList=unitList.stream().filter(x-> !id.equals(x.getUnitId())).collect(Collectors.toList());
        this.writeUnits(unitList);
    }
    public void clearUnits() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Unit> unitList=getUnitList();
        unitList=unitList.stream().filter(x->null==x.getUnitId()).collect(Collectors.toList());
        this.writeUnits(unitList);
    }
    public void updateUnitById(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Unit> unitList=getUnitList();
        try{
            unit.getUnitId().equals(unitList.stream().filter(x -> unit.getUnitId().equals(x.getUnitId())).findFirst().get().getUnitId());
            unitList=unitList.stream().filter(x-> !unit.getUnitId().equals(x.getUnitId())).collect(Collectors.toList());
            writeUnits(unitList);
            unitList.add(unit);
            unitList=sortUnitList(unitList);
            writeUnits(unitList);
        } catch (NoSuchElementException e){
            log.info("Unit does not exist");
        }
    }
    /**BUILDING*/
    public List<Building> getBuildingList() throws IOException {
        this.initReader("Buildings");
        CsvToBean<Building> csvToBean=new CsvToBeanBuilder<Building>(this.reader).withType(Building.class).build();
        List<Building> buildingList=csvToBean.parse();
        buildingList=sortBuildingList(buildingList);
        return buildingList;
    }
    public void createBuilding(Building building) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Building> buildingList=getBuildingList();
        buildingList.add(building);
        buildingList=sortBuildingList(buildingList);
        this.writeBuildings(buildingList);
    }
    public Building getBuildingById(Long id) throws IOException {
        List<Building> buildingList=getBuildingList();
        Building building=buildingList.stream().filter(x-> id.equals(x.getBuildingId())).findAny().orElse(null);
        if(building==null){
            log.info("ERROR:Building does not exist");
            return building;
        } else {
            return building;
        }
    }
    public void deleteBuildingById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Building> buildingList=getBuildingList();
        buildingList=buildingList.stream().filter(x-> !id.equals(x.getBuildingId())).collect(Collectors.toList());
        this.writeBuildings(buildingList);
    }
    public void clearBuildings() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Building> buildingList=getBuildingList();
        buildingList=buildingList.stream().filter(x->null==x.getBuildingId()).collect(Collectors.toList());
        this.writeBuildings(buildingList);
    }
    public void updateBuildingById(Building building) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Building> buildingList=getBuildingList();
        try{
            building.getBuildingId().equals(buildingList.stream().filter(x -> building.getBuildingId().equals(x.getBuildingId())).findFirst().get().getBuildingId());
            buildingList=buildingList.stream().filter(x-> !building.getBuildingId().equals(x.getBuildingId())).collect(Collectors.toList());
            writeBuildings(buildingList);
            buildingList.add(building);
            buildingList=sortBuildingList(buildingList);
            writeBuildings(buildingList);
        } catch (NoSuchElementException e){
            log.info("Building does not exist");
        }
    }
    /**PLAYERPLANET*/
    public List<PlayerPlanet> getPlayerPlanetList() throws IOException {
        this.initReader("PlayerPlanet");
        CsvToBean<PlayerPlanet> csvToBean=new CsvToBeanBuilder<PlayerPlanet>(this.reader).withType(PlayerPlanet.class).build();
        List<PlayerPlanet> playerPlanetList=csvToBean.parse();
        playerPlanetList=sortPlayerPlanetList(playerPlanetList);
        return playerPlanetList;
    }
    public void createPlayerPlanet(PlayerPlanet playerPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        playerPlanetList.add(playerPlanet);
        playerPlanetList=sortPlayerPlanetList(playerPlanetList);
        this.writePlayerPlanets(playerPlanetList);
    }
    public PlayerPlanet getPlayerPlanetById(Long id) throws IOException {
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        PlayerPlanet playerPlanet=playerPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().orElse(null);
        if(playerPlanet==null){
            log.info("ERROR:PlayerPlanet does not exist");
            return playerPlanet;
        } else {
            return playerPlanet;
        }
    }
    public void deletePlayerPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        playerPlanetList=playerPlanetList.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
        this.writePlayerPlanets(playerPlanetList);
    }
    public void clearPlayerPlanets() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        playerPlanetList=playerPlanetList.stream().filter(x->null==x.getPlanetId()).collect(Collectors.toList());
        this.writePlayerPlanets(playerPlanetList);
    }
    public void updatePlayerPlanetById(PlayerPlanet playerPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<PlayerPlanet> playerPlanetList=getPlayerPlanetList();
        try{
            playerPlanet.getPlanetId().equals(playerPlanetList.stream().filter(x -> playerPlanet.getPlanetId().equals(x.getPlanetId())).findFirst().get().getPlanetId());
            playerPlanetList=playerPlanetList.stream().filter(x-> !playerPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            writePlayerPlanets(playerPlanetList);
            playerPlanetList.add(playerPlanet);
            playerPlanetList=sortPlayerPlanetList(playerPlanetList);
            writePlayerPlanets(playerPlanetList);
        } catch (NoSuchElementException e){
            log.info("PlayerPlanet does not exist");
        }
    }
    /**ENEMYPLANET*/
    public List<EnemyPlanet> getEnemyPlanetList() throws IOException {
        this.initReader("EnemyPlanet");
        CsvToBean<EnemyPlanet> csvToBean=new CsvToBeanBuilder<EnemyPlanet>(this.reader).withType(EnemyPlanet.class).build();
        List<EnemyPlanet> enemyPlanetList=csvToBean.parse();
        enemyPlanetList=sortEnemyPlanetList(enemyPlanetList);
        return enemyPlanetList;
    }
    public void createEnemyPlanet(EnemyPlanet enemyPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        enemyPlanetList.add(enemyPlanet);
        enemyPlanetList=sortEnemyPlanetList(enemyPlanetList);
        this.writeEnemyPlanets(enemyPlanetList);
    }
    public EnemyPlanet getEnemyPlanetById(Long id) throws IOException {
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        EnemyPlanet enemyPlanet=enemyPlanetList.stream().filter(x-> id.equals(x.getPlanetId())).findAny().orElse(null);
        if(enemyPlanet==null){
            log.info("ERROR:EnemyPlanet does not exist");
            return enemyPlanet;
        } else {
            return enemyPlanet;
        }
    }
    public void deleteEnemyPlanetById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        enemyPlanetList=enemyPlanetList.stream().filter(x-> !id.equals(x.getPlanetId())).collect(Collectors.toList());
        this.writeEnemyPlanets(enemyPlanetList);
    }
    public void clearEnemyPlanets() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        enemyPlanetList=enemyPlanetList.stream().filter(x->null==x.getPlanetId()).collect(Collectors.toList());
        this.writeEnemyPlanets(enemyPlanetList);
    }
    public void updateEnemyPlanetById(EnemyPlanet enemyPlanet) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<EnemyPlanet> enemyPlanetList=getEnemyPlanetList();
        try{
            enemyPlanet.getPlanetId().equals(enemyPlanetList.stream().filter(x -> enemyPlanet.getPlanetId().equals(x.getPlanetId())).findFirst().get().getPlanetId());
            enemyPlanetList=enemyPlanetList.stream().filter(x-> !enemyPlanet.getPlanetId().equals(x.getPlanetId())).collect(Collectors.toList());
            writeEnemyPlanets(enemyPlanetList);
            enemyPlanetList.add(enemyPlanet);
            enemyPlanetList=sortEnemyPlanetList(enemyPlanetList);
            writeEnemyPlanets(enemyPlanetList);
        } catch (NoSuchElementException e){
            log.info("EnemyPlanet does not exist");
        }
    }
}

