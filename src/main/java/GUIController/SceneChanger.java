package GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneChanger {
    Stage stage;

    public void changeSceneByAction(ActionEvent e, Pages page) throws IOException {
        String path = "FXMLs/" + new PagesFinder().getName(page);
        if (e.getSource().getClass() == MenuItem.class)
            stage =  (Stage) ((MenuItem) e.getSource()).getParentPopup().getOwnerWindow();

        else
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
