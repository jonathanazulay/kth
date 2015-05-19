package se.kth.ict.nextgenpos.model;

import java.util.Map;
import java.util.HashMap;

/**
 * This singleton class is responsible for all access to the product database.
 */ 
public class ProductCatalog {
    private static ProductCatalog instance = new ProductCatalog();
    private Map<Integer, ProductSpecification> products = 
	new HashMap<Integer, ProductSpecification>();

    /**
     * Fills the catalog with some dummy items.
     */
    private ProductCatalog() {
	products.put(1, new ProductSpecification(1, "low fat milk", 
	   "a very long description, a very long description, a very long description", 10));
	products.put(2, new ProductSpecification(2, "butter", 
	   "a very long description, a very long description, a very long description", 10));
	products.put(3, new ProductSpecification(3, "bread", 
	   "a very long description, a very long description, a very long description", 10));
    }
    
    /**
     * Returns a singleton instance of <code>ProductCatalog</code>
     * @return singleton instance of <code>ProductCatalog</code>
     */
    public static ProductCatalog getInstance () {
        return ProductCatalog.instance;
    }

    /**
     * Search for an item in the product catalog.
     *
     * @param    itemId The item to look for
     * @return          The specification for the found item.
     * @throws ProductNotFoundException If the product with supplied itemId was not found
     */
    public ProductSpecification findSpecification(int itemId) throws ProductNotFoundException {
            ProductSpecification searchResult = products.get(itemId);
	    if (searchResult == null) {
                throw new ProductNotFoundException(itemId);
            } else {
                return searchResult;
            }
    }
}
