package com.oculus.library.net;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import javax.annotation.Nullable;

public class GenerateUpdateInfoRequest {
    public List<EntitlementIdentifier> mEntitlementIdentifiers;

    public static class EntitlementIdentifier {
        @SerializedName("installed_version_code")
        @Nullable
        public final String installedVersionCode;
        @SerializedName("app_id")
        public final String itemId;
    }
}
