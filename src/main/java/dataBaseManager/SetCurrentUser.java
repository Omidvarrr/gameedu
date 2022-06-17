package dataBaseManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import logic.appData;
import logic.university.Professor;
import logic.university.Student;
import logic.university.User;
import logic.university.UserTypes;

import javax.swing.text.Style;
import java.util.Objects;

public class SetCurrentUser {
    Gson gson = new Gson();

    public SetCurrentUser(JsonObject userJson) {
        if (userJson.get("type").getAsString().equals("STUDENT")) {
            Student student = createStudent(userJson);
        }
        else if (userJson.get("type").getAsString().equals("PROFESSOR")) {
            Professor professor = createProfessor(userJson);
        }
    }



    public Student createStudent(JsonObject studentJson) {
        return gson.fromJson(studentJson.toString(), Student.class);
    }

    public Professor createProfessor(JsonObject professorJson) {
        return gson.fromJson(professorJson.toString(), Professor.class);
    }
}
