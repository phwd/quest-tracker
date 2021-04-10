package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

public class MediaItem2 {
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private static final String KEY_FLAGS = "android.media.mediaitem2.flags";
    private static final String KEY_ID = "android.media.mediaitem2.id";
    private static final String KEY_METADATA = "android.media.mediaitem2.metadata";
    private static final String KEY_UUID = "android.media.mediaitem2.uuid";
    private DataSourceDesc mDataSourceDesc;
    private final int mFlags;
    private final String mId;
    private MediaMetadata2 mMetadata;
    private final UUID mUUID;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface Flags {
    }

    private MediaItem2(@NonNull String mediaId, @Nullable DataSourceDesc dsd, @Nullable MediaMetadata2 metadata, int flags) {
        this(mediaId, dsd, metadata, flags, (UUID) null);
    }

    private MediaItem2(@NonNull String mediaId, @Nullable DataSourceDesc dsd, @Nullable MediaMetadata2 metadata, int flags, @Nullable UUID uuid) {
        if (mediaId == null) {
            throw new IllegalArgumentException("mediaId shouldn't be null");
        } else if (metadata == null || TextUtils.equals(mediaId, metadata.getMediaId())) {
            this.mId = mediaId;
            this.mDataSourceDesc = dsd;
            this.mMetadata = metadata;
            this.mFlags = flags;
            this.mUUID = uuid == null ? UUID.randomUUID() : uuid;
        } else {
            throw new IllegalArgumentException("metadata's id should be matched with the mediaid");
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, this.mId);
        bundle.putInt(KEY_FLAGS, this.mFlags);
        MediaMetadata2 mediaMetadata2 = this.mMetadata;
        if (mediaMetadata2 != null) {
            bundle.putBundle(KEY_METADATA, mediaMetadata2.toBundle());
        }
        bundle.putString(KEY_UUID, this.mUUID.toString());
        return bundle;
    }

    public static MediaItem2 fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return fromBundle(bundle, UUID.fromString(bundle.getString(KEY_UUID)));
    }

    static MediaItem2 fromBundle(@NonNull Bundle bundle, @Nullable UUID uuid) {
        MediaMetadata2 metadata = null;
        if (bundle == null) {
            return null;
        }
        String id = bundle.getString(KEY_ID);
        Bundle metadataBundle = bundle.getBundle(KEY_METADATA);
        if (metadataBundle != null) {
            metadata = MediaMetadata2.fromBundle(metadataBundle);
        }
        return new MediaItem2(id, (DataSourceDesc) null, metadata, bundle.getInt(KEY_FLAGS), uuid);
    }

    public String toString() {
        return "MediaItem2{" + "mFlags=" + this.mFlags + ", mMetadata=" + ((Object) this.mMetadata) + '}';
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean isBrowsable() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isPlayable() {
        return (this.mFlags & 2) != 0;
    }

    public void setMetadata(@Nullable MediaMetadata2 metadata) {
        if (metadata == null || TextUtils.equals(this.mId, metadata.getMediaId())) {
            this.mMetadata = metadata;
            return;
        }
        throw new IllegalArgumentException("metadata's id should be matched with the mediaId");
    }

    @Nullable
    public MediaMetadata2 getMetadata() {
        return this.mMetadata;
    }

    public String getMediaId() {
        return this.mId;
    }

    @Nullable
    public DataSourceDesc getDataSourceDesc() {
        return this.mDataSourceDesc;
    }

    public int hashCode() {
        return this.mUUID.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaItem2)) {
            return false;
        }
        return this.mUUID.equals(((MediaItem2) obj).mUUID);
    }

    public static final class Builder {
        private DataSourceDesc mDataSourceDesc;
        private int mFlags;
        private String mMediaId;
        private MediaMetadata2 mMetadata;

        public Builder(int flags) {
            this.mFlags = flags;
        }

        public Builder setMediaId(@Nullable String mediaId) {
            this.mMediaId = mediaId;
            return this;
        }

        public Builder setMetadata(@Nullable MediaMetadata2 metadata) {
            this.mMetadata = metadata;
            return this;
        }

        public Builder setDataSourceDesc(@Nullable DataSourceDesc dataSourceDesc) {
            this.mDataSourceDesc = dataSourceDesc;
            return this;
        }

        public MediaItem2 build() {
            MediaMetadata2 mediaMetadata2 = this.mMetadata;
            String id = mediaMetadata2 != null ? mediaMetadata2.getString("android.media.metadata.MEDIA_ID") : null;
            if (id == null) {
                String str = this.mMediaId;
                if (str == null) {
                    str = toString();
                }
                id = str;
            }
            return new MediaItem2(id, this.mDataSourceDesc, this.mMetadata, this.mFlags);
        }
    }
}
