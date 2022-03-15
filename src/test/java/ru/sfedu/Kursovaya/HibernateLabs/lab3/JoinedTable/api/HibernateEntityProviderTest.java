package ru.sfedu.Kursovaya.HibernateLabs.lab3.JoinedTable.api;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.JoinedTable.models.HibernatePlayerPlanet;
import java.io.IOException;
import java.util.List;


public class HibernateEntityProviderTest {
    private HibernateEntityProvider dp = new HibernateEntityProvider();
    private Logger log = LogManager.getLogger(HibernateEntityProviderTest.class);

    @Test
    public void createPlayerPlanet() throws IOException {
        HibernatePlayerPlanet playerPlanet = new HibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        log.info(playerPlanet);
    }

    @Test
    public void getPlayerPlanets() throws IOException {
        HibernatePlayerPlanet playerPlanet = new HibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        List<HibernatePlayerPlanet> playerPlanets = dp.getPlayerPlanets();
        log.info(playerPlanets.toString());
        Assert.assertNotNull(playerPlanets);
    }

    @Test
    public void getPlayerPlanetById() throws IOException {
        HibernatePlayerPlanet playerPlanet = new HibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        long id = playerPlanet.getPlanetId();
        HibernatePlayerPlanet playerPlanet2 = dp.getPlayerPlanetById(id);
        log.info(playerPlanet2);
        Assert.assertTrue(playerPlanet2.getPlanetId() == id);
    }

    @Test
    public void deletePlayerPlanets() throws IOException {
        List<HibernatePlayerPlanet> playerPlanetList = dp.getPlayerPlanets();
        long id = playerPlanetList.get(0).getPlanetId();
        Boolean hasDeleted = dp.deletePlayerPlanets(id);
        log.info(hasDeleted);
    }

    @Test
    public void updatePlayerPlanet() throws IOException {
        HibernatePlayerPlanet playerPlanet = new HibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        playerPlanet.setBuildingLimit(13);
        Assert.assertTrue(playerPlanet.equals(dp.updatePlayerPlanet(playerPlanet)));
    }
}