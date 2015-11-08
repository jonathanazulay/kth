/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.jazulay.lab1;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jonathanazulay
 */
public class RecursivePascalTest {
    /**
     * Test of binom method, of class RecursivePascal.
     */
    @Test
    public void testBinom() {
        RecursivePascal rp = new RecursivePascal(false);
        assertEquals("testing 4,2", rp.binom(9, 3), 84);
    }
}
