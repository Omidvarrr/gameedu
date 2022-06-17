package GUIController;

import com.google.gson.Gson;
import dataBaseManager.AddToList;
import dataBaseManager.Returner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.util.Callback;
import logic.SaveUser;
import logic.appData;
import logic.university.Professor;
import logic.university.UserTypes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ProfessorListPage extends MainMenu implements Initializable {
    public ObservableList<Professor> professorList = FXCollections.observableArrayList();
    Gson gson = new Gson();
    private final Font font = Constants.getInstance().font;
    private final Background background = Constants.constants.themeReturner();

    @FXML
    TableView<Professor> professorListTable;
    @FXML
    TableColumn<Professor, String> nameColumn, emailColumn, departmentColumn, gradeColumn;
    @FXML
    TableColumn<Professor, Void> editColumn, deleteColumn;
    @FXML
    Button addProfessorButton;
    @FXML
    Label label, label1, label2, label3, label4, label5, label6;

    public ProfessorListPage() throws FileNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeColumns();

        try {
            initializeList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addProfessorButton.setVisible(false);
        editColumn.setVisible(false);
        deleteColumn.setVisible(false);
        setFonts();
        setColor();
        if (appData.getInstance().getType() == UserTypes.PROFESSOR) {
            if (appData.getInstance().professor.getChief()) {
                addDeleteButtonToTable();
                addEditButtonToTable();
                addProfessorButton.setVisible(true);
                editColumn.setVisible(true);
                deleteColumn.setVisible(true);
            }
        }
    }

    public void initializeColumns() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        departmentColumn.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        professorListTable.setItems(professorList);
    }

    public void initializeList() throws FileNotFoundException {
        professorList.clear();
        professorList.addAll(new Returner().returnProfessors());
    }

    public void addDeleteButtonToTable() {
        Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Professor, Void> call(TableColumn<Professor, Void> param) {
                return new TableCell<>() {
                    private final Button deleteButton = new Button("حذف");
                    {
                        deleteButton.setFont(font);
                        deleteButton.setOnAction((ActionEvent event) -> {
                            Professor professor = getTableView().getItems().get(getIndex());
                            new SaveUser().deleteProfessor(professor);

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            if (getTableView().getItems().get(getIndex()).getDepartment().getType() == appData.getInstance().getProfessor().getDepartment().getType())
                                setGraphic(deleteButton);
                        }
                    }

                };
            }
        };
        deleteColumn.setCellFactory(cellFactory);
    }

    public void addEditButtonToTable() {
        Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Professor, Void> call(TableColumn<Professor, Void> param) {
                return new TableCell<>() {
                    private final Button editButton = new Button("ویرایش");
                    {
                        editButton.setFont(font);
                        editButton.setOnAction((ActionEvent event) -> {
                            Professor professor = getTableView().getItems().get(getIndex());
                            try {
                                initializeList();
                                new OpenWindow(professor, null, null, null, Pages.PROFESSOR_EDIT);
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
                            if (getTableView().getItems().get(getIndex()).getDepartment().getType() == appData.getInstance().professor.getDepartment().getType())
                                setGraphic(editButton);
                        }
                    }

                };
            }
        };
        editColumn.setCellFactory(cellFactory);
    }

    public void addProfessor() throws IOException {
        new OpenWindow(null, null, null, null, Pages.PROFESSOR_CREATE);
    }

    public void setFonts() {
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        addProfessorButton.setFont(font);
    }

    public void setColor() {
        anchorPane.setBackground(background);
    }
}
