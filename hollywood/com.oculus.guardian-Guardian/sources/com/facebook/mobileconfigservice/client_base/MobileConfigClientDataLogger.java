package com.facebook.mobileconfigservice.client_base;

import com.facebook.debug.log.BLog;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigClientDataLogger {
    public static final String API_RESPONSE_EVENT_NAME = "mobileconfig_service_client_data_logger";
    public static final String EXTRA_FIELD = "extra";
    public static final String FUNCTION_NAME_FIELD = "function_name";
    public static final String PARAMS_FIELD = "params";
    public static final String SUCCESS_FIELD = "success";
    private static final String TAG = "MobileConfigClientDataLogger";
    private MobileConfigMarauderLogger mLogger = null;

    public void setMarauderLogger(MobileConfigMarauderLogger logger) {
        this.mLogger = logger;
    }

    public void logApiResponse(String functionName, String params, boolean success, String extra) {
        BLog.d(TAG, "Function name: %s params %s success %s extra %s", functionName, params, Boolean.valueOf(success), extra);
        if (this.mLogger != null) {
            Map<String, String> eventData = new HashMap<>();
            eventData.put(FUNCTION_NAME_FIELD, functionName);
            eventData.put(PARAMS_FIELD, params);
            eventData.put("success", Boolean.toString(success));
            eventData.put(EXTRA_FIELD, extra);
            this.mLogger.logEvent(API_RESPONSE_EVENT_NAME, eventData);
        }
    }
}
