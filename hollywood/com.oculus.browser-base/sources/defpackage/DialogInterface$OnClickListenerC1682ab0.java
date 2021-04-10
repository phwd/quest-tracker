package defpackage;

import J.N;
import android.content.DialogInterface;
import org.chromium.chrome.browser.login.ChromeHttpAuthHandler;

/* renamed from: ab0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnClickListenerC1682ab0 implements DialogInterface.OnClickListener {
    public final C2374eb0 F;

    public DialogInterface$OnClickListenerC1682ab0(C2374eb0 eb0) {
        this.F = eb0;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        C2374eb0 eb0 = this.F;
        AbstractC2204db0 db0 = eb0.c;
        ChromeHttpAuthHandler chromeHttpAuthHandler = (ChromeHttpAuthHandler) db0;
        N.MAMBiVB$(chromeHttpAuthHandler.F, chromeHttpAuthHandler, eb0.e.getText().toString(), eb0.f.getText().toString());
    }
}
