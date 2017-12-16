package com.roman.application;

import com.roman.application.batch.FileImportController;
import com.roman.core.Request;
import com.roman.core.TerminalView;
import com.roman.domain.math.numeral.InvalidConverterException;
import com.roman.domain.math.numeral.SymbolInconsistencyException;
import com.roman.domain.math.numeral.UnrecognizedSymbolException;

import java.io.IOException;

/**
 * Forward the request to the right controller
 */
public class FrontDispatcher {
    private ApplicationServiceContainer applicationServiceContainer;

    public FrontDispatcher(ApplicationServiceContainer applicationServiceContainer) {
        this.applicationServiceContainer = applicationServiceContainer;
    }

    public void dispatch(Request request)
            throws IOException, InvalidArgumentException, UnrecognizedSymbolException,
            InvalidConverterException, SymbolInconsistencyException {
        if (!request.getParams().containsKey("-operation")) {
            throw new InvalidArgumentException(
                    "peration not specified usage: -operation importFromFile -file filename"
            );
        }
        String operation = request.getParams().get("-operation");
        if (!operation.equals("importFromFile")) {
            throw new InvalidArgumentException(
                    "Operation not specified usage: -operation importFromFile -file filename"
            );
        }
        FileImportController controller = new FileImportController(
                applicationServiceContainer.domainServiceContainer(),
                applicationServiceContainer.batchRequestsImporterService()
        );
        controller.handleRequest(request, new TerminalView());
    }



}
