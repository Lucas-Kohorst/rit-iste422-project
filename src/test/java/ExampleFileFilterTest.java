import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ExampleFileFilterTest {

    @Test
    public void testWithoutFilters() {
        ExampleFileFilter filter = new ExampleFileFilter();
        assertTrue(filter.isExtensionListInDescription());
    }

    @Test
    public void testWithOneExtension() {
        ExampleFileFilter filter = new ExampleFileFilter("txt");
        assertTrue(filter.accept(new File("src/test.txt")));
        assertEquals("(.txt)", filter.getDescription());
    }

    @Test
    public void testGetExtension() {
        ExampleFileFilter filter = new ExampleFileFilter();
        assertEquals("txt", filter.getExtension(new File("src/test.txt")));
    }

    @Test
    public void testGetExtensionWithNullInput() {
        ExampleFileFilter filter = new ExampleFileFilter();
        assertNull(filter.getExtension(null));
    }

    @Test
    public void testOneExtensionWithDescription() {
        ExampleFileFilter filter = new ExampleFileFilter("jpg", "JPEG Image Images");
        assertEquals("JPEG Image Images (.jpg)", filter.getDescription());
    }

    @Test
    public void testAddExtension() {
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.addExtension("txt");
        assertEquals("(.txt)", filter.getDescription());
    }

    @Test
    public void testArrayOfFilters() {
        ExampleFileFilter filter = new ExampleFileFilter(new String[]{"gif", "jpg"});
        assertTrue(filter.accept(new File("src/test.gif")));
        assertEquals("(.gif, .jpg)", filter.getDescription());
        assertTrue(filter.isExtensionListInDescription());
    }

    @Test
    public void testArrayOfFiltersWithDescription() {
        ExampleFileFilter filter = new ExampleFileFilter(new String[]{"gif", "jpg"}, "Gif and JPG Images");
        assertTrue(filter.accept(new File("src/test.gif")));
        assertEquals("Gif and JPG Images (.gif, .jpg)", filter.getDescription());
        assertTrue(filter.isExtensionListInDescription());
    }

    @Test
    public void testAcceptWithoutFilter() {
        ExampleFileFilter filter = new ExampleFileFilter();
        assertFalse(filter.accept(new File("src/test.gif")));
    }

    @Test
    public void testAcceptWithFilters() {
        ExampleFileFilter filter = new ExampleFileFilter(new String[]{"gif", "jpg"}, "Gif and JPG Images");
        assertTrue(filter.accept(new File("src/test.gif")));
        assertEquals("Gif and JPG Images (.gif, .jpg)", filter.getDescription());
        assertTrue(filter.isExtensionListInDescription());
    }

    @Test
    public void testAcceptForDirectoryInput() {
        ExampleFileFilter filter = new ExampleFileFilter(new String[]{"gif", "jpg"}, "Gif and JPG Images");
        assertTrue(filter.accept(new File("src")));
    }

    @Test
    public void testAcceptForWrongInput() {
        ExampleFileFilter filter = new ExampleFileFilter(new String[]{"gif", "jpg"}, "Gif and JPG Images");
        assertFalse(filter.accept(new File("pdf")));
    }

    @Test
    public void testAcceptForUpperCaseInput() {
        ExampleFileFilter filter = new ExampleFileFilter(new String[]{"gif", "jpg"}, "Gif and JPG Images");
        assertFalse(filter.accept(new File("GIF")));
    }

    @Test
    public void testSetExtensionListInDescription() {
        ExampleFileFilter filter = new ExampleFileFilter();
        filter.setExtensionListInDescription(true);
        assertTrue(filter.isExtensionListInDescription());
    }
}
