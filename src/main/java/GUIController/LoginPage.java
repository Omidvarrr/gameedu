package GUIController;

import dataBaseManager.UserFinder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.EnterTime;
import logic.appData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class LoginPage implements Initializable {

    int captchaImageId;
    String path;
    Boolean captchaResult;
    Boolean userAndPassResult;
    UserFinder userFinder = new UserFinder();



    @FXML
    TextField loginPageUserNameField, loginPagePasswordField, loginPageCaptchaField;
    @FXML
    ImageView loginPageCaptchaImage;
    @FXML
    Label captchaWrongWarning, userNotFoundWarning;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            captchaLoader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void captchaLoader() throws FileNotFoundException {

        captchaImageId = ThreadLocalRandom.current().nextInt(1, 11);

        path = Constants.getInstance().captchaImagePath;
        path += captchaImageId + ".jpg";

        File captcha = new File(path);

        Image img = new Image(String.valueOf(captcha));
        loginPageCaptchaImage.setImage(img);

    }

    public void captchaChecker() throws FileNotFoundException {
        String enteredCaptcha = loginPageCaptchaField.getText();
        captchaResult = Constants.getInstance().validCaptcha.get(captchaImageId-1).equals(enteredCaptcha);
    }

    public void userAndPassChecker() throws IOException {
        String username = loginPageUserNameField.getText();
        String password = loginPagePasswordField.getText();

        userAndPassResult = userFinder.finder(username, password) == 1;
    }

    public void warningText() {
        if (!captchaResult)
            captchaWrongWarning.setText("⚠️ cpathca is wrong.");
        if (!userAndPassResult)
            userNotFoundWarning.setText("⚠️ username or password is wrong.");
    }

    public void goToMainMenu() throws IOException {
//        new
        new OpenWindow(null, null, null, null, Pages.MAIN_MENU);
    }

    public void closeLoginPage(ActionEvent event) {
        Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void doneButton(ActionEvent event) throws IOException {
        captchaChecker();
        userAndPassChecker();
        warningText();
        if (captchaResult && userAndPassResult) {
            new EnterTime().setEnterTime(appData.getInstance().getType(), appData.getInstance().getId());
            goToMainMenu();
            closeLoginPage(event);
        }
    }
}
