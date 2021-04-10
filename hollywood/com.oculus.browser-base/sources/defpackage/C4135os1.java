package defpackage;

import android.os.Handler;
import android.view.Choreographer;

/* renamed from: os1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4135os1 {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal f11033a = new C3793ms1();
    public boolean b;
    public ZM c;
    public long d;
    public boolean e;
    public boolean f;
    public final Choreographer g;
    public final Choreographer.FrameCallback h;
    public long i;
    public final Handler j = new Handler();

    public C4135os1(ZM zm, float f2) {
        this.c = zm;
        b(f2);
        this.g = Choreographer.getInstance();
        this.h = new Choreographer$FrameCallbackC3964ns1(this);
        this.i = System.nanoTime();
    }

    public void a() {
        if (!this.f) {
            this.f = true;
            this.b = ((Boolean) f11033a.get()).booleanValue();
            this.g.postFrameCallback(this.h);
        }
    }

    public void b(float f2) {
        this.e = f2 < 30.0f;
        if (f2 <= 0.0f) {
            f2 = 60.0f;
        }
        this.d = (long) (1.0E9f / f2);
    }
}
