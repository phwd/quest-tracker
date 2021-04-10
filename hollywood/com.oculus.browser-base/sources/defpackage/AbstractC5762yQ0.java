package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import com.oculus.browser.R;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: yQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5762yQ0 {

    /* renamed from: a  reason: collision with root package name */
    public static Bitmap f11679a;
    public static String b;
    public static C3542lO c;
    public static KN0 d;
    public static int e;
    public static int f;
    public static C5592xQ0 g = new C5592xQ0();

    public static boolean a(AbstractC4422qa0 qa0) {
        return qa0 != null && AbstractC5154ur1.g(qa0.i());
    }

    public static int b(Resources resources) {
        if (e == 0) {
            if (d()) {
                e = resources.getDimensionPixelSize(R.dimen.f23170_resource_name_obfuscated_RES_2131165936);
            } else {
                if (f == 0) {
                    f = resources.getDimensionPixelSize(R.dimen.f23160_resource_name_obfuscated_RES_2131165935);
                }
                e = f;
            }
        }
        return e;
    }

    public static String c(TemplateUrlService templateUrlService) {
        String Mweksmrf = N.Mweksmrf(templateUrlService.c, templateUrlService, "replace_me", null);
        if (Mweksmrf == null || !AbstractC5154ur1.d(Mweksmrf)) {
            return Mweksmrf;
        }
        Uri parse = Uri.parse(Mweksmrf);
        StringBuilder sb = new StringBuilder();
        sb.append(parse.getScheme());
        sb.append("://");
        String str = "";
        sb.append(parse.getHost() != null ? parse.getHost() : str);
        if (parse.getPort() != -1) {
            StringBuilder i = AbstractC2531fV.i(":");
            i.append(parse.getPort());
            str = i.toString();
        }
        sb.append(str);
        return sb.toString();
    }

    public static boolean d() {
        if (!g.b(false) || !AbstractC4226pO.a() || !N.M6bsIDpc("OmniboxSearchEngineLogo", "rounded_edges", false)) {
            return false;
        }
        return true;
    }

    public static boolean e() {
        return g.a();
    }

    public static void f(int i) {
        AbstractC3364kK0.g("AndroidSearchEngineLogo.Events", i, 6);
    }

    public static boolean g(boolean z) {
        return g.b(z);
    }
}
