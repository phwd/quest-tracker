package com.google.common.base;

import java.io.Serializable;

class Suppliers$MemoizingSupplier<T> implements Supplier<T>, Serializable {
    private static final long serialVersionUID = 0;
    final Supplier<T> delegate;

    public String toString() {
        return "Suppliers.memoize(" + this.delegate + ")";
    }
}
