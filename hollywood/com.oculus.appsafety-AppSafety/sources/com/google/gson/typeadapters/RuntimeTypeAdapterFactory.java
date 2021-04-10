package com.google.gson.typeadapters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final Map<String, Class<?>> labelToSubtype = new LinkedHashMap();
    private final Map<Class<?>, String> subtypeToLabel = new LinkedHashMap();
    private final String typeFieldName;

    private RuntimeTypeAdapterFactory(Class<?> baseType2, String typeFieldName2) {
        if (typeFieldName2 == null || baseType2 == null) {
            throw new NullPointerException();
        }
        this.baseType = baseType2;
        this.typeFieldName = typeFieldName2;
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType2, String typeFieldName2) {
        return new RuntimeTypeAdapterFactory<>(baseType2, typeFieldName2);
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType2) {
        return new RuntimeTypeAdapterFactory<>(baseType2, "type");
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type, String label) {
        if (type == null || label == null) {
            throw new NullPointerException();
        } else if (this.subtypeToLabel.containsKey(type) || this.labelToSubtype.containsKey(label)) {
            throw new IllegalArgumentException("types and labels must be unique");
        } else {
            this.labelToSubtype.put(label, type);
            this.subtypeToLabel.put(type, label);
            return this;
        }
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> type) {
        return registerSubtype(type, type.getSimpleName());
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (type.getRawType() != this.baseType) {
            return null;
        }
        final Map<String, TypeAdapter<?>> labelToDelegate = new LinkedHashMap<>();
        final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate = new LinkedHashMap<>();
        for (Map.Entry<String, Class<?>> entry : this.labelToSubtype.entrySet()) {
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get((Class) entry.getValue()));
            labelToDelegate.put(entry.getKey(), delegate);
            subtypeToDelegate.put(entry.getValue(), delegate);
        }
        return new TypeAdapter<R>() {
            /* class com.google.gson.typeadapters.RuntimeTypeAdapterFactory.AnonymousClass1 */

            @Override // com.google.gson.TypeAdapter
            public R read(JsonReader in) throws IOException {
                JsonElement jsonElement = Streams.parse(in);
                JsonElement labelJsonElement = jsonElement.getAsJsonObject().remove(RuntimeTypeAdapterFactory.this.typeFieldName);
                if (labelJsonElement != null) {
                    String label = labelJsonElement.getAsString();
                    TypeAdapter<R> delegate = (TypeAdapter) labelToDelegate.get(label);
                    if (delegate != null) {
                        return (R) delegate.fromJsonTree(jsonElement);
                    }
                    throw new JsonParseException("cannot deserialize " + ((Object) RuntimeTypeAdapterFactory.this.baseType) + " subtype named " + label + "; did you forget to register a subtype?");
                }
                throw new JsonParseException("cannot deserialize " + ((Object) RuntimeTypeAdapterFactory.this.baseType) + " because it does not define a field named " + RuntimeTypeAdapterFactory.this.typeFieldName);
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, R value) throws IOException {
                Class<?> srcType = value.getClass();
                String label = (String) RuntimeTypeAdapterFactory.this.subtypeToLabel.get(srcType);
                TypeAdapter<R> delegate = (TypeAdapter) subtypeToDelegate.get(srcType);
                if (delegate != null) {
                    JsonObject jsonObject = delegate.toJsonTree(value).getAsJsonObject();
                    if (!jsonObject.has(RuntimeTypeAdapterFactory.this.typeFieldName)) {
                        JsonObject clone = new JsonObject();
                        clone.add(RuntimeTypeAdapterFactory.this.typeFieldName, new JsonPrimitive(label));
                        for (Map.Entry<String, JsonElement> e : jsonObject.entrySet()) {
                            clone.add(e.getKey(), e.getValue());
                        }
                        Streams.write(clone, out);
                        return;
                    }
                    throw new JsonParseException("cannot serialize " + srcType.getName() + " because it already defines a field named " + RuntimeTypeAdapterFactory.this.typeFieldName);
                }
                throw new JsonParseException("cannot serialize " + srcType.getName() + "; did you forget to register a subtype?");
            }
        }.nullSafe();
    }
}
