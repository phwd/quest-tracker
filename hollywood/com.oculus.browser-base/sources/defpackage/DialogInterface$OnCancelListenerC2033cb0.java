package defpackage;

import J.N;
import android.content.DialogInterface;
import org.chromium.chrome.browser.login.ChromeHttpAuthHandler;

/* renamed from: cb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnCancelListenerC2033cb0 implements DialogInterface.OnCancelListener {
    public final C2374eb0 F;

    public DialogInterface$OnCancelListenerC2033cb0(C2374eb0 eb0) {
        this.F = eb0;
    }

    public void onCancel(DialogInterface dialogInterface) {
        ChromeHttpAuthHandler chromeHttpAuthHandler = (ChromeHttpAuthHandler) this.F.c;
        N.MbTC7yfl(chromeHttpAuthHandler.F, chromeHttpAuthHandler);
    }
}
