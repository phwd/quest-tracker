package defpackage;

import J.N;
import android.content.DialogInterface;
import org.chromium.chrome.browser.login.ChromeHttpAuthHandler;

/* renamed from: bb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnClickListenerC1862bb0 implements DialogInterface.OnClickListener {
    public final C2374eb0 F;

    public DialogInterface$OnClickListenerC1862bb0(C2374eb0 eb0) {
        this.F = eb0;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ChromeHttpAuthHandler chromeHttpAuthHandler = (ChromeHttpAuthHandler) this.F.c;
        N.MbTC7yfl(chromeHttpAuthHandler.F, chromeHttpAuthHandler);
    }
}
