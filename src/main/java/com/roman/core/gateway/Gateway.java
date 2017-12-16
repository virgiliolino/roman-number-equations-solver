package com.roman.core.gateway;

public interface Gateway<K, V> {
    void setValue(K key, V value);

    V getValue(K key);
}
