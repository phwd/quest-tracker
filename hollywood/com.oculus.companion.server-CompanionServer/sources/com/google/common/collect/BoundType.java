package com.google.common.collect;

public enum BoundType {
    OPEN {
    },
    CLOSED {
    };

    static BoundType forBoolean(boolean z) {
        return z ? CLOSED : OPEN;
    }
}
