package GUIController;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import logic.appData;
import logic.university.RequestsType;
import logic.university.StudentGrades;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestBarPage implements Initializable {

    @FXML
    AnchorPane anchorPane, barAnchorPane;
    @FXML
    Label label;
    @FXML
    JFXComboBox<Label> requestComboBox;

    private Label recommendationLabel, certificateLabel, minorLabel, withdrawalLabel, dormLabel, thesisDefenseLabel;

    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.constants.themeReturner();

    public RequestBarPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createLabel();
        setFont();
        setColor();
        addRequestToChoiceBox();
        setNameOfRequestInChoiceBox();
        changeBasedOnUser();
    }

    public void createLabel() {
        this.recommendationLabel = new Label("توصیه نامه");
        this.certificateLabel = new Label("گواهی اشتغال به تحصیل");
        this.minorLabel = new Label("دوره فرعی");
        this.withdrawalLabel = new Label("انصراف از تحصیل");
        this.dormLabel = new Label("خوابگاه");
        this.thesisDefenseLabel = new Label("دفاع از پایان‌نامه");
    }

    public void addRequestToChoiceBox() {
        requestComboBox.getItems().add(recommendationLabel);
        requestComboBox.getItems().add(certificateLabel);
        requestComboBox.getItems().add(minorLabel);
        requestComboBox.getItems().add(withdrawalLabel);
        requestComboBox.getItems().add(dormLabel);
        requestComboBox.getItems().add(thesisDefenseLabel);
    }

    public void changeBasedOnUser() {
        System.out.println("*******************************");
        System.out.println(appData.getInstance().getType());
        if (appData.getInstance().getType() == UserTypes.STUDENT) {
            System.out.println(appData.getInstance().getStudent().getGrade());
            if (appData.getInstance().getStudent().getGrade() == StudentGrades.B_S) {
                System.out.println("****************************");
                dormLabel.setVisible(false);
                thesisDefenseLabel.setVisible(false);
                requestComboBox.getItems().remove(dormLabel);
                requestComboBox.getItems().remove(thesisDefenseLabel);
            }
            else if (appData.getInstance().getStudent().getGrade() == StudentGrades.M_A) {
                minorLabel.setVisible(false);
                thesisDefenseLabel.setVisible(false);
                requestComboBox.getItems().remove(minorLabel);
                requestComboBox.getItems().remove(thesisDefenseLabel);
            }
            else if (appData.getInstance().getStudent().getGrade() == StudentGrades.ph_DM) {
                recommendationLabel.setVisible(false);
                minorLabel.setVisible(false);
                dormLabel.setVisible(false);
                requestComboBox.getItems().remove(recommendationLabel);
                requestComboBox.getItems().remove(minorLabel);
                requestComboBox.getItems().remove(dormLabel);
            }
        }
        if (appData.getInstance().getType() == UserTypes.PROFESSOR) {
            certificateLabel.setVisible(false);
            dormLabel.setVisible(false);
            thesisDefenseLabel.setVisible(false);
            requestComboBox.getItems().remove(certificateLabel);
            requestComboBox.getItems().remove(dormLabel);
            requestComboBox.getItems().remove(thesisDefenseLabel);
            if (!appData.getInstance().getProfessor().getEducationalAssistant()) {
                minorLabel.setVisible(false);
                withdrawalLabel.setVisible(false);
                requestComboBox.getItems().remove(minorLabel);
                requestComboBox.getItems().remove(withdrawalLabel);
            }
        }

    }

    public void setNameOfRequestInChoiceBox() {
        requestComboBox.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                if (object == null)
                    return "";
                else
                    return object.getText();
            }

            @Override
            public Label fromString(String string) {
                return null;
            }
        });
    }


    public void setFont() {
//        requestComboBox.getEditor().setFont(font);
        label.setFont(font);
        recommendationLabel.setFont(font);
        certificateLabel.setFont(font);
        minorLabel.setFont(font);
        withdrawalLabel.setFont(font);
        dormLabel.setFont(font);
        thesisDefenseLabel.setFont(font);
    }

    public void setColor() {
        barAnchorPane.setBackground(background);
        anchorPane.setBackground(background);
    }

    public void paneConnectorToPage(ActionEvent event) throws IOException {
        if (requestComboBox.getValue() == recommendationLabel)
            showRecommendation(event);
        else if (requestComboBox.getValue() == certificateLabel)
            showCertificate(event);
        else if (requestComboBox.getValue() == minorLabel)
            showMinor(event);
        else if (requestComboBox.getValue() == withdrawalLabel)
            showWithdrawal(event);
        else if (requestComboBox.getValue() == dormLabel)
            showDorm(event);
        else if (requestComboBox.getValue() == thesisDefenseLabel)
            showThesisDefense(event);
    }

    public void showRecommendation(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.RECOMMENDATION);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void showCertificate(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.CERTIFICATE_STUDENT);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void showMinor(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.MINOR);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void showWithdrawal(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.WITHDRAWAL);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void showDorm(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.DORM);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }

    public void showThesisDefense(ActionEvent event) throws IOException {
        Node node = new LoaderFXMLs().loadFXML(Pages.THESIS_DEFENCE);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(node);
    }






}
