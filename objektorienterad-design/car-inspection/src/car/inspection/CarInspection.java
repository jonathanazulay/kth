/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car.inspection;

import se.kth.iv1350.carinspection.controller.Controller;
import se.kth.iv1350.carinspection.view.View;

/**
 *
 * @author jonathanazulay
 */
public class CarInspection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
    }

}
