/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import static Base.Encoder.GenerateCodeStrings;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Rowan
 */
public class EncoderTest
{
    public final static int LOOPS = 1000000;

    public EncoderTest()
    {
    }

    @Test
    public void TestUniqueness()
    {
        Set<String> codes = new HashSet<String>();
        long start = System.nanoTime();
        
        for (int i = 0; i <= LOOPS; i++)
        {
            String toAdd = GenerateCodeStrings(1)[0];
            
            if (((double)(i * 100) / LOOPS) % 10 == 0)
            {
                System.out.println(((i * 100) / LOOPS) + " done...");
            }
            
            if (codes.contains(toAdd))
            {
                Assert.fail("Code was already generated. Caused in " + i + " attempts.");
            }
            else
            {
                codes.add(toAdd);
            }
        }
        double end = System.nanoTime();
        double elapsed = (end - start) / 1000000000;
        System.out.println(LOOPS + " unique codes generated in " + elapsed + "s. (" + (end - start) / LOOPS + "ns per code)");
    }
}
