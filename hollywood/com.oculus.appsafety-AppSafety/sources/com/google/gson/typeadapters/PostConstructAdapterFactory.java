package com.google.gson.typeadapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.PostConstruct;

public class PostConstructAdapterFactory implements TypeAdapterFactory {
    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<?> t = type.getRawType();
        while (t != Object.class && t.getSuperclass() != null) {
            Method[] declaredMethods = t.getDeclaredMethods();
            for (Method m : declaredMethods) {
                if (m.isAnnotationPresent(PostConstruct.class)) {
                    m.setAccessible(true);
                    return new PostConstructAdapter(gson.getDelegateAdapter(this, type), m);
                }
            }
            t = t.getSuperclass();
        }
        return null;
    }

    static final class PostConstructAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> delegate;
        private final Method method;

        public PostConstructAdapter(TypeAdapter<T> delegate2, Method method2) {
            this.delegate = delegate2;
            this.method = method2;
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader in) throws IOException {
            T result = this.delegate.read(in);
            if (result != null) {
                try {
                    this.method.invoke(result, new Object[0]);
                } catch (IllegalAccessException e) {
                    throw new AssertionError();
                } catch (InvocationTargetException e2) {
                    if (e2.getCause() instanceof RuntimeException) {
                        throw ((RuntimeException) e2.getCause());
                    }
                    throw new RuntimeException(e2.getCause());
                }
            }
            return result;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, T value) throws IOException {
            this.delegate.write(out, value);
        }
    }
}
