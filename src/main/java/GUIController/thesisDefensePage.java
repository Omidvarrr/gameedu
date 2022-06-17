package GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class thesisDefensePage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label thesisDefenseStatusLabel;
    @FXML
    Button sendButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFont();
        setColor();
    }

    public thesisDefensePage() throws FileNotFoundException {
    }

    public void hideSendButton() {
        sendButton.setVisible(false);
    }

    public void showStatus() {
//        thesisDefenseStatusLabel.setText();
    }

    public void setThesisDefenseDate() {
        hideSendButton();
        showStatus();
    }


    public void setFont() {
        thesisDefenseStatusLabel.setFont(font);
        sendButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }



}
