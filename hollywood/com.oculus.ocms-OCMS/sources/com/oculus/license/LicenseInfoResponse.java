package com.oculus.license;

import com.google.gson.annotations.SerializedName;
import com.oculus.http.core.annotations.SingleEntryMapResponse;

@SingleEntryMapResponse
public class LicenseInfoResponse {
    @SerializedName("app_store_item")
    public AppStoreItem appStoreItem;

    public static class AppStoreItem {
        @SerializedName("entitlement")
        public Entitlement entitlement;
    }

    public static class Entitlement {
        @SerializedName("license")
        public License license;
    }

    public static class License {
        @SerializedName("encoded")
        public String encodedLicenseBlob;
    }
}
