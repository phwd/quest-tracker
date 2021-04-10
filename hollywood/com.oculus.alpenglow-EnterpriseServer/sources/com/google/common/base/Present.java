package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class Present<T> extends Optional<T> {
    public static final long serialVersionUID = 0;
    public final T reference;

    @Override // com.google.common.base.Optional
    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.google.common.base.Optional
    public String toString() {
        return "Optional.of(" + ((Object) this.reference) + ")";
    }

    public Present(T t) {
        this.reference = t;
    }
}
