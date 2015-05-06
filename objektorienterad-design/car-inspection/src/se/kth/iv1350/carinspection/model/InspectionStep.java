package se.kth.iv1350.carinspection.model;

import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;

public class InspectionStep {
    private InspectionStepDescription description;
    private InspectionStepResult result;
    
    public InspectionStep(InspectionStepDescription descr) {
        this.description = descr;
    }
    
    public InspectionStepDescription getDescription() {
        return this.description;
    }
    
    public void setResult(InspectionStepResult result) {
        this.result = result;
    }
    
    public InspectionStepResult getResult() {
        return this.result;
    }
}
