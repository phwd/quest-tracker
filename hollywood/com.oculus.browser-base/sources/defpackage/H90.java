package defpackage;

import android.os.Handler;
import android.os.Looper;

/* renamed from: H90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H90 {

    /* renamed from: a  reason: collision with root package name */
    public final UH0 f8140a;
    public final Handler b = new G90(this, Looper.getMainLooper());
    public float c;

    public H90(UH0 uh0) {
        this.f8140a = uh0;
    }

    public void a() {
        this.c = 0.0f;
        this.f8140a.l(F90.f7995a, 0);
        this.f8140a.k(F90.b, this.c);
        this.b.sendEmptyMessage(1);
    }
}
