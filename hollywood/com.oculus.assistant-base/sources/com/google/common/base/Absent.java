package com.google.common.base;

public final class Absent extends Optional {
    public static final Absent INSTANCE = new Absent();
    public static final long serialVersionUID = 0;

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public boolean isPresent() {
        return false;
    }

    public String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.common.base.Optional
    public Object get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
