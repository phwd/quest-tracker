package com.google.common.collect;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD, serializable = BuildConfig.IS_INTERNAL_BUILD)
public class RegularImmutableMultiset<E> extends ImmutableMultiset<E> {
    static final RegularImmutableMultiset<Object> EMPTY = new RegularImmutableMultiset<>(ObjectCountHashMap.create());
    final transient ObjectCountHashMap<E> contents;
    @LazyInit
    private transient ImmutableSet<E> elementSet;
    private final transient int size;

    RegularImmutableMultiset(ObjectCountHashMap<E> contents2) {
        this.contents = contents2;
        long size2 = 0;
        for (int i = 0; i < contents2.size(); i++) {
            size2 += (long) contents2.getValue(i);
        }
        this.size = Ints.saturatedCast(size2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object element) {
        return this.contents.get(element);
    }

    @Override // com.google.common.collect.Multiset
    public int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> result = this.elementSet;
        if (result != null) {
            return result;
        }
        ElementSet elementSet2 = new ElementSet();
        this.elementSet = elementSet2;
        return elementSet2;
    }

    /* access modifiers changed from: private */
    public final class ElementSet extends IndexedImmutableSet<E> {
        private ElementSet() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.IndexedImmutableSet
        public E get(int index) {
            return RegularImmutableMultiset.this.contents.getKey(index);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean contains(@NullableDecl Object object) {
            return RegularImmutableMultiset.this.contains(object);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        public int size() {
            return RegularImmutableMultiset.this.contents.size();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int index) {
        return this.contents.getEntry(index);
    }

    @GwtIncompatible
    private static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final int[] counts;
        final Object[] elements;

        SerializedForm(Multiset<?> multiset) {
            int distinct = multiset.entrySet().size();
            this.elements = new Object[distinct];
            this.counts = new int[distinct];
            int i = 0;
            for (Multiset.Entry<?> entry : multiset.entrySet()) {
                this.elements[i] = entry.getElement();
                this.counts[i] = entry.getCount();
                i++;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableMultiset$Builder */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public Object readResolve() {
            ImmutableMultiset.Builder<Object> builder = new ImmutableMultiset.Builder<>(this.elements.length);
            for (int i = 0; i < this.elements.length; i++) {
                builder.addCopies(this.elements[i], this.counts[i]);
            }
            return builder.build();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMultiset, com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}
