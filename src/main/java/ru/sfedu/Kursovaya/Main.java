package ru.sfedu.Kursovaya;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Army;
import ru.sfedu.Kursovaya.Beans.ArmyInfo;
import ru.sfedu.Kursovaya.Beans.Game;
import ru.sfedu.Kursovaya.Beans.Resources;
import ru.sfedu.Kursovaya.utils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.Constants;
import ru.sfedu.Kursovaya.utils.DataProviders.CSVDataProvider;
import ru.sfedu.Kursovaya.utils.DataProviders.DataProvider;
import ru.sfedu.Kursovaya.utils.DataProviders.JDBCDataProvider;
import ru.sfedu.Kursovaya.utils.DataProviders.XMLDataProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Locale;


public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        checkArgumentsCount(args);
        DataProvider dataProvider = getDataProvider(args[0]);
        switch (args[1].toUpperCase(Locale.ROOT)){
            case Constants.CREATE_UNIVERSE:{
                Long id = Long.parseLong(args[2]);
                Game game = new Game();
                Resources resources = new Resources();
                Army army = new Army();
                ArmyInfo armyInfo = new ArmyInfo();
                armyInfo.setArmyAttackPoints(Constants.DEFAULT_ARMY_ATTACK_POINTS);
                armyInfo.setArmyHealthPoints(Constants.DEFAULT_ARMY_HEALTH_POINTS);
                army.setArmyId(id);
                army.setArmyInfo(armyInfo);
                resources.setResourcesId(id);
                resources.setFood(Constants.DEFAULT_FOOD);
                resources.setGold(Constants.DEFAULT_GOLD);
                resources.setMetal(Constants.DEFAULT_METAL);
                resources.setArmy(army);
                game.setGameId(id);
                game.setResources(resources);
                game.setPlayerPlanetList(dataProvider.getPlayerPlanetList());
                game.setEnemyPlanetList(dataProvider.getEnemyPlanetList());
                dataProvider.createUniverse(game,resources,army);
                break;
            }
            case Constants.DELETE_UNIVERSE:{
                dataProvider.deleteUniverse(Long.parseLong(args[2]));
                break;
            }
            case Constants.GET_ARMY_POWER:{
                dataProvider.getArmyPower(Long.parseLong(args[2]));
                break;
            }
            case Constants.GET_ENEMY_POWER:{
                dataProvider.getEnemyPower(Long.parseLong(args[2]),Long.parseLong(args[3]));
                break;
            }
            case Constants.ATTACK_PLANET:{
                dataProvider.attackPlanet(Long.parseLong(args[2]),Long.parseLong(args[3]));
                break;
            }
            case Constants.MANAGE_RESOURCES:{
                //dataProvider.manageResources();
                break;
            }
            case Constants.ADD_BUILDING:{
                dataProvider.addBuilding(Long.parseLong(args[2]),Long.parseLong(args[3]));
                break;
            }
            case Constants.REMOVE_BUILDING:{
                dataProvider.removeBuilding(Long.parseLong(args[2]),Long.parseLong(args[3]));
                break;
            }
            case Constants.HIRE_UNIT:{
                dataProvider.hireUnit(Long.parseLong(args[2]),Long.parseLong(args[3]));
                break;
            }
            case Constants.GET_BUILDINGS_INFO:{
                dataProvider.getBuildingsInfo(Long.parseLong(args[2]));
                break;
            }
            default: {
                log.error(Constants.INVALID_METHOD_NAME);
                System.exit(0);
            }
        }
    }

    private static DataProvider getDataProvider(String dpType) throws IOException, JAXBException {
        switch(dpType.toUpperCase(Locale.ROOT)) {
            case(Constants.CSV): {
                return new CSVDataProvider();
            }
            /**case(Constants.XML): {
                return new XMLDataProvider();
            }
            case(Constants.DB): {
                return new JDBCDataProvider();
            }*/
            default: {
                log.error(Constants.INVALID_DATA_PROVIDER);
                System.exit(0);
            }
        }
        return null;
    }

    private static void checkArgumentsCount(String[] args) {
        if (args.length < 2) {
            log.error(Constants.FEW_ARGUMENTS);
            System.exit(0);
        }
    }
}