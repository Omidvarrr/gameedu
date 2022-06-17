package dataBaseManager;

import com.google.gson.*;
import logic.appData;
import logic.university.Professor;
import logic.university.Student;
import logic.university.StudentStatuses;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;


public class UserFinder {
    Gson gson = new Gson();
//
//    public int finder(String enteredNumber, String enteredPassword) throws IOException {
//
//        String fileName = "/Users/omidvarrr/Desktop/edu/src/main/resources/Database/users.json";
//
//        Reader reader = new FileReader(fileName);
//
//        JsonElement tree = JsonParser.parseReader(reader);
//        JsonArray array = tree.getAsJsonArray();
//
//        for (JsonElement element : array) {
//            if (element.isJsonObject()) {
//                JsonObject user = element.getAsJsonObject();
//                    if (user.get("password").getAsString().equals(enteredPassword)){
//                        new SetCurrentUser(user);
//                        return 1;
//                     }
//                }
//            }
//        return 0;
//    }
//
    public int finder(String enteredUserName, String enteredPassword) throws FileNotFoundException {
        ArrayList<Student> students = new Returner().returnStudents();
        for (Student student : students) {
            if (Objects.equals(student.getUsername(), enteredUserName) && Objects.equals(student.getPassword(), enteredPassword)) {
                appData.getInstance().setStudent(student);
                appData.getInstance().setType(UserTypes.STUDENT);
                appData.getInstance().setId(student.getId());
                return 1;
            }
        }
        ArrayList<Professor> professors = new Returner().returnProfessors();
        for (Professor professor : professors) {
            if (Objects.equals(professor.getUsername(), enteredUserName) && Objects.equals(professor.getPassword(), enteredPassword)) {
                appData.getInstance().setProfessor(professor);
                appData.getInstance().setType(UserTypes.PROFESSOR);
                appData.getInstance().setId(professor.getId());
                return 1;
            }
        }
        return -1;
    }
}
