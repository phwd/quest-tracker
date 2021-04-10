package com.oculus.panelapp.messenger.fetchers;

public interface ICanViewerMessageFetcher {

    @FunctionalInterface
    public interface CanViewerMessageFailureCallback {
        void onFailure(Throwable th);
    }

    @FunctionalInterface
    public interface CanViewerMessageSuccessCallback {
        void onSuccess(Boolean bool);
    }

    void destroy();

    void query(String str, CanViewerMessageSuccessCallback canViewerMessageSuccessCallback, CanViewerMessageFailureCallback canViewerMessageFailureCallback);
}
