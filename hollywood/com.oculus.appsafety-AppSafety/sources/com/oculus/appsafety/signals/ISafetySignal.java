package com.oculus.appsafety.signals;

import com.google.gson.JsonObject;

public interface ISafetySignal {
    JsonObject collect();

    String getTelemetryKey();
}
