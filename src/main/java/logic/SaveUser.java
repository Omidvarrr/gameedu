package logic;

import GUIController.Constants;
import GUIController.RecommendationPage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.university.*;

import java.io.*;
import java.util.Scanner;

public class SaveUser {



    public void saveAStudent(Student student) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String studentJson = gson.toJson(student);
        FileWriter writer = new FileWriter(Constants.getInstance().studentPath + student.getId() + ".json");
        writer.write(studentJson);
        writer.close();
    }

    public void saveAProfessor(Professor professor) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String professorJson = gson.toJson(professor);
        FileWriter writer = new FileWriter(Constants.getInstance().professorPath + professor.getId() + ".json");
        writer.write(professorJson);
        writer.close();
    }

    public void saveACourse(Course course) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String courseJson = gson.toJson(course);
        FileWriter writer = new FileWriter(Constants.getInstance().coursePath + course.getName() + ".json");
        writer.write(courseJson);
        writer.close();
    }

    public void saveADepartment(Department department) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String professorJson = gson.toJson(department);
        FileWriter writer = new FileWriter(Constants.getInstance().departmentPath + department.getType().toString() + ".json");
        writer.write(professorJson);
        writer.close();
    }

    public void saveRecommendation(Recommendation recommendation) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String recommendationJson = gson.toJson(recommendation);
        FileWriter writer = new FileWriter(Constants.getInstance().recommendationPath + recommendation.getId() + ".json");
        writer.write(recommendationJson);
        writer.close();
    }

    public void saveWithdrawal(WithDrawal withDrawal) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String withdrawalJson = gson.toJson(withDrawal);
        FileWriter writer = new FileWriter(Constants.getInstance().withDrawalPath + withDrawal.getId() + ".json");
        writer.write(withdrawalJson);
        writer.close();
    }

    public void saveDorm(Dorm dorm) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String dormJson = gson.toJson(dorm);
        FileWriter writer = new FileWriter(Constants.getInstance().dormPath + dorm.getId() + ".json");
        writer.write(dormJson);
        writer.close();
    }

    public void saveMinor(Minor minor) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String minorJson = gson.toJson(minor);
        FileWriter writer = new FileWriter(Constants.getInstance().minorPath + minor.getId() + ".json");
        writer.write(minorJson);
        writer.close();
    }

    public Student loadStudent(int id) {
        try {
            File file = new File(Constants.getInstance().studentPath + id + ".json");
            Student student;

            Scanner scanner = new Scanner(file);
            String studentJson = "";
            while (scanner.hasNext()) {
                studentJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            student = gson.fromJson(studentJson, Student.class);

            return student;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Professor loadProfessor(int id) {
        try {
            File file = new File(Constants.getInstance().professorPath + id + ".json");
            Professor professor;

            Scanner scanner = new Scanner(file);
            String professorJson = "";
            while (scanner.hasNext()) {
                professorJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            professor = gson.fromJson(professorJson, Professor.class);

            return professor;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WithDrawal loadWithdrawal(int id) {
        try {
            File file = new File(Constants.getInstance().withDrawalPath + id + ".json");
            WithDrawal withDrawal;

            Scanner scanner = new Scanner(file);
            String dormJson = "";
            while (scanner.hasNext()) {
                dormJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            withDrawal = gson.fromJson(dormJson, WithDrawal.class);

            return withDrawal;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dorm loadDorm(int id) {
        try {
            File file = new File(Constants.getInstance().dormPath + id + ".json");
            Dorm dorm;

            Scanner scanner = new Scanner(file);
            String dormJson = "";
            while (scanner.hasNext()) {
                dormJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            dorm = gson.fromJson(dormJson, Dorm.class);

            return dorm;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public Recommendation loadRecommendation(int id) {
        try {
            File file = new File(Constants.getInstance().recommendationPath + id + ".json");
            Recommendation recommendation;

            Scanner scanner = new Scanner(file);
            String professorJson = "";
            while (scanner.hasNext()) {
                professorJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            recommendation = gson.fromJson(professorJson, Recommendation.class);

            return recommendation;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Department loadDepartment(String name) {
        try {
            File file = new File(Constants.getInstance().departmentPath + name + ".json");
            Department department;

            Scanner scanner = new Scanner(file);
            String departmentJson = "";
            while (scanner.hasNext()) {
                departmentJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            department = gson.fromJson(departmentJson, Department.class);

            return department;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Course loadCourse(String name) {
        try {
            File file = new File(Constants.getInstance().coursePath + name + ".json");
            Course course;

            Scanner scanner = new Scanner(file);
            String courseJson = "";
            while (scanner.hasNext()) {
                courseJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            course = gson.fromJson(courseJson, Course.class);

            return course;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Minor loadMinor(int id) {
        try {
            File file = new File(Constants.getInstance().minorPath + id + ".json");
            Minor minor;

            Scanner scanner = new Scanner(file);
            String minorJson = "";
            while (scanner.hasNext()) {
                minorJson += scanner.nextLine();
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            Gson gson = gsonBuilder.create();
            minor = gson.fromJson(minorJson, Minor.class);

            return minor;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(Student student) {
        try {
            File file = new File(Constants.getInstance().studentPath + student.getId() + ".json");
            file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteProfessor(Professor professor) {
        try {
            File file = new File(Constants.getInstance().professorPath + professor.getId() + ".json");
            file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(Course course) {
        try {
            File file = new File(Constants.getInstance().coursePath + course.getName() + ".json");
            file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getProfessorCount() throws FileNotFoundException {
        String folderPath = Constants.getInstance().professorPath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

    public int getStudentCount() {
        String folderPath = Constants.constants.studentPath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

    public int getDepartmentCount() {
        String folderPath = Constants.constants.departmentPath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

    public int getCourseCount() {
        String folderPath = Constants.constants.coursePath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

    public int getRecommendationCount() {
        String folderPath = Constants.constants.recommendationPath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

    public int getWithdrawalCount() {
        String folderPath = Constants.constants.withDrawalPath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

    public int getDormCount() {
        String folderPath = Constants.constants.dormPath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

    public int getMinorCount() {
        String folderPath = Constants.constants.minorPath;

        File folder = new File(folderPath);
        return folder.list().length;
    }

}


