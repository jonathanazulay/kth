package se.kth.iv1350.carinspection.storage;

import java.util.HashMap;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;
import se.kth.iv1350.carinspection.model.InspectionStep;

public class ResultStorageHandler {
    private final String licenseNumber;
    private InspectionStep[] storedSteps;
    private final HashMap<String, InspectionStep[]> data = new HashMap<>();
    
    /**
     * Creates a ResultStorageHandler
     * 
     * @param licenseNumber license number to store results for
     */
    public ResultStorageHandler(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        this.insertDummyData();
    }
    
    /**
     * Uses the ResultStorageHandler to fetch the results from storage
     * 
     * @return the iinspection steps stored for this license number
     */
    public InspectionStep[] loadStepResults () {
        this.fetch();
        return this.storedSteps;
    }
    
    /**
     * Uses the ResultStorageHandler to save the results to storage
     * 
     * @param steps the new inspection steps to store
     */
    public void saveStepResults (InspectionStep[] steps) {
        this.storedSteps = steps;
        this.store();
    }
    
    private void fetch () {
        // In a real scenario this would load from a db, textfile or something
        this.storedSteps = data.get(this.licenseNumber);
    }
    
    private void store () {
        // In a real scenario this would save to a db, textfile or something
        data.put(this.licenseNumber, this.storedSteps);
    }
    
    private void insertDummyData () {
        InspectionStep[] steps1 = new InspectionStep[3];
        steps1[0] = new InspectionStep(new InspectionStepDescription("Kontrollera däck, tryck och slitage"));
        steps1[0].setResult(new InspectionStepResult(false, "besiktning ej utförd"));
        steps1[1] = new InspectionStep(new InspectionStepDescription("Kontrollera bromsar"));
        steps1[1].setResult(new InspectionStepResult(false, "besiktning ej utförd"));
        steps1[2] = new InspectionStep(new InspectionStepDescription("Kontrollera lysen, strålkastare och blinkers"));
        steps1[2].setResult(new InspectionStepResult(false, "besiktning ej utförd"));
        data.put("WPK123", steps1);
        
        InspectionStep[] steps2 = new InspectionStep[3];
        steps2[0] = new InspectionStep(new InspectionStepDescription("Kontrollera däck, tryck och slitage"));
        steps2[0].setResult(new InspectionStepResult(false, "besiktning ej utförd"));
        steps2[1] = new InspectionStep(new InspectionStepDescription("Kontrollera bromsar"));
        steps2[1].setResult(new InspectionStepResult(true, ""));
        steps2[2] = new InspectionStep(new InspectionStepDescription("Kontrollera lysen, strålkastare och blinkers"));
        steps2[2].setResult(new InspectionStepResult(true, ""));
        data.put("OWA238", steps2);
        
        InspectionStep[] steps3 = new InspectionStep[3];
        steps3[0] = new InspectionStep(new InspectionStepDescription("Kontrollera däck, tryck och slitage"));
        steps3[0].setResult(new InspectionStepResult(true, ""));
        steps3[1] = new InspectionStep(new InspectionStepDescription("Kontrollera bromsar"));
        steps3[1].setResult(new InspectionStepResult(false, "besiktning ej utförd"));
        steps3[2] = new InspectionStep(new InspectionStepDescription("Kontrollera lysen, strålkastare och blinkers"));
        steps3[2].setResult(new InspectionStepResult(false, "besiktning ej utförd"));
        data.put("DAK048", steps3);
    }
    
}
