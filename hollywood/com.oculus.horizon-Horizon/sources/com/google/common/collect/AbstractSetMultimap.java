package com.google.common.collect;

import X.AbstractC03540dC;
import X.AnonymousClass0eQ;
import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class AbstractSetMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements AbstractC03540dC<K, V> {
    public static final long serialVersionUID = 7431625294878419160L;

    /* renamed from: A04 */
    public abstract Set<V> A02();

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public final Collection<V> A03(K k, Collection<V> collection) {
        return new AnonymousClass0eQ(this, k, (Set) collection);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC07090r4, com.google.common.collect.AbstractMapBasedMultimap
    public final /* bridge */ /* synthetic */ Collection A2u(@NullableDecl Object obj) {
        return super.A2u(obj);
    }

    public AbstractSetMultimap(Map<K, Collection<V>> map) {
        super(map);
    }
}
