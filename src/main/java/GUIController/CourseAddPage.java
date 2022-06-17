package GUIController;

import dataBaseManager.Returner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import logic.SaveUser;
import logic.appData;
import logic.university.Course;
import logic.university.Department;
import logic.university.Professor;
import logic.university.StudentGrades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseAddPage implements Initializable {
    String name, credit;
    Department department;
    Professor teacher;
    StudentGrades grade;
    Course prerequisites;
    private final Font font = Constants.getInstance().font;
    private Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label, label1, label3, label4, label5;
    @FXML
    Button createButton;
    @FXML
    TextField nameTextField;
    @FXML
    ChoiceBox<String> creditChoiceBox;
    @FXML
    ChoiceBox<Professor> teacherChoiceBox;
    @FXML
    ChoiceBox<StudentGrades> gradesChoiceBox;
    @FXML
    ChoiceBox<Course> prerequisitesChoiceBox;

    public CourseAddPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        addCreditToChoiceBox();
        addGradeToChoiceBox();
        try {
            addPrerequisitesToChoiceBox();
            addTeacherToChoiceBox();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setFonts();
        setColor();
        setNameOfObjects();
    }

    public void loadData() {
        name = nameTextField.getText();
        credit = creditChoiceBox.getValue();
        teacher = teacherChoiceBox.getValue();
        grade = gradesChoiceBox.getValue();
        prerequisites = prerequisitesChoiceBox.getValue();
    }

    public void addCreditToChoiceBox() {
        creditChoiceBox.getItems().add("1");
        creditChoiceBox.getItems().add("2");
        creditChoiceBox.getItems().add("3");
        creditChoiceBox.getItems().add("4");
    }

    public void addTeacherToChoiceBox() throws FileNotFoundException {
        for (Professor professor : new Returner().returnProfessors()) {
            if (professor.getDepartment() == appData.getInstance().professor.getDepartment())
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

    public void create() throws IOException {
        Course course = new Course(
            nameTextField.getText(), Integer.parseInt(creditChoiceBox.getValue()), Integer.toString(new SaveUser().getCourseCount()+1),
                appData.getInstance().getProfessor().getDepartment(), teacherChoiceBox.getValue(), gradesChoiceBox.getValue(),
                prerequisitesChoiceBox.getValue(), null, null, null, null, null
        );
        new SaveUser().saveACourse(course);
    }

    public void setFonts() {
        label.setFont(font);
        label1.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        createButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
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
}
