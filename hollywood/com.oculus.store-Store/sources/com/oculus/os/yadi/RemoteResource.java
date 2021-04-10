package com.oculus.os.yadi;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.util.Map;

public final class RemoteResource implements Parcelable {
    public static final Parcelable.Creator<RemoteResource> CREATOR = null;
    public final HashType algorithm = null;
    public final String appId = null;
    public final long createdTime = 0;
    public final ByteBuffer digest = null;
    public final Uri downloadUri = null;
    public final Uri filename = null;
    public final Map<String, String> headers = null;
    public final long lastUpdatedTime = 0;
    public final ServerType protocol = null;
    public final String resourceId = null;

    public enum HashType {
        NONE,
        SHA256
    }

    public enum ServerType {
        HTTP
    }

    RemoteResource(String resourceId2, String appId2, HashType algorithm2, byte[] digest2, Uri filename2, Uri downloadUri2, ServerType protocol2, Map<String, String> map, long createdTime2, long lastUpdatedTime2) {
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
        Builder(String resourceId) {
            throw new RuntimeException("Stub!");
        }

        public static Builder forResourceId(String fbId) {
            throw new RuntimeException("Stub!");
        }

        public Builder forApp(RemoteApp app) {
            throw new RuntimeException("Stub!");
        }

        public Builder forAppId(String appId) {
            throw new RuntimeException("Stub!");
        }

        public Builder savedAs(Uri filename) {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingUnknownContent() {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingContent(HashType algorithm, byte[] digest) {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingContent(HashType algorithm, String digest) {
            throw new RuntimeException("Stub!");
        }

        public Builder downloadedFrom(Uri downloadUri) {
            throw new RuntimeException("Stub!");
        }

        public Builder usingProtocol(ServerType protocol) {
            throw new RuntimeException("Stub!");
        }

        public Builder withHeader(String key, String value) {
            throw new RuntimeException("Stub!");
        }

        public Builder withHeaders(Map<String, String> map) {
            throw new RuntimeException("Stub!");
        }

        public Builder createdOn(long createdTime) {
            throw new RuntimeException("Stub!");
        }

        public Builder lastUpdatedOn(long lastUpdatedTime) {
            throw new RuntimeException("Stub!");
        }

        public RemoteResource build() {
            throw new RuntimeException("Stub!");
        }
    }
}
