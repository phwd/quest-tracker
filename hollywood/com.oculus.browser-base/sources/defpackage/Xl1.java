package defpackage;

import android.content.Context;
import android.graphics.RectF;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: Xl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Xl1 implements LO0 {
    public final UH0 F;
    public final C2408em1 G;
    public final C0297Ew H;
    public final C2067cm1 I;

    public Xl1(Context context, AbstractC2642g70 g70, Callback callback, AbstractC0956Pq0 pq0, AbstractC2400ek ekVar, Q31 q31, C2921hm1 hm1) {
        Map c = UH0.c(AbstractC2238dm1.i);
        NH0 nh0 = AbstractC2238dm1.f9805a;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = R.id.control_container;
        HashMap hashMap = (HashMap) c;
        hashMap.put(nh0, jh0);
        NH0 nh02 = AbstractC2238dm1.b;
        JH0 jh02 = new JH0(null);
        jh02.f8282a = R.drawable.f33750_resource_name_obfuscated_RES_2131231415;
        hashMap.put(nh02, jh02);
        QH0 qh0 = AbstractC2238dm1.g;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        RH0 rh0 = AbstractC2238dm1.e;
        IH0 ih0 = new IH0(null);
        ih0.f8214a = (float) ((C1551Zj) ekVar).T;
        hashMap.put(rh0, ih0);
        UH0 uh0 = new UH0(c, null);
        this.F = uh0;
        C2408em1 em1 = new C2408em1(q31);
        this.G = em1;
        this.H = new C0297Ew(uh0, em1, new Wl1(), ((D70) g70).l0, true);
        this.I = new C2067cm1(uh0, context, g70, callback, pq0, ekVar, hm1);
    }

    @Override // defpackage.LO0
    public boolean a() {
        return false;
    }

    @Override // defpackage.LO0
    public VL c() {
        return null;
    }

    @Override // defpackage.LO0
    public boolean e(long j, long j2) {
        return false;
    }

    @Override // defpackage.LO0
    public boolean k() {
        return false;
    }

    @Override // defpackage.LO0
    public void m(float f, float f2, float f3, int i) {
    }

    @Override // defpackage.LO0
    public MO0 q(RectF rectF, RectF rectF2, ResourceManager resourceManager, float f) {
        return this.G;
    }

    @Override // defpackage.LO0
    public void s(List list) {
    }

    @Override // defpackage.LO0
    public boolean u() {
        Objects.requireNonNull(this.I);
        return true;
    }

    @Override // defpackage.LO0
    public boolean v() {
        return false;
    }
}
