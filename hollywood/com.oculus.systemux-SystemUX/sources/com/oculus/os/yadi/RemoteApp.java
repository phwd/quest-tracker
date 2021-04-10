package com.oculus.os.yadi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RemoteApp implements Parcelable {
    public static final Parcelable.Creator<RemoteApp> CREATOR = null;
    public final String appId = null;
    public final ApplicationType appType = null;
    public final List<String> assetIds = null;
    public final String binaryResourceId = null;
    public final long createdTime = 0;
    public final long lastUpdatedTime = 0;
    public final Map<String, String> metadata = null;

    public enum ApplicationType {
        ANDROID_APP
    }

    RemoteApp(String str, String str2, List<String> list, ApplicationType applicationType, Map<String, String> map, long j, long j2) {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public static class Builder {
        Builder(String str) {
            throw new RuntimeException("Stub!");
        }

        public static Builder forAppId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder withBinaryResourceId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder withAssetId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder withAssetIds(Collection<String> collection) {
            throw new RuntimeException("Stub!");
        }

        public Builder withMetadata(String str, String str2) {
            throw new RuntimeException("Stub!");
        }

        public Builder withMetadata(Map<String, String> map) {
            throw new RuntimeException("Stub!");
        }

        public Builder ofType(ApplicationType applicationType) {
            throw new RuntimeException("Stub!");
        }

        public Builder createdOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public Builder lastUpdatedOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public RemoteApp build() {
            throw new RuntimeException("Stub!");
        }
    }
}
