package com.oculus.panelapp.messenger.fetchers;

import com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher;

public class TestCanViewerMessageFetcher implements ICanViewerMessageFetcher {
    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher
    public void destroy() {
    }

    @Override // com.oculus.panelapp.messenger.fetchers.ICanViewerMessageFetcher
    public void query(String str, ICanViewerMessageFetcher.CanViewerMessageSuccessCallback canViewerMessageSuccessCallback, ICanViewerMessageFetcher.CanViewerMessageFailureCallback canViewerMessageFailureCallback) {
        canViewerMessageSuccessCallback.onSuccess(true);
    }
}
