package GUIController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.SaveUser;
import logic.appData;
import logic.university.Professor;
import logic.university.ProfessorGrades;
import logic.university.UserTypes;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfessorAddPage implements Initializable {
    private final Font font = Constants.getInstance().font;

    String firstName;
    String lastName;
    String nationalCode;
    String email;
    String phoneNumber;
    String roomNumber;
    ProfessorGrades grade;
    Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField fistNameTextField, lastNameTextField, nationalCodeTextField, emailTextField, phoneNumberTextField, roomNumberTextField;
    @FXML
    ChoiceBox<ProfessorGrades> gradesChoiceBox;
    @FXML
    Label label, label1, label2, label3, label4, label5, label6;
    @FXML
    Button createButton;

    public ProfessorAddPage() throws FileNotFoundException {
    }

    public void create() throws IOException {
        loadData();
        Professor professor = new Professor(new SaveUser().getProfessorCount()+1, "123", "123", fistNameTextField.getText(),
                lastNameTextField.getText(), nationalCodeTextField.getText(), emailTextField.getText(), phoneNumberTextField.getText(),
                appData.getInstance().professor.getDepartment(), Integer.parseInt(roomNumberTextField.getText()), gradesChoiceBox.getValue(), false, false,
                UserTypes.PROFESSOR);
        new SaveUser().saveAProfessor(professor);
    }

    public void loadData() {
        firstName = fistNameTextField.getText();
        lastName = lastNameTextField.getText();
        nationalCode = nationalCodeTextField.getText();
        phoneNumber = phoneNumberTextField.getText();
        roomNumber = roomNumberTextField.getText();
        grade = gradesChoiceBox.getValue();
    }

    public void addGradesToChoiceBox() {
        gradesChoiceBox.getItems().add(ProfessorGrades.full);
        gradesChoiceBox.getItems().add(ProfessorGrades.assistant);
        gradesChoiceBox.getItems().add(ProfessorGrades.associate);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addGradesToChoiceBox();
        setColor();
        setFonts();
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

    public void setFonts() {
        label.setFont(font);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);

        createButton.setFont(font);
    }
}
