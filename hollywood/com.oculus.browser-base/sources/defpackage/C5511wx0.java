package defpackage;

import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: wx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5511wx0 implements AbstractC1829bL0 {
    @Override // defpackage.AbstractC1829bL0
    public Object a(ViewGroup viewGroup, int i) {
        Object obj;
        if (i == 1) {
            obj = new A0(viewGroup, R.layout.f39020_resource_name_obfuscated_RES_2131624211);
        } else if (i == 2) {
            obj = new C0117Bx0(viewGroup);
        } else if (i == 6 || i == 8) {
            return B0.a(viewGroup, i);
        } else {
            return null;
        }
        return obj;
    }
}
