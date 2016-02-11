package elevatorsimulation.Controllers;

import elevatorsimulation.Callback.WindowDidLoadCallback;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Andrew on 2/5/2016.
 * <p>
 * FOOTNOTES:
 * Every controller MUST inherit from this class.
 */
public abstract class Controller {

    protected Controller parentController;



    // Load a window on top of it's parent window
    public void loadWindow(String resource, WindowDidLoadCallback callback) {

        Region root;


        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("elevatorsimulation/Views/" + resource + ".fxml"));

        try {
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Simulation Editor");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            callback.runCallBack(loader, stage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initWithParent(Controller controller) {
        this.parentController = controller;

    }


}
