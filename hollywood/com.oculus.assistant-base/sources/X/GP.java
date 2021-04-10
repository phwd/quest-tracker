package X;

import java.util.HashMap;

public final class GP {
    public C1422zE A00 = null;

    public final void A00(String str, String str2, boolean z, String str3) {
        C0139Dd.A0K("MobileConfigClientDataLogger", "Function name: %s params %s success %s extra %s", str, str2, Boolean.valueOf(z), str3);
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
