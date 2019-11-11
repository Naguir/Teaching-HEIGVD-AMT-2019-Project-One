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
public class PlayerDAO implements IPlayerDAO {

    @Resource(lookup = "java:/jdbc/fmDS")
    DataSource dataSource;

    @EJB
    IAuthentification authentification;

    @Override
    public Player create(Player entity) throws DuplicateKeyException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("INSERT INTO amt_players (FIRST_NAME, LAST_NAME, POSITION, NUMBER, NAME_TEAMS) VALUES (?,?,?,?,?)");
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getPosition());
            statement.setInt(4, entity.getNumber());
            statement.setString(5, entity.getTeam().getName());
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
    public Player findById(Integer id) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT FIRST_NAME,LAST_NAME,POSITION,NUMBER,NAMETEAM, LOCATION, CREATIONDATE FROM amt_players,amt_teams WHERE ID = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            boolean hasRecord = rs.next();
            if (!hasRecord) {
                // code d erreur
            }
            Player existingPlayer = Player.builder()
                    .firstName(rs.getString(1))
                    .lastName(rs.getString(2))
                    .position(rs.getString(3))
                    .number(rs.getInt(4))
                    .team(Team.builder()
                            .name(rs.getString(5))
                            .location(rs.getString(6))
                            .dateCreation(rs.getDate(7))
                            .build())
                    .build();
            return existingPlayer;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public List<Player> findMyTeamPlayers(int currentPage, int recordsPerPage,Coach coach){
        List<Player> players = new ArrayList<>();
        Connection con = null;
        int start = currentPage * recordsPerPage - recordsPerPage;

        try{
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT ID,FIRST_NAME,LAST_NAME,POSITION,NUMBER,NAME_TEAMS,CREATIONDATE,LOCATION FROM amt_players " +
                    "INNER JOIN amt_teams ON amt_players.name_teams = amt_teams.name INNER JOIN amt_teams_coach ON amt_players.name_teams = amt_teams_coach.team_id  WHERE amt_teams_coach.coach_id = ? LIMIT ?,?");
            statement.setString(1,coach.getUsername());
            statement.setInt(2,start);
            statement.setInt(3,recordsPerPage);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Player player = Player.builder()
                        .id(rs.getInt(1))
                        .firstName(rs.getString(2))
                        .lastName(rs.getString(3))
                        .position(rs.getString(4))
                        .number(rs.getInt(5))
                        .team(Team.builder()
                                .name(rs.getString(6))
                                .location(rs.getString(8))
                                .dateCreation(rs.getDate(7))
                                .build())
                        .build();
                players.add(player);
            }
            return players;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        }finally {
            closeConnection(con);
        }
    }

    @Override
    public List<Player> findPlayers(int currentPage, int recordsPerPage){
        List<Player> players = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;


            Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT ID,FIRST_NAME,LAST_NAME,POSITION,NUMBER,NAME_TEAMS,CREATIONDATE,LOCATION FROM amt_players " +
                    "INNER JOIN amt_teams ON amt_players.name_teams = amt_teams.name LIMIT ?,?");

            statement.setInt(1,start);
            statement.setInt(2,recordsPerPage);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Player player = Player.builder()
                        .id(rs.getInt(1))
                        .firstName(rs.getString(2))
                        .lastName(rs.getString(3))
                        .position(rs.getString(4))
                        .number(rs.getInt(5))
                        .team(Team.builder()
                                .name(rs.getString(6))
                                .dateCreation(rs.getDate(7))
                                .location(rs.getString(8))
                                .build())
                        .build();
                System.out.println(player.getFirstName() + " " + player.getId());
                players.add(player);
            }
            return players;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new Error(e);
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public int getNumberOfRows(){
        Connection con = null;

        try {
                con = dataSource.getConnection();
                PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM amt_players");

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
    public int getNumberOfRowsForMyTeam(Coach coach){
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM amt_players INNER JOIN amt_teams ON amt_players.name_teams = amt_teams.name INNER JOIN amt_teams_coach ON amt_players.name_teams = amt_teams_coach.team_id  WHERE amt_teams_coach.coach_id = ?");
            statement.setString(1,coach.getUsername());
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
    public void update(Player entity) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("UPDATE amt_players SET FIRST_NAME=?, LAST_NAME=?, POSITION=?,NUMBER=?,NAMETEAM=? WHERE ID = ?");
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getPosition());
            statement.setInt(4, entity.getNumber());
            statement.setString(5, entity.getTeam().getName());
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
    public void deleteById(Integer id) {
        Connection con = null;
        System.out.println("ahahah-"+id);
        try {
            con = dataSource.getConnection();
            PreparedStatement statement = con.prepareStatement("DELETE FROM amt_players WHERE ID = ?");
            statement.setInt(1, id);
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
