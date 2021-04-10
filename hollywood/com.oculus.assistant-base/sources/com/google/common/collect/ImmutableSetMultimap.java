package com.google.common.collect;

import X.AbstractC1183ui;
import X.AnonymousClass08;
import X.DS;
import X.SX;
import X.U5;
import X.U8;
import X.UT;
import X.UU;
import com.google.common.collect.ImmutableMap;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

public class ImmutableSetMultimap extends ImmutableMultimap implements AbstractC1183ui {
    public static final long serialVersionUID = 0;
    public transient ImmutableSet A00;
    public final transient ImmutableSet A01 = RegularImmutableSet.A05;

    public final class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        public final transient ImmutableSetMultimap A00;

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.A00.A1U(entry.getKey(), entry.getValue());
        }

        public final int size() {
            return this.A00.size();
        }

        public EntrySet(ImmutableSetMultimap immutableSetMultimap) {
            this.A00 = immutableSetMultimap;
        }
    }

    @Override // X.AbstractC1156tw, com.google.common.collect.ImmutableMultimap
    public final /* bridge */ /* synthetic */ Collection A02() {
        ImmutableSet immutableSet = this.A00;
        if (immutableSet != null) {
            return immutableSet;
        }
        EntrySet entrySet = new EntrySet(this);
        this.A00 = entrySet;
        return entrySet;
    }

    @Override // X.UK, com.google.common.collect.ImmutableMultimap
    public final /* bridge */ /* synthetic */ Collection A2E(Object obj) {
        Object obj2 = ((ImmutableMultimap) this).A01.get(obj);
        ImmutableSet immutableSet = this.A01;
        if (obj2 == null) {
            if (immutableSet != null) {
                obj2 = immutableSet;
            } else {
                throw new NullPointerException("Both parameters are null");
            }
        }
        return (ImmutableCollection) obj2;
    }

    @Override // X.UK, com.google.common.collect.ImmutableMultimap
    public final /* bridge */ /* synthetic */ Collection A4n(Object obj) {
        throw new UnsupportedOperationException();
    }

    public ImmutableSetMultimap(ImmutableMap immutableMap, int i) {
        super(immutableMap, i);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        Object A012;
        DS sx;
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
                        sx = new DS();
                    } else {
                        sx = new SX(comparator);
                    }
                    int i3 = 0;
                    do {
                        sx.A00(objectInputStream.readObject());
                        i3++;
                    } while (i3 < readInt2);
                    ImmutableSet A02 = sx.build();
                    if (A02.size() == readInt2) {
                        builder.put(readObject, A02);
                        i += readInt2;
                    } else {
                        StringBuilder sb = new StringBuilder("Duplicate key-value pairs exist for key ");
                        sb.append(readObject);
                        throw new InvalidObjectException(sb.toString());
                    }
                } else {
                    throw new InvalidObjectException(AnonymousClass08.A00("Invalid value count ", readInt2));
                }
            }
            try {
                U5.A00.A00(this, builder.build());
                try {
                    U5.A01.A00.set(this, Integer.valueOf(i));
                    UT ut = U8.A00;
                    if (comparator == null) {
                        A012 = RegularImmutableSet.A05;
                    } else {
                        A012 = ImmutableSortedSet.A01(comparator);
                    }
                    ut.A00(this, A012);
                } catch (IllegalAccessException e) {
                    throw new AssertionError(e);
                }
            } catch (IllegalArgumentException e2) {
                throw new InvalidObjectException(e2.getMessage()).initCause(e2);
            }
        } else {
            throw new InvalidObjectException(AnonymousClass08.A00("Invalid key count ", readInt));
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        Comparator comparator;
        objectOutputStream.defaultWriteObject();
        ImmutableSet immutableSet = this.A01;
        if (immutableSet instanceof ImmutableSortedSet) {
            comparator = ((ImmutableSortedSet) immutableSet).comparator();
        } else {
            comparator = null;
        }
        objectOutputStream.writeObject(comparator);
        UU.A02(this, objectOutputStream);
    }
}
