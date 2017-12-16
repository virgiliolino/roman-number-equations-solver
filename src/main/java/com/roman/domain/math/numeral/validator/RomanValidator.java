package com.roman.domain.math.numeral.validator;

import com.roman.domain.math.numeral.Roman;
import com.roman.domain.math.numeral.UnrecognizedSymbolException;
import com.roman.domain.math.numeral.InvalidNumeralException;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Apply the different rules needed to verify that a Roman Number or romanLike one is correct
 */
public class RomanValidator {
    private RomanSymbolOccurrenceValidator occurrenceValidator;
    private RomanSymbolSubstractionValidator substractionValidator;

    public RomanValidator(
        RomanSymbolOccurrenceValidator romanSymbolOccurrenceValidator,
        RomanSymbolSubstractionValidator romanSymbolSubstractionValidator
    ) {
        this.occurrenceValidator = romanSymbolOccurrenceValidator;
        this.substractionValidator = romanSymbolSubstractionValidator;
    }

    /**
     * Will throw an exception if the number doesnt conform to the rules
     *
     * @param romanNumeral
     * @throws InvalidNumeralException
     * @throws UnrecognizedSymbolException
     */
    public void validate(Roman romanNumeral) throws InvalidNumeralException, UnrecognizedSymbolException {
        int occurrence = 1;
        String previousSymbol = "";
        String symbol = null;
        HashMap<String, Integer> overalOccurrences = new HashMap<String, Integer>();
        Iterator<String> iterator = romanNumeral.getSymbols().iterator();
        symbol = iterator.next();
        while (symbol != null) {
            occurrence = (previousSymbol.equals(symbol)) ? occurrence + 1 : 1;
            int overalOccurrence = overalOccurrences.containsKey(symbol) ? overalOccurrences.get(symbol) + 1 : 1;
            overalOccurrences.put(symbol, overalOccurrence);
            occurrenceValidator.validate(romanNumeral, symbol, overalOccurrence, occurrence);

            if (!iterator.hasNext()) {
                return;
            }

            String nextSymbol = iterator.next();
            substractionValidator.validate(romanNumeral, symbol, nextSymbol);
            previousSymbol = symbol;
            symbol = nextSymbol;
        }
    }
}
