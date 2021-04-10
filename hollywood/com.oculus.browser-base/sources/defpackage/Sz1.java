package defpackage;

import java.util.concurrent.Executor;

/* renamed from: Sz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Sz1 implements AbstractC1798bA1 {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f8930a;
    public final Object b = new Object();
    public AbstractC0838Ns0 c;

    public Sz1(Executor executor, AbstractC0838Ns0 ns0) {
        this.f8930a = executor;
        this.c = ns0;
    }

    @Override // defpackage.AbstractC1798bA1
    public final void a(C3506lA1 la1) {
        if (!la1.c()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.f8930a.execute(new Uz1(this, la1));
                }
            }
        }
    }
}
