package ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class PerClassHibernatePlayerPlanet extends PerClassHibernatePlanet {
    @Column
    private int buildingLimit;

    public PerClassHibernatePlayerPlanet() {}

    public int getBuildingLimit() {
        return this.buildingLimit;
    }
    public void setBuildingLimit(int buildingLimit) {
        this.buildingLimit = buildingLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerClassHibernatePlayerPlanet that = (PerClassHibernatePlayerPlanet) o;
        return buildingLimit == that.buildingLimit;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), buildingLimit);
    }
    @Override
    public String toString() {
        return "HibernatePlayerPlanet{" +
                "buildingLimit=" + buildingLimit +
                '}';
    }
}
