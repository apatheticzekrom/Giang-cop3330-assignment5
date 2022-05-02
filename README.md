# University of Central Florida
## COP3330: Object Oriented Programming, Summer 2021

# Overview
Using IntelliJ and Gradle, you will create a GUI-based desktop application to allow a user to track their personal inventory.

The program should allow you to enter an item, a serial number, and estimated value. The program should then be able to display a tabular report of the data that looks like this:

| Value          | Serial Number  |   Name       |
|----------------|----------------|--------------|
| $399.00        | AXB124AXY3     | Xbox One     |
| $599.99        | S40AZBDE47     | Samsung TV   |


The program should also allow the user to export the data as either a tab-separated value (TSV) file, or as a HTML file. When exported as an HTML file, the data should be stored inside of a table structure to mimic the displayed appearance.

You will be responsible for both the design (UML diagrams) and implementation (production and test code) of this application

# How to use the Application
Note: serial has to be in format XXXXXXXXXX and alphanumeric, the program won't let you add/edit the serial if it's not in the right format
Note: name has to be within 2-256 characters inclusively, the program won't let you add/edit the name if it's not the right length

addItem() - To add an item, insert a value into the Value text box, a serial code, and a name, then click the add item button.

removeItem() - To remove an item, select an item from the display table, then click remove item.

editExistingItemValue() - To edit an existing item value, type a value into the value text box, then click "edit value"

editExistingItemSN() - To edit an existing item serial, type a serial into the serial text box, then click "edit serial"

editExistingItemName() - To edit an existing item name, type a name into the name text box, then click "edit name"

sortByValue() - To sort by value, click the button under "sort by" that says by value

sortBySerial() - To sort by serial, click the button under "sort by" that says by serial

sortByName() - To sort by name, click the button under "sort by" that says by name

searchBySerial() - To search by serial, type a serial into the serial text box and click "search serial"

searchByName() - To search by name, type a name into the name text box and click "search name"

saveInventory() - To save the list, click File in the top left, saveAs. Then type the desired file name, and the directory. Then select the file type and click save.

loadInventory() - To load a list, click File in the top left, load. Then select the file and load.
