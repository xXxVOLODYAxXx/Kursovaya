package ru.sfedu.Kursovaya.utils.Transformers;

import ru.sfedu.Kursovaya.Beans.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.opencsv.bean.AbstractBeanField;

public class UnitTransformer extends AbstractBeanField {
    private String fieldsDelimiter="@";
    private String elemDelimiter="#";

    @Override
    public Object convert(String value){
        List<Unit> unitList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        stringList.stream().forEach(x-> {
            Unit unit = new Unit();
            String[] data =x.split(fieldsDelimiter);
            unit.setUnitId(Long.parseLong(data[0]));
            unit.setUnitType(data[1]);
            unit.setUnitAttackPoints(Integer.parseInt(data[2]));
            unit.setUnitHealthPoints(Integer.parseInt(data[3]));
            unit.setGoldRequired(Integer.parseInt(data[4]));
            unit.setMetalRequired(Integer.parseInt(data[5]));
            unit.setFoodRequired(Integer.parseInt(data[6]));
            unitList.add(unit);
        });
        return unitList;
    }

    @Override
    public String convertToWrite(Object unit){
        List<Unit> unitList=(List<Unit>) unit;
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
