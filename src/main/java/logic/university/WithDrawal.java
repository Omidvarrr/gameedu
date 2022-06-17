package logic.university;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WithDrawal {
    int id;
    private Student student;
    private RecommendationResult status;

    public WithDrawal(int id, Student student, RecommendationResult status) {
        this.id = id;
        this.student = student;
        this.status = status;
    }

    public StringProperty statusProperty() {
        return new SimpleStringProperty(status.toString());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RecommendationResult getStatus() {
        return status;
    }

    public void setStatus(RecommendationResult status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
