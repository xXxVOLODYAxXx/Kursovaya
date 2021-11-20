package ru.sfedu.Kursovaya.Beans;

import java.util.Objects;

public class EnemyPlanet extends Planet{
    private int enemyHealthPoints;
    private int enemyAttackPoints;

    public EnemyPlanet() {
    }

    public int getEnemyHealthPoints() {
        return this.enemyHealthPoints;
    }

    public void setEnemyHealthPoints(int enemyHealthPoints) {
        this.enemyHealthPoints = enemyHealthPoints;
    }

    public int getEnemyAttackPoints() {
        return this.enemyAttackPoints;
    }

    public void setEnemyAttackPoints(int enemyAttackPoints) {
        this.enemyAttackPoints = enemyAttackPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyPlanet that = (EnemyPlanet) o;
        return enemyHealthPoints == that.enemyHealthPoints && enemyAttackPoints == that.enemyAttackPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enemyHealthPoints, enemyAttackPoints);
    }

    @Override
    public String toString() {
        return "EnemyPlanet{" +
                "enemyHealthPoints=" + enemyHealthPoints +
                ", enemyAttackPoints=" + enemyAttackPoints +
                '}';
    }
}
