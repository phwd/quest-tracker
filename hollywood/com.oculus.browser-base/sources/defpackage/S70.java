package defpackage;

import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashSet;
import org.chromium.base.ContextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: S70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class S70 {
    public static String a(String str, String str2) {
        if (str.equals("com.google.chrome.fcm.invalidations")) {
            return AbstractC2531fV.f(str, "8181035976");
        }
        return AbstractC2531fV.f(str, str2);
    }

    public static void b(String str) {
        ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0).edit().remove(str).apply();
        e(str, false);
    }

    public static JSONArray c(JSONArray jSONArray, String str) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.optString("collapseKey", null).equals(str)) {
                StringBuilder i2 = AbstractC2531fV.i("Dropping GCM Message due to collapse key collision. Sender id:");
                i2.append(jSONObject.optString("senderId", null));
                AbstractC1220Ua0.d("LazySubscriptions", i2.toString(), new Object[0]);
            } else {
                jSONArray2.put(jSONObject);
            }
        }
        return jSONArray2;
    }

    public static boolean d(String str) {
        P21 f0 = P21.f0();
        try {
            boolean contains = new HashSet(ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0).getStringSet("fcm_lazy_subscriptions", Collections.emptySet())).contains(str);
            f0.close();
            return contains;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static void e(String str, boolean z) {
        SharedPreferences sharedPreferences = AbstractC3983nz.f10523a;
        HashSet hashSet = new HashSet(sharedPreferences.getStringSet("subscriptions_with_persisted_messages", Collections.emptySet()));
        if (hashSet.contains(str) != z) {
            if (z) {
                hashSet.add(str);
            } else {
                hashSet.remove(str);
            }
            sharedPreferences.edit().putStringSet("subscriptions_with_persisted_messages", hashSet).apply();
        }
    }

    public static void f(String str, boolean z) {
        boolean d = d(str);
        if (d != z) {
            if (d) {
                b(str);
            }
            SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0);
            HashSet hashSet = new HashSet(sharedPreferences.getStringSet("fcm_lazy_subscriptions", Collections.emptySet()));
            if (d) {
                hashSet.remove(str);
            } else {
                hashSet.add(str);
            }
            sharedPreferences.edit().putStringSet("fcm_lazy_subscriptions", hashSet).apply();
        }
    }
}
