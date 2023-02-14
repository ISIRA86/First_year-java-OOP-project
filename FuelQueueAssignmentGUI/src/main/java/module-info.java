module com.example.fuelqueueassignmentgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fuelqueueassignmentgui to javafx.fxml;
    exports com.example.fuelqueueassignmentgui;
}