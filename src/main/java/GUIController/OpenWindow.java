package GUIController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.university.*;

import java.io.IOException;

public class OpenWindow {
    public OpenWindow(Professor professor, Course course, Recommendation recommendation, Student student, Pages page) throws IOException {
        String path = "FXMLs/" + new PagesFinder().getName(page);
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(path));

        Parent root = loader.load();
        if (page == Pages.PROFESSOR_EDIT) {
            ProfessorEditPage editPage = loader.<ProfessorEditPage>getController();
            editPage.setProfessor(professor);
        }
        if (page == Pages.COURSE_EDIT) {
            CourseEditPage editPage = loader.<CourseEditPage>getController();
            editPage.setCourse(course);
        }
        if (page == Pages.RECOMMENDATION_SHOW) {
            ShowRecommendationPage showRecommendationPage = loader.<ShowRecommendationPage>getController();
            showRecommendationPage.setRecommendation(recommendation);
        }

        if (page == Pages.TEMPORARY_SCORES_OF_STUDENT) {
            TemporaryScoresOfStudent temporaryScoresOfStudent = loader.<TemporaryScoresOfStudent>getController();
            temporaryScoresOfStudent.setStudent(student);
        }

        if (page == Pages.COURSE_SUMMARY) {
            CourseSummaryPage courseSummaryPage = loader.<CourseSummaryPage>getController();
            courseSummaryPage.setCourse(course);
        }

        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        //todo maybe after update button this stage must be closed.
    }


}
