package ru.sfedu.Kursovaya;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.*;
import ru.sfedu.Kursovaya.api.DataProviders.AbstractDataProvider;
import ru.sfedu.Kursovaya.api.DataProviders.JDBCDataProvider;
import ru.sfedu.Kursovaya.api.DataProviders.XMLDataProvider;
import ru.sfedu.Kursovaya.utils.OtherUtils.BeansGenerator;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;
import ru.sfedu.Kursovaya.api.DataProviders.CSVDataProvider;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;



public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    private static Game game=new Game();
    private static final Resources resources = new Resources();
    private static final Army army = new Army();
    private static BeansGenerator beansGenerator = null;
    static {
        try {
            beansGenerator = new BeansGenerator();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        checkArgumentsCount(args);
        AbstractDataProvider dataProvider = getDataProvider(args[0]);
        switch (args[1].toUpperCase(Locale.ROOT)){
            case Constants.CREATE_UNIVERSE:{
                if (args.length > 2) {
                    Long id = Long.parseLong(args[2]);
                    switch(args[0].toUpperCase(Locale.ROOT)) {
                        case(Constants.CSV): {
                            beansGenerator.defaultBeansCSV();
                            game=beansGenerator.defaultPresetCSV(Long.parseLong(args[2]), army, resources, game);
                            dataProvider.createUniverse(game, game.getResources(), game.getResources().getArmy());
                            break;
                        }
                        case(Constants.XML): {
                            beansGenerator.defaultBeansXML();
                            game=beansGenerator.defaultPresetXML(Long.parseLong(args[2]), army, resources, game);
                            dataProvider.createUniverse(game, game.getResources(), game.getResources().getArmy());
                            break;
                        }
                        case(Constants.JDBC): {
                            beansGenerator.defaultBeansJDBC();
                            game=beansGenerator.defaultPresetJDBC(Long.parseLong(args[2]), army, resources, game);
                            dataProvider.createUniverse(game, game.getResources(), game.getResources().getArmy());
                            break;
                        }
                    }
                    log.info(Constants.YOUR_RESOURCES);
                    log.info(Constants.FOOD + game.getResources().getFood());
                    log.info(Constants.METAL + game.getResources().getMetal());
                    log.info(Constants.GOLD + game.getResources().getGold());
                    log.info(Constants.YOUR_ARMY_POWER);
                    log.info(Constants.HEALTH + game.getResources().getArmy().getArmyInfo().getArmyHealthPoints() + Constants.ATTACK + game.getResources().getArmy().getArmyInfo().getArmyAttackPoints());
                    log.info(Constants.YOUR_PLANETS);
                    game.getPlayerPlanetList().forEach(x -> {
                        log.info(Constants.ID + x.getPlanetId() + Constants.PLANET_NAME + x.getPlanetName() + Constants.BUILDING_LIMIT + x.getBuildingLimit());
                    });
                    log.info(Constants.ENEMY_PLANETS);
                    game.getEnemyPlanetList().forEach(x -> {
                        log.info(Constants.ID + x.getPlanetId() + Constants.PLANET_NAME + x.getPlanetName());
                    });

                } else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.DELETE_UNIVERSE:{
                if (args.length > 2) {
                    dataProvider.deleteUniverse(Long.parseLong(args[2]));
                    log.info(Constants.UNIVERSE_DELETED);
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.GET_ARMY_POWER:{
                if (args.length > 2) {
                    game.getResources().getArmy().setArmyInfo(dataProvider.getArmyPower(Long.parseLong(args[2])));
                    log.info(Constants.HEALTH + game.getResources().getArmy().getArmyInfo().getArmyHealthPoints());
                    log.info(Constants.ATTACK + game.getResources().getArmy().getArmyInfo().getArmyAttackPoints());
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.GET_ENEMY_POWER:{
                if (args.length > 3) {
                    game.getEnemyPlanetList().add(dataProvider.getEnemyPower(Long.parseLong(args[2]), Long.parseLong(args[3])));
                    log.info(Constants.HEALTH + game.getEnemyPlanetList().get(Integer.parseInt(args[2])).getEnemyHealthPoints());
                    log.info(Constants.ATTACK + game.getEnemyPlanetList().get(Integer.parseInt(args[2])).getEnemyAttackPoints());
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.ATTACK_PLANET:{
                if (args.length > 3) {
                    Boolean result = dataProvider.attackPlanet(Long.parseLong(args[2]), Long.parseLong(args[3]));
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.MANAGE_RESOURCES:{
                if(args.length>2){
                if (args.length > 4){
                    dataProvider.manageResources(Long.parseLong(args[2]),Integer.parseInt(args[3]),Long.parseLong(args[4]));
                } else {
                    dataProvider.manageResources(Long.parseLong(args[2]),Integer.parseInt(args[3]));
                }
                break;
            }else {
                log.info(Constants.FEW_ARGUMENTS);
            }}
            case Constants.ADD_BUILDING:{
                if (args.length > 3) {
                    dataProvider.addBuilding(Long.parseLong(args[2]), Long.parseLong(args[3]));
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.REMOVE_BUILDING:{
                if (args.length > 3) {
                    dataProvider.removeBuilding(Long.parseLong(args[2]), Long.parseLong(args[3]));
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.HIRE_UNIT:{
                if (args.length > 3) {
                    dataProvider.hireUnit(Long.parseLong(args[2]), Long.parseLong(args[3]));
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            case Constants.GET_BUILDINGS_INFO:{
                if (args.length > 3) {
                    dataProvider.getBuildingsInfo(Long.parseLong(args[2]));
                }else {
                    log.info(Constants.FEW_ARGUMENTS);
                }
                break;
            }
            default: {
                log.error(Constants.INVALID_METHOD_NAME);
                System.exit(0);
            }
        }
    }

    private static AbstractDataProvider getDataProvider(String dpType) throws IOException, JAXBException, CsvRequiredFieldEmptyException, SQLException, CsvDataTypeMismatchException {
        switch(dpType.toUpperCase(Locale.ROOT)) {
            case(Constants.CSV): {
                return new CSVDataProvider();
            }
            case(Constants.XML): {
                return new XMLDataProvider();
            }
            case(Constants.JDBC): {
                return new JDBCDataProvider();
            }
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