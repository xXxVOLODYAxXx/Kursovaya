package ru.sfedu.Kursovaya.Beans;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import ru.sfedu.Kursovaya.utils.Converters.BuildingTransformer;
import ru.sfedu.Kursovaya.utils.Converters.EnemyPlanetTransformer;
import ru.sfedu.Kursovaya.utils.Converters.PlayerPlanetTransformer;
import ru.sfedu.Kursovaya.utils.Converters.ResourcesTransformer;

import java.util.List;
import java.util.Objects;

public class Game {
    @CsvBindByName
    private Long id;
    @CsvBindByName
    private String gameName;
    @CsvCustomBindByName(required = false, converter = EnemyPlanetTransformer.class)
    private List<EnemyPlanet> enemyPlanetList;
    @CsvCustomBindByName(required = false, converter = PlayerPlanetTransformer.class)
    private List<PlayerPlanet> playerPlanetList;
    @CsvCustomBindByName(required = false, converter = ResourcesTransformer.class)
    private Resources resources;

    public Game() {}

    public Long getGameId() {
        return this.id;
    }
    public void setGameId(Long id) {
        this.id = id;
    }
    public String getGameName() {
        return this.gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public List<EnemyPlanet> getEnemyPlanetList() {
        return this.enemyPlanetList;
    }
    public void setEnemyPlanetList(List<EnemyPlanet> enemyPlanetList) {
        this.enemyPlanetList = enemyPlanetList;
    }
    public List<PlayerPlanet> getPlayerPlanetList() {
        return this.playerPlanetList;
    }
    public void setPlayerPlanetList(List<PlayerPlanet> playerPlanetList) {
        this.playerPlanetList = playerPlanetList;
    }
    public Resources getResources() {
        return this.resources;
    }
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(gameName, game.gameName) && Objects.equals(enemyPlanetList, game.enemyPlanetList) && Objects.equals(playerPlanetList, game.playerPlanetList) && Objects.equals(resources, game.resources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameName, enemyPlanetList, playerPlanetList, resources);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", enemyPlanetList=" + enemyPlanetList +
                ", playerPlanetList=" + playerPlanetList +
                ", resources=" + resources +
                '}';
    }
}
