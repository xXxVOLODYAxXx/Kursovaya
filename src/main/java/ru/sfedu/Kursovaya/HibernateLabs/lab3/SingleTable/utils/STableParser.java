package ru.sfedu.Kursovaya.HibernateLabs.lab3.SingleTable.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.SingleTable.models.SingleHibernatePlayerPlanet;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class STableParser {
    private static final Type PLAYER_TYPE = new TypeToken<List<SingleHibernatePlayerPlanet>>() {
    }.getType();

    public static SingleHibernatePlayerPlanet getHibernatePlayerPlanet(String path) throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader jr = new JsonReader(new FileReader(path));
        List<SingleHibernatePlayerPlanet> playerPlanetList = gson.fromJson(jr, PLAYER_TYPE);
        return playerPlanetList.get(0);
    }
}