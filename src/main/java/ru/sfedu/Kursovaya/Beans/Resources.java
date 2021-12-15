package ru.sfedu.Kursovaya.Beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import ru.sfedu.Kursovaya.utils.CSVConverters.ArmyTransformer;
import ru.sfedu.Kursovaya.utils.CSVConverters.BuildingTransformer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resources {
    @CsvBindByName
    private Long id;
    @CsvBindByName
    private int food;
    @CsvBindByName
    private int metal;
    @CsvBindByName
    private int gold;
    @CsvCustomBindByName(required = false, converter = ArmyTransformer.class)
    private Army army;
    @CsvCustomBindByName(required = false, converter = BuildingTransformer.class)
    private List<Building> buildingList=new ArrayList<>();
    @CsvBindByName
    private int operation;

    public Resources() {}

    public Long getResourcesId() {return this.id;}
    public void setResourcesId(Long id) {this.id = id;}
    public int getFood() {return this.food;}
    public void setFood(int food) {this.food = food;}
    public int getMetal() {return this.metal;}
    public void setMetal(int metal) {this.metal = metal;}
    public int getGold() {return this.gold;}
    public void setGold(int gold) {this.gold = gold;}
    public Army getArmy() {return this.army;}
    public void setArmy(Army army) {this.army = army;}
    public List<Building> getBuildingList() {return this.buildingList;}
    public void setBuildingList(List<Building> buildingList) {this.buildingList = buildingList;}
    public int getOperation() {return this.operation;}
    public void setOperation(int operation) {this.operation = operation;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resources resources = (Resources) o;
        return food == resources.food && metal == resources.metal && gold == resources.gold && operation == resources.operation && Objects.equals(id, resources.id) && Objects.equals(army, resources.army) && Objects.equals(buildingList, resources.buildingList);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, food, metal, gold, army, buildingList, operation);
    }
    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", food=" + food +
                ", metal=" + metal +
                ", gold=" + gold +
                ", army=" + army +
                ", buildingList=" + buildingList +
                ", operation=" + operation +
                '}';
    }
}
