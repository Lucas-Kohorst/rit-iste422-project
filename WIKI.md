# [View the Updated WIKI here](https://github.com/Lucas-Kohorst/rit-iste422-project/wiki)
## Milestone exercise 4 "Help system"
### Feature 1: Read EDG File

Steps to read in an EDG File
_You can use the Courses.edg file already in the repository or use your own _
1. Navigate to File > Open Edge File
2. Navigate from your Home directory to the project and select Courses.edg

You should now be able to see the Tables (and corresponding fields list) for the database defined in the .edg file. 

You can explore all the tables and fields in your database and even edit the attributes of a field (these changes can be written out to a DDL later). 

#### Desription of Functions used to read a .edg file

In ```EdgeConvertGUI.java:1157``` there is a EdgeMenuListener that is listening for when a file is opened. After the file is opened a new ```EdgeConvertFileParser``` object is created with the file. Below is the constructor method for ```EdgeConvertFileParser.java```

```
   public EdgeConvertFileParser(File constructorFile) {
      numFigure = 0;
      numConnector = 0;
      alTables = new ArrayList();
      alFields = new ArrayList();
      alConnectors = new ArrayList();
      isEntity = false;
      isAttribute = false;
      parseFile = constructorFile;
      numLine = 0;
      this.openFile(parseFile);
   }
```

You can see at the end of the constructor ```this.openFile(parseFile)``` is called. ```openFile()``` reads in the input file with a ```FileReader``` and ```BufferedReader``` object. If the .edg file is in the correct format than ```openFile()``` calls three methods ```parseSaveFile```, ```makeArrays()```, ```resolveConnectors()```. Otherwise the file parsing exits with an exception. Below is a description of the three mentioned functions. 

#### ```EdgeConvertFileParser: parseSaveFile()```
This function will parse a .edg file and make ```EdgeTable``` and ```EdgeField``` objects with the data for easy use in the rest of the application. 

In order to create the ```EdgeTable``` while the function starts with "Table: " the lines are read in and create a new ```EdgeTable``` object then using a ```StringTokenizer``` object the number of fields for the given ```EdgeTable``` object are determined. A for loop iterates over these and adds a field to the ```EdgeTable```. 

A ```StringTokenizer`` object is used again to determine if there is a relationship between tables. If there is then ```setRelatedField()``` is called. After determining the table the function ```makeArrays()``` is invoked. 

If there is a problem like the current line being read is ```null``` then the errors are handled by skipping the token. 

#### ```EdgeConvertFileParser: makeArrays()```
Simple helper function takes in the tables, fields, or connectors and if they are not null converts and casts them to an Array from an Arraylist. 

#### ```EdgeConvertFileParser: resolveConnectors()```
Tables and Fields parsed by the application from the .edg file will likely be connected in one way or another (primary/foreign keys). ```resolveConnectors()``` parses these and identifies the appropriate flags. 

First the function iterates over the fields array for endpoints. If when is found a flag is set. Next the tables are iterated over and same as before a flag is set with the ```setEPField()``` (Note: this method is either ```setEP1Field()``` or ```setEP2Field```) method. 

If both EP1 and EP2 flags are set this implies lack of normalization in the database and error is thrown on the GUI. As seen in the following code snippet:

```

if (connectors[cIndex].getIsEP1Field() && connectors[cIndex].getIsEP2Field()) { //both endpoints are fields, implies lack of normalization
   JOptionPane.showMessageDialog(null, "The Edge Diagrammer file\n" + parseFile + "\ncontains composite attributes. Please resolve them and try again.");
   EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
   break; //stop processing list of Connectors
}
```

If both EP1 and EP2 are tables then an error is thrown as there is a many-many relationship between the tables:

```
 if (connectors[cIndex].getIsEP1Table() && connectors[cIndex].getIsEP2Table()) { //both endpoints are tables
   if ((connectors[cIndex].getEndStyle1().indexOf("many") >= 0) &&
         (connectors[cIndex].getEndStyle2().indexOf("many") >= 0)) { //the connector represents a many-many relationship, implies lack of normalization
      JOptionPane.showMessageDialog(null, "There is a many-many relationship between tables\n\"" + tables[table1Index].getName() + "\" and \"" + tables[table2Index].getName() + "\"" + "\nPlease resolve this and try again.");
      EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
      break; //stop processing list of Connectors
   } else { //add Figure number to each table's list of related tables
      tables[table1Index].addRelatedTable(tables[table2Index].getNumFigure());
      tables[table2Index].addRelatedTable(tables[table1Index].getNumFigure());
      continue; //next Connector
   }
}
```
Finally if a field has not been assigned to a table yet the following error is thrown:

```
  if (fieldIndex >=0 && fields[fieldIndex].getTableID() == 0) { //field has not been assigned to a table yet
   if (connectors[cIndex].getIsEP1Table()) { //endpoint1 is the table
      tables[table1Index].addNativeField(fields[fieldIndex].getNumFigure()); //add to the appropriate table's field list
      fields[fieldIndex].setTableID(tables[table1Index].getNumFigure()); //tell the field what table it belongs to
   } else { //endpoint2 is the table
      tables[table2Index].addNativeField(fields[fieldIndex].getNumFigure()); //add to the appropriate table's field list
      fields[fieldIndex].setTableID(tables[table2Index].getNumFigure()); //tell the field what table it belongs to
   }
} else if (fieldIndex >=0) { //field has already been assigned to a table
   JOptionPane.showMessageDialog(null, "The attribute " + fields[fieldIndex].getName() + " is connected to multiple tables.\nPlease resolve this and try again.");
   EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
   break; //stop processing list of Connectors
}
```

After passing the three error checks, ```resolveConnectors()``` is complete and the .edg file is successfully parsed. 

You should now be able to see the tables and fields from your .edg file on the GUI interface. 

### Feature 2: Create DDL

In order to create a [Data Definition Language File](https://en.wikipedia.org/wiki/Data_definition_language) you first have to set your Output save directory. Go to Options > Set Output File Definition Location this MUST be set the the directory that contains your ```.class``` files. Then you can click Create DDL. This will ask you to choose a product name (a list of these will display if parsed correctly) and finally a Database name. A SQL script will then be generated and saved. 

#### Problems 
There are some problems with this method. 
1. The File Picker for setting the output file definition location would form an infinite loop if you pressed cancel or selected an incorrect file. 
   - This has been fixed by changing the ```while``` to a ```for``` loop
2. If there are no Product name (due to incorrect definition file) than none will display on the "Choose Product Name" picker this can be confusing. 

#### Description of functions for create ddl
Create DDL is kicked off in the GUI with the ```CreateDDLButtonListener``` which implements ActionListener. When clicked the method will check that the user has an output directory selected. If not if will display the prompt to choose an output directory. If the output directory is given than ```setOutpuClasses```, ```getSQLStatements```, and ```writeSQL``` will be called to create the DDL. 

#### ```EdgeConvertGUI: getOutputClasses()```

This function gathers all classes that extend ```EdgeConvertCreateDDL``` in order to return the classes as Product Names. 

First, the method iterates through all the ```.class``` files and then gathers only those that also extend ```EdgeConvertCreateDDL```. Next the method ```getProductName()``` is invoked and the products are added to the ```alProductNames``` list. This list is then shown to the user in the GUI in order to pick the Product Name for the creation of the DDL. 

#### Problems 
This method relies on the ```.class``` files that extend ```EdgeConvertCreateDDL``` and have the method ```getProductName```. However, the loop that searches the files for the ```.class``` files only lists the files in the working directory. This means YOU MUST set the Output Directory as the folder containing the ```.classs``` files. The prompt in the GUI has been updated accordingly to reflect this important note. 

#### ```EdgeConvertGUI: getSQLStatements()```

This method is pretty simple. First, it displays a prompt to the user via a JOptionPane to select a product. NOTE: if your output directory is not set correctly (set to a directory containing the class files). 

Once the Product Name is chosen the the class of the product is stored in ```selectedSubClass`` by using the ```getClass()``` method. With this class the SQL String and Database name are stored and displayed to the user. This ends the method. 

#### ```EdgeConvertGUI: writeSQL()```

First this function resets the File Filters and if the file is not parsed, parses it. After the file is successfully parsed using ```EdgeConvertFileParser``` a prompt to save the file is displayed to the user. If the file would overwrite an existing file the user is notified. 





