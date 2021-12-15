package ru.sfedu.Kursovaya.utils.CSVConverters;

import com.opencsv.bean.AbstractBeanField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Planet;
import ru.sfedu.Kursovaya.Beans.PlayerPlanet;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlanetTransformer extends AbstractBeanField {
    private final String fieldsDelimiter= Constants.PLAYER_PLANET_FIELDS_DELIMITER;
    private final String elemDelimiter=Constants.PLAYER_PLANET_ELEMENTS_DELIMITER;
    private static final Logger log = LogManager.getLogger(ru.sfedu.Kursovaya.utils.CSVConverters.PlayerPlanetTransformer.class);
    @Override
    public Object convert(String value){
        List<Planet> planetList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        try {
            stringList.stream().forEach(x-> {
                Planet planet=new Planet();
                String[] data =x.split(fieldsDelimiter);
                planet.setPlanetId(Long.parseLong(data[0]));
                planet.setPlanetName(data[1]);
                planet.setPlanetType(data[2]);
                planetList.add(planet);
            });
        } catch (NumberFormatException e){
            log.error(Constants.PLAYER_PLANET+Constants.DO_NOT_EXIST);
        } finally {
            return planetList;
        }
    }
    @Override
    public String convertToWrite(Object value){
        List<Planet> planetList=(List<Planet>) value;
        if (planetList==null){
            return elemDelimiter;
        }else {
            List<String> stringList= planetList.stream()
                    .map(x-> String.format("%d"
                                    +fieldsDelimiter
                                    + "%s"
                                    +fieldsDelimiter
                                    + "%s",
                            x.getPlanetId(),
                            x.getPlanetName(),
                            x.getPlanetType()))
                    .collect(Collectors.toList()
                    );
            return String.join(elemDelimiter,stringList);
        }
    }
}

