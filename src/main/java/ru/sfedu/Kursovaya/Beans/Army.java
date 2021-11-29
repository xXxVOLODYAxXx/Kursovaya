package ru.sfedu.Kursovaya.Beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import ru.sfedu.Kursovaya.utils.Converters.ArmyInfoTransformer;
import ru.sfedu.Kursovaya.utils.Converters.UnitTransformer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Army {
    @CsvBindByName
    private Long id;
    @CsvCustomBindByName(required = false, converter = UnitTransformer.class)
    private List<Unit> units;
    @CsvCustomBindByName(required = false, converter = ArmyInfoTransformer.class)
    private ArmyInfo armyInfo;

    public Army() {
    }

    public Long getArmyId() {
        return this.id;
    }
    public void setArmyId(Long id) {
        this.id = id;
    }
    public List<Unit> getUnits() {
        return this.units;
    }
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    public ArmyInfo getArmyInfo() {
        return this.armyInfo;
    }
    public void setArmyInfo(ArmyInfo armyInfo) {
        this.armyInfo = armyInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(units, army.units) && Objects.equals(armyInfo, army.armyInfo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(units, armyInfo);
    }
    @Override
    public String toString() {
        return "Army{" +
                "units=" + units +
                ", armyInfo=" + armyInfo +
                '}';
    }


}
