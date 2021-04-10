package org.chromium.chrome.browser.webapps;

import J.N;
import android.content.SharedPreferences;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.browsing_data.UrlFilterBridge;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebappRegistry {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10804a;
    public HashMap b;
    public SharedPreferences c;
    public C2756go1 d;

    public WebappRegistry(C2615fy1 fy1) {
        P21 f0 = P21.f0();
        try {
            SharedPreferences sharedPreferences = ContextUtils.getApplicationContext().getSharedPreferences("webapp_registry", 0);
            f0.close();
            this.c = sharedPreferences;
            this.b = new HashMap();
            this.d = new C2756go1();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static void clearWebappHistoryForUrls(UrlFilterBridge urlFilterBridge) {
        for (Map.Entry entry : AbstractC2957hy1.f10115a.b.entrySet()) {
            Xx1 xx1 = (Xx1) entry.getValue();
            if (N.MrY8rM_K(urlFilterBridge.f10629a, urlFilterBridge, xx1.c.getString("url", ""))) {
                xx1.a();
                SharedPreferences.Editor edit = xx1.c.edit();
                edit.remove("last_used");
                edit.remove("url");
                edit.remove("scope");
                edit.remove("last_check_web_manifest_update_time");
                edit.remove("last_update_request_complete_time");
                edit.remove("did_last_update_request_succeed");
                edit.remove("relax_updates");
                edit.remove("show_disclosure");
                edit.remove("launch_count");
                edit.remove("webapk_uninstall_timestamp");
                edit.apply();
            }
        }
        N.MBBog0Dv(urlFilterBridge.f10629a, urlFilterBridge);
        urlFilterBridge.f10629a = 0;
    }

    public static void d() {
        WebappRegistry webappRegistry = AbstractC2957hy1.f10115a;
        Set<String> stringSet = webappRegistry.c.getStringSet("webapp_set", Collections.emptySet());
        if (!webappRegistry.f10804a) {
            webappRegistry.d.e();
            webappRegistry.f10804a = true;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = stringSet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next == null) {
                next = "";
            }
            if (!webappRegistry.b.containsKey(next)) {
                arrayList.add(Pair.create(next, new Xx1(next)));
            }
        }
        PostTask.c(Zo1.f9374a, new RunnableC2444ey1(webappRegistry, arrayList));
    }

    public static void unregisterWebappsForUrls(UrlFilterBridge urlFilterBridge) {
        WebappRegistry webappRegistry = AbstractC2957hy1.f10115a;
        Iterator it = webappRegistry.b.entrySet().iterator();
        while (it.hasNext()) {
            Xx1 xx1 = (Xx1) ((Map.Entry) it.next()).getValue();
            if (N.MrY8rM_K(urlFilterBridge.f10629a, urlFilterBridge, xx1.c.getString("url", ""))) {
                xx1.a();
                xx1.c.edit().clear().apply();
                it.remove();
            }
        }
        if (webappRegistry.b.isEmpty()) {
            webappRegistry.c.edit().clear().apply();
        } else {
            webappRegistry.c.edit().putStringSet("webapp_set", webappRegistry.b.keySet()).apply();
        }
        N.MBBog0Dv(urlFilterBridge.f10629a, urlFilterBridge);
        urlFilterBridge.f10629a = 0;
    }

    public Set a() {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (Map.Entry entry : this.b.entrySet()) {
            String b2 = b((Xx1) entry.getValue());
            if (!b2.isEmpty()) {
                hashSet2.add(C4649rt0.b(b2).toString());
            }
        }
        hashSet.addAll(hashSet2);
        hashSet.addAll(this.d.e());
        return hashSet;
    }

    public final String b(Xx1 xx1) {
        if (!xx1.b.startsWith("webapk-")) {
            return "";
        }
        return xx1.c.getString("scope", "");
    }

    public Xx1 c(String str) {
        return (Xx1) this.b.get(str);
    }
}
