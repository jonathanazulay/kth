/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.dto;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jonathanazulay
 */
public class DocumentTest {
    @Test
    public void testGetContent() {
        Document doc = new Document("Test dokument");
        assertEquals(doc.getContent(), "Test dokument");
    }
}
