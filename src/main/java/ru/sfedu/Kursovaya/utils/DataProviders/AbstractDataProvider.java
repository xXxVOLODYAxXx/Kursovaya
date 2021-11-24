package ru.sfedu.Kursovaya.utils.DataProviders;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.HistoryContent;
import ru.sfedu.Kursovaya.Beans.Unit;

import java.io.IOException;

public abstract class AbstractDataProvider  {
    private final MongoDBDataProvider mdvdp=new MongoDBDataProvider();
    private static final Logger log = LogManager.getLogger(AbstractDataProvider.class);


    public AbstractDataProvider() throws IOException {}

    public abstract void createUnit(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    public abstract Unit getUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    public abstract void deleteUnitById(Long id) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
    public abstract void updateUnitById(Unit unit) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;

    public void saveToLog(HistoryContent historyContent) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
    mdvdp.insertRecord(historyContent);
    }
}
