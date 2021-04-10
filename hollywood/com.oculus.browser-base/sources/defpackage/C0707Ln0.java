package defpackage;

import J.N;
import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: Ln0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0707Ln0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0767Mn0 f8439a;
    public final Callback b;
    public final LZ c;
    public final long d;

    public C0707Ln0(C0767Mn0 mn0, Callback callback, LZ lz, long j) {
        this.f8439a = mn0;
        this.b = callback;
        this.c = lz;
        this.d = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0767Mn0 mn0 = this.f8439a;
        Callback callback = this.b;
        LZ lz = this.c;
        long j = this.d;
        Objects.requireNonNull(mn0);
        callback.onResult((Bitmap) obj);
        PZ pz = mn0.f8483a;
        String str = lz.b;
        Objects.requireNonNull(pz);
        N.MCVt6f5k(str, j);
    }
}
