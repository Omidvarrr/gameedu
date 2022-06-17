package logic.university;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Professor extends User {
    private int roomNumber;
    private ProfessorGrades grade;
    private Boolean chief = false;
    private Boolean educationalAssistant = false;


    public Professor(int id, String number, String password, String firstName, String lastName, String nationalCode,
                     String email, String phoneNumber, Department department, int roomNumber, ProfessorGrades grade,
                     Boolean chief, Boolean educationalAssistant, UserTypes type) {
        super(id, number, password, firstName, lastName, nationalCode, email, phoneNumber, department, type);
        this.roomNumber = roomNumber;
        this.grade = grade;
        this.chief = chief;
        this.educationalAssistant = educationalAssistant;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(getFirstName() + " " + getLastName());
    }

    public StringProperty departmentProperty() {
        return new SimpleStringProperty(getDepartment().getType().toString());
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public ProfessorGrades getGrade() {
        return grade;
    }

    public void setGrade(ProfessorGrades grade) {
        this.grade = grade;
    }

    public Boolean getChief() {
        return chief;
    }

    public void setChief(Boolean chief) {
        this.chief = chief;
    }

    public Boolean getEducationalAssistant() {
        return educationalAssistant;
    }

    public void setEducationalAssistant(Boolean educationalAssistant) {
        this.educationalAssistant = educationalAssistant;
    }

}
