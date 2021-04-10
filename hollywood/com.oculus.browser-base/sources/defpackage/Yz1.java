package defpackage;

import java.util.concurrent.Executor;

/* renamed from: Yz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Yz1 implements AbstractC1798bA1 {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f9311a;
    public final Object b = new Object();
    public AbstractC1082Rs0 c;

    public Yz1(Executor executor, AbstractC1082Rs0 rs0) {
        this.f9311a = executor;
        this.c = rs0;
    }

    @Override // defpackage.AbstractC1798bA1
    public final void a(C3506lA1 la1) {
        if (la1.c()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.f9311a.execute(new Wz1(this, la1));
                }
            }
        }
    }
}
