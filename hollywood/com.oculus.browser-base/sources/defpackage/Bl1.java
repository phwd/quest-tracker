package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: Bl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Bl1 extends AbstractView$OnKeyListenerC2810h60 {
    public final /* synthetic */ ToolbarTablet F;

    public Bl1(ToolbarTablet toolbarTablet) {
        this.F = toolbarTablet;
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View a() {
        if (this.F.a0.isFocusable()) {
            return this.F.a0;
        }
        if (this.F.W.isFocusable()) {
            return this.F.W;
        }
        if (this.F.V.getVisibility() == 0) {
            return this.F.findViewById(R.id.home_button);
        }
        return this.F.findViewById(R.id.menu_button);
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View b() {
        return this.F.findViewById(R.id.url_bar);
    }
}
