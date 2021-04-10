package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.ObjIntConsumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
public final class HashMultiset<E> extends AbstractMapBasedMultiset<E> {
    @GwtIncompatible
    private static final long serialVersionUID = 0;

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMapBasedMultiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ int add(@NullableDecl Object obj, int i) {
        return super.add(obj, i);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMapBasedMultiset
    public /* bridge */ /* synthetic */ int count(@NullableDecl Object obj) {
        return super.count(obj);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ Set elementSet() {
        return super.elementSet();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMapBasedMultiset, com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMapBasedMultiset
    public /* bridge */ /* synthetic */ void forEachEntry(ObjIntConsumer objIntConsumer) {
        super.forEachEntry(objIntConsumer);
    }

    @Override // com.google.common.collect.AbstractMultiset
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset, java.util.Collection, com.google.common.collect.AbstractMapBasedMultiset, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMapBasedMultiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ int remove(@NullableDecl Object obj, int i) {
        return super.remove(obj, i);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMapBasedMultiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ int setCount(@NullableDecl Object obj, int i) {
        return super.setCount(obj, i);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMultiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean setCount(@NullableDecl Object obj, int i, int i2) {
        return super.setCount(obj, i, i2);
    }

    @Override // com.google.common.collect.Multiset, com.google.common.collect.AbstractMapBasedMultiset
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public static <E> HashMultiset<E> create() {
        return new HashMultiset<>();
    }

    public static <E> HashMultiset<E> create(int i) {
        return new HashMultiset<>(i);
    }

    public static <E> HashMultiset<E> create(Iterable<? extends E> iterable) {
        HashMultiset<E> create = create(Multisets.inferDistinctElements(iterable));
        Iterables.addAll(create, iterable);
        return create;
    }

    private HashMultiset() {
        super(new HashMap());
    }

    private HashMultiset(int i) {
        super(Maps.newHashMapWithExpectedSize(i));
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMultiset(this, objectOutputStream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readCount = Serialization.readCount(objectInputStream);
        setBackingMap(Maps.newHashMap());
        Serialization.populateMultiset(this, objectInputStream, readCount);
    }
}
