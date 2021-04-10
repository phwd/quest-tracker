package defpackage;

import com.oculus.os.Version;

/* renamed from: Iz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0547Iz {

    /* renamed from: a  reason: collision with root package name */
    public final C2138dA f8261a;
    public final AbstractC0608Jz b;
    public int c;
    public int d = 0;
    public boolean e;
    public int f = 0;

    public C0547Iz(C2138dA dAVar, AbstractC0608Jz jz) {
        this.f8261a = dAVar;
        this.b = jz;
    }

    public void a(int i) {
        this.f = this.d;
        this.d = i;
        if (i == 2) {
            this.c = 2;
        } else if (i == 6) {
            this.c = 1;
        } else if (i != 15) {
            this.c = 0;
        } else {
            this.c = 3;
        }
        this.e = true;
        c(i);
    }

    public boolean b(int i) {
        return this.d == i;
    }

    public void c(int i) {
        int i2 = this.d;
        if (i == i2) {
            if (i2 == 1 || i2 == 0) {
                StringBuilder i3 = AbstractC2531fV.i("Warning, the ");
                i3.append(String.valueOf(i));
                i3.append(" state was aborted.");
                AbstractC1220Ua0.f("ContextualSearch", i3.toString(), new Object[0]);
                return;
            }
            int i4 = 14;
            int i5 = 12;
            int i6 = 8;
            switch (i) {
                case 2:
                    e(9, null);
                    return;
                case 3:
                case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                    return;
                case 4:
                    int i7 = this.f;
                    if (i7 == 0 || i7 == 1) {
                        d(7);
                        return;
                    } else {
                        e(5, null);
                        return;
                    }
                case 5:
                    d(7);
                    return;
                case 6:
                    int i8 = this.f;
                    if (!(i8 == 0 || i8 == 1)) {
                        i6 = 7;
                    }
                    e(i6, null);
                    return;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    e(8, null);
                    return;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    e(9, null);
                    return;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    int i9 = this.c;
                    if (i9 == 2) {
                        e(3, null);
                        return;
                    } else if (i9 == 3) {
                        e(12, null);
                        return;
                    } else {
                        e(10, null);
                        return;
                    }
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    e(11, null);
                    return;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    if (this.f8261a.h()) {
                        i5 = 3;
                    }
                    e(i5, null);
                    return;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    if (this.f8261a.n()) {
                        i4 = 13;
                    }
                    e(i4, null);
                    return;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    if (this.c != 1) {
                        i4 = 16;
                    }
                    e(i4, null);
                    return;
                case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                default:
                    StringBuilder i10 = AbstractC2531fV.i("The state ");
                    i10.append(String.valueOf(i));
                    i10.append(" is not transitional!");
                    AbstractC1220Ua0.a("ContextualSearch", i10.toString(), new Object[0]);
                    return;
                case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                    e(9, null);
                    return;
            }
        }
    }

    public void d(Integer num) {
        this.c = 0;
        e(1, num);
    }

    /* JADX WARNING: Removed duplicated region for block: B:142:0x0333 A[LOOP:4: B:140:0x032d->B:142:0x0333, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x036a  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void e(int r22, java.lang.Integer r23) {
        /*
        // Method dump skipped, instructions count: 1702
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0547Iz.e(int, java.lang.Integer):void");
    }
}
