package logic.university;

import dataBaseManager.Finder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Student extends User {
    private StudentGrades grade;
    private StudentStatuses status;
    private Integer gradePointAverage;
    private Professor supervisor;
    private int enteringYear;
    private Boolean registerLicense;
    private String registerTime;

    public Student(int id, String firstName, String lastName, String number, String password, String nationalCode,
                   String email, String phoneNumber, Department department, StudentGrades grade, StudentStatuses status,
                   Integer gradePointAverage, Professor supervisor, int enteringYear, UserTypes type) {
        super(id, firstName, lastName, number, password, nationalCode, email, phoneNumber, department, type);
        this.grade = grade;
        this.status = status;
        this.gradePointAverage = gradePointAverage;
        this.supervisor = supervisor;
        this.enteringYear = enteringYear;
    }

    public StringProperty scoreProperty(Course course) {
        return new SimpleStringProperty((new Finder().gradeFinder(course, getId())).toString());
    }

    public StringProperty numberProperty() {
        return new SimpleStringProperty(getUsername());
    }

//    public StringProperty professorNameProperty(Course course) {
//        return new SimpleStringProperty(course.getTeacher().nameProperty()
//    }

    public void setScore(Course course, String score) {
        //todo
        course.getGrades().put(getId(), Long.parseLong(score));
    }

    public StudentGrades getGrade() {
        return grade;
    }

    public void setGrade(StudentGrades grade) {
        this.grade = grade;
    }

    public StudentStatuses getStatus() {
        return status;
    }

    public void setStatus(StudentStatuses status) {
        this.status = status;
    }

    public int getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(int gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    public Professor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Professor supervisor) {
        this.supervisor = supervisor;
    }

    public int getEnteringYear() {
        return enteringYear;
    }

    public void setEnteringYear(int enteringYear) {
        this.enteringYear = enteringYear;
    }

    public void setGradePointAverage(Integer gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    public Boolean getRegisterLicense() {
        return registerLicense;
    }

    public void setRegisterLicense(Boolean registerLicense) {
        this.registerLicense = registerLicense;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}