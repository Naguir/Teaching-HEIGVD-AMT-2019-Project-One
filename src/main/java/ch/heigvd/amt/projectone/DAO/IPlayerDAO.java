package ch.heigvd.amt.projectone.DAO;

import ch.heigvd.amt.projectone.model.Coach;
import ch.heigvd.amt.projectone.model.Player;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPlayerDAO extends IDAO<Integer, Player>  {
    /**
     *
     * @param currentPage
     * @param recordsPerPage
     * @param coach
     * @return la liste de joueur que le coach possede
     */
    public List<Player> findMyTeamPlayers(int currentPage, int recordsPerPage,Coach coach);

    /**
     *
     * @param currentPage
     * @param numOfRecords
     * @return la liste de tout les joueurs
     */
    public List<Player> findPlayers(int currentPage, int numOfRecords);

    /**
     *
     * @return le nombre total de joueur
     */
    public int getNumberOfRows();

    /**
     *
     * @param coach
     * @return le nombre total de joueur que le coach possede
     */
    public int getNumberOfRowsForMyTeam(Coach coach);

}
