package com.facebook.rti.common.guavalite.base;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Absent extends Optional<Object> {
    static final Absent INSTANCE = new Absent();
    private static final long serialVersionUID = 0;

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public boolean equals(@Nullable Object obj) {
        return obj == this;
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public int hashCode() {
        return 1502476572;
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public boolean isPresent() {
        return false;
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public String toString() {
        return "Optional.absent()";
    }

    private Absent() {
    }

    @Override // com.facebook.rti.common.guavalite.base.Optional
    public Object get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }
}
