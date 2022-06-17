package logic.university;

import dataBaseManager.Finder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private String name;
    private int credit;
    private String id;
    private String departmentName;
    private String teacherName;
    private Department department;
    private Professor teacher;
    private StudentGrades grade;
    private Course prerequisites;
    private ArrayList<Days> days;
    private String time;
    private String testDate;
    private HashMap<Integer, Long> grades;
    private ScoreStatus scoreStatus;

    public Course(String name, int credit, String id, Department department, Professor teacher, StudentGrades grade, Course prerequisites, ArrayList<Days> days, String time,
                  String testDate, HashMap<Integer, Long> grades, ScoreStatus scoreStatus) {
        this.name = name;
        this.credit = credit;
        this.id = id;
        this.department = department;
        this.teacher = teacher;
        this.grade = grade;
        this.prerequisites = prerequisites;
        this.days = days;
        this.time = time;
        this.testDate = testDate;
        this.grades = grades;
        this.scoreStatus = scoreStatus;
    }

    public StringProperty departmentProperty() {
        return new SimpleStringProperty(department.getType().toString());
    }

    public StringProperty teacherNameProperty() {
        return new SimpleStringProperty(teacher.getFirstName() + " " + teacher.getLastName());
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(name);
    }

    public StringProperty timeProperty() {
        return new SimpleStringProperty(name + " " + time);
    }

    public StringProperty testDateProperty() {
        return new SimpleStringProperty(testDate);
    }

    public StringProperty gradeProperty(int studentId) {
        return new SimpleStringProperty((new Finder().gradeFinder(this, studentId)).toString());
    }

    public long average() {
        int sum = 0;
        long count = 0;
        for (Map.Entry<Integer, Long> grades : getGrades().entrySet()) {
            sum += grades.getValue();
            count += 1;
        }
        return sum/count;
    }

    public int rejectCount() {
        int rejectCount = 0;
        for (Map.Entry<Integer, Long> grades : getGrades().entrySet()) {
            if (grades.getValue() < 10)
                rejectCount += 1;
        }
        return rejectCount;
    }

    public int acceptCount() {
        return grades.size() - rejectCount();
    }

    public long pureAverage() {
        int count = 0;
        long sum = 0;
        for (Map.Entry<Integer, Long> grades : getGrades().entrySet()) {
            if (grades.getValue() >= 10) {
                count += 1;
                sum += grades.getValue();
            }
        }
        return sum/count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Professor getTeacher() {
        return teacher;
    }

    public void setTeacher(Professor teacher) {
        this.teacher = teacher;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public StudentGrades getGrade() {
        return grade;
    }

    public void setGrade(StudentGrades grade) {
        this.grade = grade;
    }

    public Course getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Course prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList<Days> getDays() {
        return days;
    }

    public void setDays(ArrayList<Days> days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public HashMap<Integer, Long> getGrades() {
        return grades;
    }

    public void setGrades(HashMap<Integer, Long> grades) {
        this.grades = grades;
    }

    public ScoreStatus getScoreStatus() {
        return scoreStatus;
    }

    public void setScoreStatus(ScoreStatus scoreStatus) {
        this.scoreStatus = scoreStatus;
    }
}
