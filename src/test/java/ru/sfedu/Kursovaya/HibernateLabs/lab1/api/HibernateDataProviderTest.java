package ru.sfedu.Kursovaya.HibernateLabs.lab1.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class HibernateDataProviderTest {
        private static final Logger log = LogManager.getLogger(HibernateDataProviderTest.class.getName());
        @Test
        public void getSchema() {
            log.debug("getSchema");
            HibernateDataProvider instance = new HibernateDataProvider();
            List result = instance.getSchema();
            assertNotNull(result);
        }

        @Test
        public void getTables() {
            log.debug("getTables");
            HibernateDataProvider instance = new HibernateDataProvider();
            List result = instance.getTables();
            assertNotNull(result);
        }

        @Test
        public void getSchemaName() {
            log.debug("getSchemaName");
            HibernateDataProvider instance = new HibernateDataProvider();
            List result = instance.getSchemaName();
            assertNotNull(result);
        }

        @Test
        public void getDetails() {
            log.debug("getDetails");
            HibernateDataProvider instance = new HibernateDataProvider();
            List result = instance.getDetails();
            assertNotNull(result);
        }
    }
