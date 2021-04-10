package defpackage;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* renamed from: BG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BG0 extends AbstractC2032cb {
    public long i;
    public final /* synthetic */ OG0 j;

    public BG0(OG0 og0) {
        this.j = og0;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        try {
            TraceEvent.Y("ChromeBrowserInitializer.onDeferredStartup.doInBackground", null);
            this.i = SystemClock.uptimeMillis();
            WebappRegistry webappRegistry = AbstractC2957hy1.f10115a;
            int i2 = AbstractC0938Pi.f8708a;
            Context applicationContext = ContextUtils.getApplicationContext();
            if (AppWidgetManager.getInstance(applicationContext) != null) {
                applicationContext.sendBroadcast(new Intent(applicationContext.getPackageName() + ".BOOKMARK_APPWIDGET_UPDATE", null, applicationContext, AbstractC0938Pi.class));
            }
            AbstractC3295jx1.a();
            Objects.requireNonNull(this.j);
            synchronized (OG0.f8614a) {
                PU0 pu0 = NU0.f8549a;
                if (!pu0.d("snapshot_database_removed", false)) {
                    ContextUtils.getApplicationContext().deleteDatabase("snapshots.db");
                    pu0.m("snapshot_database_removed", true);
                }
            }
            WebappRegistry.d();
            AbstractC4482qu0.a();
            return null;
        } finally {
            TraceEvent.f0("ChromeBrowserInitializer.onDeferredStartup.doInBackground");
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r11 = (Void) obj;
        WebappRegistry webappRegistry = AbstractC2957hy1.f10115a;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - webappRegistry.c.getLong("last_cleanup", 0) >= 2419200000L) {
            Iterator it = webappRegistry.b.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Xx1 xx1 = (Xx1) entry.getValue();
                String d = xx1.d();
                if (d != null) {
                    boolean z = true;
                    if (((String) entry.getKey()).startsWith("webapk-")) {
                        if (NU0.f8549a.j("webapk_uninstalled_packages").contains(d)) {
                            z = false;
                        } else {
                            z = true ^ AbstractC4652ru0.b(ContextUtils.getApplicationContext(), d);
                        }
                    }
                    if (!z) {
                    }
                } else if (currentTimeMillis - xx1.b() < 7862400000L) {
                }
                xx1.a();
                xx1.c.edit().clear().apply();
                it.remove();
            }
            webappRegistry.c.edit().putLong("last_cleanup", currentTimeMillis).putStringSet("webapp_set", webappRegistry.b.keySet()).apply();
        }
        AbstractC3364kK0.i("UMA.Debug.EnableCrashUpload.DeferredStartUpAsyncTaskDuration", SystemClock.uptimeMillis() - this.i);
    }
}
