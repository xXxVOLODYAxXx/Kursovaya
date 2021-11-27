package ru.sfedu.Kursovaya.utils.Converters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.Kursovaya.utils.Constants;

public class UnitTransformer extends AbstractBeanField {
    private final String fieldsDelimiter= Constants.UNIT_FIELDS_DELIMITER;
    private final String elemDelimiter=Constants.UNIT_ELEMENTS_DELIMITER;
    private static final Logger log = LogManager.getLogger(UnitTransformer.class);
    @Override
    public Object convert(String value){
        List<Unit> unitList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        try {
            stringList.forEach(x-> {
                Unit unit = new Unit();
                String[] parsedData =x.split(fieldsDelimiter);
                unit.setUnitId(Long.parseLong(parsedData[0]));
                unit.setUnitType(parsedData[1]);
                unit.setUnitAttackPoints(Integer.parseInt(parsedData[2]));
                unit.setUnitHealthPoints(Integer.parseInt(parsedData[3]));
                unit.setGoldRequired(Integer.parseInt(parsedData[4]));
                unit.setMetalRequired(Integer.parseInt(parsedData[5]));
                unit.setFoodRequired(Integer.parseInt(parsedData[6]));
                unitList.add(unit);
            });
        } catch (NumberFormatException e){
            log.error("UnitList is empty");

        } finally {
            return unitList;
        }


    }
    @Override
    public String convertToWrite(Object unit){
        List<Unit> unitList=(List<Unit>) unit;
        if(unitList == null){
            return String.join(elemDelimiter);
        } else {
        List<String> stringList= unitList.stream()
                .map(x-> String.format("%d"
                        +fieldsDelimiter
                        + "%s"
                        +fieldsDelimiter
                        + "%d"
                        +fieldsDelimiter
                        + "%d"
                        +fieldsDelimiter
                        + "%d"
                        +fieldsDelimiter
                        + "%d"
                        +fieldsDelimiter
                        + "%d",
                        x.getUnitId(),
                        x.getUnitType(),
                        x.getUnitAttackPoints(),
                        x.getUnitHealthPoints(),
                        x.getGoldRequired(),
                        x.getGoldRequired(),
                        x.getFoodRequired()))
                .collect(Collectors.toList()
                );
        return String.join(elemDelimiter,stringList);
        }
    }
}
