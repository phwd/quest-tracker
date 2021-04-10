package com.oculus.panelapp.messenger.views;

import android.util.Log;
import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;

/* renamed from: com.oculus.panelapp.messenger.views.-$$Lambda$ThreadView$1FmryAvF_-UaOLA5jWyown6w1r82  reason: invalid class name */
public final /* synthetic */ class $$Lambda$ThreadView$1FmryAvF_UaOLA5jWyown6w1r82 implements ICanViewerMessageFetcher.CanViewerMessageFailureCallback {
    public static final /* synthetic */ $$Lambda$ThreadView$1FmryAvF_UaOLA5jWyown6w1r82 INSTANCE = new $$Lambda$ThreadView$1FmryAvF_UaOLA5jWyown6w1r82();

    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher.CanViewerMessageFailureCallback
    public final void onFailure(Throwable th) {
        Log.e(ThreadView.TAG, "Error fetching whether viewer can message thread participants");
    }
}
