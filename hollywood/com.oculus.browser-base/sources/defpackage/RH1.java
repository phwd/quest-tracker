package defpackage;

import java.util.concurrent.Executor;

/* renamed from: RH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RH1 implements AbstractC4043oI1 {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f8824a;
    public final Object b = new Object();
    public AbstractC1143Ss0 c;

    public RH1(Executor executor, AbstractC1143Ss0 ss0) {
        this.f8824a = executor;
        this.c = ss0;
    }

    @Override // defpackage.AbstractC4043oI1
    public final void a(OI1 oi1) {
        if (oi1.d()) {
            synchronized (this.b) {
                if (this.c != null) {
                    this.f8824a.execute(new YH1(this, oi1));
                }
            }
        }
    }
}
