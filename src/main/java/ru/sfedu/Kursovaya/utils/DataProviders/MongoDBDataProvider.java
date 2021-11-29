package ru.sfedu.Kursovaya.utils.DataProviders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.joda.time.DateTime;
import ru.sfedu.Kursovaya.UtilBeans.HistoryContent;
import ru.sfedu.Kursovaya.Enums.Status;
import ru.sfedu.Kursovaya.utils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.Constants;

import java.io.IOException;
import java.util.*;

public class MongoDBDataProvider {
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class);
    private MongoClient client;
    private MongoDatabase database;

    private  <T> Map<String, Object> convertEntityToMap(T entity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(entity, Map.class);
    }
    public HistoryContent initHistoryContentTrue(Object object,String objectName,String className,String methodName){
        DateTime dateTime = new DateTime();
        Map<String,Object> map = new HashMap<>();
        map.put(objectName,convertEntityToMap(object));
        HistoryContent historyContent = new HistoryContent();
        historyContent.setId(new Random().nextLong());
        historyContent.setClassName(className);
        historyContent.setCreatedDate(dateTime.toString());
        historyContent.setActor(Constants.DEFAULT_ACTOR);
        historyContent.setMethodName(methodName);
        historyContent.setObject(map);
        historyContent.setStatus(Status.SUCCESS.toString());
        return historyContent;
    }
    public HistoryContent initHistoryContentFalse(String string,String className,String methodName){
        DateTime dateTime = new DateTime();
        Map<String,Object> map = new HashMap<>();
        map.put(Constants.NULL,Constants.NULL);
        HistoryContent historyContent = new HistoryContent();
        historyContent.setId(new Random().nextLong());
        historyContent.setClassName(className);
        historyContent.setCreatedDate(dateTime.toString());
        historyContent.setActor(Constants.SYSTEM);
        historyContent.setMethodName(methodName);
        historyContent.setObject(map);
        historyContent.setStatus(Status.FAULT.toString());
        return historyContent;
    }
    public List<Document> readAll()  {
        initConnection();
        MongoCollection<Document> mongoCollection = database.getCollection(Constants.MONGODBTESTSERVER);
        FindIterable<Document> documents = mongoCollection.find();
        return documents.into(new ArrayList<Document>());
    }
    public void initConnection() {
        try {
            this.client = new MongoClient(new MongoClientURI(ConfigurationUtil.getConfigurationEntry(Constants.MONGODB)));
            this.database = client.getDatabase(Constants.MONGODBTESTSERVER);
        } catch (IOException e){
            log.error(e);
        }

    }
    public void closeConnection(){
        this.client.close();
    }
    public void insertRecord(HistoryContent historyContent) {
        initConnection();
        MongoCollection<Document> mongoCollection = database.getCollection(Constants.MONGODBTESTSERVER);
        mongoCollection.insertOne(new Document(this.convertEntityToMap(historyContent)));
        closeConnection();
    }
}
