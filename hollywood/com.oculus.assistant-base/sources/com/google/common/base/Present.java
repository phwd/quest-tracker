package com.google.common.base;

public final class Present extends Optional {
    public static final long serialVersionUID = 0;
    public final Object reference;

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    public int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Optional.of(");
        sb.append(this.reference);
        sb.append(")");
        return sb.toString();
    }

    public Present(Object obj) {
        this.reference = obj;
    }

    @Override // com.google.common.base.Optional
    public Object get() {
        return this.reference;
    }
}
