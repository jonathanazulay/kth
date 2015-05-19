package se.kth.ict.nextgenpos.view;

import java.util.List;
import se.kth.ict.nextgenpos.controller.Controller;
import se.kth.ict.nextgenpos.model.ProductNotFoundException;
import se.kth.ict.nextgenpos.model.ProductSpecification;
import se.kth.ict.nextgenpos.model.SaleObserver;
import se.kth.ict.nextgenpos.model.SalesLineItem;

/**
 * A placeholder for the view.
 */
public class View implements SaleObserver {
    private Controller cont;

    /**
     * Creates a new <code>View</code>.
     * @param cont           The controller of the application.
     */
    public View(Controller cont) {
	this.cont = cont;
    }

    /**
     * Simulates a view. Makes some calls to the controller.
     */
    public void test() {
	cont.makeNewSale();
        cont.addSaleObserver(this);
	enterItem(1);
        enterItem(2);
	/*
            System.out.println(">>>>> NOTE!!\n" +
			   "A null pointer exception will follow since there is no handling" + 
			   " of non-existing item ids. When you have implemented exception" +
			   " handling, there should be some informative printout instead of the" +
			   " exception stack trace.");
        */
	enterItem(10);
    }

    @Override
    public void itemAdded(SalesLineItem lineItem) {
        // This event is not used but could be in the future, do nothing
    }
    
    @Override
    public void saleListUpdated(SalesLineItem[] allItems) {
        this.printList(allItems);
    }
    
    private void printList(SalesLineItem[] lineItems) {
        System.out.println("## Sale updated! Current items in Sale are:\n");
        for (SalesLineItem lineItem : lineItems) {
            System.out.println("* " + lineItem + "\n");
        }
    }
    
    private void displayError(Exception err) {
        System.out.println("Oops, try again! " + err.getMessage());
    }

    private void enterItem(int itemId) {
	int quantity = 1;
        try {
            ProductSpecification itemSpec = cont.enterItem(itemId, quantity);
        } catch (ProductNotFoundException productException) {
            this.displayError(productException);
        }   
    }
}
