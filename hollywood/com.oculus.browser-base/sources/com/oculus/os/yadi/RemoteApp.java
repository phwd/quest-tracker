package com.oculus.os.yadi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RemoteApp implements Parcelable {
    public static final Parcelable.Creator CREATOR = null;
    public final String appId;
    public final ApplicationType appType;
    public final List assetIds;
    public final String binaryResourceId;
    public final long createdTime;
    public final long lastUpdatedTime;
    public final Map metadata;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public enum ApplicationType {
        ANDROID_APP
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Builder {
        public Builder() {
            throw new RuntimeException("Stub!");
        }

        public static Builder forAppId(String str) {
            throw new RuntimeException("Stub!");
        }

        public RemoteApp build() {
            throw new RuntimeException("Stub!");
        }

        public Builder createdOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public Builder lastUpdatedOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public Builder ofType(ApplicationType applicationType) {
            throw new RuntimeException("Stub!");
        }

        public Builder withAssetId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder withAssetIds(Collection collection) {
            throw new RuntimeException("Stub!");
        }

        public Builder withBinaryResourceId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder withMetadata(String str, String str2) {
            throw new RuntimeException("Stub!");
        }

        public Builder withMetadata(Map map) {
            throw new RuntimeException("Stub!");
        }
    }

    public RemoteApp() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public boolean equals(Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int hashCode() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new RuntimeException("Stub!");
    }
}
