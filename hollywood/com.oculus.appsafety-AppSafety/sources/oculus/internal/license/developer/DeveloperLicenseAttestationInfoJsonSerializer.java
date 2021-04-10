package oculus.internal.license.developer;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;

public class DeveloperLicenseAttestationInfoJsonSerializer implements DeveloperLicenseAttestationInfoSerializer {
    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    @Override // oculus.internal.license.developer.DeveloperLicenseAttestationInfoSerializer
    public byte[] serialize(DeveloperLicenseAttestationInfo info) {
        return this.gson.toJson(info).getBytes(StandardCharsets.UTF_8);
    }
}
