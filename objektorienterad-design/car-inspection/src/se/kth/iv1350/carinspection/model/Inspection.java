package se.kth.iv1350.carinspection.model;

import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;
import se.kth.iv1350.carinspection.dto.Document;
import se.kth.iv1350.carinspection.storage.ResultStorageHandler;

public class Inspection {
    private ResultStorageHandler storageHandler;
    private InspectionStep[] inspectionSteps = new InspectionStep[0];
    private int currentStep = 0;
    
    /**
     * Sets storageHandler and fetches from it. StorageHandler is
     * what will be used to fetch and store results of inspection.
     * 
     * @param storageHandler StorageHandler to store/fetch with
     */
    public void setStorageHandler(ResultStorageHandler storageHandler) {
        this.storageHandler = storageHandler;
        inspectionSteps = storageHandler.loadStepResults();
    }
    
    /**
     * Returns the next step of the inspection that doesn't
     * have its result set to passed.
     *
     * @return description of the next step or
     * null if no more steps to perform
     */
    public InspectionStepDescription getNextStep() {
        if (currentStep >= inspectionSteps.length) {
            return null;
        } else {
            InspectionStep possibleStep = inspectionSteps[currentStep++];
            if (possibleStep.getResult().getPassed() == false) {
                return possibleStep.getDescription();
            } else {
                return this.getNextStep();
            }
        }
    }

    /**
     * Sets the result of the current step
     * 
     * @param result of the inspection step
     */
    public void setLastStepResult(InspectionStepResult result) {
        inspectionSteps[currentStep - 1].setResult(result);
    }
    
    /**
     * Returns the number of inspections left that doesn't
     * have its result set to passed
     * 
     * @return number of inspections left
     */
    public int getInspectionsLeft () {
        int inspectionsLeft = 0;
        for (InspectionStep inspectionStep : inspectionSteps) {
            if (inspectionStep.getResult().getPassed() == false) {
                inspectionsLeft += 1;
            }
        }
        return inspectionsLeft;
    }
    
    /**
     * Saves the current state of the inspection and returns
     * a printable document
     * 
     * @return document representing result of inspection
     */
    public Document finishInspection() {
        this.storageHandler.saveStepResults(inspectionSteps);
        return new Document(this.generateInspectionSummary());
    }
    
    private String generateInspectionSummary() {
        StringBuilder sb = new StringBuilder("INSPEKTIONSRESULTAT\n");
        for (int i = 0; i < this.inspectionSteps.length; i += 1) {
            InspectionStep inspectionStep = this.inspectionSteps[i];
            InspectionStepResult stepResult = inspectionStep.getResult();
            InspectionStepDescription stepDescr = inspectionStep.getDescription();
            
            sb.append("\n" + stepDescr.getDescription() + ": ");
            if (stepResult.getPassed() == true) {
                sb.append("GODKÄND" + " (" + stepResult.getNote() + ")");
            } else {
                sb.append("EJ GODKÄND" + " (" + stepResult.getNote() + ")");
            }
        }
        return sb.toString();
    }
}
