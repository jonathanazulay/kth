package se.kth.iv1350.carinspection.storage;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;
import se.kth.iv1350.carinspection.model.InspectionStep;

public class ResultStorageHandlerTest {
    
    private ResultStorageHandler storageHandler;
    
    @Before
    public void setup() {
        this.storageHandler = new ResultStorageHandler("TEST_LICENSE_NUM");
    }

    @Test
    public void testLoadAndSave() {
        InspectionStep[] stepsToSave = new InspectionStep[2];
        stepsToSave[0] = new InspectionStep(new InspectionStepDescription("TestBeskrivning1"));
        stepsToSave[0].setResult(new InspectionStepResult(true));
        stepsToSave[1] = new InspectionStep(new InspectionStepDescription("TestBeskrivning2"));
        stepsToSave[1].setResult(new InspectionStepResult(false, "anledning"));
        
        this.storageHandler.saveStepResults(stepsToSave);
        
        InspectionStep[] loadedSteps = this.storageHandler.loadStepResults();
        
        assertArrayEquals(stepsToSave, loadedSteps);
    }
}
