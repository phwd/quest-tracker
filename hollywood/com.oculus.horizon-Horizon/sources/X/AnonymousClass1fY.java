package X;

import com.oculus.deviceconfigclient.MarauderLogger;
import java.util.HashMap;

/* renamed from: X.1fY  reason: invalid class name */
public final class AnonymousClass1fY {
    public MarauderLogger A00 = null;

    public final void A00(String str, String str2, boolean z, String str3) {
        if (this.A00 != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("function_name", str);
            hashMap.put("params", str2);
            hashMap.put("success", Boolean.toString(z));
            hashMap.put("extra", str3);
            this.A00.A00(hashMap);
        }
    }
}
