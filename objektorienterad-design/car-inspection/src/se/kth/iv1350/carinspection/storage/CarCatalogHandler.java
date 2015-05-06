/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.storage;

/**
 *
 * @author jonathanazulay
 */
public class CarCatalogHandler {
    /**
     * Returns a storage unit for a specific license number
     * @param licenseNumber to store for
     * @return
     */
    public static ResultStorageHandler getResultStorage (String licenseNumber) {
        return new ResultStorageHandler(licenseNumber);
    }
}
