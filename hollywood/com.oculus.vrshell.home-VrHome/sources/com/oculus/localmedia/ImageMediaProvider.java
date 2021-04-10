package com.oculus.localmedia;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ImageMediaProvider {
    private static final String[] IMAGE_THUMBNAIL_PROJECTION = {"image_id", "_data"};
    private static MimeTypeHelper sMimeTypeHelper = new MimeTypeHelper();
    private final boolean DEBUG_OUTPUT = false;
    private final String GIF_MIMETYPE = "image/gif";
    private final String[] IMAGE_PROJECTION = {"_id", "mime_type", "date_added", "datetaken", "_data", "width", "height", "latitude", "longitude", "_size"};
    private Context mContext = null;
    private FolderMediaCountRegistry mFolderMediaCountRegistry = null;
    private boolean mHasChanged = false;
    private ArrayList<MediaItem> mItems = null;
    private LocalMediaConfig mLocalMediaConfig = null;

    static {
        sMimeTypeHelper.registerMimeType(MediaProviderUtils.JPEG_MIME_TYPE, "jpg");
        sMimeTypeHelper.registerMimeType(MediaProviderUtils.JPEG_MIME_TYPE, "jpeg");
        sMimeTypeHelper.registerMimeType("image/bmp", "bmp");
        sMimeTypeHelper.registerMimeType("image/png", "png");
    }

    public ImageMediaProvider(Context context, LocalMediaConfig config) {
        this.mContext = context;
        this.mLocalMediaConfig = config;
        this.mFolderMediaCountRegistry = FolderMediaCountRegistry.getSingleton();
        this.mItems = scanMedia();
        this.mFolderMediaCountRegistry.index(MediaType.IMAGE, getItems());
    }

    public ArrayList<MediaItem> getItems() {
        return this.mItems;
    }

    public static boolean isSupportedMimeType(String mimeType) {
        return sMimeTypeHelper.isSupportedMimeType(mimeType);
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
        int numInternalItems = items.size();
        long internalItemsTime = stopTime - startTime;
        int numPanos = 0;
        Iterator<MediaItem> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getPresentationFormat() == "SPHERICAL") {
                numPanos++;
            }
        }
        long startTime2 = System.currentTimeMillis();
        addPrivateMediaItems(items);
        long stopTime2 = System.currentTimeMillis();
        int numHiddenItems = items.size() - numInternalItems;
        long hiddenItemsTime = stopTime2 - startTime2;
        long startTime3 = System.currentTimeMillis();
        addExternalMediaItems(items);
        long stopTime3 = System.currentTimeMillis();
        int numExternalItems = items.size() - numHiddenItems;
        Log.d(LocalMediaManager.TAG, String.format("Image media scan found %s images and %s panos in %d msec.", Integer.valueOf(numInternalItems), Integer.valueOf(numPanos), Long.valueOf(internalItemsTime)));
        Log.d(LocalMediaManager.TAG, String.format("Image media scan found %s hidden images in %d msec.", Integer.valueOf(numHiddenItems), Long.valueOf(hiddenItemsTime)));
        Log.d(LocalMediaManager.TAG, String.format("Image media scan found %s external images in %d msec.", Integer.valueOf(numExternalItems), Long.valueOf(stopTime3 - startTime3)));
        if (this.mLocalMediaConfig != null && this.mLocalMediaConfig.isTestMode()) {
            int numItems = items.size();
            for (String path : this.mLocalMediaConfig.getIncludeFolders()) {
                addImagesFromPath(items, new File(path), true);
            }
            int numTestItems = items.size() - numItems;
            Log.d(LocalMediaManager.TAG, String.format("Image media scan found %s test images.", Integer.valueOf(numTestItems)));
        }
        return items;
    }

    private ArrayList<MediaItem> addMediaItems() {
        long j;
        HashMap<Integer, String> imageIdToThumbnailPath = new HashMap<>();
        Cursor cursor = this.mContext.getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, IMAGE_THUMBNAIL_PROJECTION, null, null, null);
        if (cursor != null) {
            int internalIdColumnIndex = cursor.getColumnIndex("image_id");
            int thumbnailPathColumnIndex = cursor.getColumnIndex("_data");
            int thumbnailCount = cursor.getCount();
            for (int thumbnailIndex = 0; thumbnailIndex < thumbnailCount; thumbnailIndex++) {
                cursor.moveToPosition(thumbnailIndex);
                imageIdToThumbnailPath.put(Integer.valueOf(cursor.getInt(internalIdColumnIndex)), cursor.getString(thumbnailPathColumnIndex));
            }
        }
        ArrayList<MediaItem> items = new ArrayList<>();
        Cursor cursor2 = this.mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.IMAGE_PROJECTION, null, null, null);
        if (cursor2 == null) {
            Log.d(LocalMediaManager.TAG, "ImageMediaProvider::getAllSupportedItems failed to get imageCursor");
        } else {
            int internalIdColumnIndex2 = cursor2.getColumnIndex("_id");
            int imageMimeTypeColumnIndex = cursor2.getColumnIndex("mime_type");
            int imageWidthColumnIndex = cursor2.getColumnIndex("width");
            int imageHeightColumnIndex = cursor2.getColumnIndex("height");
            int latitudeColumnIndex = cursor2.getColumnIndex("latitude");
            int longitudeColumnIndex = cursor2.getColumnIndex("longitude");
            int imageColumnIndex = cursor2.getColumnIndex("_data");
            int dateAddedColumnIndex = cursor2.getColumnIndex("date_added");
            int dateTakenColumnIndex = cursor2.getColumnIndex("datetaken");
            int sizeColumnIndex = cursor2.getColumnIndex("_size");
            int imageCount = cursor2.getCount();
            for (int imageIndex = 0; imageIndex < imageCount; imageIndex++) {
                cursor2.moveToPosition(imageIndex);
                int internalId = cursor2.getInt(internalIdColumnIndex2);
                int imageWidth = cursor2.getInt(imageWidthColumnIndex);
                int imageHeight = cursor2.getInt(imageHeightColumnIndex);
                String mimeType = cursor2.getString(imageMimeTypeColumnIndex);
                String filePath = cursor2.getString(imageColumnIndex);
                long dateAdded = cursor2.getLong(dateAddedColumnIndex);
                long dateTaken = cursor2.getLong(dateTakenColumnIndex) / 1000;
                long fileSize = cursor2.getLong(sizeColumnIndex);
                double latitude = cursor2.getDouble(latitudeColumnIndex);
                double longitude = cursor2.getDouble(longitudeColumnIndex);
                String thumbnailPath = imageIdToThumbnailPath.get(Integer.valueOf(internalId));
                if (dateTaken > 0) {
                    j = dateTaken;
                } else {
                    j = dateAdded;
                }
                addMediaItem(items, internalId, filePath, thumbnailPath, j, fileSize, imageWidth, imageHeight, mimeType, latitude, longitude, false);
            }
            cursor2.close();
        }
        return items;
    }

    private void addPrivateMediaItems(ArrayList<MediaItem> items) {
        try {
            for (String path : MediaProviderUtils.getHiddenFolders(this.mContext)) {
                Log.d(LocalMediaManager.TAG, "Scanning hidden folders : " + path);
                addImagesFromPath(items, new File(path), false);
            }
        } catch (Exception e) {
            Log.d(LocalMediaManager.TAG, "Unexpected error while scanning hidden folders : " + e);
        }
    }

    private void addExternalMediaItems(ArrayList<MediaItem> items) {
        try {
            String directory = MediaProviderUtils.getExternalStorageDirectory();
            if (directory == null || directory.isEmpty()) {
                Log.d(LocalMediaManager.TAG, "External storage directory is empty.");
                return;
            }
            Log.d(LocalMediaManager.TAG, "Scanning external folder : " + directory);
            addImagesFromPath(items, new File(directory), true);
        } catch (Exception e) {
            Log.d(LocalMediaManager.TAG, "Unexpected error while scanning external folder : " + e);
        }
    }

    private void addImagesFromPath(ArrayList<MediaItem> items, File path, boolean recursive) {
        if (!(path == null || !path.isDirectory() || path.listFiles() == null)) {
            File[] listFiles = path.listFiles();
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                File file = listFiles[i];
                if (!file.isDirectory() || !recursive) {
                    String filePath = file.getAbsoluteFile().toString();
                    boolean isPrivate = MediaProviderUtils.isPrivateFile(filePath);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, options);
                    int width = options.outWidth;
                    int height = options.outHeight;
                    if (sMimeTypeHelper.isSupportedMediaFile(filePath)) {
                        addMediaItem(items, 0, filePath, null, file.lastModified(), file.length(), width, height, sMimeTypeHelper.getMimeTypeFromFileExtension(filePath), 0.0d, 0.0d, isPrivate);
                    }
                } else {
                    addImagesFromPath(items, file, true);
                }
            }
        }
    }

    private void addMediaItem(ArrayList<MediaItem> items, int internalId, String filePath, String thumbnailPath, long dateAdded, long fileSize, int width, int height, String mimeType, double latitude, double longitude, boolean isPrivate) {
        Iterator<MediaItem> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getFilePath().equalsIgnoreCase(filePath)) {
                return;
            }
        }
        if ((this.mLocalMediaConfig != null && !this.mLocalMediaConfig.isTestMode() && !this.mLocalMediaConfig.isSupportedPath(filePath)) || "image/gif".equalsIgnoreCase(mimeType)) {
            return;
        }
        if (!new File(filePath).isFile()) {
            Log.e(LocalMediaManager.TAG, "Skipping missing media item for : " + filePath);
            return;
        }
        MediaItem item = MediaItem.builder().setType(MediaType.IMAGE).setInternalId(internalId).setFilePath(filePath).setThumbnailFilePath(thumbnailPath).setDateAdded(dateAdded).setFileSize(fileSize).setWidth(width).setHeight(height).setMimeType(mimeType).setIsPrivate(isPrivate).setLatitude(latitude).setLongitude(longitude).build();
        if (item == null) {
            Log.e(LocalMediaManager.TAG, "Skipping bogus media item for : " + filePath);
        } else {
            items.add(item);
        }
    }
}
