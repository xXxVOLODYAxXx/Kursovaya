package ru.sfedu.Kursovaya.utils.DataProviders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import ru.sfedu.Kursovaya.Beans.HistoryContent;
import ru.sfedu.Kursovaya.Beans.Unit;
import ru.sfedu.Kursovaya.Enums.Status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AbstractDataProvider {

    private final MongoDBDataProvider mdvdp=new MongoDBDataProvider();
    private CSVDataProvider csvdp=new CSVDataProvider();
    private static final Logger log = LogManager.getLogger(AbstractDataProvider.class);
    public AbstractDataProvider() throws IOException {
    }
    private String getClassName(){
        return new Object() {}
                .getClass()
                .getEnclosingClass()
                .getName();
    }
    private String getMethodName(){
        return new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName().toString();
    }



    public void createUnitCSV() throws Exception {
        csvdp.createUnit(csvdp.getUnitById(1L));
        log.info(initHistoryContent(csvdp.getUnitById(1L),getClassName(),getMethodName()));
    }

    public HistoryContent initHistoryContent(Object object,String className,String methodName){
        DateTime dateTime = new DateTime();
        Map<String,Object> map = new HashMap<>();
        map.put(object.getClass().getName(),object);
        HistoryContent historyContent = new HistoryContent();
        historyContent.setId(new Random().nextLong());
        historyContent.setClassName(className);
        historyContent.setCreatedDate(dateTime.toString());
        historyContent.setActor("SYSTEM");
        historyContent.setMethodName(methodName);
        historyContent.setObject(map);
        historyContent.setStatus(Status.SUCCESS.toString());
        log.info(historyContent.getObject());
        return historyContent;
    }

    public void saveToLog(HistoryContent historyContent) throws IOException {
        mdvdp.insertRecord(historyContent);
    }
}
