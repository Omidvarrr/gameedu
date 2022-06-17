package GUIController;

import com.jfoenix.controls.JFXComboBox;
import dataBaseManager.Finder;
import dataBaseManager.Returner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.appData;
import logic.university.Course;
import logic.university.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TemporaryScoresProfessorSidePage implements Initializable {
    private final Font font = Constants.constants.font;
    private final Background background = Constants.constants.themeReturner();
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private Course course = new Finder().findCouresOfStudent((Student) appData.getInstance().getUser()).get(0);

    @FXML
    AnchorPane anchorPane;
    @FXML
    TableView<Student> temporaryScoresTable;
    @FXML
    TableColumn<Student, String> studentNameColumn, studentNumberColumn, temporaryScoreColumn;
    @FXML
    TableColumn<Course, Void> reviewColumn;
    @FXML
    Button finalSubmitButton;
    @FXML
    Label warningLabel, label1, label2, label3, label4, label5, label6;
    @FXML
    JFXComboBox<Course> coursesComboBox;


    public TemporaryScoresProfessorSidePage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFont();
        setColor();
        try {
            addCourseToComboBox();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        initializeColumns();
        setNameOfCourse();
        makeEditableScoreColumn();
        addReviewButtonToTable();
    }


    public void addCourseToComboBox() throws FileNotFoundException {
        coursesComboBox.getItems().addAll(new Returner().returnCourses());
    }

    public void initializeColumns() {
        studentNameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        studentNumberColumn.setCellValueFactory(cellDate -> cellDate.getValue().numberProperty());
        temporaryScoreColumn.setCellValueFactory(cellDate -> cellDate.getValue().scoreProperty(course));

        temporaryScoresTable.setItems(students);
    }

    public void setNameOfCourse() {
        coursesComboBox.setConverter(new StringConverter<Course>() {
            @Override
            public String toString(Course object) {
                if (object == null)
                    return "";
                return object.getName();
            }
            @Override
            public Course fromString(String string) {
                return null;
            }
        });
    }

    public void makeEditableScoreColumn() {
        temporaryScoreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        temporaryScoreColumn.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setScore(course, event.getNewValue()));

        temporaryScoresTable.setEditable(true);


    }

    public void addReviewButtonToTable() {
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
                return new TableCell<>() {
                    private final Button reviewButton = new Button("ثبت/مشاهده اعتراض");
                    {
                        reviewButton.setFont(font);
                        reviewButton.setOnAction((ActionEvent event) -> {
                            Course course = getTableView().getItems().get(getIndex());
                            //todo send review to open window
                            try {
                                new OpenWindow(null, course, null, null, Pages.REVIEW_STUDENT_SIDE);
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
                        else
                            setGraphic(reviewButton);
                    }
                };
            }
        };
        reviewColumn.setCellFactory(cellFactory);
    }


    public void loadData() throws FileNotFoundException {
        students.clear();
        students.addAll(new Finder().studentObjectOfCourse(course));
    }

    public void setFont() {
        finalSubmitButton.setFont(font);
        warningLabel.setFont(font);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    public void finalSubmit() {
        // TODO: 4/27/22
    }

    public void courseSelector() throws FileNotFoundException {
        course = coursesComboBox.getValue();
        loadData();
    }

    public Boolean checkScore(Integer score) {
        return score >= 0 && score <= 20;
    }
}
