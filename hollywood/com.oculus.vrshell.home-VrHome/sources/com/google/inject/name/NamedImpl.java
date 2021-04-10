package com.google.inject.name;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.annotation.Annotation;

/* access modifiers changed from: package-private */
public class NamedImpl implements Named, Serializable {
    private final String value;

    public NamedImpl(String value2) {
        this.value = (String) Preconditions.checkNotNull(value2, "name");
    }

    @Override // com.google.inject.name.Named
    public String value() {
        return this.value;
    }

    public int hashCode() {
        return ("value".hashCode() * 127) ^ this.value.hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof Named)) {
            return false;
        }
        return this.value.equals(((Named) o).value());
    }

    public String toString() {
        return "@" + Named.class.getName() + "(value=" + this.value + ")";
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        return Named.class;
    }
}
