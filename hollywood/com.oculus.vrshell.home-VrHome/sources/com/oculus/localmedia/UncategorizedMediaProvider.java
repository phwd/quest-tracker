package com.oculus.localmedia;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import com.facebook.common.build.config.BuildConfig;
import com.oculus.localmedia.MediaItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class UncategorizedMediaProvider {
    private static final String DOWNLOAD_PATH = (LocalMediaManager.DOWNLOAD_FOLDER + "/%");
    private final String[] FILE_PROJECTION = {"_id", "_data", "date_added", "mime_type", "_size"};
    private Context mContext = null;
    private boolean mHasChanged = false;
    private ArrayList<MediaItem> mItems = null;

    public UncategorizedMediaProvider(Context context) {
        this.mContext = context;
        this.mItems = scanMedia();
    }

    public ArrayList<MediaItem> getItems() {
        return this.mItems;
    }

    public boolean hasChanged() {
        return this.mHasChanged;
    }

    public void onChange() {
        this.mHasChanged = true;
    }

    private ArrayList<MediaItem> scanMedia() {
        long startTime = System.currentTimeMillis();
        ArrayList<MediaItem> items = addMediaItems();
        long stopTime = System.currentTimeMillis();
        Log.d(LocalMediaManager.TAG, String.format("Uncategorized media scan found %s files in %d msec.", Integer.valueOf(items.size()), Long.valueOf(stopTime - startTime)));
        return items;
    }

    private ArrayList<MediaItem> addMediaItems() {
        ArrayList<MediaItem> items = new ArrayList<>();
        Cursor cursor = this.mContext.getContentResolver().query(MediaStore.Files.getContentUri("external"), this.FILE_PROJECTION, "_data like ? and not media_type=1 and not media_type=3", new String[]{DOWNLOAD_PATH}, null);
        if (cursor == null) {
            Log.d(LocalMediaManager.TAG, "UncategorizedMediaProvider::addMediaItems failed to get cursor");
        } else {
            int internalIdColumnIndex = cursor.getColumnIndex("_id");
            int filePathColumnIndex = cursor.getColumnIndex("_data");
            int dateAddedColumnIndex = cursor.getColumnIndex("date_added");
            int mimeTypeColumnIndex = cursor.getColumnIndex("mime_type");
            int fileSizeColumnIndex = cursor.getColumnIndex("_size");
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                addMediaItem(items, cursor.getInt(internalIdColumnIndex), cursor.getString(filePathColumnIndex), cursor.getLong(dateAddedColumnIndex), cursor.getLong(fileSizeColumnIndex), cursor.getString(mimeTypeColumnIndex));
            }
            cursor.close();
        }
        return items;
    }

    private void addMediaItem(ArrayList<MediaItem> items, int internalId, String filePath, long dateAdded, long fileSize, String mimeType) {
        Iterator<MediaItem> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getFilePath().equalsIgnoreCase(filePath)) {
                return;
            }
        }
        if (!new File(filePath).isFile()) {
            Log.e(LocalMediaManager.TAG, "Skipping missing media item for : " + filePath);
            return;
        }
        MediaItem.Builder filePath2 = MediaItem.builder().setType(MediaType.UNCATEGORIZED).setInternalId(internalId).setFileSize(fileSize).setFilePath(filePath);
        if (mimeType == null) {
            mimeType = BuildConfig.VERSION_NAME;
        }
        MediaItem item = filePath2.setMimeType(mimeType).setDateAdded(dateAdded).build();
        if (item == null) {
            Log.e(LocalMediaManager.TAG, "Skipping bogus media item for : " + filePath);
        } else {
            items.add(item);
        }
    }
}
