package com.facebook.common.listeners;

import com.facebook.common.preconditions.Preconditions;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class AbstractWeakListenersManager<K, A, T> {
    private final WeakHashMap<T, Set<K>> mListeners = new WeakHashMap<>();

    /* access modifiers changed from: protected */
    public abstract void callListener(T t, A a, K k);

    public synchronized void registerListener(K k, T t) {
        boolean z = true;
        Preconditions.checkArgument(k != null);
        if (t == null) {
            z = false;
        }
        Preconditions.checkArgument(z);
        Set<K> set = this.mListeners.get(t);
        if (set == null) {
            set = new HashSet<>(4);
        }
        set.add(k);
        this.mListeners.put(t, set);
    }

    public synchronized void unregisterListener(K k, T t) {
        boolean z = true;
        Preconditions.checkArgument(k != null);
        if (t == null) {
            z = false;
        }
        Preconditions.checkArgument(z);
        Set<K> set = this.mListeners.get(t);
        if (set != null) {
            set.remove(k);
            if (set.isEmpty()) {
                this.mListeners.remove(t);
            }
        }
    }

    public synchronized void registerListener(Collection<K> collection, T t) {
        Preconditions.checkArgument(t != null);
        Set<K> set = this.mListeners.get(t);
        if (set == null) {
            set = new HashSet<>(collection);
        } else {
            set.addAll(collection);
        }
        this.mListeners.put(t, set);
    }

    public synchronized void unregisterListener(Collection<K> collection, T t) {
        Preconditions.checkArgument(t != null);
        Set<K> set = this.mListeners.get(t);
        if (set != null) {
            for (K k : collection) {
                set.remove(k);
            }
            if (set.isEmpty()) {
                this.mListeners.remove(t);
            }
        }
    }

    public synchronized boolean isEmpty() {
        return this.mListeners.isEmpty();
    }

    /* access modifiers changed from: protected */
    public boolean hasMatchingKeys(Set<K> set, K k) {
        return set.contains(k);
    }

    public synchronized boolean containsListeners(Collection<K> collection) {
        for (K k : collection) {
            Iterator<Map.Entry<T, Set<K>>> it = this.mListeners.entrySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (hasMatchingKeys(it.next().getValue(), k)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public synchronized Map<K, Collection<T>> getListeners(Collection<K> collection) {
        HashMap hashMap;
        hashMap = null;
        for (K k : collection) {
            HashSet hashSet = null;
            for (Map.Entry<T, Set<K>> entry : this.mListeners.entrySet()) {
                if (hasMatchingKeys(entry.getValue(), k)) {
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(entry.getKey());
                }
            }
            if (hashSet != null) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(k, hashSet);
            }
        }
        return hashMap;
    }

    public void scheduleCallingListeners(final Collection<K> collection, final A a, Executor executor) {
        if (containsListeners(collection)) {
            executor.execute(new Runnable() {
                /* class com.facebook.common.listeners.AbstractWeakListenersManager.AnonymousClass1 */

                public void run() {
                    Map<K, Collection<T>> listeners = AbstractWeakListenersManager.this.getListeners(collection);
                    if (listeners != null) {
                        AbstractWeakListenersManager.this.callListeners(a, listeners);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void callListeners(A a, Map<K, Collection<T>> map) {
        for (Map.Entry<K, Collection<T>> entry : map.entrySet()) {
            for (T t : entry.getValue()) {
                callListener(t, a, entry.getKey());
            }
        }
    }
}
