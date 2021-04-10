package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.top.ToolbarTablet;

/* renamed from: Cl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Cl1 extends AbstractView$OnKeyListenerC2810h60 {
    public final /* synthetic */ ToolbarTablet F;

    public Cl1(ToolbarTablet toolbarTablet) {
        this.F = toolbarTablet;
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View a() {
        return this.F.findViewById(R.id.url_bar);
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View b() {
        return this.F.f();
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public boolean c() {
        return this.F.R.c();
    }
}
