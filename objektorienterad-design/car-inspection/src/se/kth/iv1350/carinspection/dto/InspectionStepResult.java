package se.kth.iv1350.carinspection.dto;

public class InspectionStepResult {
    private boolean passed;
    private String note;

    /**
     * Creates a inspection result without any notes
     * @param passed true/false depending on if result is passed or fail
     */
    public InspectionStepResult (boolean passed) {
        this(passed, "");
    }
    
    /**
     * Creates a inspection result with a note
     * @param passed true/false depending on if result is passed or fail
     * @param note explanation of the result, if applicable
     */
    public InspectionStepResult (boolean passed, String note) {
        this.passed = passed;
        this.note = note;
    }
    
    /**
     * Sets note
     * @param note explanation of the result, if applicable
     */
    public void setNote (String note) {
        this.note = note;
    }
    
    /**
     * Gets note
     * @return explanation of result or empty string
     */
    public String getNote () {
        return this.note;
    }
    
    /**
     * True/false depending on inspection step result
     * @return inspection step result boolean
     */
    public boolean getPassed  () {
        return this.passed;
    }
}
