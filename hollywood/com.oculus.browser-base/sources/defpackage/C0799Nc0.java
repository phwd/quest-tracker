package defpackage;

import android.view.View;
import com.oculus.browser.R;

/* renamed from: Nc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0799Nc0 extends C5349w {
    public final /* synthetic */ C1104Sc0 d;

    public C0799Nc0(C1104Sc0 sc0) {
        this.d = sc0;
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        String str;
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        if (this.d.I0.getVisibility() == 0) {
            str = this.d.O(R.string.f55730_resource_name_obfuscated_RES_2131952890);
        } else {
            str = this.d.O(R.string.f55710_resource_name_obfuscated_RES_2131952888);
        }
        d2.k(str);
    }
}
