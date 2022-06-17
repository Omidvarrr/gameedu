package GUIController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.university.Course;
import logic.university.Review;

import javax.annotation.processing.Generated;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewStudentSidePage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();
    Review review;

    public ReviewStudentSidePage() throws FileNotFoundException {
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @FXML
    AnchorPane anchorPane;
    @FXML
    TextArea reviewTextArea;
    @FXML
    Button submitButton;
    @FXML
    Label replyLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replyLabel.setWrapText(true); // go to next line if needed.
        setFont();
        setColor();
        if (review != null) {
            if (review.getReplyed())
                reviewReplyedCase();
        }
    }

    public void submitReview() {
        //todo
    }

    public void reviewReplyedCase() {
        reviewTextArea.setVisible(false);
        submitButton.setVisible(false);
        replyLabel.setText(review.getReplyText());
    }

    public void setFont() {
        reviewTextArea.setFont(font);
        submitButton.setFont(font);
        replyLabel.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }
}
