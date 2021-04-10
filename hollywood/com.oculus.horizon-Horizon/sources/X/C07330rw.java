package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multisets$ImmutableEntry;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0rw  reason: invalid class name and case insensitive filesystem */
public final class C07330rw<E> {
    public int A00;
    public int A01;
    public int A02;
    public long A03;
    @NullableDecl
    public C07330rw<E> A04;
    @NullableDecl
    public C07330rw<E> A05;
    @NullableDecl
    public C07330rw<E> A06;
    @NullableDecl
    public C07330rw<E> A07;
    @NullableDecl
    public final E A08;

    /* JADX WARNING: Removed duplicated region for block: B:26:0x004a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C07330rw<E> A0C(java.util.Comparator<? super E> r9, @org.checkerframework.checker.nullness.compatqual.NullableDecl E r10, int r11, int r12, int[] r13) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C07330rw.A0C(java.util.Comparator, java.lang.Object, int, int, int[]):X.0rw");
    }

    private int A00() {
        int i;
        int i2;
        C07330rw<E> r0 = this.A04;
        if (r0 == null) {
            i = 0;
        } else {
            i = r0.A02;
        }
        C07330rw<E> r02 = this.A06;
        if (r02 == null) {
            i2 = 0;
        } else {
            i2 = r02.A02;
        }
        return i - i2;
    }

    private C07330rw<E> A01() {
        C07330rw<E> r4;
        int i = this.A01;
        this.A01 = 0;
        C07330rw<E> r2 = this.A05;
        C07330rw<E> r0 = this.A07;
        r2.A07 = r0;
        r0.A05 = r2;
        C07330rw<E> r5 = this.A04;
        if (r5 == null) {
            return this.A06;
        }
        C07330rw<E> r3 = this.A06;
        if (r3 == null) {
            return r5;
        }
        if (r5.A02 >= r3.A02) {
            r4 = this.A05;
            r4.A04 = r5.A03(r4);
            r4.A06 = this.A06;
        } else {
            r4 = this.A07;
            r4.A06 = r3.A04(r4);
            r4.A04 = this.A04;
        }
        r4.A00 = this.A00 - 1;
        r4.A03 = this.A03 - ((long) i);
        return r4.A02();
    }

    private C07330rw<E> A03(C07330rw<E> r5) {
        C07330rw<E> r0 = this.A06;
        if (r0 == null) {
            return this.A04;
        }
        this.A06 = r0.A03(r5);
        this.A00--;
        this.A03 -= (long) r5.A01;
        return A02();
    }

    private C07330rw<E> A04(C07330rw<E> r5) {
        C07330rw<E> r0 = this.A04;
        if (r0 == null) {
            return this.A06;
        }
        this.A04 = r0.A04(r5);
        this.A00--;
        this.A03 -= (long) r5.A01;
        return A02();
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/Comparator<-TE;>;TE;)LX/0rw<TE;>; */
    @NullableDecl
    public static C07330rw A05(C07330rw r1, Comparator comparator, Object obj) {
        C07330rw A052;
        int compare = comparator.compare(obj, r1.A08);
        if (compare < 0) {
            C07330rw<E> r0 = r1.A04;
            if (r0 == null || (A052 = A05(r0, comparator, obj)) == null) {
                return r1;
            }
            return A052;
        } else if (compare == 0) {
            return r1;
        } else {
            C07330rw<E> r02 = r1.A06;
            if (r02 == null) {
                return null;
            }
            return A05(r02, comparator, obj);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/util/Comparator<-TE;>;TE;)LX/0rw<TE;>; */
    @NullableDecl
    public static C07330rw A06(C07330rw r1, Comparator comparator, Object obj) {
        C07330rw A062;
        int compare = comparator.compare(obj, r1.A08);
        if (compare > 0) {
            C07330rw<E> r0 = r1.A06;
            if (r0 == null || (A062 = A06(r0, comparator, obj)) == null) {
                return r1;
            }
            return A062;
        } else if (compare == 0) {
            return r1;
        } else {
            C07330rw<E> r02 = r1.A04;
            if (r02 == null) {
                return null;
            }
            return A06(r02, comparator, obj);
        }
    }

    private void A07() {
        int i;
        int i2;
        long j;
        long j2;
        C07330rw<E> r5 = this.A04;
        if (r5 == null) {
            i = 0;
        } else {
            i = r5.A00;
        }
        int i3 = i + 1;
        C07330rw<E> r4 = this.A06;
        if (r4 == null) {
            i2 = 0;
        } else {
            i2 = r4.A00;
        }
        this.A00 = i3 + i2;
        long j3 = (long) this.A01;
        if (r5 == null) {
            j = 0;
        } else {
            j = r5.A03;
        }
        long j4 = j3 + j;
        if (r4 == null) {
            j2 = 0;
        } else {
            j2 = r4.A03;
        }
        this.A03 = j4 + j2;
        A08();
    }

    private void A08() {
        int i;
        int i2;
        C07330rw<E> r0 = this.A04;
        if (r0 == null) {
            i = 0;
        } else {
            i = r0.A02;
        }
        C07330rw<E> r02 = this.A06;
        if (r02 == null) {
            i2 = 0;
        } else {
            i2 = r02.A02;
        }
        this.A02 = Math.max(i, i2) + 1;
    }

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/0rw<TE;>; */
    private void A09(Object obj, int i) {
        C07330rw<E> r1 = new C07330rw<>(obj, i);
        this.A04 = r1;
        C07330rw<E> r0 = this.A05;
        r0.A07 = r1;
        r1.A05 = r0;
        r1.A07 = this;
        this.A05 = r1;
        this.A02 = Math.max(2, this.A02);
        this.A00++;
        this.A03 += (long) i;
    }

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/0rw<TE;>; */
    private void A0A(Object obj, int i) {
        C07330rw<E> r1 = new C07330rw<>(obj, i);
        this.A06 = r1;
        C07330rw<E> r0 = this.A07;
        this.A07 = r1;
        r1.A05 = this;
        r1.A07 = r0;
        r0.A05 = r1;
        this.A02 = Math.max(2, this.A02);
        this.A00++;
        this.A03 += (long) i;
    }

    public final int A0B(Comparator<? super E> comparator, E e) {
        C07330rw<E> r0;
        int compare = comparator.compare(e, this.A08);
        if (compare < 0) {
            r0 = this.A04;
        } else if (compare <= 0) {
            return this.A01;
        } else {
            r0 = this.A06;
        }
        if (r0 != null) {
            return r0.A0B(comparator, e);
        }
        return 0;
    }

    public final C07330rw<E> A0D(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
        int i2;
        C07330rw<E> r4;
        int compare = comparator.compare(e, this.A08);
        boolean z = true;
        if (compare < 0) {
            C07330rw<E> r0 = this.A04;
            if (r0 == null) {
                iArr[0] = 0;
                A09(e, i);
                return this;
            }
            i2 = r0.A02;
            r4 = r0.A0D(comparator, e, i, iArr);
            this.A04 = r4;
        } else if (compare > 0) {
            C07330rw<E> r02 = this.A06;
            if (r02 == null) {
                iArr[0] = 0;
                A0A(e, i);
                return this;
            }
            i2 = r02.A02;
            r4 = r02.A0D(comparator, e, i, iArr);
            this.A06 = r4;
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
        if (r4.A02 == i2) {
            return this;
        }
        return A02();
    }

    public final C07330rw<E> A0E(Comparator<? super E> comparator, @NullableDecl E e, int i, int[] iArr) {
        long j;
        long j2;
        long j3;
        long j4;
        int compare = comparator.compare(e, this.A08);
        if (compare < 0) {
            C07330rw<E> r0 = this.A04;
            if (r0 != null) {
                this.A04 = r0.A0E(comparator, e, i, iArr);
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
            C07330rw<E> r02 = this.A06;
            if (r02 != null) {
                this.A06 = r02.A0E(comparator, e, i, iArr);
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
    public final X.C07330rw<E> A0F(java.util.Comparator<? super E> r5, @org.checkerframework.checker.nullness.compatqual.NullableDecl E r6, int r7, int[] r8) {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C07330rw.A0F(java.util.Comparator, java.lang.Object, int, int[]):X.0rw");
    }

    public final String toString() {
        return new Multisets$ImmutableEntry(this.A08, this.A01).toString();
    }

    public C07330rw(@NullableDecl E e, int i) {
        Preconditions.checkArgument(i > 0);
        this.A08 = e;
        this.A01 = i;
        this.A03 = (long) i;
        this.A00 = 1;
        this.A02 = 1;
        this.A04 = null;
        this.A06 = null;
    }

    private C07330rw<E> A02() {
        C07330rw<E> r2;
        int A002 = A00();
        if (A002 == -2) {
            C07330rw<E> r3 = this.A06;
            if (r3.A00() > 0) {
                C07330rw<E> r22 = r3.A04;
                boolean z = false;
                if (r22 != null) {
                    z = true;
                }
                Preconditions.checkState(z);
                r3.A04 = r22.A06;
                r22.A06 = r3;
                r22.A03 = r3.A03;
                r22.A00 = r3.A00;
                r3.A07();
                r22.A08();
                this.A06 = r22;
            }
            r2 = this.A06;
            boolean z2 = false;
            if (r2 != null) {
                z2 = true;
            }
            Preconditions.checkState(z2);
            this.A06 = r2.A04;
            r2.A04 = this;
        } else if (A002 != 2) {
            A08();
            return this;
        } else {
            C07330rw<E> r32 = this.A04;
            if (r32.A00() < 0) {
                C07330rw<E> r23 = r32.A06;
                boolean z3 = false;
                if (r23 != null) {
                    z3 = true;
                }
                Preconditions.checkState(z3);
                r32.A06 = r23.A04;
                r23.A04 = r32;
                r23.A03 = r32.A03;
                r23.A00 = r32.A00;
                r32.A07();
                r23.A08();
                this.A04 = r23;
            }
            r2 = this.A04;
            boolean z4 = false;
            if (r2 != null) {
                z4 = true;
            }
            Preconditions.checkState(z4);
            this.A04 = r2.A06;
            r2.A06 = this;
        }
        r2.A03 = this.A03;
        r2.A00 = this.A00;
        A07();
        r2.A08();
        return r2;
    }
}
