package persistence;

import businesslogic.kitchentask.CookingTask;
import businesslogic.kitchentask.KitchenTaskReceiver;
import businesslogic.kitchentask.SummarySheet;

import java.util.ArrayList;

//si registra come event receiver presso il KitchenTaskManager
public class KitchenTaskPersistence implements KitchenTaskReceiver {

    @Override
    public void updateSummarySheetCreated(SummarySheet s) {
            SummarySheet.saveNewSummarySheet(s);
    }


}
