package defpackage;

import org.chromium.base.task.PostTask;

/* renamed from: hs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2937hs extends PK {
    public final /* synthetic */ Runnable F;
    public final /* synthetic */ int G;
    public final /* synthetic */ boolean H;
    public final /* synthetic */ Runnable I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C3107is f10105J;

    public C2937hs(C3107is isVar, Runnable runnable, int i, boolean z, Runnable runnable2) {
        this.f10105J = isVar;
        this.F = runnable;
        this.G = i;
        this.H = z;
        this.I = runnable2;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void B(Exception exc) {
        PostTask.b(Zo1.f9374a, this.I, 0);
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public boolean N() {
        return this.H;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void u() {
        PostTask.b(Zo1.f9374a, this.F, 0);
        this.f10105J.a(this.G, this.H);
    }
}
