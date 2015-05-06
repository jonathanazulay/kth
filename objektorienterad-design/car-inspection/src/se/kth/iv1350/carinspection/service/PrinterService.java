/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.service;

import se.kth.iv1350.carinspection.dto.Document;

/**
 *
 * @author jonathanazulay
 */
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
