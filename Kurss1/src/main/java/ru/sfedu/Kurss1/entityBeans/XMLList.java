package ru.sfedu.Kurss1.entityBeans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLList {
    @XmlElement(name="Unit")
    private List<Unit> unitList=null;
    @XmlElement(name="Building")
    private List<Building> buildingList=null;


    public List<Unit> getUnits(){
        return unitList;
    }
    public List<Building> getBuilings(){
        return buildingList;
    }

    public void setUnits(List<Unit> unitList){
        this.unitList=unitList;
    }
    public void setBuildings(List<Building> buildingList){
        this.buildingList=buildingList;
    }
}
