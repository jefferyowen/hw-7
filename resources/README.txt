For assignment 7 we made our controller using a features pattern. The feautres get activated based
on different mouse of key events. These features are independent of the model or view, and act as
our controller. We also made the view that uses this controller implenet our old view of
ExcelJframe, but instead of implenting the old view, it implements EditView which has our features
included. Using these features, the model, view and controller all act indepentintly but use each
other to change the state of the program.

Changes made from old assignment
- fixed the sum function
- added getter methods for certain fields needed from the JFrame

