package oculus.internal.pkgtelemetry;

import com.google.gson.FieldNamingPolicy;
import com.oculus.os.PackageMetadata;
import java.security.cert.X509Certificate;
import oculus.internal.gson.Base64EncodedData;
import oculus.internal.gson.CertificateAdapter;

public final class GsonBuilder {
    public static com.google.gson.GsonBuilder builder() {
        return new com.google.gson.GsonBuilder().disableHtmlEscaping().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeAdapter(X509Certificate.class, new CertificateAdapter(false, false)).registerTypeAdapter(byte[].class, new Base64EncodedData.GsonTypeAdapter()).registerTypeAdapter(PackageMetadata.Signature.Scheme.class, new SignatureSchemeTypeAdapter()).registerTypeAdapterFactory(new PackageMetadataTypeAdapterFactory()).registerTypeAdapterFactory(new SignatureTypeAdapterFactory());
    }
}
