package defpackage;

import android.graphics.drawable.Drawable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2578fm0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2749gm0 f9949a;
    public final AtomicReference b;
    public final int c;

    public C2578fm0(C2749gm0 gm0, AtomicReference atomicReference, int i) {
        this.f9949a = gm0;
        this.b = atomicReference;
        this.c = i;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2749gm0 gm0 = this.f9949a;
        AtomicReference atomicReference = this.b;
        int i = this.c;
        Drawable drawable = (Drawable) obj;
        Objects.requireNonNull(gm0);
        atomicReference.set(drawable);
        gm0.a(drawable, i);
    }
}
