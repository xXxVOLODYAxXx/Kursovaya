package ru.sfedu.Kursovaya.utils.Converters;

import com.opencsv.bean.AbstractBeanField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Army;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.Beans.Unit;
import ru.sfedu.Kursovaya.utils.Constants;

import java.util.List;

public class ArmyTransformer extends AbstractBeanField {
    private final String fieldsDelimiter = Constants.ARMY_FIELDS_DELIMITER;
    private final String elementsDelimiter=Constants.ARMY_ELEMENTS_DELIMITER;
    private final UnitTransformer unitTransformer=new UnitTransformer();
    private final ArmyInfoTransformer armyInfoTransformer=new ArmyInfoTransformer();
    private static final Logger log = LogManager.getLogger(ArmyTransformer.class);
    @Override
    public Object convert(String value){
        Army army = new Army();
        try {
            String[] parsedData = value.split(fieldsDelimiter);
            army.setArmyId(Long.parseLong(parsedData[0]));
            army.setUnits((List<Unit>) unitTransformer.convert(parsedData[1]));
            army.setArmyInfo((ArmyInfo) armyInfoTransformer.convert(parsedData[2]));
        } catch (NullPointerException e){
            log.error(Constants.ARMY+Constants.DO_NOT_EXIST);
        }finally {
            return army;
        }


    }

    @Override
    public String convertToWrite(Object army){
        Army armyList = (Army) army;
        if (armyList==null){
            return elementsDelimiter;
        }else {
            return String.format("%d"
                        + fieldsDelimiter
                        + "%s"
                        + fieldsDelimiter
                        + "%s",
                armyList.getArmyId(),
                unitTransformer.convertToWrite(armyList.getUnits()),
                armyInfoTransformer.convertToWrite(armyList.getArmyInfo()));
        }
    }
}
