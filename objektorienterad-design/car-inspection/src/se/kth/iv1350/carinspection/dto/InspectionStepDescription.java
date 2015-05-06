/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.dto;

/**
 *
 * @author jonathanazulay
 */
public class InspectionStepDescription {
    private final String description;
    
    /**
     * Creates a description for a inspection step
     * @param description 
     */
    public InspectionStepDescription(String description) {
        this.description = description;
    }
    
    /**
     * Description getter
     * @return description
     */
    public String getDescription () {
        return this.description;
    }
}
