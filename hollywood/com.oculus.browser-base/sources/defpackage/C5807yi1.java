package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: yi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5807yi1 implements AbstractC1829bL0 {

    /* renamed from: a  reason: collision with root package name */
    public final Q31 f11695a;

    public C5807yi1(Q31 q31) {
        this.f11695a = q31;
    }

    @Override // defpackage.AbstractC1829bL0
    public Object a(ViewGroup viewGroup, int i) {
        View r = AbstractC2531fV.r(viewGroup, R.layout.f41040_resource_name_obfuscated_RES_2131624413, viewGroup, false);
        r.getLayoutParams().width = ((C4276pi1) this.f11695a.get()).f11083a;
        r.getLayoutParams().height = ((C4276pi1) this.f11695a.get()).f11083a;
        return new C5637xi1(r);
    }
}
