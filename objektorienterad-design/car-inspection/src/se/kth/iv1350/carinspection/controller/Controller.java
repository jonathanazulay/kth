/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.controller;

import se.kth.iv1350.carinspection.model.Inspection;
import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.service.PaymentResult;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;

/**
 *
 * @author jonathanazulay
 */
public class Controller {
    private Inspection inspection;
    
    public Controller () {
        
    }
    
    public void startNewInspection () {
        
    }
    
    public float enterLicenseNumber (String licenseNumber) {
        return 0;
    }
    
    public PaymentResult payWithCash (float amount) {
        return new PaymentResult(false, "");
    }
    
    public PaymentResult payWithCreditCard (CreditCard creditCard, float amount) {
        return new PaymentResult(false, "");
    }
    
    public InspectionStepDescription performInspection () {
        return new InspectionStepDescription("");
    }
    
    public void enterResult (boolean passed) {
        
    }
    
    public void finishInspection () {
        
    }
}
