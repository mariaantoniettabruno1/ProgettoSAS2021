package businesslogic.kitchentask;

import businesslogic.user.User;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummarySheet {

    private int id_summarySheet;
    private ArrayList<CookingTask> sheetCookingTask;
   private User owner;
    public SummarySheet() {
        this.sheetCookingTask = new ArrayList<>();
        this.id_summarySheet = 0;
    }
    public String toString(){
        String result = "Cooking Task + \n";
        for(CookingTask ctsk : sheetCookingTask){
            result += ctsk.toString();
            result +="\n";
        }
        return result;
    }


    public static void saveNewSummarySheet(SummarySheet s) {
        String summarySheetInsert = "INSERT INTO catering.summarysheet (owner_id) VALUES (?);";
        int[] result = PersistenceManager.executeBatchUpdate(summarySheetInsert, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, s.owner.getId());

            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                // should be only one
                if (count == 0) {
                    s.id_summarySheet = rs.getInt(1);
                }
            }
        });

        if (result[0] > 0) { // summarysheet effettivamente inserito
            // salva le task
            if (s.sheetCookingTask.size() > 0) {
                CookingTask.saveAllNewCookingTask(s.id_summarySheet,s.sheetCookingTask);
            }

        }
    }
}


