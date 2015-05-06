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

public class Controller {
    private Inspection inspection;
    private Sale sale;
       
    /**
     * Starts a new inspection
     */
    public void startNewInspection () {
        this.inspection = new Inspection();
    }
    
    /**
     * Enters a license number for the inspection and returns price
     * @param licenseNumber
     * @return cost
     */
    public float enterLicenseNumber (String licenseNumber) {
        this.inspection.setStorageHandler(
            CarCatalogHandler.getResultStorage(licenseNumber)
        );
        
        this.sale = new Sale(this.inspection);
        return this.sale.calculateCost();
    }
    
    /**
     * Pay with creditcard
     * @param creditCard card to use
     * @param amount
     * @return payment result containing updated total and possible change
     */
    public PaymentResult payWithCreditCard (CreditCard creditCard, float amount) {
        return this.sale.payWithCreditCard(creditCard, amount);
    }
    
    /**
     * Pay with cash
     * @param amount
     * @return payment result containing updated total and possible change
     */
    public PaymentResult payWithCash (float amount) {
        return this.sale.payWithCash(amount);
    }
        
    /**
     * Returns the description of the next inspection step to perform
     * @return 
     */
    public InspectionStepDescription performInspection () {
        return inspection.getNextStep();
    }
    
    /**
     * Enters the result of the current inspection step being processed
     * @param passed 
     */
    public void enterResult (boolean passed) {
        inspection.setLastStepResult(new InspectionStepResult(passed));
    }
    
    /**
     * Saves all results and prints a document
     */
    public void finishInspection () {
        Document resultDoc = inspection.finishInspection();
        PrinterService.print(resultDoc);
    }
}
