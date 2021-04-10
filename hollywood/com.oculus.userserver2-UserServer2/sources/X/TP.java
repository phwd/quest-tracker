package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multisets$ImmutableEntry;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class TP<E> {
    public int A00;
    public int A01;
    public int A02;
    public long A03;
    @NullableDecl
    public TP<E> A04;
    @NullableDecl
    public TP<E> A05;
    @NullableDecl
    public TP<E> A06;
    @NullableDecl
    public TP<E> A07;
    @NullableDecl
    public final E A08;

    /* JADX WARNING: Removed duplicated region for block: B:26:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.TP<E> A0C(java.util.Comparator<? super E> r9, @org.checkerframework.checker.nullness.compatqual.NullableDecl E r10, int r11, int r12, int[] r13) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.TP.A0C(java.util.Comparator, java.lang.Object, int, int, int[]):X.TP");
    }

    private int A00() {
        int i;
        int i2;
        TP<E> tp = this.A04;
        if (tp == null) {
            i = 0;
        } else {
            i = tp.A02;
        }
        TP<E> tp2 = this.A06;
        if (tp2 == null) {
            i2 = 0;
        } else {
            i2 = tp2.A02;
        }
        return i - i2;
    }

    private TP<E> A01() {
        TP<E> tp;
        int i = this.A01;
        this.A01 = 0;
        TP<E> tp2 = this.A05;
        TP<E> tp3 = this.A07;
        tp2.A07 = tp3;
        tp3.A05 = tp2;
        TP<E> tp4 = this.A04;
        if (tp4 == null) {
            return this.A06;
        }
        TP<E> tp5 = this.A06;
        if (tp5 == null) {
            return tp4;
        }
        if (tp4.A02 >= tp5.A02) {
            tp = this.A05;
            tp.A04 = tp4.A03(tp);
            tp.A06 = this.A06;
        } else {
            tp = this.A07;
            tp.A06 = tp5.A04(tp);
            tp.A04 = this.A04;
        }
        tp.A00 = this.A00 - 1;
        tp.A03 = this.A03 - ((long) i);
        return tp.A02();
    }

    private TP<E> A03(TP<E> tp) {
        TP<E> tp2 = this.A06;
        if (tp2 == null) {
            return this.A04;
        }
        this.A06 = tp2.A03(tp);
        this.A00--;
        this.A03 -= (long) tp.A01;
        return A02();
    }

    private TP<E> A04(TP<E> tp) {
        TP<E> tp2 = this.A04;
        if (tp2 == null) {
            return this.A06;
        }
        this.A04 = tp2.A04(tp);
        this.A00--;
        this.A03 -= (long) tp.A01;
        return A02();
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/Comparator<-TE;>;TE;)LX/TP<TE;>; */
    @NullableDecl
    public static TP A05(TP tp, Comparator comparator, Object obj) {
        TP A052;
        int compare = comparator.compare(obj, tp.A08);
        if (compare < 0) {
            TP<E> tp2 = tp.A04;
            if (tp2 == null || (A052 = A05(tp2, comparator, obj)) == null) {
                return tp;
            }
            return A052;
        } else if (compare == 0) {
            return tp;
        } else {
            TP<E> tp3 = tp.A06;
            if (tp3 == null) {
                return null;
            }
            return A05(tp3, comparator, obj);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/Comparator<-TE;>;TE;)LX/TP<TE;>; */
    @NullableDecl
    public static TP A06(TP tp, Comparator comparator, Object obj) {
        TP A062;
        int compare = comparator.compare(obj, tp.A08);
        if (compare > 0) {
            TP<E> tp2 = tp.A06;
            if (tp2 == null || (A062 = A06(tp2, comparator, obj)) == null) {
                return tp;
            }
            return A062;
        } else if (compare == 0) {
            return tp;
        } else {
            TP<E> tp3 = tp.A04;
            if (tp3 == null) {
                return null;
            }
            return A06(tp3, comparator, obj);
        }
    }

    private void A07() {
        int i;
        int i2;
        long j;
        long j2;
        TP<E> tp = this.A04;
        if (tp == null) {
            i = 0;
        } else {
            i = tp.A00;
        }
        int i3 = i + 1;
        TP<E> tp2 = this.A06;
        if (tp2 == null) {
            i2 = 0;
        } else {
            i2 = tp2.A00;
        }
        this.A00 = i3 + i2;
        long j3 = (long) this.A01;
        if (tp == null) {
            j = 0;
        } else {
            j = tp.A03;
        }
        long j4 = j3 + j;
        if (tp2 == null) {
            j2 = 0;
        } else {
            j2 = tp2.A03;
        }
        this.A03 = j4 + j2;
        A08();
    }

    private void A08() {
        int i;
        int i2;
        TP<E> tp = this.A04;
        if (tp == null) {
            i = 0;
        } else {
            i = tp.A02;
        }
        TP<E> tp2 = this.A06;
        if (tp2 == null) {
            i2 = 0;
        } else {
            i2 = tp2.A02;
        }
        this.A02 = Math.max(i, i2) + 1;
    }

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/TP<TE;>; */
    private void A09(Object obj, int i) {
        TP<E> tp = new TP<>(obj, i);
        this.A04 = tp;
        TP<E> tp2 = this.A05;
        tp2.A07 = tp;
        tp.A05 = tp2;
        tp.A07 = this;
        this.A05 = tp;
        this.A02 = Math.max(2, this.A02);
        this.A00++;
        this.A03 += (long) i;
    }

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/TP<TE;>; */
    private void A0A(Object obj, int i) {
        TP<E> tp = new TP<>(obj, i);
        this.A06 = tp;
        TP<E> tp2 = this.A07;
        this.A07 = tp;
        tp.A05 = this;
        tp.A07 = tp2;
        tp2.A05 = tp;
        this.A02 = Math.max(2, this.A02);
        this.A00++;
        this.A03 += (long) i;
    }

    public final int A0B(Comparator<? super E> comparator, E e) {
        TP<E> tp;
        int compare = comparator.compare(e, this.A08);
        if (compare < 0) {
            tp = this.A04;
        } else if (compare <= 0) {
            return this.A01;
        } else {
            tp = this.A06;
        }
        if (tp != null) {
            return tp.A0B(comparator, e);
        }
        return 0;
    }

    public final TP<E> A0D(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
        int i2;
        TP<E> tp;
        int compare = comparator.compare(e, this.A08);
        if (compare < 0) {
            TP<E> tp2 = this.A04;
            if (tp2 == null) {
                iArr[0] = 0;
                A09(e, i);
                return this;
            }
            i2 = tp2.A02;
            tp = tp2.A0D(comparator, e, i, iArr);
            this.A04 = tp;
        } else if (compare > 0) {
            TP<E> tp3 = this.A06;
            if (tp3 == null) {
                iArr[0] = 0;
                A0A(e, i);
                return this;
            }
            i2 = tp3.A02;
            tp = tp3.A0D(comparator, e, i, iArr);
            this.A06 = tp;
        } else {
            int i3 = this.A01;
            iArr[0] = i3;
            long j = (long) i;
            if (((long) i3) + j <= 2147483647L) {
                this.A01 = i3 + i;
                this.A03 += j;
                return this;
            }
            throw new IllegalArgumentException();
        }
        if (iArr[0] == 0) {
            this.A00++;
        }
        this.A03 += (long) i;
        if (tp.A02 == i2) {
            return this;
        }
        return A02();
    }

    public final TP<E> A0E(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
        long j;
        long j2;
        long j3;
        long j4;
        int compare = comparator.compare(e, this.A08);
        if (compare < 0) {
            TP<E> tp = this.A04;
            if (tp != null) {
                this.A04 = tp.A0E(comparator, e, i, iArr);
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
            TP<E> tp2 = this.A06;
            if (tp2 != null) {
                this.A06 = tp2.A0E(comparator, e, i, iArr);
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
    public final X.TP<E> A0F(java.util.Comparator<? super E> r5, @org.checkerframework.checker.nullness.compatqual.NullableDecl E r6, int r7, int[] r8) {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: X.TP.A0F(java.util.Comparator, java.lang.Object, int, int[]):X.TP");
    }

    public final String toString() {
        return new Multisets$ImmutableEntry(this.A08, this.A01).toString();
    }

    public TP(@NullableDecl E e, int i) {
        if (i > 0) {
            this.A08 = e;
            this.A01 = i;
            this.A03 = (long) i;
            this.A00 = 1;
            this.A02 = 1;
            this.A04 = null;
            this.A06 = null;
            return;
        }
        throw new IllegalArgumentException();
    }

    private TP<E> A02() {
        TP<E> tp;
        int A002 = A00();
        if (A002 == -2) {
            TP<E> tp2 = this.A06;
            if (tp2.A00() > 0) {
                TP<E> tp3 = tp2.A04;
                boolean z = false;
                if (tp3 != null) {
                    z = true;
                }
                Preconditions.checkState(z);
                tp2.A04 = tp3.A06;
                tp3.A06 = tp2;
                tp3.A03 = tp2.A03;
                tp3.A00 = tp2.A00;
                tp2.A07();
                tp3.A08();
                this.A06 = tp3;
            }
            tp = this.A06;
            boolean z2 = false;
            if (tp != null) {
                z2 = true;
            }
            Preconditions.checkState(z2);
            this.A06 = tp.A04;
            tp.A04 = this;
        } else if (A002 != 2) {
            A08();
            return this;
        } else {
            TP<E> tp4 = this.A04;
            if (tp4.A00() < 0) {
                TP<E> tp5 = tp4.A06;
                boolean z3 = false;
                if (tp5 != null) {
                    z3 = true;
                }
                Preconditions.checkState(z3);
                tp4.A06 = tp5.A04;
                tp5.A04 = tp4;
                tp5.A03 = tp4.A03;
                tp5.A00 = tp4.A00;
                tp4.A07();
                tp5.A08();
                this.A04 = tp5;
            }
            tp = this.A04;
            boolean z4 = false;
            if (tp != null) {
                z4 = true;
            }
            Preconditions.checkState(z4);
            this.A04 = tp.A06;
            tp.A06 = this;
        }
        tp.A03 = this.A03;
        tp.A00 = this.A00;
        A07();
        tp.A08();
        return tp;
    }
}
