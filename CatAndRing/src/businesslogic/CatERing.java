package businesslogic;


import businesslogic.event.EventManager;
import businesslogic.kitchentask.KitchenTaskManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.turn.TurnManager;
import businesslogic.user.UserManager;
import persistence.KitchenTaskPersistence;

public class CatERing {
    //pattern Singleton, perchè per ogni istanza dell'app vogliamo un solo oggetto CatERing
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private KitchenTaskManager kitchenTskMgr;
    private RecipeManager recipeMgr;
    private TurnManager turnMgr;
    private EventManager eventMgr;
    private UserManager userMgr;

    private KitchenTaskPersistence kitchenTaskPersistence;

    private CatERing() {
        //disattiva il costruttore di default che può essere chiamato solo in questa classe
        // abbiamo realizzato un pattern singleton
        kitchenTskMgr = new KitchenTaskManager();
        recipeMgr = new RecipeManager();
        turnMgr = new TurnManager();
        eventMgr = new EventManager();
        userMgr = new UserManager();
        kitchenTskMgr.addEventReceiver(kitchenTaskPersistence);

    }

    public KitchenTaskManager getKitchenTskManager() {
        return kitchenTskMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public TurnManager getTurnManager() {
        return turnMgr;
    }

    public EventManager getEventManager() {
        return eventMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

}
