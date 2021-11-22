package ru.sfedu.Kursovaya.utils.Converters;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.Kursovaya.Beans.Building;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingTransformer extends AbstractBeanField {
    private String fieldsDelimiter="@";
    private String elemDelimiter="#";

    @Override
    public Object convert(String value){
        List<Building> buildingList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        stringList.stream().forEach(x-> {
            Building building=new Building();
            String[] data =x.split(fieldsDelimiter);
            building.setBuildingId(Long.parseLong(data[0]));
            building.setBuildingType(data[1]);
            building.setFoodBuff(Integer.parseInt(data[2]));
            building.setMetalBuff(Integer.parseInt(data[3]));
            building.setGoldBuff(Integer.parseInt(data[4]));
            building.setFoodRequired(Integer.parseInt(data[5]));
            building.setMetalRequired(Integer.parseInt(data[6]));
            building.setGoldRequired(Integer.parseInt(data[7]));
            buildingList.add(building);
        });
        return buildingList;
    }

    @Override
    public String convertToWrite(Object building){
        List<Building> buildingList=(List<Building>) building;
        List<String> stringList= buildingList.stream()
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
                                + "%d"
                                +fieldsDelimiter
                                + "%d",
                        x.getBuildingId(),
                        x.getBuildingType(),
                        x.getFoodBuff(),
                        x.getMetalBuff(),
                        x.getGoldBuff(),
                        x.getGoldRequired(),
                        x.getGoldRequired(),
                        x.getFoodRequired()))
                .collect(Collectors.toList()
                );
        return String.join(elemDelimiter,stringList);
    }
}
