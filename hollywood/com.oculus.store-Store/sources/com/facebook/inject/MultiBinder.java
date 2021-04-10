package com.facebook.inject;

import com.google.inject.Key;
import com.google.inject.internal.MoreTypes;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* access modifiers changed from: package-private */
public class MultiBinder<T> {
    private static final Comparator<Key> CLASS_NAME_COMPARATOR = new Comparator<Key>() {
        /* class com.facebook.inject.MultiBinder.AnonymousClass1 */

        public int compare(Key o1, Key o2) {
            return o1.getTypeLiteral().getRawType().getName().compareTo(o2.getTypeLiteral().getRawType().getName());
        }
    };
    private final FbInjector mInjector;
    private final Key<T> mKey;
    private final List<Key<? extends T>> mValues = new ArrayList();

    MultiBinder(FbInjector injector, Key<T> key) {
        this.mInjector = injector;
        this.mKey = key;
    }

    /* access modifiers changed from: package-private */
    public void add(Key<? extends T> other) {
        int N = this.mValues.size();
        for (int i = 0; i < N; i++) {
            if (this.mValues.get(i).equals(other)) {
                return;
            }
        }
        this.mValues.add(other);
    }

    /* access modifiers changed from: package-private */
    public void addAll(Collection<Key<? extends T>> keys) {
        for (Key<? extends T> key : keys) {
            add(key);
        }
        Collections.sort(this.mValues, CLASS_NAME_COMPARATOR);
    }

    /* access modifiers changed from: package-private */
    public Key<T> getKey() {
        return this.mKey;
    }

    /* access modifiers changed from: package-private */
    public Provider<Set<T>> getProvider() {
        return new MultiBinderProvider(this.mInjector, this.mValues, this.mKey);
    }

    /* access modifiers changed from: package-private */
    public Key<? extends Set<T>> getBoundKey() {
        ParameterizedType boundType = new MoreTypes.ParameterizedTypeImpl(null, Set.class, this.mKey.getTypeLiteral().getType());
        return this.mKey.getAnnotation() != null ? (Key<? extends Set<T>>) Key.get(boundType, this.mKey.getAnnotation()) : this.mKey.getAnnotationType() != null ? (Key<? extends Set<T>>) Key.get(boundType, this.mKey.getAnnotationType()) : (Key<? extends Set<T>>) Key.get(boundType);
    }
}
