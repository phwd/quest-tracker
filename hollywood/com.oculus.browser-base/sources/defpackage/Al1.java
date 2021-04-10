package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: Al1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Al1 extends AbstractView$OnKeyListenerC2810h60 {
    public final /* synthetic */ ToolbarTablet F;

    public Al1(ToolbarTablet toolbarTablet) {
        this.F = toolbarTablet;
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View a() {
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
        return this.F.findViewById(R.id.refresh_button);
    }
}
