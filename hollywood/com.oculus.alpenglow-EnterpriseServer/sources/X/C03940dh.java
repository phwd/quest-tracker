package X;

import android.app.SharedElementCallback;

/* renamed from: X.0dh  reason: invalid class name and case insensitive filesystem */
public class C03940dh implements AnonymousClass07Y {
    public final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener A00;
    public final /* synthetic */ AnonymousClass06U A01;

    public C03940dh(AnonymousClass06U r1, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
        this.A01 = r1;
        this.A00 = onSharedElementsReadyListener;
    }

    @Override // X.AnonymousClass07Y
    public final void A6W() {
        this.A00.onSharedElementsReady();
    }
}
