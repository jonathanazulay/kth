package se.kth.iv1350.carinspection.dto;

public class InspectionStepDescription {
    private final String description;
    
    /**
     * Creates a description for a inspection step
     * @param description 
     */
    public InspectionStepDescription(String description) {
        this.description = description;
    }
    
    /**
     * Description getter
     * @return description
     */
    public String getDescription () {
        return this.description;
    }
}
