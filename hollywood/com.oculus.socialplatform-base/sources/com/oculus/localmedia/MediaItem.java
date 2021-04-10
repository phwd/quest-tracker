package com.oculus.localmedia;

import X.AnonymousClass006;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import com.facebook.acra.CrashTimeDataCollector;
import com.oculus.localmedia.database.LocalMediaContract;
import com.oculus.localmedia.metadata.CachedMetadata;
import com.oculus.localmedia.metadata.CachedMetadataManager;
import com.oculus.localmedia.metadata.FilenameMetadata;
import com.oculus.localmedia.metadata.FilenameMetadataParser;
import com.oculus.localmedia.metadata.ImageHeaderMetadata;
import com.oculus.localmedia.metadata.SidecarMetadata;
import com.oculus.localmedia.metadata.SidecarMetadataParser;
import com.oculus.localmedia.metadata.SphericalVideoMetadata;
import com.oculus.localmedia.metadata.SphericalVideoMetadataParser;
import com.oculus.vrshell.panels.AndroidPanelLayer;
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

        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            return (int) (mediaItem2.mDateAdded - mediaItem.mDateAdded);
        }
    };
    public static Comparator<MediaItem> DURATION_COMPARATOR = new Comparator<MediaItem>() {
        /* class com.oculus.localmedia.MediaItem.AnonymousClass2 */

        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            return mediaItem2.mDurationSecs - mediaItem.mDurationSecs;
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            return mediaItem2.mDurationSecs - mediaItem.mDurationSecs;
        }
    };
    public static Comparator<MediaItem> FILE_SIZE_COMPARATOR = new Comparator<MediaItem>() {
        /* class com.oculus.localmedia.MediaItem.AnonymousClass3 */

        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            return (int) (mediaItem2.mFileSize - mediaItem.mFileSize);
        }
    };
    public static final String[] IMAGE_THUMBNAIL_PROJECTION = {"_id", "image_id", "_data"};
    public static final String[] MEDIA_ID_PROJECTION = {"_id"};
    public static Comparator<MediaItem> NAME_COMPARATOR = new Comparator<MediaItem>() {
        /* class com.oculus.localmedia.MediaItem.AnonymousClass4 */

        public int compare(MediaItem mediaItem, MediaItem mediaItem2) {
            return mediaItem.getDisplayName().compareToIgnoreCase(mediaItem2.getDisplayName());
        }
    };
    public static final String[] VIDEO_THUMBNAIL_PROJECTION = {"_id", "video_id", "_data"};
    public String mAudioFilePath;
    public String mAuthHeader;
    public long mDateAdded;
    public String mDownloadUrl;
    public int mDurationSecs;
    public String mExternalFilePath;
    public String mExternalThumbnailFilePath;
    public String mFilePath;
    public long mFileSize;
    public FilenameMetadata mFilenameMetadata;
    public String mFolderPath;
    public int mHeight;
    public ImageHeaderMetadata mImageHeaderMetadata;
    public int mInternalId;
    public boolean mIsDownloadable;
    public boolean mIsPrivate;
    public double mLatitude;
    public double mLongitude;
    public String mMimeType;
    public String mName;
    public int mRemoteDeviceId;
    public String mRemoteFileId;
    public boolean mRequiresHydration;
    public SidecarMetadata mSidecarMetadata;
    public String mSource;
    public SphericalVideoMetadata mSphericalVideoMetadata;
    public int mThumbOrientation;
    public String mThumbnailFilePath;
    public MediaType mType;
    public int mWidth;

    public static class Builder {
        public String mAuthHeader;
        public long mDateAdded;
        public String mDownloadUrl;
        public int mDurationSecs;
        public String mFilePath;
        public long mFileSize;
        public int mHeight;
        public int mInternalId;
        public boolean mIsDownloadable;
        public boolean mIsPrivate;
        public double mLatitude;
        public double mLongitude;
        public String mMimeType;
        public String mName;
        public int mRemoteDeviceId;
        public String mRemoteFileId;
        public boolean mRequiresHydration;
        public String mSource;
        public int mThumbOrientation = Integer.MAX_VALUE;
        public String mThumbnailFilePath;
        public MediaType mType;
        public int mWidth;

        public static Builder from(MediaItem mediaItem) {
            Builder builder = new Builder();
            builder.mType = mediaItem.mType;
            builder.mDurationSecs = mediaItem.mDurationSecs;
            builder.mDateAdded = mediaItem.mDateAdded;
            builder.mFileSize = mediaItem.mFileSize;
            builder.mFilePath = mediaItem.mFilePath;
            builder.mInternalId = mediaItem.mInternalId;
            builder.mThumbnailFilePath = mediaItem.mThumbnailFilePath;
            builder.mRemoteFileId = mediaItem.mRemoteFileId;
            builder.mRemoteDeviceId = mediaItem.mRemoteDeviceId;
            builder.mName = mediaItem.mName;
            builder.mWidth = mediaItem.mWidth;
            builder.mHeight = mediaItem.mHeight;
            builder.mMimeType = mediaItem.mMimeType;
            builder.mSource = mediaItem.mSource;
            builder.mIsDownloadable = mediaItem.mIsDownloadable;
            builder.mDownloadUrl = mediaItem.mDownloadUrl;
            builder.mAuthHeader = mediaItem.mAuthHeader;
            builder.mLatitude = mediaItem.mLatitude;
            builder.mLongitude = mediaItem.mLongitude;
            builder.mIsPrivate = mediaItem.mIsPrivate;
            builder.mRequiresHydration = mediaItem.mRequiresHydration;
            builder.mThumbOrientation = mediaItem.mThumbOrientation;
            return builder;
        }

        public static Builder fromJson(String str) {
            Builder builder = new Builder();
            try {
                JSONObject jSONObject = new JSONObject(str);
                builder.mType = MediaType.parse(jSONObject.getString("type"));
                builder.mName = jSONObject.getString("name");
                builder.mFilePath = jSONObject.optString("path");
                builder.mRemoteFileId = jSONObject.optString("remoteFileId");
                builder.mDownloadUrl = jSONObject.optString("downloadUrl");
                builder.mMimeType = jSONObject.getString("mimeType");
                builder.mFileSize = (long) jSONObject.optInt("size");
                builder.mDateAdded = jSONObject.optLong("date");
                builder.mSource = jSONObject.getString("source");
                builder.mRequiresHydration = jSONObject.optBoolean("requiresHydration");
                builder.mThumbOrientation = jSONObject.optInt("thumbOrientation", Integer.MAX_VALUE);
                return builder;
            } catch (JSONException unused) {
                throw new IllegalArgumentException(AnonymousClass006.A07("Invalid item json : ", str));
            }
        }

        public MediaItem build() {
            String str;
            String str2;
            String str3 = this.mFilePath;
            if (str3 == null && this.mRemoteFileId == null) {
                str = LocalMediaManager.TAG;
                str2 = "Either FilePath or mRemoteFileId must be supplied to build a media item.";
            } else {
                String str4 = this.mMimeType;
                if (str4 == null) {
                    str = LocalMediaManager.TAG;
                    str2 = "MimeType is mandatory to build a media item.";
                } else {
                    long j = this.mFileSize;
                    if (j != 0 || this.mDateAdded != 0) {
                        return new MediaItem(this.mType, this.mInternalId, str3, this.mThumbnailFilePath, this.mRemoteFileId, this.mRemoteDeviceId, this.mName, this.mDateAdded, this.mDurationSecs, j, this.mWidth, this.mHeight, str4, this.mSource, this.mIsDownloadable, this.mDownloadUrl, this.mAuthHeader, this.mLatitude, this.mLongitude, this.mIsPrivate, this.mRequiresHydration, this.mThumbOrientation);
                    }
                    str = LocalMediaManager.TAG;
                    str2 = "FileSize or DateAdded (timestamp) is mandatory to build a media item id.";
                }
            }
            Log.e(str, str2);
            return null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
            if (r3.isEmpty() != false) goto L_0x000b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.oculus.localmedia.MediaItem.Builder setDownloadUrl(java.lang.String r3) {
            /*
                r2 = this;
                r2.mDownloadUrl = r3
                if (r3 == 0) goto L_0x000b
                boolean r1 = r3.isEmpty()
                r0 = 1
                if (r1 == 0) goto L_0x000c
            L_0x000b:
                r0 = 0
            L_0x000c:
                r2.mIsDownloadable = r0
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaItem.Builder.setDownloadUrl(java.lang.String):com.oculus.localmedia.MediaItem$Builder");
        }

        public Builder setAuthHeader(String str) {
            this.mAuthHeader = str;
            return this;
        }

        public Builder setDateAdded(long j) {
            this.mDateAdded = j;
            return this;
        }

        public Builder setDurationSecs(int i) {
            this.mDurationSecs = i;
            return this;
        }

        public Builder setFilePath(String str) {
            this.mFilePath = str;
            return this;
        }

        public Builder setFileSize(long j) {
            this.mFileSize = j;
            return this;
        }

        public Builder setHeight(int i) {
            this.mHeight = i;
            return this;
        }

        public Builder setInternalId(int i) {
            this.mInternalId = i;
            return this;
        }

        public Builder setIsDownloadable(boolean z) {
            this.mIsDownloadable = z;
            return this;
        }

        public Builder setIsPrivate(boolean z) {
            this.mIsPrivate = z;
            return this;
        }

        public Builder setLatitude(double d) {
            this.mLatitude = d;
            return this;
        }

        public Builder setLongitude(double d) {
            this.mLongitude = d;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mMimeType = str;
            return this;
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setRemoteDeviceId(int i) {
            this.mRemoteDeviceId = i;
            return this;
        }

        public Builder setRemoteFileId(String str) {
            this.mRemoteFileId = str;
            return this;
        }

        public Builder setRequiresHydration(boolean z) {
            this.mRequiresHydration = z;
            return this;
        }

        public Builder setSource(String str) {
            this.mSource = str;
            return this;
        }

        public Builder setThumbOrientation(int i) {
            this.mThumbOrientation = i;
            return this;
        }

        public Builder setThumbnailFilePath(String str) {
            this.mThumbnailFilePath = str;
            return this;
        }

        public Builder setType(MediaType mediaType) {
            this.mType = mediaType;
            return this;
        }

        public Builder setWidth(int i) {
            this.mWidth = i;
            return this;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        android.util.Log.e(com.oculus.localmedia.LocalMediaManager.TAG, X.AnonymousClass006.A07("Error when writing jpg thumbnail file for: ", r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0088, code lost:
        if (r2 != null) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008d, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008f, code lost:
        r5 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x007d */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0094 A[SYNTHETIC, Splitter:B:33:0x0094] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String generateThumbnail(android.content.Context r6, java.lang.String r7, long r8, boolean r10) {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaItem.generateThumbnail(android.content.Context, java.lang.String, long, boolean):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getThumbnailPath(android.content.ContentResolver r8, long r9) {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaItem.getThumbnailPath(android.content.ContentResolver, long):java.lang.String");
    }

    public int deleteMedia(ContentResolver contentResolver) {
        try {
            return contentResolver.delete(MediaStore.Files.getContentUri("external"), "_data=?", new String[]{this.mFilePath});
        } catch (Exception unused) {
            return 0;
        }
    }

    public int moveMedia(ContentResolver contentResolver, String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", str);
            return contentResolver.update(MediaStore.Files.getContentUri("external"), contentValues, "_data=?", new String[]{this.mFilePath});
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: com.oculus.localmedia.MediaItem$5  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$localmedia$MediaSort;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.localmedia.MediaSort[] r0 = com.oculus.localmedia.MediaSort.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.localmedia.MediaItem.AnonymousClass5.$SwitchMap$com$oculus$localmedia$MediaSort = r2
                com.oculus.localmedia.MediaSort r0 = com.oculus.localmedia.MediaSort.NAME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.localmedia.MediaSort r0 = com.oculus.localmedia.MediaSort.DATE     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.localmedia.MediaSort r0 = com.oculus.localmedia.MediaSort.DURATION     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.localmedia.MediaSort r0 = com.oculus.localmedia.MediaSort.SIZE     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaItem.AnonymousClass5.<clinit>():void");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private String generateThumbnailOurself(Context context) {
        return generateThumbnail(context, this.mFilePath, getId(), isVideo());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: android.content.ContentResolver */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String, android.database.Cursor] */
    private long getMediaId(ContentResolver contentResolver) {
        Uri uri;
        long j;
        int i = this.mInternalId;
        if (i != 0) {
            return (long) i;
        }
        Cursor cursor = 0;
        try {
            String[] strArr = {this.mFilePath};
            if (isVideo()) {
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else {
                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            }
            cursor = contentResolver.query(uri, MEDIA_ID_PROJECTION, "_data=?", strArr, cursor);
            if (cursor == null || !cursor.moveToFirst()) {
                j = 0;
                if (cursor == null) {
                    return 0;
                }
            } else {
                int i2 = cursor.getInt(cursor.getColumnIndex("_id"));
                this.mInternalId = i2;
                j = (long) i2;
            }
            return j;
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    private String maybeHandleContentProviderPath(String str, long j, ContentResolver contentResolver) {
        String str2;
        String str3;
        String str4;
        if (str != null && str.startsWith("/mnt/content")) {
            if (str.contains("video/media")) {
                str4 = Environment.DIRECTORY_MOVIES;
            } else if (str.contains("images/media")) {
                str4 = Environment.DIRECTORY_PICTURES;
            } else {
                str2 = LocalMediaManager.TAG;
                str3 = "Unable to transform path ";
                Log.e(str2, AnonymousClass006.A07(str3, str));
            }
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(str4);
            if (externalStoragePublicDirectory == null) {
                str2 = LocalMediaManager.TAG;
                str3 = "Unable to resolve dir for ";
                Log.e(str2, AnonymousClass006.A07(str3, str));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(externalStoragePublicDirectory.getAbsolutePath());
                String str5 = File.separator;
                sb.append(str5);
                sb.append(".thumbnails");
                sb.append(str5);
                sb.append(j);
                sb.append(".jpg");
                str = sb.toString();
                File file = new File(str);
                if (!file.exists() && !generateThumbnailViaAndroid(contentResolver, j)) {
                    String str6 = LocalMediaManager.TAG;
                    Log.e(str6, AnonymousClass006.A06("Unable to generate thumbnail for ", j));
                    try {
                        new FileOutputStream(file).close();
                        return str;
                    } catch (Exception unused) {
                        Log.e(str6, AnonymousClass006.A06("Unable to generate dummy thumbnail for ", j));
                        return str;
                    }
                }
            }
        }
        return str;
    }

    private boolean shouldSwapDimensions() {
        String str = this.mSource;
        if (str == null || !str.equalsIgnoreCase("DROPBOX")) {
            return false;
        }
        int orientation = getOrientation();
        if (orientation == 90 || orientation == 180) {
            return true;
        }
        return false;
    }

    public String getDisplayName() {
        String str;
        SidecarMetadata sidecarMetadata = this.mSidecarMetadata;
        if ((sidecarMetadata != null && (str = sidecarMetadata.mTitle) != null) || (str = this.mName) != null) {
            return str;
        }
        FilenameMetadata filenameMetadata = this.mFilenameMetadata;
        if (filenameMetadata != null) {
            return filenameMetadata.mTitle;
        }
        return "";
    }

    public String getExternalFilePath() {
        String str = this.mExternalFilePath;
        if (str == null) {
            return this.mFilePath;
        }
        return str;
    }

    public String getExternalThumbnailFilePath() {
        String str = this.mExternalThumbnailFilePath;
        if (str == null) {
            return this.mThumbnailFilePath;
        }
        return str;
    }

    public String getFormat() {
        String str;
        String str2;
        String str3;
        String str4;
        SidecarMetadata sidecarMetadata = this.mSidecarMetadata;
        if (sidecarMetadata != null && (str4 = sidecarMetadata.mFormat) != null) {
            return str4;
        }
        FilenameMetadata filenameMetadata = this.mFilenameMetadata;
        if (filenameMetadata != null && (str3 = filenameMetadata.mFormat) != null) {
            return str3;
        }
        SphericalVideoMetadata sphericalVideoMetadata = this.mSphericalVideoMetadata;
        if (sphericalVideoMetadata != null && sphericalVideoMetadata.isSpherical() && (str2 = sphericalVideoMetadata.mFormat) != null) {
            return str2;
        }
        ImageHeaderMetadata imageHeaderMetadata = this.mImageHeaderMetadata;
        if (imageHeaderMetadata == null || (str = imageHeaderMetadata.mFormat) == null) {
            return CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        }
        return str;
    }

    public float getFovX() {
        FilenameMetadata filenameMetadata = this.mFilenameMetadata;
        if (filenameMetadata != null) {
            float f = filenameMetadata.mFovX;
            if (f != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                return f;
            }
        }
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    public long getId() {
        String str = this.mRemoteFileId;
        if (str == null) {
            str = this.mFilePath;
        }
        long j = this.mFileSize;
        if (j == 0) {
            j = this.mDateAdded;
        }
        return (j & 4294967295L) | (((long) Math.abs(str.hashCode())) << 32);
    }

    public int getOrientation() {
        ImageHeaderMetadata imageHeaderMetadata = this.mImageHeaderMetadata;
        if (imageHeaderMetadata != null) {
            return imageHeaderMetadata.mOrientationDegrees;
        }
        return 0;
    }

    public String getPresentationFormat() {
        String str;
        boolean is180;
        SphericalVideoMetadata sphericalVideoMetadata = this.mSphericalVideoMetadata;
        if (sphericalVideoMetadata == null || !sphericalVideoMetadata.isSpherical()) {
            ImageHeaderMetadata imageHeaderMetadata = this.mImageHeaderMetadata;
            if (imageHeaderMetadata == null || !imageHeaderMetadata.isSpherical()) {
                FilenameMetadata filenameMetadata = this.mFilenameMetadata;
                if (filenameMetadata == null || (str = filenameMetadata.mPresentationFormat) == null) {
                    return "RECT";
                }
                return str;
            }
            is180 = imageHeaderMetadata.is180();
        } else {
            is180 = sphericalVideoMetadata.is180();
        }
        if (!is180) {
            return "SPHERICAL";
        }
        return "VR180";
    }

    public String getSource() {
        String str = this.mSource;
        if (str == null) {
            return "local";
        }
        return str;
    }

    public String getVisibility() {
        if (this.mIsPrivate) {
            return "private";
        }
        return "public";
    }

    public boolean hasAudio() {
        ImageHeaderMetadata imageHeaderMetadata = this.mImageHeaderMetadata;
        if (imageHeaderMetadata != null) {
            return imageHeaderMetadata.mHasAudio;
        }
        return false;
    }

    public boolean isCamera() {
        String str = this.mSource;
        if (str == null || !str.equalsIgnoreCase("camera")) {
            return false;
        }
        return true;
    }

    public boolean isExternalStorage() {
        String str = this.mSource;
        if (str == null || !str.equalsIgnoreCase("external_storage")) {
            return false;
        }
        return true;
    }

    public boolean isImage() {
        if (this.mType == MediaType.IMAGE) {
            return true;
        }
        return false;
    }

    public boolean isLocal() {
        String str = this.mSource;
        if (str == null || str.equalsIgnoreCase("local")) {
            return true;
        }
        return false;
    }

    public boolean isVideo() {
        if (this.mType == MediaType.VIDEO) {
            return true;
        }
        return false;
    }

    public JSONObject toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.mType);
            jSONObject.put("id", String.valueOf(getId()));
            if (this.mFilePath != null) {
                jSONObject.put("path", getExternalFilePath());
            }
            String str = this.mRemoteFileId;
            if (str != null) {
                jSONObject.put("remoteFileId", str);
            }
            jSONObject.put("remoteDeviceId", this.mRemoteDeviceId);
            jSONObject.put("thumb", getExternalThumbnailFilePath());
            jSONObject.put("name", getDisplayName());
            jSONObject.put("mimeType", this.mMimeType);
            jSONObject.put("source", getSource());
            jSONObject.put("visibility", getVisibility());
            jSONObject.put("isDeletable", isDeletable());
            jSONObject.put("isDownloadable", this.mIsDownloadable);
            String str2 = this.mDownloadUrl;
            if (str2 != null) {
                jSONObject.put("downloadUrl", str2);
            }
            String str3 = this.mAuthHeader;
            if (str3 != null) {
                jSONObject.put("authHeader", str3);
            }
            long j = this.mDateAdded;
            if (j != 0) {
                jSONObject.put("date", j);
            }
            int i = this.mDurationSecs;
            if (i != 0) {
                jSONObject.put("duration", i);
            }
            long j2 = this.mFileSize;
            if (j2 != 0) {
                jSONObject.put("size", j2);
            }
            if (this.mHeight > 0 && this.mWidth > 0) {
                jSONObject.put("height", getHeight());
                jSONObject.put("width", getWidth());
            }
            double d = this.mLatitude;
            if (d != 0.0d) {
                jSONObject.put("latitude", d);
            }
            double d2 = this.mLongitude;
            if (d2 != 0.0d) {
                jSONObject.put("longitude", d2);
            }
            int orientation = getOrientation();
            if (orientation != 0) {
                jSONObject.put(LocalMediaContract.ExtrasTable.Columns.ORIENTATION, orientation);
            }
            jSONObject.put("format", getFormat());
            jSONObject.put("presentationFormat", getPresentationFormat());
            float fovX = getFovX();
            if (fovX > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                jSONObject.put("fovX", (double) fovX);
            }
            if (requiresHydration()) {
                jSONObject.put("requiresHydration", true);
            }
            int i2 = this.mThumbOrientation;
            if (i2 != Integer.MAX_VALUE) {
                jSONObject.put("thumbOrientation", i2);
            }
            String str4 = this.mAudioFilePath;
            if (str4 != null) {
                jSONObject.put("audioPath", str4);
            }
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(LocalMediaManager.TAG, AnonymousClass006.A07("Could not serialize to JSON media with path : ", this.mFilePath));
            return null;
        }
    }

    private boolean generateThumbnailViaAndroid(ContentResolver contentResolver, long j) {
        Bitmap thumbnail;
        if (isVideo()) {
            thumbnail = MediaStore.Video.Thumbnails.getThumbnail(contentResolver, j, 1, null);
        } else {
            thumbnail = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, j, 1, null);
        }
        if (thumbnail == null) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0076, code lost:
        if (r3 != null) goto L_0x0078;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.oculus.localmedia.metadata.ImageHeaderMetadata getPanoImageMetadata() {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.MediaItem.getPanoImageMetadata():com.oculus.localmedia.metadata.ImageHeaderMetadata");
    }

    private SphericalVideoMetadata getSphericalVideoMetadata() {
        boolean isVideo = isVideo();
        if (isVideo) {
            if (isCamera()) {
                SphericalVideoMetadata.Builder builder = new SphericalVideoMetadata.Builder();
                builder.mProjectionType = "DUALFISHEYE";
                builder.mFormat = "3D";
                return builder.build();
            } else if (isVideo && isLocal()) {
                CachedMetadata cachedMetadata = CachedMetadataManager.sCachedMetadataManager.get(this.mFilePath);
                if (cachedMetadata != null) {
                    return new SphericalVideoMetadata.Builder().from(cachedMetadata);
                }
                SphericalVideoMetadata parse = SphericalVideoMetadataParser.parse(this.mFilePath);
                CachedMetadataManager.sCachedMetadataManager.set(this.mFilePath, new CachedMetadata.Builder().from(parse));
                return parse;
            }
        }
        return null;
    }

    public static void sort(List<MediaItem> list, MediaSort mediaSort, boolean z) {
        Comparator<MediaItem> comparator;
        switch (mediaSort.ordinal()) {
            case 1:
                comparator = DATE_ADDED_COMPARATOR;
                break;
            case 2:
                comparator = DURATION_COMPARATOR;
                break;
            case 3:
                comparator = FILE_SIZE_COMPARATOR;
                break;
            default:
                comparator = NAME_COMPARATOR;
                break;
        }
        if (z) {
            comparator = Collections.reverseOrder(comparator);
        }
        Collections.sort(list, comparator);
    }

    public void ensureThumbnailPath(Context context) {
        String maybeHandleContentProviderPath;
        ContentResolver contentResolver = context.getContentResolver();
        if (isLocal() && this.mThumbnailFilePath == null) {
            long mediaId = getMediaId(contentResolver);
            if (mediaId == 0) {
                maybeHandleContentProviderPath = generateThumbnailOurself(context);
                if (maybeHandleContentProviderPath == null) {
                    return;
                }
            } else {
                String thumbnailPath = getThumbnailPath(contentResolver, mediaId);
                if (thumbnailPath == null) {
                    generateThumbnailViaAndroid(contentResolver, mediaId);
                    thumbnailPath = getThumbnailPath(contentResolver, mediaId);
                }
                maybeHandleContentProviderPath = maybeHandleContentProviderPath(thumbnailPath, mediaId, contentResolver);
            }
            this.mThumbnailFilePath = maybeHandleContentProviderPath;
        }
    }

    public String getAudioFilePath() {
        return this.mAudioFilePath;
    }

    public String getAuthHeader() {
        return this.mAuthHeader;
    }

    public long getDateAdded() {
        return this.mDateAdded;
    }

    public String getDownloadUrl() {
        return this.mDownloadUrl;
    }

    public int getDurationSecs() {
        return this.mDurationSecs;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public long getFileSize() {
        return this.mFileSize;
    }

    public String getFolderPath() {
        return this.mFolderPath;
    }

    public int getHeight() {
        if (shouldSwapDimensions()) {
            return this.mWidth;
        }
        return this.mHeight;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public int getRemoteDeviceId() {
        return this.mRemoteDeviceId;
    }

    public String getRemoteFileId() {
        return this.mRemoteFileId;
    }

    public String getThumbnailFilePath() {
        return this.mThumbnailFilePath;
    }

    public MediaType getType() {
        return this.mType;
    }

    public int getWidth() {
        if (shouldSwapDimensions()) {
            return this.mHeight;
        }
        return this.mWidth;
    }

    public boolean isDeletable() {
        if (!isLocal()) {
            return false;
        }
        String externalStorageDirectory = MediaProviderUtils.getExternalStorageDirectory();
        if (externalStorageDirectory == null || externalStorageDirectory.isEmpty()) {
            return true;
        }
        String str = this.mFilePath;
        if (str == null || str.toLowerCase().startsWith(externalStorageDirectory.toLowerCase())) {
            return false;
        }
        return true;
    }

    public boolean requiresHydration() {
        if (!isLocal() || !hasAudio() || this.mAudioFilePath != null) {
            return this.mRequiresHydration;
        }
        return true;
    }

    public String toString() {
        return toJSON().toString();
    }

    public void setThumbnailFilePath(String str) {
        this.mThumbnailFilePath = str;
    }

    public MediaItem(MediaType mediaType, int i, String str, String str2, String str3, int i2, String str4, long j, int i3, long j2, int i4, int i5, String str5, String str6, boolean z, String str7, String str8, double d, double d2, boolean z2, boolean z3, int i6) {
        String str9;
        this.mType = mediaType;
        this.mFilePath = str;
        this.mInternalId = i;
        this.mThumbnailFilePath = str2;
        this.mRemoteFileId = str3;
        this.mRemoteDeviceId = i2;
        this.mName = str4;
        this.mDateAdded = j;
        this.mDurationSecs = i3;
        this.mFileSize = j2;
        this.mWidth = i4;
        this.mHeight = i5;
        this.mMimeType = str5;
        this.mSource = str6;
        this.mIsDownloadable = z;
        this.mDownloadUrl = str7;
        this.mAuthHeader = str8;
        this.mLatitude = d;
        this.mLongitude = d2;
        this.mIsPrivate = z2;
        SidecarMetadata sidecarMetadata = null;
        if (isLocal()) {
            str9 = MediaProviderUtils.getFolderPath(str);
        } else {
            str9 = null;
        }
        this.mFolderPath = str9;
        this.mFilenameMetadata = FilenameMetadataParser.parse((isLocal() || str4 == null || str4.isEmpty()) ? str : str4, mediaType);
        if (isLocal() && isVideo()) {
            sidecarMetadata = SidecarMetadataParser.parse(str);
        }
        this.mSidecarMetadata = sidecarMetadata;
        this.mImageHeaderMetadata = getPanoImageMetadata();
        this.mRequiresHydration = z3;
        this.mThumbOrientation = i6;
    }

    public void prepare(Context context, LocalMediaConfig localMediaConfig, MediaQuery mediaQuery) {
        prepare(context, localMediaConfig, mediaQuery, false);
    }

    public void prepare(Context context, LocalMediaConfig localMediaConfig, MediaQuery mediaQuery, boolean z) {
        MediaUriManager mediaUriManager;
        ensureThumbnailPath(context);
        if (this.mSphericalVideoMetadata == null) {
            this.mSphericalVideoMetadata = getSphericalVideoMetadata();
        }
        if (this.mImageHeaderMetadata == null) {
            this.mImageHeaderMetadata = getPanoImageMetadata();
        }
        if (!(localMediaConfig == null || (mediaUriManager = localMediaConfig.mMediaUriManager) == null)) {
            this.mExternalThumbnailFilePath = mediaUriManager.getThumbnailUrl(this, mediaQuery.ExternalHost).toString();
            this.mExternalFilePath = localMediaConfig.mMediaUriManager.getMediaUrl(this, mediaQuery.ExternalHost).toString();
            String str = this.mSource;
            if (str == null) {
                str = "PHONE";
            }
            this.mSource = str;
        }
        if (z && hasAudio()) {
            byte[] extractAudioFromXMPHeader = MediaProviderUtils.extractAudioFromXMPHeader(this.mFilePath);
            StringBuilder sb = new StringBuilder("audio_");
            sb.append(getId());
            sb.append(".mp4");
            this.mAudioFilePath = MediaProviderUtils.saveToCache(context, extractAudioFromXMPHeader, sb.toString());
        }
    }
}
