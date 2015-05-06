/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.controller;

import se.kth.iv1350.carinspection.model.Inspection;
import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.dto.Document;
import se.kth.iv1350.carinspection.service.PaymentResult;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.InspectionStepResult;
import se.kth.iv1350.carinspection.model.Sale;
import se.kth.iv1350.carinspection.service.PrinterService;
import se.kth.iv1350.carinspection.storage.CarCatalogHandler;

/**
 *
 * @author jonathanazulay
 */
public class Controller {
    private Inspection inspection;
    private Sale sale;
        
    public void startNewInspection () {
        this.inspection = new Inspection();
    }
    
    public float enterLicenseNumber (String licenseNumber) {
        this.inspection.setStorageHandler(
            CarCatalogHandler.getResultStorage(licenseNumber)
        );
        
        this.sale = new Sale(this.inspection);
        return this.sale.calculateCost();
    }
    
    public PaymentResult payWithCreditCard (CreditCard creditCard, float amount) {
        return this.sale.payWithCreditCard(creditCard, amount);
    }
    
    public PaymentResult payWithCash (float amount) {
        return this.sale.payWithCash(amount);
    }
        
    public InspectionStepDescription performInspection () {
        return inspection.getNextStep();
    }
    
    public void enterResult (boolean passed) {
        inspection.setLastStepResult(new InspectionStepResult(passed));
    }
    
    public void finishInspection () {
        Document resultDoc = inspection.finishInspection();
        PrinterService.print(resultDoc);
    }
}
