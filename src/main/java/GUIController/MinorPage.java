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
import logic.university.Minor;
import logic.university.Student;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MinorPage implements Initializable {
    public ObservableList<Minor> minors = FXCollections.observableArrayList();

    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.constants.themeReturner();


    @FXML
    AnchorPane anchorPane;
    @FXML
    TableView<Minor> minorTableView;
    @FXML
    TableColumn<Minor, Void> acceptColumn, rejectColumn;
    @FXML
    TableColumn<Minor, String> studentColumn, destinationColumn, statusColumn;
    @FXML
    Button newRequestButton;
    @FXML
    Label label1, label2, label3, label4, label5;

    public MinorPage() throws FileNotFoundException {
    }

    public void newMinorRequest() throws IOException {
        new OpenWindow(null, null, null, null, Pages.MINOR_REQUEST);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeColumns();
        try {
            loadData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        addAcceptButtonToTable();
        addRejectButtonToTable();
        if (appData.getInstance().getType() == UserTypes.STUDENT) {
            acceptColumn.setVisible(false);
            rejectColumn.setVisible(false);
        }
        setFont();
        setColor();
    }

    public void initializeColumns() {
        studentColumn.setCellValueFactory(cellData -> cellData.getValue().studentProperty());
        destinationColumn.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        minorTableView.setItems(minors);
    }

    public void loadData() throws FileNotFoundException {
        if (appData.getInstance().getType() == UserTypes.STUDENT) {
            loadStudentMinor();
        }
        else {
            if (appData.getInstance().getProfessor().getEducationalAssistant())
                loadProfessorMinor();
        }
    }

    public void loadStudentMinor() throws FileNotFoundException {
        ArrayList<Minor> minorsList = new Returner().returnMinors();
        for (Minor minor : minorsList) {
            System.out.println(minor.getStudent().getId());
            if (minor.getStudent().getId() == appData.getInstance().getStudent().getId()) {
                this.minors.add(minor);
            }
        }
    }

    public void loadProfessorMinor() throws FileNotFoundException {
        ArrayList<Minor> minorsList = new Returner().returnMinors();
        for (Minor minor : minorsList) {
            if (minor.getStudent().getDepartment().getType() == appData.getInstance().getProfessor().getDepartment().getType() ||
            minor.getDestination().getType() == appData.getInstance().getProfessor().getDepartment().getType())
                this.minors.add(minor);
        }
    }

    public void addAcceptButtonToTable() {
        Callback<TableColumn<Minor, Void>, TableCell<Minor, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Minor, Void> call(TableColumn<Minor, Void> param) {
                return new TableCell<>() {
                    private final Button acceptButton = new Button("موافقت");
                    {
                        acceptButton.setFont(font);
                        acceptButton.setOnAction((ActionEvent event) -> {
                            Minor minor = getTableView().getItems().get(getIndex());
                            if (appData.getInstance().getUser().getDepartment() != minor.getDestination())
                                minor.setOriginIsAccepted(true);
                            else
                                minor.setDestinationIsAccepted(true);
                            try {
                                new SaveUser().saveMinor(minor);
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
        Callback<TableColumn<Minor, Void>, TableCell<Minor, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Minor, Void> call(TableColumn<Minor, Void> param) {
                return new TableCell<>() {
                    private final Button rejectButton = new Button("مخالفت");
                    {
                        rejectButton.setFont(font);
                        rejectButton.setOnAction((ActionEvent event) -> {
                            Minor minor = getTableView().getItems().get(getIndex());
                            if (appData.getInstance().getUser().getDepartment() != minor.getDestination())
                                minor.setOriginIsAccepted(false);
                            else
                                minor.setDestinationIsAccepted(false);
                            try {
                                new SaveUser().saveMinor(minor);
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
                            setGraphic(rejectButton);
                        }
                    }

                };
            }
        };
        rejectColumn.setCellFactory(cellFactory);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }

    public void setFont() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        newRequestButton.setFont(font);
    }
}
