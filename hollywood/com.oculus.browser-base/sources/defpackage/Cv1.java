package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: Cv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Cv1 extends AbstractC3094in1 {
    public final /* synthetic */ ViewGroup F;
    public final /* synthetic */ View G;
    public final /* synthetic */ View H;
    public final /* synthetic */ UN I;

    public Cv1(UN un, ViewGroup viewGroup, View view, View view2) {
        this.I = un;
        this.F = viewGroup;
        this.G = view;
        this.H = view2;
    }

    @Override // defpackage.AbstractC2753gn1, defpackage.AbstractC3094in1
    public void b(AbstractC2924hn1 hn1) {
        if (this.G.getParent() == null) {
            this.F.getOverlay().add(this.G);
        } else {
            this.I.d();
        }
    }

    @Override // defpackage.AbstractC2753gn1
    public void c(AbstractC2924hn1 hn1) {
        this.H.setTag(R.id.save_overlay_view, null);
        this.F.getOverlay().remove(this.G);
        hn1.w(this);
    }

    @Override // defpackage.AbstractC2753gn1, defpackage.AbstractC3094in1
    public void d(AbstractC2924hn1 hn1) {
        this.F.getOverlay().remove(this.G);
    }
}
