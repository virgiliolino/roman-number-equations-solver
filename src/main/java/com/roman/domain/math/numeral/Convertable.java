package com.roman.domain.math.numeral;

import com.roman.domain.math.numeral.Numeral;

public interface Convertable {
    public Numeral convert(Numeral origin, String to) throws UnrecognizedSymbolException, InvalidConverterException;
}
