package X;

import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1uX  reason: invalid class name */
public class AnonymousClass1uX<C, T, A> implements Cloneable {
    public long A00 = 0;
    public List<C> A01 = new ArrayList();
    public long[] A02;
    public int A03;
    public final AbstractC12071ur<C, T, A> A04;

    /* JADX WARN: Incorrect args count in method signature: (TT;ITA;I)V */
    private void A00(Object obj, int i, int i2) {
        long j;
        int i3;
        int min;
        if (i2 < 0) {
            min = Math.min(64, this.A01.size());
            j = this.A00;
            i3 = 0;
        } else {
            j = this.A02[i2];
            i3 = (i2 + 1) << 6;
            min = Math.min(this.A01.size(), i3 + 64);
            A00(obj, i, i2 - 1);
        }
        A01(obj, i, i3, min, j);
    }

    public final synchronized void A03(C c) {
        if (c != null) {
            int lastIndexOf = this.A01.lastIndexOf(c);
            if (lastIndexOf < 0 || A02(lastIndexOf)) {
                this.A01.add(c);
            }
        } else {
            throw new IllegalArgumentException("callback cannot be null");
        }
    }

    public final synchronized void A04(C c) {
        if (this.A03 == 0) {
            this.A01.remove(c);
        } else {
            int lastIndexOf = this.A01.lastIndexOf(c);
            if (lastIndexOf >= 0) {
                if (lastIndexOf < 64) {
                    this.A00 = (1 << lastIndexOf) | this.A00;
                } else {
                    int i = (lastIndexOf >> 6) - 1;
                    long[] jArr = this.A02;
                    if (jArr == null) {
                        jArr = new long[(this.A01.size() / 64)];
                    } else {
                        if (jArr.length <= i) {
                            jArr = new long[(this.A01.size() / 64)];
                            long[] jArr2 = this.A02;
                            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
                        }
                        jArr[i] = (1 << (lastIndexOf % 64)) | jArr[i];
                    }
                    this.A02 = jArr;
                    jArr[i] = (1 << (lastIndexOf % 64)) | jArr[i];
                }
            }
        }
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        AnonymousClass1uX r4;
        CloneNotSupportedException e;
        synchronized (this) {
            try {
                r4 = (AnonymousClass1uX) super.clone();
                try {
                    r4.A00 = 0;
                    r4.A02 = null;
                    r4.A03 = 0;
                    r4.A01 = new ArrayList();
                    int size = this.A01.size();
                    for (int i = 0; i < size; i++) {
                        if (!A02(i)) {
                            r4.A01.add(this.A01.get(i));
                        }
                    }
                } catch (CloneNotSupportedException e2) {
                    e = e2;
                    e.printStackTrace();
                    return r4;
                }
            } catch (CloneNotSupportedException e3) {
                e = e3;
                r4 = null;
                e.printStackTrace();
                return r4;
            }
        }
        return r4;
    }

    /* JADX WARN: Incorrect args count in method signature: (TT;ITA;IIJ)V */
    private void A01(Object obj, int i, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                AbstractC12071ur<C, T, A> r2 = this.A04;
                C c = this.A01.get(i2);
                if (!(r2 instanceof C12041uk)) {
                    c.onPropertyChanged((AbstractC12101uv) obj, i);
                }
            }
            j2 <<= 1;
            i2++;
        }
    }

    private boolean A02(int i) {
        int i2;
        long j;
        long j2;
        if (i < 64) {
            j2 = 1 << i;
            j = this.A00;
        } else {
            long[] jArr = this.A02;
            if (jArr == null || (i2 = (i >> 6) - 1) >= jArr.length) {
                return false;
            }
            j = jArr[i2];
            j2 = 1 << (i % 64);
        }
        if ((j2 & j) == 0) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Incorrect args count in method signature: (TT;ITA;)V */
    public final synchronized void A05(Object obj, int i) {
        this.A03++;
        int size = this.A01.size();
        long[] jArr = this.A02;
        int length = jArr == null ? -1 : jArr.length - 1;
        A00(obj, i, length);
        A01(obj, i, (length + 2) << 6, size, 0);
        int i2 = this.A03 - 1;
        this.A03 = i2;
        if (i2 == 0) {
            long[] jArr2 = this.A02;
            if (jArr2 != null) {
                int length2 = jArr2.length;
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        break;
                    }
                    long j = jArr2[length2];
                    if (j != 0) {
                        int i3 = (length2 + 1) << 6;
                        long j2 = Long.MIN_VALUE;
                        for (int i4 = (i3 + 64) - 1; i4 >= i3; i4--) {
                            if ((j & j2) != 0) {
                                this.A01.remove(i4);
                            }
                            j2 >>>= 1;
                        }
                        jArr2 = this.A02;
                        jArr2[length2] = 0;
                    }
                }
            }
            long j3 = this.A00;
            if (j3 != 0) {
                int i5 = 63;
                long j4 = Long.MIN_VALUE;
                do {
                    if ((j3 & j4) != 0) {
                        this.A01.remove(i5);
                    }
                    j4 >>>= 1;
                    i5--;
                } while (i5 >= 0);
                this.A00 = 0;
            }
        }
    }

    public AnonymousClass1uX(AbstractC12071ur<C, T, A> r3) {
        this.A04 = r3;
    }
}
