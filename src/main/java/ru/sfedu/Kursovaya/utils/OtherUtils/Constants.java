package ru.sfedu.Kursovaya.utils.OtherUtils;

public class Constants {
    public static final String PATH_TO_CSV="PATH_TO_CSV";
    public static final String PATH_TO_XML="PATH_TO_XML";
    public static final String PATH_TO_SQL="PATH_TO_SQL";
    public static final String MONGODB="MONGODB";
    public static final String MONGODB_TEST_SERVER="test";
    public static final String MONGODB_XML_TEST_SERVER="XMLTest";
    public static final String SQL_FILE_EXTENSION="SQL_FILE_EXTENSION";
    public static final String CSV_FILE_EXTENSION="CSV_FILE_EXTENSION";
    public static final String XML_FILE_EXTENSION="XML_FILE_EXTENSION";
    public static final String FILE_NAME_UNIT="Units";
    public static final String FILE_NAME_BUILDING="Buildings";
    public static final String FILE_NAME_ENEMY_PLANET="EnemyPlanet";
    public static final String FILE_NAME_PLAYER_PLANET="PlayerPlanet";
    public static final String FILE_NAME_ARMY="Army";
    public static final String FILE_NAME_RESOURCES="Resources";
    public static final String FILE_NAME_GAME="Game";

    public static final String JDBC_URL="JDBC_URL";
    public static final String JDBC_USERNAME="JDBC_USERNAME";
    public static final String JDBC_PASSWORD="JDBC_PASSWORD";

    public static final String CSV="CSV";
    public static final String XML="XML";
    public static final String JDBC="JDBC";

    public static final String INVALID_DATA_PROVIDER = "Invalid DataProvider type...";
    public static final String FEW_ARGUMENTS = "Too few arguments were given...";
    public static final String INVALID_METHOD_NAME = "Invalid method name was given...";
    public static final String DO_NOT_EXIST=" do not exist";
    public static final String WRONG_PARAMETER="Wrong parameter";
    public static final String IS_EMPTY=" is empty";

    public static final String PLAYER="PLAYER";
    public static final String ENEMY="ENEMY";
    public static final String SYSTEM="System";
    public static final String UNIT="Unit";
    public static final String BUILDING="Building";
    public static final String ENEMY_PLANET="EnemyPlanet";
    public static final String PLAYER_PLANET="PlayerPlanet";
    public static final String ARMY="Army";
    public static final String RESOURCES="Resources";
    public static final String GAME="Game";
    public static final String ARMYINFO="ArmyInfo";
    public static final String DEFAULT_ACTOR = "SYSTEM";
    public static final int DEFAULT_FOOD=50;
    public static final int DEFAULT_GOLD=50;
    public static final int DEFAULT_METAL=50;
    public static final int DEFAULT_ARMY_HEALTH_POINTS=100;
    public static final int DEFAULT_ARMY_ATTACK_POINTS=100;
    public static final int DEFAULT_BUILDING_LIMIT=10;

    public static final String CREATE_UNIVERSE="CREATEUNIVERSE";
    public static final String DELETE_UNIVERSE="DELETEUNIVERSE";
    public static final String GET_ENEMY_POWER="GETENEMYPOWER";
    public static final String GET_ARMY_POWER="GETARMYPOWER";
    public static final String ATTACK_PLANET="ATTACKPLANET";
    public static final String HIRE_UNIT="HIREUNIT";
    public static final String GET_BUILDINGS_INFO="GETBUILDINGSINFO";
    public static final String ADD_BUILDING="ADDBUILDING";
    public static final String REMOVE_BUILDING="REMOVEBUILDING";
    public static final String MANAGE_RESOURCES="MANAGERESOURCES";

    public static final String CREATE_UNIT="INSERT INTO unit"
            + "  (unitid, unitType, unitAttackPoints, unitHealthPoints, goldRequired, foodRequired, metalRequired) VALUES "
            + " (%d, '%s', %d, %d, %d, %d, %d);";
    public static final String CREATE_BUILDING="INSERT INTO building"
            + "  (BUILDINGID,TYPE,FOODBUFF,METALBUFF,GOLDBUFF,FOODREQUIRED ,METALREQUIRED,GOLDREQUIRED  ) VALUES "
            + " (%d, '%s', %d, %d, %d, %d, %d, %d);";
    public static final String CREATE_ENEMY_PLANET="INSERT INTO ENEMYPLANET "
            + "  (PLANETID ,PLANETNAME ,TYPE ,ENEMYHEALTHPOINTS ,ENEMYATTACKPOINTS ) VALUES "
            + " (%d, '%s', '%s', %d, %d);";
    public static final String CREATE_PLAYER_PLANET="INSERT INTO PLAYERPLANET "
            + "  (PLANETID ,PLANETNAME ,TYPE ,BUILDINGLIMIT ) VALUES "
            + " (%d, '%s', '%s', %d);";
    public static final String CREATE_ARMY="INSERT INTO ARMY "
            + " (ARMYID,RESOURCESID) VALUES "
            + " (%d,%d);";
    public static final String CREATE_ARMY_INFO="INSERT INTO ARMYINFO  "
            + "  (ARMYID ,ARMYHEALTHPOINTS ,ARMYATTACKPOINTS ) VALUES "
            + " (%d, %d, %d);";
    public static final String CREATE_ARMY_UNIT="INSERT INTO ARMY_UNIT"
            + "  (ARMYID ,UNITID, UNITCOUNT) VALUES "
            + " (%d ,%d ,%d);";
    public static final String CREATE_RESOURCES="INSERT INTO RESOURCES "
            + "  (RESOURCESID , FOOD , METAL ,GOLD , OPERATION, GAMEID) VALUES "
            + " (%d, %d, %d, %d, %d ,% d);";
    public static final String CREATE_RESOURCES_BUILDING="INSERT INTO RESOURCES_BUILDING"
            + "  (RESOURCESID  ,BUILDINGID , BUILDINGCOUNT ) VALUES "
            + " (%d ,%d ,%d);";
    public static final String CREATE_GAME="INSERT INTO GAME"
            + "  (GAMEID ,GAMENAME) VALUES "
            + " (%d ,'%s');";
    public static final String CREATE_GAME_ENEMY_PLANET="INSERT INTO GAME_ENEMYPLANET"
            + " (GAMEID ,PLANETID ) VALUES "
            + " (%d ,%d );";
    public static final String CREATE_GAME_PLAYER_PLANET="INSERT INTO GAME_PLAYERPLANET"
            + "  (GAMEID ,PLANETID ) VALUES "
            + " (%d ,%d );";

    public static final String DELETE_UNIT="DELETE FROM unit WHERE UNITID=%d;";
    public static final String DELETE_BUILDING="DELETE FROM building WHERE BUILDINGID=%d;";
    public static final String DELETE_ENEMY_PLANET="DELETE FROM ENEMYPLANET WHERE PLANETID=%d;";
    public static final String DELETE_PLAYER_PLANET="DELETE FROM PLAYERPLANET WHERE PLANETID=%d;";
    public static final String DELETE_ARMY="DELETE FROM ARMY WHERE ARMYID=%d;";
    public static final String DELETE_RESOURCES="DELETE FROM RESOURCES WHERE RESOURCESID=%d;";
    public static final String DELETE_GAME="DELETE FROM GAME WHERE GAMEID=%d;";
    public static final String DELETE_GAME_PLAYER_PLANETS="DELETE FROM GAME_PLAYERPLANET WHERE GAMEID=%d;";
    public static final String DELETE_GAME_ENEMY_PLANETS="DELETE FROM GAME_ENEMYPLANET WHERE GAMEID=%d;";
    public static final String DELETE_ARMY_UNIT="DELETE FROM ARMY_UNIT WHERE ARMYID=%d;";
    public static final String DELETE_RESOURCES_BUILDING="DELETE FROM RESOURCES_BUILDING WHERE RESOURCESID=%d;";

    public static final String SELECT_UNIT_BY_ID="SELECT * FROM unit WHERE UNITID=%d;";
    public static final String SELECT_BUILDING_BY_ID="SELECT * FROM building WHERE BUILDINGID=%d;";
    public static final String SELECT_ENEMY_PLANET_BY_ID="SELECT * FROM ENEMYPLANET WHERE PLANETID=%d;";
    public static final String SELECT_PLAYER_PLANET_BY_ID="SELECT * FROM PLAYERPLANET WHERE PLANETID=%d;";
    public static final String SELECT_ARMY_BY_ID="SELECT * FROM ARMY WHERE ARMYID=%d;";
    public static final String SELECT_ARMY_INFO_BY_ID="SELECT * FROM ARMYINFO WHERE ARMYID=%d;";
    public static final String SELECT_ARMY_UNIT_BY_ID="SELECT * FROM ARMY_UNIT WHERE ARMYID=%d;";
    public static final String SELECT_RESOURCES_BY_ID="SELECT * FROM RESOURCES WHERE RESOURCESID=%d;";
    public static final String SELECT_RESOURCES_BUILDING_BY_ID="SELECT * FROM RESOURCES_BUILDING WHERE RESOURCESID=%d;";
    public static final String SELECT_GAME_BY_ID="SELECT * FROM GAME WHERE GAMEID=%d;";
    public static final String SELECT_GAME_ENEMY_PLANET_BY_ID="SELECT * FROM GAME_ENEMYPLANET WHERE GAMEID=%d;";
    public static final String SELECT_GAME_PLAYER_PLANET_BY_ID="SELECT * FROM GAME_PLAYERPLANET WHERE GAMEID=%d;";

    public static final String DROP_ALL_TABLES="drop table ARMY ,ARMYINFO ,ARMY_UNIT ,BUILDING ,ENEMYPLANET ,GAME ,GAME_ENEMYPLANET ,GAME_PLAYERPLANET ,PLAYERPLANET ,RESOURCES ,RESOURCES_BUILDING ,UNIT";
    public static final String DROP_DEFAULT="drop table BUILDING ,ENEMYPLANET ,PLAYERPLANET ,UNIT ";
    public static final String DROP_ALL_GAME_PLANETS="drop table GAME_ENEMYPLANET ,GAME_PLAYERPLANET ";
    public static final String DROP_RESOURCES_BUILDINGS="drop table RESOURCES_BUILDING ";
    public static final String DROP_ARMY_UNITS="drop table ARMY_UNIT ";

    public static final String SELECT_UNIT="SELECT * FROM unit;";
    public static final String SELECT_BUILDING="SELECT * FROM building;";
    public static final String SELECT_ENEMY_PLANET="SELECT * FROM ENEMYPLANET;";
    public static final String SELECT_PLAYER_PLANET="SELECT * FROM PLAYERPLANET;";
    public static final String SELECT_ARMY="SELECT * FROM ARMY;";
    public static final String SELECT_RESOURCES="SELECT * FROM RESOURCES;";
    public static final String SELECT_GAME="SELECT * FROM GAME;";

    public static final String UPDATE_UNIT_BY_ID="UPDATE UNIT SET " +
            "UNITTYPE = %s," +
            "UNITATTACKPOINTS = %d," +
            "UNITHEALTHPOINTS = %d," +
            "GOLDREQUIRED = %d," +
            "FOODREQUIRED = %d," +
            "METALREQUIRED = %d " +
            "WHERE UNITID = %d;";
    public static final String UPDATE_BUILDING="UPDATE BUILDING SET " +
            "TYPE = %s," +
            "FOODBUFF = %d," +
            "METALBUFF = %d," +
            "GOLDBUFF = %d," +
            "FOODREQUIRED = %d," +
            "METALREQUIRED = %d " +
            "GOLDREQUIRED = %d" +
            "WHERE BUILDINGID = %d;";
    public static final String UPDATE_ENEMY_PLANET="UPDATE ENEMYPLANET SET " +
            "PLANETNAME = ?," +
            "TYPE = ?," +
            "ENEMYHEALTHPOINTS = %d," +
            "ENEMYATTACKPOINTS = %d" +
            "WHERE PLANETID = %d;";
    public static final String UPDATE_PLAYER_PLANET="UPDATE PLAYERPLANET SET " +
            "PLANETNAME = ?," +
            "TYPE = ?," +
            "BUILDINGLIMIT = %d" +
            "WHERE PLANETID = %d;";
    public static final String UPDATE_ARMY_INFO="UPDATE ARMYINFO SET " +
            "ARMYHEALTHPOINTS = %d," +
            "ARMYATTACKPOINTS = %d" +
            "WHERE ARMYID = %d;";
    public static final String UPDATE_ARMY_UNIT="UPDATE ARMY_UNIT SET " +
            "UNITID = %d," +
            "UNITCOUNT = %d" +
            "WHERE ARMYID = %d;";
    public static final String UPDATE_RESOURCES="UPDATE RESOURCES SET " +
            "FOOD = %d," +
            "METAL = %d," +
            "GOLD = %d," +
            "OPERATION = %d," +
            "GAMEID = %d" +
            "WHERE RESOURCESID = %d;";
    public static final String UPDATE_RESOURCES_BUILDING="UPDATE RESOURCES_BUILDING SET " +
            "BUILDINGID = %d," +
            "BUILDINGCOUNT = %d" +
            "WHERE RESOURCESID = %d;";
    public static final String UPDATE_GAME="UPDATE GAME SET " +
            "GAMENAME = '%s'" +
            "WHERE GAMEID = %d;";
    public static final String UPDATE_GAME_ENEMY_PLANET="UPDATE GAME_ENEMYPLANET SET " +
            "PLANETID = %d" +
            "WHERE GAMEID = %d;";
    public static final String UPDATE_GAME_PLAYER_PLANET="UPDATE GAME_PLAYERPLANET SET " +
            "PLANETID = %d" +
            "WHERE GAMEID = %d;";





    public static final String ARMY_INFO_FIELDS_DELIMITER="::";
    public static final String ARMY_ELEMENTS_DELIMITER="*";
    public static final String ARMY_FIELDS_DELIMITER="'";
    public static final String BUILDING_FIELDS_DELIMITER="@";
    public static final String BUILDING_ELEMENTS_DELIMITER="#";
    public static final String ENEMY_PLANET_ELEMENTS_DELIMITER="<";
    public static final String ENEMY_PLANET_FIELDS_DELIMITER=">";
    public static final String PLAYER_PLANET_ELEMENTS_DELIMITER="}";
    public static final String PLAYER_PLANET_FIELDS_DELIMITER="!";
    public static final String RESOURCES_ELEMENTS_DELIMITER="~";
    public static final String RESOURCES_FIELDS_DELIMITER="&";
    public static final String UNIT_ELEMENTS_DELIMITER="@";
    public static final String UNIT_FIELDS_DELIMITER="#";
    public static final String NULL="null";
    public static final String KEY="key";

    public static final String GAME_OVER="Game over";
    public static final String YOUR_ARMY_IS_DEAD="Your army is dead";
    public static final String UNIVERSE_DELETED="Universe deleted";
    public static final String YOUR_RESOURCES="Your resources:";
    public static final String FOOD="Food:";
    public static final String METAL="Metal:";
    public static final String GOLD="Gold:";
    public static final String YOUR_ARMY_POWER="Your army power:";
    public static final String HEALTH="Health:";
    public static final String ATTACK="Attack:";
    public static final String YOUR_PLANETS="Your planets:";
    public static final String ID="Id:";
    public static final String PLANET_NAME=" Planet name:";
    public static final String BUILDING_LIMIT=" Building limit:";
    public static final String ENEMY_PLANETS="Enemy planets:";
    public static final String BUILDING_LIST="Building list ";
    public static final String VICTORY="Victory";
}
