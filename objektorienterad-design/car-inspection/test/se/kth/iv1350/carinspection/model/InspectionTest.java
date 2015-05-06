/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.model;

import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;
import se.kth.iv1350.carinspection.storage.CarCatalogHandler;
import se.kth.iv1350.carinspection.storage.ResultStorageHandler;

/**
 *
 * @author jonathanazulay
 */
public class InspectionTest {
    
    private Inspection inspection;
    private final float expectedPriceForLicenseNumber = 118;
    private String licenseNumberToTest = "OWA238";
    
    private void createInspection (String licenseNumber) {
        this.inspection = new Inspection();
        this.inspection.setStorageHandler(
            CarCatalogHandler.getResultStorage(licenseNumber)
        );
    }
    
    @Test
    public void testSetStorageHandler() {
        Inspection inspection = new Inspection();
        ResultStorageHandler storage = CarCatalogHandler.getResultStorage(licenseNumberToTest);
        
        float numInspectionsBeforeLoad = inspection.getInspectionsLeft();
        inspection.setStorageHandler(storage);
        assertNotSame(numInspectionsBeforeLoad, inspection.getInspectionsLeft());
    }

    @Test
    public void testGetNextStep() {
        this.createInspection(licenseNumberToTest);
        InspectionStepDescription firstStepExpected = new InspectionStepDescription("Kontrollera däck, tryck och slitage");
        InspectionStepDescription secondStepExpected = new InspectionStepDescription("Kontrollera lysen, strålkastare och blinkers");
        
        InspectionStepDescription step1 = this.inspection.getNextStep();
        InspectionStepDescription step2 = this.inspection.getNextStep();
        InspectionStepDescription step3 = this.inspection.getNextStep();
        
        assertEquals(firstStepExpected.getDescription(), step1.getDescription());
        assertEquals(secondStepExpected.getDescription(), step2.getDescription());
        assertNull(step3); //out of steps, returns null
    }

    @Test
    public void testSetLastStepResult() {
        this.createInspection(licenseNumberToTest);
        int initialStepsLeft = this.inspection.getInspectionsLeft();
        this.inspection.getNextStep();
        this.inspection.setLastStepResult(new InspectionStepResult(true));
        int stepsLeft = this.inspection.getInspectionsLeft();
        
        assertNotSame(initialStepsLeft, stepsLeft);
    }

    @Test
    public void testFinishInspection() {
        Inspection inspection = new Inspection();
        ResultStorageHandler storage = CarCatalogHandler.getResultStorage(licenseNumberToTest);
        
        inspection.setStorageHandler(storage);
        
        inspection.getNextStep();
        inspection.setLastStepResult(new InspectionStepResult(true));
        inspection.getNextStep();
        inspection.setLastStepResult(new InspectionStepResult(false));
        inspection.finishInspection();
        
        InspectionStepResult step1ResultsAfterSave = storage.loadStepResults()[0].getResult();
        InspectionStepResult step2ResultsAfterSave = storage.loadStepResults()[2].getResult();
        
        assertEquals(step1ResultsAfterSave.getPassed(), true);
        assertEquals(step2ResultsAfterSave.getPassed(), false);
    }
    
}
