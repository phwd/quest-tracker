package com.google.common.collect;

import X.AbstractC05100td;
import X.AnonymousClass0fU;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import java.util.Collection;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableBiMap<K, V> extends ImmutableMap<K, V> implements AbstractC05100td<K, V> {

    public static class SerializedForm extends ImmutableMap.SerializedForm {
        public static final long serialVersionUID = 0;

        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        public Object readResolve() {
            return A00(new AnonymousClass0fU());
        }

        public SerializedForm(ImmutableBiMap<?, ?> immutableBiMap) {
            super(immutableBiMap);
        }
    }

    /* renamed from: A0D */
    public abstract ImmutableBiMap<V, K> A5n();

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableCollection A08() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return new SerializedForm(this);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final /* bridge */ /* synthetic */ ImmutableCollection A07() {
        return A5n().keySet();
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        return A5n().keySet();
    }
}
