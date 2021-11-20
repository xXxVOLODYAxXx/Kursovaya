package ru.sfedu.Kursovaya.Beans;

import java.util.Objects;

public class PlayerPlanet extends Planet{
    private int buildingLimit;

    public PlayerPlanet() {}

    public int getBuildingLimit() {
        return this.buildingLimit;
    }

    public void setBuildingLimit(int buildingLimit) {
        this.buildingLimit = buildingLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerPlanet that = (PlayerPlanet) o;
        return buildingLimit == that.buildingLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingLimit);
    }

    @Override
    public String toString() {
        return "PlayerPlanet{" +
                "buildingLimit=" + buildingLimit +
                '}';
    }
}
