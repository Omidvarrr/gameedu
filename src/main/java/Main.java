import GUIController.Constants;
import com.google.gson.Gson;
import dataBaseManager.JsonFiles;
import dataBaseManager.JsonPathFinder;
import dataBaseManager.Returner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.SaveUser;
import logic.university.*;
import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

//    public String removeJsonSuffix(String jsonName) {
//        String name = "";
//        int size = jsonName.length() - 4;
//        int index = 0;
//        while (index < size) {
//            name += jsonName.charAt(index);
//            index += 1;
//        }
//        return name;
//    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/login.fxml"));

        ArrayList<Student> students = new Returner().returnStudents();
        ArrayList<Professor> professors = new Returner().returnProfessors();
        ArrayList<Course> courses = new Returner().returnCourses();
        ArrayList<Department> departments = new Returner().returnDepartments();

        for (Student student : students) {
            new SaveUser().saveAStudent(student);
        }
        for (Professor professor : professors) {
            new SaveUser().saveAProfessor(professor);
        }
        for (Department department1 : departments) {
            new SaveUser().saveADepartment(department1);
        }
        for (Course course2 : courses) {
            new SaveUser().saveACourse(course2);
        }
        Professor professor = new Returner().returnProfessors().get(0);
        professor.setEducationalAssistant(true);
        Gson gson = new Gson();
        System.out.println("--------------");
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


}
