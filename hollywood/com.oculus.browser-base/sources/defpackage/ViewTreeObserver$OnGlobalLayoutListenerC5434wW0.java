package defpackage;

import android.view.ViewTreeObserver;
import org.chromium.chrome.browser.signin.ui.SigninScrollView;

/* renamed from: wW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ViewTreeObserver$OnGlobalLayoutListenerC5434wW0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final SigninScrollView F;

    public ViewTreeObserver$OnGlobalLayoutListenerC5434wW0(SigninScrollView signinScrollView) {
        this.F = signinScrollView;
    }

    public void onGlobalLayout() {
        this.F.a();
    }
}
