package GUIController;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.university.Course;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseSummaryPage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();
    private Course course;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label1, label2, label3, label4, averageLabel, rejectCountLabel, acceptCountLabel, averagePureLabel;


    public CourseSummaryPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFont();
        setColor();
        Platform.runLater(this::setDetailToLabel);

    }

    public void setDetailToLabel() {
        long average = course.average();
        averageLabel.setText(Long.toString(average));

        int rejectCount = course.rejectCount();
        rejectCountLabel.setText(Integer.toString(rejectCount));

        int acceptCount = course.acceptCount();
        acceptCountLabel.setText(Integer.toString(acceptCount));

        long pureAverage = course.pureAverage();
        averagePureLabel.setText(Long.toString(pureAverage));

    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        averageLabel.setFont(font);
        rejectCountLabel.setFont(font);
        acceptCountLabel.setFont(font);
        averagePureLabel.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
