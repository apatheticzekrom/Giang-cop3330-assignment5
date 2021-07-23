package ucf.assignments;

public class Item {
    // Variables
    private String value;
    private String serialNumber;
    private String name;

    // Constructors
    public Item(String value, String serialNumber, String name) {
        this.value = value;
        this.serialNumber = serialNumber;
        this.name = name;
    }

    // Getters and Setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}