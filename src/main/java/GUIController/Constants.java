package GUIController;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Constants {
    public Color theme = Color.RED;
    public static Constants constants;
    public List<String> validCaptcha;
    public String captchaImagePath = "Files/CaptchaImages/";
    public String color = "e1e1e1";
    public Font font = Font.loadFont(new FileInputStream(new File("/Users/omidvarrr/Desktop/edu/src/main/resources/Font/Estedad-Regular.ttf")),13);
    private final String databasePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Database" + File.separator;
    public String studentPath = databasePath + "students" + File.separator;
    public String professorPath = databasePath + "professors" + File.separator;
    public String coursePath = databasePath + "courses" + File.separator;
    public String departmentPath = databasePath + "departments" + File.separator;
    public String recommendationPath = databasePath + "recommendation" + File.separator;
    public String dormPath = databasePath + "dorm" + File.separator;
    public String withDrawalPath = databasePath + "Withdrawal" + File.separator;
    public String minorPath = databasePath + "Minor" + File.separator;




    public Constants() throws FileNotFoundException {
        setValidChapta();
    }


    public static Constants getInstance() throws FileNotFoundException {
        if (constants == null)
            constants = new Constants();
        return constants;
    }

    public Background themeReturner() {
        Background background = new Background(new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY));
        return background;
    }

    public void setValidChapta() {
        validCaptcha = new ArrayList<>();
        validCaptcha.add("5029");
        validCaptcha.add("1706");
        validCaptcha.add("3141");
        validCaptcha.add("5865");
        validCaptcha.add("7949");
        validCaptcha.add("8838");
        validCaptcha.add("3948");
        validCaptcha.add("9131");
        validCaptcha.add("6857");
        validCaptcha.add("1256");
    }


}

