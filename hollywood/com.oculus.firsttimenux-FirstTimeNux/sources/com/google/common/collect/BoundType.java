package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public enum BoundType {
    OPEN(false),
    CLOSED(true);
    
    final boolean inclusive;

    private BoundType(boolean inclusive2) {
        this.inclusive = inclusive2;
    }

    static BoundType forBoolean(boolean inclusive2) {
        return inclusive2 ? CLOSED : OPEN;
    }

    /* access modifiers changed from: package-private */
    public BoundType flip() {
        return forBoolean(!this.inclusive);
    }
}
