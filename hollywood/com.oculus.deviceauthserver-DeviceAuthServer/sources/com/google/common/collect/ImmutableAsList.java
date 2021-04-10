package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableAsList<E> extends ImmutableList<E> {
    /* access modifiers changed from: package-private */
    public abstract ImmutableCollection<E> delegateCollection();

    ImmutableAsList() {
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public boolean contains(Object target) {
        return delegateCollection().contains(target);
    }

    public int size() {
        return delegateCollection().size();
    }

    public boolean isEmpty() {
        return delegateCollection().isEmpty();
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return delegateCollection().isPartialView();
    }

    @GwtIncompatible("serialization")
    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableCollection<?> collection;

        SerializedForm(ImmutableCollection<?> collection2) {
            this.collection = collection2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.collection.asList();
        }
    }

    @GwtIncompatible("serialization")
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    @GwtIncompatible("serialization")
    public Object writeReplace() {
        return new SerializedForm(delegateCollection());
    }
}
