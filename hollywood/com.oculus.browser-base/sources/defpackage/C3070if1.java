package defpackage;

import java.util.Arrays;

/* renamed from: if1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3070if1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C3070if1 f10154a;
    public static final C3070if1 b;
    public static final C3070if1 c;
    public static final C3070if1 d;
    public static final C3070if1 e;
    public static final C3070if1 f;
    public static final C3070if1 g;
    public static final C3070if1 h;
    public static final C3070if1 i;
    public int j;
    public boolean k;
    public boolean l;
    public byte m;
    public byte[] n;
    public boolean o;

    static {
        C3070if1 b2 = new C3070if1().b(0);
        f10154a = b2;
        b = b2.a();
        C3070if1 b3 = new C3070if1().b(1);
        c = b3;
        d = b3.a();
        C3070if1 b4 = new C3070if1().b(2);
        e = b4;
        f = b4.a();
        C3070if1 if1 = new C3070if1();
        g = if1;
        if1.o = true;
        C3070if1 b5 = new C3070if1().c().b(2);
        h = b5;
        b5.b(2);
        i = b5.b(1);
        b5.b(0);
    }

    public C3070if1() {
        this.j = 2;
    }

    public C3070if1 a() {
        C3070if1 if1 = new C3070if1(this);
        if1.k = true;
        return if1;
    }

    public C3070if1 b(int i2) {
        C3070if1 if1 = new C3070if1(this);
        if1.j = i2;
        return if1;
    }

    public C3070if1 c() {
        C3070if1 if1 = new C3070if1(this);
        if1.l = true;
        return if1;
    }

    public C3070if1 d() {
        if (!this.l) {
            if (!(this.m != 0)) {
                C3070if1 if1 = new C3070if1(this);
                if1.l = true;
                return if1;
            }
        }
        return this;
    }

    public C3070if1 e(C1905bp1 bp1, Object obj) {
        byte[] bArr = new byte[8];
        bArr[2] = 1;
        bArr[1] = (byte) ((C2076cp1) obj).g;
        C3070if1 if1 = new C3070if1(this);
        if1.m = (byte) 1;
        if1.n = bArr;
        return if1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C3070if1)) {
            return false;
        }
        C3070if1 if1 = (C3070if1) obj;
        return this.j == if1.j && this.k == if1.k && this.l == if1.l && this.m == if1.m && Arrays.equals(this.n, if1.n) && this.o == if1.o;
    }

    public int hashCode() {
        return ((Arrays.hashCode(this.n) + ((((((((1147 + this.j) * 37) + (!this.k ? 1 : 0)) * 37) + (!this.l ? 1 : 0)) * 37) + this.m) * 37)) * 37) + (!this.o ? 1 : 0);
    }

    public C3070if1(C3070if1 if1) {
        this.j = if1.j;
        this.k = if1.k;
        this.l = if1.l;
        this.m = if1.m;
        this.n = if1.n;
    }
}
