package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* renamed from: gl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2747gl1 extends AbstractView$OnKeyListenerC2810h60 {
    public final /* synthetic */ ToolbarPhone F;

    public C2747gl1(ToolbarPhone toolbarPhone) {
        this.F = toolbarPhone;
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View a() {
        return this.F.findViewById(R.id.url_bar);
    }

    @Override // defpackage.AbstractView$OnKeyListenerC2810h60
    public View b() {
        ToolbarPhone toolbarPhone = this.F;
        int i = ToolbarPhone.U;
        if (toolbarPhone.l0()) {
            return this.F.R.e;
        }
        return this.F.f();
    }
}
