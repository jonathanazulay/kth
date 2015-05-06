package se.kth.iv1350.carinspection.view;

import se.kth.iv1350.carinspection.controller.Controller;
import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.dto.InspectionStepDescription;

public class View {

    public View(Controller controller) {
        System.out.println("* controller.startNewInspection");
        controller.startNewInspection();
        System.out.println("new inspection started!\n");
        
        System.out.println("* controller.enterLicenseNumber");
        float cost = controller.enterLicenseNumber("WPK123");
        System.out.println("cost for inspection is " + cost + "\n");
        
        // Pay the full cost of inspection
        System.out.println("* controller.payWithCreditCard");
        controller.payWithCreditCard(new CreditCard("7839393839486199", 3897), cost);
        System.out.println("inspection was payed for \n");
        
        InspectionStepDescription instruction;
        
        // Loop until no more inspections to do
        while ((instruction = controller.performInspection()) != null) {
            System.out.println("* controller.performInspection: ");
            System.out.println(instruction.getDescription() + "\n");
            
            System.out.println("* controller.enterResult: ");
            controller.enterResult(true);
            System.out.println("result was entered\n");
        }
        
        System.out.println("* controller.finishInspection\n");
        controller.finishInspection();
        
    }
    
}
