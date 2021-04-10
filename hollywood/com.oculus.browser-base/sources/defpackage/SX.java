package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.Iterator;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.prefs.PrefService;

/* renamed from: SX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SX implements UE0 {

    /* renamed from: a  reason: collision with root package name */
    public static SX f8897a;
    public boolean b;
    public String c;
    public boolean d = false;
    public PrefChangeRegistrar e = null;
    public final PU0 f;
    public final C1322Vq0 g = new C1322Vq0();

    public SX() {
        PU0 pu0 = NU0.f8549a;
        this.f = pu0;
        String i = pu0.i("Chrome.Policy.HomepageLocation", "");
        this.c = i;
        this.b = !TextUtils.isEmpty(i);
        C1321Vq.b().i(new RX(this));
    }

    public static String b() {
        return c().c;
    }

    public static SX c() {
        if (f8897a == null) {
            f8897a = new SX();
        }
        return f8897a;
    }

    public static boolean d() {
        return c().b;
    }

    @Override // defpackage.UE0
    public void a() {
        e();
    }

    public final void e() {
        PrefService a2 = Wr1.a(Profile.b());
        boolean MrEgF7hX = N.MrEgF7hX(a2.f10883a, "homepage");
        String Ma80fvz5 = MrEgF7hX ? N.Ma80fvz5(a2.f10883a, "homepage") : "";
        if (MrEgF7hX != this.b || !Ma80fvz5.equals(this.c)) {
            this.b = MrEgF7hX;
            this.c = Ma80fvz5;
            this.f.p("Chrome.Policy.HomepageLocation", Ma80fvz5);
            Iterator it = this.g.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((QX) uq0.next()).h();
                } else {
                    return;
                }
            }
        }
    }
}
