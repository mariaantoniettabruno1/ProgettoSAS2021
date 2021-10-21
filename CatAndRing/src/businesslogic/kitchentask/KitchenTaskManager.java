package businesslogic.kitchentask;

import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.user.User;
import persistence.KitchenTaskPersistence;

import java.util.ArrayList;
//valutare se si deve inserire un arraylist di summarySheet per potervi accedere
public class KitchenTaskManager {
    private SummarySheet currentSummarySheet;
    private ArrayList<KitchenTaskReceiver> eventReceivers;

    public KitchenTaskManager() {
        eventReceivers = new ArrayList<>();
    }

public SummarySheet createSummarySheet() throws UseCaseLogicException {
    User user = CatERing.getInstance().getUserManager().getCurrentUser();

    if (!user.isOrganizer()) {
        throw new UseCaseLogicException();
    }
        SummarySheet s = new SummarySheet();
        this.setCurrentSummarySheet(s);
        this.notifySummarySheetCreated(s);
        return s;
}

    /*public ArrayList<CookingTask> addCookingTask(int importance, int difficulty, double preparationTime, int portions, int quantity) throws UseCaseLogicException {
        if(currentSummarySheet== null){
            throw  new UseCaseLogicException();
        }
        ArrayList<CookingTask> ctsk = this.currentSummarySheet.addCookingTask(importance,difficulty,preparationTime,portions,quantity);
        this.notifyCookingTaskAdded(ctsk);
        return ctsk;
    }*/
   /* public CookingTask editCookingTask(int importance, int difficulty, double preparationTime, int portions, int quantity) throws UseCaseLogicException{
        if(currentSummarySheet == null)
            throw new UseCaseLogicException();
        CookingTask ctsk = this.currentSummarySheet.editCookingTask(importance,difficulty,preparationTime,portions,quantity);
        this.notifyCookingTaskEdited(ctsk);
        return ctsk;
    }*/
   /* public void deleteCookingTask(CookingTask ctsk) throws UseCaseLogicException{
        this.currentSummarySheet.deleteCookingTask(ctsk);
        this.notifyCookingTaskDeleted(ctsk);
    }

    private void notifySummarySheetCreated(SummarySheet s) {
        for (KitchenTaskReceiver er : this.eventReceivers) {
            er.updateSummarySheetCreated(s);
        }
    }
    public void notifyCookingTaskAdded(ArrayList<CookingTask> ctsk) {
        for (KitchenTaskReceiver er : this.eventReceivers) {
            er.updateCookingTaskAdded(ctsk);
        }
    }
    public void notifyCookingTaskEdited(CookingTask ctsk) {
        for (KitchenTaskReceiver er : this.eventReceivers) {
            er.updateCookingTaskEdited(ctsk);
        }
    }
    public void notifyCookingTaskDeleted(CookingTask ctsk) {
        for (KitchenTaskReceiver er : this.eventReceivers) {
            er.updateCookingTaskDeleted(ctsk);
        }
    }
    public void setCurrentSummarySheet(SummarySheet s){
        this.currentSummarySheet = s;
    }
    public SummarySheet getCurrentSummarySheet(){
        return this.currentSummarySheet;
    }
    public void addEventReceiver(KitchenTaskReceiver rec) {
        this.eventReceivers.add(rec);
    }

    public void removeEventReceiver(KitchenTaskReceiver rec) {
        this.eventReceivers.remove(rec);
    }*/

    private void notifySummarySheetCreated(SummarySheet s) {
        for (KitchenTaskReceiver er : this.eventReceivers) {
            er.updateSummarySheetCreated(s);
        }
    }
    public void setCurrentSummarySheet(SummarySheet s){
        this.currentSummarySheet = s;
    }
    public SummarySheet getCurrentSummarySheet(){
        return this.currentSummarySheet;
    }
    public void addEventReceiver(KitchenTaskReceiver rec) {
        this.eventReceivers.add(rec);
    }

    public void removeEventReceiver(KitchenTaskReceiver rec) {
        this.eventReceivers.remove(rec);
    }


}


