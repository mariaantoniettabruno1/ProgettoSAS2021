package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//si fa carico di preparare le query andando a specificare per ciascun item i parametri
//gestisce anche le chiavi che sono state generate in seguito all'esecuzione della query in questione
public interface BatchUpdateHandler {
    void handleBatchItem(PreparedStatement ps, int i) throws SQLException;

    void handleGeneratedIds(ResultSet keys, int count) throws SQLException;
}
