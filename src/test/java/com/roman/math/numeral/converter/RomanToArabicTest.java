package com.roman.math.numeral.converter;

import com.roman.domain.math.numeral.Arabic;
import com.roman.domain.math.numeral.InvalidNumeralException;
import com.roman.domain.math.numeral.Roman;
import com.roman.domain.math.numeral.UnrecognizedSymbolException;
import com.roman.domain.math.numeral.converter.RomanToArabic;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RomanToArabicTest extends TestCase {
    private ArrayList<String> romanSymbol;
    private int arabicValue;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
            {"III", 3}, {"IV", 4}, {"III", 3}, {"MCMXLIV", 1944}, {"MCI", 1101},
            //as you may notice the next test is valid even if the business logic doesn't allow this combination
            //validation is being explicitely called whenever some class sets it. We could handle the validation
            //when setting the value. It's something to be decided
            {"IIII", 4},
        };

        return Arrays.asList(data);
    }

    public RomanToArabicTest(String textualRepresentation, int arabicValue) {
        this.romanSymbol = stringToList(textualRepresentation);
        this.arabicValue = arabicValue;
    }

    private ArrayList<String> stringToList(String textualRepresentation) {
        ArrayList<String> listRepresentation = new ArrayList<String>();
        for (char letter: textualRepresentation.toCharArray()) {
            listRepresentation.add(Character.toString(letter));
        }
        return listRepresentation;
    }

    @Test
    public void testData() throws InvalidNumeralException, UnrecognizedSymbolException {
        Roman roman = new Roman();
        roman.setSymbols(romanSymbol);
        RomanToArabic converter = new RomanToArabic();
        Arabic arabic = (Arabic) converter.convert(roman);
        assertEquals(arabic.getValue().intValue(), this.arabicValue);
    }
}
