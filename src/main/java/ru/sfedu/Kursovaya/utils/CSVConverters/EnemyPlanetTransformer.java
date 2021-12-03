package ru.sfedu.Kursovaya.utils.CSVConverters;

import com.opencsv.bean.AbstractBeanField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.EnemyPlanet;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnemyPlanetTransformer extends AbstractBeanField {
    private String fieldsDelimiter= Constants.ENEMY_PLANET_FIELDS_DELIMITER;
    private String elemDelimiter=Constants.ENEMY_PLANET_ELEMENTS_DELIMITER;
    private static final Logger log = LogManager.getLogger(EnemyPlanetTransformer.class);
    @Override
    public Object convert(String value){
        List<EnemyPlanet> enemyPlanetList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        try {
        stringList.stream().forEach(x-> {
            EnemyPlanet enemyPlanet=new EnemyPlanet();
            String[] data =x.split(fieldsDelimiter);
            enemyPlanet.setPlanetId(Long.parseLong(data[0]));
            enemyPlanet.setPlanetName(data[1]);
            enemyPlanet.setPlanetType(data[2]);
            enemyPlanet.setEnemyHealthPoints(Integer.parseInt(data[3]));
            enemyPlanet.setEnemyAttackPoints(Integer.parseInt(data[4]));
            enemyPlanetList.add(enemyPlanet);
        });
        } catch (NumberFormatException e){
            log.error(Constants.ENEMY_PLANET+Constants.DO_NOT_EXIST);
        } finally {
            return enemyPlanetList;
        }
    }

    @Override
    public String convertToWrite(Object value){
        List<EnemyPlanet> enemyPlanetList=(List<EnemyPlanet>) value;
        if (enemyPlanetList==null){
            return elemDelimiter;
        } else {
        List<String> stringList= enemyPlanetList.stream()
                .map(x-> String.format("%d"
                                +fieldsDelimiter
                                + "%s"
                                +fieldsDelimiter
                                + "%s"
                                +fieldsDelimiter
                                + "%d"
                                +fieldsDelimiter
                                + "%d",
                        x.getPlanetId(),
                        x.getPlanetName(),
                        x.getPlanetType(),
                        x.getEnemyHealthPoints(),
                        x.getEnemyAttackPoints()))
                .collect(Collectors.toList()
                );
        return String.join(elemDelimiter,stringList);
        }
    }
}
