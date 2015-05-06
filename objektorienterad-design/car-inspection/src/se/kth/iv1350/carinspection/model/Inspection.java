package se.kth.iv1350.carinspection.model;

import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;
import se.kth.iv1350.carinspection.dto.Document;
import se.kth.iv1350.carinspection.storage.ResultStorageHandler;

public class Inspection {
    private ResultStorageHandler storageHandler;
    private InspectionStep[] inspectionSteps;
    private int currentStep = 0;
    
    public void setStorageHandler(ResultStorageHandler sh) {
        storageHandler = sh;
        inspectionSteps = sh.loadStepResults();
    }
    
    public InspectionStepDescription getNextStep() {
        if (currentStep >= inspectionSteps.length) {
            return null;
        } else {
            return inspectionSteps[currentStep++].getDescription();
        }
    }

    public void setLastStepResult(InspectionStepResult result) {
        inspectionSteps[currentStep - 1].setResult(result);
    }
    
    public int getInspectionsLeft () {
        return inspectionSteps.length;
    }
    
    public Document finishInspection() {
        storageHandler.saveStepResults(inspectionSteps);
        return new Document(this.generateInspectionSummary());
    }
    
    private String generateInspectionSummary() {
        return "Allt gick säkert bra, kör hårt";
    }
}
