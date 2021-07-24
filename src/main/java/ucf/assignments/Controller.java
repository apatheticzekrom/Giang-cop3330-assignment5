package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;


public class Controller {
    ObservableList<Item> Inventory = FXCollections.observableArrayList();

    @FXML TextField valueTextField;
    @FXML TextField serialTextField;
    @FXML TextField nameTextField;
    @FXML TableView<Item> displayTable;
    @FXML TableColumn<Item, String> valueColumn;
    @FXML TableColumn<Item, String> serialColumn;
    @FXML TableColumn<Item, String> nameColumn;

    @FXML
    public void AddItemButtonClicked(ActionEvent actionEvent) {
        // Gets string from valueTextField, serialTextField, and nameTextField
        // Checks to make sure the name is between 2 and 256
        // runs itemAdd()
        // runs displayAll()

        String value = valueTextField.getText();
        String serial = serialTextField.getText();
        // check if serial exists

        String name = nameTextField.getText();
        if(name.length() < 257 && name.length() > 1)
        {
            itemAdd(value, serial, name);
        }
        displayAll();
    }

    @FXML
    public void RemoveItemButtonClicked(ActionEvent actionEvent) {
        // Runs itemDelete()
        // runs displayAll()
        removeItem(displayTable.getSelectionModel().getSelectedItem());
        displayAll();
    }

    @FXML
    public void EditValueButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void SortBySerialButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void SortByValueButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void SortByNameButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void EditNameButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void SearchNameButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void EditSerialButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void SearchSerialButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void SaveAsButtonClicked(ActionEvent actionEvent) {
    }
    @FXML
    public void LoadButtonClicked(ActionEvent actionEvent) {
    }

    // Actual Functions -----------------------------------------------------

    public ObservableList<Item> itemAdd(String value, String serial, String name)
    {
        // Takes the inputs value, serial, and name
        // adds the item to the list
        Inventory.add(new Item(value, serial, name));
        return Inventory;
    }

    public ObservableList<Item> removeItem(Item selectedItem)
    {
        // Removes the selected item from the list
        Inventory.remove(selectedItem);
        return Inventory;
    }

//    editExistingItemValue()
//    editExistingItemSN()
//    editExistingItemName()
//
//    sortByValue()
//    sortBySerial()
//    sortByName()
//
//    searchBySerial()
//    searchByName()
//
//    saveInventory()
//    loadInventory()

    public void displayAll()
    {
        // Clears display table
        // Adds everything currently in the list to the display table
        displayTable.getItems().clear();
        for(int i = 0; i < Inventory.size();i++)
        {
            displayTable.getItems().add(Inventory.get(i));
        }
    }

}
