package se.kth.iv1350.carinspection.service;

import se.kth.iv1350.carinspection.dto.Document;

public class PrinterService {
    /**
     * Prints a document
     * @param document to print
     */
    public static void print (Document document) {
        System.out.println("PrinterService says: Printing a document, with content:");
        System.out.println(document.getContent());
    }
}
