package com.roman.application;


import com.roman.application.batch.BatchRequestsImporter;
import com.roman.domain.DomainServiceContainer;

public class ApplicationServiceContainer {
    private RegistryServiceContainer registry = new RegistryServiceContainer();

    public BatchRequestsImporter batchRequestsImporterService() {
        String serviceName = BatchRequestsImporter.class.getName();
        if (!registry.isLoaded(serviceName)) {
            BatchRequestsImporter batchRequestsImporter = new BatchRequestsImporter();
            registry.loadService(serviceName, batchRequestsImporter);
            return batchRequestsImporter;
        }

        return (BatchRequestsImporter)registry.getService(serviceName);
    }

    public DomainServiceContainer domainServiceContainer() {
        String serviceName = DomainServiceContainer.class.getName();
        if (!registry.isLoaded(serviceName)) {
            DomainServiceContainer domainServiceContainer = new DomainServiceContainer();
            registry.loadService(serviceName, domainServiceContainer);
            return domainServiceContainer;
        }

        return (DomainServiceContainer)registry.getService(serviceName);
    }

}
