package ru.sfedu.Kursovaya.HibernateLabs.lab3.Mapped.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import ru.sfedu.Kursovaya.HibernateLabs.lab3.Mapped.models.MappedHibernatePlayerPlanet;

import java.io.IOException;
import java.util.List;

public class HibernateEntityProviderTest {
    private HibernateEntityProvider dp = new HibernateEntityProvider();
    private Logger log = LogManager.getLogger(HibernateEntityProviderTest.class);
    private MappedHibernatePlayerPlanet playerPlanet = new MappedHibernatePlayerPlanet();

    public MappedHibernatePlayerPlanet initPlayerPlanet(){
        playerPlanet.setPlanetName("A");
        playerPlanet.setBuildingLimit(10);
        playerPlanet.setPlanetType("PLAYER");
        return playerPlanet;
    }

    @Test
    public void createPlayerPlanet() throws IOException {
        MappedHibernatePlayerPlanet playerPlanet = new MappedHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        log.info(playerPlanet);
    }

    @Test
    public void getPlayerPlanets() throws IOException {
        MappedHibernatePlayerPlanet playerPlanet = new MappedHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        List<MappedHibernatePlayerPlanet> playerPlanets = dp.getPlayerPlanets();
        log.info(playerPlanets.toString());
        Assert.assertNotNull(playerPlanets);
    }

    @Test
    public void getPlayerPlanetById() throws IOException {
        MappedHibernatePlayerPlanet playerPlanet = new MappedHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        long id = playerPlanet.getPlanetId();
        MappedHibernatePlayerPlanet playerPlanet2 = dp.getPlayerPlanetById(id);
        log.info(playerPlanet2);
        Assert.assertTrue(playerPlanet2.getPlanetId() == id);
    }

    @Test
    public void deletePlayerPlanets() throws IOException {
        List<MappedHibernatePlayerPlanet> playerPlanetList = dp.getPlayerPlanets();
        long id = playerPlanetList.get(0).getPlanetId();
        Boolean hasDeleted = dp.deletePlayerPlanets(id);
        log.info(hasDeleted);
    }

    @Test
    public void updatePlayerPlanet() throws IOException {
        MappedHibernatePlayerPlanet playerPlanet = new MappedHibernatePlayerPlanet();
        playerPlanet.setBuildingLimit(10);
        dp.createPlayerPlanet(playerPlanet);
        playerPlanet.setBuildingLimit(13);
        Assert.assertTrue(playerPlanet.equals(dp.updatePlayerPlanet(playerPlanet)));
    }
}