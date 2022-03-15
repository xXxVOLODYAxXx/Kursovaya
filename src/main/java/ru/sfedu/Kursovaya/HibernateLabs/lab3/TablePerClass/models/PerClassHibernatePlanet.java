package ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PerClassHibernatePlanet {
    @Id
    @GeneratedValue(generator = "increment")
    @Column
    private Long id;
    @Column
    private String planetName;
    @Column
    private String type;

    public PerClassHibernatePlanet() {super();}

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
        PerClassHibernatePlanet that = (PerClassHibernatePlanet) o;
        return Objects.equals(id, that.id) && Objects.equals(planetName, that.planetName) && Objects.equals(type, that.type);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, planetName, type);
    }
    @Override
    public String toString() {
        return "HibernatePlanet{" +
                "id=" + id +
                ", planetName='" + planetName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}