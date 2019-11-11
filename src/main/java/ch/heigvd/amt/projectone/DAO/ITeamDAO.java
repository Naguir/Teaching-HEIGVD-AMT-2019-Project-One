package ch.heigvd.amt.projectone.DAO;

import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;
import ch.heigvd.amt.projectone.model.Team;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ITeamDAO extends IDAO<String, Team> {

    public void addCoach(String c,String t);
    public List<Team> findMyTeam(int currentPage, int recordsPerPage, String coach);
    public List<Team> findMyTeamByCoach(String coach);
    public List<Team> findAllTeam(int currentPage, int numOfRecords);
    public int getNumberOfRows();
    public int getNumberOfRowsForMyTeam(String coach);
}

