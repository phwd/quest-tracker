package com.google.common.collect;

import X.AnonymousClass0fp;
import X.AnonymousClass0fs;
import X.AnonymousClass0th;
import X.AnonymousClass0vu;
import X.AnonymousClass0w7;
import X.AnonymousClass105;
import X.C01670fr;
import X.C05150uI;
import X.C05500vq;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.horizon.api.rating.ReviewsRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
public abstract class AbstractMapBasedMultiset<E> extends AnonymousClass0fp<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public transient long A00;
    public transient AnonymousClass0vu<E> A01;

    public AbstractMapBasedMultiset() {
        A07(3);
    }

    public abstract void A07(int i);

    @Override // X.AnonymousClass0fp
    public final int A04() {
        return this.A01.A01;
    }

    @Override // X.AnonymousClass0fp
    public final Iterator<E> A05() {
        return new AnonymousClass0fs(this);
    }

    @Override // X.AnonymousClass0fp
    public final Iterator<Multiset.Entry<E>> A06() {
        return new C01670fr(this);
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A1A(@NullableDecl E e, int i) {
        if (i == 0) {
            return A2J(e);
        }
        boolean z = true;
        boolean z2 = false;
        if (i > 0) {
            z2 = true;
        }
        if (z2) {
            int A06 = this.A01.A06(e);
            if (A06 == -1) {
                this.A01.A07(e, i);
                this.A00 += (long) i;
                return 0;
            }
            AnonymousClass0vu<E> r1 = this.A01;
            Preconditions.checkElementIndex(A06, r1.A01);
            int i2 = r1.A04[A06];
            long j = (long) i;
            long j2 = ((long) i2) + j;
            if (j2 > 2147483647L) {
                z = false;
            }
            if (z) {
                AnonymousClass0vu<E> r4 = this.A01;
                Preconditions.checkElementIndex(A06, r4.A01);
                r4.A04[A06] = (int) j2;
                this.A00 += j;
                return i2;
            }
            throw new IllegalArgumentException(Strings.lenientFormat("too many occurrences: %s", Long.valueOf(j2)));
        }
        throw new IllegalArgumentException(Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i)));
    }

    @Override // X.AbstractC05490vp
    public final int A2J(@NullableDecl Object obj) {
        AnonymousClass0vu<E> r2 = this.A01;
        int A06 = r2.A06(obj);
        if (A06 == -1) {
            return 0;
        }
        return r2.A04[A06];
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A92(@NullableDecl Object obj, int i) {
        if (i == 0) {
            return A2J(obj);
        }
        int i2 = 0;
        boolean z = false;
        if (i > 0) {
            z = true;
        }
        if (z) {
            int A06 = this.A01.A06(obj);
            if (A06 != -1) {
                AnonymousClass0vu<E> r1 = this.A01;
                Preconditions.checkElementIndex(A06, r1.A01);
                i2 = r1.A04[A06];
                if (i2 > i) {
                    AnonymousClass0vu<E> r2 = this.A01;
                    Preconditions.checkElementIndex(A06, r2.A01);
                    r2.A04[A06] = i2 - i;
                } else {
                    AnonymousClass0vu<E> r4 = this.A01;
                    AnonymousClass0vu.A01(r4, r4.A06[A06], (int) (r4.A05[A06] >>> 32));
                    i = i2;
                }
                this.A00 -= (long) i;
            }
            return i2;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i)));
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    @CanIgnoreReturnValue
    public final int A9n(@NullableDecl E e, int i) {
        int A07;
        AnonymousClass0th.A00(i, ReviewsRequest.KEY_COUNT);
        AnonymousClass0vu<E> r1 = this.A01;
        if (i == 0) {
            A07 = AnonymousClass0vu.A01(r1, e, C05150uI.A02(e));
        } else {
            A07 = r1.A07(e, i);
        }
        this.A00 += (long) (i - A07);
        return A07;
    }

    @Override // X.AnonymousClass0fp, X.AbstractC05490vp
    public final boolean A9o(@NullableDecl E e, int i, int i2) {
        long j;
        long j2;
        AnonymousClass0th.A00(i, "oldCount");
        AnonymousClass0th.A00(i2, "newCount");
        int A06 = this.A01.A06(e);
        if (A06 == -1) {
            if (i == 0) {
                if (i2 > 0) {
                    this.A01.A07(e, i2);
                    j = this.A00;
                }
                return true;
            }
            return false;
        }
        AnonymousClass0vu<E> r1 = this.A01;
        Preconditions.checkElementIndex(A06, r1.A01);
        if (r1.A04[A06] == i) {
            AnonymousClass0vu<E> r4 = this.A01;
            if (i2 == 0) {
                AnonymousClass0vu.A01(r4, r4.A06[A06], (int) (r4.A05[A06] >>> 32));
                j2 = this.A00 - ((long) i);
                this.A00 = j2;
                return true;
            }
            Preconditions.checkElementIndex(A06, r4.A01);
            r4.A04[A06] = i2;
            j = this.A00;
            i2 -= i;
        }
        return false;
        j2 = j + ((long) i2);
        this.A00 = j2;
        return true;
    }

    @Override // X.AnonymousClass0fp
    public final void clear() {
        this.A01.A08();
        this.A00 = 0;
    }

    @Override // X.AbstractC05490vp
    public final int size() {
        return AnonymousClass105.A00(this.A00);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.AbstractMapBasedMultiset<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        A07(3);
        for (int i = 0; i < readInt; i++) {
            A1A(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        AnonymousClass0w7.A01(this, objectOutputStream);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new C05500vq(this, entrySet().iterator());
    }
}
