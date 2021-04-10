package com.oculus.panelapp.socialreauth;

import android.util.Log;
import com.oculus.panelapp.socialreauth.fetchers.MessengerAcknowledgedFetcher;

/* renamed from: com.oculus.panelapp.socialreauth.-$$Lambda$SocialReauthTabletPanelApp$c9l0vYlxoUy220tMBfry8HagRhQ2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$SocialReauthTabletPanelApp$c9l0vYlxoUy220tMBfry8HagRhQ2 implements MessengerAcknowledgedFetcher.IsMessengerAcknowledgedFailureCallback {
    public static final /* synthetic */ $$Lambda$SocialReauthTabletPanelApp$c9l0vYlxoUy220tMBfry8HagRhQ2 INSTANCE = new $$Lambda$SocialReauthTabletPanelApp$c9l0vYlxoUy220tMBfry8HagRhQ2();

    @Override // com.oculus.panelapp.socialreauth.fetchers.MessengerAcknowledgedFetcher.IsMessengerAcknowledgedFailureCallback
    public final void onFailure(Throwable th) {
        Log.e(SocialReauthTabletPanelApp.TAG, "Error fetching whether user has acknowledged Messenger on device", th);
    }
}
