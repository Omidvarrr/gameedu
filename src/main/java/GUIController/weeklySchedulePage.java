package GUIController;

import dataBaseManager.Finder;
import dataBaseManager.Returner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.appData;
import logic.university.Course;
import logic.university.Days;
import logic.university.UserTypes;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class weeklySchedulePage extends MainMenu implements Initializable {
    @FXML
    TableView<Course> saturdayTableView, sundayTableView, mondayTableView, tuesdayTableView, wendesdayTableView, thursdayTableView, fridayTableView;
    @FXML
    TableColumn<Course, String> saturdayColumn, sundayColumn, mondayColumn, tuesdayColumn, wendesdayColumn, thursdayColumn, fridayColumn;
    @FXML
    Label label1, label2, label3, label4, label5, label6, label7;
    @FXML
    AnchorPane anchorPane;

    private Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    private ObservableList<Course> saturdayList = FXCollections.observableArrayList();
    private ObservableList<Course> sundayList = FXCollections.observableArrayList();
    private ObservableList<Course> mondayList = FXCollections.observableArrayList();
    private ObservableList<Course> tuesdayList = FXCollections.observableArrayList();
    private ObservableList<Course> wendesdayList = FXCollections.observableArrayList();
    private ObservableList<Course> thursdayList = FXCollections.observableArrayList();
    private ObservableList<Course> fridayList = FXCollections.observableArrayList();

    private ArrayList<Course> courseList = new Returner().returnCourses();

    public weeklySchedulePage() throws FileNotFoundException {
    }


    public ObservableList<Course> daysToTableColumn(Days day) {
        return switch (day) {
            case saturday -> saturdayList;
            case sunday -> sundayList;
            case monday -> mondayList;
            case tuesday -> tuesdayList;
            case wendesday -> wendesdayList;
            case thursday -> thursdayList;
            case friday -> fridayList;
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTables();
        initializeColumns();
        try {
            loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setFont();
        setColor();
        clearEmptyTextFromTables();
    }

    public void initializeColumns() {
        saturdayColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        sundayColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        mondayColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        tuesdayColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        wendesdayColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        thursdayColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        fridayColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
    }

    public void initializeTables() {
        saturdayTableView.setItems(saturdayList);
        sundayTableView.setItems(sundayList);
        mondayTableView.setItems(mondayList);
        tuesdayTableView.setItems(tuesdayList);
        wendesdayTableView.setItems(wendesdayList);
        thursdayTableView.setItems(thursdayList);
        fridayTableView.setItems(fridayList);
    }

    public void loadData() throws FileNotFoundException {
        if (appData.getInstance().getType() == UserTypes.PROFESSOR)
            loadProfessorData();
        else
            loadStudentData();
    }

    public void loadStudentData() throws FileNotFoundException {
        for (Course course : courseList) {
            if (new Finder().checkStudentExistInCourse(course, appData.getInstance().student)) {
                for (Days day : course.getDays()) {
                    daysToTableColumn(day).add(course);
                }
            }
        }
    }

    public void loadProfessorData() throws FileNotFoundException {
        ArrayList<Course> courses = new Returner().returnCourses();
        for (Course course : courses) {
            if (course.getTeacher().getId() == appData.getInstance().getProfessor().getId()) {
                for (Days day : course.getDays()) {
                    daysToTableColumn(day).add(course);
                }
            }
        }
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

    public void clearEmptyTextFromTables() {
        saturdayTableView.setPlaceholder(new Label());
        sundayTableView.setPlaceholder(new Label());
        mondayTableView.setPlaceholder(new Label());
        tuesdayTableView.setPlaceholder(new Label());
        wendesdayTableView.setPlaceholder(new Label());
        thursdayTableView.setPlaceholder(new Label());
        fridayTableView.setPlaceholder(new Label());
    }



}
