package X;

import java.util.ArrayList;

/* renamed from: X.0kP  reason: invalid class name and case insensitive filesystem */
public final class C05140kP {
    public static final char[] A0B = new char[0];
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public String A04;
    public ArrayList<char[]> A05;
    public boolean A06 = false;
    public char[] A07;
    public char[] A08;
    public char[] A09;
    public final AnonymousClass0kL A0A;

    public static void A00(C05140kP r2) {
        r2.A06 = false;
        r2.A05.clear();
        r2.A03 = 0;
        r2.A00 = 0;
    }

    public final void A07(char[] cArr, int i, int i2) {
        this.A04 = null;
        this.A09 = null;
        this.A08 = cArr;
        this.A02 = i;
        this.A01 = i2;
        if (this.A06) {
            A00(this);
        }
    }

    public final char[] A08() {
        this.A02 = -1;
        this.A00 = 0;
        this.A01 = 0;
        this.A08 = null;
        this.A04 = null;
        this.A09 = null;
        if (this.A06) {
            A00(this);
        }
        char[] cArr = this.A07;
        if (cArr != null) {
            return cArr;
        }
        char[] A042 = A04(this, 0);
        this.A07 = A042;
        return A042;
    }

    public static void A01(C05140kP r3, int i) {
        ArrayList<char[]> arrayList = r3.A05;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            r3.A05 = arrayList;
        }
        char[] cArr = r3.A07;
        r3.A06 = true;
        arrayList.add(cArr);
        int i2 = r3.A03;
        int length = cArr.length;
        r3.A03 = i2 + length;
        int i3 = length >> 1;
        if (i3 >= i) {
            i = i3;
        }
        r3.A00 = 0;
        r3.A07 = new char[Math.min(262144, length + i)];
    }

    public static void A02(C05140kP r6, int i) {
        int i2 = r6.A01;
        r6.A01 = 0;
        char[] cArr = r6.A08;
        r6.A08 = null;
        int i3 = r6.A02;
        r6.A02 = -1;
        int i4 = i + i2;
        char[] cArr2 = r6.A07;
        if (cArr2 == null || i4 > cArr2.length) {
            cArr2 = A04(r6, i4);
            r6.A07 = cArr2;
        }
        if (i2 > 0) {
            System.arraycopy(cArr, i3, cArr2, 0, i2);
        }
        r6.A03 = 0;
        r6.A00 = i2;
    }

    public static final char[] A03(C05140kP r7) {
        char[] cArr;
        int i;
        char[] cArr2 = r7.A09;
        if (cArr2 != null) {
            return cArr2;
        }
        String str = r7.A04;
        if (str != null) {
            cArr = str.toCharArray();
        } else {
            int i2 = r7.A02;
            if (i2 >= 0) {
                int i3 = r7.A01;
                if (i3 >= 1) {
                    cArr = new char[i3];
                    System.arraycopy(r7.A08, i2, cArr, 0, i3);
                }
            } else {
                int A052 = r7.A05();
                if (A052 >= 1) {
                    cArr = new char[A052];
                    ArrayList<char[]> arrayList = r7.A05;
                    if (arrayList != null) {
                        int size = arrayList.size();
                        i = 0;
                        for (int i4 = 0; i4 < size; i4++) {
                            char[] cArr3 = r7.A05.get(i4);
                            int length = cArr3.length;
                            System.arraycopy(cArr3, 0, cArr, i, length);
                            i += length;
                        }
                    } else {
                        i = 0;
                    }
                    System.arraycopy(r7.A07, 0, cArr, i, r7.A00);
                }
            }
            cArr = A0B;
        }
        r7.A09 = cArr;
        return cArr;
    }

    public static char[] A04(C05140kP r1, int i) {
        AnonymousClass0kL r12 = r1.A0A;
        if (r12 != null) {
            return r12.A00(AnonymousClass007.A0C, i);
        }
        return new char[Math.max(i, 1000)];
    }

    public final int A05() {
        if (this.A02 >= 0) {
            return this.A01;
        }
        char[] cArr = this.A09;
        if (cArr != null) {
            return cArr.length;
        }
        String str = this.A04;
        if (str != null) {
            return str.length();
        }
        return this.A03 + this.A00;
    }

    public final String A06() {
        String str = this.A04;
        if (str == null) {
            char[] cArr = this.A09;
            if (cArr != null) {
                str = new String(cArr);
            } else {
                int i = this.A02;
                str = "";
                if (i >= 0) {
                    int i2 = this.A01;
                    if (i2 >= 1) {
                        str = new String(this.A08, i, i2);
                    }
                } else {
                    int i3 = this.A03;
                    int i4 = this.A00;
                    if (i3 != 0) {
                        StringBuilder sb = new StringBuilder(i3 + i4);
                        ArrayList<char[]> arrayList = this.A05;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            for (int i5 = 0; i5 < size; i5++) {
                                char[] cArr2 = this.A05.get(i5);
                                sb.append(cArr2, 0, cArr2.length);
                            }
                        }
                        sb.append(this.A07, 0, this.A00);
                        str = sb.toString();
                    } else if (i4 != 0) {
                        str = new String(this.A07, 0, i4);
                    }
                }
            }
            this.A04 = str;
        }
        return str;
    }

    public final char[] A09() {
        ArrayList<char[]> arrayList = this.A05;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.A05 = arrayList;
        }
        this.A06 = true;
        char[] cArr = this.A07;
        arrayList.add(cArr);
        int length = cArr.length;
        this.A03 += length;
        char[] cArr2 = new char[Math.min(length + (length >> 1), 262144)];
        this.A00 = 0;
        this.A07 = cArr2;
        return cArr2;
    }

    public final char[] A0A() {
        if (this.A02 >= 0) {
            A02(this, 1);
        } else {
            char[] cArr = this.A07;
            if (cArr == null) {
                this.A07 = A04(this, 0);
            } else if (this.A00 >= cArr.length) {
                A01(this, 1);
            }
        }
        return this.A07;
    }

    public final char[] A0B() {
        if (this.A02 >= 0) {
            return this.A08;
        }
        char[] cArr = this.A09;
        if (cArr != null) {
            return cArr;
        }
        String str = this.A04;
        if (str != null) {
            char[] charArray = str.toCharArray();
            this.A09 = charArray;
            return charArray;
        } else if (!this.A06) {
            return this.A07;
        } else {
            return A03(this);
        }
    }

    public C05140kP(AnonymousClass0kL r2) {
        this.A0A = r2;
    }

    public final String toString() {
        return A06();
    }
}
