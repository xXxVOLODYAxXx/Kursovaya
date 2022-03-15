package ru.sfedu.Kursovaya.HibernateLabs.lab1.api;
import java.util.List;
public interface IDataProvider {
    List getSchema();

    List getTables();

    List getDetails();

    List getSchemaName();
}

