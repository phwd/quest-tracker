package X;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.HashMap;

/* renamed from: X.9B  reason: invalid class name */
public class AnonymousClass9B {
    public double A00;
    public double A01;
    public double A02;
    public AnonymousClass9D A03;

    public final double A02(String str, String str2, boolean z, AnonymousClass9A r24) {
        double d;
        boolean contains;
        double d2;
        int length = str.length();
        int length2 = str2.length();
        int min = Math.min(length, 8);
        int length3 = z ? r24.A02.length() - 1 : 0;
        double[][] dArr = (double[][]) r24.A03.get(str2);
        if (!r24.A03.containsKey(str2) || dArr == null) {
            dArr = (double[][]) Array.newInstance(double.class, 9, length2 + 1);
            int i = 0;
            do {
                dArr[i][0] = ((double) i) * this.A01;
                i++;
            } while (i <= 8);
            for (int i2 = 0; i2 <= length2; i2++) {
                dArr[0][i2] = ((double) i2) * this.A02;
            }
            r24.A03.put(str2, dArr);
        }
        while (length3 < min) {
            int i3 = 0;
            while (i3 < length2) {
                char charAt = str.charAt(length3);
                char charAt2 = str2.charAt(i3);
                if (charAt == charAt2) {
                    d = this.A00;
                } else {
                    AnonymousClass9D r2 = this.A03;
                    if (charAt == charAt2) {
                        d = r2.A00;
                    } else {
                        HashMap hashMap = AnonymousClass9D.A03;
                        Character valueOf = Character.valueOf(charAt);
                        if (hashMap.get(valueOf) != null) {
                            contains = ((AbstractCollection) AnonymousClass9D.A03.get(valueOf)).contains(Character.valueOf(charAt2));
                        } else {
                            HashMap hashMap2 = AnonymousClass9D.A03;
                            Character valueOf2 = Character.valueOf(charAt2);
                            if (hashMap2.get(valueOf2) != null) {
                                contains = ((AbstractCollection) AnonymousClass9D.A03.get(valueOf2)).contains(valueOf);
                            }
                            d = r2.A02;
                        }
                        if (contains) {
                            d = r2.A01;
                        }
                        d = r2.A02;
                    }
                }
                if (length3 == min - 1) {
                    d2 = this.A00;
                } else {
                    d2 = this.A01;
                }
                double d3 = this.A02;
                double[] dArr2 = dArr[length3];
                double d4 = dArr2[i3] + d;
                double[] dArr3 = dArr[length3 + 1];
                i3++;
                dArr3[i3] = Math.max(d4, Math.max(dArr3[i3] + d2, dArr2[i3] + d3));
            }
            length3++;
        }
        return dArr[min][length2];
    }

    public AnonymousClass9B(double d, double d2, double d3, double d4, double d5) {
        this.A01 = d;
        this.A02 = d2;
        this.A00 = d5;
        this.A03 = new AnonymousClass9D(d3, d4, d5);
    }
}
