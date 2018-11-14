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
import java.beans.PropertyChangeEvent;
import javax.swing.*;


public class PrimaryView extends JPanel implements AbstractView{
    
    private DefaultController controller;
    
    private JTextField input1;
    private JTextField input2;
    private JButton button;
    private JTextArea outputArea;
    private JScrollPane pane;
    
    
    public PrimaryView(DefaultController controller){
        super();
        
        this.controller = controller;
        
        initComponents();
    }
    
    private void initComponents(){
        
        this.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0,2));
        
        JLabel label1 = new JLabel("Nth Polynomial:");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        input1 = new JTextField();
        
        inputPanel.add(label1);
        inputPanel.add(input1);
        
        JLabel label2 = new JLabel("Factor:");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        input2 = new JTextField();
        
        inputPanel.add(label2);
        inputPanel.add(input2);
        
        this.add(inputPanel, BorderLayout.PAGE_START);
        
        
        JPanel buttonPanel = new JPanel();
        button = new JButton("Run");
        button.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent e) {
                        run();
                    }
                    
                });
        buttonPanel.add(button);
        
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        JPanel outputPanel = new JPanel();
        outputArea = new JTextArea("");
        outputArea.setEditable(false);
        outputArea.setMargin(new Insets(10,10,10,10));
        //outputArea.setPreferredSize(new Dimension(700,400));
        
        
       pane = new JScrollPane(outputArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.setPreferredSize(new Dimension(700,400));
        
        //pane.add(new JScrollBar());
        //pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //outputPanel.add(pane);
        
        this.add(pane,BorderLayout.CENTER);
    }
    
    public void run(){
        try{
            outputArea.setText("");
            String input = input1.getText();
            int n = Integer.parseInt(input);
            
            if(input2.getText().isEmpty()){
                controller.findNthPolynomial(n);
            }
            else{
                input = input2.getText();
                int factor = Integer.parseInt(input);
                controller.findPartialFactorization(n, factor);
            }
            
            
            
            
            
        }
        catch(Exception e){
            System.err.println(e.toString());
            
            outputArea.setText("Invalid Input");
        }
    }
    
    
    public void modelPropertyChange(PropertyChangeEvent e){
        
        
        if(e.getPropertyName().equals(DefaultController.NTH_POLYNOMIAL)){
            String output = (String)e.getNewValue();
            outputArea.setText(output);
            
        }
        
        else if(e.getPropertyName().equals(DefaultController.PARTIAL_FACTORIZATION)){
            String output = (String)e.getNewValue();
            outputArea.setText(output);
        }
        
    }
}
