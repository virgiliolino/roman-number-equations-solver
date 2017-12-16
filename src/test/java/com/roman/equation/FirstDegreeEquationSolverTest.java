package com.roman.equation;

import com.roman.domain.math.equation.FirstDegreeEquation;
import com.roman.domain.math.equation.FirstDegreeEquationSolver;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FirstDegreeEquationSolverTest extends TestCase {
    private int coefficient;
    private double result;
    private double expected;

    public FirstDegreeEquationSolverTest(int coefficient, double result, double expected) {
        this.coefficient = coefficient;
        this.result = result;
        this.expected = expected;
    }
    
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                //2x = 5
                {2 , 5, 2.5},
                //4x = 6
                {4, 6, 1.5},
                //6, 2, 3
                {3, 6, 2}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testCalculationOfVariable() {
        FirstDegreeEquationSolver firstDegreeEquationSolver = new FirstDegreeEquationSolver();
        FirstDegreeEquation firstDegreeEquation = new FirstDegreeEquation(coefficient, "x", result);
        assertEquals("Result", expected, firstDegreeEquationSolver.solve(firstDegreeEquation));
    }
}