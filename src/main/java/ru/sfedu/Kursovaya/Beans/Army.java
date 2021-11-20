package ru.sfedu.Kursovaya.Beans;

import java.util.List;
import java.util.Objects;

public class Army {
    private Long id;
    private List<Unit> units;
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
