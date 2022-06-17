package GUIController;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import logic.appData;
import logic.university.Professor;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {
    Background background = Constants.getInstance().themeReturner();

    @FXML
    public AnchorPane anchorPane;
    @FXML
    Label mainMenuTimer, mainMenuNameLabel, mainMenuEmailLabel, mainMenuLastVisitLabel;
    @FXML
    HBox HBox1, HBox2, HBox3, HBox4, HBox5, HBox6, HBox7, HBox8;
    @FXML
    BorderPane borderPane;

    public MainMenu() throws FileNotFoundException {
    }

    public void setColor() throws FileNotFoundException {
        HBox1.setBackground(background);
        HBox2.setBackground(background);
        HBox3.setBackground(background);
        HBox4.setBackground(background);
        HBox5.setBackground(background);
        HBox6.setBackground(background);
        HBox7.setBackground(background);
        HBox8.setBackground(background);
    }

    public void setTimer() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mainMenuTimer.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm:ss")));
            }
        };
        timer.start();
    }

//    public void setUserProfile() {
//        String name = appData.getInstance().getUser().getFirstName() + appData.getInstance().getUser().getLastName();
//        String email = appData.getInstance().getUser().getEmail();
//        mainMenuNameLabel.setText(name);
//        mainMenuEmailLabel.setText(email);
//    }



    public void goToHome() throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.HOME_PAGE);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }




    public void goToCoursesListPage(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.COURSES_LIST);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);//        new GoToPage(event, Pages.COURSES_LIST);
    }

    public void goToProfessorsListPage(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.PROFESSOR_LIST);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void goToWeeklySchedule(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.WEEKLY_SCHEDULE);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void goToExamDatePage(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.EXAMS);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void openRequestsPage(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.REQUEST_BAR);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
//        new OpenWindow(null, null, null, Pages.REQUESTS);
    }

    public void openTemporaryScoresPage(ActionEvent event) throws  IOException {
        Node node;
        if (appData.getInstance().getType() == UserTypes.PROFESSOR) {
            if (appData.getInstance().getProfessor().getEducationalAssistant()) {
                node = new LoaderFXMLs().loadFXML(Pages.TEMPORARY_SCORES_EDUCATIONAL_ASISTANT_SIDE);
            }
            else {
                node = new LoaderFXMLs().loadFXML(Pages.TEMPORARY_SCORES_PEOFESSOR_SIDE);
            }
        }
        else {
            node = new LoaderFXMLs().loadFXML(Pages.TEMPORARY_SCORES_STUDENT_SIDE);
        }
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setColor();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setTimer();
        try {
            goToHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
