package fr.arcelormittal.Controllers;

import fr.arcelormittal.Helpers.ApplicationHelper;
import fr.arcelormittal.Models.Stand;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EngineerController implements Initializable {

    private Stand selectedStand = null;

    @FXML
    private ListView<Stand> standListView;
    @FXML
    private ToggleButton toggleStandButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListView();
    }

    @FXML
    private void onListItemsClick(MouseEvent mouseEvent) {
        selectedStand = standListView.getSelectionModel().getSelectedItem();
        toggleStandButton.setSelected(selectedStand.isActive());
    }

    @FXML
    private void onToggleStandButtonClick(ActionEvent actionEvent){
        setListView();
    }

    private void setListView(){
        try {
            standListView.setItems(FXCollections.observableArrayList(ApplicationHelper.getStands()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
