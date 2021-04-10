package com.oculus.appsafety.signals;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

public class SafetySignalCollectorConfigurationRequest {
    public final String version;

    public SafetySignalCollectorConfigurationRequest(String version2) {
        this.version = version2;
    }

    public String toJson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create().toJson(this);
    }
}
