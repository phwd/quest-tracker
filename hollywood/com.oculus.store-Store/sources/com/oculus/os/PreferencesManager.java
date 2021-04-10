package com.oculus.os;

import android.util.Pair;

public class PreferencesManager {

    public interface PreferencesListener {
        void onChanged(String[] strArr);

        void onConnectionEstablished();

        void onConnectionLost();
    }

    public enum Type {
        INTEGER,
        DOUBLE,
        STRING,
        LONG,
        BOOLEAN,
        FLOAT,
        UNKNOWN
    }

    public PreferencesManager() {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String key, int value) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String key, double value) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String key, String value) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String key, long value) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String key, boolean value) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String key, float value) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Integer> getInteger(String key) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Double> getDouble(String key) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, String> getString(String key) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Long> getLong(String key) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Boolean> getBoolean(String key) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Float> getFloat(String key) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Type> getType(String key) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean setListener(PreferencesListener listener, String[] keys) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean clearListener() {
        throw new RuntimeException("Stub!");
    }
}
