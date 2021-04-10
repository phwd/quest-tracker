package defpackage;

import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.LocaleUtils;

/* renamed from: x60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5539x60 extends AbstractC5750yK0 {
    public List I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public List f11588J = new ArrayList();
    public HashSet K;
    public final /* synthetic */ A60 L;

    public C5539x60(A60 a60, HashSet hashSet) {
        this.L = a60;
        this.K = hashSet;
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.f11588J.size() + this.I.size() + 1;
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        return i == this.I.size() ? 1 : 0;
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        B60 b60;
        if (d(i) == 0) {
            if (i < this.I.size()) {
                b60 = (B60) this.I.get(i);
            } else {
                b60 = i > this.I.size() ? (B60) this.f11588J.get((i - this.I.size()) - 1) : null;
            }
            View$OnClickListenerC5369w60 w60 = (View$OnClickListenerC5369w60) xk0;
            String str = b60.b;
            String str2 = b60.c;
            String str3 = b60.f7716a;
            HashSet hashSet = this.K;
            w60.Z.setText(str);
            w60.a0.setText(str2);
            w60.d0 = str3;
            w60.e0 = hashSet;
            w60.b0.setChecked(hashSet.contains(str3));
            w60.c0.setVisibility(LocaleUtils.getDefaultLocaleString().equals(str3) ? 0 : 4);
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new View$OnClickListenerC5369w60(this.L, AbstractC2531fV.r(viewGroup, R.layout.f39060_resource_name_obfuscated_RES_2131624215, viewGroup, false));
        } else if (i == 1) {
            return new C5879z60(this.L, AbstractC2531fV.r(viewGroup, R.layout.f39070_resource_name_obfuscated_RES_2131624216, viewGroup, false));
        } else {
            return null;
        }
    }
}
