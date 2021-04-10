package X;

import android.app.SharedElementCallback;

/* renamed from: X.aU  reason: case insensitive filesystem */
public class C0296aU implements AnonymousClass4B {
    public final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener A00;
    public final /* synthetic */ AnonymousClass36 A01;

    public C0296aU(AnonymousClass36 r1, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
        this.A01 = r1;
        this.A00 = onSharedElementsReadyListener;
    }

    @Override // X.AnonymousClass4B
    public final void A3y() {
        this.A00.onSharedElementsReady();
    }
}
