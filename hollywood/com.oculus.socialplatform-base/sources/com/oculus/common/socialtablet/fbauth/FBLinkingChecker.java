package com.oculus.common.socialtablet.fbauth;

import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.socialtablet.fbauth.FBLinkingChecker;
import com.oculus.common.socialtablet.fetchers.FBLinkingFetcher;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;

public final class FBLinkingChecker {
    public static final SystemUXRoute LINK_FLOW_SYSTEMUX_ROUTE = SystemUXRoute.AUI_PEOPLE;
    public static final String TAG = LoggingUtil.tag(FBLinkingChecker.class);
    public FBLinkingFetcher mFBLinkingFetcher;
    public FBLinkingFetcher.FetchLinkedStatusListener mListener;
    public AndroidPanelApp mPanelApp;

    public interface OnVerifiedFBLinkedCallback {
        void onVerifiedFBLinked();
    }

    public void destroy() {
        FBLinkingFetcher.FetchLinkedStatusListener fetchLinkedStatusListener = this.mListener;
        if (fetchLinkedStatusListener != null) {
            this.mFBLinkingFetcher.unregisterFBLinkedStatusListener(fetchLinkedStatusListener);
            this.mListener = null;
        }
        this.mFBLinkingFetcher = null;
    }

    public /* synthetic */ void lambda$redirectToLinkFlowIfUnlinked$0$FBLinkingChecker(OnVerifiedFBLinkedCallback onVerifiedFBLinkedCallback, boolean z) {
        if (z) {
            onVerifiedFBLinkedCallback.onVerifiedFBLinked();
        } else {
            actionNavigate(LINK_FLOW_SYSTEMUX_ROUTE, "");
        }
    }

    public void redirectToLinkFlowIfUnlinked(OnVerifiedFBLinkedCallback onVerifiedFBLinkedCallback) {
        $$Lambda$FBLinkingChecker$JZOJW4JM0TBvoQbc1sbNJSgDVhU2 r1 = new FBLinkingFetcher.FetchLinkedStatusListener(onVerifiedFBLinkedCallback) {
            /* class com.oculus.common.socialtablet.fbauth.$$Lambda$FBLinkingChecker$JZOJW4JM0TBvoQbc1sbNJSgDVhU2 */
            public final /* synthetic */ FBLinkingChecker.OnVerifiedFBLinkedCallback f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.common.socialtablet.fetchers.FBLinkingFetcher.FetchLinkedStatusListener
            public final void onLinkedStatusFetched(boolean z) {
                FBLinkingChecker.this.lambda$redirectToLinkFlowIfUnlinked$0$FBLinkingChecker(this.f$1, z);
            }
        };
        this.mListener = r1;
        this.mFBLinkingFetcher.registerFBLinkedStatusListener(r1);
    }

    public FBLinkingChecker(AndroidPanelApp androidPanelApp, FBLinkingFetcher fBLinkingFetcher) {
        this.mPanelApp = androidPanelApp;
        this.mFBLinkingFetcher = fBLinkingFetcher;
    }

    private void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        this.mPanelApp.mFrameCommandChannel.launch(systemUXRoute.path(), str);
    }
}
