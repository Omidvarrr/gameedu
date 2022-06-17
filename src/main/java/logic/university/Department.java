package logic.university;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Department {
    private Departments type;

    public Department(Departments type) {
        this.type = type;
    }

    public StringProperty nameProperty() {
        return new SimpleStringProperty(type.toString());
    }

    public Departments getType() {
        return type;
    }

    public void setType(Departments type) {
        this.type = type;
    }

}