package logic.university;

import dataBaseManager.Finder;
import dataBaseManager.Returner;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Recommendation {
    int id;
    private Student student;
    private Professor teacher;
    private RecommendationResult result = RecommendationResult.pending;
    private ArrayList<Course> commonCoureses;

    public Recommendation(int id, Student student, Professor teacher, RecommendationResult result){
        this.id = id;
        this.student = student;
        this.teacher = teacher;
    }

    public StringProperty studentProperty() {
        return new SimpleStringProperty(student.getFirstName() + " " + student.getLastName());
    }

    public StringProperty teacherProperty() {
        return new SimpleStringProperty(teacher.getFirstName() + " " + teacher.getLastName());
    }

    public StringProperty resultProperty() {
        return new SimpleStringProperty(result.toString());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getTeacher() {
        return teacher;
    }

    public void setTeacher(Professor teacher) {
        this.teacher = teacher;
    }

    public RecommendationResult getResult() {
        return result;
    }

    public void setResult(RecommendationResult result) {
        this.result = result;
    }

    public ArrayList<Course> getCommonCoureses() {
        return commonCoureses;
    }

    public void setCommonCoureses(ArrayList<Course> commonCoureses) {
        this.commonCoureses = commonCoureses;
    }

    public void findCommonCourses() throws FileNotFoundException {
        ArrayList<Course> commonCoureses = new ArrayList<>();
        ArrayList<Course> courses = new Returner().returnCourses();
        for (Course course : courses) {
            if (course.getTeacher() == teacher && new Finder().studentsOfCourseFinder(course).contains(student)) {
                commonCoureses.add(course);
            }
        }
        this.commonCoureses = commonCoureses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
