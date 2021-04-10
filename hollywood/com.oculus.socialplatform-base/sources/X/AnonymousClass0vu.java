package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.horizon.api.rating.ReviewsRequest;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
/* renamed from: X.0vu  reason: invalid class name */
public class AnonymousClass0vu<K> {
    public transient int A00;
    public transient int A01;
    public transient int A02;
    public transient int[] A03;
    public transient int[] A04;
    @VisibleForTesting
    public transient long[] A05;
    public transient Object[] A06;
    public transient float A07;

    public int A05(int i, int i2) {
        return i - 1;
    }

    public void A0B(int i, float f) {
        boolean z = false;
        boolean z2 = false;
        if (i >= 0) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "Initial capacity must be non-negative");
        if (f > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            z = true;
        }
        Preconditions.checkArgument(z, "Illegal load factor");
        int A012 = C05150uI.A01(i, (double) f);
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A03 = iArr;
        this.A07 = f;
        this.A06 = new Object[i];
        this.A04 = new int[i];
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        this.A05 = jArr;
        this.A02 = Math.max(1, (int) (((float) A012) * f));
    }

    public static int A01(@NullableDecl AnonymousClass0vu r10, Object obj, int i) {
        int[] iArr = r10.A03;
        int length = (iArr.length - 1) & i;
        int i2 = iArr[length];
        if (i2 != -1) {
            int i3 = -1;
            while (true) {
                if (((int) (r10.A05[i2] >>> 32)) != i || !Objects.equal(obj, r10.A06[i2])) {
                    int i4 = (int) r10.A05[i2];
                    if (i4 == -1) {
                        break;
                    }
                    i3 = i2;
                    i2 = i4;
                } else {
                    int i5 = r10.A04[i2];
                    if (i3 == -1) {
                        r10.A03[length] = (int) r10.A05[i2];
                    } else {
                        long[] jArr = r10.A05;
                        jArr[i3] = (jArr[i3] & -4294967296L) | (((long) ((int) jArr[i2])) & 4294967295L);
                    }
                    r10.A09(i2);
                    r10.A01--;
                    r10.A00++;
                    return i5;
                }
            }
        }
        return 0;
    }

    public static void A02(AnonymousClass0vu r11, int i) {
        if (r11.A03.length >= 1073741824) {
            r11.A02 = Integer.MAX_VALUE;
            return;
        }
        int i2 = ((int) (((float) i) * r11.A07)) + 1;
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        long[] jArr = r11.A05;
        int i3 = i - 1;
        for (int i4 = 0; i4 < r11.A01; i4++) {
            int i5 = (int) (jArr[i4] >>> 32);
            int i6 = i5 & i3;
            int i7 = iArr[i6];
            iArr[i6] = i4;
            jArr[i4] = (((long) i5) << 32) | (4294967295L & ((long) i7));
        }
        r11.A02 = i2;
        r11.A03 = iArr;
    }

    public int A03() {
        if (this.A01 == 0) {
            return -1;
        }
        return 0;
    }

    public int A04(int i) {
        int i2 = i + 1;
        if (i2 >= this.A01) {
            return -1;
        }
        return i2;
    }

    @CanIgnoreReturnValue
    public final int A07(@NullableDecl K k, int i) {
        if (i > 0) {
            long[] jArr = this.A05;
            Object[] objArr = this.A06;
            int[] iArr = this.A04;
            int A022 = C05150uI.A02(k);
            int[] iArr2 = this.A03;
            int length = (iArr2.length - 1) & A022;
            int i2 = this.A01;
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
                int length2 = this.A05.length;
                if (i6 > length2) {
                    int max = Math.max(1, length2 >>> 1) + length2;
                    if (max < 0) {
                        max = Integer.MAX_VALUE;
                    }
                    if (max != length2) {
                        A0A(max);
                    }
                }
                A0C(i2, k, i, A022);
                this.A01 = i6;
                if (i2 >= this.A02) {
                    A02(this, this.A03.length << 1);
                }
                this.A00++;
                return 0;
            }
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        throw new IllegalArgumentException(AnonymousClass006.A08(ReviewsRequest.KEY_COUNT, " must be positive but was: ", i));
    }

    public void A08() {
        this.A00++;
        Arrays.fill(this.A06, 0, this.A01, (Object) null);
        Arrays.fill(this.A04, 0, this.A01, 0);
        Arrays.fill(this.A03, -1);
        Arrays.fill(this.A05, -1L);
        this.A01 = 0;
    }

    public void A09(int i) {
        int i2 = this.A01 - 1;
        if (i < i2) {
            Object[] objArr = this.A06;
            objArr[i] = objArr[i2];
            int[] iArr = this.A04;
            iArr[i] = iArr[i2];
            objArr[i2] = null;
            iArr[i2] = 0;
            long[] jArr = this.A05;
            long j = jArr[i2];
            jArr[i] = j;
            jArr[i2] = -1;
            int[] iArr2 = this.A03;
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
            this.A06[i] = null;
            this.A04[i] = 0;
            this.A05[i] = -1;
        }
    }

    public void A0A(int i) {
        this.A06 = Arrays.copyOf(this.A06, i);
        this.A04 = Arrays.copyOf(this.A04, i);
        long[] jArr = this.A05;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(copyOf, length, i, -1L);
        }
        this.A05 = copyOf;
    }

    public void A0C(int i, @NullableDecl K k, int i2, int i3) {
        this.A05[i] = (((long) i3) << 32) | 4294967295L;
        this.A06[i] = k;
        this.A04[i] = i2;
    }

    public final int A06(@NullableDecl Object obj) {
        int A022 = C05150uI.A02(obj);
        int[] iArr = this.A03;
        int i = iArr[(iArr.length - 1) & A022];
        while (i != -1) {
            long j = this.A05[i];
            if (((int) (j >>> 32)) == A022 && Objects.equal(obj, this.A06[i])) {
                return i;
            }
            i = (int) j;
        }
        return -1;
    }

    public AnonymousClass0vu() {
        A0B(3, 1.0f);
    }

    public AnonymousClass0vu(int i) {
        A0B(i, 1.0f);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.0vu<K> */
    /* JADX WARN: Multi-variable type inference failed */
    public AnonymousClass0vu(AnonymousClass0vu<? extends K> r4) {
        A0B(r4.A01, 1.0f);
        int A032 = r4.A03();
        while (A032 != -1) {
            Preconditions.checkElementIndex(A032, r4.A01);
            Object obj = r4.A06[A032];
            Preconditions.checkElementIndex(A032, r4.A01);
            A07(obj, r4.A04[A032]);
            A032 = r4.A04(A032);
        }
    }
}
