package com.oculus.os;

import android.os.Handler;

public class SettingsManager {
    public SettingsManager() {
        throw new RuntimeException("Stub!");
    }

    public boolean setBoolean(String name, boolean value) {
        throw new RuntimeException("Stub!");
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDouble(String name, double value) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(String name, double defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public boolean setLong(String name, long value) {
        throw new RuntimeException("Stub!");
    }

    public long getLong(String name, long defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public boolean setFloat(String name, float value) {
        throw new RuntimeException("Stub!");
    }

    public float getFloat(String name, float defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public boolean setInt(String name, int value) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(String name, int defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public boolean setString(String name, String value) {
        throw new RuntimeException("Stub!");
    }

    public String getString(String name, String defaultValue) {
        throw new RuntimeException("Stub!");
    }

    public synchronized boolean registerSettingsObserver(String settingName, SettingsObserverCallback observer, Handler handler) {
        throw new RuntimeException("Stub!");
    }

    public synchronized void unregisterSettingsObserver(String settingName, SettingsObserverCallback observer) {
        throw new RuntimeException("Stub!");
    }
}
