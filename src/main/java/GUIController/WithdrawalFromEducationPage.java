package GUIController;

import dataBaseManager.Returner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class WithdrawalFromEducationPage implements Initializable {
    public ObservableList<WithDrawal> withDrawals = FXCollections.observableArrayList();
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.getInstance().themeReturner();

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label label1, label2, label3, sendedLabel;
    @FXML
    TableView<WithDrawal> withdrawalTableView;
    @FXML
    TableColumn<WithDrawal, Void> acceptColumn, rejectColumn;
    @FXML
    TableColumn<WithDrawal, String> statusColumn;
    @FXML
    Button sendRequestButton;

    public WithdrawalFromEducationPage() throws FileNotFoundException {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sendedLabel.setVisible(false);
        setFont();
        setColor();
        initializeColumn();
        loadData();
        if (appData.getInstance().getType() == UserTypes.PROFESSOR) {
            addAcceptButtonToTable();
            addRejectButtonToTable();
        }
    }

    public void initializeColumn() {
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        withdrawalTableView.setItems(withDrawals);
    }

    public void loadData() {
        if (appData.getInstance().getType() == UserTypes.PROFESSOR)
            loadProfessorData();
        else
            loadStudentData();

    }

    public void loadStudentData() {
        ArrayList<WithDrawal> withdrawals = new Returner().returnWithdrawal();
        for (WithDrawal withDrawal : withdrawals) {
            if (withDrawal.getStudent().getId() == appData.getInstance().getStudent().getId()) {
                withDrawals.add(withDrawal);
            }
    }
    }

    public void loadProfessorData() {
        ArrayList<WithDrawal> withdrawals = new Returner().returnWithdrawal();
        for (WithDrawal withDrawal : withdrawals) {
            if (withDrawal.getStudent().getDepartment().getType() == appData.getInstance().getProfessor().getDepartment().getType()) {
                withDrawals.add(withDrawal);
            }
        }

    }

    public void addAcceptButtonToTable() {
        Callback<TableColumn<WithDrawal, Void>, TableCell<WithDrawal, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<WithDrawal, Void> call(TableColumn<WithDrawal, Void> param) {
                return new TableCell<>() {
                    private final Button acceptButton = new Button("موافقت");
                    {
                        acceptButton.setFont(font);
                        acceptButton.setOnAction((ActionEvent event) -> {
                            WithDrawal withDrawal = getTableView().getItems().get(getIndex());
                            withDrawal.setStatus(RecommendationResult.accpeted);
                            loadData();
                            try {
                                new SaveUser().saveWithdrawal(withDrawal);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(acceptButton);
                        }
                    }

                };
            }
        };
        acceptColumn.setCellFactory(cellFactory);
    }

    public void addRejectButtonToTable() {
        Callback<TableColumn<WithDrawal, Void>, TableCell<WithDrawal, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<WithDrawal, Void> call(TableColumn<WithDrawal, Void> param) {
                return new TableCell<>() {
                    private final Button rejectButton = new Button("مخالفت");
                    {
                        rejectButton.setFont(font);
                        rejectButton.setOnAction((ActionEvent event) -> {
                            WithDrawal withDrawal = getTableView().getItems().get(getIndex());
                            withDrawal.setStatus(RecommendationResult.rejected);
                            new SaveUser().deleteStudent(withDrawal.getStudent());
                            try {
                                new SaveUser().saveWithdrawal(withDrawal);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            loadData();
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(rejectButton);
                        }
                    }

                };
            }
        };
        rejectColumn.setCellFactory(cellFactory);
    }


    public void goToWithdrawalRequestPage(ActionEvent event) throws IOException {
        WithDrawal withDrawal = new WithDrawal(new SaveUser().getWithdrawalCount()+1, appData.getInstance().getStudent(), RecommendationResult.pending);
        new SaveUser().saveWithdrawal(withDrawal);
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        sendedLabel.setFont(font);

        sendRequestButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }




}
