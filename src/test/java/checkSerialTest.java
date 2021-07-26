import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;
import ucf.assignments.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkSerialTest {
    @Test
    public void checkSerialTest()
    {
        Controller controller = new Controller();

        boolean actual = controller.checkSerial("1234567890");
        assertEquals(true, actual);

    }

    @Test
    public void checkSerialFalseTest()
    {
        Controller controller = new Controller();

        boolean actual = controller.checkSerial("12245");
        assertEquals(false, actual);

    }
}
