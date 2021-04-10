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

    RemoteResource(String str, String str2, HashType hashType, byte[] bArr, Uri uri, Uri uri2, ServerType serverType, Map<String, String> map, long j, long j2) {
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

        public static Builder forResourceId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder forApp(RemoteApp remoteApp) {
            throw new RuntimeException("Stub!");
        }

        public Builder forAppId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder savedAs(Uri uri) {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingUnknownContent() {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingContent(HashType hashType, byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingContent(HashType hashType, String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder downloadedFrom(Uri uri) {
            throw new RuntimeException("Stub!");
        }

        public Builder usingProtocol(ServerType serverType) {
            throw new RuntimeException("Stub!");
        }

        public Builder withHeader(String str, String str2) {
            throw new RuntimeException("Stub!");
        }

        public Builder withHeaders(Map<String, String> map) {
            throw new RuntimeException("Stub!");
        }

        public Builder createdOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public Builder lastUpdatedOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public RemoteResource build() {
            throw new RuntimeException("Stub!");
        }
    }
}
