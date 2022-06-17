package GUIController;

import dataBaseManager.Finder;
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
import logic.appData;
import logic.university.Course;
import logic.university.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TemporaryScoresStudentSidePage implements Initializable {
    private final Font font = Constants.constants.font;
    private final Background background = Constants.constants.themeReturner();
    private ObservableList<Course> courses = FXCollections.observableArrayList();

    Student student;

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label1, label2, label3, label5;
    @FXML
    TableView<Course> temporaryScoresTable;
    @FXML
    TableColumn<Course, String> courseTitleColumn, temporaryScoreColumn;
    @FXML
    TableColumn<Course, Void> reviewColumn, editScoreColumn;
    @FXML
    Button finalSubmitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        student = (Student) appData.getInstance().getUser();
        setFonts();
        setColor();
        iniializeColumns();
        try {
            setCoursesToTable();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        addReviewButtonToTable();
//        addReplyToReviewButtonToTable();
        addEditScoreButtonToTable();
    }

    public void iniializeColumns() {
        courseTitleColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        temporaryScoreColumn.setCellValueFactory(cellDate -> cellDate.getValue().gradeProperty(student.getId()));

        temporaryScoresTable.setItems(courses);
    }

    public void setCoursesToTable() throws FileNotFoundException {
        courses.addAll(new Finder().findCoursesWithTemporaryScores(student));
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

//    public void addReplyToReviewButtonToTable() {
//        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<>() {
//            @Override
//            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
//                return new TableCell<>() {
//                    private final Button replyButton = new Button("مشاهده/پاسخ به اعتراض");
//                    {
//                        replyButton.setFont(font);
//                        replyButton.setOnAction((ActionEvent event) -> {
//                            Course course = getTableView().getItems().get(getIndex());
//
//                        });
//                    }
//
//                    @Override
//                    protected void updateItem(Void item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty)
//                            setGraphic(null);
//                        else
//                            setGraphic(replyButton);
//                    }
//                };
//            }
//        };
//        replyToReviewColumn.setCellFactory(cellFactory);
//    }

    public void addEditScoreButtonToTable() {
        Callback<TableColumn<Course, Void>, TableCell<Course, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Course, Void> call(TableColumn<Course, Void> param) {
                return new TableCell<>() {
                    private final Button editButton = new Button("ویرایش");
                    {
                        editButton.setFont(font);
                        editButton.setOnAction((ActionEvent event) -> {
                            Course course = getTableView().getItems().get(getIndex());
                            try {
                                new OpenWindow(null, course, null, null, Pages.EDIT_SCORE);
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
                            setGraphic(editButton);
                    }
                };
            }
        };
        editScoreColumn.setCellFactory(cellFactory);
    }

    public void setFonts() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label5.setFont(font);

        finalSubmitButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void finalSubmit() {
    }
}
