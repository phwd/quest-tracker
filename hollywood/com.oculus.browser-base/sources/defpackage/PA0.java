package defpackage;

import android.os.Handler;

/* renamed from: PA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PA0 {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f8674a = new Handler();
    public final Runnable b;
    public boolean c;

    public PA0(Runnable runnable) {
        this.b = new OA0(this, runnable);
    }

    public void a() {
        if (!this.c) {
            this.c = true;
            this.f8674a.post(this.b);
        }
    }
}
