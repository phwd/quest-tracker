package com.google.common.collect;

import X.AbstractC03540dC;
import X.AnonymousClass0CC;
import X.AnonymousClass0rS;
import X.AnonymousClass0rT;
import X.C001206j;
import X.C06820pw;
import X.C06860q4;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public class ImmutableSetMultimap<K, V> extends ImmutableMultimap<K, V> implements AbstractC03540dC<K, V> {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public final transient ImmutableSet<V> A00 = RegularImmutableSet.A05;

    @Override // com.google.common.collect.ImmutableMultimap
    public final /* bridge */ /* synthetic */ ImmutableCollection A02(@NullableDecl Object obj) {
        return (ImmutableCollection) MoreObjects.firstNonNull(((ImmutableMultimap) this).A00.get(obj), this.A00);
    }

    @Override // X.AbstractC07090r4, com.google.common.collect.ImmutableMultimap
    public final /* bridge */ /* synthetic */ Collection A2u(@NullableDecl Object obj) {
        return (ImmutableCollection) MoreObjects.firstNonNull(((ImmutableMultimap) this).A00.get(obj), this.A00);
    }

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
        AnonymousClass0CC r0;
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            ImmutableMap.Builder A01 = ImmutableMap.A01();
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    if (comparator == null) {
                        r0 = new AnonymousClass0CC();
                    } else {
                        r0 = new C001206j(comparator);
                    }
                    int i3 = 0;
                    do {
                        r0.A04(objectInputStream.readObject());
                        i3++;
                    } while (i3 < readInt2);
                    ImmutableSet A06 = r0.build();
                    if (A06.size() == readInt2) {
                        A01.put(readObject, A06);
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
                C06820pw.A00.A00(this, A01.build());
                try {
                    C06820pw.A01.A00.set(this, Integer.valueOf(i));
                    AnonymousClass0rS<ImmutableSetMultimap> r1 = C06860q4.A00;
                    if (comparator == null) {
                        A04 = RegularImmutableSet.A05;
                    } else {
                        A04 = ImmutableSortedSet.A04(comparator);
                    }
                    r1.A00(this, A04);
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
        AnonymousClass0rT.A01(this, objectOutputStream);
    }
}
