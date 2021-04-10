package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
/* renamed from: X.0tI  reason: invalid class name */
public class AnonymousClass0tI<K> {
    public transient float A00;
    public transient int A01;
    public transient int A02;
    public transient int A03;
    public transient int[] A04;
    public transient int[] A05;
    @VisibleForTesting
    public transient long[] A06;
    public transient Object[] A07;

    public int A04(int i, int i2) {
        return i - 1;
    }

    public void A0A(int i, float f) {
        boolean z = false;
        boolean z2 = false;
        if (i >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Initial capacity must be non-negative");
        if (f > 0.0f) {
            z = true;
        }
        Preconditions.checkArgument(z, "Illegal load factor");
        int A012 = AnonymousClass0rg.A01(i, (double) f);
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A04 = iArr;
        this.A00 = f;
        this.A07 = new Object[i];
        this.A05 = new int[i];
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        this.A06 = jArr;
        this.A03 = Math.max(1, (int) (((float) A012) * f));
    }

    public static int A01(@NullableDecl AnonymousClass0tI r10, Object obj, int i) {
        int[] iArr = r10.A04;
        int length = (iArr.length - 1) & i;
        int i2 = iArr[length];
        if (i2 != -1) {
            int i3 = -1;
            while (true) {
                if (((int) (r10.A06[i2] >>> 32)) != i || !Objects.equal(obj, r10.A07[i2])) {
                    int i4 = (int) r10.A06[i2];
                    if (i4 == -1) {
                        break;
                    }
                    i3 = i2;
                    i2 = i4;
                } else {
                    int i5 = r10.A05[i2];
                    if (i3 == -1) {
                        r10.A04[length] = (int) r10.A06[i2];
                    } else {
                        long[] jArr = r10.A06;
                        jArr[i3] = (jArr[i3] & -4294967296L) | (((long) ((int) jArr[i2])) & 4294967295L);
                    }
                    r10.A08(i2);
                    r10.A02--;
                    r10.A01++;
                    return i5;
                }
            }
        }
        return 0;
    }

    public int A02() {
        if (this.A02 == 0) {
            return -1;
        }
        return 0;
    }

    public int A03(int i) {
        int i2 = i + 1;
        if (i2 >= this.A02) {
            return -1;
        }
        return i2;
    }

    @CanIgnoreReturnValue
    public final int A06(@NullableDecl K k, int i) {
        if (i > 0) {
            long[] jArr = this.A06;
            Object[] objArr = this.A07;
            int[] iArr = this.A05;
            int A022 = AnonymousClass0rg.A02(k);
            int[] iArr2 = this.A04;
            int length = (iArr2.length - 1) & A022;
            int i2 = this.A02;
            int i3 = iArr2[length];
            if (i3 == -1) {
                iArr2[length] = i2;
            } else {
                while (true) {
                    long j = jArr[i3];
                    if (((int) (j >>> 32)) != A022 || !Objects.equal(k, objArr[i3])) {
                        int i4 = (int) j;
                        if (i4 == -1) {
                            jArr[i3] = (j & -4294967296L) | (((long) i2) & 4294967295L);
                            break;
                        }
                        i3 = i4;
                    } else {
                        int i5 = iArr[i3];
                        iArr[i3] = i;
                        return i5;
                    }
                }
            }
            if (i2 != Integer.MAX_VALUE) {
                int i6 = i2 + 1;
                int length2 = this.A06.length;
                if (i6 > length2) {
                    int max = Math.max(1, length2 >>> 1) + length2;
                    if (max < 0) {
                        max = Integer.MAX_VALUE;
                    }
                    if (max != length2) {
                        A09(max);
                    }
                }
                A0B(i2, k, i, A022);
                this.A02 = i6;
                if (i2 >= this.A03) {
                    int length3 = this.A04.length;
                    int i7 = length3 << 1;
                    if (length3 >= 1073741824) {
                        this.A03 = Integer.MAX_VALUE;
                    } else {
                        int i8 = ((int) (((float) i7) * this.A00)) + 1;
                        int[] iArr3 = new int[i7];
                        Arrays.fill(iArr3, -1);
                        long[] jArr2 = this.A06;
                        int length4 = iArr3.length - 1;
                        for (int i9 = 0; i9 < this.A02; i9++) {
                            int i10 = (int) (jArr2[i9] >>> 32);
                            int i11 = i10 & length4;
                            int i12 = iArr3[i11];
                            iArr3[i11] = i9;
                            jArr2[i9] = (((long) i10) << 32) | (4294967295L & ((long) i12));
                        }
                        this.A03 = i8;
                        this.A04 = iArr3;
                    }
                }
                this.A01++;
                return 0;
            }
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        throw new IllegalArgumentException(AnonymousClass006.A06("count", " must be positive but was: ", i));
    }

    public void A07() {
        this.A01++;
        Arrays.fill(this.A07, 0, this.A02, (Object) null);
        Arrays.fill(this.A05, 0, this.A02, 0);
        Arrays.fill(this.A04, -1);
        Arrays.fill(this.A06, -1L);
        this.A02 = 0;
    }

    public void A08(int i) {
        int i2 = this.A02 - 1;
        if (i < i2) {
            Object[] objArr = this.A07;
            objArr[i] = objArr[i2];
            int[] iArr = this.A05;
            iArr[i] = iArr[i2];
            objArr[i2] = null;
            iArr[i2] = 0;
            long[] jArr = this.A06;
            long j = jArr[i2];
            jArr[i] = j;
            jArr[i2] = -1;
            int[] iArr2 = this.A04;
            int length = ((int) (j >>> 32)) & (iArr2.length - 1);
            int i3 = iArr2[length];
            if (i3 == i2) {
                iArr2[length] = i;
                return;
            }
            while (true) {
                long j2 = jArr[i3];
                int i4 = (int) j2;
                if (i4 == i2) {
                    jArr[i3] = (j2 & -4294967296L) | (((long) i) & 4294967295L);
                    return;
                }
                i3 = i4;
            }
        } else {
            this.A07[i] = null;
            this.A05[i] = 0;
            this.A06[i] = -1;
        }
    }

    public void A09(int i) {
        this.A07 = Arrays.copyOf(this.A07, i);
        this.A05 = Arrays.copyOf(this.A05, i);
        long[] jArr = this.A06;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.A06 = copyOf;
    }

    public void A0B(int i, @NullableDecl K k, int i2, int i3) {
        this.A06[i] = (((long) i3) << 32) | 4294967295L;
        this.A07[i] = k;
        this.A05[i] = i2;
    }

    public final int A05(@NullableDecl Object obj) {
        int A022 = AnonymousClass0rg.A02(obj);
        int[] iArr = this.A04;
        int i = iArr[(iArr.length - 1) & A022];
        while (i != -1) {
            long j = this.A06[i];
            if (((int) (j >>> 32)) == A022 && Objects.equal(obj, this.A07[i])) {
                return i;
            }
            i = (int) j;
        }
        return -1;
    }

    public AnonymousClass0tI() {
        A0A(3, 1.0f);
    }

    public AnonymousClass0tI(int i) {
        A0A(i, 1.0f);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.0tI<K> */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass0tI(AnonymousClass0tI<? extends K> r4) {
        A0A(r4.A02, 1.0f);
        int A022 = r4.A02();
        while (A022 != -1) {
            Preconditions.checkElementIndex(A022, r4.A02);
            Object obj = r4.A07[A022];
            Preconditions.checkElementIndex(A022, r4.A02);
            A06(obj, r4.A05[A022]);
            A022 = r4.A03(A022);
        }
    }
}
