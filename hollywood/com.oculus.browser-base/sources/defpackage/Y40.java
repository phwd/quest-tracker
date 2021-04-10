package defpackage;

import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: Y40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Y40 implements AbstractC1829bL0 {
    @Override // defpackage.AbstractC1829bL0
    public Object a(ViewGroup viewGroup, int i) {
        Object obj;
        if (i == 0) {
            obj = new C1956c60(viewGroup, R.layout.f38920_resource_name_obfuscated_RES_2131624201);
        } else if (i == 1) {
            obj = new D50(viewGroup);
        } else if (i != 2) {
            return AbstractC2297e60.b(viewGroup, i);
        } else {
            obj = new E50(viewGroup);
        }
        return obj;
    }
}
