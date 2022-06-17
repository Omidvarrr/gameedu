package logic;

import com.google.gson.JsonObject;
import logic.university.Professor;
import logic.university.Student;
import logic.university.User;
import logic.university.UserTypes;

public class appData {
    public Student student;
    public Professor professor;
    private JsonObject userJson;
    static appData currentUser;
    private UserTypes type;
    private int id;

    public static appData getInstance() {
        if (currentUser == null)
            currentUser = new appData();
        return currentUser;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public JsonObject getUserJson() {
        return userJson;
    }

    public User getUser() {
        if (type == UserTypes.STUDENT)
            return (User) student;
        else
            return (User) professor;
    }

    public void setUserJson(JsonObject userJson) {
        this.userJson = userJson;
    }

    public static void setCurrentUser(appData currentUser) {
        appData.currentUser = currentUser;
    }

    public UserTypes getType() {
        return type;
    }

    public void setType(UserTypes type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
