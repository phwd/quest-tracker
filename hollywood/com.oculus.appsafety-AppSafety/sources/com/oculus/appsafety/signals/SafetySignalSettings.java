package com.oculus.appsafety.signals;

import com.google.gson.JsonObject;

public class SafetySignalSettings {
    public final String name;
    public final JsonObject properties;

    public SafetySignalSettings(String name2, JsonObject properties2) {
        this.name = name2;
        this.properties = properties2;
    }
}
