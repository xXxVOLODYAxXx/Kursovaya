package ru.sfedu.Kursovaya.utils.Converters;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.Kursovaya.Beans.EnemyPlanet;
import ru.sfedu.Kursovaya.Beans.PlayerPlanet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerPlanetTransformer extends AbstractBeanField {
    private String fieldsDelimiter="}";
    private String elemDelimiter="!";

    @Override
    public Object convert(String value){
        List<PlayerPlanet> playerPlanetList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        stringList.stream().forEach(x-> {
            PlayerPlanet playerPlanet=new PlayerPlanet();
            String[] data =x.split(fieldsDelimiter);
            playerPlanet.setPlanetId(Long.parseLong(data[0]));
            playerPlanet.setPlanetName(data[1]);
            playerPlanet.setPlanetType(data[2]);
            playerPlanet.setBuildingLimit(Integer.parseInt(data[3]));
            playerPlanetList.add(playerPlanet);
        });
        return playerPlanetList;
    }

    @Override
    public String convertToWrite(Object value){
        List<PlayerPlanet> playerPlanetList=(List<PlayerPlanet>) value;
        List<String> stringList= playerPlanetList.stream()
                .map(x-> String.format("%d"
                                +fieldsDelimiter
                                + "%s"
                                +fieldsDelimiter
                                + "%s"
                                +fieldsDelimiter
                                + "%d",
                        x.getPlanetId(),
                        x.getPlanetName(),
                        x.getPlanetType(),
                        x.getBuildingLimit()))
                .collect(Collectors.toList()
                );
        return String.join(elemDelimiter,stringList);
    }
}
