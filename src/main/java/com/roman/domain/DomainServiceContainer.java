package com.roman.domain;

import com.roman.application.RegistryServiceContainer;
import com.roman.core.gateway.Gateway;
import com.roman.core.gateway.KeyValueStoragStorageGateway;
import com.roman.domain.guide.GuideService;
import com.roman.domain.guide.resources.ResourcesDisabled;
import com.roman.domain.guide.resources.gateway.KeyValueStorageResourcesGateway;
import com.roman.domain.math.equation.FirstDegreeEquationSolver;
import com.roman.domain.math.numeral.ConverterService;
import com.roman.domain.math.numeral.RomanLike;
import com.roman.domain.math.numeral.converter.RomanLikeToArabic;
import com.roman.domain.math.numeral.converter.RomanLikeToRoman;
import com.roman.domain.math.numeral.converter.RomanToArabic;
import com.roman.domain.resources.Resources;
import com.roman.domain.symbols.Symbols;

/**
 * Created by virgiliolino on 02.08.16.
 */
public class DomainServiceContainer {
    private RegistryServiceContainer registry = new RegistryServiceContainer();

    public Symbols getSymbolsService() {
        String serviceName = Symbols.class.getName();
        if (!registry.isLoaded(serviceName)) {
            Gateway<String, Integer> gateway = new KeyValueStoragStorageGateway<String, Integer>();
            Symbols symbolsService = new Symbols(gateway);
            registry.loadService(serviceName, symbolsService);
            return symbolsService;
        }
        return (Symbols)registry.getService(serviceName);
    }

    public ResourcesDisabled getResourcesService() {
        String serviceName = Symbols.class.getName();
        if (!registry.isLoaded(serviceName)) {
            Gateway gateway = new KeyValueStoragStorageGateway<String, Integer>();
            Resources resourcesService = new Resources(gateway);
            registry.loadService(serviceName, resourcesService);
        }
        return (ResourcesDisabled)registry.getService(serviceName);
    }

    public ConverterService converterService() {
        String serviceName = ConverterService.class.getName();
        if (!registry.isLoaded(serviceName)) {
            RomanToArabic romanToArabic = new RomanToArabic();
            RomanLikeToRoman romanLikeToRoman = new RomanLikeToRoman();
            RomanLikeToArabic romanLikeToArabic = new RomanLikeToArabic(romanToArabic, romanLikeToRoman);
            ConverterService converterService = new ConverterService(new RegistryServiceContainer());
            converterService.addConverter(
                    ConverterService.TYPE_ROMAN, ConverterService.TYPE_ARABIC, romanToArabic
            );
            converterService.addConverter(
                    ConverterService.TYPE_ROMAN_LIKE, ConverterService.TYPE_ROMAN, romanLikeToRoman
            );
            converterService.addConverter(
                    ConverterService.TYPE_ROMAN_LIKE, ConverterService.TYPE_ARABIC, romanLikeToArabic
            );
            registry.loadService(serviceName, converterService);
        }
        return (ConverterService)registry.getService(serviceName);
    }

    public FirstDegreeEquationSolver firstDegreeEquationSolverService() {
        return new FirstDegreeEquationSolver();
    }
}
