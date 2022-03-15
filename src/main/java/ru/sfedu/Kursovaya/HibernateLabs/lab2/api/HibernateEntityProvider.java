package ru.sfedu.Kursovaya.HibernateLabs.lab2.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.Kursovaya.HibernateLabs.lab2.models.NewEntity;
import ru.sfedu.Kursovaya.HibernateLabs.lab2.models.TestEntity;
import ru.sfedu.Kursovaya.HibernateLabs.models.ResultType;
import ru.sfedu.Kursovaya.utils.HibernateUtil;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class HibernateEntityProvider implements IEntityDataProvider {

        public static Logger log = LogManager.getLogger(HibernateEntityProvider.class);
        static Session session;

        @Override
        public Long createEntity(String name, String description, Boolean check, NewEntity newEntity) {
            log.debug("In createBean method");
            TestEntity entity = new TestEntity();
            entity.setName(name);
            entity.setDescription(description);
            entity.setChecking(check);
            entity.setNewEntity(newEntity);
            log.debug("Initializing entity:" + entity.toString());
            Long id = this.save(entity);
            log.debug("Saving object with id: " + id);
            return id;
        }

        @Override
        public ResultType updateEntity(Long id, String name, String description, Boolean check, NewEntity newEntity) {
            try {
                log.debug("In updateBean method");
                TestEntity entity = new TestEntity();
                entity = getById(TestEntity.class, id).get();
                entity.setName(name);
                entity.setDescription(description);
                entity.setChecking(check);
                entity.setNewEntity(newEntity);
                this.update(entity);
                return ResultType.COMPLETE;
            } catch (NoSuchElementException e) {
                log.error("Updating bean error");
                return ResultType.FAIL;
            }

        }


        @Override
        public void update(TestEntity entity) {
            try {
                session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(entity);
                transaction.commit();
            } catch (IOException | NonUniqueObjectException e) {
                log.error(e);
            } finally {
                if (session != null) session.close();
            }
        }

        @Override
        public ResultType delete(Long Id) {
            try {
                session = this.getSession();
                TestEntity entity = getById(TestEntity.class, Id).get();
                Transaction transaction = session.beginTransaction();
                session.delete(entity);
                transaction.commit();
                return ResultType.COMPLETE;
            } catch (IOException | NoSuchElementException e) {
                log.error(e);
                return ResultType.FAIL;

            } finally {
                if (session != null) session.close();
            }
        }

        @Override
        @Transactional
        public Optional<TestEntity> getById(Class<TestEntity> entity, Long id) {
            try {
                Session session = this.getSession();
                TestEntity testBean = session.get(entity, id);
                log.debug("Returned entity: " + testBean.toString());
                session.close();
                return Optional.of(testBean);
            } catch (IOException | NullPointerException e) {
                log.error(e);
                return Optional.empty();
            }
        }

        @Override
        public Long save(TestEntity entity) {
            try {
                session = this.getSession();
                Transaction transaction = session.beginTransaction();
                Long id = (Long) session.save(entity);
                transaction.commit();
                log.debug("Saving bean with the id: " + id.toString());
                return id;
            } catch (IOException e) {
                log.error(e);
                return null;
            } finally {
                if (session != null) session.close();
            }
        }

        private Session getSession() throws IOException {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            return factory.openSession();
        }
    }
