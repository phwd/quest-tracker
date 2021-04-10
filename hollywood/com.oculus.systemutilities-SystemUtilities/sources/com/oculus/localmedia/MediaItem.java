package com.oculus.localmedia;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.oculus.localmedia.database.LocalMediaCacheDatabase;
import com.oculus.localmedia.metadata.CachedMetadata;
import com.oculus.localmedia.metadata.CachedMetadataManager;
import com.oculus.localmedia.metadata.FilenameMetadata;
import com.oculus.localmedia.metadata.FilenameMetadataParser;
import com.oculus.localmedia.metadata.ImageHeaderMetadata;
import com.oculus.localmedia.metadata.ImageHeaderMetadataParser;
import com.oculus.localmedia.metadata.SidecarMetadata;
import com.oculus.localmedia.metadata.SidecarMetadataParser;
import com.oculus.localmedia.metadata.SphericalVideoMetadata;
import com.oculus.localmedia.metadata.SphericalVideoMetadataParser;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaItem {
    public static Comparator<MediaItem> DATE_ADDED_COMPARATOR = new Comparator<MediaItem>() {
        /* class com.oculus.localmedia.MediaItem.AnonymousClass1 */

        public int compare(MediaItem mediaItem1, MediaItem mediaItem2) {
            return (int) (mediaItem2.mDateAdded - mediaItem1.mDateAdded);
        }
    };
    public static Comparator<MediaItem> DURATION_COMPARATOR = new Comparator<MediaItem>() {
        /* class com.oculus.localmedia.MediaItem.AnonymousClass2 */

        public int compare(MediaItem mediaItem1, MediaItem mediaItem2) {
            return mediaItem2.mDurationSecs - mediaItem1.mDurationSecs;
        }
    };
    public static Comparator<MediaItem> FILE_SIZE_COMPARATOR = new Comparator<MediaItem>() {
        /* class com.oculus.localmedia.MediaItem.AnonymousClass3 */

        public int compare(MediaItem mediaItem1, MediaItem mediaItem2) {
            return (int) (mediaItem2.mFileSize - mediaItem1.mFileSize);
        }
    };
    private static final String[] IMAGE_THUMBNAIL_PROJECTION = {"_id", "image_id", "_data"};
    private static final String[] MEDIA_ID_PROJECTION = {"_id"};
    public static Comparator<MediaItem> NAME_COMPARATOR = new Comparator<MediaItem>() {
        /* class com.oculus.localmedia.MediaItem.AnonymousClass4 */

        public int compare(MediaItem mediaItem1, MediaItem mediaItem2) {
            return mediaItem1.getDisplayName().compareToIgnoreCase(mediaItem2.getDisplayName());
        }
    };
    private static final String[] VIDEO_THUMBNAIL_PROJECTION = {"_id", "video_id", "_data"};
    private String mAudioFilePath;
    private String mAuthHeader;
    private long mDateAdded;
    private String mDownloadUrl;
    private int mDurationSecs;
    private String mExternalFilePath;
    private String mExternalThumbnailFilePath;
    private String mFilePath;
    private long mFileSize;
    private FilenameMetadata mFilenameMetadata;
    private String mFolderPath;
    private int mHeight;
    private ImageHeaderMetadata mImageHeaderMetadata;
    private int mInternalId;
    private boolean mIsDownloadable;
    private boolean mIsPrivate;
    private double mLatitude;
    private double mLongitude;
    private String mMimeType;
    private String mName;
    private int mRemoteDeviceId;
    private String mRemoteFileId;
    private boolean mRequiresHydration;
    private SidecarMetadata mSidecarMetadata;
    private String mSource;
    private SphericalVideoMetadata mSphericalVideoMetadata;
    private int mThumbOrientation;
    private String mThumbnailFilePath;
    private MediaType mType;
    private int mWidth;

    private MediaItem(MediaType type, int internalId, String filePath, String thumbnailFilePath, String remoteFileId, int remoteDeviceId, String name, long dateAdded, int durationSecs, long fileSize, int width, int height, String mimeType, String source, boolean isDownloadable, String downloadUrl, String authHeader, double latitude, double longitude, boolean isPrivate, boolean requiresHydration, int thumbOrientation) {
        this.mType = type;
        this.mFilePath = filePath;
        this.mInternalId = internalId;
        this.mThumbnailFilePath = thumbnailFilePath;
        this.mRemoteFileId = remoteFileId;
        this.mRemoteDeviceId = remoteDeviceId;
        this.mName = name;
        this.mDateAdded = dateAdded;
        this.mDurationSecs = durationSecs;
        this.mFileSize = fileSize;
        this.mWidth = width;
        this.mHeight = height;
        this.mMimeType = mimeType;
        this.mSource = source;
        this.mIsDownloadable = isDownloadable;
        this.mDownloadUrl = downloadUrl;
        this.mAuthHeader = authHeader;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mIsPrivate = isPrivate;
        this.mFolderPath = isLocal() ? MediaProviderUtils.getFolderPath(filePath) : null;
        this.mFilenameMetadata = FilenameMetadataParser.parse((isLocal() || name == null || name.isEmpty()) ? filePath : name, type);
        this.mSidecarMetadata = (!isLocal() || !isVideo()) ? null : SidecarMetadataParser.parse(filePath);
        this.mImageHeaderMetadata = getPanoImageMetadata();
        this.mRequiresHydration = requiresHydration;
        this.mThumbOrientation = thumbOrientation;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isVideo() {
        return this.mType == MediaType.VIDEO;
    }

    public boolean isImage() {
        return this.mType == MediaType.IMAGE;
    }

    public boolean isLocal() {
        return this.mSource == null || this.mSource.equalsIgnoreCase("local");
    }

    public boolean isCamera() {
        return this.mSource != null && this.mSource.equalsIgnoreCase("camera");
    }

    public boolean isDeletable() {
        if (!isLocal()) {
            return false;
        }
        String directory = MediaProviderUtils.getExternalStorageDirectory();
        if (directory == null || directory.isEmpty()) {
            return true;
        }
        return this.mFilePath != null && !this.mFilePath.toLowerCase().startsWith(directory.toLowerCase());
    }

    public String getSource() {
        return this.mSource != null ? this.mSource : "local";
    }

    public MediaType getType() {
        return this.mType;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public String getExternalFilePath() {
        return this.mExternalFilePath != null ? this.mExternalFilePath : this.mFilePath;
    }

    public String getRemoteFileId() {
        return this.mRemoteFileId;
    }

    public int getRemoteDeviceId() {
        return this.mRemoteDeviceId;
    }

    public String getThumbnailFilePath() {
        return this.mThumbnailFilePath;
    }

    public String getExternalThumbnailFilePath() {
        return this.mExternalThumbnailFilePath != null ? this.mExternalThumbnailFilePath : this.mThumbnailFilePath;
    }

    public String getFolderPath() {
        return this.mFolderPath;
    }

    public long getId() {
        return (((long) Math.abs((this.mRemoteFileId != null ? this.mRemoteFileId : this.mFilePath).hashCode())) << 32) | (4294967295L & (this.mFileSize == 0 ? this.mDateAdded : this.mFileSize));
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public long getFileSize() {
        return this.mFileSize;
    }

    public long getDateAdded() {
        return this.mDateAdded;
    }

    public int getDurationSecs() {
        return this.mDurationSecs;
    }

    private boolean shouldSwapDimensions() {
        return this.mSource != null && this.mSource.equalsIgnoreCase("DROPBOX") && (getOrientation() == 90 || getOrientation() == 180);
    }

    public int getWidth() {
        return shouldSwapDimensions() ? this.mHeight : this.mWidth;
    }

    public int getHeight() {
        return shouldSwapDimensions() ? this.mWidth : this.mHeight;
    }

    public String getDisplayName() {
        if (this.mSidecarMetadata != null && this.mSidecarMetadata.getTitle() != null) {
            return this.mSidecarMetadata.getTitle();
        }
        if (this.mName != null) {
            return this.mName;
        }
        if (this.mFilenameMetadata != null) {
            return this.mFilenameMetadata.getTitle();
        }
        return "";
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public String getAuthHeader() {
        return this.mAuthHeader;
    }

    public String getFormat() {
        if (this.mSidecarMetadata != null && this.mSidecarMetadata.getFormat() != null) {
            return this.mSidecarMetadata.getFormat();
        }
        if (this.mFilenameMetadata != null && this.mFilenameMetadata.getFormat() != null) {
            return this.mFilenameMetadata.getFormat();
        }
        if (this.mSphericalVideoMetadata != null && this.mSphericalVideoMetadata.isSpherical() && this.mSphericalVideoMetadata.getFormat() != null) {
            return this.mSphericalVideoMetadata.getFormat();
        }
        if (this.mImageHeaderMetadata == null || this.mImageHeaderMetadata.getFormat() == null) {
            return "UNKNOWN";
        }
        return this.mImageHeaderMetadata.getFormat();
    }

    public String getPresentationFormat() {
        if (this.mSphericalVideoMetadata != null && this.mSphericalVideoMetadata.isSpherical()) {
            return this.mSphericalVideoMetadata.is180() ? "VR180" : "SPHERICAL";
        }
        if (this.mImageHeaderMetadata != null && this.mImageHeaderMetadata.isSpherical()) {
            return this.mImageHeaderMetadata.is180() ? "VR180" : "SPHERICAL";
        }
        if (this.mFilenameMetadata == null || this.mFilenameMetadata.getPresentationFormat() == null) {
            return "RECT";
        }
        return this.mFilenameMetadata.getPresentationFormat();
    }

    public float getFovX() {
        if (this.mFilenameMetadata == null || this.mFilenameMetadata.getFovX() == 0.0f) {
            return 0.0f;
        }
        return this.mFilenameMetadata.getFovX();
    }

    public String getVisibility() {
        return this.mIsPrivate ? "private" : "public";
    }

    public int getOrientation() {
        if (this.mImageHeaderMetadata != null) {
            return this.mImageHeaderMetadata.getOrientation();
        }
        return 0;
    }

    public boolean hasAudio() {
        if (this.mImageHeaderMetadata != null) {
            return this.mImageHeaderMetadata.hasAudio();
        }
        return false;
    }

    public String getAudioFilePath() {
        return this.mAudioFilePath;
    }

    /* access modifiers changed from: protected */
    public void setThumbnailFilePath(String thumbnailFilePath) {
        this.mThumbnailFilePath = thumbnailFilePath;
    }

    /* access modifiers changed from: protected */
    public boolean requiresHydration() {
        if (!isLocal() || !hasAudio() || this.mAudioFilePath != null) {
            return this.mRequiresHydration;
        }
        return true;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try {
            json.put("type", getType());
            json.put("id", String.valueOf(getId()));
            if (getFilePath() != null) {
                json.put("path", getExternalFilePath());
            }
            if (getRemoteFileId() != null) {
                json.put("remoteFileId", getRemoteFileId());
            }
            json.put("remoteDeviceId", getRemoteDeviceId());
            json.put("thumb", getExternalThumbnailFilePath());
            json.put("name", getDisplayName());
            json.put("mimeType", getMimeType());
            json.put("source", getSource());
            json.put("visibility", getVisibility());
            json.put("isDeletable", isDeletable());
            json.put("isDownloadable", this.mIsDownloadable);
            if (getDownloadUrl() != null) {
                json.put("downloadUrl", getDownloadUrl());
            }
            if (getAuthHeader() != null) {
                json.put("authHeader", getAuthHeader());
            }
            if (this.mDateAdded != 0) {
                json.put("date", this.mDateAdded);
            }
            if (this.mDurationSecs != 0) {
                json.put("duration", this.mDurationSecs);
            }
            if (this.mFileSize != 0) {
                json.put("size", this.mFileSize);
            }
            if (this.mHeight > 0 && this.mWidth > 0) {
                json.put("height", getHeight());
                json.put("width", getWidth());
            }
            if (this.mLatitude != 0.0d) {
                json.put("latitude", this.mLatitude);
            }
            if (this.mLongitude != 0.0d) {
                json.put("longitude", this.mLongitude);
            }
            if (getOrientation() != 0) {
                json.put("orientation", getOrientation());
            }
            json.put("format", getFormat());
            json.put("presentationFormat", getPresentationFormat());
            if (getFovX() > 0.0f) {
                json.put("fovX", (double) getFovX());
            }
            if (requiresHydration()) {
                json.put("requiresHydration", true);
            }
            if (this.mThumbOrientation != Integer.MAX_VALUE) {
                json.put("thumbOrientation", this.mThumbOrientation);
            }
            if (getAudioFilePath() == null) {
                return json;
            }
            json.put("audioPath", getAudioFilePath());
            return json;
        } catch (JSONException e) {
            Log.e(LocalMediaManager.TAG, "Could not serialize to JSON media with path : " + this.mFilePath);
            return null;
        }
    }

    public String toString() {
        return toJSON().toString();
    }

    public static class Builder {
        private String mAuthHeader;
        private long mDateAdded;
        private String mDownloadUrl;
        private int mDurationSecs;
        private String mFilePath;
        private long mFileSize;
        private int mHeight;
        private int mInternalId;
        private boolean mIsDownloadable;
        private boolean mIsPrivate;
        private double mLatitude;
        private double mLongitude;
        private String mMimeType;
        private String mName;
        private int mRemoteDeviceId;
        private String mRemoteFileId;
        private boolean mRequiresHydration;
        private String mSource;
        private int mThumbOrientation = Integer.MAX_VALUE;
        private String mThumbnailFilePath;
        private MediaType mType;
        private int mWidth;

        public static Builder fromJson(String jsonItem) {
            Builder builder = new Builder();
            try {
                JSONObject queryObject = new JSONObject(jsonItem);
                builder.mType = MediaType.parse(queryObject.getString("type"));
                builder.mName = queryObject.getString("name");
                builder.mFilePath = queryObject.optString("path");
                builder.mRemoteFileId = queryObject.optString("remoteFileId");
                builder.mDownloadUrl = queryObject.optString("downloadUrl");
                builder.mMimeType = queryObject.getString("mimeType");
                builder.mFileSize = (long) queryObject.optInt("size");
                builder.mDateAdded = queryObject.optLong("date");
                builder.mSource = queryObject.getString("source");
                builder.mRequiresHydration = queryObject.optBoolean("requiresHydration");
                builder.mThumbOrientation = queryObject.optInt("thumbOrientation", Integer.MAX_VALUE);
                return builder;
            } catch (JSONException e) {
                throw new IllegalArgumentException("Invalid item json : " + jsonItem);
            }
        }

        public Builder setType(MediaType type) {
            this.mType = type;
            return this;
        }

        public Builder setDurationSecs(int durationSecs) {
            this.mDurationSecs = durationSecs;
            return this;
        }

        public Builder setDateAdded(long dateAdded) {
            this.mDateAdded = dateAdded;
            return this;
        }

        public Builder setFileSize(long fileSize) {
            this.mFileSize = fileSize;
            return this;
        }

        public Builder setFilePath(String filePath) {
            this.mFilePath = filePath;
            return this;
        }

        public Builder setInternalId(int internalId) {
            this.mInternalId = internalId;
            return this;
        }

        public Builder setThumbnailFilePath(String filePath) {
            this.mThumbnailFilePath = filePath;
            return this;
        }

        public Builder setWidth(int width) {
            this.mWidth = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.mHeight = height;
            return this;
        }

        public Builder setMimeType(String mimeType) {
            this.mMimeType = mimeType;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.mLatitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.mLongitude = longitude;
            return this;
        }

        public Builder setIsPrivate(boolean isPrivate) {
            this.mIsPrivate = isPrivate;
            return this;
        }

        public MediaItem build() {
            if (this.mFilePath == null && this.mRemoteFileId == null) {
                Log.e(LocalMediaManager.TAG, "Either FilePath or mRemoteFileId must be supplied to build a media item.");
                return null;
            } else if (this.mMimeType == null) {
                Log.e(LocalMediaManager.TAG, "MimeType is mandatory to build a media item.");
                return null;
            } else if (this.mFileSize != 0 || this.mDateAdded != 0) {
                return new MediaItem(this.mType, this.mInternalId, this.mFilePath, this.mThumbnailFilePath, this.mRemoteFileId, this.mRemoteDeviceId, this.mName, this.mDateAdded, this.mDurationSecs, this.mFileSize, this.mWidth, this.mHeight, this.mMimeType, this.mSource, this.mIsDownloadable, this.mDownloadUrl, this.mAuthHeader, this.mLatitude, this.mLongitude, this.mIsPrivate, this.mRequiresHydration, this.mThumbOrientation);
            } else {
                Log.e(LocalMediaManager.TAG, "FileSize or DateAdded (timestamp) is mandatory to build a media item id.");
                return null;
            }
        }
    }

    public static void sort(List<MediaItem> items, MediaSort sortType, boolean ascendingOrder) {
        Comparator<MediaItem> comparator;
        switch (sortType) {
            case NAME:
                comparator = NAME_COMPARATOR;
                break;
            case DATE:
                comparator = DATE_ADDED_COMPARATOR;
                break;
            case DURATION:
                comparator = DURATION_COMPARATOR;
                break;
            case SIZE:
                comparator = FILE_SIZE_COMPARATOR;
                break;
            default:
                comparator = NAME_COMPARATOR;
                break;
        }
        if (ascendingOrder) {
            comparator = Collections.reverseOrder(comparator);
        }
        Collections.sort(items, comparator);
    }

    public void prepare(Context context, LocalMediaConfig config, MediaQuery query) {
        prepare(context, config, query, false);
    }

    public void prepare(Context context, LocalMediaConfig config, MediaQuery query, boolean complete) {
        ensureThumbnailPath(context);
        if (this.mSphericalVideoMetadata == null) {
            this.mSphericalVideoMetadata = getSphericalVideoMetadata();
        }
        if (this.mImageHeaderMetadata == null) {
            this.mImageHeaderMetadata = getPanoImageMetadata();
        }
        if (!(config == null || config.getMediaUriManager() == null)) {
            this.mExternalThumbnailFilePath = config.getMediaUriManager().getThumbnailUrl(this, query.ExternalHost).toString();
            this.mExternalFilePath = config.getMediaUriManager().getMediaUrl(this, query.ExternalHost).toString();
            this.mSource = this.mSource == null ? "PHONE" : this.mSource;
        }
        if (complete && hasAudio()) {
            this.mAudioFilePath = MediaProviderUtils.saveToCache(context, MediaProviderUtils.extractAudioFromXMPHeader(this.mFilePath), "audio_" + getId() + ".mp4");
        }
    }

    public int moveMedia(ContentResolver contentResolver, String newFilePath) {
        try {
            ContentValues values = new ContentValues();
            values.put("_data", newFilePath);
            return contentResolver.update(MediaStore.Files.getContentUri("external"), values, "_data=?", new String[]{this.mFilePath});
        } catch (Exception e) {
            return 0;
        }
    }

    public int deleteMedia(ContentResolver contentResolver) {
        try {
            return contentResolver.delete(MediaStore.Files.getContentUri("external"), "_data=?", new String[]{this.mFilePath});
        } catch (Exception e) {
            return 0;
        }
    }

    public void ensureThumbnailPath(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        if (isLocal() && getThumbnailFilePath() == null) {
            long mediaId = getMediaId(contentResolver);
            if (mediaId == 0) {
                String thumbnailPath = generateThumbnailOurself(context);
                if (thumbnailPath != null) {
                    setThumbnailFilePath(thumbnailPath);
                    return;
                }
                return;
            }
            String thumbnailPath2 = getThumbnailPath(contentResolver, mediaId);
            if (thumbnailPath2 == null) {
                generateThumbnailViaAndroid(contentResolver, mediaId);
                thumbnailPath2 = getThumbnailPath(contentResolver, mediaId);
            }
            setThumbnailFilePath(maybeHandleContentProviderPath(thumbnailPath2, mediaId, contentResolver));
        }
    }

    private String maybeHandleContentProviderPath(String path, long mediaId, ContentResolver contentResolver) {
        File baseDir;
        if (path == null || !path.startsWith("/mnt/content")) {
            return path;
        }
        if (path.contains("video/media")) {
            baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        } else if (path.contains("images/media")) {
            baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            Log.e(LocalMediaManager.TAG, "Unable to transform path " + path);
            return path;
        }
        if (baseDir == null) {
            Log.e(LocalMediaManager.TAG, "Unable to resolve dir for " + path);
            return path;
        }
        String resolvedPath = baseDir.getAbsolutePath() + File.separator + ".thumbnails" + File.separator + mediaId + ".jpg";
        File thumbnail = new File(resolvedPath);
        if (!thumbnail.exists()) {
            Log.d(LocalMediaManager.TAG, "Generate thumbnail at " + resolvedPath);
            if (!generateThumbnailViaAndroid(contentResolver, mediaId)) {
                Log.e(LocalMediaManager.TAG, "Unable to generate thumbnail for " + mediaId);
                try {
                    new FileOutputStream(thumbnail).close();
                } catch (Exception e) {
                    Log.e(LocalMediaManager.TAG, "Unable to generate dummy thumbnail for " + mediaId);
                }
            }
        }
        return resolvedPath;
    }

    private String generateThumbnailOurself(Context context) {
        return generateThumbnail(context, getFilePath(), getId(), isVideo());
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c1 A[SYNTHETIC, Splitter:B:29:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ca A[SYNTHETIC, Splitter:B:34:0x00ca] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String generateThumbnail(android.content.Context r12, java.lang.String r13, long r14, boolean r16) {
        /*
        // Method dump skipped, instructions count: 218
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaItem.generateThumbnail(android.content.Context, java.lang.String, long, boolean):java.lang.String");
    }

    private long getMediaId(ContentResolver contentResolver) {
        if (this.mInternalId != 0) {
            return (long) this.mInternalId;
        }
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(isVideo() ? MediaStore.Video.Media.EXTERNAL_CONTENT_URI : MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MEDIA_ID_PROJECTION, "_data=?", new String[]{getFilePath()}, null);
            if (cursor == null || !cursor.moveToFirst()) {
            }
            this.mInternalId = cursor.getInt(cursor.getColumnIndex("_id"));
            long j = (long) this.mInternalId;
            if (cursor == null) {
                return j;
            }
            cursor.close();
            return j;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String getThumbnailPath(ContentResolver contentResolver, long mediaId) {
        Cursor cursor;
        String string;
        Cursor cursor2 = null;
        try {
            if (isVideo()) {
                cursor = contentResolver.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, VIDEO_THUMBNAIL_PROJECTION, "video_id = " + mediaId, null, null);
            } else {
                cursor = contentResolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, IMAGE_THUMBNAIL_PROJECTION, "image_id = " + mediaId, null, null);
            }
            if (cursor2 == null || !cursor2.moveToFirst()) {
                Log.e(LocalMediaManager.TAG, "Thumbnail not found for mediaId: " + mediaId);
                if (cursor2 != null) {
                    cursor2.close();
                }
                return null;
            }
            if (isVideo()) {
                string = cursor2.getString(cursor2.getColumnIndex("_data"));
            } else {
                string = cursor2.getString(cursor2.getColumnIndex("_data"));
            }
        } finally {
            if (cursor2 != null) {
                cursor2.close();
            }
        }
    }

    private boolean generateThumbnailViaAndroid(ContentResolver contentResolver, long mediaId) {
        Bitmap thumbnail;
        if (isVideo()) {
            thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, mediaId, 1, null);
        } else {
            thumbnail = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, mediaId, 1, null);
        }
        if (thumbnail != null) {
            return true;
        }
        return false;
    }

    private ImageHeaderMetadata getPanoImageMetadata() {
        if (!isImage()) {
            return null;
        }
        if (isCamera()) {
            return ImageHeaderMetadata.builder().setFormat("EQUIRECT").setFormat("2D").build();
        }
        if (MediaProviderUtils.isFile(this.mFilePath)) {
            CachedMetadata metadata = CachedMetadataManager.getSingleton().get(this.mFilePath);
            if (metadata != null) {
                Log.d(LocalMediaCacheDatabase.TAG, "Got DB cache entry for " + this.mFilePath + " :" + metadata);
                return ImageHeaderMetadata.builder().from(metadata);
            }
            ImageHeaderMetadata imageHeaderMetadata = ImageHeaderMetadataParser.parse(this.mFilePath, this.mMimeType, this.mWidth, this.mHeight);
            if (imageHeaderMetadata == null) {
                int degrees = MediaProviderUtils.getImageOrientationDegrees(this.mFilePath, this.mMimeType);
                if (MediaProviderUtils.shouldTreatImageAsPano(this.mFilePath, this.mWidth, this.mHeight)) {
                    imageHeaderMetadata = ImageHeaderMetadata.builder().setFormat("EQUIRECTANGULAR").setFormat(this.mWidth == this.mHeight ? "3DTB" : "2D").setOrientation(degrees).build();
                } else {
                    imageHeaderMetadata = ImageHeaderMetadata.builder().setOrientation(degrees).build();
                }
            }
            if (imageHeaderMetadata != null) {
                CachedMetadata metadata2 = CachedMetadata.builder().from(imageHeaderMetadata);
                CachedMetadataManager.getSingleton().set(this.mFilePath, metadata2);
                Log.d(LocalMediaCacheDatabase.TAG, "Set DB cache entry for " + this.mFilePath + " :" + metadata2);
                return imageHeaderMetadata;
            }
        }
        return null;
    }

    private SphericalVideoMetadata getSphericalVideoMetadata() {
        if (!isVideo()) {
            return null;
        }
        if (isCamera()) {
            return SphericalVideoMetadata.builder().setProjectionType("DUALFISHEYE").setFormat("3D").build();
        }
        if (!isVideo() || !isLocal()) {
            return null;
        }
        CachedMetadata metadata = CachedMetadataManager.getSingleton().get(this.mFilePath);
        if (metadata == null) {
            SphericalVideoMetadata sphericalVideoMetadata = SphericalVideoMetadataParser.parse(this.mFilePath);
            CachedMetadata metadata2 = CachedMetadata.builder().from(sphericalVideoMetadata);
            CachedMetadataManager.getSingleton().set(this.mFilePath, metadata2);
            Log.d(LocalMediaCacheDatabase.TAG, "Set DB cache entry for " + this.mFilePath + " :" + metadata2);
            return sphericalVideoMetadata;
        }
        Log.d(LocalMediaCacheDatabase.TAG, "Got DB cache entry for " + this.mFilePath + " :" + metadata);
        return SphericalVideoMetadata.builder().from(metadata);
    }
}
