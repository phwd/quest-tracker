package defpackage;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* renamed from: hF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2838hF1 {

    /* renamed from: a  reason: collision with root package name */
    public final NF1 f10060a;
    public final String b;
    public IL0 c;
    public final List d;

    public AbstractC2838hF1(String str, String str2) {
        GF1.d(str);
        this.b = str;
        NF1 nf1 = new NF1(str2);
        this.f10060a = nf1;
        String str3 = null;
        if (!TextUtils.isEmpty(null)) {
            if (!TextUtils.isEmpty(null)) {
                str3 = String.format("[%s] ", null);
            }
            nf1.c = str3;
        }
        this.d = Collections.synchronizedList(new ArrayList());
    }

    public final void a(String str, long j, String str2) {
        IL0 il0 = this.c;
        String str3 = this.b;
        YV yv = il0.f8220a;
        if (yv != null) {
            Objects.requireNonNull(il0.c.e);
            yv.f(new C3353kG1(yv, str3, str)).b(new ZD1(il0, j));
            return;
        }
        throw new IllegalStateException("No GoogleApiClient available");
    }

    public final long b() {
        IL0 il0 = this.c;
        long j = il0.b + 1;
        il0.b = j;
        return j;
    }
}
