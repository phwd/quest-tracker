package com.facebook.rti.common.guavalite.base;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
final class Present<T> extends Optional<T> {
    private static final long serialVersionUID = 0;
    private final T reference;

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public boolean isPresent() {
        return true;
    }

    Present(T t) {
        this.reference = t;
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public T get() {
        return this.reference;
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public String toString() {
        return "Optional.of(" + ((Object) this.reference) + ")";
    }
}
