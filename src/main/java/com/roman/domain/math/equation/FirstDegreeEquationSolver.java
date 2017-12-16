package com.roman.domain.math.equation;

public class FirstDegreeEquationSolver {
    /**
     * Received a first degree equation in input and returns the value of the variable
     * @param equation
     * @return
     */
    public double solve(FirstDegreeEquation equation) {
        return  equation.getValue() / equation.getCoefficient();
    }

}
