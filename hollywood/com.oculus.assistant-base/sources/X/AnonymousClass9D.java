package X;

import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.HashSet;

/* renamed from: X.9D  reason: invalid class name */
public final class AnonymousClass9D {
    public static HashMap A03;
    public static final char[][] A04 = {new char[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'}, new char[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';'}, new char[]{'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'}};
    public double A00;
    public double A01;
    public double A02;

    public AnonymousClass9D(double d, double d2, double d3) {
        this.A01 = d;
        this.A02 = d2;
        this.A00 = d3;
        A03 = new HashMap();
        char[][] cArr = A04;
        int length = cArr.length;
        int length2 = cArr[0].length;
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            while (i2 < cArr[0].length) {
                if (!A03.containsKey(Character.valueOf(cArr[i][i2]))) {
                    A03.put(Character.valueOf(cArr[i][i2]), new HashSet());
                }
                AbstractCollection abstractCollection = (AbstractCollection) A03.get(Character.valueOf(cArr[i][i2]));
                int i3 = i2 - 1;
                if (i3 >= 0) {
                    char c = cArr[i][i3];
                    if (abstractCollection != null) {
                        abstractCollection.add(Character.valueOf(c));
                    }
                }
                int i4 = i2 + 1;
                if (i4 < length2) {
                    char c2 = cArr[i][i4];
                    if (abstractCollection != null) {
                        abstractCollection.add(Character.valueOf(c2));
                    }
                }
                int i5 = i - 1;
                if (i5 >= 0) {
                    char c3 = cArr[i5][i2];
                    if (abstractCollection != null) {
                        abstractCollection.add(Character.valueOf(c3));
                    }
                }
                int i6 = i + 1;
                if (i6 < length) {
                    char c4 = cArr[i6][i2];
                    if (abstractCollection != null) {
                        abstractCollection.add(Character.valueOf(c4));
                    }
                }
                if (i6 < length && i3 >= 0) {
                    char c5 = cArr[i6][i3];
                    if (abstractCollection != null) {
                        abstractCollection.add(Character.valueOf(c5));
                    }
                }
                if (i5 >= 0 && i4 < length2) {
                    char c6 = cArr[i5][i4];
                    if (abstractCollection != null) {
                        abstractCollection.add(Character.valueOf(c6));
                    }
                }
                i2 = i4;
            }
        }
    }
}
