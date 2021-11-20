package ru.sfedu.Kurss1.entityBeans;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Building {
    @CsvBindByName
    private Long id;
    @CsvBindByName
    private String buildingType;
    @CsvBindByName
    private int foodBuff;
    @CsvBindByName
    private int metalBuff;
    @CsvBindByName
    private int goldBuff;
    @CsvBindByName
    private int foodRequired;
    @CsvBindByName
    private int metalRequired;
    @CsvBindByName
    private int goldRequired;

    public Building(){super();}

    public Long getBuildingId(){
        return this.id;
    }
    public String getBuildingType(){
        return this.buildingType;
    }
    public int getFoodBuff(){
        return this.foodBuff;
    }
    public int getMetalBuff(){
        return this.metalBuff;
    }
    public int getGoldBuff(){return this.goldBuff;}
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
    public void setBuildingType(String buildingType) {
        this.buildingType=buildingType;
    }
    public void setFoodBuff(int foodBuff) {
        this.foodBuff = foodBuff;
    }
    public void setMetalBuff(int metalBuff) {
        this.metalBuff = metalBuff;
    }
    public void setGoldBuff(int goldBuff) {
        this.goldBuff = goldBuff;
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
        Building building = (Building) o;
        return id.equals(building.id)
                && buildingType.equals(building.buildingType)
                && goldBuff == building.goldBuff
                && metalBuff == building.metalBuff
                && foodBuff == building.foodBuff
                && foodRequired == building.foodRequired
                && metalRequired == building.metalRequired
                && goldRequired == building.goldRequired;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buildingType, foodBuff, metalBuff, goldBuff, goldRequired, metalRequired, foodRequired);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", buildingType='" + buildingType + '\'' +
                ", foodBuff=" + foodBuff +
                ", metalBuff=" + metalBuff +
                ", goldBuff=" + goldBuff +
                ", goldRequired=" + goldRequired +
                ", metalRequired=" + metalRequired +
                ", foodRequired=" + foodRequired +
                '}';
    }
}
