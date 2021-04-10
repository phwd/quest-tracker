package defpackage;

import android.content.Intent;

/* renamed from: fs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2596fs0 extends XY0 {
    @Override // defpackage.XY0
    public void a(Intent intent) {
        C3279js0 js0;
        YY0 yy0 = this.f9216a;
        synchronized (C3279js0.b) {
            if (C3279js0.c == null) {
                C3279js0.c = new C3279js0(yy0);
            }
            js0 = C3279js0.c;
        }
        js0.d();
    }

    @Override // defpackage.XY0
    public void b() {
        this.f9216a.setIntentRedelivery(true);
    }
}
