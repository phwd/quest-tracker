package com.google.common.collect;

import X.UU;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class ArrayListMultimap extends ArrayListMultimapGwtSerializationDependencies {
    public static final long serialVersionUID = 0;
    public transient int A00 = 3;

    public ArrayListMultimap() {
        super(new CompactHashMap(12));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.A00 = 3;
        int readInt = objectInputStream.readInt();
        A05(new CompactHashMap());
        UU.A01(this, objectInputStream, readInt);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        UU.A02(this, objectOutputStream);
    }
}
