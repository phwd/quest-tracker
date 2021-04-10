package org.chromium.url;

import J.N;
import android.os.SystemClock;
import android.text.TextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GURL {

    /* renamed from: a  reason: collision with root package name */
    public String f11029a;
    public boolean b;
    public Parsed c;

    public GURL(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f11029a = "";
            this.c = new Parsed(0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, false, null);
            return;
        }
        b();
        N.MWBVWQ0I(str, this);
    }

    public static GURL a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return emptyGURL();
            }
            String[] split = str.split(Character.toString(0));
            String str2 = split[0];
            if (str.length() == Integer.parseInt(str2) + str2.length() + 1) {
                String str3 = split[split.length - 1];
                if (str.endsWith(Character.toString(0))) {
                    str3 = "";
                }
                if (Integer.parseInt(split[1]) != 1) {
                    return new GURL(str3);
                }
                boolean parseBoolean = Boolean.parseBoolean(split[2]);
                Parsed a2 = Parsed.a(split, 3);
                GURL gurl = new GURL();
                gurl.init(str3, parseBoolean, a2);
                return gurl;
            }
            throw new IllegalArgumentException("Serialized GURL had the wrong length.");
        } catch (Exception e) {
            AbstractC1220Ua0.f("GURL", AbstractC2531fV.f("Exception while deserializing a GURL: ", str), e);
            return emptyGURL();
        }
    }

    public static void b() {
        if (!C2474f80.f9900a.f()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            C2474f80.f9900a.c();
            if (ThreadUtils.i()) {
                AbstractC3364kK0.k("Startup.Android.GURLEnsureMainDexInitialized", SystemClock.elapsedRealtime() - elapsedRealtime);
            }
        }
    }

    public static GURL emptyGURL() {
        return AbstractC5429wU.f11547a;
    }

    public static boolean k(GURL gurl) {
        return gurl == null || gurl.j() || !gurl.b;
    }

    public final String c(int i, int i2) {
        return i2 <= 0 ? "" : this.f11029a.substring(i, i2 + i);
    }

    public String d() {
        Parsed parsed = this.c;
        return c(parsed.g, parsed.h);
    }

    public GURL e() {
        GURL gurl = new GURL();
        N.MNBd3mFA(this.f11029a, this.b, this.c.c(), gurl);
        return gurl;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GURL)) {
            return false;
        }
        return this.f11029a.equals(((GURL) obj).f11029a);
    }

    public String f() {
        Parsed parsed = this.c;
        return c(parsed.k, parsed.l);
    }

    public String g() {
        Parsed parsed = this.c;
        return c(parsed.f11031a, parsed.b);
    }

    public String h() {
        return (this.b || this.f11029a.isEmpty()) ? this.f11029a : "";
    }

    public final int hashCode() {
        return this.f11029a.hashCode();
    }

    public String i() {
        return this.b ? this.f11029a : "";
    }

    public final void init(String str, boolean z, Parsed parsed) {
        this.f11029a = str;
        this.b = z;
        this.c = parsed;
    }

    public boolean j() {
        return this.f11029a.isEmpty();
    }

    public final String l() {
        String str = 1 + (char) 0 + this.b + (char) 0 + this.c.b() + (char) 0 + this.f11029a;
        return Integer.toString(str.length()) + (char) 0 + str;
    }

    public final long toNativeGURL() {
        return N.MnPIH$$1(this.f11029a, this.b, this.c.c());
    }

    public GURL() {
    }
}
