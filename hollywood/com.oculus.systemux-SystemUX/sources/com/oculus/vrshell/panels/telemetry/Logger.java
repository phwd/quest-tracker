package com.oculus.vrshell.panels.telemetry;

import java.util.Map;

public interface Logger {
    void rawLogEvent(String str, Map<String, String> map);

    void rawLogEvent(String str, String... strArr);

    void rawLogJsonEvent(String str, String str2);
}
