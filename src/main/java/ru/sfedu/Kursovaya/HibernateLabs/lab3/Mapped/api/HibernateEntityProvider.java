package ru.sfedu.Kursovaya.HibernateLabs.lab3.Mapped.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.Mapped.models.MappedHibernatePlayerPlanet;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.Mapped.utils.HibernateUtil;
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

    public MappedHibernatePlayerPlanet createPlayerPlanet(MappedHibernatePlayerPlanet playerPlanet) throws IOException {
        initSession();
        Transaction tx = session.beginTransaction();
        log.info(playerPlanet);
        playerPlanet.setPlanetId((long) session.save(playerPlanet));
        tx.commit();
        close();
        return playerPlanet;
    }

    public List<MappedHibernatePlayerPlanet> getPlayerPlanets() throws IOException {
        initSession();
        try {
            String query = String.format(Constants.QUERY, MappedHibernatePlayerPlanet.class.getSimpleName());
            session.getTransaction().begin();
            List<MappedHibernatePlayerPlanet> playerPlanets = session.createQuery(query).list();
            session.getTransaction().commit();
            close();
            log.info(playerPlanets);
            return playerPlanets;
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }

    public MappedHibernatePlayerPlanet getPlayerPlanetById(long id) throws IOException {
        initSession();
        try {
            MappedHibernatePlayerPlanet playerPlanet = session.get(MappedHibernatePlayerPlanet.class, id);
            close();
            return playerPlanet;
        } catch (Exception e) {
            log.error("Not found");
            close();
            return new MappedHibernatePlayerPlanet();
        }
    }

    public Boolean deletePlayerPlanets(long id) throws IOException {
        initSession();
        MappedHibernatePlayerPlanet playerPlanet = new MappedHibernatePlayerPlanet();
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

    public MappedHibernatePlayerPlanet updatePlayerPlanet(MappedHibernatePlayerPlanet playerPlanet) throws IOException {
        initSession();
        Transaction tr = session.beginTransaction();
        session.update(playerPlanet);
        tr.commit();
        close();
        return playerPlanet;
    }
}
