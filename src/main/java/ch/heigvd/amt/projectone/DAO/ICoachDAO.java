package ch.heigvd.amt.projectone.DAO;

import ch.heigvd.amt.projectone.model.Coach;
import javax.ejb.Local;
import java.util.List;

@Local
public interface ICoachDAO extends IDAO<String, Coach>  {
    /**
     *
     * @param currentPage
     * @param numOfRecords
     * @return une liste de tout les coachs se trouvant dans la base de donnée
     * avec un debut et le nombre ligne.
     */
    List<Coach> findAllCoach(int currentPage, int numOfRecords);

    /**
     *
     * @return le nombre de coach au total
     */
    public int getNumberOfRows();
}
