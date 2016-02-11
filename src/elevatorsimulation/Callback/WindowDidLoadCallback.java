package elevatorsimulation.Callback;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Created by andrewlincoln on 2/2/16.
 */
public interface WindowDidLoadCallback {

    void runCallBack(FXMLLoader loader, Stage stage);
}
