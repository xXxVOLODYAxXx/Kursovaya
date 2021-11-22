package ru.sfedu.Kursovaya.utils.DataProviders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.HistoryContent;

import java.io.IOException;

public class AbstractDataProvider {
    private MongoDBDataProvider mdvdp=new MongoDBDataProvider();
    private CSVDataProvider csvdp=new CSVDataProvider();
    private static final Logger log = LogManager.getLogger(AbstractDataProvider.class);
    public AbstractDataProvider() throws IOException {
    }

    public void createUnit() throws Exception {
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        Object a = new Object(){}
                .getClass();
        Object b = new Object(){}
                .getClass()
                .getEnclosingMethod();
        HistoryContent historyContent=new HistoryContent();
        log.info(methodName);
        log.info(a);
        log.info(b);
    }

    public void saveToLog(HistoryContent historyContent) {
        mdvdp.insertRecord(historyContent);

    }
}
