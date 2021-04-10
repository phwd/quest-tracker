package com.oculus.localmedia;

import X.AnonymousClass006;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
import com.oculus.localmedia.MediaItem;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ImageMediaProvider {
    public static final String[] IMAGE_THUMBNAIL_PROJECTION = {"image_id", "_data"};
    public static MimeTypeHelper sMimeTypeHelper;
    public final boolean DEBUG_OUTPUT = false;
    public final String GIF_MIMETYPE = "image/gif";
    public final String[] IMAGE_PROJECTION = {"_id", "mime_type", "date_added", "datetaken", "_data", "width", "height", "latitude", "longitude", "_size"};
    public Context mContext = null;
    public FolderMediaCountRegistry mFolderMediaCountRegistry = null;
    public boolean mHasChanged = false;
    public ArrayList<MediaItem> mItems = null;
    public LocalMediaConfig mLocalMediaConfig = null;

    public void onChange() {
        this.mHasChanged = true;
    }

    static {
        MimeTypeHelper mimeTypeHelper = new MimeTypeHelper();
        sMimeTypeHelper = mimeTypeHelper;
        mimeTypeHelper.registerMimeType(MediaProviderUtils.JPEG_MIME_TYPE, "jpg");
        sMimeTypeHelper.registerMimeType(MediaProviderUtils.JPEG_MIME_TYPE, "jpeg");
        sMimeTypeHelper.registerMimeType("image/bmp", "bmp");
        sMimeTypeHelper.registerMimeType("image/png", "png");
    }

    private void addImagesFromPath(ArrayList<MediaItem> arrayList, File file, boolean z) {
        if (!(file == null || !file.isDirectory() || file.listFiles() == null)) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (!file2.isDirectory() || !z) {
                    String obj = file2.getAbsoluteFile().toString();
                    boolean isPrivateFile = MediaProviderUtils.isPrivateFile(obj);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(obj, options);
                    int i = options.outWidth;
                    int i2 = options.outHeight;
                    if (sMimeTypeHelper.isSupportedMediaFile(obj)) {
                        addMediaItem(arrayList, 0, obj, null, file2.lastModified(), file2.length(), i, i2, sMimeTypeHelper.getMimeTypeFromFileExtension(obj), 0.0d, 0.0d, isPrivateFile);
                    }
                } else {
                    addImagesFromPath(arrayList, file2, true);
                }
            }
        }
    }

    private ArrayList<MediaItem> addMediaItems() {
        HashMap hashMap = new HashMap();
        Cursor query = this.mContext.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, IMAGE_THUMBNAIL_PROJECTION, null, null, null);
        if (query != null) {
            int columnIndex = query.getColumnIndex("image_id");
            int columnIndex2 = query.getColumnIndex("_data");
            int count = query.getCount();
            for (int i = 0; i < count; i++) {
                query.moveToPosition(i);
                hashMap.put(Integer.valueOf(query.getInt(columnIndex)), query.getString(columnIndex2));
            }
        }
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        Cursor query2 = this.mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.IMAGE_PROJECTION, null, null, null);
        if (query2 != null) {
            int columnIndex3 = query2.getColumnIndex("_id");
            int columnIndex4 = query2.getColumnIndex("mime_type");
            int columnIndex5 = query2.getColumnIndex("width");
            int columnIndex6 = query2.getColumnIndex("height");
            int columnIndex7 = query2.getColumnIndex("latitude");
            int columnIndex8 = query2.getColumnIndex("longitude");
            int columnIndex9 = query2.getColumnIndex("_data");
            int columnIndex10 = query2.getColumnIndex("date_added");
            int columnIndex11 = query2.getColumnIndex("datetaken");
            int columnIndex12 = query2.getColumnIndex("_size");
            int count2 = query2.getCount();
            for (int i2 = 0; i2 < count2; i2++) {
                query2.moveToPosition(i2);
                int i3 = query2.getInt(columnIndex3);
                int i4 = query2.getInt(columnIndex5);
                int i5 = query2.getInt(columnIndex6);
                String string = query2.getString(columnIndex4);
                String string2 = query2.getString(columnIndex9);
                long j = query2.getLong(columnIndex10);
                long j2 = query2.getLong(columnIndex11) / 1000;
                long j3 = query2.getLong(columnIndex12);
                double d = query2.getDouble(columnIndex7);
                double d2 = query2.getDouble(columnIndex8);
                String str = (String) hashMap.get(Integer.valueOf(i3));
                if (j2 > 0) {
                    j = j2;
                }
                addMediaItem(arrayList, i3, string2, str, j, j3, i4, i5, string, d, d2, false);
            }
            query2.close();
        }
        return arrayList;
    }

    private void addPrivateMediaItems(ArrayList<MediaItem> arrayList) {
        try {
            Iterator<String> it = MediaProviderUtils.getHiddenFolders(this.mContext).iterator();
            while (it.hasNext()) {
                addImagesFromPath(arrayList, new File(it.next()), false);
            }
        } catch (Exception unused) {
        }
    }

    public static boolean isSupportedMimeType(String str) {
        return sMimeTypeHelper.isSupportedMimeType(str);
    }

    public ImageMediaProvider(Context context, LocalMediaConfig localMediaConfig) {
        this.mContext = context;
        this.mLocalMediaConfig = localMediaConfig;
        this.mFolderMediaCountRegistry = FolderMediaCountRegistry.getSingleton();
        ArrayList<MediaItem> scanMedia = scanMedia();
        this.mItems = scanMedia;
        this.mFolderMediaCountRegistry.index(MediaType.IMAGE, scanMedia);
    }

    private void addExternalMediaItems(ArrayList<MediaItem> arrayList) {
        try {
            String externalStorageDirectory = MediaProviderUtils.getExternalStorageDirectory();
            if (externalStorageDirectory != null && !externalStorageDirectory.isEmpty()) {
                addImagesFromPath(arrayList, new File(externalStorageDirectory), true);
            }
        } catch (Exception unused) {
        }
    }

    private void addMediaItem(ArrayList<MediaItem> arrayList, int i, String str, String str2, long j, long j2, int i2, int i3, String str3, double d, double d2, boolean z) {
        String str4;
        String str5;
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().mFilePath.equalsIgnoreCase(str)) {
                return;
            }
        }
        LocalMediaConfig localMediaConfig = this.mLocalMediaConfig;
        if ((localMediaConfig == null || localMediaConfig.mTestMode || localMediaConfig.isSupportedPath(str)) && !"image/gif".equalsIgnoreCase(str3)) {
            if (!new File(str).isFile()) {
                str4 = LocalMediaManager.TAG;
                str5 = "Skipping missing media item for : ";
            } else {
                MediaItem.Builder builder = new MediaItem.Builder();
                builder.mType = MediaType.IMAGE;
                builder.mInternalId = i;
                builder.mFilePath = str;
                builder.mThumbnailFilePath = str2;
                builder.mDateAdded = j;
                builder.mFileSize = j2;
                builder.mWidth = i2;
                builder.mHeight = i3;
                builder.mMimeType = str3;
                builder.mIsPrivate = z;
                builder.mLatitude = d;
                builder.mLongitude = d2;
                MediaItem build = builder.build();
                if (build == null) {
                    str4 = LocalMediaManager.TAG;
                    str5 = "Skipping bogus media item for : ";
                } else {
                    arrayList.add(build);
                    return;
                }
            }
            Log.e(str4, AnonymousClass006.A07(str5, str));
        }
    }

    private ArrayList<MediaItem> scanMedia() {
        System.currentTimeMillis();
        ArrayList<MediaItem> addMediaItems = addMediaItems();
        System.currentTimeMillis();
        addMediaItems.size();
        Iterator<MediaItem> it = addMediaItems.iterator();
        while (it.hasNext()) {
            it.next();
        }
        System.currentTimeMillis();
        addPrivateMediaItems(addMediaItems);
        System.currentTimeMillis();
        addMediaItems.size();
        System.currentTimeMillis();
        addExternalMediaItems(addMediaItems);
        System.currentTimeMillis();
        addMediaItems.size();
        LocalMediaConfig localMediaConfig = this.mLocalMediaConfig;
        if (localMediaConfig != null && localMediaConfig.mTestMode) {
            addMediaItems.size();
            for (String str : localMediaConfig.mIncludeFolders) {
                addImagesFromPath(addMediaItems, new File(str), true);
            }
            addMediaItems.size();
        }
        return addMediaItems;
    }

    public ArrayList<MediaItem> getItems() {
        return this.mItems;
    }

    public boolean hasChanged() {
        return this.mHasChanged;
    }
}
