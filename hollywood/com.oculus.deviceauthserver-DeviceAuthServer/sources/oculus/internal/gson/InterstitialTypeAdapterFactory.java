package oculus.internal.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public abstract class InterstitialTypeAdapterFactory<C> implements TypeAdapterFactory {
    private final Class<C> clazz;

    public InterstitialTypeAdapterFactory(Class<C> clazz2) {
        this.clazz = clazz2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.gson.reflect.TypeToken<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v2. Raw type applied. Possible types: com.google.gson.TypeAdapter<C>, com.google.gson.TypeAdapter<T> */
    @Override // com.google.gson.TypeAdapterFactory
    public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType() == this.clazz) {
            return (TypeAdapter<C>) generateTypeAdapter(gson, type);
        }
        return null;
    }

    private TypeAdapter<C> generateTypeAdapter(Gson gson, TypeToken<C> type) {
        final TypeAdapter<C> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        return new TypeAdapter<C>() {
            /* class oculus.internal.gson.InterstitialTypeAdapterFactory.AnonymousClass1 */

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, C value) throws IOException {
                JsonElement tree = delegate.toJsonTree(value);
                InterstitialTypeAdapterFactory.this.beforeWrite(value, tree);
                elementAdapter.write(out, tree);
            }

            @Override // com.google.gson.TypeAdapter
            public C read(JsonReader in) throws IOException {
                JsonElement tree = (JsonElement) elementAdapter.read(in);
                InterstitialTypeAdapterFactory.this.afterRead(tree);
                return (C) delegate.fromJsonTree(tree);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void beforeWrite(C c, JsonElement dest) {
    }

    /* access modifiers changed from: protected */
    public void afterRead(JsonElement deserialized) {
    }
}
