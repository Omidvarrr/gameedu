package dataBaseManager;

import com.google.gson.Gson;
import logic.university.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public class Finder {
    Gson gson = new Gson();

    public Student studentReturner(int id) throws FileNotFoundException {
        ArrayList<Student> students = new Returner().returnStudents();
        for (Student student : students) {
            if (student.getId() == id)
                return student;
        }
        return null;
    }

    public Professor professorReturner(int id) throws FileNotFoundException {
        ArrayList<Professor> professors = new Returner().returnProfessors();
        for (Professor professor : professors) {
            if (professor.getId() == id)
                return professor;
        }
        return null;
    }

    public Long gradeFinder(Course course, int studentId) {
        for (Map.Entry<Integer, Long> grades : course.getGrades().entrySet()) {
            if (grades.getKey() == studentId) {
                return grades.getValue();
            }
        }
        return null;
    }

    public ArrayList<Integer> studentsOfCourseFinder(Course course) throws FileNotFoundException {
        ArrayList<Integer> students = new ArrayList<>();
        for (Map.Entry<Integer, Long> grades : course.getGrades().entrySet()) {
            students.add(grades.getKey());
        }
        return students;
    }

    public Boolean checkStudentExistInCourse(Course course, Student student) throws FileNotFoundException {
        ArrayList<Integer> students = studentsOfCourseFinder(course);
        for (Integer id : students) {
            if (id == student.getId())
                return true;
        }
        return false;
    }

    public ArrayList<Course> findCouresOfStudent(Student student) throws FileNotFoundException {
        ArrayList<Course> studentCourses = new ArrayList<>();
        for (Course course : (new Returner().returnCourses())) {
            if (checkStudentExistInCourse(course, student)) {
                studentCourses.add(course);
            }
        }
        return studentCourses;
    }

    public ArrayList<Professor> professorOfStudentsFinder(Student student) throws FileNotFoundException {
        ArrayList<Professor> professors = new ArrayList<>();
        for (Course course : findCouresOfStudent(student)) {
            if (!professors.contains(course.getTeacher()))
                professors.add(course.getTeacher());
        }
        return professors;
    }

    public ArrayList<Course> findCoursesWithTemporaryScores(Student student) throws FileNotFoundException {
        ArrayList<Course> tempraryScoreCourses = new ArrayList<>();
        ArrayList<Course> allCourses = new Returner().returnCourses();
        for (Course course : allCourses) {
            if (studentsOfCourseFinder(course).contains(student.getId()) && course.getScoreStatus() == ScoreStatus.TEMPORARY) {
                tempraryScoreCourses.add(course);
            }
        }
        return tempraryScoreCourses;
    }

    public ArrayList<Student> studentObjectOfCourse(Course course) throws FileNotFoundException {
        ArrayList<Integer> studentsId = studentsOfCourseFinder(course);
        ArrayList<Student> students = new ArrayList<>();
        for (Integer id : studentsId) {
            students.add(studentReturner(id));
        }
        return students;
    }


}
