package logic.university;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Minor {
    private int id;
    private Student student;
    private Department destination;
    private Boolean originIsAccepted = null;
    private Boolean destinationIsAccepted = null;
    private MinorStatus status = MinorStatus.SUBMITTED;

    public Minor(int id, Student student, Department destination) {
        this.id = id;
        this.student = student;
        this.destination = destination;
    }

    public void updateStutus() {
        if (originIsAccepted && destinationIsAccepted)
            status = MinorStatus.ACCEPTED;
        else if (!originIsAccepted || !destinationIsAccepted)
            status = MinorStatus.REJECTED;
    }

    public StringProperty studentProperty() {
        return new SimpleStringProperty(student.getFirstName() + " " + student.getLastName());
    }

    public StringProperty destinationProperty() {
        return new SimpleStringProperty(destination.getType().toString());
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

    public Department getDestination() {
        return destination;
    }

    public void setDestination(Department destination) {
        this.destination = destination;
    }

    public Boolean getOriginIsAccepted() {
        return originIsAccepted;
    }

    public void setOriginIsAccepted(Boolean originIsAccepted) {
        this.originIsAccepted = originIsAccepted;
    }

    public Boolean getDestinationIsAccepted() {
        return destinationIsAccepted;
    }

    public void setDestinationIsAccepted(Boolean destinationIsAccepted) {
        this.destinationIsAccepted = destinationIsAccepted;
    }

    public MinorStatus getStatus() {
        return status;
    }

    public void setStatus(MinorStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
