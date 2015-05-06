package se.kth.iv1350.carinspection.model;

import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.dto.PaymentResult;
import se.kth.iv1350.carinspection.storage.CarCatalogHandler;

public class SaleTest {
    
    private Inspection inspection;
    private final float expectedPriceForLicenseNumber = 216;
    private String licenseNumberToTest = "OWA238";
    
    private void createInspection (String licenseNumber) {
        this.inspection = new Inspection();
        this.inspection.setStorageHandler(
            CarCatalogHandler.getResultStorage(licenseNumber)
        );
    }

    @Test
    public void testCalculateCost() {
        createInspection(licenseNumberToTest);
        Sale sale = new Sale(inspection);
        
        assertEquals(
            sale.calculateCost(),
            this.expectedPriceForLicenseNumber,
            0
        );
    }

    @Test
    public void testPayWithCreditCard() {
        createInspection(licenseNumberToTest);
        Sale sale = new Sale(inspection);
        CreditCard creditCard = new CreditCard("98398029383439", 9838);
        
        PaymentResult result = sale.payWithCreditCard(creditCard, 30);
        
        // Test updated total
        assertEquals(
            this.expectedPriceForLicenseNumber - 30,
            result.getNewTotal(),
            0
        );
        
        // Check that change = 0
        
        assertEquals(
            0,
            result.getChange(),
            0
        );
        
        // Do a second payment, 100 kr more than new total
        PaymentResult resultAfterOverPay = sale.payWithCreditCard(creditCard, result.getNewTotal() + 100);

        // Check that change is 100
        assertEquals(
            100,
            resultAfterOverPay.getChange(),
            0
        );
    }

    @Test
    public void testPayWithCash() {
        createInspection(licenseNumberToTest);
        Sale sale = new Sale(inspection);
        
        PaymentResult result = sale.payWithCash(30);
        
        // Test updated total
        assertEquals(
            this.expectedPriceForLicenseNumber - 30,
            result.getNewTotal(),
            0
        );
        
        // Check that change = 0
        assertEquals(
            0,
            result.getChange(),
            0
        );
        
        // Do a second payment, 100 kr more than needed
        PaymentResult resultAfterOverPay = sale.payWithCash(result.getNewTotal() + 100);

        // Check that change is 100
        assertEquals(
            100,
            resultAfterOverPay.getChange(),
        0);
    }
}
