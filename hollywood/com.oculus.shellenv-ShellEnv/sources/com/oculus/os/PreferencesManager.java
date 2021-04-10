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

    public synchronized boolean clearListener() {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair getBoolean(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair getDouble(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair getFloat(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair getInteger(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair getLong(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair getString(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized Pair getType(String str) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, double d) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, float f) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, int i) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, long j) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean set(String str, boolean z) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean setListener(PreferencesListener preferencesListener, String[] strArr) {
        throw new RuntimeException("Stub!");
    }
}
