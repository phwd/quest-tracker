package com.google.common.collect;

import X.AbstractC0118Bl;
import X.Bg;
import X.C0361Tm;
import X.C0368Ue;
import X.C0369Uf;
import X.C0374Uk;
import X.UB;
import X.UN;
import X.UT;
import X.UU;
import X.Z2;
import com.google.common.base.Preconditions;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

public final class TreeMultiset<E> extends Z2<E> implements Serializable {
    public static final long serialVersionUID = 1;
    public final transient GeneralRange A00;
    public final transient C0368Ue A01;
    public final transient C0369Uf A02;

    @Override // X.UM
    public final int A1V(Object obj) {
        C0368Ue ue;
        C0368Ue ue2;
        try {
            C0368Ue ue3 = (C0368Ue) this.A02.A00;
            if (this.A00.A03(obj) && ue3 != null) {
                Comparator comparator = comparator();
                int compare = comparator.compare(obj, ue3.A08);
                if (compare < 0) {
                    ue = ue3.A04;
                } else if (compare <= 0) {
                    return ue3.A01;
                } else {
                    ue = ue3.A06;
                }
                if (ue == null) {
                    return 0;
                }
                int compare2 = comparator.compare(obj, ue.A08);
                if (compare2 < 0) {
                    ue2 = ue.A04;
                } else if (compare2 <= 0) {
                    return ue.A01;
                } else {
                    ue2 = ue.A06;
                }
                if (ue2 != null) {
                    return ue2.A0B(comparator, obj);
                }
                return 0;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private long A00(Aggregate aggregate, C0368Ue ue) {
        long j;
        long j2;
        if (ue == null) {
            return 0;
        }
        Comparator comparator = comparator();
        GeneralRange generalRange = this.A00;
        int compare = comparator.compare(generalRange.upperEndpoint, ue.A08);
        if (compare > 0) {
            return A00(aggregate, ue.A06);
        }
        if (compare == 0) {
            switch (generalRange.upperBoundType.ordinal()) {
                case 0:
                    j = (long) aggregate.nodeAggregate(ue);
                    j2 = aggregate.treeAggregate(ue.A06);
                    break;
                case 1:
                    return aggregate.treeAggregate(ue.A06);
                default:
                    throw new AssertionError();
            }
        } else {
            j = aggregate.treeAggregate(ue.A06) + ((long) aggregate.nodeAggregate(ue));
            j2 = A00(aggregate, ue.A04);
        }
        return j + j2;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private long A01(Aggregate aggregate, C0368Ue ue) {
        long j;
        long j2;
        if (ue == null) {
            return 0;
        }
        Comparator comparator = comparator();
        GeneralRange generalRange = this.A00;
        int compare = comparator.compare(generalRange.lowerEndpoint, ue.A08);
        if (compare < 0) {
            return A01(aggregate, ue.A04);
        }
        if (compare == 0) {
            switch (generalRange.lowerBoundType.ordinal()) {
                case 0:
                    j = (long) aggregate.nodeAggregate(ue);
                    j2 = aggregate.treeAggregate(ue.A04);
                    break;
                case 1:
                    return aggregate.treeAggregate(ue.A04);
                default:
                    throw new AssertionError();
            }
        } else {
            j = aggregate.treeAggregate(ue.A04) + ((long) aggregate.nodeAggregate(ue));
            j2 = A01(aggregate, ue.A06);
        }
        return j + j2;
    }

    public static long A02(TreeMultiset treeMultiset, Aggregate aggregate) {
        C0368Ue ue = (C0368Ue) treeMultiset.A02.A00;
        long treeAggregate = aggregate.treeAggregate(ue);
        GeneralRange generalRange = treeMultiset.A00;
        if (generalRange.hasLowerBound) {
            treeAggregate -= treeMultiset.A01(aggregate, ue);
        }
        if (generalRange.hasUpperBound) {
            return treeAggregate - treeMultiset.A00(aggregate, ue);
        }
        return treeAggregate;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        UU.A00(Z2.class, "comparator").A00(this, comparator);
        UT A002 = UU.A00(TreeMultiset.class, "range");
        BoundType boundType = BoundType.OPEN;
        A002.A00(this, new GeneralRange(comparator, false, null, boundType, false, null, boundType));
        UU.A00(TreeMultiset.class, "rootReference").A00(this, new C0369Uf());
        C0368Ue ue = new C0368Ue(null, 1);
        UU.A00(TreeMultiset.class, "header").A00(this, ue);
        ue.A07 = ue;
        ue.A05 = ue;
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            A19(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @Override // X.AbstractC1157tx, X.UM
    public final int A19(Object obj, int i) {
        C0361Tm.A00(i, "occurrences");
        if (i == 0) {
            return A1V(obj);
        }
        Preconditions.checkArgument(this.A00.A03(obj));
        C0369Uf uf = this.A02;
        C0368Ue ue = (C0368Ue) uf.A00;
        if (ue == null) {
            comparator().compare(obj, obj);
            C0368Ue ue2 = new C0368Ue(obj, i);
            C0368Ue ue3 = this.A01;
            ue3.A07 = ue2;
            ue2.A05 = ue3;
            ue2.A07 = ue3;
            ue3.A05 = ue2;
            uf.A00(ue, ue2);
            return 0;
        }
        int[] iArr = new int[1];
        uf.A00(ue, ue.A0D(comparator(), obj, i, iArr));
        return iArr[0];
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A3B(Object obj, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange(comparator(), false, null, BoundType.OPEN, true, obj, boundType)), this.A01);
    }

    @Override // X.AbstractC1157tx, X.UM
    public final int A4m(Object obj, int i) {
        C0361Tm.A00(i, "occurrences");
        if (i == 0) {
            return A1V(obj);
        }
        C0369Uf uf = this.A02;
        C0368Ue ue = (C0368Ue) uf.A00;
        int[] iArr = new int[1];
        try {
            if (this.A00.A03(obj) && ue != null) {
                uf.A00(ue, ue.A0E(comparator(), obj, i, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // X.AbstractC1157tx, X.UM
    public final int A4z(Object obj, int i) {
        C0361Tm.A00(i, "count");
        boolean z = true;
        if (!this.A00.A03(obj)) {
            if (i != 0) {
                z = false;
            }
            Preconditions.checkArgument(z);
        } else {
            C0369Uf uf = this.A02;
            C0368Ue ue = (C0368Ue) uf.A00;
            if (ue != null) {
                int[] iArr = new int[1];
                uf.A00(ue, ue.A0F(comparator(), obj, i, iArr));
                return iArr[0];
            } else if (i > 0) {
                A19(obj, i);
                return 0;
            }
        }
        return 0;
    }

    @Override // X.AbstractC1157tx, X.UM
    public final boolean A50(Object obj, int i, int i2) {
        C0361Tm.A00(i2, "newCount");
        C0361Tm.A00(i, "oldCount");
        Preconditions.checkArgument(this.A00.A03(obj));
        C0369Uf uf = this.A02;
        C0368Ue ue = (C0368Ue) uf.A00;
        if (ue != null) {
            int[] iArr = new int[1];
            uf.A00(ue, ue.A0C(comparator(), obj, i, i2, iArr));
            if (iArr[0] == i) {
                return true;
            }
            return false;
        } else if (i != 0) {
            return false;
        } else {
            if (i2 > 0) {
                A19(obj, i2);
            }
            return true;
        }
    }

    @Override // X.AbstractC0118Bl
    public final AbstractC0118Bl A5F(Object obj, BoundType boundType) {
        return new TreeMultiset(this.A02, this.A00.A00(new GeneralRange(comparator(), true, obj, boundType, false, null, BoundType.OPEN)), this.A01);
    }

    public final void clear() {
        GeneralRange generalRange = this.A00;
        if (generalRange.hasLowerBound || generalRange.hasUpperBound) {
            UB.A00(A03());
            return;
        }
        C0368Ue ue = this.A01;
        C0368Ue ue2 = ue.A07;
        while (ue2 != ue) {
            C0368Ue ue3 = ue2.A07;
            ue2.A01 = 0;
            ue2.A04 = null;
            ue2.A06 = null;
            ue2.A05 = null;
            ue2.A07 = null;
            ue2 = ue3;
        }
        ue.A07 = ue;
        ue.A05 = ue;
        this.A02.A00 = null;
    }

    @Override // X.UM
    public final int size() {
        return C0374Uk.A00(A02(this, Aggregate.SIZE));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(A1m().comparator());
        UU.A03(this, objectOutputStream);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new UN(this, entrySet().iterator());
    }

    public TreeMultiset(C0369Uf uf, GeneralRange generalRange, C0368Ue ue) {
        super(generalRange.comparator);
        this.A02 = uf;
        this.A00 = generalRange;
        this.A01 = ue;
    }

    public TreeMultiset(Comparator comparator) {
        super(comparator);
        BoundType boundType = BoundType.OPEN;
        this.A00 = new GeneralRange(comparator, false, null, boundType, false, null, boundType);
        C0368Ue ue = new C0368Ue(null, 1);
        this.A01 = ue;
        ue.A07 = ue;
        ue.A05 = ue;
        this.A02 = new C0369Uf();
    }

    public enum Aggregate {
        SIZE {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(C0368Ue ue) {
                if (ue == null) {
                    return 0;
                }
                return ue.A03;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(C0368Ue ue) {
                return ue.A01;
            }
        },
        DISTINCT {
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public int nodeAggregate(C0368Ue ue) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            public long treeAggregate(C0368Ue ue) {
                if (ue == null) {
                    return 0;
                }
                return (long) ue.A00;
            }
        };

        public abstract int nodeAggregate(C0368Ue ue);

        public abstract long treeAggregate(C0368Ue ue);

        /* access modifiers changed from: public */
        /* synthetic */ Aggregate(Bg bg) {
            this();
        }
    }
}
