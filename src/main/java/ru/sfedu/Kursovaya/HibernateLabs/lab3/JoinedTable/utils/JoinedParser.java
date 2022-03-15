package ru.sfedu.Kursovaya.HibernateLabs.lab3.JoinedTable.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.JoinedTable.models.HibernatePlayerPlanet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

class JoinedParser {
    private static final Type PLANET_TYPE = new TypeToken<List<HibernatePlayerPlanet>>() {
    }.getType();

    public static HibernatePlayerPlanet getPlayerPlanet(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jr = new JsonReader(new FileReader(path));
        List<HibernatePlayerPlanet> playerPlanetList = gson.fromJson(jr, PLANET_TYPE);
        return playerPlanetList.get(0);
    }
}