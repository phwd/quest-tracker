package defpackage;

import org.chromium.base.task.PostTask;

/* renamed from: RE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RE {

    /* renamed from: a  reason: collision with root package name */
    public OE f8819a;
    public AbstractC1189Tl b = new C0209Di1(1000);
    public RunnableC5266vX0 c;
    public Runnable d;
    public final QE e = null;

    public RE(QE qe) {
    }

    public void a(Runnable runnable) {
        QE qe = this.e;
        if (qe != null) {
            RunnableC5266vX0 vx0 = this.c;
            int i = 0;
            if (vx0 != null) {
                if (vx0.F == 1) {
                    i = 1;
                }
                i = i != 0 ? 1 : 2;
            }
            qe.a(i);
        }
        this.d = runnable;
        RunnableC5266vX0 vx02 = this.c;
        if (vx02 == null) {
            b();
        } else {
            vx02.run();
        }
    }

    public final void b() {
        OE oe = this.f8819a;
        if (oe != null) {
            oe.f1(false, false);
        }
        Runnable runnable = this.d;
        if (runnable != null) {
            PostTask.b(Zo1.f9374a, runnable, 0);
        }
        this.f8819a = null;
        this.d = null;
        this.c = null;
    }
}
