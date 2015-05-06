package se.kth.iv1350.carinspection.storage;

import java.util.HashMap;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;
import se.kth.iv1350.carinspection.model.InspectionStep;

public class ResultStorageHandler {
    private String licenseNumber;
    private InspectionStep[] storedSteps;
    private HashMap<String, InspectionStep[]> data = new HashMap<>();
    
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
        InspectionStep[] steps;
        
        steps = new InspectionStep[3];
        steps[0] = new InspectionStep(new InspectionStepDescription("Kontrollera däck, tryck och slitage"));
        steps[0].setResult(new InspectionStepResult(false));
        steps[1] = new InspectionStep(new InspectionStepDescription("Kontrollera bromsar"));
        steps[1].setResult(new InspectionStepResult(true));
        steps[2] = new InspectionStep(new InspectionStepDescription("Kontrollera lysen, strålkastare och blinkers"));
        steps[2].setResult(new InspectionStepResult(false));
        data.put("WPK123", steps);
        
        steps = new InspectionStep[3];
        steps[0] = new InspectionStep(new InspectionStepDescription("Kontrollera däck, tryck och slitage"));
        steps[0].setResult(new InspectionStepResult(false));
        steps[1] = new InspectionStep(new InspectionStepDescription("Kontrollera bromsar"));
        steps[0].setResult(new InspectionStepResult(false));
        steps[2] = new InspectionStep(new InspectionStepDescription("Kontrollera lysen, strålkastare och blinkers"));
        steps[0].setResult(new InspectionStepResult(false));
        data.put("OWA238", steps);
        
        steps = new InspectionStep[3];
        steps[0] = new InspectionStep(new InspectionStepDescription("Kontrollera däck, tryck och slitage"));
        steps[0].setResult(new InspectionStepResult(false));
        steps[1] = new InspectionStep(new InspectionStepDescription("Kontrollera bromsar"));
        steps[0].setResult(new InspectionStepResult(false));
        steps[2] = new InspectionStep(new InspectionStepDescription("Kontrollera lysen, strålkastare och blinkers"));
        steps[0].setResult(new InspectionStepResult(true));
        data.put("DAK048", steps);
    }
    
}
