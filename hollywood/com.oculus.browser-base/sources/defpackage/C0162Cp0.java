package defpackage;

import android.app.Notification;
import android.os.Bundle;

/* renamed from: Cp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0162Cp0 extends AbstractC0345Fp0 {
    public CharSequence b;

    public C0162Cp0() {
    }

    @Override // defpackage.AbstractC0345Fp0
    public void a(Bundle bundle) {
        bundle.putString("androidx.core.app.extra.COMPAT_TEMPLATE", "androidx.core.app.NotificationCompat$BigTextStyle");
        bundle.putCharSequence("android.bigText", this.b);
    }

    @Override // defpackage.AbstractC0345Fp0
    public void b(C0406Gp0 gp0) {
        new Notification.BigTextStyle(gp0.f8113a).setBigContentTitle(null).bigText(this.b);
    }

    @Override // defpackage.AbstractC0345Fp0
    public String c() {
        return "androidx.core.app.NotificationCompat$BigTextStyle";
    }

    public C0162Cp0 g(CharSequence charSequence) {
        this.b = C0223Dp0.c(charSequence);
        return this;
    }

    public C0162Cp0(C0223Dp0 dp0) {
        if (this.f8041a != dp0) {
            this.f8041a = dp0;
            if (dp0 != null && dp0.l != this) {
                dp0.l = this;
                if (dp0 != dp0) {
                    this.f8041a = dp0;
                    if (this != this) {
                        dp0.l = this;
                        f(dp0);
                    }
                }
            }
        }
    }
}
