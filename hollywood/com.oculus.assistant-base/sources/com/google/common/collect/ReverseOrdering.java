package com.google.common.collect;

import X.UR;
import java.io.Serializable;

public final class ReverseOrdering extends UR implements Serializable {
    public static final long serialVersionUID = 0;
    public final UR forwardOrder;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReverseOrdering) {
            return this.forwardOrder.equals(((ReverseOrdering) obj).forwardOrder);
        }
        return false;
    }

    public final int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.forwardOrder);
        sb.append(".reverse()");
        return sb.toString();
    }

    public ReverseOrdering(UR ur) {
        this.forwardOrder = ur;
    }
}
