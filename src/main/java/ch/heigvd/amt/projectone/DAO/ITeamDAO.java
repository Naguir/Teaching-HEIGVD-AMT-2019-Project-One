package ch.heigvd.amt.projectone.DAO;

import ch.heigvd.amt.projectone.model.Team;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ITeamDAO extends IDAO<String, Team> {

    /**
     *
     * @param c
     * @param t
     * Ajoute un coach à l'équipe sélectionner
     */
    public void addCoach(String c,String t);

    /**
     *
     * @param currentPage
     * @param recordsPerPage
     * @param coach
     * @return le ou les équipes du coach
     */
    public List<Team> findMyTeam(int currentPage, int recordsPerPage, String coach);
    public List<Team> findMyTeamByCoach(String coach);

    /**
     *
     * @param currentPage
     * @param numOfRecords
     * @return toutes les equipes
     */
    public List<Team> findAllTeam(int currentPage, int numOfRecords);

    /**
     *
     * @return le nombre total d'equipe
     */
    public int getNumberOfRows();

    /**
     *
     * @param coach
     * @return le nombre d'equipe que possede le coach
     */
    public int getNumberOfRowsForMyTeam(String coach);
}

