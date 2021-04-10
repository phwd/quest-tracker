package com.google.common.collect;

import X.AnonymousClass0rT;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Set;

@GwtCompatible(emulated = true, serializable = true)
public final class HashMultimap<K, V> extends HashMultimapGwtSerializationDependencies<K, V> {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    @VisibleForTesting
    public transient int A00 = 2;

    public HashMultimap() {
        super(new CompactHashMap(12));
        Preconditions.checkArgument(true);
        this.A00 = 2;
    }

    @Override // com.google.common.collect.AbstractSetMultimap
    /* renamed from: A04 */
    public final Set<V> A02() {
        return new CompactHashSet(this.A00);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: com.google.common.collect.HashMultimap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.A00 = 2;
        int readInt = objectInputStream.readInt();
        CompactHashMap compactHashMap = new CompactHashMap(12);
        ((AbstractMapBasedMultimap) this).A01 = compactHashMap;
        ((AbstractMapBasedMultimap) this).A00 = 0;
        for (V v : compactHashMap.values()) {
            Preconditions.checkArgument(!v.isEmpty());
            ((AbstractMapBasedMultimap) this).A00 += v.size();
        }
        for (int i = 0; i < readInt; i++) {
            Collection A2u = A2u(objectInputStream.readObject());
            int readInt2 = objectInputStream.readInt();
            for (int i2 = 0; i2 < readInt2; i2++) {
                A2u.add(objectInputStream.readObject());
            }
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        AnonymousClass0rT.A01(this, objectOutputStream);
    }
}
