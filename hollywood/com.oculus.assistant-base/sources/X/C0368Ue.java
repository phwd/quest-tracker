package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multisets$ImmutableEntry;
import java.util.Comparator;

/* renamed from: X.Ue  reason: case insensitive filesystem */
public final class C0368Ue {
    public int A00;
    public int A01;
    public int A02;
    public long A03;
    public C0368Ue A04;
    public C0368Ue A05;
    public C0368Ue A06;
    public C0368Ue A07;
    public final Object A08;

    /* JADX WARNING: Removed duplicated region for block: B:26:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0368Ue A0C(java.util.Comparator r9, java.lang.Object r10, int r11, int r12, int[] r13) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0368Ue.A0C(java.util.Comparator, java.lang.Object, int, int, int[]):X.Ue");
    }

    private int A00() {
        int i;
        int i2;
        C0368Ue ue = this.A04;
        if (ue == null) {
            i = 0;
        } else {
            i = ue.A02;
        }
        C0368Ue ue2 = this.A06;
        if (ue2 == null) {
            i2 = 0;
        } else {
            i2 = ue2.A02;
        }
        return i - i2;
    }

    private C0368Ue A01() {
        C0368Ue ue;
        int i = this.A01;
        this.A01 = 0;
        C0368Ue ue2 = this.A05;
        C0368Ue ue3 = this.A07;
        ue2.A07 = ue3;
        ue3.A05 = ue2;
        C0368Ue ue4 = this.A04;
        if (ue4 == null) {
            return this.A06;
        }
        C0368Ue ue5 = this.A06;
        if (ue5 == null) {
            return ue4;
        }
        if (ue4.A02 >= ue5.A02) {
            ue = this.A05;
            ue.A04 = ue4.A03(ue);
            ue.A06 = this.A06;
        } else {
            ue = this.A07;
            ue.A06 = ue5.A04(ue);
            ue.A04 = this.A04;
        }
        ue.A00 = this.A00 - 1;
        ue.A03 = this.A03 - ((long) i);
        return ue.A02();
    }

    private C0368Ue A03(C0368Ue ue) {
        C0368Ue ue2 = this.A06;
        if (ue2 == null) {
            return this.A04;
        }
        this.A06 = ue2.A03(ue);
        this.A00--;
        this.A03 -= (long) ue.A01;
        return A02();
    }

    private C0368Ue A04(C0368Ue ue) {
        C0368Ue ue2 = this.A04;
        if (ue2 == null) {
            return this.A06;
        }
        this.A04 = ue2.A04(ue);
        this.A00--;
        this.A03 -= (long) ue.A01;
        return A02();
    }

    public static C0368Ue A05(C0368Ue ue, Comparator comparator, Object obj) {
        C0368Ue A052;
        int compare = comparator.compare(obj, ue.A08);
        if (compare < 0) {
            C0368Ue ue2 = ue.A04;
            if (ue2 == null || (A052 = A05(ue2, comparator, obj)) == null) {
                return ue;
            }
            return A052;
        } else if (compare == 0) {
            return ue;
        } else {
            C0368Ue ue3 = ue.A06;
            if (ue3 == null) {
                return null;
            }
            return A05(ue3, comparator, obj);
        }
    }

    public static C0368Ue A06(C0368Ue ue, Comparator comparator, Object obj) {
        C0368Ue A062;
        int compare = comparator.compare(obj, ue.A08);
        if (compare > 0) {
            C0368Ue ue2 = ue.A06;
            if (ue2 == null || (A062 = A06(ue2, comparator, obj)) == null) {
                return ue;
            }
            return A062;
        } else if (compare == 0) {
            return ue;
        } else {
            C0368Ue ue3 = ue.A04;
            if (ue3 == null) {
                return null;
            }
            return A06(ue3, comparator, obj);
        }
    }

    private void A07() {
        int i;
        int i2;
        long j;
        long j2;
        C0368Ue ue = this.A04;
        if (ue == null) {
            i = 0;
        } else {
            i = ue.A00;
        }
        int i3 = i + 1;
        C0368Ue ue2 = this.A06;
        if (ue2 == null) {
            i2 = 0;
        } else {
            i2 = ue2.A00;
        }
        this.A00 = i3 + i2;
        long j3 = (long) this.A01;
        if (ue == null) {
            j = 0;
        } else {
            j = ue.A03;
        }
        long j4 = j3 + j;
        if (ue2 == null) {
            j2 = 0;
        } else {
            j2 = ue2.A03;
        }
        this.A03 = j4 + j2;
        A08();
    }

    private void A08() {
        int i;
        int i2;
        C0368Ue ue = this.A04;
        if (ue == null) {
            i = 0;
        } else {
            i = ue.A02;
        }
        C0368Ue ue2 = this.A06;
        if (ue2 == null) {
            i2 = 0;
        } else {
            i2 = ue2.A02;
        }
        this.A02 = Math.max(i, i2) + 1;
    }

    private void A09(Object obj, int i) {
        C0368Ue ue = new C0368Ue(obj, i);
        this.A04 = ue;
        C0368Ue ue2 = this.A05;
        ue2.A07 = ue;
        ue.A05 = ue2;
        ue.A07 = this;
        this.A05 = ue;
        this.A02 = Math.max(2, this.A02);
        this.A00++;
        this.A03 += (long) i;
    }

    private void A0A(Object obj, int i) {
        C0368Ue ue = new C0368Ue(obj, i);
        this.A06 = ue;
        C0368Ue ue2 = this.A07;
        this.A07 = ue;
        ue.A05 = this;
        ue.A07 = ue2;
        ue2.A05 = ue;
        this.A02 = Math.max(2, this.A02);
        this.A00++;
        this.A03 += (long) i;
    }

    public final int A0B(Comparator comparator, Object obj) {
        C0368Ue ue;
        int compare = comparator.compare(obj, this.A08);
        if (compare < 0) {
            ue = this.A04;
        } else if (compare <= 0) {
            return this.A01;
        } else {
            ue = this.A06;
        }
        if (ue != null) {
            return ue.A0B(comparator, obj);
        }
        return 0;
    }

    public final C0368Ue A0D(Comparator comparator, Object obj, int i, int[] iArr) {
        int i2;
        C0368Ue ue;
        int compare = comparator.compare(obj, this.A08);
        boolean z = true;
        if (compare < 0) {
            C0368Ue ue2 = this.A04;
            if (ue2 == null) {
                iArr[0] = 0;
                A09(obj, i);
                return this;
            }
            i2 = ue2.A02;
            ue = ue2.A0D(comparator, obj, i, iArr);
            this.A04 = ue;
        } else if (compare > 0) {
            C0368Ue ue3 = this.A06;
            if (ue3 == null) {
                iArr[0] = 0;
                A0A(obj, i);
                return this;
            }
            i2 = ue3.A02;
            ue = ue3.A0D(comparator, obj, i, iArr);
            this.A06 = ue;
        } else {
            int i3 = this.A01;
            iArr[0] = i3;
            long j = (long) i;
            if (((long) i3) + j > 2147483647L) {
                z = false;
            }
            Preconditions.checkArgument(z);
            this.A01 += i;
            this.A03 += j;
            return this;
        }
        if (iArr[0] == 0) {
            this.A00++;
        }
        this.A03 += (long) i;
        if (ue.A02 == i2) {
            return this;
        }
        return A02();
    }

    public final C0368Ue A0E(Comparator comparator, Object obj, int i, int[] iArr) {
        long j;
        long j2;
        long j3;
        long j4;
        int compare = comparator.compare(obj, this.A08);
        if (compare < 0) {
            C0368Ue ue = this.A04;
            if (ue != null) {
                this.A04 = ue.A0E(comparator, obj, i, iArr);
                int i2 = iArr[0];
                if (i2 > 0) {
                    if (i >= i2) {
                        this.A00--;
                        j3 = this.A03;
                        j4 = (long) i2;
                    } else {
                        j3 = this.A03;
                        j4 = (long) i;
                    }
                    this.A03 = j3 - j4;
                }
                if (i2 == 0) {
                    return this;
                }
            }
            iArr[0] = 0;
            return this;
        } else if (compare > 0) {
            C0368Ue ue2 = this.A06;
            if (ue2 != null) {
                this.A06 = ue2.A0E(comparator, obj, i, iArr);
                int i3 = iArr[0];
                if (i3 > 0) {
                    if (i >= i3) {
                        this.A00--;
                        j = this.A03;
                        j2 = (long) i3;
                    } else {
                        j = this.A03;
                        j2 = (long) i;
                    }
                    this.A03 = j - j2;
                }
            }
            iArr[0] = 0;
            return this;
        } else {
            int i4 = this.A01;
            iArr[0] = i4;
            if (i >= i4) {
                return A01();
            }
            this.A01 = i4 - i;
            this.A03 -= (long) i;
            return this;
        }
        return A02();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004a A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0368Ue A0F(java.util.Comparator r5, java.lang.Object r6, int r7, int[] r8) {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0368Ue.A0F(java.util.Comparator, java.lang.Object, int, int[]):X.Ue");
    }

    public final String toString() {
        return new Multisets$ImmutableEntry(this.A08, this.A01).toString();
    }

    public C0368Ue(Object obj, int i) {
        Preconditions.checkArgument(i > 0);
        this.A08 = obj;
        this.A01 = i;
        this.A03 = (long) i;
        this.A00 = 1;
        this.A02 = 1;
        this.A04 = null;
        this.A06 = null;
    }

    private C0368Ue A02() {
        C0368Ue ue;
        int A002 = A00();
        if (A002 == -2) {
            C0368Ue ue2 = this.A06;
            if (ue2.A00() > 0) {
                C0368Ue ue3 = ue2.A04;
                boolean z = false;
                if (ue3 != null) {
                    z = true;
                }
                Preconditions.checkState(z);
                ue2.A04 = ue3.A06;
                ue3.A06 = ue2;
                ue3.A03 = ue2.A03;
                ue3.A00 = ue2.A00;
                ue2.A07();
                ue3.A08();
                this.A06 = ue3;
            }
            ue = this.A06;
            boolean z2 = false;
            if (ue != null) {
                z2 = true;
            }
            Preconditions.checkState(z2);
            this.A06 = ue.A04;
            ue.A04 = this;
        } else if (A002 != 2) {
            A08();
            return this;
        } else {
            C0368Ue ue4 = this.A04;
            if (ue4.A00() < 0) {
                C0368Ue ue5 = ue4.A06;
                boolean z3 = false;
                if (ue5 != null) {
                    z3 = true;
                }
                Preconditions.checkState(z3);
                ue4.A06 = ue5.A04;
                ue5.A04 = ue4;
                ue5.A03 = ue4.A03;
                ue5.A00 = ue4.A00;
                ue4.A07();
                ue5.A08();
                this.A04 = ue5;
            }
            ue = this.A04;
            boolean z4 = false;
            if (ue != null) {
                z4 = true;
            }
            Preconditions.checkState(z4);
            this.A04 = ue.A06;
            ue.A06 = this;
        }
        ue.A03 = this.A03;
        ue.A00 = this.A00;
        A07();
        ue.A08();
        return ue;
    }
}
