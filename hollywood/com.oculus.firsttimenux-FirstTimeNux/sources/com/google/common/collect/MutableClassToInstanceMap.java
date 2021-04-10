package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Primitives;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@GwtIncompatible
public final class MutableClassToInstanceMap<B> extends ForwardingMap<Class<? extends B>, B> implements ClassToInstanceMap<B>, Serializable {
    private final Map<Class<? extends B>, B> delegate;

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((Class) ((Class) obj), obj2);
    }

    public static <B> MutableClassToInstanceMap<B> create() {
        return new MutableClassToInstanceMap<>(new HashMap());
    }

    public static <B> MutableClassToInstanceMap<B> create(Map<Class<? extends B>, B> backingMap) {
        return new MutableClassToInstanceMap<>(backingMap);
    }

    private MutableClassToInstanceMap(Map<Class<? extends B>, B> delegate2) {
        this.delegate = (Map) Preconditions.checkNotNull(delegate2);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    public Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }

    static <B> Map.Entry<Class<? extends B>, B> checkedEntry(final Map.Entry<Class<? extends B>, B> entry) {
        return new ForwardingMapEntry<Class<? extends B>, B>() {
            /* class com.google.common.collect.MutableClassToInstanceMap.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingMapEntry, com.google.common.collect.ForwardingMapEntry
            public Map.Entry<Class<? extends B>, B> delegate() {
                return entry;
            }

            @Override // java.util.Map.Entry, com.google.common.collect.ForwardingMapEntry
            public B setValue(B value) {
                return (B) super.setValue(MutableClassToInstanceMap.cast((Class) getKey(), value));
            }
        };
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public Set<Map.Entry<Class<? extends B>, B>> entrySet() {
        return new ForwardingSet<Map.Entry<Class<? extends B>, B>>() {
            /* class com.google.common.collect.MutableClassToInstanceMap.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingObject, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingCollection
            public Set<Map.Entry<Class<? extends B>, B>> delegate() {
                return MutableClassToInstanceMap.this.delegate().entrySet();
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection, java.lang.Iterable
            public Iterator<Map.Entry<Class<? extends B>, B>> iterator() {
                return new TransformedIterator<Map.Entry<Class<? extends B>, B>, Map.Entry<Class<? extends B>, B>>(delegate().iterator()) {
                    /* class com.google.common.collect.MutableClassToInstanceMap.AnonymousClass2.AnonymousClass1 */

                    /* access modifiers changed from: package-private */
                    @Override // com.google.common.collect.TransformedIterator
                    public /* bridge */ /* synthetic */ Object transform(Object obj) {
                        return transform((Map.Entry) ((Map.Entry) obj));
                    }

                    /* access modifiers changed from: package-private */
                    public Map.Entry<Class<? extends B>, B> transform(Map.Entry<Class<? extends B>, B> from) {
                        return MutableClassToInstanceMap.checkedEntry(from);
                    }
                };
            }

            @Override // com.google.common.collect.ForwardingCollection
            public Object[] toArray() {
                return standardToArray();
            }

            @Override // java.util.Collection, java.util.Set, com.google.common.collect.ForwardingCollection
            public <T> T[] toArray(T[] array) {
                return (T[]) standardToArray(array);
            }
        };
    }

    @CanIgnoreReturnValue
    public B put(Class<? extends B> key, B value) {
        return (B) super.put((Object) key, cast(key, value));
    }

    @Override // com.google.common.collect.ForwardingMap, java.util.Map
    public void putAll(Map<? extends Class<? extends B>, ? extends B> map) {
        Map<Class<? extends B>, B> copy = new LinkedHashMap<>(map);
        for (Map.Entry<? extends Class<? extends B>, B> entry : copy.entrySet()) {
            cast(entry.getKey(), entry.getValue());
        }
        super.putAll(copy);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: T extends B */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ClassToInstanceMap
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> type, T value) {
        return (T) cast(type, put((Class) type, (Object) value));
    }

    @Override // com.google.common.collect.ClassToInstanceMap
    public <T extends B> T getInstance(Class<T> type) {
        return (T) cast(type, get(type));
    }

    /* access modifiers changed from: private */
    @CanIgnoreReturnValue
    public static <B, T extends B> T cast(Class<T> type, B value) {
        return (T) Primitives.wrap(type).cast(value);
    }

    private Object writeReplace() {
        return new SerializedForm(delegate());
    }

    private static final class SerializedForm<B> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Map<Class<? extends B>, B> backingMap;

        SerializedForm(Map<Class<? extends B>, B> backingMap2) {
            this.backingMap = backingMap2;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return MutableClassToInstanceMap.create(this.backingMap);
        }
    }
}
