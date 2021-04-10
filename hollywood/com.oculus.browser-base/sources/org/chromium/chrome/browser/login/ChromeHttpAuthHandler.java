package org.chromium.chrome.browser.login;

import J.N;
import android.app.Activity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeHttpAuthHandler extends WK implements AbstractC2204db0 {
    public long F;
    public String G;
    public String H;
    public C2374eb0 I;

    public ChromeHttpAuthHandler(long j) {
        this.F = j;
    }

    public static ChromeHttpAuthHandler create(long j) {
        return new ChromeHttpAuthHandler(j);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        N.MbTC7yfl(this.F, this);
    }

    public final void closeDialog() {
        C2374eb0 eb0 = this.I;
        if (eb0 != null) {
            eb0.d.dismiss();
            this.I = null;
        }
    }

    public final void onAutofillDataAvailable(String str, String str2) {
        this.G = str;
        this.H = str2;
        C2374eb0 eb0 = this.I;
        if (eb0 != null) {
            eb0.e.setText(str);
            eb0.f.setText(str2);
            eb0.e.selectAll();
        }
    }

    public final void onNativeDestroyed() {
        this.F = 0;
    }

    public final void showDialog(Tab tab, WindowAndroid windowAndroid) {
        String str;
        if (tab == null || tab.isHidden() || windowAndroid == null) {
            N.MbTC7yfl(this.F, this);
            return;
        }
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            N.MbTC7yfl(this.F, this);
            return;
        }
        C2374eb0 eb0 = new C2374eb0(activity, N.MDNVFLnS(this.F, this), null, this);
        this.I = eb0;
        String str2 = this.G;
        if (!(str2 == null || (str = this.H) == null)) {
            eb0.e.setText(str2);
            eb0.f.setText(str);
            eb0.e.selectAll();
        }
        C2374eb0 eb02 = this.I;
        eb02.d.show();
        eb02.e.requestFocus();
    }
}
