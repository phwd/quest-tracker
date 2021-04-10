package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: em0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2407em0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2749gm0 f9877a;
    public final int b;
    public final AtomicReference c;
    public final String d;
    public final boolean e;

    public C2407em0(C2749gm0 gm0, int i, AtomicReference atomicReference, String str, boolean z) {
        this.f9877a = gm0;
        this.b = i;
        this.c = atomicReference;
        this.d = str;
        this.e = z;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2749gm0 gm0 = this.f9877a;
        int i = this.b;
        AtomicReference atomicReference = this.c;
        String str = this.d;
        boolean z = this.e;
        gm0.b((Bitmap) obj, i);
        if (atomicReference.get() != null) {
            gm0.a((Drawable) atomicReference.get(), i);
        } else {
            gm0.j.p.b(str, z, new C2578fm0(gm0, atomicReference, i));
        }
    }
}
