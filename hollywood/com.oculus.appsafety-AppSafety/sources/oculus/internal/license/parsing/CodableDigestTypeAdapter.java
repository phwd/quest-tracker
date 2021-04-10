package oculus.internal.license.parsing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import oculus.internal.gson.HexString;
import oculus.internal.license.parsing.CodableLicense;

/* access modifiers changed from: package-private */
public final class CodableDigestTypeAdapter implements JsonDeserializer<CodableLicense.CodablePackageFilter.CodableSigner.CodableDigest>, JsonSerializer<CodableLicense.CodablePackageFilter.CodableSigner.CodableDigest> {
    CodableDigestTypeAdapter() {
    }

    @Override // com.google.gson.JsonDeserializer
    public CodableLicense.CodablePackageFilter.CodableSigner.CodableDigest deserialize(JsonElement json, Type t, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        return new CodableLicense.CodablePackageFilter.CodableSigner.CodableDigest(obj.getAsJsonPrimitive("algorithm").getAsString(), HexString.decode(obj.getAsJsonPrimitive("digest").getAsString()));
    }

    public JsonElement serialize(CodableLicense.CodablePackageFilter.CodableSigner.CodableDigest codableDigest, Type t, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("algorithm", codableDigest.algorithm);
        obj.addProperty("digest", HexString.encode(codableDigest.digest));
        return obj;
    }
}
