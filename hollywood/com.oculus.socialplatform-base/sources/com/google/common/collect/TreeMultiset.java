package com.google.common.collect;

import X.AnonymousClass0Bh;
import X.AnonymousClass0M9;
import X.AnonymousClass0MC;
import X.AnonymousClass0f3;
import X.AnonymousClass0th;
import X.AnonymousClass0w6;
import X.AnonymousClass0w7;
import X.AnonymousClass0wX;
import X.AnonymousClass0wY;
import X.AnonymousClass105;
import X.C05500vq;
import X.C05650wb;
import X.C05660wc;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.horizon.api.rating.ReviewsRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public final class TreeMultiset<E> extends AnonymousClass0Bh<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 1;
    public final transient GeneralRange<E> A00;
    public final transient C05650wb<E> A01;
    public final transient C05660wc<C05650wb<E>> A02;

    @Override // X.AbstractC05490vp
    public final int A2J(@NullableDecl Object obj) {
        C05650wb<E> r2;
        C05650wb<E> r0;
        try {
            T t = this.A02.A00;
            if (this.A00.A03(obj) && t != null) {
                Comparator<? super E> comparator = comparator();
                int compare = comparator.compare(obj, t.A08);
                if (compare < 0) {
                    r2 = t.A04;
                } else if (compare <= 0) {
                    return t.A01;
                } else {
                    r2 = t.A06;
                }
                if (r2 == null) {
                    return 0;
                }
                int compare2 = comparator.compare(obj, r2.A08);
                if (compare2 < 0) {
                    r0 = r2.A04;
                } else if (compare2 <= 0) {
                    return r2.A01;
                } else {
                    r0 = r2.A06;
                }
                if (r0 != null) {
                    return r0.A0B(comparator, obj);
                }
                return 0;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    private long A00(Aggregate aggregate) {
        T t = this.A02.A00;
        long treeAggregate = aggregate.treeAggregate(t);
        GeneralRange<E> generalRange = this.A00;
        if (generalRange.hasLowerBound) {
            treeAggregate -= A02(aggregate, t);
        }
        if (generalRange.hasUpperBound) {
            return treeAggregate - A01(aggregate, t);
        }
        return treeAggregate;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private long A01(Aggregate aggregate, @NullableDecl C05650wb<E> r6) {
        long j;
        long j2;
        if (r6 == null) {
            return 0;
        }
        Comparator<? super E> comparator = comparator();
        GeneralRange<E> generalRange = this.A00;
        int compare = comparator.compare(generalRange.upperEndpoint, r6.A08);
        if (compare > 0) {
            return A01(aggregate, r6.A06);
        }
        if (compare == 0) {
            switch (generalRange.upperBoundType.ordinal()) {
                case 0:
                    j = (long) aggregate.nodeAggregate(r6);
                    j2 = aggregate.treeAggregate(r6.A06);
                    break;
                case 1:
                    return aggregate.treeAggregate(r6.A06);
                default:
                    throw new AssertionError();
            }
        } else {
            j = aggregate.treeAggregate(r6.A06) + ((long) aggregate.nodeAggregate(r6));
            j2 = A01(aggregate, r6.A04);
        }
        return j + j2;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private long A02(Aggregate aggregate, @NullableDecl C05650wb<E> r6) {
        long j;
        long j2;
        if (r6 == null) {
            return 0;
        }
        Comparator<? super E> comparator = comparator();
        GeneralRange<E> generalRange = this.A00;
        int compare = comparator.compare(generalRange.lowerEndpoint, r6.A08);
        if (compare < 0) {
            return A02(aggregate, r6.A04);
        }
        if (compare == 0) {
            switch (generalRange.lowerBoundType.ordinal()) {
                case 0:
                    j = (long) aggregate.nodeAggregate(r6);
                    j2 = aggregate.treeAggregate(r6.A04);
                    break;
                case 1:
                    return aggregate.treeAggregate(r6.A04);
                default:
                    throw new AssertionError();
            }
        } else {
            j = aggregate.treeAggregate(r6.A04) + ((long) aggregate.nodeAggregate(r6));
            j2 = A02(aggregate, r6.A06);
        }
        return j + j2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: com.google.common.collect.TreeMultiset<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        AnonymousClass0w7.A00(AnonymousClass0Bh.class, "comparator").A00(this, comparator);
        AnonymousClass0w6 A002 = AnonymousClass0w7.A00(TreeMultiset.class, "range");
        BoundType boundType = BoundType.OPEN;
        A002.A00(this, new GeneralRange(comparator, false, null, boundType, false, null, boundType));
        AnonymousClass0w7.A00(TreeMultiset.class, "rootReference").A00(this, new C05660wc());
        C05650wb<E> r1 = new C05650wb<>(null, 1);
        AnonymousClass0w7.A00(TreeMultiset.class, "header").A00(this, r1);
        r1.A07 = r1;
        r1.A05 = r1;
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            A1A(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @Override // X.AnonymousClass0fp
    public final int A04() {
        return AnonymousClass105.A00(A00(Aggregate.DISTINCT));
    }

    @Override // X.AnonymousClass0fp
    public final Iterator<Multiset.Entry<E>> A06() {
        return new AnonymousClass0wX(this);
    }

    @Override // X.AnonymousClass0Bh
    public final Iterator<Multiset.Entry<E>> A07() {
        return new AnonymousClass0wY(this);
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A1A(@NullableDecl E e, int i) {
        AnonymousClass0th.A00(i, "occurrences");
        if (i == 0) {
            return A2J(e);
        }
        Preconditions.checkArgument(this.A00.A03(e));
        C05660wc<C05650wb<E>> r4 = this.A02;
        T t = r4.A00;
        if (t == null) {
            comparator().compare(e, e);
            C05650wb<E> r1 = new C05650wb<>(e, i);
            C05650wb<E> r0 = this.A01;
            r0.A07 = r1;
            r1.A05 = r0;
            r1.A07 = r0;
            r0.A05 = r1;
            r4.A00(t, r1);
            return 0;
        }
        int[] iArr = new int[1];
        r4.A00(t, t.A0D(comparator(), e, i, iArr));
        return iArr[0];
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> A5T(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange<>(comparator(), false, null, BoundType.OPEN, true, e, boundType)), this.A01);
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A92(@NullableDecl Object obj, int i) {
        AnonymousClass0th.A00(i, "occurrences");
        if (i == 0) {
            return A2J(obj);
        }
        C05660wc<C05650wb<E>> r4 = this.A02;
        T t = r4.A00;
        int[] iArr = new int[1];
        try {
            if (this.A00.A03(obj) && t != null) {
                r4.A00(t, t.A0E(comparator(), obj, i, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A9n(@NullableDecl E e, int i) {
        AnonymousClass0th.A00(i, ReviewsRequest.KEY_COUNT);
        boolean z = true;
        if (!this.A00.A03(e)) {
            if (i != 0) {
                z = false;
            }
            Preconditions.checkArgument(z);
        } else {
            C05660wc<C05650wb<E>> r3 = this.A02;
            T t = r3.A00;
            if (t != null) {
                int[] iArr = new int[1];
                r3.A00(t, t.A0F(comparator(), e, i, iArr));
                return iArr[0];
            } else if (i > 0) {
                A1A(e, i);
                return 0;
            }
        }
        return 0;
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final boolean A9o(@NullableDecl E e, int i, int i2) {
        AnonymousClass0th.A00(i2, "newCount");
        AnonymousClass0th.A00(i, "oldCount");
        Preconditions.checkArgument(this.A00.A03(e));
        C05660wc<C05650wb<E>> r2 = this.A02;
        T t = r2.A00;
        if (t != null) {
            int[] iArr = new int[1];
            r2.A00(t, t.A0C(comparator(), e, i, i2, iArr));
            if (iArr[0] == i) {
                return true;
            }
            return false;
        } else if (i != 0) {
            return false;
        } else {
            if (i2 > 0) {
                A1A(e, i2);
            }
            return true;
        }
    }

    @Override // X.AnonymousClass0MC
    public final AnonymousClass0MC<E> AAg(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange<>(comparator(), true, e, boundType, false, null, BoundType.OPEN)), this.A01);
    }

    @Override // X.AnonymousClass0fp
    public final void clear() {
        GeneralRange<E> generalRange = this.A00;
        if (generalRange.hasLowerBound || generalRange.hasUpperBound) {
            Iterator<Multiset.Entry<E>> A06 = A06();
            if (A06 == null) {
                throw null;
            }
            while (A06.hasNext()) {
                A06.next();
                A06.remove();
            }
            return;
        }
        C05650wb<E> r3 = this.A01;
        C05650wb<E> r2 = r3.A07;
        while (r2 != r3) {
            C05650wb<E> r1 = r2.A07;
            r2.A01 = 0;
            r2.A04 = null;
            r2.A06 = null;
            r2.A05 = null;
            r2.A07 = null;
            r2 = r1;
        }
        r3.A07 = r3;
        r3.A05 = r3;
        this.A02.A00 = null;
    }

    @Override // X.AbstractC05490vp
    public final int size() {
        return AnonymousClass105.A00(A00(Aggregate.SIZE));
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(A2k().comparator());
        AnonymousClass0w7.A01(this, objectOutputStream);
    }

    @Override // X.AnonymousClass0fp
    public final Iterator<E> A05() {
        return new AnonymousClass0f3(A06());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new C05500vq(this, entrySet().iterator());
    }

    public TreeMultiset(C05660wc<C05650wb<E>> r2, GeneralRange<E> generalRange, C05650wb<E> r4) {
        super(generalRange.comparator);
        this.A02 = r2;
        this.A00 = generalRange;
        this.A01 = r4;
    }

    public TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        BoundType boundType = BoundType.OPEN;
        this.A00 = new GeneralRange<>(comparator, false, null, boundType, false, null, boundType);
        C05650wb<E> r0 = new C05650wb<>(null, 1);
        this.A01 = r0;
        r0.A07 = r0;
        r0.A05 = r0;
        this.A02 = new C05660wc<>();
    }

    public enum Aggregate {
        SIZE {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(@NullableDecl C05650wb<?> r3) {
                if (r3 == null) {
                    return 0;
                }
                return r3.A03;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(C05650wb<?> r2) {
                return r2.A01;
            }
        },
        DISTINCT {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(C05650wb<?> r2) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(@NullableDecl C05650wb<?> r3) {
                if (r3 == null) {
                    return 0;
                }
                return (long) r3.A00;
            }
        };

        public abstract int nodeAggregate(C05650wb<?> v);

        public abstract long treeAggregate(@NullableDecl C05650wb<?> v);

        /* access modifiers changed from: public */
        /* synthetic */ Aggregate(AnonymousClass0M9 r3) {
            this();
        }
    }
}
