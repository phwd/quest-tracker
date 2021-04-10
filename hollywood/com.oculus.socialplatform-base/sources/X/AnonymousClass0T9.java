package X;

import com.oculus.deviceconfigclient.MarauderLogger;
import java.util.HashMap;
import libraries.marauder.analytics.AnalyticsEventBase;

/* renamed from: X.0T9  reason: invalid class name */
public final class AnonymousClass0T9 {
    public MarauderLogger A00 = null;

    public final void A00(String str, String str2, boolean z, String str3) {
        if (this.A00 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("function_name", str);
            hashMap.put("params", str2);
            hashMap.put("success", Boolean.toString(z));
            hashMap.put(AnalyticsEventBase.EXTRAS_KEY, str3);
            this.A00.logEvent("mobileconfig_service_client_data_logger", hashMap);
        }
    }
}
