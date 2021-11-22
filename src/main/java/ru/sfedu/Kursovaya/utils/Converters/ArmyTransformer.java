package ru.sfedu.Kursovaya.utils.Converters;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.Kursovaya.Beans.Army;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.Beans.Unit;

import java.util.List;

public class ArmyTransformer extends AbstractBeanField {
    private final String fieldsDelimiter = "'";
    private final UnitTransformer unitTransformer=new UnitTransformer();
    private final ArmyInfoTransformer armyInfoTransformer=new ArmyInfoTransformer();
    @Override
    public Object convert(String value){
        Army army = new Army();
        String[] parsedData = value.split(fieldsDelimiter);
        army.setArmyId(Long.parseLong(parsedData[0]));
        army.setUnits((List<Unit>) unitTransformer.convert(parsedData[1]));
        army.setArmyInfo((ArmyInfo) armyInfoTransformer.convert(parsedData[2]));
        return army;
    }

    @Override
    public String convertToWrite(Object army){
        Army armyList = (Army) army;
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
