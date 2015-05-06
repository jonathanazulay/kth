/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.dto;

/**
 *
 * @author jonathanazulay
 */
public class InspectionStepResult {
    private boolean passed;
    private String note;

    public InspectionStepResult (boolean passed) {
        this.passed = passed;
    }
    
    public InspectionStepResult (boolean passed, String note) {
        this.passed = passed;
        this.note = note;
    }
    
    public void setNote (String note) {
        this.note = note;
    }
    
    public String getNote () {
        return this.note;
    }
    
    public boolean getPassed  () {
        return this.passed;
    }
}
