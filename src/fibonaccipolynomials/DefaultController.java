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
    public static final String DESIRED_PRODUCT = "DesiredProduct";
    public static final String N = "N";
    public static final String ERROR_MARGIN = "ErrorMargin";
    
    
    public void findNthPolynomial(int n){
        
        setModelProperty(NTH_POLYNOMIAL, n);
    }
    
    public void findPartialFactorization(int n, int desiredProduct, int errorMargin){
        
        HashMap<String,Integer> values = new HashMap();
        values.put(N, n);
        values.put(DESIRED_PRODUCT, desiredProduct);
        values.put(ERROR_MARGIN, errorMargin);
        
        setModelProperty(PARTIAL_FACTORIZATION,values);
        
        
    }
    
}
