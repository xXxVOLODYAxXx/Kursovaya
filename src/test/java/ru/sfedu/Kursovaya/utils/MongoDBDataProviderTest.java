package ru.sfedu.Kursovaya.utils;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MongoDBDataProviderTest extends BaseTest{
    MongoDBDataProvider m = new MongoDBDataProvider();

    public MongoDBDataProviderTest() throws JAXBException, IOException {
    }

    @Test
    void initConnection() {
    }

    @Test
    void closeConnection() {
    }

    @Test
    void insertRand() {
        m.insertRand(initHistoryContent());
    }
    @Test
    void readAll(){
        log.info(m.readAll());
    }
}