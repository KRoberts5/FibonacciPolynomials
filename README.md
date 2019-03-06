# FibonacciPolynomials

This project was designed to help a Jacksonville State University Professor gather data for a research project.
The objective was to create a program that could computate the trigonometric factors of any given fibonacci number.
Once the factors are computed, we can view the products of all the subsets of factors.
This information is valuable because it indicates if coupling exists.

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

### generateFactors

Accepts an integer, n, which represents which Fibonacci number is requested. The trigonometric factors are then generated.
As each factor is generated, it is multiplied to the product of all previous factors. A string is generated to represent the 
Fibonacci number, the number's value, and the full list of factors.

### setNthPolynomial

Calls generateFactors() with an integer value retrieved from input. The string is sent to the controller so that a view class can
output the data.

### setPartialFactorization

Performs the same steps as setNthPolynomial.
Once the full factorization has been generated, the program generates all subsets of the factors and their appropriate values.

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
