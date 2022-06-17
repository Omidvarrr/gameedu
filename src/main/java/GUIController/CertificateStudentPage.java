package GUIController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.appData;
import logic.university.User;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CertificateStudentPage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label nameLabel, numberLabel, departmentLabel, dateLabel, label1, label2, label3, label4, label5;

    public CertificateStudentPage() throws FileNotFoundException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillLabel();
        setColor();
        setFont();
    }


    public void fillLabel() {
        User user = appData.getInstance().getUser();
        String fullName = user.getFirstName() + " " + user.getLastName();
        nameLabel.setText(fullName);

        numberLabel.setText(user.getUsername());
        departmentLabel.setText(user.getDepartment().getType().toString());
        // todo if has permission to ثبت نام  محوز صادر شود و بعد تاریخ هم بخورد
        dateLabel.setText("شهریور‌ماه ۱۴۰۱");


    }

    public void backToRequestPage(ActionEvent event) throws IOException {
        new GoToPage(event, Pages.REQUESTS);
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        dateLabel.setFont(font);
        nameLabel.setFont(font);
        numberLabel.setFont(font);
        departmentLabel.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }
}
