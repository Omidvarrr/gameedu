package GUIController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.appData;
import logic.university.Student;
import logic.university.User;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label1, label2, label3, label4, label5, label6, label7;
    @FXML
    ImageView profileImageView;
    @FXML
    Label nameLabel, emailLabel, lastEnterTimeLabel, statusLabel, supervisorLabel, regesterLiscenceLabel, registerTimeLabel;

    public HomePage() throws FileNotFoundException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setColor();
        setFont();
        setCommonDataToLabel();
        if (appData.getInstance().getType() == UserTypes.STUDENT)
            setStudentDataToLabel();
    }

    public void setCommonDataToLabel() {
        User user = (User) appData.getInstance().getUser();
        nameLabel.setText(user.nameProperty().getValue());
        emailLabel.setText(user.getEmail());
        lastEnterTimeLabel.setText(user.getEnterTime());
    }

    public void setStudentDataToLabel() {
        Student student = appData.getInstance().getStudent();
        lastEnterTimeLabel.setText(student.getEnterTime());
        statusLabel.setText(student.getStatus().toString());
        supervisorLabel.setText(student.getSupervisor().nameProperty().getValue());
//        regesterLiscenceLabel.setText(student.getRegisterLicense().toString());
//        registerTimeLabel.setText(student.getRegisterTime());
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }
}
