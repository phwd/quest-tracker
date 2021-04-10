package defpackage;

import android.view.View;
import org.chromium.chrome.browser.ui.tablet.emptybackground.EmptyBackgroundViewTablet;

/* renamed from: GK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GK implements View.OnClickListener {
    public final /* synthetic */ EmptyBackgroundViewTablet F;

    public GK(EmptyBackgroundViewTablet emptyBackgroundViewTablet) {
        this.F = emptyBackgroundViewTablet;
    }

    public void onClick(View view) {
        EmptyBackgroundViewTablet emptyBackgroundViewTablet = this.F;
        if (emptyBackgroundViewTablet.H != null) {
            ((AbstractC0246Ea1) emptyBackgroundViewTablet.G).l(false).d();
            this.F.H.e();
        }
    }
}
