package com.roman.domain.sentence.execution.strategy;

import com.roman.core.MessageResponse;
import com.roman.domain.DomainServiceContainer;
import com.roman.domain.guide.GuideService;
import com.roman.domain.math.equation.FirstDegreeEquation;
import com.roman.domain.math.numeral.*;
import com.roman.domain.sentence.ExpressionMatcher;
import com.roman.domain.sentence.execution.ExecutionStrategy;
import com.roman.domain.symbols.Symbols;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Handles Sentences like "X I Apples is 34 Credits"
 * Expectes a conversion and calculation of a FirstDegreeEquation
 */
final public class Equivalence extends ExecutionStrategy {
    public Equivalence(
            DomainServiceContainer domainServiceContainer,
            ExpressionMatcher expressionMatcher
    ) {
        super(domainServiceContainer, expressionMatcher);
    }

    public MessageResponse applyHandlerStrategy(String sentence)
            throws IOException, UnrecognizedSymbolException, InvalidConverterException {
        Matcher sentenceMatcher =  expressionMatcher.match(sentence);
        if (sentenceMatcher.matches()) {
            List<String> romanSymbols = new ArrayList<String>();
            Roman roman = new Roman();
            Integer i = 1;
            Symbols symbos = domainServiceContainer.getSymbolsService();
            while (i < sentenceMatcher.groupCount() && !sentenceMatcher.group(i + 1).equals("is")) {
                String romanLikeSymbol = sentenceMatcher.group(i).trim();
                Integer romanLikeSymbolValue = symbos.getValue(romanLikeSymbol);
                romanSymbols.add(roman.getRomanSymbolFromValue(romanLikeSymbolValue));
                i++;
            }
            String variable = sentenceMatcher.group(i);
            Integer value = Integer.parseInt(sentenceMatcher.group(i + 2));//Double.parseDouble(sentenceMatcher.group(i + 2));
            roman.setSymbols(romanSymbols);
            //Arabic arabic = (Arabic)romanLikeToArabic.convert(romanLike, romanLikeToRoman);
            Arabic arabic = (Arabic)domainServiceContainer.converterService().convert(
                    roman, ConverterService.TYPE_ARABIC
            );
            FirstDegreeEquation equation = new FirstDegreeEquation(arabic.getValue(), variable, value);
            Double resourceNameValue = domainServiceContainer.firstDegreeEquationSolverService().solve(equation);
            domainServiceContainer.getResourcesService().set(variable, resourceNameValue);
        }
        return new MessageResponse("", sentenceMatcher.matches(), sentenceMatcher.matches());
    }
}
