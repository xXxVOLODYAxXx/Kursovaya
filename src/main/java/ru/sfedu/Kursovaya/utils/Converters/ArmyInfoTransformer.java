package ru.sfedu.Kursovaya.utils.Converters;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;

public class ArmyInfoTransformer extends AbstractBeanField {
    private final String fieldsDelimiter = "::";

    @Override
    public Object convert(String value){
        ArmyInfo armyInfo = new ArmyInfo();
        String[] data = value.split(fieldsDelimiter);
        armyInfo.setArmyAttackPoints(Integer.parseInt(data[0]));
        armyInfo.setArmyHealthPoints(Integer.parseInt(data[1]));
        return armyInfo;
    }

    @Override
    public String convertToWrite(Object armyInfo){
        ArmyInfo armyInfoList = (ArmyInfo) armyInfo;
        return String.format("%d"
                        +fieldsDelimiter
                        + "%d",
                armyInfoList.getArmyAttackPoints(),
                armyInfoList.getArmyHealthPoints());
    }
}