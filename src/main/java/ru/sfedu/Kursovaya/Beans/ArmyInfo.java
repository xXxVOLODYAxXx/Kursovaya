package ru.sfedu.Kursovaya.Beans;

import java.util.Objects;

public class ArmyInfo {
    private int armyHealthPoints;
    private int armyAttackPoints;

    public ArmyInfo() {
    }

    public int getArmyHealthPoints() {
        return this.armyHealthPoints;
    }

    public void setArmyHealthPoints(int armyHealthPoints) {
        this.armyHealthPoints = armyHealthPoints;
    }

    public int getArmyAttackPoints() {
        return this.armyAttackPoints;
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
