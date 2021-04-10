package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.services.gcm.GCMBackgroundService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: Dr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0226Dr implements Runnable {
    public final String F;
    public final Bundle G;

    public RunnableC0226Dr(String str, Bundle bundle) {
        this.F = str;
        this.G = bundle;
    }

    public void run() {
        boolean z;
        boolean z2 = false;
        try {
            C3383kU kUVar = new C3383kU(this.F, this.G);
            Object obj = ThreadUtils.f10596a;
            if (kUVar.b.startsWith("wp:")) {
                boolean isDeviceIdleMode = ((PowerManager) ContextUtils.getApplicationContext().getSystemService("power")).isDeviceIdleMode();
                int i = kUVar.b() == 2 ? 1 : 0;
                if (isDeviceIdleMode) {
                    i = i != 0 ? 3 : 2;
                }
                AbstractC3364kK0.g("GCM.WebPushReceived.DeviceState", i, 4);
            }
            if (C1321Vq.b().h) {
                z = false;
            } else {
                String a2 = S70.a(kUVar.b, kUVar.f10282a);
                z = S70.d(a2) && !(kUVar.b() == 2);
                if (z) {
                    SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0);
                    try {
                        JSONArray jSONArray = new JSONArray(sharedPreferences.getString(a2, "[]"));
                        String str = kUVar.d;
                        if (str != null) {
                            jSONArray = S70.c(jSONArray, str);
                        }
                        if (jSONArray.length() == 3) {
                            AbstractC1220Ua0.f("LazySubscriptions", "Dropping GCM Message due queue size limit. Sender id:" + C3383kU.c(jSONArray.getJSONObject(0)), new Object[0]);
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i2 = 1; i2 < 3; i2++) {
                                jSONArray2.put(jSONArray.get(i2));
                            }
                            jSONArray = jSONArray2;
                        }
                        jSONArray.put((JSONObject) kUVar.d(new C2871hU(kUVar, null)));
                        sharedPreferences.edit().putString(a2, jSONArray.toString()).apply();
                        S70.e(a2, true);
                    } catch (JSONException e) {
                        StringBuilder k = AbstractC2531fV.k("Error when parsing the persisted message queue for subscriber:", a2, ":");
                        k.append(e.getMessage());
                        AbstractC1220Ua0.a("LazySubscriptions", k.toString(), new Object[0]);
                    }
                }
            }
            if (!z) {
                if (kUVar.b() == 2) {
                    String a3 = AbstractC4681s31.a(kUVar.b, kUVar.f10282a);
                    P21 f0 = P21.f0();
                    try {
                        boolean z3 = (ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.subscription_flags", 0).getInt(a3, 0) & 2) == 2;
                        f0.close();
                        if (z3) {
                            try {
                                Context applicationContext = ContextUtils.getApplicationContext();
                                Intent intent = new Intent(applicationContext, GCMBackgroundService.class);
                                intent.putExtras((Bundle) kUVar.d(new C2529fU(kUVar, null)));
                                applicationContext.startService(intent);
                                z2 = true;
                            } catch (IllegalStateException e2) {
                                AbstractC1220Ua0.a("ChromeGcmListener", "Could not start background service", e2);
                            }
                        }
                    } catch (Throwable th) {
                        AbstractC0754Mh1.f8495a.a(th, th);
                    }
                }
                if (!z2) {
                    C1294Ve1 ve1 = new C1294Ve1();
                    ve1.b = 0;
                    C1355We1 a4 = ve1.a();
                    C1111Se1 se1 = new C1111Se1(1);
                    se1.g = a4;
                    se1.b = (Bundle) kUVar.d(new C2529fU(kUVar, null));
                    AbstractC2898hf.b().b(ContextUtils.getApplicationContext(), se1.a());
                    return;
                }
                return;
            }
            return;
            throw th;
        } catch (IllegalArgumentException e3) {
            AbstractC1220Ua0.a("ChromeGcmListener", "Received an invalid GCM Message", e3);
        }
    }
}
