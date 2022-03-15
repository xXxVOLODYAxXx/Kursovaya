package ru.sfedu.Kursovaya.HibernateLabs.lab1.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import ru.sfedu.Kursovaya.utils.HibernateUtil;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;
import java.io.IOException;
import java.util.List;


public class HibernateDataProvider implements IDataProvider {

    private static final Logger log = LogManager.getLogger(HibernateDataProvider.class.getName());

        @Override
        public List getSchema() {
            try {
                Session session = this.getSession();
                NativeQuery query = session.createSQLQuery(Constants.SCHEMA);
                List resList = query.getResultList();
                session.close();
                log.info(Constants.SCHEMA_SIZE, resList.size());
                return resList;
            } catch (IOException e) {
                log.error(e);
                return null;
            }
        }

        @Override
        public List getTables() {
            try {
                Session session = this.getSession();
                NativeQuery query = session.createSQLQuery(Constants.TABLE);
                List resList = query.getResultList();
                session.close();
                log.debug(Constants.TABLE_NAME + resList.toString());
                return resList;
            }
            catch (IOException e) {
                log.error(e);
                return null;
            }
        }

        @Override
        public List getSchemaName() {
            try {
                Session session = this.getSession();
                NativeQuery query = session.createSQLQuery(Constants.SCHEMA_NAME);
                List resList = query.getResultList();
                session.close();
                log.debug(Constants.POST_SCHEMA_NAME, resList);
                return resList;
            }
            catch (IOException e) {
                log.error(e);
                return null;
            }
        }

        @Override
        public List getDetails() {
            try {
                Session session = this.getSession();

                NativeQuery query = session.createSQLQuery(Constants.DETAILS);
                List resList = query.getResultList();
                session.close();
                log.info(Constants.SCHEMA_DETAILS,resList);
                return resList;
            }
            catch (IOException e) {
                log.error(e);
                return null;
            }
        }

        private Session getSession() throws IOException {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            return factory.openSession();
        }
    }

