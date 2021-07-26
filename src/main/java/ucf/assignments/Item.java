package ucf.assignments;

public class Item {
    // Variables
    private String value;
    private String serial;
    private String name;

    // Constructors
    public Item(String value, String serial, String name) {
        this.value = value;
        this.serial = serial;
        this.name = name;
    }

    // Getters and Setters
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean itemEquals(Item other)
    {
        if(this.value.equals(other.value)){
            return false;
        }
        if(!this.serial.equals(other.serial)){
            return false;
        }
        if(!this.name.equals(other.name)){
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other)
    {
        if(!(other instanceof Item))
        {
            return false;
        }

        Item that = (Item)other;
        return itemEquals(that);

    }

    @Override
    public String toString()
    {
        String convertedString = this.getValue() + this.getSerial() + this.getName();
        return convertedString;
    }
}