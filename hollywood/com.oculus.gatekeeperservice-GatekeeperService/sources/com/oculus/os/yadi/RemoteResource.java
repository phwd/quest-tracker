package com.oculus.os.yadi;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class RemoteResource implements Parcelable {
    public static final Parcelable.Creator<RemoteResource> CREATOR = new Creator(null);
    private final byte[] _digest;
    private final int _hashCode;
    public final HashType algorithm;
    public final String appId;
    public final long createdTime;
    public final ByteBuffer digest;
    public final Uri downloadUri;
    public final Uri filename;
    public final Map<String, String> headers;
    public final long lastUpdatedTime;
    public final ServerType protocol;
    public final String resourceId;

    public enum ServerType {
        HTTP
    }

    /* synthetic */ RemoteResource(String x0, String x1, HashType x2, byte[] x3, Uri x4, Uri x5, ServerType x6, Map x7, long x8, long x9, AnonymousClass1 x10) {
        this(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.os.yadi.RemoteResource$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$yadi$RemoteResource$HashType = new int[HashType.values().length];

        static {
            try {
                $SwitchMap$com$oculus$os$yadi$RemoteResource$HashType[HashType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$os$yadi$RemoteResource$HashType[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum HashType {
        NONE,
        SHA256;

        static int expectedSize(HashType algorithm) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType[algorithm.ordinal()];
            if (i == 1 || i != 2) {
                return 0;
            }
            return 32;
        }
    }

    public static class Builder {
        private HashType _algorithm;
        private String _appId;
        private long _createdTime;
        private byte[] _digest;
        private Uri _downloadUri;
        private Uri _filename;
        private final Map<String, String> _headers = new HashMap();
        private long _lastUpdatedTime;
        private ServerType _protocol;
        private final String _resourceId;

        public static Builder forResourceId(String fbId) {
            return new Builder(fbId);
        }

        public Builder forApp(RemoteApp app) {
            this._appId = app.appId;
            return this;
        }

        public Builder forAppId(String appId) {
            this._appId = appId;
            return this;
        }

        public Builder savedAs(Uri filename) {
            this._filename = filename;
            return this;
        }

        public Builder expectingUnknownContent() {
            this._algorithm = HashType.NONE;
            this._digest = null;
            return this;
        }

        public Builder expectingContent(HashType algorithm, byte[] digest) {
            if (digest.length == HashType.expectedSize(algorithm)) {
                this._algorithm = algorithm;
                this._digest = Arrays.copyOf(digest, digest.length);
                return this;
            }
            throw new IllegalArgumentException("Invalid digest length");
        }

        public Builder expectingContent(HashType algorithm, String digest) {
            if (digest.length() == HashType.expectedSize(algorithm) * 2) {
                this._algorithm = algorithm;
                this._digest = new byte[(digest.length() / 2)];
                for (int i = 0; i < this._digest.length; i++) {
                    int hi = Character.digit(digest.charAt(i * 2), 16);
                    int lo = Character.digit(digest.charAt((i * 2) + 1), 16);
                    if (hi < 0 || lo < 0) {
                        throw new IllegalArgumentException("Invalid character in digest");
                    }
                    this._digest[i] = (byte) ((hi << 4) | lo);
                }
                return this;
            }
            throw new IllegalArgumentException("Invalid digest length");
        }

        public Builder downloadedFrom(Uri downloadUri) {
            this._downloadUri = downloadUri;
            return this;
        }

        public Builder usingProtocol(ServerType protocol) {
            this._protocol = protocol;
            return this;
        }

        public Builder withHeader(String key, String value) {
            this._headers.put(key, value);
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this._headers.putAll(headers);
            return this;
        }

        public Builder createdOn(long createdTime) {
            this._createdTime = createdTime;
            return this;
        }

        public Builder lastUpdatedOn(long lastUpdatedTime) {
            this._lastUpdatedTime = lastUpdatedTime;
            return this;
        }

        public RemoteResource build() {
            if (this._appId != null) {
                HashType hashType = this._algorithm;
                if (hashType == null) {
                    throw new NullPointerException("algorithm");
                } else if (hashType == HashType.NONE || this._digest != null) {
                    Uri uri = this._downloadUri;
                    if (uri != null) {
                        Uri uri2 = this._filename;
                        if (uri2 != null) {
                            ServerType serverType = this._protocol;
                            if (serverType != null) {
                                long j = this._createdTime;
                                if (j > 0) {
                                    long j2 = this._lastUpdatedTime;
                                    if (j2 > 0) {
                                        return new RemoteResource(this._resourceId, this._appId, this._algorithm, this._digest, uri2, uri, serverType, this._headers, j, j2, null);
                                    }
                                    throw new IllegalArgumentException("lastUpdatedTime");
                                }
                                throw new IllegalArgumentException("createdTime");
                            }
                            throw new NullPointerException("protocol");
                        }
                        throw new NullPointerException("filename");
                    }
                    throw new NullPointerException("downloadUri");
                } else {
                    throw new NullPointerException("digest");
                }
            } else {
                throw new NullPointerException("appId");
            }
        }

        private Builder(String resourceId) {
            if (resourceId != null) {
                this._resourceId = resourceId;
                return;
            }
            throw new NullPointerException("resourceId");
        }
    }

    private static class Creator implements Parcelable.Creator<RemoteResource> {
        private Creator() {
        }

        /* synthetic */ Creator(AnonymousClass1 x0) {
            this();
        }

        @Override // android.os.Parcelable.Creator
        public RemoteResource[] newArray(int size) {
            return new RemoteResource[size];
        }

        @Override // android.os.Parcelable.Creator
        public RemoteResource createFromParcel(Parcel p) {
            byte[] digest;
            String resourceId = p.readString();
            String appId = p.readString();
            HashType algorithm = HashType.values()[p.readInt()];
            if (algorithm != HashType.NONE) {
                byte[] digest2 = new byte[HashType.expectedSize(algorithm)];
                p.readByteArray(digest2);
                digest = digest2;
            } else {
                digest = null;
            }
            Uri filename = Uri.parse(p.readString());
            Uri downloadUri = Uri.parse(p.readString());
            ServerType protocol = ServerType.values()[p.readInt()];
            int headerSize = p.readInt();
            Map<String, String> headers = new HashMap<>(headerSize);
            for (int i = 0; i < headerSize; i++) {
                headers.put(p.readString(), p.readString());
            }
            return new RemoteResource(resourceId, appId, algorithm, digest, filename, downloadUri, protocol, headers, p.readLong(), p.readLong(), null);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceId);
        dest.writeString(this.appId);
        dest.writeInt(this.algorithm.ordinal());
        if (this.algorithm != HashType.NONE) {
            dest.writeByteArray(this._digest);
        }
        dest.writeString(this.filename.toString());
        dest.writeString(this.downloadUri.toString());
        dest.writeInt(this.protocol.ordinal());
        dest.writeInt(this.headers.size());
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
        dest.writeLong(this.createdTime);
        dest.writeLong(this.lastUpdatedTime);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || RemoteResource.class != other.getClass()) {
            return false;
        }
        RemoteResource o = (RemoteResource) other;
        if (this.createdTime != o.createdTime || this.lastUpdatedTime != o.lastUpdatedTime || this.protocol != o.protocol || this.algorithm != o.algorithm || !this.resourceId.equals(o.resourceId) || !this.appId.equals(o.appId) || !this.filename.equals(o.filename) || !this.downloadUri.equals(o.downloadUri) || !Arrays.equals(this._digest, o._digest) || !this.headers.equals(o.headers)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this._hashCode;
    }

    private RemoteResource(String resourceId2, String appId2, HashType algorithm2, byte[] digest2, Uri filename2, Uri downloadUri2, ServerType protocol2, Map<String, String> headers2, long createdTime2, long lastUpdatedTime2) {
        this.resourceId = resourceId2;
        this.appId = appId2;
        this.algorithm = algorithm2;
        this.filename = filename2;
        this.downloadUri = downloadUri2;
        this.protocol = protocol2;
        this.headers = Collections.unmodifiableMap(headers2);
        this.createdTime = createdTime2;
        this.lastUpdatedTime = lastUpdatedTime2;
        this._digest = digest2;
        this.digest = algorithm2 != HashType.NONE ? ByteBuffer.wrap(digest2).asReadOnlyBuffer() : null;
        int hash = (((7 * 31) + resourceId2.hashCode()) * 31) + appId2.hashCode();
        byte[] bArr = this._digest;
        this._hashCode = ((((((((((((((((((bArr != null ? (hash * 31) + Arrays.hashCode(bArr) : hash) * 31) + filename2.hashCode()) * 31) + downloadUri2.hashCode()) * 31) + protocol2.hashCode()) * 31) + headers2.hashCode()) * 31) + algorithm2.hashCode()) * 31) + ((int) (createdTime2 >> 32))) * 31) + ((int) (createdTime2 & 4294967295L))) * 31) + ((int) (lastUpdatedTime2 >> 32))) * 31) + ((int) (lastUpdatedTime2 & 4294967295L));
    }
}
