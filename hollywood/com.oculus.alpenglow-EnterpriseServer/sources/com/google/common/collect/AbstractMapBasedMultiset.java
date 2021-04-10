package com.google.common.collect;

import X.AnonymousClass0SK;
import X.AnonymousClass0YK;
import X.AnonymousClass0YM;
import X.AnonymousClass0YN;
import X.AnonymousClass0rg;
import X.AnonymousClass0tE;
import X.AnonymousClass0tI;
import X.AnonymousClass0tW;
import X.C07340r5;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public abstract class AbstractMapBasedMultiset<E> extends AnonymousClass0YK<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public transient long A00;
    public transient AnonymousClass0tI<E> A01;

    public AbstractMapBasedMultiset() {
        A07(3);
    }

    public abstract void A07(int i);

    @Override // X.AnonymousClass0YK
    public final int A04() {
        return this.A01.A02;
    }

    @Override // X.AnonymousClass0YK
    public final Iterator<E> A05() {
        return new AnonymousClass0YN(this);
    }

    @Override // X.AnonymousClass0YK
    public final Iterator<Multiset.Entry<E>> A06() {
        return new AnonymousClass0YM(this);
    }

    @Override // X.AnonymousClass0YK, X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final int A0x(@NullableDecl E e, int i) {
        String str;
        if (i == 0) {
            return A1s(e);
        }
        boolean z = true;
        boolean z2 = false;
        if (i > 0) {
            z2 = true;
        }
        if (z2) {
            int A05 = this.A01.A05(e);
            if (A05 == -1) {
                this.A01.A06(e, i);
                this.A00 += (long) i;
                return 0;
            }
            AnonymousClass0tI<E> r1 = this.A01;
            Preconditions.checkElementIndex(A05, r1.A02);
            int i2 = r1.A05[A05];
            long j = (long) i;
            long j2 = ((long) i2) + j;
            if (j2 > 2147483647L) {
                z = false;
            }
            if (z) {
                AnonymousClass0tI<E> r4 = this.A01;
                Preconditions.checkElementIndex(A05, r4.A02);
                r4.A05[A05] = (int) j2;
                this.A00 += j;
                return i2;
            }
            str = Strings.lenientFormat("too many occurrences: %s", Long.valueOf(j2));
        } else {
            str = Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i));
        }
        throw new IllegalArgumentException(str);
    }

    @Override // X.AnonymousClass0tC
    public final int A1s(@NullableDecl Object obj) {
        AnonymousClass0tI<E> r2 = this.A01;
        int A05 = r2.A05(obj);
        if (A05 == -1) {
            return 0;
        }
        return r2.A05[A05];
    }

    @Override // X.AnonymousClass0YK, X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final int A7L(@NullableDecl Object obj, int i) {
        if (i == 0) {
            return A1s(obj);
        }
        int i2 = 0;
        boolean z = false;
        if (i > 0) {
            z = true;
        }
        if (z) {
            int A05 = this.A01.A05(obj);
            if (A05 != -1) {
                AnonymousClass0tI<E> r1 = this.A01;
                Preconditions.checkElementIndex(A05, r1.A02);
                i2 = r1.A05[A05];
                if (i2 > i) {
                    AnonymousClass0tI<E> r2 = this.A01;
                    Preconditions.checkElementIndex(A05, r2.A02);
                    r2.A05[A05] = i2 - i;
                } else {
                    AnonymousClass0tI<E> r4 = this.A01;
                    AnonymousClass0tI.A01(r4, r4.A07[A05], (int) (r4.A06[A05] >>> 32));
                    i = i2;
                }
                this.A00 -= (long) i;
            }
            return i2;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i)));
    }

    @Override // X.AnonymousClass0YK, X.AnonymousClass0tC
    @CanIgnoreReturnValue
    public final int A7q(@NullableDecl E e, int i) {
        int A06;
        C07340r5.A00(i, "count");
        AnonymousClass0tI<E> r1 = this.A01;
        if (i == 0) {
            A06 = AnonymousClass0tI.A01(r1, e, AnonymousClass0rg.A02(e));
        } else {
            A06 = r1.A06(e, i);
        }
        this.A00 += (long) (i - A06);
        return A06;
    }

    @Override // X.AnonymousClass0YK, X.AnonymousClass0tC
    public final boolean A7r(@NullableDecl E e, int i, int i2) {
        long j;
        long j2;
        C07340r5.A00(i, "oldCount");
        C07340r5.A00(i2, "newCount");
        int A05 = this.A01.A05(e);
        if (A05 == -1) {
            if (i == 0) {
                if (i2 > 0) {
                    this.A01.A06(e, i2);
                    j = this.A00;
                }
                return true;
            }
            return false;
        }
        AnonymousClass0tI<E> r1 = this.A01;
        Preconditions.checkElementIndex(A05, r1.A02);
        if (r1.A05[A05] == i) {
            AnonymousClass0tI<E> r4 = this.A01;
            if (i2 == 0) {
                AnonymousClass0tI.A01(r4, r4.A07[A05], (int) (r4.A06[A05] >>> 32));
                j2 = this.A00 - ((long) i);
                this.A00 = j2;
                return true;
            }
            Preconditions.checkElementIndex(A05, r4.A02);
            r4.A05[A05] = i2;
            j = this.A00;
            i2 -= i;
        }
        return false;
        j2 = j + ((long) i2);
        this.A00 = j2;
        return true;
    }

    @Override // X.AnonymousClass0YK
    public final void clear() {
        this.A01.A07();
        this.A00 = 0;
    }

    @Override // X.AnonymousClass0tC
    public final int size() {
        return AnonymousClass0SK.A00(this.A00);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.AbstractMapBasedMultiset<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        A07(3);
        for (int i = 0; i < readInt; i++) {
            A0x(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        AnonymousClass0tW.A02(this, objectOutputStream);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new AnonymousClass0tE(this, entrySet().iterator());
    }
}
