package com.facebook.mobileconfigservice.client_base;

import com.facebook.debug.log.BLog;
import java.util.HashMap;

public final class MobileConfigClientDataLogger {
    public MobileConfigMarauderLogger mLogger = null;

    public final void logApiResponse(String str, String str2, boolean z, String str3) {
        BLog.d("MobileConfigClientDataLogger", "Function name: %s params %s success %s extra %s", str, str2, Boolean.valueOf(z), str3);
        if (this.mLogger != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("function_name", str);
            hashMap.put("params", str2);
            hashMap.put("success", Boolean.toString(z));
            hashMap.put("extra", str3);
            this.mLogger.logEvent("mobileconfig_service_client_data_logger", hashMap);
        }
    }
}
