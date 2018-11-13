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

import java.lang.Math;

public class Factor {
    
    private String representation;
    private double value;
    
    public Factor(int current, int n){
        this.setString(current,n);
        this.setValue(current,n);
    }
    
    private void setString(int current,int n){
        representation = "(1 + 4*(COS(" + current + "*pi/" + n + "))^2)";
    }
    private void setValue(int current,int n){
        double theta = (current*Math.PI)/n;
        double cosine = Math.cos(theta);
        
        value = 1 + 4*(Math.pow(cosine, 2));
    }
    
    public String toString(){
        return representation;
    }
    public double getValue(){
        return value;
    }
}
