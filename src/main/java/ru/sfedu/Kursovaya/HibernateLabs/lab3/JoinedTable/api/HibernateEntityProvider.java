package ru.sfedu.Kursovaya.HibernateLabs.lab3.JoinedTable.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.JoinedTable.models.HibernatePlayerPlanet;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.JoinedTable.utils.HibernateUtil;
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

    public HibernatePlayerPlanet createPlayerPlanet(HibernatePlayerPlanet playerPlanet) throws IOException {
        initSession();
        Transaction tx = session.beginTransaction();
        playerPlanet.setPlanetId((long) session.save(playerPlanet));
        tx.commit();
        close();
        return playerPlanet;
    }

    public List<HibernatePlayerPlanet> getPlayerPlanets() throws IOException {
        initSession();
        try {
            String query = String.format(Constants.QUERY, HibernatePlayerPlanet.class.getSimpleName());
            session.getTransaction().begin();
            List<HibernatePlayerPlanet> playerPlanets = session.createQuery(query).list();
            session.getTransaction().commit();
            close();
            return playerPlanets;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public HibernatePlayerPlanet getPlayerPlanetById(long id) throws IOException {
        initSession();
        try {
            HibernatePlayerPlanet playerPlanet = session.get(HibernatePlayerPlanet.class, id);
            close();
            return playerPlanet;
        } catch (Exception e) {
            log.error("Not found");
            close();
            return new HibernatePlayerPlanet();
        }
    }

    public Boolean deletePlayerPlanets(long id) throws IOException {
        initSession();
        HibernatePlayerPlanet playerPlanet = new HibernatePlayerPlanet();
        playerPlanet.setPlanetId(id);
        try {
            Transaction tr = session.beginTransaction();
            session.delete(playerPlanet);
            tr.commit();
            close();
            return true;
        } catch (Exception e) {
            log.error(e);
            close();
            return false;
        }
    }

    public HibernatePlayerPlanet updatePlayerPlanet(HibernatePlayerPlanet playerPlanet) throws IOException {
        initSession();
        Transaction tr = session.beginTransaction();
        session.update(playerPlanet);
        tr.commit();
        close();
        return playerPlanet;
    }
}
