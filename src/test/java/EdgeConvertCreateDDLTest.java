import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EdgeConvertCreateDDLTest {
	EdgeConvertCreateDDL testObj;
	EdgeTable[] testTables;
   EdgeField[] testFields;
   EdgeTable table;
   EdgeField field;

	@Before
	public void setUp() throws Exception {
      // Initializing the table and field arrays
      // with just 1 value each
      table = new EdgeTable("1|Student");
      field = new EdgeField("1|ID");

		// Creating a new EdgeTable
		testTables = new EdgeTable[]{
         table
		};

		// Creating a new EdgeField
		testFields = new EdgeField[]{
         field
      };
      
      // Creating a testObj with abstract methods overridden
      // initialized with the testTables and testFields array
		testObj = new EdgeConvertCreateDDL(testTables, testFields){
         @Override
         public String getSQLString() {
            createDDL();
            return sb.toString();
         }
      
         @Override
         public String getProductName() {
            return "MySQL";
         }
      
         @Override
         public String getDatabaseName() {
            return "DatabaseName";
         }
      
         @Override
         public void createDDL() {
            // Creation of a very basic DDL script
            // contains db and the name of the first table
            // and first field
            // this method is more extensively tested in CreateDDLMySQLTest
            String databaseName = getDatabaseName();
            sb.append("CREATE DATABASE " + databaseName + ";\r\n");
            sb.append("USE " + databaseName + ";\r\n");
            sb.append("CREATE TABLE " + testTables[0].getName() + " (\r\n");
            sb.append("    " + testFields[0].getName() + "\r\n");
            sb.append(")\r\n");
         }
      };
	}

	@Test
	public void testGetProductName() {
      // Checking if the name of the product is the standard string
		assertEquals("Product Name is MySQL", "MySQL", testObj.getProductName());
	}

	@Test
	public void testGetDatabaseName() {
      // Checking if the database name is the standard string
		assertEquals("Database Name is DatabaseName", "DatabaseName", testObj.getDatabaseName());
	}
   
	@Test
	public void testGetFieldsLength() {
      // Checking that the length of the fields is equal to (1)
      // which is what was initialized
      int length = testObj.getFields().length;
      System.out.println("Fields Length: " + length);
		assertEquals("Length of the Fields is 1", 1, length);
	}
   
	@Test
	public void testGetTablesLength() {
      // Checking that the length of the table is equal to (1)
      // which is what was initialized
      int length = testObj.getTables().length;
      System.out.println("Table Length: " + length);
		assertEquals("Length of the Tables is 1", 1, length);
   }
   
	@Test
	public void testGetTableName() {
      // Getting the name of the table passed into the testTables
      String tableName = table.getName();
      System.out.println("Table Name: " + tableName);
		assertEquals("Table Name is Student", "Student", tableName);
   }
   
	@Test
	public void testGetRelatedFieldsArray() {
      // Checking that the length of the related fields is equal to 
      // the default 10
      int[] array = table.getRelatedFieldsArray();;
      System.out.println("Related Fields Array Length: " + array.length);
		assertEquals("Related Fields Array Length is 10", 10, array.length);
   }
   
	@Test
	public void testSQLStringCreateDDL() {
      // Checking that the basic DDL contains the correct
      // table name and field name
      String sqlString = testObj.getSQLString();
      // Matching the default table
      assertTrue(sqlString.contains("Student"));
      // Matching the default field
      assertTrue(sqlString.contains("ID"));
   }

}
