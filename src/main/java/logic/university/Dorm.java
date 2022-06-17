package logic.university;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Dorm {
    int id;
    private Student student;
    private RecommendationResult status;

    public Dorm(int id, Student student) {
        this.id = id;
        this.student = student;
        this.status = randomChooseStatus();
    }

    public RecommendationResult randomChooseStatus() {
        ArrayList<RecommendationResult> recommendationResults = arrayListGenerator();
        int resultId = ThreadLocalRandom.current().nextInt(0, 1);
        return recommendationResults.get(resultId);
    }

    public ArrayList<RecommendationResult> arrayListGenerator() {
        ArrayList<RecommendationResult> recommendationResults = new ArrayList<>();
        recommendationResults.add(RecommendationResult.accpeted);
        recommendationResults.add(RecommendationResult.rejected);
        return recommendationResults;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RecommendationResult getStatus() {
        return status;
    }

    public void setStatus(RecommendationResult status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
