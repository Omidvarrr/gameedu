package GUIController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.university.Course;

import java.net.URL;
import java.util.ResourceBundle;


public class editScorePage implements Initializable {
    private final Font font = Constants.constants.font;
    private final Background background = Constants.constants.themeReturner();
    Course course;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label1;
    @FXML
    TextField scoreTextField;
    @FXML
    Button submitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFont();
        setColor();
    }


    public void setCourse(Course course) {
        this.course = course;
    }

    public void update() {
        //todo
    }

    public void setFont() {
        submitButton.setFont(font);
        scoreTextField.setFont(font);
        label1.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }




}
