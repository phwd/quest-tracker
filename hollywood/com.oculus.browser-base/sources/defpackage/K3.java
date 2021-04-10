package defpackage;

import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: K3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class K3 implements AbstractC1829bL0 {
    @Override // defpackage.AbstractC1829bL0
    public Object a(ViewGroup viewGroup, int i) {
        Object obj;
        if (i == 1) {
            obj = new A0(viewGroup, R.layout.f39020_resource_name_obfuscated_RES_2131624211);
        } else if (i == 3) {
            obj = new N3(viewGroup);
        } else if (i != 6) {
            return null;
        } else {
            return B0.a(viewGroup, i);
        }
        return obj;
    }
}
