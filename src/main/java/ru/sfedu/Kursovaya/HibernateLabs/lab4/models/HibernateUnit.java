package ru.sfedu.Kursovaya.HibernateLabs.lab4.models;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class HibernateUnit {
    @Column
    private Long id;
    @Column
    private String unitType;
    @Column
    private int unitAttackPoints;
    @Column
    private int unitHealthPoints;
    @Column
    private int goldRequired;
    @Column
    private int metalRequired;
    @Column
    private int foodRequired;

    public HibernateUnit(){}

    public Long getUnitId(){
        return this.id;
    }
    public String getUnitType(){
        return this.unitType;
    }
    public int getUnitAttackPoints(){
        return this.unitAttackPoints;
    }
    public int getUnitHealthPoints(){
        return this.unitHealthPoints;
    }
    public int getGoldRequired(){
        return this.goldRequired;
    }
    public int getMetalRequired(){
        return this.metalRequired;
    }
    public int getFoodRequired(){
        return this.foodRequired;
    }
    public void setUnitId(Long id){
        this.id=id;
    }
    public void setUnitType(String unitType) {
        this.unitType=unitType;
    }
    public void setUnitAttackPoints(int unitAttackPoints) {
        this.unitAttackPoints = unitAttackPoints;
    }
    public void setUnitHealthPoints(int unitHealthPoints) {
        this.unitHealthPoints = unitHealthPoints;
    }
    public void setGoldRequired(int goldRequired) {
        this.goldRequired = goldRequired;
    }
    public void setMetalRequired(int metalRequired) {
        this.metalRequired = metalRequired;
    }
    public void setFoodRequired(int foodRequired) {
        this.foodRequired = foodRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateUnit that = (HibernateUnit) o;
        return unitAttackPoints == that.unitAttackPoints && unitHealthPoints == that.unitHealthPoints && goldRequired == that.goldRequired && metalRequired == that.metalRequired && foodRequired == that.foodRequired && Objects.equals(id, that.id) && Objects.equals(unitType, that.unitType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, unitType, unitAttackPoints, unitHealthPoints, goldRequired, metalRequired, foodRequired);
    }

    @Override
    public String toString() {
        return "HibernateUnit{" +
                "id=" + id +
                ", unitType='" + unitType + '\'' +
                ", unitAttackPoints=" + unitAttackPoints +
                ", unitHealthPoints=" + unitHealthPoints +
                ", goldRequired=" + goldRequired +
                ", metalRequired=" + metalRequired +
                ", foodRequired=" + foodRequired +
                '}';
    }
}
