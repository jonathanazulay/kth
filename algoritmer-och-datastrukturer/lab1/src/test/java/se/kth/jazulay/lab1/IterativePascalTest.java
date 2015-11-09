/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.jazulay.lab1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonathanazulay
 */
public class IterativePascalTest {
    /**
     * Test of binom method, of class IterativePascal.
     */
    @Test
    public void testBinom() {
        IterativePascal rp = new IterativePascal(false);
        assertEquals("testing 4,2", rp.binom(9, 3), 84);
    }
    
}
