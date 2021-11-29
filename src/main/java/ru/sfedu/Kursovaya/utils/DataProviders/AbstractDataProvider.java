package ru.sfedu.Kursovaya.utils.DataProviders;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.UtilBeans.HistoryContent;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public abstract class AbstractDataProvider  {
    private final MongoDBDataProvider mdvdp=new MongoDBDataProvider();
    private static final Logger log = LogManager.getLogger(AbstractDataProvider.class);

    public AbstractDataProvider() throws IOException {}

    public abstract void createUnit(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Unit getUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract void deleteUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract void updateUnitById(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;

    public void saveToLog(HistoryContent historyContent,String string) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
    mdvdp.insertRecord(historyContent,string);
    }

    public abstract Game createUniverse(Game game,Resources resources,Army army) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Boolean deleteUniverse(Long id) throws CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, IOException;
    public abstract EnemyPlanet getEnemyPower(Long planetId, Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    public abstract ArmyInfo getArmyPower(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    public abstract Boolean attackPlanet(Long enemyPlanetId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Game hireUnit(Long unitId, Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract List<Building> getBuildingsInfo(Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    public abstract Game addBuilding(Long buildingId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Game removeBuilding(Long buildingId,Long gameId) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, JAXBException;
    public abstract Game manageResources(Long gameId,int operation,Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    public abstract Game manageResources(Long gameId,int operation) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
}
