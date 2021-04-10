package defpackage;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* renamed from: QX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QX {

    /* renamed from: a  reason: collision with root package name */
    public static QX f8766a;
    public final PU0 b = NU0.f8549a;
    public final C1322Vq0 c = new C1322Vq0();
    public C2528fT0 d;

    public QX() {
        SX.c().g.b(this);
        PartnerBrowserCustomizations.c().g = this;
        this.d = new C2528fT0();
    }

    public static String a() {
        return PartnerBrowserCustomizations.c().e() ? PartnerBrowserCustomizations.c().b() : "chrome://newtab/";
    }

    public static String b() {
        String str;
        QX c2 = c();
        Objects.requireNonNull(c2);
        if (SX.d()) {
            str = SX.b();
        } else if (c2.e()) {
            str = "chrome://newtab/";
        } else if (c2.f()) {
            str = a();
        } else {
            str = c2.d();
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public static QX c() {
        if (f8766a == null) {
            f8766a = new QX();
        }
        return f8766a;
    }

    public static boolean g() {
        if (SX.d() || c().b.d("homepage", true)) {
            return true;
        }
        return false;
    }

    public static boolean i() {
        return g() && !AbstractC5154ur1.g(b());
    }

    public String d() {
        return this.b.i("homepage_custom_uri", "");
    }

    public boolean e() {
        return this.b.d("Chrome.Homepage.UseNTP", false);
    }

    public boolean f() {
        return this.b.d("homepage_partner_enabled", true);
    }

    public void h() {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((C2744gk1) uq0.next()).a();
            } else {
                return;
            }
        }
    }
}
