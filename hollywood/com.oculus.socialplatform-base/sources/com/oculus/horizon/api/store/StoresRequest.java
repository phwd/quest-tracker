package com.oculus.horizon.api.store;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;

public class StoresRequest extends ApiRequest<StoresResponse> {
    public final StoreOverride appStoreOverride;
    public final StoreOverride conceptsStoreOverride;
    public final StoreVersion storeVersion;

    public enum StoreOverride {
        NONE("NONE"),
        TEST_STORE("HORIZON_TEST_STORE"),
        CONCEPTS_TEST_STORE("HORIZON_CONCEPTS_TEST_STORE");
        
        public final String graphQLString;

        /* access modifiers changed from: public */
        StoreOverride(String str) {
            this.graphQLString = str;
        }
    }

    public enum StoreVersion {
        V2("v2");
        
        public final String headerValue;

        /* access modifiers changed from: public */
        StoreVersion(String str) {
            this.headerValue = str;
        }
    }

    public StoresRequest(StoreVersion storeVersion2, boolean z) {
        StoreOverride storeOverride;
        StoreOverride storeOverride2;
        this.storeVersion = storeVersion2;
        if (z) {
            storeOverride = StoreOverride.TEST_STORE;
        } else {
            storeOverride = StoreOverride.NONE;
        }
        this.appStoreOverride = storeOverride;
        if (z) {
            storeOverride2 = StoreOverride.CONCEPTS_TEST_STORE;
        } else {
            storeOverride2 = StoreOverride.NONE;
        }
        this.conceptsStoreOverride = storeOverride2;
    }

    public ImmutableMap<String, String> getParams() {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("app_store_override", this.appStoreOverride.graphQLString);
        A04.put("concepts_store_override", this.conceptsStoreOverride.graphQLString);
        return A04.build();
    }
}
