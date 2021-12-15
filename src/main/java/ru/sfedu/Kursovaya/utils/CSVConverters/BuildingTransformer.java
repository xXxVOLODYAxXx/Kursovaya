package ru.sfedu.Kursovaya.utils.CSVConverters;

import com.opencsv.bean.AbstractBeanField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Building;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingTransformer extends AbstractBeanField {
    private final String fieldsDelimiter= Constants.BUILDING_FIELDS_DELIMITER;
    private final String elemDelimiter=Constants.BUILDING_ELEMENTS_DELIMITER;
    private static final Logger log = LogManager.getLogger(BuildingTransformer.class);
    @Override
    public Object convert(String value){
        List<Building> buildingList=new ArrayList<>();
        List<String> stringList= Arrays.asList(value.split(elemDelimiter));
        try {
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
        } catch (NumberFormatException e){
            //log.error(Constants.BUILDING+Constants.DO_NOT_EXIST);
        } finally {
            return buildingList;
        }


    }

    @Override
    public String convertToWrite(Object building){
        List<Building> buildingList=(List<Building>) building;
        if(buildingList==null){
          return elemDelimiter;
        } else {
            List<String> stringList = buildingList.stream()
                    .map(x -> String.format("%d"
                                    + fieldsDelimiter
                                    + "%s"
                                    + fieldsDelimiter
                                    + "%d"
                                    + fieldsDelimiter
                                    + "%d"
                                    + fieldsDelimiter
                                    + "%d"
                                    + fieldsDelimiter
                                    + "%d"
                                    + fieldsDelimiter
                                    + "%d"
                                    + fieldsDelimiter
                                    + "%d",
                            x.getBuildingId(),
                            x.getBuildingType(),
                            x.getFoodBuff(),
                            x.getMetalBuff(),
                            x.getGoldBuff(),
                            x.getFoodRequired(),
                            x.getMetalRequired(),
                            x.getGoldRequired()))
                    .collect(Collectors.toList()
                    );
            return String.join(elemDelimiter, stringList);
        }
    }
}
