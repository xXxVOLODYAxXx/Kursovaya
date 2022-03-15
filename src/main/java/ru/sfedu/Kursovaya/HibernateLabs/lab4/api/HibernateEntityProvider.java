package ru.sfedu.Kursovaya.HibernateLabs.lab4.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.Kursovaya.HibernateLabs.lab4.models.HibernateArmy;
import ru.sfedu.Kursovaya.HibernateLabs.lab4.utils.HibernateUtil;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;


import java.io.IOException;
import java.util.List;

public class HibernateEntityProvider {
    private Logger log = LogManager.getLogger(HibernateEntityProvider.class);
    private Session session;

    private void initSession() throws IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    private void close() {session.close();}

    public HibernateArmy createHibernateArmy(HibernateArmy army) throws IOException {
        initSession();
        Transaction tx = session.beginTransaction();
        army.setId((Long) session.save(army));
        tx.commit();
        close();
        return army;
    }

    public List<HibernateArmy> getHibernateArmys() throws IOException {
        initSession();
        try {
            String query = String.format(Constants.QUERY, HibernateArmy.class.getSimpleName());
            Transaction tx = session.beginTransaction();
            List<HibernateArmy> armyList = session.createQuery(query).list();
            tx.commit();
            log.debug(armyList);
            close();
            return armyList;
        } catch (Exception e) {
            log.error(e);
            close();
            return null;
        }
    }

    public HibernateArmy getHibernateArmyById(Long id) throws IOException{
        initSession();
        try {
            HibernateArmy army = session.get(HibernateArmy.class, id);
            log.debug(army);
            close();
            if (army == null) {
                army = new HibernateArmy();
                log.error("Not found");
            }
            return army;
        }
        catch (Exception e) {
            close();
            return new HibernateArmy();
        }
    }

    public Boolean deleteHibernateArmy(Long id) throws IOException{
        initSession();
        HibernateArmy army = new HibernateArmy();
        army.setId(id);
        try {
            Transaction tx = session.beginTransaction();
            session.delete(army);
            tx.commit();
            close();
            return true;
        } catch (Exception e) {
            log.error(e);
            close();
            return false;
        }
    }

    public HibernateArmy updateHibernateArmy(HibernateArmy army) throws IOException {
        initSession();
        Transaction tx = session.beginTransaction();
        session.update(army);
        tx.commit();
        close();
        return army;
    }
}
