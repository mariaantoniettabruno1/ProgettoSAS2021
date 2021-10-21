package persistence;

import businesslogic.user.User;

import java.sql.*;

public class PersistenceManager {

    private static String url = "jdbc:mysql://localhost:3306/catering?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "";

    public static void testSQLConnection() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                long id = rs.getInt("id");
                String name = rs.getString("username");
                System.out.println(name + " ha id = " + id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    //esegue una query e fa gestire ad un gestore esterno il risultato
    public static void executeQuery(String query, ResultHandler handler) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                handler.handle(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //permette di parametrizzare la query creandone multiple istanze con singoli valori diversi
    public static int[] executeBatchUpdate(String parametrizedQuery, int itemNumber, BatchUpdateHandler handler) {
        int[] result = new int[0];
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                //prende la stringa parametrizzata e gli chiediamo di restituirci gli id autoincrementanti
                // che sono stati assegnati durante l'inserimento
                PreparedStatement ps = conn.prepareStatement(parametrizedQuery, Statement.RETURN_GENERATED_KEYS);
        ) {
            for (int i = 0; i < itemNumber; i++) {
                //per ciascuna item che abbiamo a disposizione richiamo handleBatchItem
                //per ciascuno di questi item dimmi i parametri che valori hanno
                handler.handleBatchItem(ps, i);
                //aggiungo il risultato dell'unione tra stringa parametrizzata e i parametri ad una query
                // che non viene ancora eseguita
                ps.addBatch();
            }

            result = ps.executeBatch();
            //gli chiedo eventuali chiavi generate
            ResultSet keys = ps.getGeneratedKeys();
            int count = 0;
            while (keys.next()) {
                handler.handleGeneratedIds(keys, count);
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    return result;
    }


}

