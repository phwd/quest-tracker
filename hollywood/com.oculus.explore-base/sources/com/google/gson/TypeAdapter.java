package com.google.gson;

import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public abstract class TypeAdapter<T> {
    public abstract void write(JsonWriter jsonWriter, T t) throws IOException;

    public final TypeAdapter<T> nullSafe() {
        return new TypeAdapter<T>() {
            /* class com.google.gson.TypeAdapter.AnonymousClass1 */

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, T value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    TypeAdapter.this.write(out, value);
                }
            }
        };
    }

    public final JsonElement toJsonTree(T value) {
        try {
            JsonTreeWriter jsonWriter = new JsonTreeWriter();
            write(jsonWriter, value);
            return jsonWriter.get();
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }
}
