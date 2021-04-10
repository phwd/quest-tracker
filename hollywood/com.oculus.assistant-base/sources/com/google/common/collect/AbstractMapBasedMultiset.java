package com.google.common.collect;

import X.AbstractC1157tx;
import X.C0374Uk;
import X.C1182ue;
import X.UN;
import X.UQ;
import X.UU;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public abstract class AbstractMapBasedMultiset<E> extends AbstractC1157tx<E> implements Serializable {
    public static final long serialVersionUID = 0;
    public transient long A00;
    public transient UQ A01;

    @Override // X.UM
    public final int A1V(Object obj) {
        UQ uq = this.A01;
        int A04 = uq.A04(obj);
        if (A04 == -1) {
            return 0;
        }
        return uq.A05[A04];
    }

    public final void clear() {
        this.A01.A06();
        this.A00 = 0;
    }

    @Override // X.UM
    public final int size() {
        return C0374Uk.A00(this.A00);
    }

    public AbstractMapBasedMultiset() {
        UQ ueVar;
        if (!(this instanceof LinkedHashMultiset)) {
            ueVar = new UQ(3);
        } else {
            ueVar = new C1182ue(3);
        }
        this.A01 = ueVar;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        UQ ueVar;
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (!(this instanceof LinkedHashMultiset)) {
            ueVar = new UQ(3);
        } else {
            ueVar = new C1182ue(3);
        }
        this.A01 = ueVar;
        for (int i = 0; i < readInt; i++) {
            A19(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        UU.A03(this, objectOutputStream);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new UN(this, entrySet().iterator());
    }
}
