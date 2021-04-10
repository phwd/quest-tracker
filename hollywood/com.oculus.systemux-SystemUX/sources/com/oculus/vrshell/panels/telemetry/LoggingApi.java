package com.oculus.vrshell.panels.telemetry;

import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.vrshell.panels.AndroidPanelApp;
import java.util.Map;

public class LoggingApi implements Logger {
    private AndroidPanelApp mPanelApp;
    private UnifiedTelemetryLogger mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(this.mPanelApp.getContext());

    public LoggingApi(AndroidPanelApp androidPanelApp) {
        this.mPanelApp = androidPanelApp;
    }

    @Override // com.oculus.vrshell.panels.telemetry.Logger
    public void rawLogEvent(String str, String... strArr) {
        if (strArr.length % 2 == 0) {
            AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
            for (int i = 0; i < strArr.length; i += 2) {
                String str2 = strArr[i];
                String str3 = strArr[i + 1];
                if (str3 != null && !str3.isEmpty()) {
                    analyticsEvent.setExtra(str2, str3);
                }
            }
            this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
            return;
        }
        throw new IllegalArgumentException("Invalid count of pairs in call to rawLogEvent.");
    }

    @Override // com.oculus.vrshell.panels.telemetry.Logger
    public void rawLogEvent(String str, Map<String, String> map) {
        AnalyticsEvent analyticsEvent = new AnalyticsEvent(str);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null && !value.isEmpty()) {
                analyticsEvent.setExtra(key, value);
            }
        }
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    @Override // com.oculus.vrshell.panels.telemetry.Logger
    public void rawLogJsonEvent(String str, String str2) {
        this.mPanelApp.getCommandChannel().sendCommand(String.format("telemetry %s 1 1 %s", str, str2));
    }
}
