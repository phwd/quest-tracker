package com.oculus.library.net;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class GenerateUpdateInfoRequest {
    private List<EntitlementIdentifier> mEntitlementIdentifiers;

    public static class EntitlementIdentifier {
        @SerializedName("installed_version_code")
        @Nullable
        public final String installedVersionCode;
        @SerializedName("app_id")
        public final String itemId;

        public EntitlementIdentifier(String str, long j) {
            this.itemId = str;
            this.installedVersionCode = Long.toString(j);
        }

        public EntitlementIdentifier(String str) {
            this.itemId = str;
            this.installedVersionCode = null;
        }
    }

    public GenerateUpdateInfoRequest(List<EntitlementIdentifier> list) {
        this.mEntitlementIdentifiers = list;
    }

    public ImmutableMap<String, ImmutableMap<String, List<Object>>> getParams() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        ArrayList arrayList = new ArrayList(this.mEntitlementIdentifiers.size());
        arrayList.addAll(this.mEntitlementIdentifiers);
        builder.put("app_params", arrayList);
        return ImmutableMap.of("params", builder.build());
    }
}
