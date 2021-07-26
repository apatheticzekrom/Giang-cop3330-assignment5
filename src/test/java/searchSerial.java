import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import ucf.assignments.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class searchSerial {
    @Test
    public void searchSerialTest()
    {
        ObservableList<Item> Inventory = FXCollections.observableArrayList();
        Controller controller = new Controller();

        Item test = new Item("12", "1234567890", "abc");
        Inventory.add(test);

        int actual = controller.searchBySerial(Inventory, "1234567890");
        assertEquals(0, actual);

    }
}
