package ru.sfedu.Kursovaya.HibernateLabs.lab4.models;


import javax.persistence.*;
import java.util.*;


@Table
@Entity
public class HibernateArmy {

    @Id
    @GeneratedValue(generator = "increment")
    @Column
    private Long id;

    @Column
    private int armyHealthPoints;

    @ElementCollection
    @CollectionTable(name = "Tags")
    @Column
    private Set<String> tags;

    @ElementCollection
    @CollectionTable(name = "Units")
    @OrderColumn
    @Column
    private List<String> units;

    @ElementCollection
    @CollectionTable(name = "HibernateUnit")
    @MapKeyColumn(name = "UnitType")
    @Column
    private Map<String, String> hibernateUnit;

    @ElementCollection
    @CollectionTable(name = "UnitList")
    @AttributeOverride(
            name = "unitName",
            column = @Column(name = "unit_name")
    )
    private Set<HibernateUnit> unitSet;

    public HibernateArmy() {}

    public Long getId() {
        return id;
    }

    public int getArmyHealthPoints() {
        return armyHealthPoints;
    }

    public void setArmyHealthPoints(int armyHealthPoints) {
        this.armyHealthPoints = armyHealthPoints;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    public Map<String, String> getHibernateUnit() {
        return hibernateUnit;
    }

    public void setHibernateUnit(Map<String, String> hibernateUnit) {
        this.hibernateUnit = hibernateUnit;
    }

    public Set<HibernateUnit> getUnitSet() {
        return unitSet;
    }

    public void setUnitSet(Set<HibernateUnit> unitSet) {
        this.unitSet = unitSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateArmy that = (HibernateArmy) o;
        return Objects.equals(id, that.id) && Objects.equals(tags, that.tags) && Objects.equals(units, that.units) && Objects.equals(hibernateUnit, that.hibernateUnit) && Objects.equals(unitSet, that.unitSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tags, units, hibernateUnit, unitSet);
    }

    @Override
    public String toString() {
        return "HibernateArmy{" +
                "id=" + id +
                ", tags=" + tags +
                ", units=" + units +
                ", hibernateUnit=" + hibernateUnit +
                ", unitSet=" + unitSet +
                '}';
    }
}
