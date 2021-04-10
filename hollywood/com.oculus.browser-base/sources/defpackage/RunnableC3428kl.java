package defpackage;

import J.N;
import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: kl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3428kl implements Runnable {
    public final C3941nl F;
    public final LZ G;
    public final Callback H;
    public final Bitmap I;

    /* renamed from: J  reason: collision with root package name */
    public final long f10300J;

    public RunnableC3428kl(C3941nl nlVar, LZ lz, Callback callback, Bitmap bitmap, long j) {
        this.F = nlVar;
        this.G = lz;
        this.H = callback;
        this.I = bitmap;
        this.f10300J = j;
    }

    public void run() {
        C3941nl nlVar = this.F;
        LZ lz = this.G;
        Callback callback = this.H;
        Bitmap bitmap = this.I;
        long j = this.f10300J;
        Objects.requireNonNull(nlVar);
        if (bitmap != null) {
            callback.onResult(MZ.g(bitmap, lz.c, lz.d));
            nlVar.f(lz.b, 9);
            PZ pz = nlVar.f8483a;
            String str = lz.b;
            Objects.requireNonNull(pz);
            N.M6WiBncI(str, j);
            return;
        }
        N.MVa6KqKn(nlVar.f8483a.f8698a, 1, lz.f8424a, lz.b, lz.e, new OZ(new C3257jl(nlVar, callback, lz, j), lz));
    }
}
