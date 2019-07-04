package com.ignoubadhega.studycentremanager.utils;

public class Pair<K, V> {
    
    private K id;
    private V value;
    
    public Pair(K id, V value) {
        this.id = id;
        this.value = value;
    }

    public K getId() {
        return id;
    }

    public V getValue() {
        return value;
    }
    
}
