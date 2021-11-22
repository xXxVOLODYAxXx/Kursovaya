package ru.sfedu.Kursovaya.utils.DataProviders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.Beans.Unit;
import ru.sfedu.Kursovaya.utils.JDBCH2Utils;

import java.sql.*;

public class JDBCDataProvider {
    private static final Logger log = LogManager.getLogger(JDBCDataProvider.class);
    private Connection connection=null;
    private static final String createTableSQL = "create table units (\r\n"
            + "  id  Long(1000) primary key,\r\n"
            + "  unitType varchar(50),\r\n"
            + "  unitAttackPoints int(50),\r\n"
            + "  unitHealthPoints int(50),\r\n"
            + "  goldRequired int(50),\r\n"
            + "  metalRequired int(50),\r\n"
            + "  foodRequired int(50),\r\n"
            + "  );";
    private static final String INSERT_UNITS_SQL = "INSERT INTO units"
            + "  (id, unitType, unitAttackPoints, unitHealthPoints, goldRequired, metalRequired, foodRequired) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?);";
    private static final String READ_UNIT_SQL = "select id,unitType,unitAttackPoints,unitHealthPoints,goldRequired,metalRequired,foodRequired from units where id =";
    private static final String UPDATE_UNITS_SQL = "update units set " +
            "unitType = ?," +
            "unitAttackPoints = ?," +
            "unitHealthPoints = ?," +
            "goldRequired = ?," +
            "metalRequired = ?," +
            "foodRequired = ? " +
            "where id = ?;";
    private static final String DELETE_UNITS_SQL = "delete from units where id = ";

    private Connection initConnection(){
        connection = JDBCH2Utils.getConnection();
        return connection;
    }
    private void closeConnection() throws SQLException {
        connection.close();
    }
    public void createTable() throws SQLException {
        try (Statement statement = initConnection().createStatement();) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            log.info(e);
        }finally {
            closeConnection();
        }
    }

    public void insertUnit(Unit unit) throws SQLException {
        try (PreparedStatement preparedStatement = initConnection().prepareStatement(INSERT_UNITS_SQL)) {
                preparedStatement.setLong(1, unit.getUnitId());
                preparedStatement.setString(2, unit.getUnitType());
                preparedStatement.setInt(3, unit.getUnitAttackPoints());
                preparedStatement.setInt(4, unit.getUnitHealthPoints());
                preparedStatement.setInt(5, unit.getGoldRequired());
                preparedStatement.setInt(6, unit.getMetalRequired());
                preparedStatement.setInt(7, unit.getFoodRequired());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.info(e);
        }finally {
            closeConnection();
        }
    }
    public Unit readUnitById(Long id) throws SQLException {
        Unit unit=new Unit();
        try (PreparedStatement preparedStatement = initConnection().prepareStatement(READ_UNIT_SQL+Long.toString(id));) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    unit.setUnitId(rs.getLong("id"));
                    unit.setUnitType(rs.getString("unitType"));
                    unit.setUnitAttackPoints(rs.getInt("unitAttackPoints"));
                    unit.setUnitHealthPoints(rs.getInt("unitHealthPoints"));
                    unit.setGoldRequired(rs.getInt("goldRequired"));
                    unit.setMetalRequired(rs.getInt("metalRequired"));
                    unit.setFoodRequired(rs.getInt("foodRequired"));
                }
        } catch (SQLException e) {
            log.info(e);
        }finally {
            closeConnection();
        }
        return unit;
    }
    public void updateUnitById(Unit unit) throws SQLException {
        try (PreparedStatement preparedStatement = initConnection().prepareStatement(UPDATE_UNITS_SQL)) {
            preparedStatement.setString(1, unit.getUnitType());
            preparedStatement.setInt(2, unit.getUnitAttackPoints());
            preparedStatement.setInt(3, unit.getUnitHealthPoints());
            preparedStatement.setInt(4, unit.getGoldRequired());
            preparedStatement.setInt(5, unit.getMetalRequired());
            preparedStatement.setInt(6, unit.getFoodRequired());
            preparedStatement.setLong(7, unit.getUnitId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.info(e);
        }finally {
            closeConnection();
        }
    }
    public void deleteUnitById(Long id) throws SQLException {
        try (Statement statement = initConnection().createStatement();) {
            statement.execute(DELETE_UNITS_SQL+id);
        } catch (SQLException e) {
            log.info(e);
        }finally {
            closeConnection();
        }
    }

}
