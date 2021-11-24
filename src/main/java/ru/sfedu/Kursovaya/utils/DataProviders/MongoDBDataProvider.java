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
import ru.sfedu.Kursovaya.Beans.HistoryContent;
import ru.sfedu.Kursovaya.Enums.Status;
import ru.sfedu.Kursovaya.utils.DataProviders.CSVDataProvider;

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
        log.info(map);
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
    public HistoryContent initHistoryContentFalse(String string,String className,String methodName){
        DateTime dateTime = new DateTime();
        Map<String,Object> map = new HashMap<>();
        map.put("null","null");
        HistoryContent historyContent = new HistoryContent();
        historyContent.setId(new Random().nextLong());
        historyContent.setClassName(className);
        historyContent.setCreatedDate(dateTime.toString());
        historyContent.setActor("SYSTEM");
        historyContent.setMethodName(methodName);
        historyContent.setObject(map);
        historyContent.setStatus(Status.FAULT.toString());
        log.info(historyContent.getObject());
        return historyContent;
    }
    public List<Document> readAll() {
        initConnection();
        MongoCollection<Document> mongoCollection = database.getCollection("test");
        FindIterable<Document> documents = mongoCollection.find();
        return documents.into(new ArrayList<Document>());
    }
    public void initConnection(){
        this.client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        this.database = client.getDatabase("test");
    }
    public void closeConnection(){
        this.client.close();
    }
    public void insertRecord(HistoryContent historyContent){
        initConnection();
        MongoCollection<Document> mongoCollection = database.getCollection("test");
        mongoCollection.insertOne(new Document(this.convertEntityToMap(historyContent)));
        closeConnection();
    }
}
