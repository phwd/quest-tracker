package defpackage;

import java.util.concurrent.Executor;

/* renamed from: sH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4723sH1 implements AbstractC4043oI1 {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f11264a;
    public final Object b = new Object();
    public AbstractC0899Os0 c;

    public C4723sH1(Executor executor, AbstractC0899Os0 os0) {
        this.f11264a = executor;
        this.c = os0;
    }

    @Override // defpackage.AbstractC4043oI1
    public final void a(OI1 oi1) {
        if (!oi1.d()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.f11264a.execute(new JH1(this, oi1));
                }
            }
        }
    }
}
