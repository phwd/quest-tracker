package defpackage;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/* renamed from: Kv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0661Kv {

    /* renamed from: a  reason: collision with root package name */
    public static final Comparator f8393a = new C0539Iv();
    public final int[] b;
    public final int[] c;
    public final List d;
    public final C1925bw0[] e;
    public final float[] f = new float[3];

    public C0661Kv(int[] iArr, int i, C1925bw0[] bw0Arr) {
        C0600Jv jv;
        int i2;
        this.e = bw0Arr;
        int[] iArr2 = new int[32768];
        this.c = iArr2;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int i4 = iArr[i3];
            int c2 = c(Color.blue(i4), 8, 5) | (c(Color.red(i4), 8, 5) << 10) | (c(Color.green(i4), 8, 5) << 5);
            iArr[i3] = c2;
            iArr2[c2] = iArr2[c2] + 1;
        }
        int i5 = 0;
        for (int i6 = 0; i6 < 32768; i6++) {
            if (iArr2[i6] > 0) {
                int a2 = a((i6 >> 10) & 31, (i6 >> 5) & 31, i6 & 31);
                float[] fArr = this.f;
                ThreadLocal threadLocal = AbstractC1331Vv.f9116a;
                AbstractC1331Vv.a(Color.red(a2), Color.green(a2), Color.blue(a2), fArr);
                if (d(a2, this.f)) {
                    iArr2[i6] = 0;
                }
            }
            if (iArr2[i6] > 0) {
                i5++;
            }
        }
        int[] iArr3 = new int[i5];
        this.b = iArr3;
        int i7 = 0;
        for (int i8 = 0; i8 < 32768; i8++) {
            if (iArr2[i8] > 0) {
                iArr3[i7] = i8;
                i7++;
            }
        }
        if (i5 <= i) {
            this.d = new ArrayList();
            for (int i9 = 0; i9 < i5; i9++) {
                int i10 = iArr3[i9];
                this.d.add(new C2266dw0(a((i10 >> 10) & 31, (i10 >> 5) & 31, i10 & 31), iArr2[i10]));
            }
            return;
        }
        PriorityQueue priorityQueue = new PriorityQueue(i, f8393a);
        priorityQueue.offer(new C0600Jv(this, 0, this.b.length - 1));
        while (priorityQueue.size() < i && (jv = (C0600Jv) priorityQueue.poll()) != null) {
            int i11 = jv.b;
            int i12 = jv.f8326a;
            int i13 = (i11 + 1) - i12;
            if (!(i13 > 1)) {
                break;
            }
            if (i13 > 1) {
                int i14 = jv.e - jv.d;
                int i15 = jv.g - jv.f;
                int i16 = jv.i - jv.h;
                int i17 = (i14 < i15 || i14 < i16) ? (i15 < i14 || i15 < i16) ? -1 : -2 : -3;
                C0661Kv kv = jv.j;
                int[] iArr4 = kv.b;
                int[] iArr5 = kv.c;
                b(iArr4, i17, i12, i11);
                Arrays.sort(iArr4, jv.f8326a, jv.b + 1);
                b(iArr4, i17, jv.f8326a, jv.b);
                int i18 = jv.c / 2;
                int i19 = jv.f8326a;
                int i20 = 0;
                while (true) {
                    int i21 = jv.b;
                    if (i19 > i21) {
                        i2 = jv.f8326a;
                        break;
                    }
                    i20 += iArr5[iArr4[i19]];
                    if (i20 >= i18) {
                        i2 = Math.min(i21 - 1, i19);
                        break;
                    }
                    i19++;
                }
                C0600Jv jv2 = new C0600Jv(jv.j, i2 + 1, jv.b);
                jv.b = i2;
                jv.a();
                priorityQueue.offer(jv2);
                priorityQueue.offer(jv);
            } else {
                throw new IllegalStateException("Can not split a box with only 1 color");
            }
        }
        ArrayList arrayList = new ArrayList(priorityQueue.size());
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            C0600Jv jv3 = (C0600Jv) it.next();
            C0661Kv kv2 = jv3.j;
            int[] iArr6 = kv2.b;
            int[] iArr7 = kv2.c;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            for (int i26 = jv3.f8326a; i26 <= jv3.b; i26++) {
                int i27 = iArr6[i26];
                int i28 = iArr7[i27];
                i23 += i28;
                i22 += ((i27 >> 10) & 31) * i28;
                i24 += ((i27 >> 5) & 31) * i28;
                i25 += i28 * (i27 & 31);
            }
            float f2 = (float) i23;
            C2266dw0 dw0 = new C2266dw0(a(Math.round(((float) i22) / f2), Math.round(((float) i24) / f2), Math.round(((float) i25) / f2)), i23);
            if (!d(dw0.d, dw0.b())) {
                arrayList.add(dw0);
            }
        }
        this.d = arrayList;
    }

    public static int a(int i, int i2, int i3) {
        return Color.rgb(c(i, 5, 8), c(i2, 5, 8), c(i3, 5, 8));
    }

    public static void b(int[] iArr, int i, int i2, int i3) {
        if (i == -2) {
            while (i2 <= i3) {
                int i4 = iArr[i2];
                iArr[i2] = (i4 & 31) | (((i4 >> 5) & 31) << 10) | (((i4 >> 10) & 31) << 5);
                i2++;
            }
        } else if (i == -1) {
            while (i2 <= i3) {
                int i5 = iArr[i2];
                iArr[i2] = ((i5 >> 10) & 31) | ((i5 & 31) << 10) | (((i5 >> 5) & 31) << 5);
                i2++;
            }
        }
    }

    public static int c(int i, int i2, int i3) {
        return (i3 > i2 ? i << (i3 - i2) : i >> (i2 - i3)) & ((1 << i3) - 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0055 A[LOOP:0: B:5:0x000a->B:29:0x0055, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0054 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean d(int r6, float[] r7) {
        /*
            r5 = this;
            bw0[] r6 = r5.e
            r0 = 0
            if (r6 == 0) goto L_0x0058
            int r1 = r6.length
            if (r1 <= 0) goto L_0x0058
            int r6 = r6.length
            r1 = r0
        L_0x000a:
            if (r1 >= r6) goto L_0x0058
            bw0[] r2 = r5.e
            r2 = r2[r1]
            java.util.Objects.requireNonNull(r2)
            r2 = 2
            r3 = r7[r2]
            r4 = 1064514355(0x3f733333, float:0.95)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            r4 = 1
            if (r3 < 0) goto L_0x0020
            r3 = r4
            goto L_0x0021
        L_0x0020:
            r3 = r0
        L_0x0021:
            if (r3 != 0) goto L_0x0051
            r2 = r7[r2]
            r3 = 1028443341(0x3d4ccccd, float:0.05)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x002e
            r2 = r4
            goto L_0x002f
        L_0x002e:
            r2 = r0
        L_0x002f:
            if (r2 != 0) goto L_0x0051
            r2 = r7[r0]
            r3 = 1092616192(0x41200000, float:10.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 < 0) goto L_0x004c
            r2 = r7[r0]
            r3 = 1108606976(0x42140000, float:37.0)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x004c
            r2 = r7[r4]
            r3 = 1062333317(0x3f51eb85, float:0.82)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x004c
            r2 = r4
            goto L_0x004d
        L_0x004c:
            r2 = r0
        L_0x004d:
            if (r2 != 0) goto L_0x0051
            r2 = r4
            goto L_0x0052
        L_0x0051:
            r2 = r0
        L_0x0052:
            if (r2 != 0) goto L_0x0055
            return r4
        L_0x0055:
            int r1 = r1 + 1
            goto L_0x000a
        L_0x0058:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0661Kv.d(int, float[]):boolean");
    }
}
