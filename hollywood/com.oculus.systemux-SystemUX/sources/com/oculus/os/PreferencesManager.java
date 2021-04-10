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

    public synchronized boolean set(String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, double d) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, long j) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, float f) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Integer> getInteger(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Double> getDouble(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, String> getString(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Long> getLong(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Boolean> getBoolean(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Float> getFloat(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair<Boolean, Type> getType(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean setListener(PreferencesListener preferencesListener, String[] strArr) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean clearListener() {
        throw new RuntimeException("Stub!");
    }
}
