/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonaccipolynomials;

/**
 *
 * @author Brendan
 */
import java.util.ArrayList;
import java.lang.Math;
import java.lang.Double;
public class FibonacciPolynomials {
    
    public static void main(String[] args) {
        
        int n = 19;
        double value = 1;
        
        ArrayList<Factor> factors = new ArrayList<>();
        
        for(int i = 1; i <=n/2; ++i){
            //System.out.println("________");
            Factor currentFactor = new Factor(i,n);
            
            factors.add(currentFactor);
            
            //System.out.println(currentFactor);
            //System.out.println(currentFactor.getValue());

            value = value*currentFactor.getValue();
        }
        
        //System.out.println(value);
        
        int numFactors = factors.size();
        //boolean primeFactorization = false;
        ArrayList<Factor> primeFactors;
        int prime = 37;
        
        long superSetMax = (int)Math.pow(2, numFactors);
        
        for(int i = 1; i <= superSetMax; ++i){
            
            //System.out.println("____");
            primeFactors = new ArrayList();
            Double currentValue = new Double(1);
            String binary = Integer.toBinaryString(i);
            //System.out.println(binary);
            for(int j = 0; j <binary.length(); ++j){
                
                if(binary.charAt(j) == '1'){
                    primeFactors.add(factors.get(j));
                    currentValue = currentValue * factors.get(j).getValue();
                    
                }
            }
            
            long finalValue = currentValue.intValue();
            //System.out.println(currentValue);
            
            if(finalValue == prime){
                System.out.println("_________");
                System.out.println(currentValue);
                System.out.println(finalValue);
                for(Factor f : primeFactors){
                    System.out.println(f);
                }
            }

            
        }


    }
    
    
    
}
