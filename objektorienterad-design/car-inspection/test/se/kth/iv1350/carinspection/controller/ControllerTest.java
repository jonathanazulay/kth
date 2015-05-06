/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.controller;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.dto.Document;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.dto.PaymentResult;

/**
 *
 * @author jonathanazulay
 */
public class ControllerTest {
    
    private Controller controller;
    
    @Before
    public void setup () {
        this.controller = new Controller();
    }

    @Test(expected=NullPointerException.class) 
    public void testEnterLicenseNumberWithoutStartingInspection() {
        this.controller.enterLicenseNumber("OWA238");
    }
    
    @Test
    public void testEnterLicenseNumber() {
        this.controller.startNewInspection();
        float cost = this.controller.enterLicenseNumber("OWA238");
        assertEquals(216, cost, 0);
    }
    
    @Test(expected=NullPointerException.class) 
    public void testInvalidLicenseNumber() {
        this.controller.startNewInspection();
        float cost = this.controller.enterLicenseNumber("x");
        assertEquals(216, cost, 0);
    }

    @Test
    public void testPayWithCreditCard() {
        this.controller.startNewInspection();
        float cost = this.controller.enterLicenseNumber("OWA238");
        PaymentResult paymentRes = this.controller.payWithCreditCard(new CreditCard("1238127838279323", 2398), cost);
        assertEquals(paymentRes.getChange(), 0, 0);
        assertEquals(paymentRes.getNewTotal(), 0, 0);
        assertEquals(paymentRes.isSuccess(), true);
    }

    @Test
    public void testPayWithCash() {
        this.controller.startNewInspection();
        float cost = this.controller.enterLicenseNumber("OWA238");
        PaymentResult paymentRes = this.controller.payWithCash(cost);
        assertEquals(0, paymentRes.getChange(), 0);
        assertEquals(0, paymentRes.getNewTotal(), 0);
        assertEquals(true, paymentRes.isSuccess());
    }
    
    @Test
    public void testPayNegativeAmount() {
        this.controller.startNewInspection();
        float cost = this.controller.enterLicenseNumber("OWA238");
        PaymentResult paymentRes = this.controller.payWithCash(-100);
        assertEquals(0, paymentRes.getChange(), 0);
        assertEquals(cost, paymentRes.getNewTotal(), 0);
        assertEquals(false, paymentRes.isSuccess());
    }

    @Test
    public void testPerformInspection() {
        this.controller.startNewInspection();
        float cost = this.controller.enterLicenseNumber("OWA238");
        InspectionStepDescription inspectionStepDescr = this.controller.performInspection();
        assertEquals("Kontrollera däck, tryck och slitage", inspectionStepDescr.getDescription());
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class) 
    public void testEnterResultWithoutInspecting() {
        this.controller.startNewInspection();
        float cost = this.controller.enterLicenseNumber("OWA238");
        this.controller.enterResult(true);
    }
    
    public void testEnterResult() {
        this.controller.startNewInspection();
        this.controller.enterLicenseNumber("OWA238");
        this.controller.performInspection();
        this.controller.enterResult(true);
        this.controller.performInspection();
        this.controller.enterResult(false);
    }

    @Test
    public void testFinishInspection() {
        this.controller.startNewInspection();
        this.controller.enterLicenseNumber("OWA238");
        this.controller.performInspection();
        this.controller.enterResult(true);
        this.controller.performInspection();
        this.controller.enterResult(false);
        Document resultDoc = this.controller.finishInspection();
        assertEquals(
            "INSPEKTIONSRESULTAT\n" +
            "\n" +
            "Kontrollera däck, tryck och slitage: GODKÄND ()\n" +
            "Kontrollera bromsar: GODKÄND ()\n" +
            "Kontrollera lysen, strålkastare och blinkers: EJ GODKÄND ()",
            resultDoc.getContent()
        );
    }
    
}
