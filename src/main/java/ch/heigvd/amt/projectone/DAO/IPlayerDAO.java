package ch.heigvd.amt.projectone.DAO;

import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;
import ch.heigvd.amt.projectone.model.Team;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPlayerDAO extends IDAO<Integer, Player>  {
    public List<Player> findMyTeamPlayers(int currentPage, int recordsPerPage,Coach coach);
    public List<Player> findPlayers(int currentPage, int numOfRecords);
    public int getNumberOfRows();
    public int getNumberOfRowsForMyTeam(Coach coach);

}
