package X;

import java.util.ArrayList;

/* renamed from: X.jy  reason: case insensitive filesystem */
public final class C0437jy {
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
    public final k2 A0A;

    public static void A00(C0437jy jyVar) {
        jyVar.A06 = false;
        jyVar.A05.clear();
        jyVar.A03 = 0;
        jyVar.A00 = 0;
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

    public static void A01(C0437jy jyVar, int i) {
        ArrayList<char[]> arrayList = jyVar.A05;
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            jyVar.A05 = arrayList;
        }
        char[] cArr = jyVar.A07;
        jyVar.A06 = true;
        arrayList.add(cArr);
        int i2 = jyVar.A03;
        int length = cArr.length;
        jyVar.A03 = i2 + length;
        int i3 = length >> 1;
        if (i3 >= i) {
            i = i3;
        }
        jyVar.A00 = 0;
        jyVar.A07 = new char[Math.min(262144, length + i)];
    }

    public static void A02(C0437jy jyVar, int i) {
        int i2 = jyVar.A01;
        jyVar.A01 = 0;
        char[] cArr = jyVar.A08;
        jyVar.A08 = null;
        int i3 = jyVar.A02;
        jyVar.A02 = -1;
        int i4 = i + i2;
        char[] cArr2 = jyVar.A07;
        if (cArr2 == null || i4 > cArr2.length) {
            cArr2 = A04(jyVar, i4);
            jyVar.A07 = cArr2;
        }
        if (i2 > 0) {
            System.arraycopy(cArr, i3, cArr2, 0, i2);
        }
        jyVar.A03 = 0;
        jyVar.A00 = i2;
    }

    public static final char[] A03(C0437jy jyVar) {
        char[] cArr;
        int i;
        char[] cArr2 = jyVar.A09;
        if (cArr2 != null) {
            return cArr2;
        }
        String str = jyVar.A04;
        if (str != null) {
            cArr = str.toCharArray();
        } else {
            int i2 = jyVar.A02;
            if (i2 >= 0) {
                int i3 = jyVar.A01;
                if (i3 >= 1) {
                    cArr = new char[i3];
                    System.arraycopy(jyVar.A08, i2, cArr, 0, i3);
                }
            } else {
                int A052 = jyVar.A05();
                if (A052 >= 1) {
                    cArr = new char[A052];
                    ArrayList<char[]> arrayList = jyVar.A05;
                    if (arrayList != null) {
                        int size = arrayList.size();
                        i = 0;
                        for (int i4 = 0; i4 < size; i4++) {
                            char[] cArr3 = jyVar.A05.get(i4);
                            int length = cArr3.length;
                            System.arraycopy(cArr3, 0, cArr, i, length);
                            i += length;
                        }
                    } else {
                        i = 0;
                    }
                    System.arraycopy(jyVar.A07, 0, cArr, i, jyVar.A00);
                }
            }
            cArr = A0B;
        }
        jyVar.A09 = cArr;
        return cArr;
    }

    public static char[] A04(C0437jy jyVar, int i) {
        k2 k2Var = jyVar.A0A;
        if (k2Var != null) {
            return k2Var.A00(AnonymousClass07.A02, i);
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

    public C0437jy(k2 k2Var) {
        this.A0A = k2Var;
    }

    public final String toString() {
        return A06();
    }
}
