package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: hl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2916hl extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3941nl f10098a;
    public final Callback b;
    public final LZ c;
    public final long d;

    public C2916hl(C3941nl nlVar, Callback callback, LZ lz, long j) {
        this.f10098a = nlVar;
        this.b = callback;
        this.c = lz;
        this.d = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3941nl nlVar = this.f10098a;
        Callback callback = this.b;
        LZ lz = this.c;
        long j = this.d;
        Objects.requireNonNull(nlVar);
        callback.onResult((C2901hg) obj);
        PZ pz = nlVar.f8483a;
        String str = lz.b;
        Objects.requireNonNull(pz);
        N.MCVt6f5k(str, j);
    }
}
