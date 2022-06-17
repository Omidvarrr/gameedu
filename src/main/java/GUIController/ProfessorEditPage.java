package GUIController;

import dataBaseManager.Returner;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import logic.SaveUser;
import logic.appData;
import logic.university.Professor;
import logic.university.ProfessorGrades;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfessorEditPage implements Initializable {
    public Professor professor;
    private final Font font = Constants.getInstance().font;

    @FXML
    TextField emailTextField, phoneNumberTextField, roomNumberTextField;
    @FXML
    ChoiceBox<ProfessorGrades> gradesChoiceBox;
    @FXML
    Button updateButton;
    @FXML
    RadioButton educationalAssistantRadioButton;
    @FXML
    Label label, label1, label2, label3, warningLabel;


    public ProfessorEditPage() throws FileNotFoundException {
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::addCurrentValue);
        Platform.runLater(this::setEducationalAssistant);
        addGradesToChoiceBox();
        setFonts();
    }

    public void addCurrentValue() {
        emailTextField.setText(professor.getEmail());
        phoneNumberTextField.setText(professor.getEmail());
        roomNumberTextField.setText(Integer.toString(professor.getRoomNumber()));
        gradesChoiceBox.setValue(professor.getGrade());



    }


    public void addGradesToChoiceBox() {
        gradesChoiceBox.getItems().add(ProfessorGrades.associate);
        gradesChoiceBox.getItems().add(ProfessorGrades.assistant);
        gradesChoiceBox.getItems().add(ProfessorGrades.full);
    }

    public void update() throws IOException {
        professor.setEmail(emailTextField.getText());
        professor.setPhoneNumber(phoneNumberTextField.getText());
        professor.setRoomNumber(Integer.parseInt(roomNumberTextField.getText()));
        professor.setGrade(gradesChoiceBox.getValue());

        new SaveUser().saveAProfessor(professor);
    }

    public void setEducationalAssistant() {
        if (professor.getEducationalAssistant())
            educationalAssistantRadioButton.fire();
    }

    public void changeEducationalAssistant() throws FileNotFoundException {
        Professor educationalAssistant = null;
        Boolean result = true;
        ArrayList<Professor> professors = new Returner().returnProfessors();
        for (Professor prf : professors) {
            if (prf.getDepartment().getType() == appData.getInstance().getProfessor().getDepartment().getType()) {
                if (prf.getEducationalAssistant()) {
                    educationalAssistant = prf;
                    result = false;
                    break;
                }
            }
        }
        if (result)
            professor.setEducationalAssistant(true);
        else {
            warningLabel.setText(educationalAssistant.nameProperty().getValue() + " is EducationalAssistant");
        }
    }



    public void setFonts() {
        label.setFont(font);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        warningLabel.setFont(font);

        updateButton.setFont(font);
        educationalAssistantRadioButton.setFont(font);
    }


}
