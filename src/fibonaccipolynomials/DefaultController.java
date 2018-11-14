/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonaccipolynomials;

import java.util.HashMap;
public class DefaultController extends AbstractController{
    public static final String NTH_POLYNOMIAL = "NthPolynomial";
    public static final String PARTIAL_FACTORIZATION = "PartialFactorization";
    public static final String FACTOR = "Factor";
    public static final String N = "N";
    
    
    public void findNthPolynomial(int n){
        
        setModelProperty(NTH_POLYNOMIAL, n);
    }
    
    public void findPartialFactorization(int n, int factor){
        
        HashMap<String,Integer> values = new HashMap();
        values.put(N, n);
        values.put(FACTOR, factor);
        
        setModelProperty(PARTIAL_FACTORIZATION,values);
        
        
    }
    
}
