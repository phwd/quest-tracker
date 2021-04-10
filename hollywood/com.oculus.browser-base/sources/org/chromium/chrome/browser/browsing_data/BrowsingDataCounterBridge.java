package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.view.View;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowsingDataCounterBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10627a;
    public AbstractC0334Fk b;

    public BrowsingDataCounterBridge(AbstractC0334Fk fk, int i, int i2) {
        this.b = fk;
        this.f10627a = N.MfPmZbvq(this, i, i2);
    }

    public final void onBrowsingDataCounterFinished(String str) {
        View view;
        C0537Iu iu = (C0537Iu) this.b;
        iu.H.T(str);
        if (iu.f8255J && (view = iu.H.z0) != null) {
            view.announceForAccessibility(str);
        }
    }
}
