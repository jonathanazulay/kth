/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;
import se.kth.iv1350.carinspection.model.PaymentResult;

/**
 *
 * @author jonathanazulay
 */
public class ControllerTest {
    
    public ControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of startNewInspection method, of class Controller.
     */
    @Test
    public void testStartNewInspection() {
        System.out.println("startNewInspection");
        Controller instance = new Controller();
        instance.startNewInspection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enterLicenseNumber method, of class Controller.
     */
    @Test
    public void testEnterLicenseNumber() {
        System.out.println("enterLicenseNumber");
        String licenseNumber = "";
        Controller instance = new Controller();
        float expResult = 0.0F;
        float result = instance.enterLicenseNumber(licenseNumber);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of payWithCash method, of class Controller.
     */
    @Test
    public void testPayWithCash() {
        System.out.println("payWithCash");
        float amount = 0.0F;
        Controller instance = new Controller();
        PaymentResult expResult = null;
        PaymentResult result = instance.payWithCash(amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of payWithCreditCard method, of class Controller.
     */
    @Test
    public void testPayWithCreditCard() {
        System.out.println("payWithCreditCard");
        CreditCard creditCard = null;
        float amount = 0.0F;
        Controller instance = new Controller();
        PaymentResult expResult = null;
        PaymentResult result = instance.payWithCreditCard(creditCard, amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of performInspection method, of class Controller.
     */
    @Test
    public void testPerformInspection() {
        System.out.println("performInspection");
        Controller instance = new Controller();
        InspectionStepDescription expResult = null;
        InspectionStepDescription result = instance.performInspection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enterResult method, of class Controller.
     */
    @Test
    public void testEnterResult() {
        System.out.println("enterResult");
        boolean passed = false;
        Controller instance = new Controller();
        instance.enterResult(passed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finishInspection method, of class Controller.
     */
    @Test
    public void testFinishInspection() {
        System.out.println("finishInspection");
        Controller instance = new Controller();
        instance.finishInspection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
