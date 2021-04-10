package defpackage;

import J.N;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: gl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2745gl implements Runnable {
    public final C3941nl F;
    public final LZ G;
    public final Callback H;
    public final long I;

    public RunnableC2745gl(C3941nl nlVar, LZ lz, Callback callback, long j) {
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
        PZ pz = nlVar.f8483a;
        String Mtj63gRg = N.Mtj63gRg(pz.f8698a, lz.f8424a);
        Objects.requireNonNull(nlVar.b);
        C2901hg hgVar = null;
        try {
            int length = (int) new File(Mtj63gRg).length();
            byte[] bArr = new byte[length];
            if (new FileInputStream(Mtj63gRg).read(bArr) == length) {
                hgVar = new C2901hg(bArr);
            }
        } catch (IOException e) {
            AbstractC1220Ua0.f("CachedImageFetcher", "Failed to read: %s", Mtj63gRg, e);
        }
        PostTask.b(Zo1.c, new RunnableC3599ll(nlVar, lz, callback, hgVar, j), 0);
    }
}
