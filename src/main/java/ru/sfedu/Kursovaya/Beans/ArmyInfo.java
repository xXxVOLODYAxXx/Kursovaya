package ru.sfedu.Kursovaya.Beans;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ArmyInfo {
    @CsvBindByName
    private int armyHealthPoints;
    @CsvBindByName
    private int armyAttackPoints;

    public ArmyInfo() {
    }

    public int getArmyHealthPoints() {
        return this.armyHealthPoints;
    }
    public int getArmyAttackPoints() {
        return this.armyAttackPoints;
    }
    public void setArmyHealthPoints(int armyHealthPoints) {
        this.armyHealthPoints = armyHealthPoints;
    }
    public void setArmyAttackPoints(int armyAttackPoints) {
        this.armyAttackPoints = armyAttackPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArmyInfo armyInfo = (ArmyInfo) o;
        return armyHealthPoints == armyInfo.armyHealthPoints && armyAttackPoints == armyInfo.armyAttackPoints;
    }
    @Override
    public int hashCode() {
        return Objects.hash(armyHealthPoints, armyAttackPoints);
    }
    @Override
    public String toString() {
        return "ArmyInfo{" +
                "armyHealthPoints=" + armyHealthPoints +
                ", armyAttackPoints=" + armyAttackPoints +
                '}';
    }
}
