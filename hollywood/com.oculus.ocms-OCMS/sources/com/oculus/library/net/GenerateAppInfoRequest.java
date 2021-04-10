package com.oculus.library.net;

import com.google.common.collect.ImmutableMap;
import com.google.gson.annotations.SerializedName;
import com.oculus.autoupdates.database.AutoUpdatesDBContract;
import java.util.ArrayList;
import java.util.List;

public class GenerateAppInfoRequest {
    private final List<EntitlementIdentifier> mEntitlementIdentifiers = new ArrayList();

    /* access modifiers changed from: private */
    public static class EntitlementIdentifier {
        @SerializedName("app_id")
        public final String itemId;
        @SerializedName(AutoUpdatesDBContract.AutoUpdatesDBTable.COLS.VERSION_CODE)
        public final long versionCode;

        EntitlementIdentifier(String str, long j) {
            this.itemId = str;
            this.versionCode = j;
        }
    }

    public GenerateAppInfoRequest(String str, long j) {
        this.mEntitlementIdentifiers.add(new EntitlementIdentifier(str, j));
    }

    public ImmutableMap<String, ImmutableMap<String, List<Object>>> getParams() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        ArrayList arrayList = new ArrayList(2);
        arrayList.addAll(this.mEntitlementIdentifiers);
        builder.put("app_params", arrayList);
        return ImmutableMap.of("params", builder.build());
    }
}
