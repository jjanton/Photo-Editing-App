README



This program is an image processor with a GUI. 

Functions supported by this image processor are:
Load images from a file chooser. 
Save images to the local hard drive using file chooser. 
Perform transformations to a loaded image: Blur, Sharpen, Greyscale, Sepia, Mosaic and Dither. 
Generate images of several kinds: Checkerboard, Swiss Flag, Greek Flag, French Flag,
Horizontal Rainbow and Vertical rainbow. 
Script processing via a text window. 
Script processing through command line via the -script argument. 
Undo and Redo functionality. 

Design Changes and Justifications: 
GUI: New class "View" extending Iframe. This class represented the main window in which all
other GUI functions were supported. This class contains menus which support the main
operations, either via directly activating that function or by creating a temporary panel which allows the modification of input values. 

Controller: A new secondary controller "ControllerButtons" was added to support the GUI. 
There is an interface Features, which is a list of actions that can be performed when a button
or menu is pressed. This interface is implemented in ControllerButtons, and operates actions in 
the view and model. 

Undo/Redo:
Modification to Model class to support Undo Redo: Replaced Image "original image" with a stack
of Images to represent the current image and previous states of the image. In addition, a second
stack was added to represent Redos. Two methods were added to undo and redo, and a delete all 
method was also added for clearing the undo and redo stacks. These changes allowed for
undo and redo to be added smoothly into the model in an efficient manner. 

Driver: 
Changes to driver to allow program to be loaded from command line, either in script or GUI mode.

GenerateChessboard: Caught and fixed previously unidentified bug which affected chessboards of
unequal height and width. 

This program is easily extendable. New filters can be created by simply creating a new class
that extends the AbstractFilter class, and passing the new matrix to the super constructor. 
New generators can be created simply by creating a new class, and creating a specific 
implementation of the generate method. New color transformations can be created by   
creating a new class that extends the AbstractColorTransformation class, and passing the 
new matrix to the super constructor. In order to support new filters or generators in the GUI, 
changes would need to be made to it and the controller. 

This program is run from the Driver in one of two ways: When the Driver instantiates the ControllerScript, the controller will read a list of commands from a script, and delegate all work to the Model. When a method
is called from the model, the model instantiates an object of the relevant class, and all
work is delegated to the object. 
If the Driver instead instantiates the ControllerButtons controller, it will delegate all
further inputs to the view. The view then works with the controller to take in data, and
the controller works with the model to alter it. Throughout the process the controller
instructs the view to update itself to show the latest version of the image. 

This program is packaged in the following categories: generator classes, image transformation
classes, image classes. The model, controller, and driver live in the source package. 

All parts of this program are complete. All extra credit across all 3 assignments have
been completed on time. The cat image is our own personal photo. The
elf image was borrowed from the free, open source site: 
https://units.wesnoth.org/1.12/Trader/en_US/Elf.html
