package ru.sfedu.Kursovaya.utils.DataProviders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Unit;
import ru.sfedu.Kursovaya.UtilBeans.XMLList;
import ru.sfedu.Kursovaya.utils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.Constants;

import javax.xml.bind.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class XMLDataProvider {
    public XMLDataProvider() throws IOException, JAXBException {}
    private final String PATH= ConfigurationUtil.getConfigurationEntry(Constants.PATH_TO_XML)+"Units"+ConfigurationUtil.getConfigurationEntry(Constants.XML_FILE_EXTENSION);
    private final File file=new File(PATH);
    private FileReader fileReader=null;
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class);

    /*СДЕЛАТЬ ИНТЕРФЕЙС ЭТОЙ КРАСОТЫ*/
    /*ЧИТАЕМ и ПИШЕМ*/
    /**UNIT*/
    private final JAXBContext unitjaxbContext = JAXBContext.newInstance(XMLList.class);
    private final Marshaller unitMarshaller = unitjaxbContext.createMarshaller();
    private final Unmarshaller unitUnmarshaller = unitjaxbContext.createUnmarshaller();
    private void initReader () throws FileNotFoundException {
        this.fileReader=new FileReader(PATH);
    }
    private void closeReader () throws IOException {
        fileReader.close();
    }
    public void unitsToXML(List<Unit> ulist) throws JAXBException, IOException {
        initReader();
        XMLList unitList=new XMLList();
        unitList.setUnits(ulist);
        unitMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        unitMarshaller.marshal(unitList,file);
        closeReader();
    }
    private List<Unit> unitsFromXML() throws JAXBException {
        XMLList unitList=(XMLList) unitUnmarshaller.unmarshal(file);
        List<Unit> ulist=unitList.getUnits();
        return ulist;
    }
    private List<Unit> sortUnitList(List<Unit> unitList) throws IOException {
        unitList=unitList.stream().sorted((o1, o2)->o1.getUnitId().compareTo(o2.getUnitId())).collect(Collectors.toList());
        return unitList;
    }

    /**BUILDING
    private final JAXBContext buildingJaxbContext = JAXBContext.newInstance(BuildingList.class);
    private final Marshaller buildingMarshaller = buildingJaxbContext.createMarshaller();
    private final Unmarshaller buildingUnmarshaller = buildingJaxbContext.createUnmarshaller();
    private void buildingsToXML(List<Building> blist) throws JAXBException {
        BuildingList buildingList=new BuildingList();
        buildingList.setBuildings(blist);
        buildingMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        buildingMarshaller.marshal(buildingList,file);
    }
    private List<Building> buildingsToXML() throws JAXBException {
        BuildingList buildingList=(BuildingList) buildingUnmarshaller.unmarshal(file);
        List<Building> blist=buildingList.getBuildings();
        return blist;
    }
    private List<Building> sortBuildingList(List<Building> buildingList) throws IOException {
        buildingList=buildingList.stream().sorted((o1, o2)->o1.getBuildingId().compareTo(o2.getBuildingId())).collect(Collectors.toList());
        return buildingList;
    }
*/

    /*CRUD*/
    /**UNIT*/
    public List<Unit> getUnitList() throws JAXBException, IOException {
        List<Unit> unitList=unitsFromXML();
        unitList=sortUnitList(unitList);
        return unitList;
    }
    public void createUnit(Unit unit) throws JAXBException, IOException {
        List<Unit> unitList=unitsFromXML();
        unitList.add(unit);
        unitList=sortUnitList(unitList);
        unitsToXML(unitList);
    }
    public Unit getUnitById(Long id) throws JAXBException {
        List<Unit> unitList=unitsFromXML();
        Unit unit=unitList.stream().filter(x-> id.equals(x.getUnitId())).findAny().orElse(null);
        if(unit==null){
            log.info("ERROR:Unit does not exist");
            return unit;
        } else {
            return unit;
        }
    }
    public void deleteUnitById(Long id) throws JAXBException, IOException {
        List<Unit> unitList=unitsFromXML();
        unitList=unitList.stream().filter(x-> !id.equals(x.getUnitId())).collect(Collectors.toList());
        unitsToXML(unitList);
    }
    public void updateUnitById(Unit unit) throws JAXBException, IOException {
        List<Unit> unitList=unitsFromXML();
        try{
            unit.getUnitId().equals(unitList.stream().filter(x -> unit.getUnitId().equals(x.getUnitId())).findFirst().get().getUnitId());
            unitList=unitList.stream().filter(x-> !unit.getUnitId().equals(x.getUnitId())).collect(Collectors.toList());
            unitsToXML(unitList);
            unitList.add(unit);
            unitList=sortUnitList(unitList);
            unitsToXML(unitList);
        } catch (NoSuchElementException e){
            log.info("Unit does not exist");
        }
    }

    /**BUILDING
    public List<Building> getBuildingList() throws JAXBException, IOException {
        List<Building> buildingList=buildingsToXML();
        buildingList=sortBuildingList(buildingList);
        return buildingList;
    }
    public void createBuilding(Building building) throws JAXBException, IOException {
        List<Building> buildingList=buildingsToXML();
        buildingList.add(building);
        buildingList=sortBuildingList(buildingList);
        buildingsToXML(buildingList);
    }
    public Building getBuildingById(Long id) throws JAXBException {
        List<Building> buildingList=buildingsToXML();
        Building building=buildingList.stream().filter(x-> id.equals(x.getBuildingId())).findAny().orElse(null);
        if(building==null){
            log.info("ERROR:Unit does not exist");
            return building;
        } else {
            return building;
        }
    }
    public void deleteBuildingById(Long id) throws JAXBException {
        List<Building> buildingList=buildingsToXML();
        buildingList=buildingList.stream().filter(x-> !id.equals(x.getBuildingId())).collect(Collectors.toList());
        buildingsToXML(buildingList);
    }
    public void updateBuildingById(Building building) throws JAXBException, IOException {
        List<Building> buildingList=buildingsToXML();
        buildingList=buildingList.stream().filter(x-> !building.getBuildingId().equals(x.getBuildingId())).collect(Collectors.toList());
        buildingsToXML(buildingList);
        buildingList.add(building);
        buildingList=sortBuildingList(buildingList);
        buildingsToXML(buildingList);
    }
*/
}
