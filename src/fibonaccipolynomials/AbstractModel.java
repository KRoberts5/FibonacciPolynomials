/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonaccipolynomials;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel
{
 
    protected PropertyChangeSupport propertyChangeSupport;
    
    /*
     * These methods add and remove an AbstractController subclass as a
     * a PropertyChangeListener, and ensure that changes to the Model are
     * announced to the controller in the form of a PropertyChange event.
     */
 
    public AbstractModel()
    {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
 
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
 
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
 
}