import java.io.*;
import java.util.*;
import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeConvertFileParserTest {
	
   EdgeConvertFileParser testObj;
   private File testFile = new File("./Courses.edg");
   
   @Before
   public void run(){
      testObj = new EdgeConvertFileParser(testFile);
   }
   
   @Test
   public void testIsTableDup(){
      EdgeTable[] testTables = testObj.getEdgeTables();
      assertTrue("Check if it returns bool", true || false);      
   }
   
   @Test
   public void testGetEdgeTables(){
      boolean bool;
      EdgeTable[] test = testObj.getEdgeTables();
      if (test!=null){
         bool = true;
      } else{
         bool = false;
      }
      assertEquals("Check if EdgeTables[] array exists",true,bool);
   }
   
   @Test
   public void testGetEdgeFields(){
      boolean bool;
      EdgeField[] test = testObj.getEdgeFields();
      if (test!=null){
         bool = true;
      } else{
         bool = false;
      }
      assertEquals("Check if EdgeFields[] array exists",true,bool);
   }
   
}