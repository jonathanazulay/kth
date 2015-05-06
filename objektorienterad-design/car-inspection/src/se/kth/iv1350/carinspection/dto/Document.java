package se.kth.iv1350.carinspection.dto;

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
