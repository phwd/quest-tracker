package com.oculus.localmedia;

import X.AnonymousClass006;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import com.oculus.localmedia.MediaItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class UncategorizedMediaProvider {
    public static final String DOWNLOAD_PATH = AnonymousClass006.A07(LocalMediaManager.DOWNLOAD_FOLDER, "/%");
    public final String[] FILE_PROJECTION = {"_id", "_data", "date_added", "mime_type", "_size"};
    public Context mContext = null;
    public boolean mHasChanged = false;
    public ArrayList<MediaItem> mItems = null;

    public void onChange() {
        this.mHasChanged = true;
    }

    private ArrayList<MediaItem> addMediaItems() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor query = this.mContext.getContentResolver().query(MediaStore.Files.getContentUri("external"), this.FILE_PROJECTION, "_data like ? and not media_type=1 and not media_type=3", new String[]{DOWNLOAD_PATH}, null);
        if (query != null) {
            int columnIndex = query.getColumnIndex("_id");
            int columnIndex2 = query.getColumnIndex("_data");
            int columnIndex3 = query.getColumnIndex("date_added");
            int columnIndex4 = query.getColumnIndex("mime_type");
            int columnIndex5 = query.getColumnIndex("_size");
            for (int i = 0; i < query.getCount(); i++) {
                query.moveToPosition(i);
                addMediaItem(arrayList, query.getInt(columnIndex), query.getString(columnIndex2), query.getLong(columnIndex3), query.getLong(columnIndex5), query.getString(columnIndex4));
            }
            query.close();
        }
        return arrayList;
    }

    public UncategorizedMediaProvider(Context context) {
        this.mContext = context;
        this.mItems = scanMedia();
    }

    private void addMediaItem(ArrayList<MediaItem> arrayList, int i, String str, long j, long j2, String str2) {
        String str3;
        String str4;
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().mFilePath.equalsIgnoreCase(str)) {
                return;
            }
        }
        if (!new File(str).isFile()) {
            str3 = LocalMediaManager.TAG;
            str4 = "Skipping missing media item for : ";
        } else {
            MediaItem.Builder builder = new MediaItem.Builder();
            builder.mType = MediaType.UNCATEGORIZED;
            builder.mInternalId = i;
            builder.mFileSize = j2;
            builder.mFilePath = str;
            if (str2 == null) {
                str2 = "unknown";
            }
            builder.mMimeType = str2;
            builder.mDateAdded = j;
            MediaItem build = builder.build();
            if (build == null) {
                str3 = LocalMediaManager.TAG;
                str4 = "Skipping bogus media item for : ";
            } else {
                arrayList.add(build);
                return;
            }
        }
        Log.e(str3, AnonymousClass006.A07(str4, str));
    }

    private ArrayList<MediaItem> scanMedia() {
        System.currentTimeMillis();
        ArrayList<MediaItem> addMediaItems = addMediaItems();
        System.currentTimeMillis();
        addMediaItems.size();
        return addMediaItems;
    }

    public ArrayList<MediaItem> getItems() {
        return this.mItems;
    }

    public boolean hasChanged() {
        return this.mHasChanged;
    }
}
