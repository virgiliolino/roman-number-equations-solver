package com.roman.application;

import com.roman.core.KeyValueStorage;

public class RegistryServiceContainer {
    private KeyValueStorage<String, Object>storage = new KeyValueStorage<String, Object>();

    public boolean isLoaded(String serviceName) {
        return storage.containsKey(serviceName);
    }

    public Object getService(String serviceName) {
        return storage.get(serviceName);
    }

    public void loadService(String serviceName, Object service) {
        storage.set(serviceName, service);
    }

}
