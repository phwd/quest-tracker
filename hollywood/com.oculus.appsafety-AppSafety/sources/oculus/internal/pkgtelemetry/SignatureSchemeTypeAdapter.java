package oculus.internal.pkgtelemetry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.oculus.os.PackageMetadata;
import java.lang.reflect.Type;

public class SignatureSchemeTypeAdapter implements JsonDeserializer<PackageMetadata.Signature.Scheme>, JsonSerializer<PackageMetadata.Signature.Scheme> {
    @Override // com.google.gson.JsonDeserializer
    public PackageMetadata.Signature.Scheme deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) {
        return PackageMetadata.Signature.Scheme.fromValue(element.getAsInt());
    }

    public JsonElement serialize(PackageMetadata.Signature.Scheme scheme, Type type, JsonSerializationContext context) {
        return new JsonPrimitive((Number) new Integer(scheme.value));
    }
}
