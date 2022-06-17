package dataBaseManager;

import com.google.gson.*;
import javafx.collections.ObservableList;
import logic.university.Course;
import logic.university.Professor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import static dataBaseManager.JsonFiles.COURSES;
import static dataBaseManager.JsonFiles.PROFESSORS;

public class AddToList {
    Gson gson = new Gson();



    public ObservableList<Course> addCourse(ObservableList<Course> list) throws FileNotFoundException {
        Course course;
        String fileName = "/Users/omidvarrr/IdeaProjects/EDU/EDU/src/main/resources/Database/" + JsonPathFinder.getName(COURSES);

        Reader reader = new FileReader(fileName);

        JsonElement tree = JsonParser.parseReader(reader);

        JsonArray array = tree.getAsJsonArray();

        for (JsonElement element : array) {
            if (element.isJsonObject()) {
                JsonObject courseJson = element.getAsJsonObject();
                course = gson.fromJson(courseJson.toString(), Course.class);
                list.addAll(course);
            }
        }
        return list;
    }

    public ObservableList<Professor> addProfessor(ObservableList<Professor> list) throws FileNotFoundException {
        Professor professor;
        String fileName = "/Users/omidvarrr/IdeaProjects/EDU/EDU/src/main/resources/Database/" + JsonPathFinder.getName(PROFESSORS);

        Reader reader = new FileReader(fileName);

        JsonElement tree = JsonParser.parseReader(reader);

        JsonArray array = tree.getAsJsonArray();

        for (JsonElement element : array) {
            if (element.isJsonObject()) {
                JsonObject professorJson = element.getAsJsonObject();
                professor = gson.fromJson(professorJson.toString(), Professor.class);
                list.addAll(professor);
            }
        }
        return list;
    }
}
