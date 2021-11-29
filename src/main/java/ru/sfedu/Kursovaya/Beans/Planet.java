package ru.sfedu.Kursovaya.Beans;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Planet {
    @XmlElement(type = Long.class)
    @CsvBindByName
    private Long id;
    @XmlElement(type = String.class)
    @CsvBindByName
    private String planetName;
    @XmlElement(type = String.class)
    @CsvBindByName
    private String type;
    public Planet() {super();}

    public Long getPlanetId() {return this.id;}
    public String getPlanetName() {return this.planetName;}
    public String getPlanetType() {return type;}
    public void setPlanetId(Long id) {this.id = id;}
    public void setPlanetName(String planetName) {this.planetName = planetName;}
    public void setPlanetType(String type) {this.type = type;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id) && Objects.equals(planetName, planet.planetName) && Objects.equals(type, planet.type);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, planetName, type);
    }
    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", planetName='" + planetName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
