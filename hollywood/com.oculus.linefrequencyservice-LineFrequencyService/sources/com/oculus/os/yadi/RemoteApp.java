package com.oculus.os.yadi;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteApp implements Parcelable {
    public static final Parcelable.Creator<RemoteApp> CREATOR = new Creator();
    private final int _hashCode;
    public final String appId;
    public final ApplicationType appType;
    public final List<String> assetIds;
    public final String binaryResourceId;
    public final long createdTime;
    public final long lastUpdatedTime;
    public final Map<String, String> metadata;

    public enum ApplicationType {
        ANDROID_APP
    }

    public static class Builder {
        private final String _appId;
        private ApplicationType _appType;
        private final List<String> _assetIds = new ArrayList();
        private String _binaryResourceId;
        private long _createdTime;
        private long _lastUpdatedTime;
        private final Map<String, String> _metadata = new HashMap();

        public static Builder forAppId(String appId) {
            return new Builder(appId);
        }

        public Builder withBinaryResourceId(String binaryResourceId) {
            this._binaryResourceId = binaryResourceId;
            return this;
        }

        public Builder withAssetId(String assetId) {
            this._assetIds.add(assetId);
            return this;
        }

        public Builder withAssetIds(Collection<String> assetIds) {
            this._assetIds.addAll(assetIds);
            return this;
        }

        public Builder withMetadata(String key, String value) {
            this._metadata.put(key, value);
            return this;
        }

        public Builder withMetadata(Map<String, String> metadata) {
            this._metadata.putAll(metadata);
            return this;
        }

        public Builder ofType(ApplicationType appType) {
            this._appType = appType;
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

        public RemoteApp build() {
            String str = this._binaryResourceId;
            if (str != null) {
                ApplicationType applicationType = this._appType;
                if (applicationType != null) {
                    long j = this._createdTime;
                    if (j > 0) {
                        long j2 = this._lastUpdatedTime;
                        if (j2 > 0) {
                            return new RemoteApp(this._appId, str, this._assetIds, applicationType, this._metadata, j, j2);
                        }
                        throw new IllegalArgumentException("lastUpdatedTime");
                    }
                    throw new IllegalArgumentException("createdTime");
                }
                throw new NullPointerException("appType");
            }
            throw new NullPointerException("binaryResourceId");
        }

        private Builder(String appId) {
            if (appId != null) {
                this._appId = appId;
                return;
            }
            throw new NullPointerException("appId");
        }
    }

    private static class Creator implements Parcelable.Creator<RemoteApp> {
        private Creator() {
        }

        @Override // android.os.Parcelable.Creator
        public RemoteApp[] newArray(int size) {
            return new RemoteApp[size];
        }

        @Override // android.os.Parcelable.Creator
        public RemoteApp createFromParcel(Parcel p) {
            String appId = p.readString();
            String binaryResourceId = p.readString();
            List<String> assetIds = new ArrayList<>();
            p.readStringList(assetIds);
            ApplicationType appType = ApplicationType.values()[p.readInt()];
            int metadataSize = p.readInt();
            Map<String, String> metadata = new HashMap<>(metadataSize);
            for (int i = 0; i < metadataSize; i++) {
                metadata.put(p.readString(), p.readString());
            }
            return new RemoteApp(appId, binaryResourceId, assetIds, appType, metadata, p.readLong(), p.readLong());
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appId);
        dest.writeString(this.binaryResourceId);
        dest.writeStringList(this.assetIds);
        dest.writeInt(this.appType.ordinal());
        dest.writeInt(this.metadata.size());
        for (Map.Entry<String, String> entry : this.metadata.entrySet()) {
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
        if (other == null || RemoteApp.class != other.getClass()) {
            return false;
        }
        RemoteApp o = (RemoteApp) other;
        if (this.createdTime != o.createdTime || this.lastUpdatedTime != o.lastUpdatedTime || this.appType != o.appType || !this.appId.equals(o.appId) || !this.binaryResourceId.equals(o.binaryResourceId) || !this.assetIds.equals(o.assetIds) || !this.metadata.equals(o.metadata)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this._hashCode;
    }

    private RemoteApp(String appId2, String binaryResourceId2, List<String> assetIds2, ApplicationType appType2, Map<String, String> metadata2, long createdTime2, long lastUpdatedTime2) {
        this.appId = appId2;
        this.binaryResourceId = binaryResourceId2;
        this.assetIds = Collections.unmodifiableList(assetIds2);
        this.appType = appType2;
        this.metadata = Collections.unmodifiableMap(metadata2);
        this.createdTime = createdTime2;
        this.lastUpdatedTime = lastUpdatedTime2;
        int hash = (((((((((((7 * 31) + appId2.hashCode()) * 31) + binaryResourceId2.hashCode()) * 31) + ((int) (createdTime2 >> 32))) * 31) + ((int) (createdTime2 & 4294967295L))) * 31) + ((int) (lastUpdatedTime2 >> 32))) * 31) + ((int) (lastUpdatedTime2 & 4294967295L));
        for (String asset : assetIds2) {
            hash = (hash * 31) + asset.hashCode();
        }
        for (Map.Entry<String, String> entry : metadata2.entrySet()) {
            hash = (((hash * 31) + entry.getKey().hashCode()) * 31) + entry.getValue().hashCode();
        }
        this._hashCode = hash;
    }
}
