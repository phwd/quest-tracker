package com.google.common.collect;

import X.AnonymousClass6l;
import X.C00179c;
import X.C0119Qw;
import X.Qs;
import X.Ra;
import X.Re;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;
import com.oculus.common.build.BuildConfig;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD, serializable = BuildConfig.IS_LIBCXX_BUILD)
public class ImmutableSetMultimap<K, V> extends ImmutableMultimap<K, V> implements SetMultimap<K, V> {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final transient ImmutableSet<V> A00 = RegularImmutableSet.A05;

    /* JADX WARN: Incorrect args count in method signature: (Lcom/google/common/collect/ImmutableMap<TK;Lcom/google/common/collect/ImmutableSet<TV;>;>;ILjava/util/Comparator<-TV;>;)V */
    public ImmutableSetMultimap(ImmutableMap immutableMap) {
        super(immutableMap);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: com.google.common.collect.ImmutableMap$Builder */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        StringBuilder sb;
        Object A04;
        C00179c r0;
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    if (comparator == null) {
                        r0 = new C00179c();
                    } else {
                        r0 = new AnonymousClass6l(comparator);
                    }
                    int i3 = 0;
                    do {
                        r0.A00(objectInputStream.readObject());
                        i3++;
                    } while (i3 < readInt2);
                    ImmutableSet A02 = r0.build();
                    if (A02.size() == readInt2) {
                        builder.put(readObject, A02);
                        i += readInt2;
                    } else {
                        sb = new StringBuilder("Duplicate key-value pairs exist for key ");
                        sb.append(readObject);
                    }
                } else {
                    sb = new StringBuilder("Invalid value count ");
                    sb.append(readInt2);
                }
            }
            try {
                Qs.A00.A00(this, builder.build());
                try {
                    Qs.A01.A00.set(this, Integer.valueOf(i));
                    Ra<ImmutableSetMultimap> ra = C0119Qw.A00;
                    if (comparator == null) {
                        A04 = RegularImmutableSet.A05;
                    } else {
                        A04 = ImmutableSortedSet.A04(comparator);
                    }
                    ra.A00(this, A04);
                    return;
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
            } catch (IllegalArgumentException e2) {
                throw new InvalidObjectException(e2.getMessage()).initCause(e2);
            }
        } else {
            sb = new StringBuilder("Invalid key count ");
            sb.append(readInt);
        }
        throw new InvalidObjectException(sb.toString());
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Comparator comparator;
        objectOutputStream.defaultWriteObject();
        ImmutableSet<V> immutableSet = this.A00;
        if (immutableSet instanceof ImmutableSortedSet) {
            comparator = ((ImmutableSortedSet) immutableSet).comparator();
        } else {
            comparator = null;
        }
        objectOutputStream.writeObject(comparator);
        Re.A01(this, objectOutputStream);
    }
}
