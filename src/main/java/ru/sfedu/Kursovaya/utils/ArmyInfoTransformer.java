package ru.sfedu.Kursovaya.utils;

import com.opencsv.bean.AbstractBeanField;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.Beans.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArmyInfoTransformer extends AbstractBeanField {
    private String fieldsDelimiter = "@";
    private String elemDelimiter = "#";

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
