package oculus.internal.pkgtelemetry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oculus.os.PackageMetadata;
import oculus.internal.gson.Base64EncodedData;
import oculus.internal.gson.HexString;
import oculus.internal.gson.InterstitialTypeAdapterFactory;

/* access modifiers changed from: package-private */
public final class PackageMetadataTypeAdapterFactory extends InterstitialTypeAdapterFactory<PackageMetadata> {
    PackageMetadataTypeAdapterFactory() {
        super(PackageMetadata.class);
    }

    /* access modifiers changed from: protected */
    public void beforeWrite(PackageMetadata value, JsonElement dest) {
        JsonObject obj = dest.getAsJsonObject();
        if (value.packageHash != null) {
            obj.addProperty(Package.PACKAGE_HASH, HexString.encode(value.packageHash));
        }
    }

    /* access modifiers changed from: protected */
    @Override // oculus.internal.gson.InterstitialTypeAdapterFactory
    public void afterRead(JsonElement deserialized) {
        JsonObject obj = deserialized.getAsJsonObject();
        JsonElement packageHash = obj.get(Package.PACKAGE_HASH);
        if (packageHash != null) {
            obj.addProperty(Package.PACKAGE_HASH, Base64EncodedData.getEncodedString(HexString.decode(packageHash.getAsString())));
        }
    }
}
