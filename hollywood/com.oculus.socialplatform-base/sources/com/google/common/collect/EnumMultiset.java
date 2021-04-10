package com.google.common.collect;

import X.AnonymousClass0fp;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Enum;

@GwtCompatible(emulated = true)
public final class EnumMultiset<E extends Enum<E>> extends AnonymousClass0fp<E> implements Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        throw null;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        throw null;
    }
}
