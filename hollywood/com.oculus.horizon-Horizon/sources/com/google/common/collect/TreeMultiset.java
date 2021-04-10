package com.google.common.collect;

import X.AbstractC001306r;
import X.AnonymousClass0Bi;
import X.AnonymousClass0Bk;
import X.AnonymousClass0dO;
import X.AnonymousClass0p6;
import X.AnonymousClass0qL;
import X.AnonymousClass0rS;
import X.AnonymousClass0rT;
import X.C07130rB;
import X.C07310rs;
import X.C07320rt;
import X.C07330rw;
import X.C07340rx;
import X.C07820vP;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public final class TreeMultiset<E> extends AbstractC001306r<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 1;
    public final transient GeneralRange<E> A00;
    public final transient C07330rw<E> A01;
    public final transient C07340rx<C07330rw<E>> A02;

    @Override // X.AnonymousClass0r9
    public final int A1v(@NullableDecl Object obj) {
        C07330rw<E> r2;
        C07330rw<E> r0;
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
    private long A01(Aggregate aggregate, @NullableDecl C07330rw<E> r6) {
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
    private long A02(Aggregate aggregate, @NullableDecl C07330rw<E> r6) {
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
        AnonymousClass0rT.A00(AbstractC001306r.class, "comparator").A00(this, comparator);
        AnonymousClass0rS A002 = AnonymousClass0rT.A00(TreeMultiset.class, "range");
        BoundType boundType = BoundType.OPEN;
        A002.A00(this, new GeneralRange(comparator, false, null, boundType, false, null, boundType));
        AnonymousClass0rT.A00(TreeMultiset.class, "rootReference").A00(this, new C07340rx());
        C07330rw<E> r1 = new C07330rw<>(null, 1);
        AnonymousClass0rT.A00(TreeMultiset.class, "header").A00(this, r1);
        r1.A07 = r1;
        r1.A05 = r1;
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            A11(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @Override // X.AnonymousClass0eM
    public final int A04() {
        return C07820vP.A00(A00(Aggregate.DISTINCT));
    }

    @Override // X.AnonymousClass0eM
    public final Iterator<Multiset.Entry<E>> A06() {
        return new C07310rs(this);
    }

    @Override // X.AbstractC001306r
    public final Iterator<Multiset.Entry<E>> A07() {
        return new C07320rt(this);
    }

    @Override // X.AnonymousClass0r9, X.AnonymousClass0eM
    @CanIgnoreReturnValue
    public final int A11(@NullableDecl E e, int i) {
        AnonymousClass0p6.A00(i, "occurrences");
        if (i == 0) {
            return A1v(e);
        }
        Preconditions.checkArgument(this.A00.A03(e));
        C07340rx<C07330rw<E>> r4 = this.A02;
        T t = r4.A00;
        if (t == null) {
            comparator().compare(e, e);
            C07330rw<E> r1 = new C07330rw<>(e, i);
            C07330rw<E> r0 = this.A01;
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

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A4j(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange<>(comparator(), false, null, BoundType.OPEN, true, e, boundType)), this.A01);
    }

    @Override // X.AnonymousClass0r9, X.AnonymousClass0eM
    @CanIgnoreReturnValue
    public final int A87(@NullableDecl Object obj, int i) {
        AnonymousClass0p6.A00(i, "occurrences");
        if (i == 0) {
            return A1v(obj);
        }
        C07340rx<C07330rw<E>> r4 = this.A02;
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

    @Override // X.AnonymousClass0r9, X.AnonymousClass0eM
    @CanIgnoreReturnValue
    public final int A8d(@NullableDecl E e, int i) {
        AnonymousClass0p6.A00(i, "count");
        boolean z = true;
        if (!this.A00.A03(e)) {
            if (i != 0) {
                z = false;
            }
            Preconditions.checkArgument(z);
        } else {
            C07340rx<C07330rw<E>> r3 = this.A02;
            T t = r3.A00;
            if (t != null) {
                int[] iArr = new int[1];
                r3.A00(t, t.A0F(comparator(), e, i, iArr));
                return iArr[0];
            } else if (i > 0) {
                A11(e, i);
                return 0;
            }
        }
        return 0;
    }

    @Override // X.AnonymousClass0r9, X.AnonymousClass0eM
    @CanIgnoreReturnValue
    public final boolean A8e(@NullableDecl E e, int i, int i2) {
        AnonymousClass0p6.A00(i2, "newCount");
        AnonymousClass0p6.A00(i, "oldCount");
        Preconditions.checkArgument(this.A00.A03(e));
        C07340rx<C07330rw<E>> r2 = this.A02;
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
                A11(e, i2);
            }
            return true;
        }
    }

    @Override // X.AnonymousClass0Bk
    public final AnonymousClass0Bk<E> A9c(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange<>(comparator(), true, e, boundType, false, null, BoundType.OPEN)), this.A01);
    }

    @Override // X.AnonymousClass0eM
    public final void clear() {
        GeneralRange<E> generalRange = this.A00;
        if (generalRange.hasLowerBound || generalRange.hasUpperBound) {
            AnonymousClass0qL.A00(A06());
            return;
        }
        C07330rw<E> r3 = this.A01;
        C07330rw<E> r2 = r3.A07;
        while (r2 != r3) {
            C07330rw<E> r1 = r2.A07;
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

    @Override // X.AnonymousClass0r9
    public final int size() {
        return C07820vP.A00(A00(Aggregate.SIZE));
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(A2N().comparator());
        AnonymousClass0rT.A02(this, objectOutputStream);
    }

    @Override // X.AnonymousClass0eM
    public final Iterator<E> A05() {
        return new AnonymousClass0dO(A06());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new C07130rB(this, entrySet().iterator());
    }

    public TreeMultiset(C07340rx<C07330rw<E>> r2, GeneralRange<E> generalRange, C07330rw<E> r4) {
        super(generalRange.comparator);
        this.A02 = r2;
        this.A00 = generalRange;
        this.A01 = r4;
    }

    public TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        BoundType boundType = BoundType.OPEN;
        this.A00 = new GeneralRange<>(comparator, false, null, boundType, false, null, boundType);
        C07330rw<E> r0 = new C07330rw<>(null, 1);
        this.A01 = r0;
        r0.A07 = r0;
        r0.A05 = r0;
        this.A02 = new C07340rx<>();
    }

    public enum Aggregate {
        SIZE {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(@NullableDecl C07330rw<?> r3) {
                if (r3 == null) {
                    return 0;
                }
                return r3.A03;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(C07330rw<?> r2) {
                return r2.A01;
            }
        },
        DISTINCT {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(C07330rw<?> r2) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(@NullableDecl C07330rw<?> r3) {
                if (r3 == null) {
                    return 0;
                }
                return (long) r3.A00;
            }
        };

        public abstract int nodeAggregate(C07330rw<?> v);

        public abstract long treeAggregate(@NullableDecl C07330rw<?> v);

        /* access modifiers changed from: public */
        /* synthetic */ Aggregate(AnonymousClass0Bi r3) {
            this();
        }
    }
}
