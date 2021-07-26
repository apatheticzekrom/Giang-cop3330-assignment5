import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import ucf.assignments.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkForSerialTest {
    @Test
    public void checkForSerialTest()
    {
        ObservableList<Item> Inventory = FXCollections.observableArrayList();
        Controller controller = new Controller();

        Item test = new Item("12", "1234567890", "abc");
        Inventory.add(test);

        boolean actual = controller.checkForSerial(Inventory, "1234567890");
        assertEquals(true, actual);

    }
    @Test
    public void checkForSerialFalseTest()
    {
        ObservableList<Item> Inventory = FXCollections.observableArrayList();
        Controller controller = new Controller();

        Item test = new Item("12", "1234567893", "abc");
        Inventory.add(test);

        boolean actual = controller.checkForSerial(Inventory, "1234567890");
        assertEquals(false, actual);

    }

}
