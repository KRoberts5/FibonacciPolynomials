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
import java.awt.*;
import javax.swing.*;
public class FibonacciPolynomials {
    
    public static void main(String[] args) {
        
        DefaultController c = new DefaultController();
        DefaultModel m = new DefaultModel();
        
        PrimaryView v = new PrimaryView(c);
        
        c.addModel(m);
        c.addView(v);
        
        EventQueue.invokeLater(() -> {
        
            JFrame window = new JFrame();
            
            window.add(v);

            window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setPreferredSize(new Dimension(1000,1000));
            window.pack();
            window.setVisible(true);
            
        });


    }
    
    
    
}
