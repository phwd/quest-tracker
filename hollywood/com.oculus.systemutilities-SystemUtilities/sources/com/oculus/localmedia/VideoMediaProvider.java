package com.oculus.localmedia;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class VideoMediaProvider {
    private static final String[] VIDEO_THUMBNAIL_PROJECTION = {"video_id", "_data"};
    private static MimeTypeHelper sMimeTypeHelper = new MimeTypeHelper();
    private final String[] VIDEO_PROJECTION = {"_id", "duration", "mime_type", "date_added", "datetaken", "_data", "width", "height", "_size"};
    private Context mContext = null;
    private FolderMediaCountRegistry mFolderMediaCountRegistry = null;
    private boolean mHasChanged = false;
    private ArrayList<MediaItem> mItems = null;
    private LocalMediaConfig mLocalMediaConfig = null;

    static {
        sMimeTypeHelper.registerMimeType("video/mp4", "mp4");
        sMimeTypeHelper.registerMimeType("video/m4v", "m4v");
        sMimeTypeHelper.registerMimeType("video/3gp", "3gp");
        sMimeTypeHelper.registerMimeType("video/3gpp", "3gpp");
        sMimeTypeHelper.registerMimeType("video/3gpp2", "3g2");
        sMimeTypeHelper.registerMimeType("video/ts", "ts");
        sMimeTypeHelper.registerMimeType("video/mp2ts", "ts");
        sMimeTypeHelper.registerMimeType("video/MP2T", "ts");
        sMimeTypeHelper.registerMimeType("video/webm", "webm");
        sMimeTypeHelper.registerMimeType("video/mkv", "mkv");
        sMimeTypeHelper.registerMimeType("video/x-matroska", "mkv");
        sMimeTypeHelper.registerMimeType("video/wmv", "wmv");
        sMimeTypeHelper.registerMimeType("video/x-ms-wmv", "wmv");
        sMimeTypeHelper.registerMimeType("video/asf", "asf");
        sMimeTypeHelper.registerMimeType("video/x-ms-asf", "asf");
        sMimeTypeHelper.registerMimeType("video/avi", "avi");
        sMimeTypeHelper.registerMimeType("video/x-msvideo", "avi");
        sMimeTypeHelper.registerMimeType("video/flv", "flv");
        sMimeTypeHelper.registerMimeType("video/x-flv", "flv");
    }

    public VideoMediaProvider(Context context, LocalMediaConfig config) {
        this.mContext = context;
        this.mLocalMediaConfig = config;
        this.mFolderMediaCountRegistry = FolderMediaCountRegistry.getSingleton();
        this.mItems = scanMedia();
        this.mFolderMediaCountRegistry.index(MediaType.VIDEO, getItems());
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
        ArrayList<MediaItem> items = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        scanMediaStore(items);
        long stopTime = System.currentTimeMillis();
        int numInternalItems = items.size();
        long internalItemsTime = stopTime - startTime;
        long startTime2 = System.currentTimeMillis();
        scanExternalStorage(items);
        long stopTime2 = System.currentTimeMillis();
        int numExternalItems = items.size() - numInternalItems;
        long externalItemsTime = stopTime2 - startTime2;
        long startTime3 = System.currentTimeMillis();
        scanHiddenFolders(items);
        long stopTime3 = System.currentTimeMillis();
        int numHiddenItems = items.size() - (numExternalItems + numInternalItems);
        Log.d(LocalMediaManager.TAG, String.format("Video media scan found %s internal videos in %d msec.", Integer.valueOf(numInternalItems), Long.valueOf(internalItemsTime)));
        Log.d(LocalMediaManager.TAG, String.format("Video media scan found %s external videos in %d msec.", Integer.valueOf(numExternalItems), Long.valueOf(externalItemsTime)));
        Log.d(LocalMediaManager.TAG, String.format("Video media scan found %s hidden videos in %d msec.", Integer.valueOf(numHiddenItems), Long.valueOf(stopTime3 - startTime3)));
        return items;
    }

    private void scanMediaStore(ArrayList<MediaItem> items) {
        HashMap<Integer, String> videoIdToThumbnailPath = new HashMap<>();
        Cursor cursor = this.mContext.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, VIDEO_THUMBNAIL_PROJECTION, null, null, null);
        if (cursor != null) {
            int internalIdColumnIndex = cursor.getColumnIndex("video_id");
            int thumbnailPathColumnIndex = cursor.getColumnIndex("_data");
            int thumbnailCount = cursor.getCount();
            for (int thumbnailIndex = 0; thumbnailIndex < thumbnailCount; thumbnailIndex++) {
                cursor.moveToPosition(thumbnailIndex);
                videoIdToThumbnailPath.put(Integer.valueOf(cursor.getInt(internalIdColumnIndex)), cursor.getString(thumbnailPathColumnIndex));
            }
        }
        Cursor cursor2 = this.mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, this.VIDEO_PROJECTION, null, null, null);
        while (cursor2.moveToNext()) {
            try {
                int internalId = cursor2.getInt(cursor2.getColumnIndex("_id"));
                int videoDurationInSeconds = cursor2.getInt(cursor2.getColumnIndex("duration")) / 1000;
                long dateAdded = cursor2.getLong(cursor2.getColumnIndex("date_added"));
                long dateTaken = cursor2.getLong(cursor2.getColumnIndex("datetaken")) / 1000;
                long fileSize = cursor2.getLong(cursor2.getColumnIndex("_size"));
                String filePath = cursor2.getString(cursor2.getColumnIndex("_data"));
                String mimeType = cursor2.getString(cursor2.getColumnIndex("mime_type"));
                int width = cursor2.getInt(cursor2.getColumnIndex("width"));
                int height = cursor2.getInt(cursor2.getColumnIndex("height"));
                String thumbnailPath = videoIdToThumbnailPath.get(Integer.valueOf(internalId));
                if (filePath.indexOf("Download/oculus_downloaded_videos") > 0) {
                    Log.d(LocalMediaManager.TAG, "Skipping downloaded movie : " + filePath);
                } else if (filePath.toLowerCase().indexOf("cinemagraph.mp4") <= 0) {
                    if (mimeType == null || mimeType.isEmpty()) {
                        if (!sMimeTypeHelper.isSupportedMediaFile(filePath)) {
                            Log.d(LocalMediaManager.TAG, "Skipping unsupported extension for file " + filePath);
                        }
                    } else if (!sMimeTypeHelper.isSupportedMimeType(mimeType)) {
                        Log.d(LocalMediaManager.TAG, "Skipping unsupported mimeType type " + mimeType + " for file " + filePath);
                    }
                    Log.d(LocalMediaManager.TAG, "Adding internal file : " + filePath);
                    addVideoMediaItem(items, internalId, filePath, thumbnailPath, dateTaken > 0 ? dateTaken : dateAdded, fileSize, videoDurationInSeconds, width, height, mimeType, false);
                }
            } catch (Exception e) {
                Log.d(LocalMediaManager.TAG, "Unexpected error while scanning media store : " + e);
                if (cursor2 != null) {
                    cursor2.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    private void scanExternalStorage(ArrayList<MediaItem> items) {
        try {
            String directory = MediaProviderUtils.getExternalStorageDirectory();
            if (directory == null || directory.isEmpty()) {
                Log.d(LocalMediaManager.TAG, "External storage directory is empty.");
                return;
            }
            Log.d(LocalMediaManager.TAG, "Scanning external folder : " + directory);
            addVideosFromPath(items, new File(directory), true);
        } catch (Exception e) {
            Log.d(LocalMediaManager.TAG, "Unexpected error while scanning external storage : " + e);
        }
    }

    private void scanHiddenFolders(ArrayList<MediaItem> items) {
        try {
            for (String path : MediaProviderUtils.getHiddenFolders(this.mContext)) {
                Log.d(LocalMediaManager.TAG, "Scanning hidden folders : " + path);
                addVideosFromPath(items, new File(path), false);
            }
        } catch (Exception e) {
            Log.d(LocalMediaManager.TAG, "Unexpected error while scanning hidden folders : " + e);
        }
    }

    private void addVideosFromPath(ArrayList<MediaItem> items, File path, boolean recursive) {
        if (!(path == null || !path.isDirectory() || path.listFiles() == null)) {
            File[] listFiles = path.listFiles();
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                File file = listFiles[i];
                if (!file.isDirectory() || !recursive) {
                    String filePath = file.getAbsoluteFile().toString();
                    if (filePath.toLowerCase().indexOf("cinemagraph.mp4") <= 0) {
                        boolean isPrivate = MediaProviderUtils.isPrivateFile(filePath);
                        if (sMimeTypeHelper.isSupportedMediaFile(filePath)) {
                            Log.d(LocalMediaManager.TAG, "Adding external file : " + filePath);
                            addVideoMediaItem(items, 0, filePath, null, file.lastModified(), file.length(), 0, 0, 0, null, isPrivate);
                        }
                    }
                } else {
                    addVideosFromPath(items, file, true);
                }
            }
        }
    }

    private void addVideoMediaItem(ArrayList<MediaItem> items, int internalId, String filePath, String thumbnailPath, long dateAdded, long fileSize, int videoDurationInSeconds, int width, int height, String mimeType, boolean isPrivate) {
        Iterator<MediaItem> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getFilePath().equalsIgnoreCase(filePath)) {
                Log.d(LocalMediaManager.TAG, "Skipping duplicate file : " + filePath);
                return;
            }
        }
        if (this.mLocalMediaConfig != null && !this.mLocalMediaConfig.isSupportedPath(filePath)) {
            return;
        }
        if (!new File(filePath).isFile()) {
            Log.e(LocalMediaManager.TAG, "Skipping missing media item for : " + filePath);
            return;
        }
        if (mimeType == null) {
            mimeType = sMimeTypeHelper.getMimeTypeFromFileExtension(filePath);
        }
        MediaItem item = MediaItem.builder().setType(MediaType.VIDEO).setInternalId(internalId).setDurationSecs(videoDurationInSeconds).setDateAdded(dateAdded).setFileSize(fileSize).setFilePath(filePath).setThumbnailFilePath(thumbnailPath).setWidth(width).setHeight(height).setMimeType(mimeType).setIsPrivate(isPrivate).build();
        if (item == null) {
            Log.e(LocalMediaManager.TAG, "Skipping bogus media item for : " + filePath);
        } else {
            items.add(item);
        }
    }
}
