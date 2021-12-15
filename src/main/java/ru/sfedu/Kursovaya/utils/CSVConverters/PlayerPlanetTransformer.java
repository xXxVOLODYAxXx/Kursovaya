package ru.sfedu.Kursovaya.utils.CSVConverters;

import com.opencsv.bean.AbstractBeanField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.PlayerPlanet;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerPlanetTransformer extends AbstractBeanField {
    private final String fieldsDelimiter= Constants.PLAYER_PLANET_FIELDS_DELIMITER;
    private final String elemDelimiter=Constants.PLAYER_PLANET_ELEMENTS_DELIMITER;
    private static final Logger log = LogManager.getLogger(PlayerPlanetTransformer.class);
    @Override
    public Object convert(String value){
        List<PlayerPlanet> playerPlanetList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        try {
            stringList.stream().forEach(x-> {
                PlayerPlanet playerPlanet=new PlayerPlanet();
                String[] data =x.split(fieldsDelimiter);
                playerPlanet.setPlanetId(Long.parseLong(data[0]));
                playerPlanet.setPlanetName(data[1]);
                playerPlanet.setPlanetType(data[2]);
                playerPlanet.setBuildingLimit(Integer.parseInt(data[3]));
                playerPlanetList.add(playerPlanet);
            });
        } catch (NumberFormatException e){
            //log.error(Constants.PLAYER_PLANET+Constants.DO_NOT_EXIST);
        } finally {
            return playerPlanetList;
        }
    }
    @Override
    public String convertToWrite(Object value){
        List<PlayerPlanet> playerPlanetList=(List<PlayerPlanet>) value;
        if (playerPlanetList==null){
            return elemDelimiter;
        }else {
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
}
