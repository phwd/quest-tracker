package defpackage;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import org.chromium.base.ContextUtils;

/* renamed from: we0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5454we0 {

    /* renamed from: a  reason: collision with root package name */
    public C5624xe0 f11556a;
    public final Handler b = new Handler();
    public Runnable c;
    public C0074Be0 d;

    public C5454we0(C5624xe0 xe0) {
        this.f11556a = xe0;
    }

    public void a(C0074Be0 be0) {
        C5624xe0 xe0 = this.f11556a;
        if (!C5624xe0.g(xe0.e, be0)) {
            xe0.e = be0;
            Service service = xe0.f11621a;
            if (service != null || !be0.c) {
                if (service == null) {
                    xe0.i();
                    xe0.k();
                    Intent a2 = ((C1323Vr) xe0.b).a();
                    Context applicationContext = ContextUtils.getApplicationContext();
                    Object obj = K2.f8337a;
                    if (Build.VERSION.SDK_INT >= 26) {
                        applicationContext.startForegroundService(a2);
                    } else {
                        applicationContext.startService(a2);
                    }
                } else {
                    xe0.j(false, false);
                }
            }
        }
        RunnableC5284ve0 ve0 = new RunnableC5284ve0(this);
        this.c = ve0;
        if (!this.b.postDelayed(ve0, 500)) {
            AbstractC1220Ua0.f("MediaNotification", "Failed to post the throttler task.", new Object[0]);
            this.c = null;
        }
    }
}
