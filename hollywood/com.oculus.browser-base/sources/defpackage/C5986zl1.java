package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: zl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5986zl1 extends AbstractView$OnKeyListenerC2810h60 {
    public final /* synthetic */ ToolbarTablet F;

    public C5986zl1(ToolbarTablet toolbarTablet) {
        this.F = toolbarTablet;
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View a() {
        if (this.F.V.getVisibility() == 0) {
            return this.F.findViewById(R.id.home_button);
        }
        return this.F.findViewById(R.id.menu_button);
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View b() {
        if (this.F.a0.isFocusable()) {
            return this.F.findViewById(R.id.forward_button);
        }
        return this.F.findViewById(R.id.refresh_button);
    }
}
