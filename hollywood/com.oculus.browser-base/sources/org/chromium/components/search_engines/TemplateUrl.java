package org.chromium.components.search_engines;

import J.N;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TemplateUrl {

    /* renamed from: a  reason: collision with root package name */
    public final long f10886a;

    public TemplateUrl(long j) {
        this.f10886a = j;
    }

    public static TemplateUrl create(long j) {
        return new TemplateUrl(j);
    }

    public boolean a() {
        return N.M_Gh_h3K(this.f10886a);
    }

    public String b() {
        return N.M74Ymq6T(this.f10886a);
    }

    public long c() {
        return N.MzOF1_dp(this.f10886a);
    }

    public String d() {
        return N.M35ewi23(this.f10886a);
    }

    public boolean equals(Object obj) {
        if ((obj instanceof TemplateUrl) && this.f10886a == ((TemplateUrl) obj).f10886a) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format(Locale.US, "TemplateURL -- keyword: %s, short name: %s, prepopulated: %b", b(), d(), Boolean.valueOf(a()));
    }
}
