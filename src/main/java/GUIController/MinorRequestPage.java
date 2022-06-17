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
import logic.university.Department;
import logic.university.Minor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MinorRequestPage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    ChoiceBox<Department> departmentChoiceBox;
    @FXML
    Label minorSendedLabel;
    @FXML
    Label sendedLabel, label1;
    @FXML
    Button sendMinorButton;

    public MinorRequestPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendedLabel.setVisible(false);
        try {
            addDepartmentsToChoceBox();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setColor();
        setFont();
        setNameOfDepartment();
    }

    public void setNameOfDepartment() {
        departmentChoiceBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department object) {
                if (object == null)
                    return "";
                else
                    return object.getType().toString();
            }

            @Override
            public Department fromString(String string) {
                return null;
            }
        });
    }
    public void addDepartmentsToChoceBox() throws FileNotFoundException {
        for (Department department : new Returner().returnDepartments()) {
            departmentChoiceBox.getItems().add(department);
        }
//        departmentChoiceBox.getItems().remove(appData.getInstance().getUser().getDepartment());
    }


    public void sendMinorRequest() throws IOException {
        Minor minor = new Minor(new SaveUser().getMinorCount()+1, appData.getInstance().getStudent(), departmentChoiceBox.getValue());
        new SaveUser().saveMinor(minor);
        sendedLabel.setText("✅درخواست شما با موفقیت ثبت شد!");
        sendedLabel.setVisible(true);
    }

    public void setFont() {
        label1.setFont(font);
        sendedLabel.setFont(font);
        sendMinorButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }


}
