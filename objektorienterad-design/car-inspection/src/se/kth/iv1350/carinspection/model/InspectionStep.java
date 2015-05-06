package se.kth.iv1350.carinspection.model;

import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;

public class InspectionStep {
    private final InspectionStepDescription description;
    private InspectionStepResult result;
    
    /**
     * Creates a inspection step with provided description
     * 
     * @param descr object representing a description on 
     * how to perform inspection
     */
    public InspectionStep(InspectionStepDescription descr) {
        this.description = descr;
    }
    
    /**
     * Getter for inspection step description
     * @return the desription for this inspection step
     */
    public InspectionStepDescription getDescription() {
        return this.description;
    }
    
    /**
     * Sets the result of the inspection step
     * @param result
     */
    public void setResult(InspectionStepResult result) {
        this.result = result;
    }
    
    /**
     * Gets the result of the inspection step
     * @return result
     */
    public InspectionStepResult getResult() {
        return this.result;
    }
}
