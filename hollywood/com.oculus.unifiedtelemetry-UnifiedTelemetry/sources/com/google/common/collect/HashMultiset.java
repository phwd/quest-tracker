package com.google.common.collect;

import X.AnonymousClass3s;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

@GwtCompatible(emulated = true, serializable = true)
public class HashMultiset<E> extends AbstractMapBasedMultiset<E> {
    @GwtIncompatible
    public static final long serialVersionUID = 0;

    @Override // com.google.common.collect.AbstractMapBasedMultiset
    public final void A08(int i) {
        ((AbstractMapBasedMultiset) this).A01 = new AnonymousClass3s<>(i);
    }
}
