package GUIController;

import dataBaseManager.Returner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.util.Callback;
import logic.SaveUser;
import logic.appData;
import logic.university.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RecommendationPage implements Initializable {
    @FXML
    TableView<Recommendation> recommendationTableView;
    @FXML
    TableColumn<Recommendation, String> studentColumn, teacherColumn, resultColumn;
    @FXML
    TableColumn<Recommendation, Void> acceptColumn, rejectColumn, showColumn;
    @FXML
    Label label1, label2, label3, label4, label5, label6;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button createRecommendationButton;

    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();


    public ArrayList<Recommendation> userRecommandations = new ArrayList<>();
    public ObservableList<Recommendation> recommendationsList = FXCollections.observableArrayList();

    public RecommendationPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addAcceptButtonsToTable();
        addRejectButtonToTable();
        initializeColumns();
        addShowButtonToTable();
        setFont();
        setColor();
        changeBasedOnUser();
        addRecommendationToList();
    }

    public void addRecommendationToList() {
        recommendationsList.clear();
        ArrayList<Recommendation> recommendations = new Returner().returnRecommendation();
        System.out.println(recommendations);
        for (Recommendation recommendation : recommendations) {
            if (appData.getInstance().getType() == UserTypes.PROFESSOR) {
                if (recommendation.getTeacher().getId() == appData.getInstance().getProfessor().getId()) {
                    recommendationsList.add(recommendation);
                }
            }
            else if (appData.getInstance().getType() == UserTypes.STUDENT) {
                if (recommendation.getStudent().getId() == appData.getInstance().getStudent().getId()) {
                    recommendationsList.add(recommendation);
                }
            }
        }
    }

    public void changeBasedOnUser() {
        if (appData.getInstance().getType() == UserTypes.PROFESSOR) {
//            createRecommendationButton.setVisible(false);
        }
        else {
            acceptColumn.setVisible(false);
            rejectColumn.setVisible(false);
        }
    }

    public void addAcceptButtonsToTable() {
        Callback<TableColumn<Recommendation, Void>, TableCell<Recommendation, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Recommendation, Void> call(TableColumn<Recommendation, Void> param) {
                return new TableCell<>() {
                    private final Button acceptButton = new Button("موافقت");
                    {
                        acceptButton.setFont(font);
                        acceptButton.setOnAction((ActionEvent event) -> {
                            Recommendation recommendation = getTableView().getItems().get(getIndex());
                            recommendation.setResult(RecommendationResult.accpeted);
                            try {
                                new SaveUser().saveRecommendation(recommendation);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            addRecommendationToList();
                            if (recommendation.getResult() != RecommendationResult.pending)
                                acceptButton.setVisible(false);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            if (getTableView().getItems().get(getIndex()).getResult() == RecommendationResult.pending)
                                setGraphic(acceptButton);
                        }
                    }
                };
            }
        };
        acceptColumn.setCellFactory(cellFactory);
    }

    public void addRejectButtonToTable() {
        Callback<TableColumn<Recommendation, Void>, TableCell<Recommendation, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Recommendation, Void> call(TableColumn<Recommendation, Void> param) {
                return new TableCell<>() {
                    private final Button rejectButton = new Button("مخالفت");
                    {
                        rejectButton.setFont(font);
                        rejectButton.setOnAction((ActionEvent event) -> {
                            Recommendation recommendation = getTableView().getItems().get(getIndex());
                            recommendation.setResult(RecommendationResult.rejected);
                            try {
                                new SaveUser().saveRecommendation(recommendation);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            addRecommendationToList();
                            if (recommendation.getResult() != RecommendationResult.pending)
                                rejectButton.setVisible(false);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            if (getTableView().getItems().get(getIndex()).getResult() == RecommendationResult.pending)
                                setGraphic(rejectButton);
                        }
                    }
                };
            }
        };
        rejectColumn.setCellFactory(cellFactory);
    }

    public void addShowButtonToTable() {
        Callback<TableColumn<Recommendation, Void>, TableCell<Recommendation, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Recommendation, Void> call(TableColumn<Recommendation, Void> param) {
                return new TableCell<>() {
                    private final Button showButton = new Button("نمایش");
                    {
                        showButton.setFont(font);
                        showButton.setOnAction((ActionEvent event) -> {
                            Recommendation recommendation = getTableView().getItems().get(getIndex());
                            try {
                                recommendation.findCommonCourses();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            try {
                                new OpenWindow(null, null, recommendation, null, Pages.RECOMMENDATION_SHOW);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty)
                            setGraphic(null);
                        else {
                            if (getTableView().getItems().get(getIndex()).getResult() != RecommendationResult.pending)
                                setGraphic(showButton);
                        }

                    }
                };
            }
        };
        showColumn.setCellFactory(cellFactory);
    }

    public void initializeColumns() {
        studentColumn.setCellValueFactory(cellData -> cellData.getValue().studentProperty());
        teacherColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
        resultColumn.setCellValueFactory(cellData -> cellData.getValue().resultProperty());

        recommendationTableView.setItems(recommendationsList);
    }

    public void goToCreateRecommendationPage(ActionEvent event) throws IOException {
        new OpenWindow(null, null, null, null, Pages.RECOMMENDATION_CREATE);
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        createRecommendationButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }



}
