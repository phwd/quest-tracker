package defpackage;

import java.util.Comparator;

/* renamed from: hx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2954hx1 implements Comparable {
    public static Comparator F = new C2783gx1();
    public String G;
    public int H;
    public int I = 0;

    /* renamed from: J  reason: collision with root package name */
    public int f10113J;

    public C2954hx1(String str, int i, int i2) {
        this.G = str;
        this.H = i;
        this.f10113J = i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return this.G.compareTo(((C2954hx1) obj).G);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C2954hx1)) {
            return false;
        }
        return this.G.equals(((C2954hx1) obj).G);
    }

    public int hashCode() {
        return this.G.hashCode();
    }
}
