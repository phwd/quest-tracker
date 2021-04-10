package com.google.common.collect;

import X.UU;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class HashMultimap extends HashMultimapGwtSerializationDependencies {
    public static final long serialVersionUID = 0;
    public transient int A00;

    public HashMultimap() {
        super(new CompactHashMap(12));
        this.A00 = 2;
        this.A00 = 2;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.A00 = 2;
        int readInt = objectInputStream.readInt();
        A05(new CompactHashMap(12));
        UU.A01(this, objectInputStream, readInt);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        UU.A02(this, objectOutputStream);
    }
}
