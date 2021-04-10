package com.facebook.common.listeners;

import com.facebook.common.listeners.Listener;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class WeakListenersManager<K, A, T extends Listener<A, K>> extends AbstractWeakListenersManager<K, A, T> {
    /* access modifiers changed from: protected */
    public void callListener(T t, A a, K k) {
        t.call(a, k);
    }
}
