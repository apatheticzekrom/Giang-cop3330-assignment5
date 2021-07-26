import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import ucf.assignments.Item;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class itemAddTest {
    @Test
    public void itemAddTest()
    {
        ObservableList<Item> Inventory = FXCollections.observableArrayList();
        Controller controller = new Controller();

        Item test = new Item("12", "1234567890", "abc");
        Inventory.add(test);

        ObservableList<Item> actual = controller.itemAdd("12", "1234567890", "abc");

        for(int i = 0; i < Inventory.size();i++)
        {
            assertTrue(Inventory.get(i).equals(actual.get(i)));
        }
    }
}
