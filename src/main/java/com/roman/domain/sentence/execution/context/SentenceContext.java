package com.roman.domain.sentence.execution.context;

import com.roman.core.MessageResponse;
import com.roman.domain.guide.GuideService;
import com.roman.domain.math.numeral.InvalidConverterException;
import com.roman.domain.math.numeral.SymbolInconsistencyException;
import com.roman.domain.math.numeral.UnrecognizedSymbolException;
import com.roman.domain.sentence.execution.ExecutionStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract logic for handling the Sentence Strategies
 */
abstract public class SentenceContext {
    protected List<ExecutionStrategy> strategies = new ArrayList<ExecutionStrategy>();

    public void addStrategy(ExecutionStrategy strategy) {
        strategies.add(strategy);
    }

    /**
     * Apply the Sentences into the guideService
     * @param sentence
     * @return message
     * @throws IOException
     * @throws UnrecognizedSymbolException
     */
    public MessageResponse process(String sentence)
            throws IOException, UnrecognizedSymbolException, InvalidConverterException, SymbolInconsistencyException {
        MessageResponse response = null;
        for (ExecutionStrategy strategy : strategies) {
            response = strategy.applyHandlerStrategy(sentence);
            if (response.handled()) {
                break;
            }
        }

        return response;
    }


}