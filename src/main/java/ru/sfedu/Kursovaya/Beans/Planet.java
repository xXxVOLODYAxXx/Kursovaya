package ru.sfedu.Kursovaya.Beans;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Planet {
    @CsvBindByName
    private Long id;
    @CsvBindByName
    private String planetName;
    @CsvBindByName
    private String type;
    public Planet() {super();}

    public Long getPlanetId() {return this.id;}
    public void setPlanetId(Long id) {this.id = id;}
    public String getPlanetName() {return this.planetName;}
    public void setPlanetName(String planetName) {this.planetName = planetName;}
    public String getPlanetType() {return type;}
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
