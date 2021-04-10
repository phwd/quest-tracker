package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import java.lang.reflect.Field;

public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field2) {
        C$Gson$Preconditions.checkNotNull(field2);
        this.field = field2;
    }
}
