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
import ru.sfedu.Kursovaya.entityBeans.Building;
import ru.sfedu.Kursovaya.entityBeans.Unit;
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
    private List<Unit> sortUnitList(List<Unit> unitList) throws IOException {
        unitList=unitList.stream().sorted((o1, o2)->o1.getUnitId().compareTo(o2.getUnitId())).collect(Collectors.toList());
        return unitList;
    }
    private List<Building> sortBuildingList(List<Building> buildingList) throws IOException {
        buildingList=buildingList.stream().sorted((o1, o2)->o1.getBuildingId().compareTo(o2.getBuildingId())).collect(Collectors.toList());
        return buildingList;
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
        Unit unit=unitList.stream().filter(x->id== x.getUnitId()).findAny().orElse(null);
        if(unit==null){
            log.info("ERROR:Unit does not exist");
            return unit;
        } else {
            return unit;
        }
    }
    public void deleteUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Unit> unitList=getUnitList();
        unitList=unitList.stream().filter(x->id!=x.getUnitId()).collect(Collectors.toList());
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
}

