package ru.sfedu.Kursovaya.HibernateLabs.lab2.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="entity")
public class TestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;


    @Column
    private Boolean checking;

    @Embedded
    private NewEntity newEntity;

    public TestEntity() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Boolean getChecking() { return checking; }

    public void setChecking(Boolean checking) { this.checking = checking; }

    public NewEntity getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(NewEntity newEntity) {
        this.newEntity = newEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(checking, that.checking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, newEntity, checking);
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", newEntity=" + newEntity.toString() +
                ", checking=" + checking +
                '}';
    }
}