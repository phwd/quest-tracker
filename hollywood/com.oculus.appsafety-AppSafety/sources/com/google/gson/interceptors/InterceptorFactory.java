package com.google.gson.interceptors;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public final class InterceptorFactory implements TypeAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Intercept intercept = (Intercept) type.getRawType().getAnnotation(Intercept.class);
        if (intercept == null) {
            return null;
        }
        return new InterceptorAdapter(gson.getDelegateAdapter(this, type), intercept);
    }

    static class InterceptorAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> delegate;
        private final JsonPostDeserializer<T> postDeserializer;

        public InterceptorAdapter(TypeAdapter<T> delegate2, Intercept intercept) {
            try {
                this.delegate = delegate2;
                this.postDeserializer = (JsonPostDeserializer) intercept.postDeserialize().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, T value) throws IOException {
            this.delegate.write(out, value);
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader in) throws IOException {
            T result = this.delegate.read(in);
            this.postDeserializer.postDeserialize(result);
            return result;
        }
    }
}
