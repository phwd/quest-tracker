package org.chromium.components.gcm_driver;

import J.N;
import android.content.SharedPreferences;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GCMDriver {

    /* renamed from: a  reason: collision with root package name */
    public static GCMDriver f10847a;
    public long b;
    public C2704gW c = new C2704gW();

    public GCMDriver(long j) {
        this.b = j;
    }

    public static void a(C3383kU kUVar) {
        Object obj = ThreadUtils.f10596a;
        GCMDriver gCMDriver = f10847a;
        if (gCMDriver != null) {
            N.M6eL4wmM(gCMDriver.b, gCMDriver, kUVar.b, kUVar.f10282a, kUVar.c, kUVar.d, kUVar.e, kUVar.g);
            return;
        }
        throw new RuntimeException("Failed to instantiate GCMDriver.");
    }

    public static GCMDriver create(long j) {
        if (f10847a == null) {
            f10847a = new GCMDriver(j);
            SharedPreferences sharedPreferences = AbstractC3983nz.f10523a;
            if (sharedPreferences.getBoolean("has_persisted_messages", false)) {
                P21 f0 = P21.f0();
                try {
                    HashSet hashSet = new HashSet(ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0).getStringSet("fcm_lazy_subscriptions", Collections.emptySet()));
                    f0.close();
                    sharedPreferences.edit().putStringSet("subscriptions_with_persisted_messages", hashSet).apply();
                    sharedPreferences.edit().remove("has_persisted_messages").apply();
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            }
            return f10847a;
        }
        throw new IllegalStateException("Already instantiated");
        throw th;
    }

    public final void destroy() {
        f10847a = null;
        this.b = 0;
    }

    public final void register(String str, String str2) {
        C1846bU bUVar = new C1846bU(this, str, str2);
        Executor executor = AbstractC2032cb.f9616a;
        bUVar.f();
        ((ExecutorC1463Ya) executor).execute(bUVar.e);
    }

    public final void replayPersistedMessages(String str) {
        C3383kU[] kUVarArr;
        HashSet hashSet = new HashSet(AbstractC3983nz.f10523a.getStringSet("subscriptions_with_persisted_messages", Collections.emptySet()));
        HashSet hashSet2 = new HashSet();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str2.startsWith(str)) {
                hashSet2.add(str2);
            }
        }
        if (!hashSet2.isEmpty()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                String str3 = (String) it2.next();
                try {
                    JSONArray jSONArray = new JSONArray(ContextUtils.getApplicationContext().getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0).getString(str3, "[]"));
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            C3383kU a2 = C3383kU.a(jSONArray.getJSONObject(i), new C2700gU(null));
                            if (a2 == null) {
                                AbstractC1220Ua0.a("LazySubscriptions", "Persisted GCM Message is invalid. Sender id:" + C3383kU.c(jSONArray.getJSONObject(i)), new Object[0]);
                            } else {
                                arrayList.add(a2);
                            }
                        } catch (JSONException e) {
                            AbstractC1220Ua0.a("LazySubscriptions", "Error when creating a GCMMessage from a JSONObject:" + e.getMessage(), new Object[0]);
                        }
                    }
                    kUVarArr = (C3383kU[]) arrayList.toArray(new C3383kU[arrayList.size()]);
                } catch (JSONException unused) {
                    AbstractC1220Ua0.a("LazySubscriptions", AbstractC2531fV.f("Error when parsing the persisted message queue for subscriber:", str3), new Object[0]);
                    kUVarArr = new C3383kU[0];
                }
                for (C3383kU kUVar : kUVarArr) {
                    a(kUVar);
                }
                S70.b(str3);
            }
            PostTask.b(C3070if1.b, new RunnableC1666aU(SystemClock.elapsedRealtime() - elapsedRealtime), 0);
        }
    }

    public final void unregister(String str, String str2) {
        C2017cU cUVar = new C2017cU(this, str, str2);
        Executor executor = AbstractC2032cb.f9616a;
        cUVar.f();
        ((ExecutorC1463Ya) executor).execute(cUVar.e);
    }
}
