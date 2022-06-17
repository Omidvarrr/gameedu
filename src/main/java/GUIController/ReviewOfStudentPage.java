package GUIController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.university.Review;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewOfStudentPage implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();
    Review review;

    public ReviewOfStudentPage() throws FileNotFoundException {
    }

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label1, label2, reviewLabel, replyLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setColor();
        setFont();
        setReivewToLabel();
        setReplyToLabel();
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        reviewLabel.setFont(font);
        replyLabel.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

    public void setReivewToLabel() {
        reviewLabel.setText(review.getReviewText());
    }

    public void setReplyToLabel() {
        replyLabel.setText(review.getReplyText());
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
