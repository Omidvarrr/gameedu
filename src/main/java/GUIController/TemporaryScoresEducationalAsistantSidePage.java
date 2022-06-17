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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.university.Course;
import logic.university.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TemporaryScoresEducationalAsistantSidePage implements Initializable {
    private ObservableList<Student> students = FXCollections.observableArrayList();
    Course course;
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label1, label2, label3, label4, label5, label6, label7;
    @FXML
    TableView<Student> scoresTableView;
    @FXML
    TableColumn<Student, String> studentColumn, professorColumn, scoreColumn;
    @FXML
    TableColumn<Student, Void> temporaryScoreOfStudentColumn, reviewColumn, courseSummaryColumn;
    @FXML
    JFXComboBox<Course> coursesComboBox;



    public TemporaryScoresEducationalAsistantSidePage() throws FileNotFoundException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFont();
        setColor();
        setNameOfCourse();
        label5.setWrapText(true);
        try {
            addCourseToComboBox();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        initializeColumns();
        addTemporaryScoreButtonToTable();
        addReviewButtonToTable();
        addSummaryButtonToTable();
    }

    public void initializeColumns() {
        studentColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        professorColumn.setCellValueFactory(cellDate -> course.getTeacher().nameProperty());
        scoreColumn.setCellValueFactory(cellDate -> cellDate.getValue().scoreProperty(course));

        scoresTableView.setItems(students);
    }

    public void loadDate() throws FileNotFoundException {
        students.clear();
        students.addAll(new Finder().studentObjectOfCourse(course));

    }

    public void courseSelector() throws FileNotFoundException {
        course = coursesComboBox.getValue();
        loadDate();
    }

    public void addCourseToComboBox() throws FileNotFoundException {
        coursesComboBox.getItems().addAll(new Returner().returnCourses());
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
    // temporaryScoreOfStudentColumn
    public void addTemporaryScoreButtonToTable() {
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student, Void> call(TableColumn<Student, Void> param) {
                return new TableCell<>() {
                    private final Button scoreButton = new Button("نمره موقت");
                    {
                        scoreButton.setFont(font);
                        scoreButton.setOnAction((ActionEvent event) -> {
                            Student student = getTableView().getItems().get(getIndex());
                            //todo send review to open window
                            try {
                                new OpenWindow(null, null, null, student, Pages.TEMPORARY_SCORES_OF_STUDENT);
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
                            setGraphic(scoreButton);
                    }
                };
            }
        };
        temporaryScoreOfStudentColumn.setCellFactory(cellFactory);
    }

    public void addSummaryButtonToTable() {
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student, Void> call(TableColumn<Student, Void> param) {
                return new TableCell<>() {
                    private final Button scoreButton = new Button("مشاهده اعتراض");
                    {
                        //todo if hasn't review disable button.
                        scoreButton.setFont(font);
                        scoreButton.setOnAction((ActionEvent event) -> {
                            Student student = getTableView().getItems().get(getIndex());
                            //todo send review to open window
                            try {
                                new OpenWindow(null, null, null, student, Pages.REVIEW_OF_STUDENT);
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
                            setGraphic(scoreButton);
                    }
                };
            }
        };
        reviewColumn.setCellFactory(cellFactory);
    }

    public void addReviewButtonToTable() {
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student, Void> call(TableColumn<Student, Void> param) {
                return new TableCell<>() {
                    private final Button scoreButton = new Button("خلاصه درس");
                    {
                        //todo if hasn't review disable button.
                        scoreButton.setFont(font);
                        scoreButton.setOnAction((ActionEvent event) -> {
                            Student student = getTableView().getItems().get(getIndex());
                            //todo send review to open window
                            try {
                                new OpenWindow(null, course, null, student, Pages.COURSE_SUMMARY);
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
                            setGraphic(scoreButton);
                    }
                };
            }
        };
        courseSummaryColumn.setCellFactory(cellFactory);
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
}
