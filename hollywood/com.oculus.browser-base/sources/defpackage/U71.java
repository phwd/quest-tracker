package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: U71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class U71 implements View.OnClickListener {
    public final C2475f81 F;

    public U71(C2475f81 f81) {
        this.F = f81;
    }

    public void onClick(View view) {
        C2475f81 f81 = this.F;
        f81.g(-1);
        boolean z = false;
        AbstractC3293jx.e(0);
        AbstractC3535lK0.a("TabStrip.UserDismissed");
        int f = NU0.f8549a.f("Chrome.ConditionalTabStrip.ContinuousDismissCounter", 0);
        if (f == -1 || (f + 1) % AbstractC3293jx.c.c() != 0) {
            z = true;
        }
        if (z) {
            f81.n.U().l(f81.o);
            return;
        }
        SimpleConfirmInfoBarBuilder.a(((AbstractC0246Ea1) f81.e).j().l(), new C2134d81(f81), 99, f81.f9901a, 0, f81.f9901a.getString(R.string.f63200_resource_name_obfuscated_RES_2131953637), f81.f9901a.getString(R.string.f63210_resource_name_obfuscated_RES_2131953638), f81.f9901a.getString(R.string.f63190_resource_name_obfuscated_RES_2131953636), null, true);
    }
}
