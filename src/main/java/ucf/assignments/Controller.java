/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Benjamin Giang
 */

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.Scanner;


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
        // checks that serial is alphanumeric
        // Checks to make sure the name is between 2 and 256
        // runs itemAdd()
        // runs displayAll()

        String value = valueTextField.getText();

        String serial = serialTextField.getText();

        String name = nameTextField.getText();
        if(checkSerial(serial) && checkName(name))
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
        if(checkSerial(serial))
        {
            editExistingItemSN(displayTable.getSelectionModel().getSelectedIndex(), serial);
        }
        displayAll();
    }
    @FXML
    public void EditNameButtonClicked(ActionEvent actionEvent) {
        // Gets string from name text field
        // runs editExistingItemName()
        // runs displayAll()
        String name = nameTextField.getText();
        // check to make sure the description is 256 or less
        if(checkName(name))
        {
            editExistingItemName(displayTable.getSelectionModel().getSelectedIndex(), name);
        }
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
        if(searchBySerial(Inventory, serial) != -1)
        {
            displayTable.getSelectionModel().select(searchBySerial(Inventory, serial));
        }
    }
    @FXML
    public void SearchNameButtonClicked(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        if(searchByName(Inventory, name) != -1)
        {
            displayTable.getSelectionModel().select(searchByName(Inventory, name));
        }
    }


    @FXML
    public void SaveAsButtonClicked(ActionEvent actionEvent) {
        saveList();
    }

    @FXML
    public void LoadButtonClicked(ActionEvent actionEvent) throws FileNotFoundException {
        File file = fileChooser.showOpenDialog(new Stage());
        loadList(file);
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

    public int searchBySerial(ObservableList<Item> Inventory, String serial) {

        for (int i = 0; i < Inventory.size(); i++) {
            if (Inventory.get(i).getSerial().equals(serial)) {
                return i;
            }
        }
        return -1;
    }
    public int searchByName(ObservableList<Item> Inventory, String name) {

        for (int i = 0; i < Inventory.size(); i++) {
            if (Inventory.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // ----------------------------------------------------
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter tsvExt = new FileChooser.ExtensionFilter("(*.tsv)", "*.tsv");
    FileChooser.ExtensionFilter htmlExt = new FileChooser.ExtensionFilter("(*.html)", "*.html");
    FileChooser.ExtensionFilter jsonExt = new FileChooser.ExtensionFilter("(*.json)", "*.json");

    public void saveList()
    {
        // Sends content to the saveText() function
        File file = fileChooser.showSaveDialog(new Stage());
        if(file != null)
        {
            String content = "";
            if(fileChooser.getSelectedExtensionFilter().equals(tsvExt))
            {
                content += "Value\tSerial\tName\n";
                for(int i = 0; i < Inventory.size();i++)
                {
                    content += Inventory.get(i).getValue();
                    content += "\t" + Inventory.get(i).getSerial();
                    content += "\t" + Inventory.get(i).getName() + "\n";
                }

            } else if (fileChooser.getSelectedExtensionFilter().equals(htmlExt)){
                content += "<!DOCTYPE html>\n";
                content +="<html>\n<title> Inventory </title>\n";
                content += "<body> Value Serial Name </body>\n";
                for(int i = 0; i < Inventory.size();i++)
                {
                    content += "<li>" + Inventory.get(i).getValue() + " " + Inventory.get(i).getSerial() + " " + Inventory.get(i).getName() + "</li>\n";
                }

                content +="</html>\n";

            } else if (fileChooser.getSelectedExtensionFilter().equals(jsonExt))
            {
                content += "{\n";
                content += "\t\"Inventory\" : [\n";
                for(int i = 0; i < Inventory.size();i++)
                {
                    content += "\t{\"value\": \""+Inventory.get(i).getValue()+"\", \"serial\": \""+Inventory.get(i).getSerial()+"\", \"name\": \""+ Inventory.get(i).getName() + "\" }\n";
                }
                content += "\t]\n}";
            }
            saveText(file, content);
        }

    }

    public  void saveText(File file, String content){
        // Saves the inputted string into a text file
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(content);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  ObservableList<Item> loadList(File file) throws FileNotFoundException {
        // Prompts user to select a file to be loaded
        // Inputs the file contents into an item
        // Runs the addItem() function to add each item to the list
        // displays the list once completed

        try {
            Scanner scanner = new Scanner(file);
            clearList();
            while (scanner.hasNextLine()) {
                String value = scanner.nextLine();
                String serial = scanner.nextLine();
                String name = scanner.nextLine();
                itemAdd(value, serial, name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Inventory;
    }

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

    public  ObservableList<Item> clearList()
    {
        // Removes all items from the list
        Inventory.clear();
        return Inventory;
    }

    public boolean checkSerial(String serial)
    {
        // checks if serial is alphanumeric and 10 characters long
        char[] charArray = serial.toCharArray();
        boolean yes = false;
        if(charArray.length == 10)
        {
            for(char c:charArray)
            {
                yes = Character.isLetterOrDigit(c);
            }
        } else
        {
            yes = false;
        }
        return yes;
    }

    public boolean checkForSerial(ObservableList<Item> Inventory, String serial)
    {
        // checks if serial exists within the list
        boolean isIn = false;
        for(int i = 0; i < Inventory.size();i++){
            isIn = Inventory.get(i).getSerial().equals(serial);
        }
        return isIn;
    }

    public boolean checkName(String serial)
    {
        // checks that name is within 2 and 256
        if(serial.length() < 257 && serial.length() > 1)
        {
                return true;
        } else
        {
            return false;
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
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(tsvExt);
        fileChooser.getExtensionFilters().add(htmlExt);
        fileChooser.getExtensionFilters().add(jsonExt);

    }
}