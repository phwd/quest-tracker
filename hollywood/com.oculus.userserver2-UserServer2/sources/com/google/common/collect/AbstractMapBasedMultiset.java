package com.google.common.collect;

import X.C0103Ma;
import X.C0145Tq;
import X.NO;
import X.R0;
import X.RB;
import X.Re;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.oculus.common.build.BuildConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public abstract class AbstractMapBasedMultiset<E> extends NO<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public transient long A00;
    public transient RB<E> A01;

    @Override // X.AbstractC0120Qz
    public final int A1D(@NullableDecl Object obj) {
        RB<E> rb = this.A01;
        int A04 = rb.A04(obj);
        if (A04 == -1) {
            return 0;
        }
        return rb.A05[A04];
    }

    @Override // X.NO
    public final void clear() {
        this.A01.A06();
        this.A00 = 0;
    }

    @Override // X.AbstractC0120Qz
    public final int size() {
        return C0145Tq.A00(this.A00);
    }

    public AbstractMapBasedMultiset() {
        RB<E> ma;
        if (!(this instanceof LinkedHashMultiset)) {
            ma = new RB<>(3);
        } else {
            ma = new C0103Ma<>(3);
        }
        this.A01 = ma;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.AbstractMapBasedMultiset<E> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        RB<E> ma;
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (!(this instanceof LinkedHashMultiset)) {
            ma = new RB<>(3);
        } else {
            ma = new C0103Ma<>(3);
        }
        this.A01 = ma;
        for (int i = 0; i < readInt; i++) {
            A0k(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Re.A02(this, objectOutputStream);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        return new R0(this, entrySet().iterator());
    }
}
