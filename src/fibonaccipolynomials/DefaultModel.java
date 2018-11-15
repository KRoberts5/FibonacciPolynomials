/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonaccipolynomials;

import java.util.ArrayList;
import java.lang.Math;
import java.lang.Double;
import java.math.BigDecimal;
import java.util.HashMap;
public class DefaultModel extends AbstractModel{
    
    private Integer nthPolynomial;
    private Double value;
    private String fullFactorization;
    private ArrayList<Factor> factors;
    
    public DefaultModel(){
        nthPolynomial = 0;
        value = new Double(1);
        fullFactorization = "";
        factors = new ArrayList();
    }
    public void setNthPolynomial(Integer n){
        
        
        if(!n.equals(nthPolynomial)){
            
            nthPolynomial = n;
            value = new Double(1);
            fullFactorization = "";
            factors = new ArrayList();
            for(int i = 1; i <=n/2; ++i){
                Factor currentFactor = new Factor(i,n);
                fullFactorization += currentFactor.toString() + "  ";
                factors.add(currentFactor);
                value = value*currentFactor.getValue();
            }
            
        }
        
        long finalValue = value.longValue();
        String output = "F" + nthPolynomial + ": " + finalValue + "\n";
        output += "Full Factorization: " + fullFactorization;
            
        firePropertyChange(DefaultController.NTH_POLYNOMIAL,null,output);
        
    }
    public void setPartialFactorization(HashMap<String,Integer> values){
        
        String output = "";
        
        Integer n = values.get(DefaultController.N);
        Integer factor = values.get(DefaultController.FACTOR);
        
        boolean subsetExists = false;
        
        if(!n.equals(nthPolynomial)){
            nthPolynomial = n;
            value = new Double(1);
            fullFactorization = "";
            factors = new ArrayList();
            for(int i = 1; i <=n/2; ++i){
                Factor currentFactor = new Factor(i,n);
                fullFactorization += currentFactor.toString() + "  ";
                factors.add(currentFactor);
                value = value*currentFactor.getValue();
            }
        }
        
        long finalValue = value.longValue();
        output = "F" + nthPolynomial + ": " + finalValue + "\n";
        output += "Full Factorization: " + fullFactorization + "\n";
        
        
        int numFactors = factors.size();
        ArrayList<Factor> factorSubset = new ArrayList();
        long superSetMax = (long)Math.pow(2, numFactors);
        
        for(int i = 1; i < superSetMax; ++i){
            
            
            factorSubset = new ArrayList();
            Double currentValue = new Double(1);
            String subsetFactorization = "";
            String binary = Integer.toBinaryString(i);
            
            while(binary.length() < numFactors){
                binary = "0" + binary;
            }
            
            for(int j = 0; j <binary.length(); ++j){
                
                if(binary.charAt(j) == '1'){
                    
                    factorSubset.add(factors.get(j));
                    subsetFactorization += factors.get(j).toString() + "  ";
                    currentValue = currentValue * factors.get(j).getValue();
                    
                }
            }
            
            Integer subsetProduct = currentValue.intValue();
            Integer subsetProductMinusOne = currentValue.intValue() +1;
            
            
            if(subsetProduct.equals(factor) || subsetProductMinusOne.equals(factor)){
                
                subsetExists = true;
                output += "\n____________________________________\n";
                output += "Actual Value: " + currentValue + "\n";
                output += "Factorization: " + subsetFactorization;
            }
            
            
            
        }
        
        if(!subsetExists){
            output += "No Such Subset Exists";
        }
        
        firePropertyChange(DefaultController.PARTIAL_FACTORIZATION,null,output);
    }
}
