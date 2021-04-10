package com.google.common.collect;

import X.AnonymousClass006;
import X.AnonymousClass0tW;
import X.C07430ry;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public class ImmutableListMultimap<K, V> extends ImmutableMultimap<K, V> implements ListMultimap<K, V> {
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final ImmutableList<V> A2s(@NullableDecl K k) {
        ImmutableList<V> immutableList = (ImmutableList) this.A00.get(k);
        if (immutableList == null) {
            return ImmutableList.of();
        }
        return immutableList;
    }

    public static <K, V> ImmutableListMultimap<K, V> A01() {
        return EmptyImmutableListMultimap.A00;
    }

    /* JADX WARN: Incorrect args count in method signature: <K:Ljava/lang/Object;V:Ljava/lang/Object;>(Ljava/util/Collection<+Ljava/util/Map$Entry<+TK;+Ljava/util/Collection<+TV;>;>;>;Ljava/util/Comparator<-TV;>;)Lcom/google/common/collect/ImmutableListMultimap<TK;TV;>; */
    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    public static ImmutableListMultimap A02(Collection collection) {
        if (collection.isEmpty()) {
            return A01();
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            ImmutableList copyOf = ImmutableList.copyOf((Collection) entry.getValue());
            if (!copyOf.isEmpty()) {
                builder.put(key, copyOf);
                copyOf.size();
            }
        }
        return new ImmutableListMultimap(builder.build());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        String str;
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            ImmutableMap.Builder builder = new ImmutableMap.Builder();
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    ImmutableList.Builder builder2 = ImmutableList.builder();
                    int i3 = 0;
                    do {
                        builder2.add(objectInputStream.readObject());
                        i3++;
                    } while (i3 < readInt2);
                    builder.put(readObject, builder2.build());
                    i += readInt2;
                } else {
                    str = AnonymousClass006.A01("Invalid value count ", readInt2);
                }
            }
            try {
                C07430ry.A00.A00(this, builder.build());
                try {
                    C07430ry.A01.A00.set(this, Integer.valueOf(i));
                    return;
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
            } catch (IllegalArgumentException e2) {
                throw new InvalidObjectException(e2.getMessage()).initCause(e2);
            }
        } else {
            str = AnonymousClass006.A01("Invalid key count ", readInt);
        }
        throw new InvalidObjectException(str);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        AnonymousClass0tW.A01(this, objectOutputStream);
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableMap<TK;Lcom/google/common/collect/ImmutableList<TV;>;>;I)V */
    public ImmutableListMultimap(ImmutableMap immutableMap) {
        super(immutableMap);
    }
}
