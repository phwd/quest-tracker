package com.facebook.mobileconfigservice.client_base;

import com.facebook.debug.log.BLog;
import java.util.HashMap;

public class MobileConfigClientDataLogger {
    public static final String API_RESPONSE_EVENT_NAME = "mobileconfig_service_client_data_logger";
    public static final String EXTRA_FIELD = "extra";
    public static final String FUNCTION_NAME_FIELD = "function_name";
    public static final String PARAMS_FIELD = "params";
    public static final String SUCCESS_FIELD = "success";
    private static final String TAG = "MobileConfigClientDataLogger";
    private MobileConfigMarauderLogger mLogger = null;

    public void setMarauderLogger(MobileConfigMarauderLogger mobileConfigMarauderLogger) {
        this.mLogger = mobileConfigMarauderLogger;
    }

    public void logApiResponse(String str, String str2, boolean z, String str3) {
        BLog.d(TAG, "Function name: %s params %s success %s extra %s", str, str2, Boolean.valueOf(z), str3);
        if (this.mLogger != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(FUNCTION_NAME_FIELD, str);
            hashMap.put(PARAMS_FIELD, str2);
            hashMap.put("success", Boolean.toString(z));
            hashMap.put(EXTRA_FIELD, str3);
            this.mLogger.logEvent(API_RESPONSE_EVENT_NAME, hashMap);
        }
    }
}
