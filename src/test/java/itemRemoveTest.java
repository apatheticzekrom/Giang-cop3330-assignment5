import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import ucf.assignments.Item;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class itemRemoveTest {
    @Test
    public void removeItemTest()
    {
        ObservableList<Item> ToDoList = FXCollections.observableArrayList();
        Controller controller = new Controller();

        Item test = new Item("12", "1234567890", "abc");
        ToDoList.add(test);
        ToDoList.clear();

        ObservableList<Item> actual = controller.itemAdd("12", "1234567890", "abc");
        controller.removeItem(actual.get(0));

        for(int i = 0; i < ToDoList.size();i++)
        {
            assertTrue(ToDoList.get(i).equals(actual.get(i)));
        }
    }
}
