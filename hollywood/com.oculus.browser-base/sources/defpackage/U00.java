package defpackage;

import android.view.View;
import org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButtonTablet;

/* renamed from: U00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U00 implements View.OnClickListener {
    public final /* synthetic */ IncognitoToggleButtonTablet F;

    public U00(IncognitoToggleButtonTablet incognitoToggleButtonTablet) {
        this.F = incognitoToggleButtonTablet;
    }

    public void onClick(View view) {
        AbstractC0124Ca1 ca1 = this.F.H;
        if (ca1 != null) {
            ca1.e(!((AbstractC0246Ea1) ca1).r());
        }
    }
}
