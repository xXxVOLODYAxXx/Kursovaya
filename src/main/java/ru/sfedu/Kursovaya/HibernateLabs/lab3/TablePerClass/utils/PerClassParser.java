package ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.models.PerClassHibernatePlayerPlanet;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class PerClassParser {
    private static final Type PLAYER_TYPE = new TypeToken<List<PerClassHibernatePlayerPlanet>>() {
    }.getType();

    public static PerClassHibernatePlayerPlanet getHibernatePlayerPlanet(String path) throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader jr = new JsonReader(new FileReader(path));
        List<PerClassHibernatePlayerPlanet> playerPlanetList = gson.fromJson(jr, PLAYER_TYPE);
        return playerPlanetList.get(0);
    }
}