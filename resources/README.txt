For assignment 6, we added a View interface and many classes that implement view, that 
give representations of our Worksheet class. For our graphical GUI view we have a class
called ExcelJFrame that extends JFrame and implements our view interface. It is the 
handler for our ExcelJTable, DefaultTableModel, and our ScrollPane. ExcelJTable is a class
that we created that extends JTable and with it, we are able to override methods to fit
our program's needs. It takes in a DefaultTableModel that contains all of our data. We use
a DefaultTableModel since it is much easier to add rows and columns after the Table has been initiated.
Once we set all the visual rules and reformatting rules, we are able to
add a scroll pane to let us scroll through our GUI. We added action listeners to our 
scroll bars, which allows us to have infinite scrolling in our program. We also added a
second JTable that is the header for all of our rows, it is "frozen" so we can see what
row we are at, no matter how far horizontally we scroll. As for our textual view we take
in a file and use a printer writer to append all of the cells into the textual view asked
of us. 

As for changes that we made from the previous homework
- created a new evaluate() method for a cell content that returns what the user would see as output for a cell
- update toString for cellContents to represent what it would be when parsed
- fixed bugs of formulas not working
- changed sum and product constructors to account for one or two references, and added field references for toString() purposes.
