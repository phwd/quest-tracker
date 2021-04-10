package com.oculus.library.net;

import com.google.gson.annotations.SerializedName;
import com.oculus.horizon.deeplinking.contract.StoreContract;
import java.util.List;

public class GenerateAppInfoRequest {
    public final List<EntitlementIdentifier> mEntitlementIdentifiers;

    public static class EntitlementIdentifier {
        @SerializedName("app_id")
        public final String itemId;
        @SerializedName(StoreContract.ARG_DEFAULT_VERSION_CODE)
        public final long versionCode;
    }
}
