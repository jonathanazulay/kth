/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.dto;

/**
 *
 * @author jonathanazulay
 */
public class Document {
    private final String content;

    /**
     * Creates a new document with specified content
     * @param content contents of document
     */
    public Document(String content) {
        this.content = content;
    }
    
    /**
     * Returns the content
     * @return document content
     */
    public String getContent() {
        return content;
    }
}
