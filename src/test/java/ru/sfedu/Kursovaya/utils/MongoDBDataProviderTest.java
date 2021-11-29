package ru.sfedu.Kursovaya.utils;

import org.junit.jupiter.api.Test;
import ru.sfedu.Kursovaya.utils.DataProviders.MongoDBDataProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;

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
    void insertRand() throws IOException {
        m.insertRecord(initHistoryContent());
    }
    @Test
    void readAll() throws IOException {
        log.info(m.readAll());
    }
}