package GUIController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.university.Course;
import logic.university.Recommendation;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowRecommendationPage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.constants.themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label teacherNameTextField, studentNameTextField, label1, label2, label3, label4, label5;
    @FXML
    TableView<Course> courseAndGradeTableView;
    @FXML
    TableColumn<Course, String> courseNameColumn, gradeColumn;


    public Recommendation recommendation;

    public ObservableList<Course> commonCourses = FXCollections.observableArrayList();

    public ShowRecommendationPage() throws FileNotFoundException {
    }


    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::setNamesToLabels);
        Platform.runLater(this::initializeColumns);
        Platform.runLater(this::setCoursesToTable);
        setFont();
        setColor();
    }

    public void setNamesToLabels() {
        teacherNameTextField.setText(recommendation.getTeacher().getFirstName() + " " + recommendation.getStudent().getLastName());
        studentNameTextField.setText(recommendation.getStudent().getFirstName() + " " + recommendation.getStudent().getLastName());
    }

    public void initializeColumns() {
        courseNameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty(recommendation.getStudent().getId()));

        courseAndGradeTableView.setItems(commonCourses);
    }

    public void setCoursesToTable() {
        commonCourses.addAll(recommendation.getCommonCoureses());
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }
}
