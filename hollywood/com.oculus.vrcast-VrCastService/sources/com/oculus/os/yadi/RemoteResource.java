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

    public int describeContents() {
        return 0;
    }

    /* synthetic */ RemoteResource(String str, String str2, HashType hashType, byte[] bArr, Uri uri, Uri uri2, ServerType serverType, Map map, long j, long j2, AnonymousClass1 r13) {
        this(str, str2, hashType, bArr, uri, uri2, serverType, map, j, j2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.os.yadi.RemoteResource$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$os$yadi$RemoteResource$HashType = new int[HashType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.os.yadi.RemoteResource$HashType[] r0 = com.oculus.os.yadi.RemoteResource.HashType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.os.yadi.RemoteResource.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType = r0
                int[] r0 = com.oculus.os.yadi.RemoteResource.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.os.yadi.RemoteResource$HashType r1 = com.oculus.os.yadi.RemoteResource.HashType.NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.os.yadi.RemoteResource.AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.os.yadi.RemoteResource$HashType r1 = com.oculus.os.yadi.RemoteResource.HashType.SHA256     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.yadi.RemoteResource.AnonymousClass1.<clinit>():void");
        }
    }

    public enum HashType {
        NONE,
        SHA256;

        static int expectedSize(HashType hashType) {
            int i = AnonymousClass1.$SwitchMap$com$oculus$os$yadi$RemoteResource$HashType[hashType.ordinal()];
            return (i == 1 || i != 2) ? 0 : 32;
        }
    }

    private static class Creator implements Parcelable.Creator<RemoteResource> {
        private Creator() {
        }

        /* synthetic */ Creator(AnonymousClass1 r1) {
            this();
        }

        @Override // android.os.Parcelable.Creator
        public RemoteResource[] newArray(int i) {
            return new RemoteResource[i];
        }

        @Override // android.os.Parcelable.Creator
        public RemoteResource createFromParcel(Parcel parcel) {
            byte[] bArr;
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            HashType hashType = HashType.values()[parcel.readInt()];
            if (hashType != HashType.NONE) {
                bArr = new byte[HashType.expectedSize(hashType)];
                parcel.readByteArray(bArr);
            } else {
                bArr = null;
            }
            Uri parse = Uri.parse(parcel.readString());
            Uri parse2 = Uri.parse(parcel.readString());
            ServerType serverType = ServerType.values()[parcel.readInt()];
            int readInt = parcel.readInt();
            HashMap hashMap = new HashMap(readInt);
            for (int i = 0; i < readInt; i++) {
                hashMap.put(parcel.readString(), parcel.readString());
            }
            return new RemoteResource(readString, readString2, hashType, bArr, parse, parse2, serverType, hashMap, parcel.readLong(), parcel.readLong(), null);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.resourceId);
        parcel.writeString(this.appId);
        parcel.writeInt(this.algorithm.ordinal());
        if (this.algorithm != HashType.NONE) {
            parcel.writeByteArray(this._digest);
        }
        parcel.writeString(this.filename.toString());
        parcel.writeString(this.downloadUri.toString());
        parcel.writeInt(this.protocol.ordinal());
        parcel.writeInt(this.headers.size());
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            parcel.writeString(entry.getKey());
            parcel.writeString(entry.getValue());
        }
        parcel.writeLong(this.createdTime);
        parcel.writeLong(this.lastUpdatedTime);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || RemoteResource.class != obj.getClass()) {
            return false;
        }
        RemoteResource remoteResource = (RemoteResource) obj;
        return this.createdTime == remoteResource.createdTime && this.lastUpdatedTime == remoteResource.lastUpdatedTime && this.protocol == remoteResource.protocol && this.algorithm == remoteResource.algorithm && this.resourceId.equals(remoteResource.resourceId) && this.appId.equals(remoteResource.appId) && this.filename.equals(remoteResource.filename) && this.downloadUri.equals(remoteResource.downloadUri) && Arrays.equals(this._digest, remoteResource._digest) && this.headers.equals(remoteResource.headers);
    }

    public int hashCode() {
        return this._hashCode;
    }

    private RemoteResource(String str, String str2, HashType hashType, byte[] bArr, Uri uri, Uri uri2, ServerType serverType, Map<String, String> map, long j, long j2) {
        this.resourceId = str;
        this.appId = str2;
        this.algorithm = hashType;
        this.filename = uri;
        this.downloadUri = uri2;
        this.protocol = serverType;
        this.headers = Collections.unmodifiableMap(map);
        this.createdTime = j;
        this.lastUpdatedTime = j2;
        this._digest = bArr;
        this.digest = hashType != HashType.NONE ? ByteBuffer.wrap(bArr).asReadOnlyBuffer() : null;
        int hashCode = ((217 + str.hashCode()) * 31) + str2.hashCode();
        byte[] bArr2 = this._digest;
        this._hashCode = ((((((((((((((((((bArr2 != null ? (hashCode * 31) + Arrays.hashCode(bArr2) : hashCode) * 31) + uri.hashCode()) * 31) + uri2.hashCode()) * 31) + serverType.hashCode()) * 31) + map.hashCode()) * 31) + hashType.hashCode()) * 31) + ((int) (j >> 32))) * 31) + ((int) (j & 4294967295L))) * 31) + ((int) (j2 >> 32))) * 31) + ((int) (j2 & 4294967295L));
    }
}
