import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CreateDDLMySQLTest {
	CreateDDLMySQL testObj;
	EdgeTable[] testTables;
   EdgeField[] testFields;
   EdgeTable table;
	EdgeField field;
	
	String tableName;
	String weightOfField;
	String fieldName;

	@Before
	public void setUp() throws Exception {
		 // Initializing the table and field arrays
		// with just 1 value each
		tableName = "Student";
      table = new EdgeTable("1|" + tableName); 
		table.addNativeField(1);
		table.makeArrays();
		
		weightOfField = "1";
		fieldName = "ID";
		field = new EdgeField(weightOfField + "|" + fieldName);

		// Creating a new EdgeTable
		testTables = new EdgeTable[]{
         table
		};

		// Creating a new EdgeField
		testFields = new EdgeField[]{
         field
      };

		testObj = (CreateDDLMySQL) new CreateDDLMySQL(testTables, testFields);
	}

	@Test
	public void testConvertStrBooleanToIntTrue() {
		// Testing a true value converting to a 1
		assertEquals("True converts to 1", 1, testObj.convertStrBooleanToInt("true"));
	}

	@Test
	public void testConvertStrBooleanToIntFalse() {
		// Testing a false value converting to a 0
		assertEquals("False converts to 0", 0, testObj.convertStrBooleanToInt("false"));
	}

	@Test
	public void testGenerateDatabaseName() {
		// Testing the default database name
		assertEquals("Generated Database name is MySQLDB", "MySQLDB", testObj.generateDatabaseName());
	}

	@Test
	public void testGetProductName() {
		// Testing the generated product name
		assertEquals("Product Name is MySQL", "MySQL", testObj.getProductName());
	}

	@Test
	public void testGetSQLString() {
		// Testing the generated SQL String
		// First the string is split by its line breaks
		// then each expected value is matched
		String sqlString = testObj.getSQLString();
		// Splitting up sqlString into different lines for testing
		String[] values = sqlString.split("\n");

		// Matching the database to the generated database name
      assertTrue(values[0].contains("CREATE DATABASE " + testObj.generateDatabaseName() + ";"));
		// Matching the db to use to the db name
      assertTrue(values[1].contains("USE " + testObj.generateDatabaseName() + ";"));
		// Matching the table to the table name
      assertTrue(values[2].contains("CREATE TABLE " + tableName + " ("));
		// Matching the field
      assertTrue(values[3].contains(fieldName + " VARCHAR(" + weightOfField + "),"));
	}

}
