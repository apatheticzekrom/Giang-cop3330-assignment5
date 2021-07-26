import org.junit.jupiter.api.Test;
import ucf.assignments.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkNameTest {
    @Test
    public void checkNameTest()
    {
        Controller controller = new Controller();

        boolean actual = controller.checkName("12");
        assertEquals(true, actual);

    }

    @Test
    public void checkNameFalseTest()
    {
        Controller controller = new Controller();

        boolean actual = controller.checkSerial("1");
        assertEquals(false, actual);

    }
}
