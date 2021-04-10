package com.google.common.collect;

import X.AnonymousClass6s;
import X.AnonymousClass9I;
import X.AnonymousClass9K;
import X.C0145Tq;
import X.QN;
import X.R0;
import X.Ra;
import X.Re;
import X.TP;
import X.TQ;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.common.build.BuildConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public final class TreeMultiset<E> extends AnonymousClass6s<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 1;
    public final transient GeneralRange<E> A00;
    public final transient TP<E> A01;
    public final transient TQ<TP<E>> A02;

    @Override // X.AbstractC0120Qz
    public final int A1D(@NullableDecl Object obj) {
        TP<E> tp;
        TP<E> tp2;
        try {
            T t = this.A02.A00;
            if (this.A00.A03(obj) && t != null) {
                Comparator<? super E> comparator = comparator();
                int compare = comparator.compare(obj, t.A08);
                if (compare < 0) {
                    tp = t.A04;
                } else if (compare <= 0) {
                    return t.A01;
                } else {
                    tp = t.A06;
                }
                if (tp == null) {
                    return 0;
                }
                int compare2 = comparator.compare(obj, tp.A08);
                if (compare2 < 0) {
                    tp2 = tp.A04;
                } else if (compare2 <= 0) {
                    return tp.A01;
                } else {
                    tp2 = tp.A06;
                }
                if (tp2 != null) {
                    return tp2.A0B(comparator, obj);
                }
                return 0;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private long A00(Aggregate aggregate, @NullableDecl TP<E> tp) {
        long j;
        long j2;
        if (tp == null) {
            return 0;
        }
        Comparator<? super E> comparator = comparator();
        GeneralRange<E> generalRange = this.A00;
        int compare = comparator.compare(generalRange.upperEndpoint, tp.A08);
        if (compare > 0) {
            return A00(aggregate, tp.A06);
        }
        if (compare == 0) {
            switch (generalRange.upperBoundType.ordinal()) {
                case 0:
                    j = (long) aggregate.nodeAggregate(tp);
                    j2 = aggregate.treeAggregate(tp.A06);
                    break;
                case 1:
                    return aggregate.treeAggregate(tp.A06);
                default:
                    throw new AssertionError();
            }
        } else {
            j = aggregate.treeAggregate(tp.A06) + ((long) aggregate.nodeAggregate(tp));
            j2 = A00(aggregate, tp.A04);
        }
        return j + j2;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private long A01(Aggregate aggregate, @NullableDecl TP<E> tp) {
        long j;
        long j2;
        if (tp == null) {
            return 0;
        }
        Comparator<? super E> comparator = comparator();
        GeneralRange<E> generalRange = this.A00;
        int compare = comparator.compare(generalRange.lowerEndpoint, tp.A08);
        if (compare < 0) {
            return A01(aggregate, tp.A04);
        }
        if (compare == 0) {
            switch (generalRange.lowerBoundType.ordinal()) {
                case 0:
                    j = (long) aggregate.nodeAggregate(tp);
                    j2 = aggregate.treeAggregate(tp.A04);
                    break;
                case 1:
                    return aggregate.treeAggregate(tp.A04);
                default:
                    throw new AssertionError();
            }
        } else {
            j = aggregate.treeAggregate(tp.A04) + ((long) aggregate.nodeAggregate(tp));
            j2 = A01(aggregate, tp.A06);
        }
        return j + j2;
    }

    public static long A02(TreeMultiset treeMultiset, Aggregate aggregate) {
        T t = treeMultiset.A02.A00;
        long treeAggregate = aggregate.treeAggregate(t);
        GeneralRange<E> generalRange = treeMultiset.A00;
        if (generalRange.hasLowerBound) {
            treeAggregate -= treeMultiset.A01(aggregate, t);
        }
        if (generalRange.hasUpperBound) {
            return treeAggregate - treeMultiset.A00(aggregate, t);
        }
        return treeAggregate;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: com.google.common.collect.TreeMultiset<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        Re.A00(AnonymousClass6s.class, "comparator").A00(this, comparator);
        Ra A002 = Re.A00(TreeMultiset.class, "range");
        BoundType boundType = BoundType.OPEN;
        A002.A00(this, new GeneralRange(comparator, false, null, boundType, false, null, boundType));
        Re.A00(TreeMultiset.class, "rootReference").A00(this, new TQ());
        TP<E> tp = new TP<>(null, 1);
        Re.A00(TreeMultiset.class, "header").A00(this, tp);
        tp.A07 = tp;
        tp.A05 = tp;
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            A0k(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @Override // X.AbstractC0120Qz, X.NO
    @CanIgnoreReturnValue
    public final int A0k(@NullableDecl E e, int i) {
        QN.A00(i, "occurrences");
        if (i == 0) {
            return A1D(e);
        }
        if (this.A00.A03(e)) {
            TQ<TP<E>> tq = this.A02;
            T t = tq.A00;
            if (t == null) {
                comparator().compare(e, e);
                TP<E> tp = new TP<>(e, i);
                TP<E> tp2 = this.A01;
                tp2.A07 = tp;
                tp.A05 = tp2;
                tp.A07 = tp2;
                tp2.A05 = tp;
                tq.A00(t, tp);
                return 0;
            }
            int[] iArr = new int[1];
            tq.A00(t, t.A0D(comparator(), e, i, iArr));
            return iArr[0];
        }
        throw new IllegalArgumentException();
    }

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A26(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange<>(comparator(), false, null, BoundType.OPEN, true, e, boundType)), this.A01);
    }

    @Override // X.AbstractC0120Qz, X.NO
    @CanIgnoreReturnValue
    public final int A3F(@NullableDecl Object obj, int i) {
        QN.A00(i, "occurrences");
        if (i == 0) {
            return A1D(obj);
        }
        TQ<TP<E>> tq = this.A02;
        T t = tq.A00;
        int[] iArr = new int[1];
        try {
            if (this.A00.A03(obj) && t != null) {
                tq.A00(t, t.A0E(comparator(), obj, i, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // X.AbstractC0120Qz, X.NO
    @CanIgnoreReturnValue
    public final int A3X(@NullableDecl E e, int i) {
        QN.A00(i, "count");
        if (this.A00.A03(e)) {
            TQ<TP<E>> tq = this.A02;
            T t = tq.A00;
            if (t != null) {
                int[] iArr = new int[1];
                tq.A00(t, t.A0F(comparator(), e, i, iArr));
                return iArr[0];
            } else if (i > 0) {
                A0k(e, i);
            }
        } else if (i != 0) {
            throw new IllegalArgumentException();
        }
        return 0;
    }

    @Override // X.AbstractC0120Qz, X.NO
    @CanIgnoreReturnValue
    public final boolean A3Y(@NullableDecl E e, int i, int i2) {
        QN.A00(i2, "newCount");
        QN.A00(i, "oldCount");
        if (this.A00.A03(e)) {
            TQ<TP<E>> tq = this.A02;
            T t = tq.A00;
            if (t != null) {
                int[] iArr = new int[1];
                tq.A00(t, t.A0C(comparator(), e, i, i2, iArr));
                if (iArr[0] == i) {
                    return true;
                }
                return false;
            } else if (i != 0) {
                return false;
            } else {
                if (i2 > 0) {
                    A0k(e, i2);
                }
                return true;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // X.AnonymousClass9K
    public final AnonymousClass9K<E> A3p(@NullableDecl E e, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange<>(comparator(), true, e, boundType, false, null, BoundType.OPEN)), this.A01);
    }

    @Override // X.NO
    public final void clear() {
        GeneralRange<E> generalRange = this.A00;
        if (generalRange.hasLowerBound || generalRange.hasUpperBound) {
            Iterator<Multiset.Entry<E>> A03 = A03();
            if (A03 == null) {
                throw null;
            }
            while (A03.hasNext()) {
                A03.next();
                A03.remove();
            }
            return;
        }
        TP<E> tp = this.A01;
        TP<E> tp2 = tp.A07;
        while (tp2 != tp) {
            TP<E> tp3 = tp2.A07;
            tp2.A01 = 0;
            tp2.A04 = null;
            tp2.A06 = null;
            tp2.A05 = null;
            tp2.A07 = null;
            tp2 = tp3;
        }
        tp.A07 = tp;
        tp.A05 = tp;
        this.A02.A00 = null;
    }

    @Override // X.AbstractC0120Qz
    public final int size() {
        return C0145Tq.A00(A02(this, Aggregate.SIZE));
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(A1R().comparator());
        Re.A02(this, objectOutputStream);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new R0(this, entrySet().iterator());
    }

    public TreeMultiset(TQ<TP<E>> tq, GeneralRange<E> generalRange, TP<E> tp) {
        super(generalRange.comparator);
        this.A02 = tq;
        this.A00 = generalRange;
        this.A01 = tp;
    }

    public TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        BoundType boundType = BoundType.OPEN;
        this.A00 = new GeneralRange<>(comparator, false, null, boundType, false, null, boundType);
        TP<E> tp = new TP<>(null, 1);
        this.A01 = tp;
        tp.A07 = tp;
        tp.A05 = tp;
        this.A02 = new TQ<>();
    }

    public enum Aggregate {
        SIZE {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(@NullableDecl TP<?> tp) {
                if (tp == null) {
                    return 0;
                }
                return tp.A03;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(TP<?> tp) {
                return tp.A01;
            }
        },
        DISTINCT {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(TP<?> tp) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(@NullableDecl TP<?> tp) {
                if (tp == null) {
                    return 0;
                }
                return (long) tp.A00;
            }
        };

        public abstract int nodeAggregate(TP<?> tp);

        public abstract long treeAggregate(@NullableDecl TP<?> tp);

        /* access modifiers changed from: public */
        /* synthetic */ Aggregate(AnonymousClass9I r3) {
            this();
        }
    }
}
