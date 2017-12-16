package com.roman.domain.math.numeral.validator;

import com.roman.domain.math.numeral.InvalidNumeralException;
import com.roman.domain.math.numeral.Roman;
import com.roman.domain.math.numeral.UnrecognizedSymbolException;

/**
 * 1. "I" can be subtracted from "V" and "X" only.
 * 2. "X" can be subtracted from "L" and "C" only.
 * 3. "C" can be subtracted from "D" and "M" only.
 * 4. "V", "L", "D" can never be subtracted.
 */
public class RomanSymbolSubstractionValidator {

    /**
     * @param roman Value Object containing the representation of the roman numeral
     * @param symbol The current symbol being validated
     * @param nextSymbol The symbol on the next position
     * @throws UnrecognizedSymbolException when requesting the value for a non existing symbol
     * @throws InvalidNumeralException In rules 1..4 this exception will be thrown
     */
    public void validate(Roman roman, String symbol, String nextSymbol)
            throws UnrecognizedSymbolException, InvalidNumeralException
    {
        int numericalValue = roman.getRomanSymbolValue(symbol);
        int nextSymbolNumericalValue = roman.getRomanSymbolValue(nextSymbol);

        if (numericalValue > nextSymbolNumericalValue) {
            return;
        }

        boolean isNumberIXorC = numericalValue % 10 < 2;
        if (isNumberIXorC && (nextSymbolNumericalValue / numericalValue) > 10) {
            throw new InvalidNumeralException(symbol + " can't be substracted from " + nextSymbol);
        }

        if (!isNumberIXorC) {
            throw new InvalidNumeralException(symbol + " can never be substracted");
        }
    }
}
