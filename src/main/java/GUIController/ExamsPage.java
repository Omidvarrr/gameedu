package GUIController;

import dataBaseManager.Finder;
import dataBaseManager.Returner;
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
import logic.appData;
import logic.university.Course;
import logic.university.UserTypes;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ExamsPage extends MainMenu implements Initializable {
    private ArrayList<Course> userCourses = new Returner().returnCourses();
    private final Background background = Constants.getInstance().themeReturner();
    private final Font font = Constants.getInstance().font;

    @FXML
    AnchorPane anchorPane;
    @FXML
    TableView<Course> examsTableView;
    @FXML
    TableColumn<Course, String> courseNameColumn, examDateColumn;
    @FXML
    Label label1, label2;

    private ObservableList<Course> courses = FXCollections.observableArrayList();

    public ExamsPage() throws FileNotFoundException {
    }

    private void setCourses(ArrayList<Course> courses) {
        this.userCourses = courses;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeColumns();
        initializeTable();
        try {
            loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setColors();
        setFont();

    }

    private void initializeColumns() {
        courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        examDateColumn.setCellValueFactory(cellData -> cellData.getValue().testDateProperty());
    }

    private void initializeTable() {
        examsTableView.setItems(courses);
    }

    private void loadData() throws FileNotFoundException {
        if (appData.getInstance().getType() == UserTypes.PROFESSOR)
            loadProfessorData();
        else
            loadStudentData();
    }

    public void loadStudentData() throws FileNotFoundException {
        for (Course course : userCourses) {
            if (new Finder().checkStudentExistInCourse(course, appData.getInstance().getStudent())) {
                courses.add(course);
            }

        }

    }

    public void loadProfessorData() {
        for (Course course : userCourses) {
            if (course.getTeacher().getId() == appData.getInstance().getProfessor().getId())
                courses.add(course);
        }

    }

    public void setColors() {
        anchorPane.setBackground(background);
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
    }



}
