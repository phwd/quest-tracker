package defpackage;

import android.view.View;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: AM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AM implements View.OnAttachStateChangeListener {
    public final /* synthetic */ AbstractC5127ui1 F;
    public final /* synthetic */ Profile G;
    public final /* synthetic */ ChromeActivity H;

    public AM(AbstractC5127ui1 ui1, Profile profile, ChromeActivity chromeActivity) {
        this.F = ui1;
        this.G = profile;
        this.H = chromeActivity;
    }

    public void onViewAttachedToWindow(View view) {
        CM.a(this.F, this.G, this.H);
    }

    public void onViewDetachedFromWindow(View view) {
    }
}
