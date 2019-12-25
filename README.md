# Simple-Linear-Regression
An implementation of simple linear regression in Java.

Regression analysis is commonly used for modeling the relationship between a single dependent variable Y and one or more predictors. When we have one predictor, we call this "simple" linear regression. The expected value of Y is a straight-line function of X. The betas are selected by choosing the line that minimizes the squared distance between each Y value and the line of best fit. The betas are chosen such that they minimize this expression:
 
∑i (yi – (β0 + β1X))^2

There are four assumptions associated with a linear regression model:

     Linearity: The relationship between X and the mean of Y is linear.
     Homoscedasticity: The variance of the residual is the same for any value of X.
     Independence: Observations are independent of each other.
     Normality: For any fixed value of X, Y is normally distributed.

 Other resources:
 1. http://sphweb.bumc.bu.edu/otlt/MPH-Modules/BS/R/R5_Correlation-Regression/R5_Correlation-Regression4.html
 2. https://towardsdatascience.com/linear-regression-detailed-view-ea73175f6e86

NOTE: The CSVParser class is used to import data from a CSV file and remove any Byte Order Mark (BOM) characters prepended to the data. There are other libraries out there that can do this a little easier, but as this project is mostly for practice, I wanted to implement this myself. 
