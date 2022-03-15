package ru.sfedu.Kursovaya.HibernateLabs.lab2.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Embeddable
public class NewEntity implements Serializable  {

    public NewEntity() { };

    @Column(name="NewName")
    private String name;

    @Column(name="NewDescription")
    private String description;

    @ElementCollection
    private List<String> collect;



    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<String> getCollection() { return collect; }

    public void setCollection(List<String> collection) { this.collect = collect; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewEntity newEntity = (NewEntity) o;
        return name.equals(newEntity.name) &&
                description.equals(newEntity.description) &&
                collect.equals(newEntity.collect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, collect);
    }

    @Override
    public String toString() {
        return "NewEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", collection=" + collect +
                '}';
    }
}