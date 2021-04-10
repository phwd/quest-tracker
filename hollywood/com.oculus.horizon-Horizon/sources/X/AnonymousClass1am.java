package X;

import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1am  reason: invalid class name */
public final class AnonymousClass1am {
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final int A05;
    public final String A06;
    public final String A07;
    public final boolean A08;
    public final boolean A09;
    public final boolean A0A;

    public static String A00(String str) {
        if (str.startsWith("v2,")) {
            String[] split = str.split("\\r?\\n");
            if (split.length != 0) {
                String[] split2 = split[0].split(",");
                if (split2.length >= 2) {
                    return split2[1];
                }
            }
        }
        return "";
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/lang/String;Z)Ljava/util/List<LX/1am;>; */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List A01(java.lang.String r41, boolean r42) {
        /*
        // Method dump skipped, instructions count: 753
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1am.A01(java.lang.String, boolean):java.util.List");
    }

    public static Map<String, AnonymousClass1am> A02(String str) {
        HashMap hashMap = new HashMap();
        for (AnonymousClass1am r3 : A01(str, true)) {
            hashMap.put(AnonymousClass006.A07(r3.A06, ":", r3.A07), r3);
        }
        return hashMap;
    }

    public final long A03() {
        long j;
        int i = this.A04;
        int i2 = this.A00;
        int i3 = this.A03;
        int i4 = this.A05;
        boolean z = this.A08;
        boolean z2 = this.A09;
        boolean z3 = this.A0A;
        int i5 = 2;
        if (z) {
            i5 = 1;
        }
        long j2 = ((long) i5) << 54;
        long j3 = 0;
        if (z2) {
            j = 1152921504606846976L;
        } else {
            j = 0;
        }
        long j4 = ((long) i) << 48;
        long j5 = ((long) i2) << 32;
        long j6 = ((long) i3) << 16;
        if (z3) {
            j3 = 1;
        }
        return j5 | j | j2 | j4 | j6 | ((long) i4) | (j3 << 61);
    }

    public AnonymousClass1am(String str, String str2, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, int i6) {
        this.A06 = str;
        this.A07 = str2;
        this.A02 = i;
        this.A00 = i2;
        this.A03 = i3;
        this.A05 = i4;
        this.A04 = i5;
        this.A08 = z;
        this.A09 = z2;
        this.A0A = z3;
        this.A01 = i6;
    }
}
