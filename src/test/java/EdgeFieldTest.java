import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EdgeFieldTest {
    EdgeField testObject;

    @Before
    public void createTestObject() throws Exception {
        testObject = new EdgeField("1|Eric");
    }

    @Test
    public void testGetNumFigure() {
        // numFigure should return the number we created it with, 1
        int numFigure = testObject.getNumFigure();
        assertEquals("numFigure should be value we instantiated it with", 1, numFigure);
    }

    @Test
    public void testGetName() {
        // name should return the name we passed to it, Eric
        String name = testObject.getName();
        assertEquals("name should be value we instantiated it with", "Eric", name);
    }

    @Test
    public void testGetTableID() {
        // default tableID should be 0, as stated in the default constructor
        int defaultTableID = testObject.getTableID();
        assertEquals("default tableID should be 0 as stated in default constructor", 0, defaultTableID);
    }

    @Test
    public void testSetTableID() {
        // We know getTableID works on default, now set to make sure set works
        testObject.setTableID(1);
        int tableID = testObject.getTableID();
        assertEquals("new tableID value should be what we set it to", 1, tableID);
    }

    @Test
    public void testGetTableBound() {
        // default table bound should be 0, as stated in default constructor
        int defaultTableBound = testObject.getTableBound();
        assertEquals("default tableBound should be 0 as stated in constructor", 0, defaultTableBound);
    }

    @Test
    public void testSetTableBound() {
        // We know getTableBound works on default, now set and get to make sure set works
        testObject.setTableBound(2);
        int tableBound = testObject.getTableBound();
        assertEquals("new tableBound value should be what we set it to be", 2, tableBound);
    }

    @Test
    public void testGetFieldBound() {
        // default field bound should be 0, as in default constructor
        int defaultFieldBound = testObject.getFieldBound();
        assertEquals("default fieldBound should be 0, as in constructor", 0, defaultFieldBound);
    }

    @Test
    public void testSetFieldBound() {
        // we know getFieldBound works on default, now set and get again
        testObject.setFieldBound(3);
        int fieldBound = testObject.getFieldBound();
        assertEquals("new fieldBound value should be what we set the new value to be", 3, fieldBound);
    }

    @Test
    public void testGetDisallowNull() {
        // default disallowNull value should be false, as in constructor
        boolean defaultDisallowNull = testObject.getDisallowNull();
        assertEquals("default disallowNull value should be false, as in constructor", false, defaultDisallowNull);
    }

    @Test
    public void testSetDisallowNull() {
        // we know getDisallowNull works on default value, now set and compare new value
        testObject.setDisallowNull(true);
        boolean disallowNull = testObject.getDisallowNull();
        assertEquals("new disallowNull value should be what we set it to be", true, disallowNull);
    }

    @Test
    public void testGetIsPrimaryKey() {
        // default IsPrimaryKey value should be false
        boolean defaultIsPrimaryKey = testObject.getIsPrimaryKey();
        assertEquals("default isPrimaryKey value should be false in constructor", false, defaultIsPrimaryKey);
    }

    @Test
    public void testSetIsPrimaryKey() {
        // get works, now set and compare values
        testObject.setIsPrimaryKey(true);
        boolean isPrimaryKey = testObject.getIsPrimaryKey();
        assertEquals("new isPrimaryKey value should be what we set it to", true, isPrimaryKey);
    }

    @Test
    public void testGetDefaultValue() {
        // default defaultValue should be an empty string, ""
        String defaultDefaultValue = testObject.getDefaultValue();
        assertEquals("default defaultValue should be an empty string", "", defaultDefaultValue);
    }

    @Test
    public void testSetDefaultValue() {
        // set the default value to something else and get to compare
        testObject.setDefaultValue("notnull");
        String defaultValue = testObject.getDefaultValue();
        assertEquals("new defaultValue should be what we set it to", "notnull", defaultValue);
    }

    @Test
    public void testGetVarcharValue() {
        // default varchar value should be VARCHAR_DEFAULT_LENGTH in constructor
        int defaultVarcharValue = testObject.getVarcharValue();
        assertEquals("default varcharValue should be VARCHAR_DEFAULT_LENGTH", EdgeField.VARCHAR_DEFAULT_LENGTH, defaultVarcharValue);
    }

    @Test
    public void testSet0VarcharValue() {
        // not supposed to do anything if value set is < 0, test that
        testObject.setVarcharValue(0);
        int varcharValue = testObject.getVarcharValue();
        assertNotEquals("varcharValue should NOT be value less than or equal to 0", 0, varcharValue);
    }

    @Test
    public void testSetVarcharValue() {
        // test that set with a value greater than 0 actually sets the varcharValue
        testObject.setVarcharValue(5);
        int varcharValue = testObject.getVarcharValue();
        assertEquals("varcharValue should be equal to value we set it to", 5, varcharValue);
    }

    @Test
    public void testGetDataType() {
        // default datatype value should be 0, as in the constructor
        int defaultDatatype = testObject.getDataType();
        assertEquals("default datatype value should be 0 as in constructor", 0, defaultDatatype);
    }

    @Test
    public void testSetDataType() {
        // setting datatype has to be equal to or greater than 0, but less than strDataType length. 0 should always work, so set and test that
        testObject.setDataType(0);
        int dataType = testObject.getDataType();
        assertEquals("dataType should be equal to value we set it to", 0, dataType);
    }

    @Test
    public void printStatements() {
        System.out.println(testObject.toString());
    }
}
