package GUIController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import logic.university.Review;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewProfessorSide implements Initializable {
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();
    private Review review;

    public ReviewProfessorSide() throws FileNotFoundException {
    }

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label reviewLabel;
    @FXML
    Button submibButton;
    @FXML
    TextArea replyTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFont();
        setColor();
        reviewLabel.setText(review.getReviewText());

    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setFont() {
        submibButton.setFont(font);
        reviewLabel.setFont(font);
        replyTextArea.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

    public void submitReply() {
        //
    }
}
