package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;


public class Controller implements Initializable {
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
        // Gets string from value text field
        // runs editExistingItemValue()
        // runs displayAll()
        String value = valueTextField.getText();
        editExistingItemValue(displayTable.getSelectionModel().getSelectedIndex(), value);
        displayAll();
    }
    @FXML
    public void EditSerialButtonClicked(ActionEvent actionEvent) {
        // Gets string from serial text field
        // runs editExistingItemSN()
        // runs displayAll()
        String serial = serialTextField.getText();
        // Check to make sure serial doesnt exist
        editExistingItemSN(displayTable.getSelectionModel().getSelectedIndex(), serial);
        displayAll();
    }
    @FXML
    public void EditNameButtonClicked(ActionEvent actionEvent) {
        // Gets string from name text field
        // runs editExistingItemName()
        // runs displayAll()
        String name = nameTextField.getText();
        // check to make sure the description is 256 or less
        editExistingItemName(displayTable.getSelectionModel().getSelectedIndex(), name);
        displayAll();
    }

    @FXML
    public void SortByValueButtonClicked(ActionEvent actionEvent) {
        sortByValue();
    }
    @FXML
    public void SortBySerialButtonClicked(ActionEvent actionEvent) {
        sortBySerial();
    }
    @FXML
    public void SortByNameButtonClicked(ActionEvent actionEvent) {
        sortByName();
    }

    @FXML
    public void SearchSerialButtonClicked(ActionEvent actionEvent) {
        String serial = serialTextField.getText();
        if(searchBySerial(serial) != -1)
        {
            displayTable.getSelectionModel().select(searchBySerial(serial));
        }
    }
    @FXML
    public void SearchNameButtonClicked(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        if(searchByName(name) != -1)
        {
            displayTable.getSelectionModel().select(searchByName(name));
        }
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

    public  ObservableList<Item> editExistingItemValue(int selectedIndex, String value)
    {
        // Takes input string
        // Sets value of selected item
        Inventory.get(selectedIndex).setValue(value);
        return Inventory;
    }
    public  ObservableList<Item> editExistingItemSN(int selectedIndex, String serial)
    {
        // Takes input string
        // Sets serial of selected item
        Inventory.get(selectedIndex).setSerial(serial);
        return Inventory;
    }
    public  ObservableList<Item> editExistingItemName(int selectedIndex, String name)
    {
        // Takes input string
        // Sets name of selected item
        Inventory.get(selectedIndex).setName(name);
        return Inventory;
    }

    public void sortByValue()
    {
        // Clears display table
        displayTable.getItems().clear();
        Inventory.sort(compareByValue);
        for(int i = 0; i < Inventory.size();i++)
        {
            displayTable.getItems().add(Inventory.get(i));
        }
    }
    public void sortBySerial()
    {
        // Clears display table
        displayTable.getItems().clear();
        Inventory.sort(compareBySerial);
        for(int i = 0; i < Inventory.size();i++)
        {
            displayTable.getItems().add(Inventory.get(i));
        }
    }
    public void sortByName()
    {
        // Clears display table
        displayTable.getItems().clear();
        Inventory.sort(compareByName);
        for(int i = 0; i < Inventory.size();i++)
        {
            displayTable.getItems().add(Inventory.get(i));
        }
    }

    public int searchBySerial(String serial) {

        for (int i = 0; i < Inventory.size(); i++) {
            if (Inventory.get(i).getSerial().equals(serial)) {
                return i;
            }
        }
        return -1;
    }
    public int searchByName(String name) {

        for (int i = 0; i < Inventory.size(); i++) {
            if (Inventory.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

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

    //Value sorter
    Comparator<Item> compareByValue = (Item o1, Item o2) ->
            o1.getValue().compareTo( o2.getValue() );

    //Serial sorter
    Comparator<Item> compareBySerial = (Item o1, Item o2) ->
            o1.getSerial().compareTo( o2.getSerial() );

    //Name sorter
    Comparator<Item> compareByName = (Item o1, Item o2) ->
            o1.getName().compareTo( o2.getName() );

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        valueColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("value"));
        serialColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serial"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    }
}