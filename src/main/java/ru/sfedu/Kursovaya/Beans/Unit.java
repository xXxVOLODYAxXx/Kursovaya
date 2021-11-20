package ru.sfedu.Kursovaya.Beans;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Unit {
    @CsvBindByName
    private Long id;
    @CsvBindByName
    private String unitType;
    @CsvBindByName
    private int unitAttackPoints;
    @CsvBindByName
    private int unitHealthPoints;
    @CsvBindByName
    private int goldRequired;
    @CsvBindByName
    private int metalRequired;
    @CsvBindByName
    private int foodRequired;

    /**КОНСТРУКТОРЫ*/
    public Unit(){super();}

    /**ГЕТТЕРЫ*/
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

    /**СЕТТЕРЫ*/
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
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        Unit unit = (Unit) o;
        return id == unit.id
                && unitAttackPoints == unit.unitAttackPoints
                && unitHealthPoints == unit.unitHealthPoints
                && goldRequired == unit.goldRequired
                && metalRequired == unit.metalRequired
                && foodRequired == unit.foodRequired
                && unitType.equals(unit.unitType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, unitType, unitAttackPoints, unitHealthPoints, goldRequired, metalRequired, foodRequired);
    }

    @Override
    public String toString() {
        return "Unit{" +
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

