package GUIController;

import dataBaseManager.Finder;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.university.Course;
import logic.university.Student;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class TemporaryScoresOfStudent implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();
    private ObservableList<Course> courses = FXCollections.observableArrayList();
    private Student student;

    @FXML
    AnchorPane anchorPane;
    @FXML
    TableView<Course> scoreTableView;
    @FXML
    TableColumn<Course, String> courseColumn, scoreColumn;
    @FXML
    Label label1, label2;


    public TemporaryScoresOfStudent() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::initializeColumns);

        setFont();
        setColor();
    }

    public void setStudent(Student student) throws FileNotFoundException {
        this.student = student;
        loadData();
    }

    public void initializeColumns() {
        courseColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        scoreColumn.setCellValueFactory(cellDate -> cellDate.getValue().gradeProperty(student.getId()));

        scoreTableView.setItems(courses);
    }

    public Runnable loadData() throws FileNotFoundException {
        courses.addAll(new Finder().findCoursesWithTemporaryScores(student));

        return null;
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }




}
