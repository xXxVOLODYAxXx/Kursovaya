package ru.sfedu.Kursovaya.HibernateLabs.lab2.api;

import ru.sfedu.Kursovaya.HibernateLabs.lab2.models.NewEntity;
import ru.sfedu.Kursovaya.HibernateLabs.lab2.models.TestEntity;
import ru.sfedu.Kursovaya.HibernateLabs.models.ResultType;

import java.util.Optional;

public interface IEntityDataProvider {
    public Long createEntity(String name, String description, Boolean check, NewEntity newEntity);
    public ResultType updateEntity(Long id, String name, String description, Boolean check, NewEntity newEntity);
    public void update(TestEntity entity);
    public ResultType delete(Long Id);
    public Optional<TestEntity> getById(Class<TestEntity> entity, Long id);
    public Long save(TestEntity entity);
}