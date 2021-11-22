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
import ru.sfedu.Kursovaya.Beans.HistoryContent;
import ru.sfedu.Kursovaya.utils.DataProviders.CSVDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoDBDataProvider {
    private static final Logger log = LogManager.getLogger(CSVDataProvider.class);
    private MongoClient client;
    private MongoDatabase database;

    private  <T> Map<String, Object> convertEntityToMap(T entity) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.convertValue(entity, Map.class);
        return map;
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
