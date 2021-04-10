package com.oculus.horizon.api.store;

import com.google.common.collect.ImmutableMap;
import com.oculus.http.core.base.ApiRequest;
import com.oculus.ocms.library.provider.contract.TrustedBinaryContract;

public class StoresRequest extends ApiRequest<StoresResponse> {
    public final StoreOverride appStoreOverride;
    public final StoreOverride conceptsStoreOverride;
    public final StoreVersion storeVersion;

    public enum StoreOverride {
        NONE(TrustedBinaryContract.ACTION_NONE),
        TEST_STORE("HORIZON_TEST_STORE"),
        CONCEPTS_TEST_STORE("HORIZON_CONCEPTS_TEST_STORE");
        
        public final String graphQLString;

        private StoreOverride(String str) {
            this.graphQLString = str;
        }
    }

    public enum StoreVersion {
        V2("v2");
        
        public final String headerValue;

        private StoreVersion(String str) {
            this.headerValue = str;
        }
    }

    public StoresRequest(StoreVersion storeVersion2, boolean z) {
        this.storeVersion = storeVersion2;
        this.appStoreOverride = z ? StoreOverride.TEST_STORE : StoreOverride.NONE;
        this.conceptsStoreOverride = z ? StoreOverride.CONCEPTS_TEST_STORE : StoreOverride.NONE;
    }

    public ImmutableMap<String, String> getParams() {
        return ImmutableMap.builder().put("app_store_override", this.appStoreOverride.graphQLString).put("concepts_store_override", this.conceptsStoreOverride.graphQLString).build();
    }
}
