package X;

import android.app.SharedElementCallback;

public class U9 implements AnonymousClass4v {
    public final /* synthetic */ SharedElementCallback.OnSharedElementsReadyListener A00;
    public final /* synthetic */ AnonymousClass3q A01;

    public U9(AnonymousClass3q r1, SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener) {
        this.A01 = r1;
        this.A00 = onSharedElementsReadyListener;
    }

    @Override // X.AnonymousClass4v
    public final void A2e() {
        this.A00.onSharedElementsReady();
    }
}
