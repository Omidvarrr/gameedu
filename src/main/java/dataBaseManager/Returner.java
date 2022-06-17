package dataBaseManager;

import GUIController.Constants;
import com.google.gson.*;
import logic.SaveUser;
import logic.university.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Returner {
    Gson gson = new Gson();


    public ArrayList<Department> returnDepartments() throws FileNotFoundException {
        ArrayList<Department> departments = new ArrayList<>();
        String folderPath = Constants.getInstance().departmentPath;

        File folder = new File(folderPath);
        String contents[] = folder.list();
        Department department;
        for (int i = 0; i < contents.length; i++) {
            department = new SaveUser().loadDepartment(removeJsonSuffix(contents[i]));
            departments.add(department);
        }
        return departments;
    }

    public ArrayList<Recommendation> returnRecommendation() {
        ArrayList<Recommendation> recommendations = new ArrayList<>();
        Recommendation recommendation;
        for (int i = 1; i < new SaveUser().getRecommendationCount()+1; i++) {
            recommendation = new SaveUser().loadRecommendation(i);
            recommendations.add(recommendation);
        }
        return recommendations;
    }

    public ArrayList<Dorm> returnDorms() {
        ArrayList<Dorm> dorms = new ArrayList<>();
        Dorm dorm;
        for (int i = 1; i < new SaveUser().getDormCount()+1; i++) {
            dorm = new SaveUser().loadDorm(i);
            dorms.add(dorm);
        }
        return dorms;
    }

    public ArrayList<WithDrawal> returnWithdrawal() {
        ArrayList<WithDrawal> withDrawals = new ArrayList<>();
        WithDrawal withDrawal;
        for (int i = 1; i < new SaveUser().getWithdrawalCount()+1; i++) {
            withDrawal = new SaveUser().loadWithdrawal(i);
            withDrawals.add(withDrawal);
        }
        return withDrawals;
     }


    public ArrayList<Professor> returnProfessors() throws FileNotFoundException {
        ArrayList<Professor> professors = new ArrayList<>();
        String folderPath = Constants.getInstance().professorPath;

        File folder = new File(folderPath);
        String contents[] = folder.list();
        Professor professor;
        for (int i = 0; i < contents.length; i++) {
            professor = new SaveUser().loadProfessor(Integer.parseInt(removeJsonSuffix(contents[i])));
            professors.add(professor);
        }
        return professors;
    }

    public ArrayList<Course> returnCourses() throws FileNotFoundException {
        ArrayList<Course> courses = new ArrayList<>();
        String folderPath = Constants.getInstance().coursePath;

        File folder = new File(folderPath);
        String contents[] = folder.list();
        Course course;
        for (int i = 0; i < contents.length ; i++) {
            course = new SaveUser().loadCourse(removeJsonSuffix(contents[i]));
//            System.out.println(removeJsonSuffix());
            courses.add(course);
        }
        return courses;
    }

    public ArrayList<Student> returnStudents() throws FileNotFoundException {
        ArrayList<Student > students = new ArrayList<>();
        String folderPath = Constants.getInstance().studentPath;

        File folder = new File(folderPath);
        String contents[] = folder.list();
        Student student;
        for (int i = 0; i < contents.length; i++) {
            student = new SaveUser().loadStudent(Integer.parseInt(removeJsonSuffix(contents[i])));
            students.add(student);
        }
        return students;
    }

    public ArrayList<Minor> returnMinors() throws FileNotFoundException {
        ArrayList<Minor> minors = new ArrayList<>();
        String folderPath = Constants.getInstance().minorPath;

        File folder = new File(folderPath);
        Minor minor;
        for (int i = 1; i < new SaveUser().getMinorCount()+1; i++) {
            minor = new SaveUser().loadMinor(i);
            minors.add(minor);
        }
        return minors;
    }


    public String removeJsonSuffix(String jsonName) {
        String name = "";
        int size = jsonName.length() - 5;
        int index = 0;
        while (index < size) {
            name += jsonName.charAt(index);
            index += 1;
        }
        return name;
    }

}
