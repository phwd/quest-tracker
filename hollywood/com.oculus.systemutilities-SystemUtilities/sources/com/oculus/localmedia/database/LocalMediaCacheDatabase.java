package com.oculus.localmedia.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.oculus.localmedia.MediaType;
import com.oculus.localmedia.database.LocalMediaContract;
import com.oculus.localmedia.metadata.CachedMetadata;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LocalMediaCacheDatabase {
    public static final String TAG = LocalMediaCacheDatabase.class.getSimpleName();
    private final LocalMediaSQLHelper mLocalMediaSQLHelper;

    public LocalMediaCacheDatabase(LocalMediaSQLHelper localMediaSqlHelper) {
        this.mLocalMediaSQLHelper = localMediaSqlHelper;
    }

    public Map<String, CachedMetadata> loadAllFromDatabase() {
        Map<String, CachedMetadata> values = new HashMap<>();
        try {
            ArrayList<String> pathsToDelete = new ArrayList<>();
            SQLiteDatabase database = this.mLocalMediaSQLHelper.getWritableDatabase();
            long startTime = System.currentTimeMillis();
            Cursor cursor = database.query("localmedia_metadata", new String[]{LocalMediaContract.ExtrasTable.Columns.PATH, LocalMediaContract.ExtrasTable.Columns.TYPE, LocalMediaContract.ExtrasTable.Columns.LAST_MODIFIED, LocalMediaContract.ExtrasTable.Columns.IS_360, LocalMediaContract.ExtrasTable.Columns.IS_180, LocalMediaContract.ExtrasTable.Columns.IS_3D, LocalMediaContract.ExtrasTable.Columns.IS_TB, LocalMediaContract.ExtrasTable.Columns.ORIENTATION, LocalMediaContract.ExtrasTable.Columns.HAS_AUDIO}, null, null, null, null, null);
            if (cursor != null) {
                if (!cursor.moveToFirst()) {
                    cursor.close();
                } else {
                    int pathIdx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.PATH);
                    int typeIdx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.TYPE);
                    int timestampIdx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.LAST_MODIFIED);
                    int is360Idx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_360);
                    int is180Idx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_180);
                    int is3DIdx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_3D);
                    int isTBIdx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_TB);
                    int orientationIdx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.ORIENTATION);
                    int hasAudioIdx = cursor.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.HAS_AUDIO);
                    while (!cursor.isAfterLast()) {
                        String path = cursor.getString(pathIdx);
                        long fileLastModified = getFileLastModified(path);
                        long cacheTimestamp = cursor.getLong(timestampIdx);
                        MediaType type = valueToType(cursor.getInt(typeIdx));
                        if (cacheTimestamp >= fileLastModified) {
                            boolean is360 = cursor.getInt(is360Idx) > 0;
                            boolean is180 = cursor.getInt(is180Idx) > 0;
                            boolean is3D = cursor.getInt(is3DIdx) > 0;
                            boolean isTB = cursor.getInt(isTBIdx) > 0;
                            values.put(path, CachedMetadata.builder().setType(type).setIs3D(is3D).setIs360(is360).setIs180(is180).setIsTopBotton(isTB).setOrientation(cursor.getInt(orientationIdx)).setHasAudio(cursor.getInt(hasAudioIdx) > 0).build());
                        } else {
                            pathsToDelete.add(path);
                        }
                        cursor.moveToNext();
                    }
                    cursor.close();
                    Iterator<String> it = pathsToDelete.iterator();
                    while (it.hasNext()) {
                        String path2 = it.next();
                        Log.d(TAG, "Delete from DB cache path=" + path2);
                        remove(path2);
                    }
                    Log.d(TAG, "Got " + values.size() + " from DB cache (" + (System.currentTimeMillis() - startTime) + " msec)");
                }
            }
        } catch (Throwable t) {
            Log.e(TAG, "Unexpected error when fetching cached metadata from DB : " + t.getMessage());
        }
        return values;
    }

    public void put(String path, CachedMetadata metadata) {
        SQLiteDatabase database = this.mLocalMediaSQLHelper.getWritableDatabase();
        database.beginTransaction();
        long fileLastModified = getFileLastModified(path);
        try {
            remove(path);
            if (fileLastModified < Long.MAX_VALUE) {
                ContentValues values = new ContentValues();
                values.put(LocalMediaContract.ExtrasTable.Columns.PATH, path);
                values.put(LocalMediaContract.ExtrasTable.Columns.LAST_MODIFIED, Long.valueOf(fileLastModified));
                values.put(LocalMediaContract.ExtrasTable.Columns.TYPE, Integer.valueOf(typeToValue(metadata.getType())));
                values.put(LocalMediaContract.ExtrasTable.Columns.IS_360, Boolean.valueOf(metadata.is360()));
                values.put(LocalMediaContract.ExtrasTable.Columns.IS_180, Boolean.valueOf(metadata.is180()));
                values.put(LocalMediaContract.ExtrasTable.Columns.IS_3D, Boolean.valueOf(metadata.is3D()));
                values.put(LocalMediaContract.ExtrasTable.Columns.IS_TB, Boolean.valueOf(metadata.isTopBotton()));
                values.put(LocalMediaContract.ExtrasTable.Columns.ORIENTATION, Integer.valueOf(metadata.getOrientation()));
                values.put(LocalMediaContract.ExtrasTable.Columns.HAS_AUDIO, Boolean.valueOf(metadata.hasAudio()));
                database.insert("localmedia_metadata", null, values);
                database.setTransactionSuccessful();
            }
        } finally {
            database.endTransaction();
        }
    }

    public void remove(String path) {
        this.mLocalMediaSQLHelper.getWritableDatabase().delete("localmedia_metadata", LocalMediaContract.ExtrasTable.Columns.PATH + " = ?", new String[]{path});
    }

    private int typeToValue(MediaType type) {
        if (type == MediaType.VIDEO) {
            return 1;
        }
        if (type == MediaType.IMAGE) {
            return 2;
        }
        return 0;
    }

    private MediaType valueToType(int type) throws Exception {
        if (type == 1) {
            return MediaType.VIDEO;
        }
        if (type == 2) {
            return MediaType.IMAGE;
        }
        throw new Exception("Unsupported MediaType:" + type);
    }

    private static long getFileLastModified(String filepath) {
        if (filepath != null) {
            File file = new File(filepath);
            if (file.exists() && !file.isDirectory()) {
                return file.lastModified();
            }
        }
        return Long.MAX_VALUE;
    }
}
