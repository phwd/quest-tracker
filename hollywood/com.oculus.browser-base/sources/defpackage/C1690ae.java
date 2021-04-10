package defpackage;

import android.os.Handler;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* renamed from: ae  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1690ae implements AbstractC1521Yy0 {

    /* renamed from: a  reason: collision with root package name */
    public Handler f9439a;

    public static Set b(Map map) {
        if (!map.containsKey("basic-card")) {
            return Collections.emptySet();
        }
        return AbstractC1054Rg.a((C1401Wz0) map.get("basic-card"));
    }

    @Override // defpackage.AbstractC1521Yy0
    public void a(AbstractC1460Xy0 xy0) {
        C1533Zd zd = new C1533Zd(xy0, null);
        if (this.f9439a == null) {
            this.f9439a = new Handler();
        }
        this.f9439a.post(new RunnableC1350Wd(this, zd, xy0));
    }
}
