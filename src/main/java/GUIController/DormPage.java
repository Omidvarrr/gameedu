package GUIController;

import dataBaseManager.Returner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.SaveUser;
import logic.appData;
import logic.university.Dorm;
import logic.university.RecommendationResult;
import logic.university.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DormPage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label dormRequestStatusLabel;
    @FXML
    Button sendButton;

    public Dorm dorm;

    public DormPage() throws FileNotFoundException {
    }

    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }

    public void hideSendButton() {
        sendButton.setVisible(false);
    }

    public void showStatus() {
        dormRequestStatusLabel.setText(dorm.getStatus().toString());
    }

    public void sendRequest() throws IOException {
        this.dorm = new Dorm(new SaveUser().getDormCount()+1, appData.getInstance().getStudent());
        new SaveUser().saveDorm(dorm);
        showStatus();
        hideSendButton();
    }

    public void setResult() {
        ArrayList<Dorm> dorms = new Returner().returnDorms();
        for (Dorm drm : dorms) {
            System.out.println(drm);
            if (drm.getStudent().getId() == appData.getInstance().getStudent().getId())
                this.dorm = drm;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setResult();
        setColor();
        setFont();
        if (dorm != null) {
            hideSendButton();
            showStatus();
        }
        else
            dormRequestStatusLabel.setText("درخواستی ثبت نشده است.");
    }

    public void setFont() {
        dormRequestStatusLabel.setFont(font);
        sendButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }
}
