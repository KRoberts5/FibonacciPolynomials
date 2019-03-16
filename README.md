# FibonacciPolynomials

This project was designed to help a Jacksonville State University Professor gather data for a research project.
The objective was to create a program that could generate the trigonometric factors of any given fibonacci number.
Once the factors are computed, we can view the products of all the subsets of factors.
This information is valuable because it indicates if coupling exists.

# Math Overview

To better illustrate this program's functionality, I will briefly discuss the supporting math. When computing a given Fibonacci number, it is possible to generate this number non-recursively by finding its trigonometric factors. The number of factors for each given Fibonacci number (n) is found by dividing n by 2 and rounding down. For instance, F5 has 2 factors, while F6 and F7 have 3 factors. The factors of each Fibonacci number follow a specific pattern:

### Examples

1. F5 = 5  = (1 + 4 * (COS (1 * pi / 5))^2)  (1 + 4 * (COS (2 * pi / 5))^2)  
2. F6 = 8  = (1 + 4 * (COS (1 * pi / 6))^2)  (1 + 4 * (COS (2 * pi / 6))^2)  (1 + 4 * (COS (3 * pi / 6))^2)
3. F7 = 13 = (1 + 4 * (COS (1 * pi / 7))^2)  (1 + 4 * (COS (2 * pi / 7))^2)  (1 + 4 * (COS (3 * pi / 7))^2)

As you can see, the only variation between factors exists in the argument for cosine. The denominator for all the cosine arguments of a given Fibonacci number will always be the Fibonacci number itself. For instance, all the denominators for F7 are 7. The numerator is always pi multiplied by an integer starting at 1 and incrementing for each factor. For instance, F7 has the numerators 1, 2, and 3. F8 and F9 have the numerators 1, 2, 3, and 4.  

# Project Classes

## FibonacciPolynomials

A basic class that contains the main function.
Its purpose is to instantiate the model, view, and controller objects.

## Default Model

This class provides the primary computation. The state of the object is represented by the following variables:

1. nthPolynomial - The value that controls which particular Fibonacci number is requested.
2. value - The actual value of the given Fibonacci number.
3. fullFactorization - A string that represents the complete trigonmetric factorization
4. factors - An array list that contains all the factor objects for the given Fibonacci number.

### generateFactors()

This method is invoked by setNthPolynomial() and setPartialFactorization(). The method accepts an integer, n, which represents which Fibonacci number is requested. The trigonometric factors are then generated.
As each factor is generated, its real value is multiplied to the product of all previous factors. A string is generated to represent all desired data  (the Fibonacci number, the number's value, and the full list of factors).

### setNthPolynomial()

This method is called whenever the user only provides input for which Fibonacci number they want.
The method calls generateFactors() with an integer value retrieved from input. The string, received from generateFactors(), is sent to the controller so that a view class can output the data.

### setPartialFactorization()

This method is called whenever the user provides an input for both Nth Polynomial and Factor.
The nth polynomial represents which Fibonacci number the user would like to generate. Desired product represents the value to search for when generating the products of subsets.
To start, this method performs the same steps as setNthPolynomial.
Once the full factorization has been generated, the program generates all subsets of the factors and their products.

To generate every subset, I utilize a binary string to represent which factors are present in a given subset. For example, 
if we were computing the 7th Fibonacci number, we would have 3 trigonometric factors. Since each factor can either be included or 
excluded, the total number of subsets is 2^3 = 8. If we represent each factor as a binary digit, we can visually determine which 
factors are included in our subset. For instance, 110 in this example would include the first and second factors. To determine the 
total product of a subset, I simply iterate through the binary string. If there is a 1 in the current position, I multiply the
factor's value to the current product value of the subset. If the current position contains a 0, I ignore it. 

Once the product has been generated, I compare it to the desired product value. If the product falls within a specified range of the desired product value, we append that subsets data to the final output. If the product falls outside the range, we ignore it. It should be noted that the range is set to 1 by default. If the user specifies a value for "Margin of Error," this value will be used to determine the range.

Finally, the output string, containing the data for all desired subsets, is sent to the controller so that a view can handle it.

## Factor

This class holds all the desired information about an individual trigonometric factor. It contains two fields: a real value representation and a string value representation. The real value is used for actual computations. The string value is used for visualization. 

### Constructor

The contstructor accepts two integers (current and n). Current is utilized to generate the numerator for the cosine argument. The value of n is utilized as the denominator for the cosine argument. 

### setString()

Creates the string representation of the factor.

### setValue()

Uses the libraries from java.lang.Math to generate the real value of the factor. "Theta" in this method is the cosine argument.

### Accessor Classes

I provide two accessor classes, toString() and getValue(), so that the information can be used in DefaultModel. 

## Default Controller

This class allows for communication between the model and view classes. At the top of the source code file, I have included several string constants. These constants are used so that I can package all the input values into a hashmap before I send them to the Default Model. Once received, the Default Model can use the strings to retrieve the values from the hashmap.

### findNthPolynomial()

Called by the view whenever a user only inputs a value for "Nth Polynomial." The controller then calls the Default Model's setNthPolynomial() method with the argument value of n.

### findPartialFactorization()

This method is called by the view whenever a user inputs values for both "Nth Polynomial" and "Desired Product." The controller then packs three values ( n, desired product, and margin of error) into a hashmap and calls the Default Model's findPatialFactorization() method.

## Primary View

This is the primary view class for the program. It contains three input fields, one large output field, and a button for submission. 

### run()

This method is called whenever a user clicks the "Run" button. It first clears the output field. Next, it evaluates which values it has been given. It always expects a value for "Nth Polynomial." If no value is given for "Margin of Error," the margin is set to one by default. Finally, it checks if there is a value in "Desired Product." In the case that this field contains a value, the value is retrieved and setPartialFactorization() is requested. Otherwise, this value is ignored and findNthPolynomial() is requested. In any case that invalid input is retrieved, the catch block simply writes "Invalid Input" into the outputfield. 

### modelPropertyChange()

This method catches any property changes that occur by evaluating the property change event name through a series of if statements. If the name matches the string in the if statement, that block of code will handle the event.

## AbstractController

This is an abstract class that acts as the super class of the DefaultController class.
The constructor instantiates two array lists for the potential models and views.

### addModel()

Accepts an AbstractModel object and adds it to the array list of models. Once added, the controller registers
itself as the property change listener of the model object.

### removeModel()

Accepts an AbstractModel object and removes it from the array list of models. Once removed, the controller un-registers 
itself as the property change listener of the model object.

### addView()

Accepts an AbstractView object and adds it to the array list of views.

### removewView()

Accepts an AbstractView object and removes it from the array list of views.

### propertyChange()

Called automatically when the model fires a property change.
When called, the function iterates through all the views in the array list of views and gives them the current PropertyChangeEvent
Object. If the event applies to a particular view, that view will handle the event and make the appropriate changes.

### setModelProperty()

This method is called by an AbstractController subclass when the View informs it of a user interaction which requires
a change to a Model. It utilizes the property name given to identify which of the registered models have a corresponding method.
The controller then invokes the method so that the model can be updated properly.

## AbstractModel

A class that acts as a super class to any model classes.
This class provides facilities to instantiated models to allow communication with the controller.

### addPropertyChangeListener()

Registers a given property change listener to the current model.

### removePropertyChangeListener()

Un-registers a given property change listener that the model currently has.

### firePropertyChange()

Called whenever an instantiated model has a property change.
The call of this function will result in the call of propertyChange() located in the AbstractController.

## AbstractView

An interface that is implemented by the non-abstract view classes.
This interface creates an abstract method, modelPropertyChange(), that non-abstract views must implement to 
receive and handle property change events.
