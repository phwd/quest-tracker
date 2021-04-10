package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public enum BoundType {
    OPEN {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.BoundType
        public BoundType flip() {
            return CLOSED;
        }
    },
    CLOSED {
        /* access modifiers changed from: package-private */
        @Override // com.google.common.collect.BoundType
        public BoundType flip() {
            return OPEN;
        }
    };

    /* access modifiers changed from: package-private */
    public abstract BoundType flip();

    static BoundType forBoolean(boolean inclusive) {
        return inclusive ? CLOSED : OPEN;
    }
}
