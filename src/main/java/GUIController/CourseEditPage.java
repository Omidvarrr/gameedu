package GUIController;

import dataBaseManager.Returner;
import javafx.application.Platform;
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
import logic.university.Course;

import logic.university.Professor;
import logic.university.StudentGrades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseEditPage extends CourseListPage implements Initializable {
    private Course course;
    private final Background background = Constants.getInstance().themeReturner();
    private final Font font = Constants.getInstance().font;

    @FXML
    AnchorPane anchorPane;
    @FXML
    ChoiceBox<String> creditChoiceBox;
    @FXML
    ChoiceBox<Professor> teacherChoiceBox;
    @FXML
    ChoiceBox<StudentGrades> gradesChoiceBox;
    @FXML
    ChoiceBox<Course> prerequisitesChoiceBox;
    @FXML
    Button updateButton;
    @FXML
    Label label, label2, label3, label4;

    public CourseEditPage() throws FileNotFoundException {
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(this::addValueToChoiceBoxes);
        addCreditToChoiceBox();
        try {
            addTeacherToChoiceBox();
            addPrerequisitesToChoiceBox();
            addGradeToChoiceBox();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setColors();
        setFont();
        setNameOfObjects();
    }

    public void addCreditToChoiceBox() {
        creditChoiceBox.getItems().add("1");
        creditChoiceBox.getItems().add("2");
        creditChoiceBox.getItems().add("3");
        creditChoiceBox.getItems().add("4");
    }

    public void setNameOfObjects() {
        setNameOfPrerequisite();
        setNameOfProfessor();
    }

    public void setNameOfProfessor() {
        teacherChoiceBox.setConverter(new StringConverter<Professor>() {
            @Override
            public String toString(Professor object) {
                if (object == null)
                    return "";
                else
                    return object.getFirstName() + " " + object.getLastName();
            }

            @Override
            public Professor fromString(String string) {
                return null;
            }
        });
    }

    public void setNameOfPrerequisite() {
        prerequisitesChoiceBox.setConverter(new StringConverter<Course>() {
            @Override
            public String toString(Course object) {
                if (object == null)
                    return "";
                else
                    return object.getName();
            }

            @Override
            public Course fromString(String string) {
                return null;
            }
        });

    }

    public void addTeacherToChoiceBox() throws FileNotFoundException {
        for (Professor professor : new Returner().returnProfessors()) {
            teacherChoiceBox.getItems().add(professor);
        }
    }

    public void addPrerequisitesToChoiceBox() throws FileNotFoundException {
        for (Course course : new Returner().returnCourses()) {
            prerequisitesChoiceBox.getItems().add(course);
        }
    }

    public void addGradeToChoiceBox() {
        gradesChoiceBox.getItems().add(StudentGrades.B_S);
        gradesChoiceBox.getItems().add(StudentGrades.M_A);
        gradesChoiceBox.getItems().add(StudentGrades.ph_DM);
    }

    public void addValueToChoiceBoxes() {
        creditChoiceBox.setValue(Integer.toString(course.getCredit()));
        teacherChoiceBox.setValue(course.getTeacher());
        gradesChoiceBox.setValue(course.getGrade());
        prerequisitesChoiceBox.setValue(course.getPrerequisites());
    }

    public void update() throws IOException {
        course.setCredit(Integer.parseInt(creditChoiceBox.getValue()));
        course.setTeacher(teacherChoiceBox.getValue());
        course.setGrade(gradesChoiceBox.getValue());
        course.setPrerequisites(prerequisitesChoiceBox.getValue());
        new SaveUser().saveACourse(course);
        setDataToTable();
    }


    public void setColors() {
        anchorPane.setBackground(background);
    }

    public void setFont() {
        label.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        updateButton.setFont(font);
    }
}