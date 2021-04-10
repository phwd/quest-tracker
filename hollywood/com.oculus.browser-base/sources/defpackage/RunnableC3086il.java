package defpackage;

import J.N;
import android.graphics.BitmapFactory;
import java.io.File;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: il  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3086il implements Runnable {
    public final C3941nl F;
    public final LZ G;
    public final Callback H;
    public final long I;

    public RunnableC3086il(C3941nl nlVar, LZ lz, Callback callback, long j) {
        this.F = nlVar;
        this.G = lz;
        this.H = callback;
        this.I = j;
    }

    public void run() {
        C3941nl nlVar = this.F;
        LZ lz = this.G;
        Callback callback = this.H;
        long j = this.I;
        String Mtj63gRg = N.Mtj63gRg(nlVar.f8483a.f8698a, lz.f8424a);
        Objects.requireNonNull(nlVar.b);
        PostTask.b(Zo1.c, new RunnableC3428kl(nlVar, lz, callback, new File(Mtj63gRg).exists() ? BitmapFactory.decodeFile(Mtj63gRg, null) : null, j), 0);
    }
}
