package GUIController;

import javafx.event.ActionEvent;

import java.io.IOException;

public class RequestsPage {

    public void goToRecommendationPage(ActionEvent event) throws IOException {
        new GoToPage(event, Pages.RECOMMENDATION);
    }

    public void goToCertificateStudentPage(ActionEvent event) throws IOException {
        new GoToPage(event, Pages.CERTIFICATE_STUDENT);
    }

    public void goToMinorPage(ActionEvent event) throws IOException {
        new GoToPage(event, Pages.MINOR);
    }

    public void goToWithdrawalFromEducation(ActionEvent event) throws IOException {
        new GoToPage(event, Pages.WITHDRAWAL);
    }

    public void goToDormPage(ActionEvent event) throws IOException {
        new GoToPage(event, Pages.DORM);
    }

    public void goToThesisDefenePage(ActionEvent event) throws IOException{
        new GoToPage(event, Pages.THESIS_DEFENCE);
    }
}
