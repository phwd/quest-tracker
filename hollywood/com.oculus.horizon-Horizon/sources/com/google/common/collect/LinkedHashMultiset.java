package com.google.common.collect;

import X.AnonymousClass0dG;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true, serializable = true)
public final class LinkedHashMultiset<E> extends AbstractMapBasedMultiset<E> {
    @Override // com.google.common.collect.AbstractMapBasedMultiset
    public final void A07(int i) {
        ((AbstractMapBasedMultiset) this).A01 = new AnonymousClass0dG(i);
    }
}
