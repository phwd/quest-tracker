package defpackage;

import android.content.res.Resources;
import java.util.Map;

/* renamed from: HH0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HH0 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f8149a;

    public HH0(KH0... kh0Arr) {
        this.f8149a = UH0.c(kh0Arr);
    }

    public UH0 a() {
        return new UH0(this.f8149a, null);
    }

    public HH0 b(MH0 mh0, boolean z) {
        GH0 gh0 = new GH0(null);
        gh0.f8081a = z;
        this.f8149a.put(mh0, gh0);
        return this;
    }

    public HH0 c(NH0 nh0, int i) {
        JH0 jh0 = new JH0(null);
        jh0.f8282a = i;
        this.f8149a.put(nh0, jh0);
        return this;
    }

    public HH0 d(OH0 oh0, Resources resources, int i) {
        if (i != 0) {
            e(oh0, resources.getString(i));
        }
        return this;
    }

    public HH0 e(OH0 oh0, Object obj) {
        LH0 lh0 = new LH0(null);
        lh0.f8415a = obj;
        this.f8149a.put(oh0, lh0);
        return this;
    }

    public HH0 f(RH0 rh0, float f) {
        IH0 ih0 = new IH0(null);
        ih0.f8214a = f;
        this.f8149a.put(rh0, ih0);
        return this;
    }
}
