package defpackage;

import org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButtonTablet;

/* renamed from: W00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W00 implements Runnable {
    public final /* synthetic */ IncognitoToggleButtonTablet F;

    public W00(IncognitoToggleButtonTablet incognitoToggleButtonTablet) {
        this.F = incognitoToggleButtonTablet;
    }

    public void run() {
        IncognitoToggleButtonTablet incognitoToggleButtonTablet = this.F;
        incognitoToggleButtonTablet.setVisibility(((AbstractC0246Ea1) incognitoToggleButtonTablet.H).l(true).getCount() > 0 ? 0 : 8);
    }
}
