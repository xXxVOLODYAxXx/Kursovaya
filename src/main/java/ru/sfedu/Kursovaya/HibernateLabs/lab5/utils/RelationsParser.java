package ru.sfedu.Kursovaya.HibernateLabs.lab5.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.sfedu.Kursovaya.Beans.Game;
import ru.sfedu.Kursovaya.HibernateLabs.lab5.models.HGame;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class RelationsParser {

    private static final Type GAME_TYPE = new TypeToken<List<HGame>>() {}.getType();

    public static HGame getHGame(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jr = new JsonReader(new FileReader(path));
        List<HGame> channelList = gson.fromJson(jr, GAME_TYPE);
        return channelList.get(0);
    }
}
