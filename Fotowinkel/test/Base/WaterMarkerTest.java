package Base;

import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martijn
 */
public class WaterMarkerTest {

    public WaterMarkerTest() {
    }

    BufferedImage modify = new BufferedImage(400, 500, BufferedImage.TYPE_BYTE_INDEXED);
    BufferedImage waterMark = new BufferedImage(400, 500, BufferedImage.TYPE_BYTE_INDEXED);

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestMakeingThumbnail() {
        assertNotNull(Helpers.WaterMarker.AddToImage(modify, waterMark, 300, true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestScaleZero() {
        Helpers.WaterMarker.AddToImage(modify, waterMark, 0, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestNullImage() {
        Helpers.WaterMarker.AddToImage(modify, null, 200, true);
    }

}
