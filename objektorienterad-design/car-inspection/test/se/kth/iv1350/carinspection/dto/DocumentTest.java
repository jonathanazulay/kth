package se.kth.iv1350.carinspection.dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class DocumentTest {
    @Test
    public void testGetContent() {
        Document doc = new Document("Test dokument");
        assertEquals(doc.getContent(), "Test dokument");
    }
}
