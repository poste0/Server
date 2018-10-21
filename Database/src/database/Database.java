package database;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Сергей on 21.09.2017.
 */
public interface Database {
   Participant getInfo(int id) throws Exception;
   List<Papers> show() throws Exception;
   List<Participant>showParticipants() throws Exception;
    void add(Participant participant) throws SQLException;
    void delete(int id) throws SQLException;
    List<Papers> getPapers(int id) throws Exception;
    void deletePapers(int id) throws SQLException;
    void addPapers(Papers paper) throws SQLException;

}
