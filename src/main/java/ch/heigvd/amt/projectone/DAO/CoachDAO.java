package ch.heigvd.amt.projectone.DAO;

import ch.heigvd.amt.projectone.business.IAuthentification;
import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;
import ch.heigvd.amt.projectone.model.Team;

import javax.annotation.Resource;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CoachDAO implements ICoachDAO {

    @Resource(lookup = "java:/jdbc/fmDS")
    DataSource dataSource;



    @Override
    public Coach create(Coach entity) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO amt_coaches (USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, ISADMIN) VALUES (?, ?, ?, ?,?)");
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getFirstName());
            statement.setString(4, entity.getLastName());
            statement.setBoolean(5, entity.getIsAdmin());
            statement.execute();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public Coach findById(String username) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT USERNAME, PASSWORD, FIRST_NAME, LAST_NAME,ISADMIN FROM amt_coaches WHERE USERNAME = ?");

            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();
            if (!hasRecord) {
                // code d erreur
            }
            Coach existingCoach = Coach.builder()
                    .username(rs.getString(1))
                    .password(rs.getString(2))
                    .firstName(rs.getString(3))
                    .lastName(rs.getString(4))
                    .isAdmin(rs.getBoolean(5))
                    .build();
            return existingCoach;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }

    }

    @Override
    public List<Coach> findAllCoach(int currentPage, int numOfRecords) {
        List<Coach> coaches = new ArrayList<>();
        Connection con = null;
        int start = currentPage * numOfRecords - numOfRecords;

        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,ISADMIN FROM amt_coaches LIMIT ?,?");
            statement.setInt(1,start);
            statement.setInt(2,numOfRecords);

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Coach existingCoach = Coach.builder()
                        .username(rs.getString(1))
                        .password(rs.getString(2))
                        .firstName(rs.getString(3))
                        .lastName(rs.getString(4))
                        .isAdmin(rs.getBoolean(5))
                        .build();
                coaches.add(existingCoach);
            }
            return coaches;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    public int getNumberOfRows(){
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM amt_coaches");

            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();
            if (hasRecord) {
                return rs.getInt(1);
            }
            return 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }


    @Override
    public void update(Coach entity) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE amt_coaches SET USERNAME=?, PASSWORD=?, FIRST_NAME=?, LAST_NAME=?, ISADMIN=? WHERE USERNAME = ?");
            statement.setString(1, entity.getUsername());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getFirstName());
            statement.setString(4, entity.getLastName());
            statement.setBoolean(5, entity.getIsAdmin());
            int numberOfUpdatedUsers = statement.executeUpdate();
            if (numberOfUpdatedUsers != 1) {
                // erreur
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void deleteById(String username) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM amt_coaches WHERE USERNAME = ?");
            statement.setString(1, username);
            int numberOfDeletedUsers = statement.executeUpdate();
            if (numberOfDeletedUsers != 1) {
               // throw new KeyNotFoundException("Could not find user with username = " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    private void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


