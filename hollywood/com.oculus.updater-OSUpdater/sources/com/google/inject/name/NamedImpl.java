package com.google.inject.name;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.annotation.Annotation;

/* access modifiers changed from: package-private */
public class NamedImpl implements Named, Serializable {
    private static final long serialVersionUID = 0;
    private final String value;

    public NamedImpl(String str) {
        this.value = (String) Preconditions.checkNotNull(str, "name");
    }

    @Override // com.google.inject.name.Named
    public String value() {
        return this.value;
    }

    public int hashCode() {
        return ("value".hashCode() * 127) ^ this.value.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Named)) {
            return false;
        }
        return this.value.equals(((Named) obj).value());
    }

    public String toString() {
        return "@" + Named.class.getName() + "(value=" + this.value + ")";
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        return Named.class;
    }
}
