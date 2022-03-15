package ru.sfedu.Kursovaya.HibernateLabs.lab2.api;

import org.junit.Test;
import ru.sfedu.Kursovaya.HibernateLabs.lab2.models.NewEntity;
import ru.sfedu.Kursovaya.HibernateLabs.lab2.models.TestEntity;
import ru.sfedu.Kursovaya.HibernateLabs.models.ResultType;
import java.util.Date;
import java.util.Arrays;
import static org.junit.Assert.*;

public class HibernateEntityProviderTest {

    @Test
    public void createEntity(){
        IEntityDataProvider provider = new HibernateEntityProvider();
        NewEntity embeddedEntity = new NewEntity();
        embeddedEntity.setName("Nested");
        embeddedEntity.setDescription("Nested Description");
        embeddedEntity.setCollection(Arrays.asList("First","Second"));
        Long id = provider.createEntity("test","test entity description",true,embeddedEntity);
        TestEntity testBean = provider.getById(TestEntity.class,id).get();
        TestEntity expected = new TestEntity();
        expected.setId(id);
        expected.setName("test");
        expected.setDescription("test entity description");
        expected.setChecking(true);
        expected.setNewEntity(embeddedEntity);
        assertEquals(expected,testBean);
    }

    @Test
    public void getById() {
        IEntityDataProvider provider = new HibernateEntityProvider();
        NewEntity embeddedEntity = new NewEntity();
        embeddedEntity.setName("Nested");
        embeddedEntity.setDescription("Nested Description");
        embeddedEntity.setCollection(Arrays.asList("First","Second"));
        Date date = new Date();
        Long id = provider.createEntity("test","test bean description",true,embeddedEntity);
        assertEquals(ResultType.COMPLETE,provider.updateEntity(id,"test","new bean description",true,embeddedEntity));
        assertEquals("new bean description",provider.getById(TestEntity.class,id).get().getDescription());
    }

    @Test
    public void update1() {
        IEntityDataProvider provider = new HibernateEntityProvider();
        NewEntity embeddedEntity = new NewEntity();
        embeddedEntity.setName("Nested");
        embeddedEntity.setDescription("Nested Description");
        embeddedEntity.setCollection(Arrays.asList("First","Second"));
        Date date = new Date();
        Long id = provider.createEntity("test","test bean description",true,embeddedEntity);
        assertNotEquals(ResultType.FAIL,provider.updateEntity(id,"test","new bean description",true,embeddedEntity));
    }

    @Test
    public void delete() {
        IEntityDataProvider provider = new HibernateEntityProvider();
        TestEntity bean = new TestEntity();
        bean.setDescription("Test bean");
        bean.setName("Test");
        Long id = provider.save(bean);
        assertEquals(ResultType.COMPLETE,provider.delete(id));

    }

    @Test
    public void delete1() {
        IEntityDataProvider provider = new HibernateEntityProvider();
        TestEntity bean = new TestEntity();
        bean.setDescription("Test bean");
        bean.setName("Test");
        Long id = provider.save(bean);
        assertNotEquals(provider.delete(id),null);
    }

}