package org.chromium.url;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Parsed {

    /* renamed from: a  reason: collision with root package name */
    public final int f11031a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    public final int n;
    public final int o;
    public final int p;
    public final Parsed q;
    public final boolean r;

    public Parsed(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, boolean z, Parsed parsed) {
        this.f11031a = i2;
        this.b = i3;
        this.c = i4;
        this.d = i5;
        this.e = i6;
        this.f = i7;
        this.g = i8;
        this.h = i9;
        this.i = i10;
        this.j = i11;
        this.k = i12;
        this.l = i13;
        this.m = i14;
        this.n = i15;
        this.o = i16;
        this.p = i17;
        this.r = z;
        this.q = parsed;
    }

    public static Parsed a(String[] strArr, int i2) {
        int i3 = i2 + 1;
        int parseInt = Integer.parseInt(strArr[i2]);
        int i4 = i3 + 1;
        int parseInt2 = Integer.parseInt(strArr[i3]);
        int i5 = i4 + 1;
        int parseInt3 = Integer.parseInt(strArr[i4]);
        int i6 = i5 + 1;
        int parseInt4 = Integer.parseInt(strArr[i5]);
        int i7 = i6 + 1;
        int parseInt5 = Integer.parseInt(strArr[i6]);
        int i8 = i7 + 1;
        int parseInt6 = Integer.parseInt(strArr[i7]);
        int i9 = i8 + 1;
        int parseInt7 = Integer.parseInt(strArr[i8]);
        int i10 = i9 + 1;
        int parseInt8 = Integer.parseInt(strArr[i9]);
        int i11 = i10 + 1;
        int parseInt9 = Integer.parseInt(strArr[i10]);
        int i12 = i11 + 1;
        int parseInt10 = Integer.parseInt(strArr[i11]);
        int i13 = i12 + 1;
        int parseInt11 = Integer.parseInt(strArr[i12]);
        int i14 = i13 + 1;
        int parseInt12 = Integer.parseInt(strArr[i13]);
        int i15 = i14 + 1;
        int parseInt13 = Integer.parseInt(strArr[i14]);
        int i16 = i15 + 1;
        int parseInt14 = Integer.parseInt(strArr[i15]);
        int i17 = i16 + 1;
        int parseInt15 = Integer.parseInt(strArr[i16]);
        int i18 = i17 + 1;
        int parseInt16 = Integer.parseInt(strArr[i17]);
        int i19 = i18 + 1;
        return new Parsed(parseInt, parseInt2, parseInt3, parseInt4, parseInt5, parseInt6, parseInt7, parseInt8, parseInt9, parseInt10, parseInt11, parseInt12, parseInt13, parseInt14, parseInt15, parseInt16, Boolean.parseBoolean(strArr[i18]), Boolean.parseBoolean(strArr[i19]) ? a(strArr, i19 + 1) : null);
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f11031a);
        sb.append((char) 0);
        sb.append(this.b);
        sb.append((char) 0);
        sb.append(this.c);
        sb.append((char) 0);
        sb.append(this.d);
        sb.append((char) 0);
        sb.append(this.e);
        sb.append((char) 0);
        sb.append(this.f);
        sb.append((char) 0);
        sb.append(this.g);
        sb.append((char) 0);
        sb.append(this.h);
        sb.append((char) 0);
        sb.append(this.i);
        sb.append((char) 0);
        sb.append(this.j);
        sb.append((char) 0);
        sb.append(this.k);
        sb.append((char) 0);
        sb.append(this.l);
        sb.append((char) 0);
        sb.append(this.m);
        sb.append((char) 0);
        sb.append(this.n);
        sb.append((char) 0);
        sb.append(this.o);
        sb.append((char) 0);
        sb.append(this.p);
        sb.append((char) 0);
        sb.append(this.r);
        sb.append((char) 0);
        sb.append(this.q != null);
        if (this.q != null) {
            sb.append((char) 0);
            sb.append(this.q.b());
        }
        return sb.toString();
    }

    public long c() {
        Parsed parsed = this.q;
        return N.MsTyiXfW(this.f11031a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.r, parsed != null ? parsed.c() : 0);
    }
}
