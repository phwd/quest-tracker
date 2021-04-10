package com.google.common.collect;

import X.C0178Uc;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true, serializable = true)
public final class LinkedHashMultiset<E> extends AbstractMapBasedMultiset<E> {
    @Override // com.google.common.collect.AbstractMapBasedMultiset
    public final void A08(int i) {
        ((AbstractMapBasedMultiset) this).A01 = new C0178Uc(i);
    }
}
