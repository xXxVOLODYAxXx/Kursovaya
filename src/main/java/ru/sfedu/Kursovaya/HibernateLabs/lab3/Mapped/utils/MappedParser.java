package ru.sfedu.Kursovaya.HibernateLabs.lab3.Mapped.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.Mapped.models.MappedHibernatePlayerPlanet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class MappedParser {
    private static final Type PLANET_TYPE = new TypeToken<List<MappedHibernatePlayerPlanet>>() {
    }.getType();

    public static MappedHibernatePlayerPlanet getHibernatePlayerPlanet(String path) throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader jr = new JsonReader(new FileReader(path));
        List<MappedHibernatePlayerPlanet> playerPlanetList = gson.fromJson(jr, PLANET_TYPE);
        return playerPlanetList.get(0);
    }
}