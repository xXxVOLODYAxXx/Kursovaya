package ru.sfedu.Kursovaya.utils.CSVConverters;

import com.opencsv.bean.AbstractBeanField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

import java.util.List;

public class ResourcesTransformer extends AbstractBeanField {
    private final String fieldsDelimiter = Constants.RESOURCES_FIELDS_DELIMITER;
    private final String elementsDelimiter=Constants.RESOURCES_ELEMENTS_DELIMITER;
    private final BuildingTransformer buildingTransformer=new BuildingTransformer();
    private final ArmyTransformer armyTransformer=new ArmyTransformer();
    private static final Logger log = LogManager.getLogger(ResourcesTransformer.class);
    @Override
    public Object convert(String value){
        Resources resources = new Resources();
        try {
            String[] parsedData = value.split(fieldsDelimiter);
            resources.setResourcesId(Long.parseLong(parsedData[0]));
            resources.setFood(Integer.parseInt(parsedData[1]));
            resources.setMetal(Integer.parseInt(parsedData[2]));
            resources.setGold(Integer.parseInt(parsedData[3]));
            resources.setArmy((Army) armyTransformer.convert(parsedData[4]));
            resources.setBuildingList((List<Building>) buildingTransformer.convert(parsedData[5]));
            resources.setOperation(Integer.parseInt(parsedData[6]));
        }catch (NumberFormatException e){
            log.error(Constants.RESOURCES+Constants.DO_NOT_EXIST);
        }finally {
            return resources;
        }

    }
    @Override
    public String convertToWrite(Object value){
        Resources resources = (Resources) value;
        if (resources==null){
            return elementsDelimiter;
        }else {
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
}
