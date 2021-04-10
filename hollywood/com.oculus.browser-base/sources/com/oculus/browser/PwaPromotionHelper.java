package com.oculus.browser;

import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PwaPromotionHelper {

    /* renamed from: a  reason: collision with root package name */
    public static JSONObject f9709a;

    public static void a() {
        if (f9709a == null) {
            String string = Preferences.getInstance().getString("PWA_PROMOTION_INFO", null);
            if (string == null) {
                f9709a = new JSONObject();
                return;
            }
            try {
                f9709a = new JSONObject(string);
            } catch (Exception e) {
                AbstractC1220Ua0.a("PwaPromotionHelper", "Error: JSON stored in Preferences invalid!", new Object[0]);
                AbstractC1220Ua0.a("PwaPromotionHelper", e.toString(), new Object[0]);
                Preferences.getInstance().setString("PWA_PROMOTION_INFO", null);
                f9709a = null;
                f9709a = new JSONObject();
            }
        }
    }

    public static void savePwaAppPromotionShown(String str, boolean z, String str2) {
        JSONObject jSONObject;
        a();
        JSONObject jSONObject2 = null;
        try {
            jSONObject = f9709a.getJSONObject(str);
        } catch (Exception unused) {
            jSONObject = null;
        }
        String str3 = "open";
        if (jSONObject == null) {
            try {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("appId", str);
                jSONObject3.put("scope", str2);
                jSONObject3.put("install", true);
                jSONObject3.put(str3, z);
                jSONObject2 = jSONObject3;
            } catch (Exception e) {
                AbstractC1220Ua0.a("PwaPromotionHelper", e.toString(), new Object[0]);
            }
            if (jSONObject2 != null) {
                try {
                    f9709a.put(str, jSONObject2);
                } catch (Exception e2) {
                    AbstractC1220Ua0.a("PwaPromotionHelper", e2.toString(), new Object[0]);
                }
            }
        } else {
            if (!z) {
                str3 = "install";
            }
            try {
                jSONObject.put(str3, true);
            } catch (Exception e3) {
                AbstractC1220Ua0.a("PwaPromotionHelper", e3.toString(), new Object[0]);
            }
        }
        Preferences.getInstance().setString("PWA_PROMOTION_INFO", f9709a.toString());
    }

    public static boolean wasPwaAppPromotionShown(String str, boolean z) {
        JSONObject jSONObject;
        a();
        try {
            jSONObject = f9709a.getJSONObject(str);
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return false;
        }
        return jSONObject.optBoolean(z ? "open" : "install", true);
    }
}
