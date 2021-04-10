package com.oculus.os.yadi;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RemoteResource implements Parcelable {
    public static final Parcelable.Creator CREATOR = null;
    public final HashType algorithm;
    public final String appId;
    public final long createdTime;
    public final ByteBuffer digest;
    public final Uri downloadUri;
    public final Uri filename;
    public final Map headers;
    public final long lastUpdatedTime;
    public final ServerType protocol;
    public final String resourceId;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Builder {
        public Builder() {
            throw new RuntimeException("Stub!");
        }

        public static Builder forResourceId(String str) {
            throw new RuntimeException("Stub!");
        }

        public RemoteResource build() {
            throw new RuntimeException("Stub!");
        }

        public Builder createdOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public Builder downloadedFrom(Uri uri) {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingContent(HashType hashType, byte[] bArr) {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingUnknownContent() {
            throw new RuntimeException("Stub!");
        }

        public Builder forApp(RemoteApp remoteApp) {
            throw new RuntimeException("Stub!");
        }

        public Builder forAppId(String str) {
            throw new RuntimeException("Stub!");
        }

        public Builder lastUpdatedOn(long j) {
            throw new RuntimeException("Stub!");
        }

        public Builder savedAs(Uri uri) {
            throw new RuntimeException("Stub!");
        }

        public Builder usingProtocol(ServerType serverType) {
            throw new RuntimeException("Stub!");
        }

        public Builder withHeader(String str, String str2) {
            throw new RuntimeException("Stub!");
        }

        public Builder withHeaders(Map map) {
            throw new RuntimeException("Stub!");
        }

        public Builder expectingContent(HashType hashType, String str) {
            throw new RuntimeException("Stub!");
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public enum HashType {
        NONE,
        SHA256
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public enum ServerType {
        HTTP
    }

    public RemoteResource() {
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
