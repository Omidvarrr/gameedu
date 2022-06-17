package logic.university;

public class Review {
    private Course course;
    private Student student;
    private Professor teacher;
    private String reviewText;
    private String replyText;
    private Boolean isReplyed;

    public Review(Course course, Student student, Professor teacher, String reviewText, String replyText, Boolean isReplyed) {
        this.course = course;
        this.student = student;
        this.teacher = teacher;
        this.reviewText = reviewText;
        this.replyText = replyText;
        this.isReplyed = isReplyed;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Professor getTeacher() {
        return teacher;
    }

    public void setTeacher(Professor teacher) {
        this.teacher = teacher;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public Boolean getReplyed() {
        return isReplyed;
    }

    public void setReplyed(Boolean replyed) {
        isReplyed = replyed;
    }
}
