package GUIController;

import com.google.gson.*;
import dataBaseManager.Returner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;
import logic.SaveUser;
import logic.appData;
import logic.university.Course;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseListPage extends MainMenu implements Initializable {
    public ObservableList<Course> courseList = FXCollections.observableArrayList();
    public ObservableList<Course> defaultCourseList = FXCollections.observableArrayList();

    @FXML
    AnchorPane anchorPane;
    @FXML
    TableView<Course> courseListTable;
    @FXML
    TableColumn<Course, String> title, number, department, teacher, prerequisites, grade, credit;
    @FXML
    TableColumn<Course, Void> edit;
    @FXML
    TableColumn<Course, Void> delete;
    @FXML
    RadioButton departmentRadioButton, gradeRadioButton, creditRadioButton;
    ToggleGroup filterBaseChoices = new ToggleGroup();
    @FXML
    ChoiceBox<String> filterChoiceBox;
    @FXML
    VBox VBox;
    @FXML
    Label label1, label2, label3, label4, label5, label6, label7, label8, label9;
    @FXML
    javafx.scene.control.Button addCourseButton;

    Font font = Constants.getInstance().font;

    public CourseListPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initializeTable();
            setDataToTable();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        edit.setVisible(false);
        delete.setVisible(false);
        addCourseButton.setVisible(false);
        if (appData.getInstance().getType() == UserTypes.PROFESSOR) {
            if (appData.getInstance().getProfessor().getEducationalAssistant()) {
                edit.setVisible(true);
                delete.setVisible(true);
                addCourseButton.setVisible(true);
                addEditButtonToTable();
                addDeleteButtonToTable();
            }
        }
    }

    public void initializeTable() throws FileNotFoundException {
        setRadioButtonsToGroup();
        initializeColumns();
        initializeList();
        setColor();
        setFonts();
    }

    public void setColor() throws FileNotFoundException {
        VBox.setBackground(Constants.getInstance().themeReturner());
    }

    public void setDataToTable() throws FileNotFoundException {
        courseList.clear();
        filter();
    }

    public void clearFilter() throws FileNotFoundException {
        filterBaseChoices.getSelectedToggle().setSelected(false);
        filterChoiceBox.getItems().clear();
        setDataToTable();
    }

    public void initializeColumns() {
        title.setCellValueFactory(new PropertyValueFactory<>("name"));
        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        department.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        teacher.setCellValueFactory(cellData -> cellData.getValue().teacherNameProperty());
        prerequisites.setCellValueFactory(cellData -> {
            if (cellData.getValue().getPrerequisites() != null)
                return cellData.getValue().getPrerequisites().nameProperty();
            return null;
        });

        courseListTable.setItems(courseList);
    }

    public void initializeList() throws FileNotFoundException {
        defaultCourseList.clear();
        defaultCourseList.addAll(new Returner().returnCourses());
    }

    public void setRadioButtonsToGroup() {
        departmentRadioButton.setToggleGroup(filterBaseChoices);
        creditRadioButton.setToggleGroup(filterBaseChoices);
        gradeRadioButton.setToggleGroup(filterBaseChoices);
    }

    public void setChoicesForCredit() {
        filterChoiceBox.getItems().clear();
        filterChoiceBox.getItems().add("1");
        filterChoiceBox.getItems().add("2");
        filterChoiceBox.getItems().add("3");
        filterChoiceBox.getItems().add("4");
    }

    public void setChoicesForGrade() {
        filterChoiceBox.getItems().clear();
        filterChoiceBox.getItems().add("BS");
        filterChoiceBox.getItems().add("MA");
        filterChoiceBox.getItems().add("PhD");
    }

    public void setChoicesForDepartment() {
        filterChoiceBox.getItems().clear();
        filterChoiceBox.getItems().add("CHE");
        filterChoiceBox.getItems().add("CE");
        filterChoiceBox.getItems().add("MATH");
        filterChoiceBox.getItems().add("IE");
        filterChoiceBox.getItems().add("EE");
    }

    public Boolean checkerBasedOnFilter(Course course, RadioButton filterBase, String filterValue) {
        if (filterBase == departmentRadioButton)
            return course.getDepartment().getType().toString().equals(filterValue);
        else if (filterBase ==  creditRadioButton)
            return Integer.toString(course.getCredit()).equals(filterValue);
        else if (filterBase == gradeRadioButton)
            return course.getGrades().toString().equals(filterValue);
        return true;
    }

    public void filter() throws FileNotFoundException {
        initializeList();
        for (Course course : defaultCourseList) {
            try {
                if (checkerBasedOnFilter(course, ((RadioButton) filterBaseChoices.getSelectedToggle()), filterChoiceBox.getValue())) {
                    courseList.add(course);
                    System.out.println(course.getName());
                    System.out.println(course.teacherNameProperty().toString());
            }
            } catch (NullPointerException e) {
                courseList.add(course);
                e.printStackTrace();
            }
        }
    }

    public void addEditButtonToTable() {
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
                return new TableCell<>() {
                    private final javafx.scene.control.Button editButton = new javafx.scene.control.Button( "ویرایش");
                    {
                        editButton.setFont(font);
                        editButton.setOnAction((ActionEvent event) -> {
                            Course course = getTableView().getItems().get(getIndex());
                            try {
                                new OpenWindow(null, course, null, null, Pages.COURSE_EDIT);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            if (getTableView().getItems().get(getIndex()).getDepartment().getType() == appData.getInstance().getProfessor().getDepartment().getType()) {
                                setGraphic(editButton);
                            }
                        }

                    }

                };
            }
        };
        edit.setCellFactory(cellFactory);
    }

    public void addDeleteButtonToTable() {
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
                return new TableCell<>() {
                    private final javafx.scene.control.Button deleteButton = new javafx.scene.control.Button("حذف");
                    {
                        deleteButton.setFont(font);
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Course course = getTableView().getItems().get(getIndex());
                            new SaveUser().deleteCourse(course);
                            try {
                                setDataToTable();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            if (getTableView().getItems().get(getIndex()).getDepartment().getType() == appData.getInstance().getProfessor().getDepartment().getType())
                                setGraphic(deleteButton);
                        }
                    }
                };
            }
        };
        delete.setCellFactory(cellFactory);
    }

    public void addCourse() throws IOException {
        new OpenWindow(null, null, null, null, Pages.COURSE_CREATE);
    }

    public void setFonts() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);
        label8.setFont(font);
        label9.setFont(font);
    }

}
