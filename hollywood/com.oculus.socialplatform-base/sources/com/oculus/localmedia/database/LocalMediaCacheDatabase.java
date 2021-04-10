package com.oculus.localmedia.database;

import X.AnonymousClass006;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.localmedia.MediaType;
import com.oculus.localmedia.database.LocalMediaContract;
import com.oculus.localmedia.metadata.CachedMetadata;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LocalMediaCacheDatabase {
    public static final String TAG = "LocalMediaCacheDatabase";
    public final LocalMediaSQLHelper mLocalMediaSQLHelper;

    private MediaType valueToType(int i) throws Exception {
        if (i == 1) {
            return MediaType.VIDEO;
        }
        if (i == 2) {
            return MediaType.IMAGE;
        }
        throw new Exception(AnonymousClass006.A03("Unsupported MediaType:", i));
    }

    public static long getFileLastModified(String str) {
        if (str == null) {
            return RecyclerView.FOREVER_NS;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return RecyclerView.FOREVER_NS;
        }
        return file.lastModified();
    }

    private int typeToValue(MediaType mediaType) {
        if (mediaType == MediaType.VIDEO) {
            return 1;
        }
        if (mediaType == MediaType.IMAGE) {
            return 2;
        }
        return 0;
    }

    public Map<String, CachedMetadata> loadAllFromDatabase() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        HashMap hashMap = new HashMap();
        try {
            ArrayList arrayList = new ArrayList();
            SQLiteDatabase writableDatabase = this.mLocalMediaSQLHelper.getWritableDatabase();
            System.currentTimeMillis();
            Cursor query = writableDatabase.query(LocalMediaContract.ExtrasTable.TABLE_LOCALMEDIA_METADATA, new String[]{"path", "type", LocalMediaContract.ExtrasTable.Columns.LAST_MODIFIED, LocalMediaContract.ExtrasTable.Columns.IS_360, LocalMediaContract.ExtrasTable.Columns.IS_180, LocalMediaContract.ExtrasTable.Columns.IS_3D, LocalMediaContract.ExtrasTable.Columns.IS_TB, LocalMediaContract.ExtrasTable.Columns.ORIENTATION, LocalMediaContract.ExtrasTable.Columns.HAS_AUDIO}, null, null, null, null, null);
            if (query != null) {
                if (!query.moveToFirst()) {
                    query.close();
                    return hashMap;
                }
                int columnIndex = query.getColumnIndex("path");
                int columnIndex2 = query.getColumnIndex("type");
                int columnIndex3 = query.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.LAST_MODIFIED);
                int columnIndex4 = query.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_360);
                int columnIndex5 = query.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_180);
                int columnIndex6 = query.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_3D);
                int columnIndex7 = query.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.IS_TB);
                int columnIndex8 = query.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.ORIENTATION);
                int columnIndex9 = query.getColumnIndex(LocalMediaContract.ExtrasTable.Columns.HAS_AUDIO);
                while (!query.isAfterLast()) {
                    String string = query.getString(columnIndex);
                    long fileLastModified = getFileLastModified(string);
                    long j = query.getLong(columnIndex3);
                    MediaType valueToType = valueToType(query.getInt(columnIndex2));
                    if (j >= fileLastModified) {
                        if (query.getInt(columnIndex4) > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (query.getInt(columnIndex5) > 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (query.getInt(columnIndex6) > 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (query.getInt(columnIndex7) > 0) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        int i = query.getInt(columnIndex8);
                        if (query.getInt(columnIndex9) > 0) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        CachedMetadata.Builder builder = new CachedMetadata.Builder();
                        builder.mType = valueToType;
                        builder.mIs3D = z3;
                        builder.mIs360 = z;
                        builder.mIs180 = z2;
                        builder.mIsTopBotton = z4;
                        builder.mOrientation = i;
                        builder.mHasAudio = z5;
                        hashMap.put(string, builder.build());
                    } else {
                        arrayList.add(string);
                    }
                    query.moveToNext();
                }
                query.close();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    remove((String) it.next());
                }
                System.currentTimeMillis();
                hashMap.size();
                return hashMap;
            }
        } catch (Throwable th) {
            Log.e(TAG, AnonymousClass006.A07("Unexpected error when fetching cached metadata from DB : ", th.getMessage()));
        }
        return hashMap;
    }

    public void put(String str, CachedMetadata cachedMetadata) {
        SQLiteDatabase writableDatabase = this.mLocalMediaSQLHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        long fileLastModified = getFileLastModified(str);
        try {
            remove(str);
            if (fileLastModified < RecyclerView.FOREVER_NS) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("path", str);
                contentValues.put(LocalMediaContract.ExtrasTable.Columns.LAST_MODIFIED, Long.valueOf(fileLastModified));
                contentValues.put("type", Integer.valueOf(typeToValue(cachedMetadata.mType)));
                contentValues.put(LocalMediaContract.ExtrasTable.Columns.IS_360, Boolean.valueOf(cachedMetadata.mIs360));
                contentValues.put(LocalMediaContract.ExtrasTable.Columns.IS_180, Boolean.valueOf(cachedMetadata.mIs180));
                contentValues.put(LocalMediaContract.ExtrasTable.Columns.IS_3D, Boolean.valueOf(cachedMetadata.mIs3D));
                contentValues.put(LocalMediaContract.ExtrasTable.Columns.IS_TB, Boolean.valueOf(cachedMetadata.mIsTopBotton));
                contentValues.put(LocalMediaContract.ExtrasTable.Columns.ORIENTATION, Integer.valueOf(cachedMetadata.mOrientation));
                contentValues.put(LocalMediaContract.ExtrasTable.Columns.HAS_AUDIO, Boolean.valueOf(cachedMetadata.mHasAudio));
                writableDatabase.insert(LocalMediaContract.ExtrasTable.TABLE_LOCALMEDIA_METADATA, null, contentValues);
                writableDatabase.setTransactionSuccessful();
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public void remove(String str) {
        this.mLocalMediaSQLHelper.getWritableDatabase().delete(LocalMediaContract.ExtrasTable.TABLE_LOCALMEDIA_METADATA, AnonymousClass006.A07("path", " = ?"), new String[]{str});
    }

    public LocalMediaCacheDatabase(LocalMediaSQLHelper localMediaSQLHelper) {
        this.mLocalMediaSQLHelper = localMediaSQLHelper;
    }
}
