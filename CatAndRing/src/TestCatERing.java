import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchentask.SummarySheet;
import persistence.PersistenceManager;

public class TestCatERing {
    public static void main(String[] args) throws UseCaseLogicException {

        System.out.println("TEST DATABASE CONNECTION");
        PersistenceManager.testSQLConnection();
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

        System.out.println("\n TEST CREATE SUMMARY SHEET");
        SummarySheet s = CatERing.getInstance().getKitchenTskManager().createSummarySheet();



    }
}
