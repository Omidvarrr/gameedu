package dataBaseManager;

import static dataBaseManager.JsonFiles.*;

public class JsonPathFinder {
    public static String getName(JsonFiles file) {
        return switch (file) {
            case COURSES -> "courses.json";
            case PROFESSORS, STUDENTS -> "users.json";
            case DEPARTMENTS -> "departments.json";
            case ENTER_TIME -> "enterTime.json";
        };
    }
}
