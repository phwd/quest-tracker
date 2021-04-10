package org.chromium.chrome.browser.omaha.notification;

import J.N;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UpdateNotificationServiceBridge implements AbstractC3103iq1, AbstractC4371qE {
    public final Callback F;
    public M2 G;
    public C5321vq1 H;

    public UpdateNotificationServiceBridge(M2 m2) {
        C3787mq1 mq1 = new C3787mq1(this);
        this.F = mq1;
        this.G = m2;
        AbstractC4981tq1.f11374a.a(mq1);
        this.G.a(this);
    }

    public static void launchChromeActivity(int i) {
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            if (i == 1) {
                AbstractC4981tq1.f11374a.a(new Bq1(applicationContext));
            } else if (i == 3) {
                S20.y(new Intent("android.intent.action.VIEW").addFlags(268435456).setClass(applicationContext, Lr.class).putExtra("org.chromium.chrome.browser.omaha.inline_update_notification_received_extra", true), null);
            }
        } catch (IllegalArgumentException e) {
            AbstractC1220Ua0.a("cr_UpdateNotif", "Failed to start activity in background.", e);
        }
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        AbstractC4981tq1.f11374a.e(this.F);
        this.G.b(this);
        this.G = null;
    }

    @Override // defpackage.AbstractC3103iq1
    public void f(Intent intent) {
        g();
    }

    public final void g() {
        C5321vq1 vq1 = this.H;
        if (vq1 != null) {
            int i = vq1.f11503a;
            boolean z = true;
            if (i == 1 || i == 3) {
                if (i != 3 && !N.M09VlOh_("UpdateNotificationScheduleServiceImmediateShowOption")) {
                    z = false;
                }
                N.Mk77ZiB7(Sp1.c(), Sp1.b(), this.H.f11503a, z);
            }
        }
    }
}
