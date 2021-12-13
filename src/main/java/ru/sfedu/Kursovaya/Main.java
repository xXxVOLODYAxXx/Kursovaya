package ru.sfedu.Kursovaya;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Army;
import ru.sfedu.Kursovaya.Beans.Game;
import ru.sfedu.Kursovaya.Beans.Resources;
import ru.sfedu.Kursovaya.api.DataProviders.AbstractDataProvider;
import ru.sfedu.Kursovaya.api.DataProviders.XMLDataProvider;
import ru.sfedu.Kursovaya.utils.OtherUtils.BeansGenerator;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;
import ru.sfedu.Kursovaya.api.DataProviders.CSVDataProvider;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Locale;


public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    private static BeansGenerator beansGenerator = null;
    static {
        try {
            beansGenerator = new BeansGenerator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        checkArgumentsCount(args);
        AbstractDataProvider dataProvider = getDataProvider(args[0]);
        switch (args[1].toUpperCase(Locale.ROOT)){
            case Constants.CREATE_UNIVERSE:{
                Long id = Long.parseLong(args[2]);
                Game game = new Game();
                Resources resources = new Resources();
                Army army = new Army();
                beansGenerator.defaultBeans();
                beansGenerator.defaultPreset(id,army,resources,game);
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
                log.info(dataProvider.attackPlanet(Long.parseLong(args[2]),Long.parseLong(args[3])));
                break;
            }
            case Constants.MANAGE_RESOURCES:{
                if (args[4]!=null){
                    dataProvider.manageResources(Long.parseLong(args[2]),Integer.parseInt(args[3]),Long.parseLong(args[4]));
                } else {
                    dataProvider.manageResources(Long.parseLong(args[2]),Integer.parseInt(args[3]));
                }
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

    private static AbstractDataProvider getDataProvider(String dpType) throws IOException, JAXBException {
        switch(dpType.toUpperCase(Locale.ROOT)) {
            case(Constants.CSV): {
                return new CSVDataProvider();
            }
            case(Constants.XML): {
                return new XMLDataProvider();
            }
            /**case(Constants.DB): {
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