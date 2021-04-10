package defpackage;

import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: nB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3849nB implements AbstractC1829bL0 {
    @Override // defpackage.AbstractC1829bL0
    public Object a(ViewGroup viewGroup, int i) {
        Object obj;
        if (i != 1) {
            if (i == 4) {
                obj = new C4191pB(viewGroup);
                return obj;
            } else if (i == 6) {
                return B0.a(viewGroup, i);
            } else {
                if (i != 7) {
                    return null;
                }
            }
        }
        obj = new A0(viewGroup, R.layout.f39020_resource_name_obfuscated_RES_2131624211);
        return obj;
    }
}
