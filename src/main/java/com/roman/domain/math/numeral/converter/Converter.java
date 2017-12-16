package com.roman.domain.math.numeral.converter;

import com.roman.domain.math.numeral.InvalidConverterException;
import com.roman.domain.math.numeral.Numeral;
import com.roman.domain.math.numeral.UnrecognizedSymbolException;

public interface Converter {
    public Numeral convert(Numeral origin) throws UnrecognizedSymbolException, InvalidConverterException;
}
