package businesslogic.kitchentask;


import businesslogic.recipe.Recipe;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CookingTask {

    private String name;
    private int id_cookingTask;
    private int importance;
    private int difficulty;
    private double preparationTime;
    private int portions;
    private int quantity;
    Recipe recipe;


    public CookingTask() {

    }

    public int getId_cookingTask() {
        return id_cookingTask;
    }

    public static void saveAllNewCookingTask(int summarysheetid, ArrayList<CookingTask> cookingTasks) {
        String secInsert = "INSERT INTO catering.cookingTask (importance, difficulty, preparationTime, portions,quantity,id_recipe) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PersistenceManager.executeBatchUpdate(secInsert, cookingTasks.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1,summarysheetid);
                ps.setInt(2,cookingTasks.get(batchCount).importance);
                ps.setInt(3,cookingTasks.get(batchCount).difficulty);
                ps.setDouble(4,cookingTasks.get(batchCount).preparationTime);
                ps.setInt(5,cookingTasks.get(batchCount).portions);
                ps.setInt(6,cookingTasks.get(batchCount).quantity);
                ps.setInt(7,cookingTasks.get(batchCount).recipe.getIdRecipe());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                cookingTasks.get(count).id_cookingTask = rs.getInt(1);
            }
        });

    }

}
