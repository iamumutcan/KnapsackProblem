
  
# Dynamic Programming

This repository contains a Java program that solves the knapsack problem using dynamic programming. The knapsack problem is a classic optimization problem in which you are given a set of items, each with a value and a weight, and a maximum weight capacity. The goal is to find the subset of items that has the maximum value and whose total weight does not exceed the maximum weight capacity.

The program in this repository uses a bottom-up approach to solve the knapsack problem. The algorithm works by first creating a table of all possible combinations of items and their weights. For each combination, the algorithm calculates the value of the combination and whether it is feasible (i.e., whether its total weight does not exceed the maximum weight capacity).

The algorithm then uses the table to find the optimal solution to the knapsack problem. The optimal solution is the combination of items that has the maximum value and whose total weight does not exceed the maximum weight capacity.

To run the program, simply compile and run the `DynamicProgramming.java` file. The program will read the input data from the `ks_4_0` file and print the optimal solution to the console.

## Input Data

The input data for the program is a file that contains the following information:

-   The number of items
-   The value of each item
-   The weight of each item
-   The maximum weight capacity

The input data file is formatted as follows:

# Number of items

4

# Value of each item

10 20 30 40

# Weight of each item

5 10 15 20

# Maximum weight capacity

30
