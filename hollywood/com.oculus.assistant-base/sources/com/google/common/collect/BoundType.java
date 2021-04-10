package com.google.common.collect;

public enum BoundType {
    OPEN(false),
    CLOSED(true);
    
    public final boolean inclusive;

    public static BoundType forBoolean(boolean z) {
        if (z) {
            return CLOSED;
        }
        return OPEN;
    }

    public BoundType flip() {
        return forBoolean(!this.inclusive);
    }

    /* access modifiers changed from: public */
    BoundType(boolean z) {
        this.inclusive = z;
    }
}
