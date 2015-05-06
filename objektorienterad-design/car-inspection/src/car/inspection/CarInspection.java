package car.inspection;

import se.kth.iv1350.carinspection.controller.Controller;
import se.kth.iv1350.carinspection.view.View;

public class CarInspection {
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
    }
}
