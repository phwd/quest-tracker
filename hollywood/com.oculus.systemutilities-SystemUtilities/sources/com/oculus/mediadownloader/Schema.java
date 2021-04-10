package com.oculus.mediadownloader;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class Schema {
    public static String OMD_PROVIDER_METHOD_RESULT_KEY = "result";

    public enum OmdProviderMethods {
        GET_DOWNLOADS_SIZE,
        CLEAR_ALL_DOWNLOADS
    }

    public enum FileElement {
        DASH_MANIFEST("dash.mpd"),
        MEDIA0("media0"),
        MEDIA1("media1"),
        METADATA_BLOBS("metadata_blobs");
        
        public String filename;

        private FileElement(String filename2) {
            this.filename = filename2;
        }

        public String toString() {
            return this.filename;
        }

        public String getDownloadedBytesKey() {
            return toString() + "_downloaded_bytes";
        }

        public String getDownloadedSizeKey() {
            return toString() + "_size_bytes";
        }
    }

    public enum Table {
        DOWNLOADS(Column.NAMES),
        SETTINGS(Setting.NAMES);
        
        public final String[] NAMES;

        private Table(String[] names) {
            this.NAMES = names;
        }

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum Column {
        _ID,
        DOWNLOAD_ID,
        URL,
        DASH_MANIFEST,
        QUEUE_TIME,
        STATUS,
        MEDIA0_DOWNLOADED_BYTES,
        MEDIA0_SIZE_BYTES,
        MEDIA1_DOWNLOADED_BYTES,
        MEDIA1_SIZE_BYTES,
        METADATA_TEXT,
        METADATA_BLOBS,
        ERROR_MSG;
        
        public static final String[] NAMES = new String[values().length];
        private static final Set<String> NAME_SET = new HashSet();

        static {
            Column[] values = values();
            for (Column c : values) {
                NAME_SET.add(c.toString());
                NAMES[c.ordinal()] = c.toString();
            }
        }

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum Status {
        QUEUED,
        DOWNLOADING,
        COMPLETE,
        EXPIRED,
        ERROR;

        public static Status fromString(String string) {
            try {
                return valueOf(string);
            } catch (IllegalArgumentException e) {
                Log.e("DownloadSchema", "Unknown status: " + string);
                return ERROR;
            }
        }
    }

    public static class DownloadEntry {
        public final long _id;
        public final Uri contentUri;
        public final DashManifest dashManifest;
        public final String downloadId;
        public final String errorMsg;
        public final Progress media0Progress;
        public final Progress media1Progress;
        public final MetadataBlobs metadataBlobs;
        public final JSONObject metadataJson;
        public final String metadataText;
        public final long queueTime;
        public final Status status;
        public final String url;

        public static class Progress {
            public final long downloadedBytes;
            public final long sizeBytes;

            public Progress(long downloadedBytes2, long sizeBytes2) {
                this.downloadedBytes = downloadedBytes2;
                this.sizeBytes = sizeBytes2;
            }
        }

        public DownloadEntry(Cursor cursor) {
            this._id = cursor.getLong(Column._ID.ordinal());
            this.downloadId = cursor.getString(Column.DOWNLOAD_ID.ordinal());
            this.queueTime = cursor.getLong(Column.QUEUE_TIME.ordinal());
            this.url = cursor.getString(Column.URL.ordinal());
            String dashManifestString = cursor.getString(Column.DASH_MANIFEST.ordinal());
            this.dashManifest = dashManifestString == null ? null : new DashManifest(dashManifestString);
            this.metadataBlobs = new MetadataBlobs(cursor.getString(Column.METADATA_BLOBS.ordinal()));
            this.metadataText = cursor.getString(Column.METADATA_TEXT.ordinal());
            JSONObject json = null;
            try {
                if (!TextUtils.isEmpty(this.metadataText)) {
                    json = new JSONObject(this.metadataText);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.metadataJson = json == null ? new JSONObject() : json;
            this.status = Status.fromString(cursor.getString(Column.STATUS.ordinal()));
            this.media0Progress = new Progress(cursor.getLong(Column.MEDIA0_DOWNLOADED_BYTES.ordinal()), cursor.getLong(Column.MEDIA0_SIZE_BYTES.ordinal()));
            this.media1Progress = new Progress(cursor.getLong(Column.MEDIA1_DOWNLOADED_BYTES.ordinal()), cursor.getLong(Column.MEDIA1_SIZE_BYTES.ordinal()));
            this.errorMsg = cursor.getString(Column.ERROR_MSG.ordinal());
            this.contentUri = Schema.getDownloadUri(this.downloadId);
        }

        public JSONObject toJson() {
            try {
                JSONObject entryJson = new JSONObject();
                entryJson.put(Column._ID.toString(), this._id);
                entryJson.put(Column.DOWNLOAD_ID.toString(), this.downloadId);
                entryJson.put(Column.QUEUE_TIME.toString(), this.queueTime);
                if (!TextUtils.isEmpty(this.url)) {
                    entryJson.put(Column.URL.toString(), this.url);
                }
                if (!TextUtils.isEmpty(this.dashManifest.rawXml)) {
                    entryJson.put(Column.DASH_MANIFEST.toString(), this.dashManifest.createLocalManifest(this.contentUri.toString() + '/'));
                }
                if (!TextUtils.isEmpty(this.metadataBlobs.srcJson)) {
                    entryJson.put(Column.METADATA_BLOBS.toString(), this.metadataBlobs.toString());
                }
                entryJson.put(Column.STATUS.toString(), this.status.toString());
                entryJson.put("downloaded_bytes", this.media0Progress.downloadedBytes + this.media1Progress.downloadedBytes);
                entryJson.put("size_bytes", this.media0Progress.sizeBytes + this.media1Progress.sizeBytes);
                entryJson.put("content_uri", this.contentUri.toString());
                if (TextUtils.isEmpty(this.errorMsg)) {
                    return entryJson;
                }
                entryJson.put(Column.ERROR_MSG.toString(), this.errorMsg);
                return entryJson;
            } catch (JSONException e) {
                return null;
            }
        }
    }

    public static Pair<Uri, ContentValues> insertDashDownload(String downloadId, String manifest, String metadataText, String metadataBlobs) {
        ContentValues values = new ContentValues();
        values.put(Column.DOWNLOAD_ID.toString(), downloadId);
        values.put(Column.QUEUE_TIME.toString(), Long.valueOf(System.currentTimeMillis()));
        values.put(Column.DASH_MANIFEST.toString(), manifest);
        values.put(Column.STATUS.toString(), Status.QUEUED.toString());
        if (metadataText != null) {
            values.put(Column.METADATA_TEXT.toString(), metadataText);
        }
        if (metadataBlobs != null) {
            values.put(Column.METADATA_BLOBS.toString(), metadataBlobs);
        }
        return new Pair<>(getDownloadUri(downloadId), values);
    }

    public static Uri getDownloadUri() {
        return new Uri.Builder().scheme("content").authority("com.oculus.mediadownloader.OmdProvider").appendPath(Table.DOWNLOADS.toString()).build();
    }

    public static String getDownloadId(Uri uri) {
        if (uri == null) {
            return null;
        }
        List<String> path = uri.getPathSegments();
        try {
            if (path.size() <= 1) {
                return null;
            }
            String id = path.get(1);
            Long.parseLong(id);
            return id;
        } catch (NumberFormatException e) {
            Log.e("DownloadSchema", "Invalid id: " + ((String) null));
            return null;
        }
    }

    public static Uri getDownloadUri(String downloadId) {
        return getDownloadUri().buildUpon().appendPath(downloadId).build();
    }

    public static Uri getSettingsUri() {
        return new Uri.Builder().scheme("content").authority("com.oculus.mediadownloader.OmdProvider").appendPath(Table.SETTINGS.toString()).build();
    }

    public static Uri getSettingsUri(Setting key) {
        return getSettingsUri().buildUpon().appendPath(key.toString()).build();
    }

    public enum Setting {
        IS_ACTIVE,
        IS_NETWORK_ACTIVE;
        
        static final String[] NAMES = {"key", "value"};

        public String toString() {
            return super.toString().toLowerCase();
        }
    }
}
