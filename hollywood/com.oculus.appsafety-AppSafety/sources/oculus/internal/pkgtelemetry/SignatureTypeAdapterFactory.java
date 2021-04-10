package oculus.internal.pkgtelemetry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oculus.os.PackageMetadata;
import oculus.internal.gson.Base64EncodedData;
import oculus.internal.gson.HexString;
import oculus.internal.gson.InterstitialTypeAdapterFactory;

/* access modifiers changed from: package-private */
public final class SignatureTypeAdapterFactory extends InterstitialTypeAdapterFactory<PackageMetadata.Signature> {
    SignatureTypeAdapterFactory() {
        super(PackageMetadata.Signature.class);
    }

    /* access modifiers changed from: protected */
    public void beforeWrite(PackageMetadata.Signature data, JsonElement dest) {
        JsonObject obj = dest.getAsJsonObject();
        if (data.fingerprint != null) {
            obj.addProperty("fingerprint", HexString.encode(data.fingerprint));
        }
    }

    /* access modifiers changed from: protected */
    @Override // oculus.internal.gson.InterstitialTypeAdapterFactory
    public void afterRead(JsonElement deserialized) {
        JsonObject obj = deserialized.getAsJsonObject();
        JsonElement fingerprint = obj.get("fingerprint");
        if (fingerprint != null) {
            obj.addProperty("fingerprint", Base64EncodedData.getEncodedString(HexString.decode(fingerprint.getAsString())));
        }
    }
}
