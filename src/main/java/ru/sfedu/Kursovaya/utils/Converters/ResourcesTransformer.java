package ru.sfedu.Kursovaya.utils.Converters;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.Kursovaya.Beans.*;

import java.util.List;

public class ResourcesTransformer extends AbstractBeanField {
    private final String fieldsDelimiter = "&";
    private final BuildingTransformer buildingTransformer=new BuildingTransformer();
    private final ArmyTransformer armyTransformer=new ArmyTransformer();
    @Override
    public Object convert(String value){
        Resources resources = new Resources();
        String[] parsedData = value.split(fieldsDelimiter);
        resources.setResourcesId(Long.parseLong(parsedData[0]));
        resources.setFood(Integer.parseInt(parsedData[1]));
        resources.setMetal(Integer.parseInt(parsedData[2]));
        resources.setGold(Integer.parseInt(parsedData[3]));
        resources.setArmy((Army) armyTransformer.convert(parsedData[4]));
        resources.setBuildingList((List<Building>) buildingTransformer.convert(parsedData[5]));
        resources.setOperation(Integer.parseInt(parsedData[6]));
        return resources;
    }

    @Override
    public String convertToWrite(Object value){
        Resources resources = (Resources) value;
        return String.format("%d"
                        + fieldsDelimiter
                        + "%d"
                        + fieldsDelimiter
                        + "%d"
                        + fieldsDelimiter
                        + "%d"
                        + fieldsDelimiter
                        + "%s"
                        + fieldsDelimiter
                        + "%s"
                        + fieldsDelimiter
                        + "%d",
                resources.getResourcesId(),
                resources.getFood(),
                resources.getMetal(),
                resources.getGold(),
                armyTransformer.convertToWrite(resources.getArmy()),
                buildingTransformer.convertToWrite(resources.getBuildingList()),
                resources.getOperation());
    }
}
