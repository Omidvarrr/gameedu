package GUIController;

public class PagesFinder {
    public String getName(Pages page) {
        return switch (page) {
            case MAIN_MENU -> "mainMenu.fxml";
            case LOGIN_PAGE -> "login.fxml";
            case COURSES_LIST -> "courseList.fxml";
            case COURSE_EDIT -> "courseEdit.fxml";
            case COURSE_CREATE -> "courseAdd.fxml";
            case PROFESSOR_LIST -> "professorList.fxml";
            case PROFESSOR_EDIT -> "professorEdit.fxml";
            case PROFESSOR_CREATE -> "professorAdd.fxml";
            case WEEKLY_SCHEDULE -> "weeklySchedule.fxml";
            case REQUESTS -> "requests.fxml";
            case EXAMS -> "exams.fxml";
            case RECOMMENDATION -> "recommendation.fxml";
            case RECOMMENDATION_SHOW -> "showRecommendation.fxml";
            case RECOMMENDATION_CREATE -> "createReceommendation.fxml";
            case CERTIFICATE_STUDENT -> "certificateStudent.fxml";
            case MINOR -> "minor.fxml";
            case MINOR_REQUEST -> "minorRequest.fxml";
            case WITHDRAWAL -> "withdrawalFromEducation.fxml";
            case WITHDRAWAL_REQUEST -> "withdrawalRequest.fxml";
            case DORM -> "dorm.fxml";
            case THESIS_DEFENCE -> "thesisDefense.fxml";
            case REQUEST_BAR -> "requestBar.fxml";
            case TEMPORARY_SCORES_STUDENT_SIDE -> "temporaryScoresStudentSide.fxml";
            case EDIT_SCORE -> "editScore.fxml";
            case REVIEW_STUDENT_SIDE -> "reviewStudentSide.fxml";
            case TEMPORARY_SCORES_PEOFESSOR_SIDE -> "temporaryScoresProfessorSide.fxml";
            case REVIEW_PROFESSOR_SIDE -> "reviewProfessorSide.fxml";
            case TEMPORARY_SCORES_EDUCATIONAL_ASISTANT_SIDE -> "temporaryScoresEducationalAsistantSide.fxml";
            case TEMPORARY_SCORES_OF_STUDENT -> "temporaryScoresOfStudent.fxml";
            case REVIEW_OF_STUDENT -> "reviewOfStudent.fxml";
            case COURSE_SUMMARY -> "courseSummary.fxml";
            case HOME_PAGE -> "homePage.fxml";
        };
    }
}
