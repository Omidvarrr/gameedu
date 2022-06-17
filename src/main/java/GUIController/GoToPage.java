package GUIController;


import javafx.event.ActionEvent;

import java.io.IOException;

public class GoToPage {
    public GoToPage(ActionEvent event, Pages page) throws IOException {
        SceneChanger sceneChanger = new SceneChanger();
        try {
            sceneChanger.changeSceneByAction(event, page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
