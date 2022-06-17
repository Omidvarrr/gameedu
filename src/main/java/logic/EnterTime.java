package logic;

import logic.university.Student;
import logic.university.UserTypes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnterTime {

    public String currentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setEnterTime(UserTypes type, int id) throws IOException {
        SaveUser saveUser = new SaveUser();
        if (type == UserTypes.STUDENT) {

            Student student = saveUser.loadStudent(id);
            student.setEnterTime(currentTime());
            saveUser.saveAStudent(student);

        }



    }
}
