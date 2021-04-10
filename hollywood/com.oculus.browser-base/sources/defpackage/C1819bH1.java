package defpackage;

import java.util.concurrent.Executor;

/* renamed from: bH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1819bH1 implements AbstractC4043oI1 {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f9528a;
    public final Object b = new Object();
    public AbstractC0716Ls0 c;

    public C1819bH1(Executor executor, AbstractC0716Ls0 ls0) {
        this.f9528a = executor;
        this.c = ls0;
    }

    @Override // defpackage.AbstractC4043oI1
    public final void a(OI1 oi1) {
        synchronized (this.b) {
            if (this.c != null) {
                this.f9528a.execute(new RunnableC3527lH1(this, oi1));
            }
        }
    }
}
