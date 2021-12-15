package ru.sfedu.Kursovaya.utils.CSVConverters;

import com.opencsv.bean.AbstractBeanField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

public class ArmyInfoTransformer extends AbstractBeanField {
    private final String fieldsDelimiter = Constants.ARMY_INFO_FIELDS_DELIMITER;
    private static final Logger log = LogManager.getLogger(ArmyInfoTransformer.class);
    @Override
    public Object convert(String value){
        ArmyInfo armyInfo = new ArmyInfo();
        try {
            String[] data = value.split(fieldsDelimiter);
            armyInfo.setArmyAttackPoints(Integer.parseInt(data[0]));
            armyInfo.setArmyHealthPoints(Integer.parseInt(data[1]));
        } catch (NullPointerException e){
            //log.error(Constants.ARMYINFO+Constants.DO_NOT_EXIST);
        } finally {
            return armyInfo;
        }
    }

    @Override
    public String convertToWrite(Object armyInfo){
        ArmyInfo armyInfoList = (ArmyInfo) armyInfo;
        if (armyInfoList==null){
            return null;
        } else {
        return String.format("%d"
                        +fieldsDelimiter
                        + "%d",
                armyInfoList.getArmyAttackPoints(),
                armyInfoList.getArmyHealthPoints());
        }
    }
}
