package ru.sfedu.Kursovaya.utils;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AbstractDataProviderTest extends BaseTest{

    public AbstractDataProviderTest() throws JAXBException, IOException {
    }

    @Test
    void createUnit() throws Exception {
        abstractDataProvider.createUnit();
    }

    @Test
    void saveToLog() {
    }
}