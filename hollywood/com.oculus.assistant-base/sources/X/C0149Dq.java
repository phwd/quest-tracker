package X;

import android.util.Log;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: X.Dq  reason: case insensitive filesystem */
public final class C0149Dq {
    public static Map A00;
    public static JSONObject A01;
    public static boolean A02;
    public static boolean A03;

    static {
        JSONObject jSONObject = new JSONObject();
        A01 = jSONObject;
        if (jSONObject.has("systemproperties")) {
            try {
                JSONObject jSONObject2 = A01.getJSONObject("systemproperties");
                StringBuilder sb = new StringBuilder();
                sb.append("Setting E2E system properties: ");
                sb.append(jSONObject2);
                Log.w("EndToEnd-Test", sb.toString());
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    System.setProperty(next, jSONObject2.getString(next));
                }
            } catch (Exception e) {
                Log.e("EndToEnd-Test", "Failed to set E2E system properties", e);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        if ("true".equals(java.lang.System.getProperty("fb.running_e2e")) != false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean A00() {
        /*
            java.lang.Class<X.Dq> r4 = X.C0149Dq.class
            monitor-enter(r4)
            boolean r0 = X.C0149Dq.A02     // Catch:{ all -> 0x0033 }
            if (r0 != 0) goto L_0x002f
            java.lang.String r3 = "true"
            java.lang.String r1 = "fb.running_e2e"
            java.lang.String r0 = X.AnonymousClass89.A02(r1)     // Catch:{ all -> 0x0033 }
            boolean r0 = r3.equals(r0)     // Catch:{ all -> 0x0033 }
            r2 = 1
            if (r0 != 0) goto L_0x0021
            java.lang.String r0 = java.lang.System.getProperty(r1)     // Catch:{ all -> 0x0033 }
            boolean r1 = r3.equals(r0)     // Catch:{ all -> 0x0033 }
            r0 = 0
            if (r1 == 0) goto L_0x0022
        L_0x0021:
            r0 = 1
        L_0x0022:
            X.C0149Dq.A03 = r0     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x002d
            java.lang.String r1 = "EndToEnd-Test"
            java.lang.String r0 = "Is running E2E test"
            android.util.Log.w(r1, r0)     // Catch:{ all -> 0x0033 }
        L_0x002d:
            X.C0149Dq.A02 = r2     // Catch:{ all -> 0x0033 }
        L_0x002f:
            boolean r0 = X.C0149Dq.A03     // Catch:{ all -> 0x0033 }
            monitor-exit(r4)
            return r0
        L_0x0033:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0149Dq.A00():boolean");
    }
}
