package com.example.fuelqueueassignmentgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.fuelqueueassignmentgui.QueueManager.queues;

public class HelloController {
    @FXML
    private Button SearchButton;

    @FXML
    private Button ViewButton;

    @FXML
    private TextArea FuelQueues;

    @FXML
    private TextArea WaitingQueue;

    @FXML
    private TextField SearchPassenger;

    @FXML
    private TextArea Passenger;

    @FXML
    void SearchButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SearchWindow.fxml"));
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root, 550, 600);
        currentWindow.setScene(newScene);
        currentWindow.show();
    }


    @FXML
    void ViewButtonOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DetailWindow.fxml"));
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root, 1200, 900);
        currentWindow.setScene(newScene);
        currentWindow.show();
    }

    @FXML
    void ViewMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root, 500, 600);
        currentWindow.setScene(newScene);
        currentWindow.show();
    }

    @FXML
    void DisplayQueueDetails() {
        String details = "";

        for (int i = 0; i < 5; i++) {
            details += "\n Queue No " + i + "\n";
            if (queues[i].getPassengers().size() > 0) details += ("\n.....................\n");
            else details += ("Queue is Empty");
            for (int j = 0; j < queues[i].getCount(); j++) {
                Passengers currentPassenger = queues[i].getPassengers().get(j);
                details += (currentPassenger.getFirstName() + "," + currentPassenger.getLastName() + "," + currentPassenger.getVehicleNo() + "," + currentPassenger.getLitres() + "\n");
            }
            FuelQueues.setText(details);
        }
    }

    @FXML
    void DisplayWaitingQueue() {
        String details = "";

        if (Main.WaitingList.getPassengers().size() > 0) {
            details += ("Queue Sorted\n............\n");
            for (int i = 0; i < Main.WaitingList.getCount(); i++) {
                Passengers currentPassenger = Main.WaitingList.getPassengers().get(i);
                details += (currentPassenger.getFirstName() + "," + currentPassenger.getLastName() + "," + currentPassenger.getVehicleNo() + "," + currentPassenger.getLitres() + "\n");
            }
            WaitingQueue.setText(details);
        } else {
            WaitingQueue.setText("No one found in the waiting List");
        }


    }

    @FXML
    void SearchCustomer() {
        String customer = SearchPassenger.getText();
        String setCustomer = "";

        for (int i = 0; i < queues.length; i++) {
            for (int j = 0; j < queues[i].getPassengers().size(); j++) {
                Passengers newPassenger = queues[i].getPassengers().get(j);
                if (customer.equals(newPassenger.getFirstName())) {
                    setCustomer += (newPassenger.getFirstName() + "," + newPassenger.getLastName() + "," + newPassenger.getVehicleNo() + "," + newPassenger.getLitres() + "\n");
                    Passenger.setText(setCustomer);
                }
                if (Objects.equals(setCustomer, "")) {
                    Passenger.setText("No customer Found");
                }
            }
        }


    }

}

