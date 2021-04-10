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

    RemoteApp(String appId2, String binaryResourceId2, List<String> list, ApplicationType appType2, Map<String, String> map, long createdTime2, long lastUpdatedTime2) {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object other) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public static class Builder {
        Builder(String appId) {
            throw new RuntimeException("Stub!");
        }

        public static Builder forAppId(String appId) {
            throw new RuntimeException("Stub!");
        }

        public Builder withBinaryResourceId(String binaryResourceId) {
            throw new RuntimeException("Stub!");
        }

        public Builder withAssetId(String assetId) {
            throw new RuntimeException("Stub!");
        }

        public Builder withAssetIds(Collection<String> collection) {
            throw new RuntimeException("Stub!");
        }

        public Builder withMetadata(String key, String value) {
            throw new RuntimeException("Stub!");
        }

        public Builder withMetadata(Map<String, String> map) {
            throw new RuntimeException("Stub!");
        }

        public Builder ofType(ApplicationType appType) {
            throw new RuntimeException("Stub!");
        }

        public Builder createdOn(long createdTime) {
            throw new RuntimeException("Stub!");
        }

        public Builder lastUpdatedOn(long lastUpdatedTime) {
            throw new RuntimeException("Stub!");
        }

        public RemoteApp build() {
            throw new RuntimeException("Stub!");
        }
    }
}
