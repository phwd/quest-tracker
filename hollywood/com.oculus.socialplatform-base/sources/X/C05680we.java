package X;

import android.app.SharedElementCallback;

/* renamed from: X.0we  reason: invalid class name and case insensitive filesystem */
public class C05680we implements AnonymousClass04G {
    public final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener A00;
    public final /* synthetic */ AnonymousClass03C A01;

    public C05680we(AnonymousClass03C r1, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
        this.A01 = r1;
        this.A00 = onSharedElementsReadyListener;
    }

    @Override // X.AnonymousClass04G
    public final void A81() {
        this.A00.onSharedElementsReady();
    }
}
