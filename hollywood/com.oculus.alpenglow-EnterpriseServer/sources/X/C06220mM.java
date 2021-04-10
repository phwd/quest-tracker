package X;

import java.util.ArrayList;

/* renamed from: X.0mM  reason: invalid class name and case insensitive filesystem */
public final class C06220mM {
    public static final char[] A09 = new char[0];
    public int A00;
    public int A01;
    public int A02;
    public String A03;
    public ArrayList<char[]> A04;
    public boolean A05 = false;
    public char[] A06;
    public char[] A07;
    public final AnonymousClass0mI A08;

    public static final void A00(C06220mM r2) {
        r2.A01 = -1;
        r2.A00 = 0;
        r2.A03 = null;
        r2.A07 = null;
        if (r2.A05) {
            r2.A05 = false;
            r2.A04.clear();
            r2.A02 = 0;
            r2.A00 = 0;
        }
    }

    public static void A02(C06220mM r3, int i) {
        char[] cArr;
        r3.A01 = -1;
        int i2 = i + 0;
        char[] cArr2 = r3.A06;
        if (cArr2 == null || i2 > cArr2.length) {
            AnonymousClass0mI r1 = r3.A08;
            if (r1 != null) {
                cArr = r1.A00(AnonymousClass007.A0C, i2);
            } else {
                cArr = new char[Math.max(i2, 1000)];
            }
            r3.A06 = cArr;
        }
        r3.A02 = 0;
        r3.A00 = 0;
    }

    public static void A01(C06220mM r3, int i) {
        ArrayList<char[]> arrayList = r3.A04;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            r3.A04 = arrayList;
        }
        char[] cArr = r3.A06;
        r3.A05 = true;
        arrayList.add(cArr);
        int i2 = r3.A02;
        int length = cArr.length;
        r3.A02 = i2 + length;
        int i3 = length >> 1;
        if (i3 >= i) {
            i = i3;
        }
        r3.A00 = 0;
        r3.A06 = new char[Math.min(262144, length + i)];
    }

    public final String A03() {
        String str = this.A03;
        if (str == null) {
            char[] cArr = this.A07;
            if (cArr != null) {
                str = new String(cArr);
            } else {
                str = "";
                if (this.A01 < 0) {
                    int i = this.A02;
                    int i2 = this.A00;
                    if (i != 0) {
                        StringBuilder sb = new StringBuilder(i + i2);
                        ArrayList<char[]> arrayList = this.A04;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            for (int i3 = 0; i3 < size; i3++) {
                                char[] cArr2 = this.A04.get(i3);
                                sb.append(cArr2, 0, cArr2.length);
                            }
                        }
                        sb.append(this.A06, 0, this.A00);
                        str = sb.toString();
                    } else if (i2 != 0) {
                        str = new String(this.A06, 0, i2);
                    }
                }
            }
            this.A03 = str;
        }
        return str;
    }

    public final void A04(String str, int i, int i2) {
        if (this.A01 >= 0) {
            A02(this, i2);
        }
        this.A03 = null;
        this.A07 = null;
        char[] cArr = this.A06;
        int length = cArr.length;
        int i3 = this.A00;
        int i4 = length - i3;
        if (i4 >= i2) {
            str.getChars(i, i + i2, cArr, i3);
            this.A00 += i2;
            return;
        }
        if (i4 > 0) {
            int i5 = i + i4;
            str.getChars(i, i5, cArr, i3);
            i2 -= i4;
            i = i5;
        }
        while (true) {
            A01(this, i2);
            char[] cArr2 = this.A06;
            int min = Math.min(cArr2.length, i2);
            int i6 = i + min;
            str.getChars(i, i6, cArr2, 0);
            this.A00 += min;
            i2 -= min;
            if (i2 > 0) {
                i = i6;
            } else {
                return;
            }
        }
    }

    public final void A05(char[] cArr, int i, int i2) {
        if (this.A01 >= 0) {
            A02(this, i2);
        }
        this.A03 = null;
        this.A07 = null;
        char[] cArr2 = this.A06;
        int length = cArr2.length;
        int i3 = this.A00;
        int i4 = length - i3;
        if (i4 >= i2) {
            System.arraycopy(cArr, i, cArr2, i3, i2);
            this.A00 += i2;
            return;
        }
        if (i4 > 0) {
            System.arraycopy(cArr, i, cArr2, i3, i4);
            i += i4;
            i2 -= i4;
        }
        do {
            A01(this, i2);
            char[] cArr3 = this.A06;
            int min = Math.min(cArr3.length, i2);
            System.arraycopy(cArr, i, cArr3, 0, min);
            this.A00 += min;
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    public final char[] A06() {
        ArrayList<char[]> arrayList = this.A04;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.A04 = arrayList;
        }
        this.A05 = true;
        char[] cArr = this.A06;
        arrayList.add(cArr);
        int length = cArr.length;
        this.A02 += length;
        char[] cArr2 = new char[Math.min(length + (length >> 1), 262144)];
        this.A00 = 0;
        this.A06 = cArr2;
        return cArr2;
    }

    public C06220mM(AnonymousClass0mI r2) {
        this.A08 = r2;
    }

    public final String toString() {
        return A03();
    }
}
