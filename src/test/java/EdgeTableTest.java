import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class EdgeTableTest {
    EdgeTable testObject;

    @Before
    public void createTestObject() throws Exception {
        testObject = new EdgeTable("1|Eric");
    }

    @Test
    public void testEdgeTableObject() {
        // make sure that the object we created exists
        assertNotNull(testObject);
    }

    @Test
    public void testGetNumFigure() {
        // numFigure should be what we instantiated it as with constructor parameter
        int numFigure = testObject.getNumFigure();
        assertEquals("numFigure should be what we instantiated it as in constructor creator", 1, numFigure);
    }

    @Test
    public void testGetName() {
        // name should be what we instantiated object with
        String name = testObject.getName();
        assertEquals("name should be what we constructed object with", "Eric", name);
    }

    @Test
    public void testMakeArrays() {
        // Since we need this makeArrays function to work first before we can test the other setters and
        // getters for related tables/fields, we start with this

        // create arrays and then print them out using toString method
        testObject.makeArrays();
        assertNotNull("should be able to print out string of empty arrays and information instead of null", testObject.toString());
    }

    @Test
    public void testGetRelatedTablesArray() {
        // add related table
        testObject.addRelatedTable(20);
        // we call makeArrays to instantiate the array
        testObject.makeArrays();

        //System.out.println(Arrays.toString(testObject.getRelatedTablesArray()));
        //System.out.println(testObject.getRelatedTablesArray().length);

        // make sure the length only increases to 1 since we only added 1 value
        assertEquals(testObject.getRelatedTablesArray().length, 1);
        // make sure the value is same as the one we added
        assertEquals(testObject.getRelatedTablesArray()[0], 20);
    }

    @Test
    public void testAddNativeField() {
        assertNull("native fields should be empty before instantiating", testObject.getNativeFieldsArray());
        // add a native field, then get the field
        testObject.addNativeField(1);
        // instantiate the arrays
        testObject.makeArrays();
        assertNotNull("native fields should no longer be empty", testObject.getNativeFieldsArray());
    }

    @Test
    public void testGetNativeFieldsArray() {
        testObject.addNativeField(3);
        testObject.makeArrays();

        // since we only added on value, length should be 1
        assertEquals(testObject.getNativeFieldsArray().length, 1);
    }

    @Test
    public void testGetRelatedFieldsArray() {
        testObject.addNativeField(1);
        // we call makeArrays to instantiate the arrays
        testObject.makeArrays();

        // make sure related fields array is not empty
        assertNotNull(testObject.getRelatedFieldsArray());
    }

    @Test
    public void testSetRelatedFieldsArray() {
        testObject.addNativeField(1);
        // we call makeArrays to instantiate the arrays
        testObject.makeArrays();
        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));
        testObject.setRelatedField(0, 5);

        // first value should be what we set it to, 5
        assertEquals("make sure the value of the first index is what we set it to", testObject.getRelatedFieldsArray()[0], 5);
        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));
    }

    @Test
    public void testAddRelatedTable() {
        testObject.makeArrays();

        //System.out.println(Arrays.toString(testObject.getRelatedTablesArray()));

        // get initial length returned
        int initialTable = testObject.getRelatedTablesArray().length;

        // try adding a related table
        testObject.addRelatedTable(5);
        testObject.makeArrays();

        //System.out.println(Arrays.toString(testObject.getRelatedTablesArray()));

        // get new length returned after adding
        int newTable = testObject.getRelatedTablesArray().length;
        assertNotEquals("length should have changed after adding", initialTable, newTable);
    }

    @Test
    public void testMoveFieldUp() {
        // create new fields with 0, 0, 0 values
        testObject.addNativeField(1);
        testObject.addNativeField(2);
        testObject.addNativeField(3);
        testObject.makeArrays();
        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));

        // set these values to 2, 4, and 6
        testObject.setRelatedField(0,2);
        testObject.setRelatedField(1,4);
        testObject.setRelatedField(2,6);

        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));

        // move the value at index 1 to index 0, and index 0 to index 1
        testObject.moveFieldUp(1);
        assertTrue(testObject.getRelatedFieldsArray()[0] == 4);
        assertTrue(testObject.getRelatedFieldsArray()[1] == 2);
        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));
    }

    @Test
    public void testMoveFieldDown() {
        // create new fields with 0, 0, 0 values
        testObject.addNativeField(1);
        testObject.addNativeField(2);
        testObject.addNativeField(3);
        testObject.makeArrays();
        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));

        // set these values to 2, 4, and 6
        testObject.setRelatedField(0,2);
        testObject.setRelatedField(1,4);
        testObject.setRelatedField(2,6);

        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));

        // move the value at index 1 to index 0, and index 0 to index 1
        testObject.moveFieldDown(1);
        assertTrue(testObject.getRelatedFieldsArray()[1] == 6);
        assertTrue(testObject.getRelatedFieldsArray()[2] == 4);
        //System.out.println(Arrays.toString(testObject.getRelatedFieldsArray()));
    }

    @Test
    public void testToString() {
        // we know makeArrays works
        testObject.makeArrays();
        // if toString returns empty object, we get NullPointerException so ensure it is not null
        assertNotNull("if toString returns empty object, we get NullPointerException so ensure it is not null", testObject.toString());
        //System.out.println(testObject.toString());
    }
}
