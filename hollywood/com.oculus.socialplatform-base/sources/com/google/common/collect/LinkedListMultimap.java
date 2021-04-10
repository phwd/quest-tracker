package com.google.common.collect;

import X.AnonymousClass0f4;
import X.AnonymousClass0fD;
import X.AnonymousClass0fE;
import X.AnonymousClass0fF;
import X.AnonymousClass0fq;
import X.AnonymousClass0v3;
import X.AnonymousClass0v7;
import X.AnonymousClass0v9;
import X.C05250uz;
import X.C05260v4;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public class LinkedListMultimap<K, V> extends AnonymousClass0fq<K, V> implements AnonymousClass0fD<K, V>, Serializable {
    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public transient int A00;
    public transient int A01;
    @NullableDecl
    public transient AnonymousClass0fE<K, V> A02;
    @NullableDecl
    public transient AnonymousClass0fE<K, V> A03;
    public transient Map<K, AnonymousClass0v7<K, V>> A04 = new CompactHashMap(12);

    @Override // X.AbstractC05440vj
    public final void clear() {
        this.A02 = null;
        this.A03 = null;
        this.A04.clear();
        this.A01 = 0;
        this.A00++;
    }

    /* JADX WARN: Incorrect args count in method signature: (TK;TV;LX/0fE<TK;TV;>;)LX/0fE<TK;TV;>; */
    @CanIgnoreReturnValue
    public static AnonymousClass0fE A00(@NullableDecl LinkedListMultimap linkedListMultimap, @NullableDecl Object obj, @NullableDecl Object obj2, AnonymousClass0fE r6) {
        AnonymousClass0fE<K, V> r2 = new AnonymousClass0fE<>(obj, obj2);
        if (linkedListMultimap.A02 == null) {
            linkedListMultimap.A03 = r2;
            linkedListMultimap.A02 = r2;
        } else {
            if (r6 == null) {
                AnonymousClass0fE<K, V> r0 = linkedListMultimap.A03;
                r0.A03 = r2;
                r2.A04 = r0;
                linkedListMultimap.A03 = r2;
                AnonymousClass0v7<K, V> r1 = linkedListMultimap.A04.get(obj);
                if (r1 != null) {
                    r1.A00++;
                    AnonymousClass0fE<K, V> r02 = r1.A02;
                    r02.A00 = r2;
                    r2.A01 = r02;
                    r1.A02 = r2;
                }
            } else {
                linkedListMultimap.A04.get(obj).A00++;
                r2.A04 = r6.A04;
                r2.A01 = r6.A01;
                r2.A03 = r6;
                r2.A00 = r6;
                AnonymousClass0fE<K, V> r03 = r6.A01;
                if (r03 == null) {
                    linkedListMultimap.A04.get(obj).A01 = r2;
                } else {
                    r03.A00 = r2;
                }
                AnonymousClass0fE<K, V> r04 = r6.A04;
                if (r04 == null) {
                    linkedListMultimap.A02 = r2;
                } else {
                    r04.A03 = r2;
                }
                r6.A04 = r2;
                r6.A01 = r2;
            }
            linkedListMultimap.A01++;
            return r2;
        }
        linkedListMultimap.A04.put(obj, new AnonymousClass0v7<>(r2));
        linkedListMultimap.A00++;
        linkedListMultimap.A01++;
        return r2;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0fE<TK;TV;>;)V */
    public static void A01(LinkedListMultimap linkedListMultimap, AnonymousClass0fE r4) {
        AnonymousClass0fE<K, V> r1 = r4.A04;
        if (r1 != null) {
            r1.A03 = r4.A03;
        } else {
            linkedListMultimap.A02 = r4.A03;
        }
        AnonymousClass0fE<K, V> r0 = r4.A03;
        if (r0 != null) {
            r0.A04 = r1;
        } else {
            linkedListMultimap.A03 = r1;
        }
        if (r4.A01 == null && r4.A00 == null) {
            linkedListMultimap.A04.remove(r4.A05).A00 = 0;
            linkedListMultimap.A00++;
        } else {
            AnonymousClass0v7<K, V> r2 = linkedListMultimap.A04.get(r4.A05);
            r2.A00--;
            AnonymousClass0fE<K, V> r12 = r4.A01;
            if (r12 == null) {
                r2.A01 = r4.A00;
            } else {
                r12.A00 = r4.A00;
            }
            AnonymousClass0fE<K, V> r02 = r4.A00;
            if (r02 == null) {
                r2.A02 = r12;
            } else {
                r02.A01 = r12;
            }
        }
        linkedListMultimap.A01--;
    }

    @Override // X.AnonymousClass0fq
    public final /* bridge */ /* synthetic */ Collection A03() {
        return new C05260v4(this);
    }

    @Override // X.AnonymousClass0fq
    public final Map<K, Collection<V>> A04() {
        return new AnonymousClass0f4(this);
    }

    @Override // X.AnonymousClass0fq
    public final Set<K> A05() {
        return new AnonymousClass0fF(this);
    }

    @CanIgnoreReturnValue
    /* renamed from: A06 */
    public final List<V> A96(@NullableDecl Object obj) {
        AnonymousClass0v9 r1 = new AnonymousClass0v9(this, obj);
        ArrayList arrayList = new ArrayList();
        C05250uz.A00(arrayList, r1);
        List<V> unmodifiableList = Collections.unmodifiableList(arrayList);
        AnonymousClass0v9 r12 = new AnonymousClass0v9(this, obj);
        while (r12.hasNext()) {
            r12.next();
            r12.remove();
        }
        return unmodifiableList;
    }

    @Override // X.AbstractC05440vj
    public final /* bridge */ /* synthetic */ Collection A3M(@NullableDecl Object obj) {
        return new AnonymousClass0v3(this, obj);
    }

    @Override // X.AbstractC05440vj
    public final boolean containsKey(@NullableDecl Object obj) {
        return this.A04.containsKey(obj);
    }

    @Override // X.AbstractC05440vj, X.AnonymousClass0fq
    public final boolean isEmpty() {
        if (this.A02 == null) {
            return true;
        }
        return false;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.A04 = new CompactLinkedHashMap();
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            A00(this, objectInputStream.readObject(), objectInputStream.readObject(), null);
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry entry : (List) super.A02()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @Override // X.AnonymousClass0fq
    public final /* bridge */ /* synthetic */ Collection A02() {
        return super.A02();
    }

    @Override // X.AbstractC05440vj
    public final int size() {
        return this.A01;
    }
}
