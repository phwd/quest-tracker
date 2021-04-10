package defpackage;

import org.chromium.base.ThreadUtils;

/* renamed from: Kd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0621Kd1 {

    /* renamed from: a  reason: collision with root package name */
    public static C0560Jd1 f8376a;

    public static C0560Jd1 a() {
        Object obj = ThreadUtils.f10596a;
        if (f8376a == null) {
            f8376a = new C0560Jd1(new WD(), AbstractC1341Wa.f9155a);
        }
        return f8376a;
    }
}
