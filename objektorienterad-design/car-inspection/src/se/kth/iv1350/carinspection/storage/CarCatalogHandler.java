package se.kth.iv1350.carinspection.storage;

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
