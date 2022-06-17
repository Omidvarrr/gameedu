package GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class LoaderFXMLs {
    Stage stage;

    public Node loadFXML(Pages pages) throws IOException {
        String path = "FXMLs/" + new PagesFinder().getName(pages);
//        if (event.getSource().getClass() == MenuItem.class)
//            stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
//        else
//            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));
        return (Node) loader.load();



    }
}
