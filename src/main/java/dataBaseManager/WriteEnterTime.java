package dataBaseManager;

import logic.appData;
import logic.university.UserTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class WriteEnterTime {


    public void writer() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (appData.getInstance().getType() == UserTypes.STUDENT) {
            appData.getInstance().student.setEnterTime(time);
        } else {
            appData.getInstance().professor.setEnterTime(time);
        }
    }
}


