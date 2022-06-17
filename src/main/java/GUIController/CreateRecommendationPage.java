package GUIController;

import dataBaseManager.Returner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import logic.SaveUser;
import logic.appData;
import logic.university.Professor;
import logic.university.Recommendation;
import logic.university.RecommendationResult;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateRecommendationPage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    ChoiceBox<Professor> professorChoiceBox;
    @FXML
    Label professorLabel, sendedLabel;
    @FXML
    Button sendButton;

    public CreateRecommendationPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendedLabel.setVisible(false);
        try {
            setProfessorToChoiceBox();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setColor();
        setFont();
        setNameOfProfessor();
    }

    public void setProfessorToChoiceBox() throws FileNotFoundException {
        professorChoiceBox.getItems().addAll(new Returner().returnProfessors());
    }

    public void setNameOfProfessor() {
        professorChoiceBox.setConverter(new StringConverter<Professor>() {
            @Override
            public String toString(Professor object) {
                if (object == null)
                    return "";
                return object.getFirstName() + " " + object.getLastName();
            }

            @Override
            public Professor fromString(String string) {
                return null;
            }
        });
    }


    public void sendRecommendation() throws IOException {
        sendedLabel.setVisible(true);
        Recommendation recommendation = new Recommendation(new SaveUser().getRecommendationCount()+1, appData.getInstance().getStudent(), professorChoiceBox.getValue(), RecommendationResult.pending);
        new SaveUser().saveRecommendation(recommendation);
    }

    public void setFont() {
        sendedLabel.setFont(font);
        professorLabel.setFont(font);
        sendButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

}
