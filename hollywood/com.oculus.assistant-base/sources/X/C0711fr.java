package X;

import com.facebook.assistant.oacr.Oacr;

/* renamed from: X.fr  reason: case insensitive filesystem */
public final class C0711fr implements Oacr.ResourceStatusCallback {
    public final /* synthetic */ C0740gP A00;

    public C0711fr(C0740gP gPVar) {
        this.A00 = gPVar;
    }

    @Override // com.facebook.assistant.oacr.Oacr.ResourceStatusCallback
    public final void onDownloadFinish() {
        C0139Dd.A09("AssistantClientPlatform_NativeExecutor", "mOacrResourceStatusCallbackListener, onDownloadFinish");
    }

    @Override // com.facebook.assistant.oacr.Oacr.ResourceStatusCallback
    public final void onDownloadStart() {
        C0139Dd.A09("AssistantClientPlatform_NativeExecutor", "mOacrResourceStatusCallbackListener, onDownloadStart");
        this.A00.A0h.A01(new C0810hy());
    }

    @Override // com.facebook.assistant.oacr.Oacr.ResourceStatusCallback
    public final void onDownloadProgress(double d) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "mOacrResourceStatusCallbackListener, onDownloadProgress, percentage: %f", Double.valueOf(d));
    }

    @Override // com.facebook.assistant.oacr.Oacr.ResourceStatusCallback
    public final void onDownloadRequested(int i) {
        C0139Dd.A0F("AssistantClientPlatform_NativeExecutor", "mOacrResourceStatusCallbackListener, onDownloadRequested, size: %d", Integer.valueOf(i));
    }
}
