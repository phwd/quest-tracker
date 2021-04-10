package X;

import android.app.SharedElementCallback;

/* renamed from: X.0sq  reason: invalid class name and case insensitive filesystem */
public class C07470sq implements AnonymousClass04B {
    public final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener A00;
    public final /* synthetic */ AnonymousClass037 A01;

    public C07470sq(AnonymousClass037 r1, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
        this.A01 = r1;
        this.A00 = onSharedElementsReadyListener;
    }

    @Override // X.AnonymousClass04B
    public final void A6t() {
        this.A00.onSharedElementsReady();
    }
}
