package com.google.common.collect;

import X.AL;
import X.AnonymousClass3L;
import X.AnonymousClass3q;
import X.AnonymousClass3s;
import X.AnonymousClass6g;
import X.AnonymousClass9M;
import X.C0192Ux;
import X.Uv;
import X.Uw;
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
public abstract class AbstractMapBasedMultiset<E> extends Uv<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public transient long A00;
    public transient AnonymousClass3s<E> A01;

    public AbstractMapBasedMultiset() {
        A08(3);
    }

    public abstract void A08(int i);

    @Override // X.Uv
    public final int A05() {
        return this.A01.A02;
    }

    @Override // X.Uv
    public final Iterator<E> A06() {
        return new C0192Ux(this);
    }

    @Override // X.Uv
    public final Iterator<Multiset.Entry<E>> A07() {
        return new Uw(this);
    }

    @Override // X.Uv, X.AnonymousClass34
    @CanIgnoreReturnValue
    public final int A11(@NullableDecl E e, int i) {
        String str;
        if (i == 0) {
            return A1c(e);
        }
        boolean z = true;
        boolean z2 = false;
        if (i > 0) {
            z2 = true;
        }
        if (z2) {
            int A02 = this.A01.A02(e);
            if (A02 == -1) {
                this.A01.A03(e, i);
                this.A00 += (long) i;
                return 0;
            }
            AnonymousClass3s<E> r1 = this.A01;
            Preconditions.checkElementIndex(A02, r1.A02);
            int i2 = r1.A05[A02];
            long j = (long) i;
            long j2 = ((long) i2) + j;
            if (j2 > 2147483647L) {
                z = false;
            }
            if (z) {
                AnonymousClass3s<E> r4 = this.A01;
                Preconditions.checkElementIndex(A02, r4.A02);
                r4.A05[A02] = (int) j2;
                this.A00 += j;
                return i2;
            }
            str = Strings.lenientFormat("too many occurrences: %s", Long.valueOf(j2));
        } else {
            str = Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i));
        }
        throw new IllegalArgumentException(str);
    }

    @Override // X.AnonymousClass34
    public final int A1c(@NullableDecl Object obj) {
        AnonymousClass3s<E> r2 = this.A01;
        int A02 = r2.A02(obj);
        if (A02 == -1) {
            return 0;
        }
        return r2.A05[A02];
    }

    @Override // X.Uv, X.AnonymousClass34
    @CanIgnoreReturnValue
    public final int A4f(@NullableDecl Object obj, int i) {
        if (i == 0) {
            return A1c(obj);
        }
        int i2 = 0;
        boolean z = false;
        if (i > 0) {
            z = true;
        }
        if (z) {
            int A02 = this.A01.A02(obj);
            if (A02 != -1) {
                AnonymousClass3s<E> r1 = this.A01;
                Preconditions.checkElementIndex(A02, r1.A02);
                i2 = r1.A05[A02];
                if (i2 > i) {
                    AnonymousClass3s<E> r2 = this.A01;
                    Preconditions.checkElementIndex(A02, r2.A02);
                    r2.A05[A02] = i2 - i;
                } else {
                    AnonymousClass3s<E> r4 = this.A01;
                    AnonymousClass3s.A00(r4, r4.A07[A02], (int) (r4.A06[A02] >>> 32));
                    i = i2;
                }
                this.A00 -= (long) i;
            }
            return i2;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("occurrences cannot be negative: %s", Integer.valueOf(i)));
    }

    @Override // X.Uv, X.AnonymousClass34
    @CanIgnoreReturnValue
    public final int A4x(@NullableDecl E e, int i) {
        int A03;
        int hashCode;
        AnonymousClass9M.A00(i, "count");
        AnonymousClass3s<E> r1 = this.A01;
        if (i == 0) {
            if (e == null) {
                hashCode = 0;
            } else {
                hashCode = e.hashCode();
            }
            A03 = AnonymousClass3s.A00(r1, e, AnonymousClass3q.A00(hashCode));
        } else {
            A03 = r1.A03(e, i);
        }
        this.A00 += (long) (i - A03);
        return A03;
    }

    @Override // X.Uv, X.AnonymousClass34
    public final boolean A4y(@NullableDecl E e, int i, int i2) {
        long j;
        long j2;
        AnonymousClass9M.A00(i, "oldCount");
        AnonymousClass9M.A00(i2, "newCount");
        int A02 = this.A01.A02(e);
        if (A02 == -1) {
            if (i == 0) {
                if (i2 > 0) {
                    this.A01.A03(e, i2);
                    j = this.A00;
                }
                return true;
            }
            return false;
        }
        AnonymousClass3s<E> r1 = this.A01;
        Preconditions.checkElementIndex(A02, r1.A02);
        if (r1.A05[A02] == i) {
            AnonymousClass3s<E> r4 = this.A01;
            if (i2 == 0) {
                AnonymousClass3s.A00(r4, r4.A07[A02], (int) (r4.A06[A02] >>> 32));
                j2 = this.A00 - ((long) i);
                this.A00 = j2;
                return true;
            }
            Preconditions.checkElementIndex(A02, r4.A02);
            r4.A05[A02] = i2;
            j = this.A00;
            i2 -= i;
        }
        return false;
        j2 = j + ((long) i2);
        this.A00 = j2;
        return true;
    }

    @Override // X.Uv
    public final void clear() {
        this.A01.A07();
        this.A00 = 0;
    }

    @Override // X.AnonymousClass34
    public final int size() {
        return AL.A00(this.A00);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.AbstractMapBasedMultiset<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        A08(3);
        for (int i = 0; i < readInt; i++) {
            A11(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        AnonymousClass6g.A00(this, objectOutputStream);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new AnonymousClass3L(this, entrySet().iterator());
    }
}
