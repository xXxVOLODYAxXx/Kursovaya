package ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.models.PerClassHibernatePlayerPlanet;
import java.io.IOException;
import java.util.List;

class HibernateEntityProviderTest {
    private ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.api.HibernateEntityProvider dp = new HibernateEntityProvider();
    private Logger log = LogManager.getLogger(ru.sfedu.Kursovaya.HibernateLabs.lab3.TablePerClass.api.HibernateEntityProviderTest.class);

    @Test
    public void createPlayerPlanet() throws IOException {
        PerClassHibernatePlayerPlanet playerPlanet = new PerClassHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        log.info(playerPlanet);
    }

    @Test
    public void getPlayerPlanets() throws IOException {
        PerClassHibernatePlayerPlanet playerPlanet = new PerClassHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        List<PerClassHibernatePlayerPlanet> playerPlanets = dp.getPlayerPlanets();
        log.info(playerPlanets.toString());
        Assert.assertNotNull(playerPlanets);
    }

    @Test
    public void getPlayerPlanetById() throws IOException {
        PerClassHibernatePlayerPlanet playerPlanet = new PerClassHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        long id = playerPlanet.getPlanetId();
        PerClassHibernatePlayerPlanet playerPlanet2 = dp.getPlayerPlanetById(id);
        log.info(playerPlanet2);
        Assert.assertTrue(playerPlanet2.getPlanetId() == id);
    }

    @Test
    public void deletePlayerPlanets() throws IOException {
        List<PerClassHibernatePlayerPlanet> playerPlanetList = dp.getPlayerPlanets();
        long id = playerPlanetList.get(0).getPlanetId();
        Boolean hasDeleted = dp.deletePlayerPlanets(id);
        log.info(hasDeleted);
    }

    @Test
    public void updatePlayerPlanet() throws IOException {
        PerClassHibernatePlayerPlanet playerPlanet = new PerClassHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        playerPlanet.setBuildingLimit(13);
        Assert.assertTrue(playerPlanet.equals(dp.updatePlayerPlanet(playerPlanet)));
    }
}