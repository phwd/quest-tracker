package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.ReconnectionService;

/* renamed from: fD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2490fD1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9906a;
    public final CastOptions b;
    public final TI1 c;
    public final C2508fJ1 d;
    public final C2508fJ1 e;
    public final Handler f = new HandlerC2841hG1(Looper.getMainLooper());
    public final Runnable g = new RunnableC4370qD1(this);

    public C2490fD1(Context context, CastOptions castOptions, TI1 ti1) {
        this.f9906a = context;
        this.b = castOptions;
        this.c = ti1;
        C2508fJ1 fj1 = new C2508fJ1(context);
        this.d = fj1;
        fj1.g = new C3515lD1(this);
        C2508fJ1 fj12 = new C2508fJ1(context);
        this.e = fj12;
        fj12.g = new C5901zD1(this);
    }

    public final void a(boolean z) {
    }

    public final void b(boolean z) {
        if (this.b.L) {
            this.f.removeCallbacks(this.g);
            Intent intent = new Intent(this.f9906a, ReconnectionService.class);
            intent.setPackage(this.f9906a.getPackageName());
            try {
                this.f9906a.startService(intent);
            } catch (IllegalStateException unused) {
                if (z) {
                    this.f.postDelayed(this.g, 1000);
                }
            }
        }
    }
}
