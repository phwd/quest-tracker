package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: ll  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3599ll implements Runnable {
    public final C3941nl F;
    public final LZ G;
    public final Callback H;
    public final C2901hg I;

    /* renamed from: J  reason: collision with root package name */
    public final long f10369J;

    public RunnableC3599ll(C3941nl nlVar, LZ lz, Callback callback, C2901hg hgVar, long j) {
        this.F = nlVar;
        this.G = lz;
        this.H = callback;
        this.I = hgVar;
        this.f10369J = j;
    }

    public void run() {
        C3941nl nlVar = this.F;
        LZ lz = this.G;
        Callback callback = this.H;
        C2901hg hgVar = this.I;
        long j = this.f10369J;
        Objects.requireNonNull(nlVar);
        if (hgVar != null) {
            callback.onResult(hgVar);
            nlVar.f(lz.b, 9);
            PZ pz = nlVar.f8483a;
            String str = lz.b;
            Objects.requireNonNull(pz);
            N.M6WiBncI(str, j);
            return;
        }
        N.Mq$OMPDL(nlVar.f8483a.f8698a, 1, lz.f8424a, lz.b, lz.e, new NZ(new C2916hl(nlVar, callback, lz, j)));
    }
}
