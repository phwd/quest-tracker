package com.google.common.collect;

import X.AnonymousClass06;
import X.N6;
import X.Qs;
import X.Re;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.build.BuildConfig;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public class ImmutableListMultimap<K, V> extends ImmutableMultimap<K, V> implements ListMultimap<K, V> {
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final ImmutableList<V> A1b(@NullableDecl K k) {
        ImmutableList<V> immutableList = (ImmutableList) this.A00.get(k);
        if (immutableList == null) {
            return ImmutableList.of();
        }
        return immutableList;
    }

    public static <K, V> N6<K, V> A01() {
        return new N6<>();
    }

    public static <K, V> ImmutableListMultimap<K, V> A02() {
        return EmptyImmutableListMultimap.A00;
    }

    /* JADX WARN: Incorrect args count in method signature: <K:Ljava/lang/Object;V:Ljava/lang/Object;>(Ljava/util/Collection<+Ljava/util/Map$Entry<+TK;+Ljava/util/Collection<+TV;>;>;>;Ljava/util/Comparator<-TV;>;)Lcom/google/common/collect/ImmutableListMultimap<TK;TV;>; */
    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    public static ImmutableListMultimap A03(Collection collection) {
        if (collection.isEmpty()) {
            return A02();
        }
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            ImmutableList A09 = ImmutableList.A09((Collection) entry.getValue());
            if (!A09.isEmpty()) {
                builder.put(key, A09);
                A09.size();
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
            ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    ImmutableList.Builder A02 = ImmutableList.A02();
                    int i3 = 0;
                    do {
                        A02.add(objectInputStream.readObject());
                        i3++;
                    } while (i3 < readInt2);
                    builder.put(readObject, A02.build());
                    i += readInt2;
                } else {
                    str = AnonymousClass06.A01("Invalid value count ", readInt2);
                }
            }
            try {
                Qs.A00.A00(this, builder.build());
                try {
                    Qs.A01.A00.set(this, Integer.valueOf(i));
                    return;
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
            } catch (IllegalArgumentException e2) {
                throw new InvalidObjectException(e2.getMessage()).initCause(e2);
            }
        } else {
            str = AnonymousClass06.A01("Invalid key count ", readInt);
        }
        throw new InvalidObjectException(str);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Re.A01(this, objectOutputStream);
    }

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableMap<TK;Lcom/google/common/collect/ImmutableList<TV;>;>;I)V */
    public ImmutableListMultimap(ImmutableMap immutableMap) {
        super(immutableMap);
    }
}
