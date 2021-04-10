package com.facebook.mobileconfigservice.client_base;

import com.facebook.debug.log.BLog;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigClientDataLogger {
    private MobileConfigMarauderLogger mLogger = null;

    public void setMarauderLogger(MobileConfigMarauderLogger logger) {
        this.mLogger = logger;
    }

    public void logApiResponse(String functionName, String params, boolean success, String extra) {
        BLog.d("MobileConfigClientDataLogger", "Function name: %s params %s success %s extra %s", functionName, params, Boolean.valueOf(success), extra);
        if (this.mLogger != null) {
            Map<String, String> eventData = new HashMap<>();
            eventData.put("function_name", functionName);
            eventData.put("params", params);
            eventData.put("success", Boolean.toString(success));
            eventData.put("extra", extra);
            this.mLogger.logEvent("mobileconfig_service_client_data_logger", eventData);
        }
    }
}
