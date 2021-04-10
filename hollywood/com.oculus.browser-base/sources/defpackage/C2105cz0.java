package defpackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* renamed from: cz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2105cz0 implements AbstractC1521Yy0 {

    /* renamed from: a  reason: collision with root package name */
    public static C2105cz0 f9736a;
    public final Map b = new HashMap();

    @Override // defpackage.AbstractC1521Yy0
    public void a(AbstractC1460Xy0 xy0) {
        C1934bz0 bz0 = new C1934bz0(this, new HashSet(this.b.values()), xy0, null);
        for (AbstractC1521Yy0 yy0 : this.b.values()) {
            yy0.a(bz0);
        }
    }

    public void b(AbstractC1521Yy0 yy0, String str) {
        if (!this.b.containsKey(str)) {
            this.b.put(str, yy0);
        }
    }
}
