package defpackage;

import org.chromium.chrome.browser.incognito.IncognitoTabLauncher;

/* renamed from: L00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class L00 implements Runnable {
    public final boolean F;

    public L00(boolean z) {
        this.F = z;
    }

    public void run() {
        IncognitoTabLauncher.b(this.F);
    }
}
