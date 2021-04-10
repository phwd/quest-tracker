package com.oculus.mediadownloader;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.oculus.mediadownloader.DashManifest;
import com.oculus.mediadownloader.Schema;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OmdResolver {
    private static final String TAG = "OmdResolver";
    private final DownloadEntryObserver downloadEntryObserver;
    private final ContentResolver resolver;

    /* access modifiers changed from: private */
    public class DownloadEntryObserver extends ContentObserver {
        private boolean didEntryValidityChange = false;
        private final HashMap<String, Schema.DownloadEntry> entryMap = new HashMap<>();

        DownloadEntryObserver() {
            super(new Handler(Looper.getMainLooper()));
            loadEntryMap();
        }

        /* access modifiers changed from: package-private */
        public synchronized Schema.DownloadEntry getEntry(String id) {
            return this.entryMap.get(id);
        }

        /* access modifiers changed from: package-private */
        public synchronized Collection<Schema.DownloadEntry> getAllEntries() {
            return this.entryMap.values();
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean clearEntryValidityChangeFlag() {
            boolean didChange;
            didChange = this.didEntryValidityChange;
            this.didEntryValidityChange = false;
            return didChange;
        }

        /* access modifiers changed from: package-private */
        public synchronized void checkEntryValidityChange(String downloadId) {
            Schema.DownloadEntry previous = this.entryMap.get(downloadId);
            Schema.DownloadEntry current = OmdResolver.this.getDownloadEntry(downloadId);
            if (!(previous == null && current == null) && ((previous == null || current == null || previous.status != current.status) && (previous == null || current == null || previous.status == Schema.Status.ERROR || current.status == Schema.Status.ERROR || previous.status == Schema.Status.EXPIRED || current.status == Schema.Status.EXPIRED))) {
                this.didEntryValidityChange = true;
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void loadEntryMap() {
            this.entryMap.clear();
            for (Schema.DownloadEntry entry : OmdResolver.this.queryAllDownloadEntries()) {
                this.entryMap.put(entry.downloadId, entry);
            }
            this.didEntryValidityChange = true;
        }

        /* access modifiers changed from: package-private */
        public synchronized void updateEntry(String id) {
            checkEntryValidityChange(id);
            Schema.DownloadEntry entry = OmdResolver.this.getDownloadEntry(id);
            if (entry == null) {
                this.entryMap.remove(id);
            } else {
                this.entryMap.put(id, entry);
            }
        }

        public synchronized void onChange(boolean selfChange, Uri uri) {
            try {
                Uri downloadsUri = Schema.getDownloadUri();
                if (uri == null || downloadsUri.equals(uri)) {
                    loadEntryMap();
                } else {
                    String id = Schema.getDownloadId(uri);
                    if (id == null) {
                        throw new Exception("onChange received invalid uri: " + uri.getPath());
                    }
                    updateEntry(id);
                }
            } catch (Throwable t) {
                Log.e(OmdResolver.TAG, "Error onChange()", t);
            }
            return;
        }
    }

    public OmdResolver(Context context, boolean useCachedEntries) {
        this.resolver = context.getContentResolver();
        this.downloadEntryObserver = useCachedEntries ? new DownloadEntryObserver() : null;
        if (this.downloadEntryObserver != null) {
            this.resolver.registerContentObserver(Schema.getDownloadUri(), true, this.downloadEntryObserver);
        }
    }

    public OmdResolver(ContentResolver resolver2) {
        this.resolver = resolver2;
        this.downloadEntryObserver = null;
    }

    public boolean didEntryValidityChange() {
        return this.downloadEntryObserver != null && this.downloadEntryObserver.clearEntryValidityChangeFlag();
    }

    public Cursor queryDownloadCursor(String id) {
        Uri download = id == null ? Schema.getDownloadUri() : Schema.getDownloadUri(id);
        Cursor cursor = this.resolver.query(download, null, null, null, null);
        if (cursor != null) {
            return cursor;
        }
        Log.e(TAG, "Unable to get cursor for " + download);
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private List<Schema.DownloadEntry> queryAllDownloadEntries() {
        Cursor cursor = queryDownloadCursor(null);
        if (cursor == null || cursor.getCount() == 0) {
            Log.d(TAG, "No downloads founds");
            return Collections.emptyList();
        }
        List<Schema.DownloadEntry> entries = new ArrayList<>(cursor.getCount());
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            entries.add(i, new Schema.DownloadEntry(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return entries;
    }

    public String getDownloadEntryJson(String id) {
        if (TextUtils.isEmpty(id)) {
            return "null";
        }
        try {
            Schema.DownloadEntry entry = this.downloadEntryObserver == null ? getDownloadEntry(id) : this.downloadEntryObserver.getEntry(id);
            JSONObject entryJsonObj = entry == null ? null : entry.toJson();
            if (entryJsonObj == null || !id.equals(entryJsonObj.getString("download_id"))) {
                return "null";
            }
            return entryJsonObj.toString();
        } catch (JSONException e) {
            Log.e(TAG, "Error fetching download_id from Schema.DownloadEntry");
            return "null";
        }
    }

    public Schema.DownloadEntry getDownloadEntry(String id) {
        Cursor cursor = queryDownloadCursor(id);
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        Schema.DownloadEntry downloadEntry = new Schema.DownloadEntry(cursor);
        cursor.close();
        return downloadEntry;
    }

    public String getAllDownloadEntriesJson() {
        Collection<Schema.DownloadEntry> entries;
        try {
            JSONArray jsonArray = new JSONArray();
            if (this.downloadEntryObserver == null) {
                entries = queryAllDownloadEntries();
            } else {
                entries = this.downloadEntryObserver.getAllEntries();
            }
            for (Schema.DownloadEntry entry : entries) {
                jsonArray.put(entry.toJson());
            }
            return jsonArray.toString();
        } catch (Exception e) {
            return "[]";
        }
    }

    public Schema.DownloadEntry getNextDownload() {
        List<Schema.DownloadEntry> downloads = queryAllDownloadEntries();
        downloads.removeIf(new Predicate<Schema.DownloadEntry>() {
            /* class com.oculus.mediadownloader.OmdResolver.AnonymousClass1 */

            public boolean test(Schema.DownloadEntry downloadEntry) {
                return (downloadEntry.status == Schema.Status.QUEUED || downloadEntry.status == Schema.Status.DOWNLOADING) ? false : true;
            }
        });
        if (downloads.size() == 0) {
            return null;
        }
        downloads.sort(new Comparator<Schema.DownloadEntry>() {
            /* class com.oculus.mediadownloader.OmdResolver.AnonymousClass2 */

            public int compare(Schema.DownloadEntry left, Schema.DownloadEntry right) {
                return new Long(left.queueTime - right.queueTime).intValue();
            }
        });
        return downloads.get(0);
    }

    public void markError(Uri contentUri, String error) {
        ContentValues values = new ContentValues();
        values.put(Schema.Column.STATUS.toString(), Schema.Status.ERROR.toString());
        values.put(Schema.Column.ERROR_MSG.toString(), error);
        this.resolver.update(contentUri, values, null, null);
    }

    public void noteDownloadStart(Uri contentUri, Schema.FileElement element, long size) {
        ContentValues values = new ContentValues();
        values.put(Schema.Column.STATUS.toString(), Schema.Status.DOWNLOADING.toString());
        values.put(element.getDownloadedBytesKey(), (Integer) 0);
        values.put(element.getDownloadedSizeKey(), Long.valueOf(size));
        this.resolver.update(contentUri, values, null, null);
    }

    public int updateBytesDownloaded(Uri contentUri, Schema.FileElement element, long bytesRead) {
        ContentValues values = new ContentValues();
        values.put(element.getDownloadedBytesKey(), Long.valueOf(bytesRead));
        return this.resolver.update(contentUri, values, null, null);
    }

    public void updateMetadataBlobs(Uri contentUri, String metadataBlobs) {
        ContentValues values = new ContentValues();
        values.put(Schema.Column.METADATA_BLOBS.toString(), metadataBlobs);
        this.resolver.update(contentUri, values, null, null);
    }

    public void noteDownloadCompleted(Uri contentUri, Schema.FileElement element, long bytesRead) {
        ContentValues values = new ContentValues();
        values.put(Schema.Column.STATUS.toString(), Schema.Status.COMPLETE.toString());
        values.put(element.getDownloadedBytesKey(), Long.valueOf(bytesRead));
        this.resolver.update(contentUri, values, null, null);
    }

    public void insertDash(String id, String dashManifest, String metadataJson) {
        String str;
        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(dashManifest)) {
            StringBuilder append = new StringBuilder().append("insertDash: ");
            if (TextUtils.isEmpty(id)) {
                str = "Invalid ID";
            } else {
                str = "Invalid DASH Manifest";
            }
            Log.e(TAG, append.append(str).toString());
            return;
        }
        if (TextUtils.isEmpty(metadataJson)) {
            metadataJson = "{}";
        }
        Pair<Uri, ContentValues> command = Schema.insertDashDownload(id, dashManifest, metadataJson, "[]");
        this.resolver.insert((Uri) command.first, (ContentValues) command.second);
        if (this.downloadEntryObserver != null) {
            this.downloadEntryObserver.updateEntry(id);
        }
    }

    public void deleteDownload(String id) {
        deleteDownload(Schema.getDownloadUri(id));
    }

    public void deleteDownload(Uri uri) {
        this.resolver.delete(uri, null, null);
        if (this.downloadEntryObserver != null) {
            this.downloadEntryObserver.updateEntry(Schema.getDownloadId(uri));
        }
    }

    public void enableDownload(Context context, boolean enable) {
        setSetting(Schema.Setting.IS_ACTIVE, Boolean.toString(enable));
        setSetting(Schema.Setting.IS_NETWORK_ACTIVE, Boolean.toString(enable));
    }

    public long getDownloadsSize() {
        try {
            return this.resolver.call(Schema.getDownloadUri(), Schema.OmdProviderMethods.GET_DOWNLOADS_SIZE.toString(), (String) null, (Bundle) null).getLong(Schema.OMD_PROVIDER_METHOD_RESULT_KEY, -1);
        } catch (Throwable t) {
            Log.e(TAG, "Unable to get total download size", t);
            return -1;
        }
    }

    public boolean deleteAllDownloads() {
        try {
            Bundle result = this.resolver.call(Schema.getDownloadUri(), Schema.OmdProviderMethods.CLEAR_ALL_DOWNLOADS.toString(), (String) null, (Bundle) null);
            if (this.downloadEntryObserver != null) {
                this.downloadEntryObserver.loadEntryMap();
            }
            return result.getBoolean(Schema.OMD_PROVIDER_METHOD_RESULT_KEY, false);
        } catch (Throwable t) {
            Log.e(TAG, "Error on deleting all downloads", t);
            return false;
        }
    }

    public void setSetting(Schema.Setting key, String value) {
        ContentValues values = new ContentValues();
        values.put("value", value);
        this.resolver.update(Schema.getSettingsUri(key), values, null, null);
    }

    public String getSetting(Schema.Setting key) {
        Cursor cursor = this.resolver.query(Schema.getSettingsUri(key), Schema.Setting.NAMES, null, null, null);
        if (cursor == null) {
            Log.e(TAG, "Unable to get cursor for " + key);
            return "";
        } else if (cursor.getCount() == 0) {
            Log.e(TAG, "No settings matching " + key);
            return "";
        } else {
            cursor.moveToFirst();
            String string = cursor.getString(1);
            cursor.close();
            return string;
        }
    }

    public void registerSettingsObserver(ContentObserver observer) {
        this.resolver.registerContentObserver(Schema.getSettingsUri(), true, observer);
    }

    public void shutdown() {
        if (this.downloadEntryObserver != null) {
            this.resolver.unregisterContentObserver(this.downloadEntryObserver);
        }
    }

    public static long estimateFileSizeFromDash(String dash) {
        try {
            DashManifest.LocalManifest localManifest = new DashManifest(dash).createLocalManifest("n/a");
            if (localManifest == null) {
                return 0;
            }
            return localManifest.audioBytesFromDash + localManifest.videoBytesFromDash;
        } catch (Exception e) {
            return 0;
        }
    }
}
