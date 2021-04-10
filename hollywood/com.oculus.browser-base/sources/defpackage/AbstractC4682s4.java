package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.base.Callback;

/* renamed from: s4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4682s4 {

    /* renamed from: a  reason: collision with root package name */
    public static final OH0 f11249a;
    public static final OH0 b;
    public static final MH0 c;
    public static final KH0[] d;

    static {
        OH0 oh0 = new OH0("credential");
        f11249a = oh0;
        OH0 oh02 = new OH0("on_click_listener");
        b = oh02;
        MH0 mh0 = new MH0("is_password_field");
        c = mh0;
        d = new KH0[]{oh0, oh02, mh0};
    }

    public static UH0 a(C2824hB hBVar, Callback callback, boolean z) {
        Map c2 = UH0.c(d);
        OH0 oh0 = f11249a;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = hBVar;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(oh0, lh0);
        OH0 oh02 = b;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = callback;
        hashMap.put(oh02, lh02);
        MH0 mh0 = c;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = z;
        hashMap.put(mh0, gh0);
        return new UH0(c2, null);
    }
}
