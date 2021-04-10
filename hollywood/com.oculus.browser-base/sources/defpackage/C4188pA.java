package defpackage;

import J.N;
import android.net.Uri;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.url.GURL;

/* renamed from: pA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4188pA {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11054a;
    public Uri b;
    public Uri c;
    public boolean d;
    public boolean e;
    public boolean f;

    public C4188pA(String str, String str2, String str3, boolean z, String str4, String str5) {
        Uri uri;
        this.f11054a = z;
        boolean z2 = false;
        boolean z3 = !TextUtils.isEmpty(str4) && N.Mn0fHwI$(str4);
        this.f = z3;
        if (z3) {
            uri = Uri.parse(str4);
        } else {
            uri = d(str, str2, str3, false);
        }
        this.c = uri;
        if (z) {
            if (!TextUtils.isEmpty(str5) && N.Mn0fHwI$(str5)) {
                z2 = true;
            }
            if (z2) {
                this.b = Uri.parse(str5);
            } else {
                Uri d2 = d(str, str2, str3, true);
                if (d2.getPath() != null && d2.getPath().contains("search")) {
                    d2 = d2.buildUpon().path("s").appendQueryParameter("sns", "1").build();
                }
                this.b = d2;
            }
        } else {
            this.b = null;
        }
        this.d = z;
    }

    public void a(String str, String str2) {
        if (!this.f && !TextUtils.isEmpty(str2) && !str2.equals(str)) {
            Uri uri = this.b;
            if (uri != null) {
                this.b = e(uri, str, str2);
            }
            this.c = e(this.c, str, str2);
        }
    }

    public String b() {
        Uri uri;
        if (!this.d || (uri = this.b) == null) {
            return this.c.toString();
        }
        return uri.toString();
    }

    public String c() {
        URL url;
        this.d = false;
        try {
            url = new URL(b().replaceAll("(ctxs=[^&]+)", "ctxr").replaceAll("(\\&pf=\\w)", ""));
        } catch (MalformedURLException unused) {
            url = null;
        }
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    public Uri d(String str, String str2, String str3, boolean z) {
        TemplateUrlService a2 = AbstractC0444Hf1.a();
        Uri parse = Uri.parse(((GURL) N.MBQwEcmT(a2.c, a2, str, str2, z, "2")).h());
        return !TextUtils.isEmpty(str3) ? parse.buildUpon().appendQueryParameter("kgmid", str3).build() : parse;
    }

    public final Uri e(Uri uri, String str, String str2) {
        Uri.Builder buildUpon = uri.buildUpon();
        buildUpon.appendQueryParameter("ctxsl_trans", "1");
        if (!str.isEmpty()) {
            buildUpon.appendQueryParameter("tlitesl", str);
        }
        if (!str2.isEmpty()) {
            buildUpon.appendQueryParameter("tlitetl", str2);
        }
        buildUpon.appendQueryParameter("tlitetxt", uri.getQueryParameter("q"));
        return buildUpon.build();
    }
}
