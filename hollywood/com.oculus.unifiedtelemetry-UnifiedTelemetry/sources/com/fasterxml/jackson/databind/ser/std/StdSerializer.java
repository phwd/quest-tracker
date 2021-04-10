package com.fasterxml.jackson.databind.ser.std;

import X.V8;
import X.VF;
import com.fasterxml.jackson.databind.JsonSerializer;

public abstract class StdSerializer<T> extends JsonSerializer<T> implements VF, V8 {
    public StdSerializer(Class<?> cls, boolean z) {
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<TT;>;)V */
    public StdSerializer() {
    }
}
