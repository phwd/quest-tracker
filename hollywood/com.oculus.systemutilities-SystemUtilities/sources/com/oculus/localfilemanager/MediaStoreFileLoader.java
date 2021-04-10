package com.oculus.localfilemanager;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import com.facebook.debug.log.BLog;
import com.oculus.localfilemanager.FileModel;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class MediaStoreFileLoader {
    private static final String DOWNLOAD_FILTER_QUERY = ("_data LIKE '%" + Environment.DIRECTORY_DOWNLOADS + "%' ");
    private static final String[] METADATA_PROJECTIONS = {"_data", "media_type", "_size", "height", "width", "date_added", "mime_type"};
    private static final String TAG = MediaStoreFileLoader.class.getSimpleName();

    public enum FileCategoryFilter {
        ALL,
        DOWNLOADS,
        IMAGES,
        VIDEOS,
        MEDIA
    }

    public enum FileOrderingFilter {
        LARGEST,
        SMALLEST,
        MOST_RECENT,
        OLDEST
    }

    /* access modifiers changed from: package-private */
    public enum MediaStoreFileLoaderRequestType {
        FILES_AND_FOLDERS_IN_FOLDER,
        FILES_IN_MEDIA_STORE
    }

    private static boolean isKnownFileType(String filePath, MimeTypeMap mimeTypeMap) {
        if (filePath.contains(".")) {
            return mimeTypeMap.hasExtension(filePath.substring(filePath.lastIndexOf(46) + 1));
        }
        return false;
    }

    public static class MediaStoreFileLoaderRequest {
        public final FileCategoryFilter fileCategoryFilter;
        public final FileOrderingFilter fileOrderingFilter;
        public final String folder;
        public final int limit;
        public final boolean loadedAll;
        public final int loadedFilesCount;
        public final int offset;
        final MediaStoreFileLoaderRequestType type;

        private MediaStoreFileLoaderRequest(int offset2, int limit2, FileOrderingFilter fileOrderingFilter2, FileCategoryFilter fileCategoryFilter2, boolean loadedAll2, int loadedFilesCount2, String folder2, MediaStoreFileLoaderRequestType type2) {
            this.offset = offset2;
            this.limit = limit2;
            this.fileOrderingFilter = fileOrderingFilter2;
            this.fileCategoryFilter = fileCategoryFilter2;
            this.loadedAll = loadedAll2;
            this.loadedFilesCount = loadedFilesCount2;
            this.folder = folder2;
            this.type = type2;
        }

        public static MediaStoreFileLoaderRequest create(int limit2, FileOrderingFilter fileOrderingFilter2, FileCategoryFilter fileCategoryFilter2) {
            return new MediaStoreFileLoaderRequest(0, limit2, fileOrderingFilter2, fileCategoryFilter2, false, 0, null, MediaStoreFileLoaderRequestType.FILES_IN_MEDIA_STORE);
        }

        public static MediaStoreFileLoaderRequest create(String folder2, FileOrderingFilter fileOrderingFilter2) {
            return new MediaStoreFileLoaderRequest(0, 0, fileOrderingFilter2, FileCategoryFilter.ALL, false, 0, folder2, MediaStoreFileLoaderRequestType.FILES_AND_FOLDERS_IN_FOLDER);
        }
    }

    /* access modifiers changed from: package-private */
    public static class HiddenFileFilter implements FileFilter {
        HiddenFileFilter() {
        }

        public boolean accept(File file) {
            return !file.isHidden();
        }
    }

    public static class MediaStoreFileLoaderResult {
        public final ArrayList<FileModel.FileData> files;
        public final MediaStoreFileLoaderRequest nextMediaStoreFileLoaderRequest;

        public MediaStoreFileLoaderResult(ArrayList<FileModel.FileData> files2, MediaStoreFileLoaderRequest nextMediaStoreFileLoaderRequest2) {
            this.files = files2;
            this.nextMediaStoreFileLoaderRequest = nextMediaStoreFileLoaderRequest2;
        }
    }

    /* access modifiers changed from: package-private */
    public static class CursorIndices {
        public final int dateAdded;
        public final int height;
        public final int mediaType;
        public final int mimeType;
        public final int path;
        public final int size;
        public final int width;

        CursorIndices(Cursor cursor) {
            this.path = cursor.getColumnIndex("_data");
            this.size = cursor.getColumnIndex("_size");
            this.height = cursor.getColumnIndex("height");
            this.width = cursor.getColumnIndex("width");
            this.dateAdded = cursor.getColumnIndex("date_added");
            this.mimeType = cursor.getColumnIndex("mime_type");
            this.mediaType = cursor.getColumnIndex("media_type");
        }
    }

    static FileModel.FileType getFileType(int mediaType, MimeTypeMap mimeTypeMap, String filePath) {
        if (mediaType == 3) {
            return FileModel.FileType.VIDEO;
        }
        if (mediaType == 1) {
            return FileModel.FileType.IMAGE;
        }
        return isKnownFileType(filePath, mimeTypeMap) ? FileModel.FileType.KNOWN : FileModel.FileType.UNKNOWN;
    }

    private static String getFileFilterQueryFromCategory(FileCategoryFilter fileCategoryFilter) {
        switch (fileCategoryFilter) {
            case ALL:
                return "_data NOT LIKE '%/Oculus/Avatars%' AND _data NOT LIKE '%/Android/data%' AND _data NOT LIKE '%/Android/obb%' AND _data NOT LIKE '%/DCIM/.thumbnails%'";
            case DOWNLOADS:
                return DOWNLOAD_FILTER_QUERY;
            case IMAGES:
                return "media_type = 1";
            case VIDEOS:
                return "media_type = 3";
            case MEDIA:
                return "media_type = 1 OR media_type = 3";
            default:
                return null;
        }
    }

    private static String getFileSortingQueryFromOrdering(FileOrderingFilter fileOrderingFilter) {
        switch (fileOrderingFilter) {
            case MOST_RECENT:
                return "date_added DESC";
            case OLDEST:
                return "date_added ASC";
            case LARGEST:
                return "_size DESC";
            case SMALLEST:
                return "_size ASC";
            default:
                return null;
        }
    }

    private static Uri getContentUriWithPagination(Uri contentUri, int offset, int limit) {
        return contentUri.buildUpon().appendQueryParameter("limit", String.valueOf(offset) + "," + String.valueOf(limit)).build();
    }

    /* access modifiers changed from: package-private */
    public FileModel.FileData getFileData(File file, boolean allowFolder, Cursor cursor, CursorIndices cursorIndices, int index, MimeTypeMap mimeTypeMap, MediaMetadataRetriever mediaMetadataRetriever) {
        if (file == null || !file.exists()) {
            return null;
        }
        boolean isFolder = file.isDirectory();
        if (isFolder && !allowFolder) {
            return null;
        }
        try {
            String filePath = file.getCanonicalPath();
            int dateAdded = cursor.getInt(cursorIndices.dateAdded);
            if (isFolder) {
                int itemCount = 0;
                File[] filesInFolder = file.listFiles(new HiddenFileFilter());
                if (filesInFolder != null) {
                    itemCount = filesInFolder.length;
                }
                return FileModel.FileData.createFolder(filePath, dateAdded, new FileModel.FolderMetadata(itemCount), index);
            }
            long size = cursor.getLong(cursorIndices.size);
            int height = cursor.getInt(cursorIndices.height);
            int width = cursor.getInt(cursorIndices.width);
            FileModel.FileType type = getFileType(cursor.getInt(cursorIndices.mediaType), mimeTypeMap, filePath);
            String mimeType = cursor.getString(cursorIndices.mimeType);
            long durationInMs = 0;
            if (type == FileModel.FileType.VIDEO) {
                try {
                    mediaMetadataRetriever.setDataSource(filePath);
                    String duration = mediaMetadataRetriever.extractMetadata(9);
                    if (duration != null) {
                        durationInMs = Long.parseLong(duration);
                    }
                } catch (IllegalArgumentException e) {
                    BLog.e(TAG, "Unable to retrieve the video metadata", e);
                }
            }
            return FileModel.FileData.createFile(filePath, dateAdded, new FileModel.FileMetadata(size, width, height, type, mimeType, durationInMs), index);
        } catch (IOException e2) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Cursor getFilesCursor(Context context, FileCategoryFilter fileCategoryFilter, FileOrderingFilter fileOrderingFilter, int offset, int limit, Uri contentUri) {
        return context.getContentResolver().query(getContentUriWithPagination(contentUri, offset, limit), METADATA_PROJECTIONS, getFileFilterQueryFromCategory(fileCategoryFilter), null, getFileSortingQueryFromOrdering(fileOrderingFilter));
    }

    /* access modifiers changed from: package-private */
    public Cursor getFilesCursor(Context context, File externalStorageDirectory, String folder, FileOrderingFilter fileOrderingFilter, Uri contentUri) {
        try {
            StringBuilder sb = new StringBuilder();
            if (folder == null || folder.equals("")) {
                folder = externalStorageDirectory.getCanonicalPath();
            }
            String folderPath = sb.append(folder).append("/").toString();
            return context.getContentResolver().query(contentUri, METADATA_PROJECTIONS, "_data LIKE '" + folderPath + "%' AND " + "_data" + " NOT LIKE '" + folderPath + "%/%'", null, getFileSortingQueryFromOrdering(fileOrderingFilter));
        } catch (IOException e) {
            BLog.e(TAG, "couldn't get external storage canonical path", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<FileModel.FileData> getFiles(Cursor cursor, Uri contentUri, MimeTypeMap mimeTypeMap, int filesCount, boolean allowFolder, MediaMetadataRetriever mediaMetadataRetriever) {
        FileModel.FileData fileData;
        CursorIndices cursorIndices = new CursorIndices(cursor);
        ArrayList<FileModel.FileData> files = new ArrayList<>();
        while (cursor.moveToNext()) {
            String filePath = cursor.getString(cursorIndices.path);
            if (!(filePath == null || (fileData = getFileData(new File(filePath), allowFolder, cursor, cursorIndices, filesCount + files.size(), mimeTypeMap, mediaMetadataRetriever)) == null)) {
                files.add(fileData);
            }
        }
        cursor.close();
        return files;
    }

    /* access modifiers changed from: package-private */
    public MediaStoreFileLoaderResult getFilesInMediaStore(Context context, FileCategoryFilter fileCategoryFilter, FileOrderingFilter fileOrderingFilter, int offset, int limit, Uri contentUri, MimeTypeMap mimeTypeMap, int filesCount, MediaMetadataRetriever mediaMetadataRetriever) {
        ArrayList<FileModel.FileData> files = new ArrayList<>();
        while (files.size() < limit) {
            Cursor cursor = getFilesCursor(context, fileCategoryFilter, fileOrderingFilter, offset, limit, contentUri);
            if (cursor == null) {
                return new MediaStoreFileLoaderResult(files, new MediaStoreFileLoaderRequest(offset, limit, fileOrderingFilter, fileCategoryFilter, false, files.size(), null, MediaStoreFileLoaderRequestType.FILES_IN_MEDIA_STORE));
            }
            if (cursor.getCount() == 0) {
                return new MediaStoreFileLoaderResult(files, null);
            }
            files.addAll(getFiles(cursor, contentUri, mimeTypeMap, filesCount + files.size(), false, mediaMetadataRetriever));
            offset += limit;
        }
        return new MediaStoreFileLoaderResult(files, new MediaStoreFileLoaderRequest(offset, limit, fileOrderingFilter, fileCategoryFilter, false, files.size(), null, MediaStoreFileLoaderRequestType.FILES_IN_MEDIA_STORE));
    }

    /* access modifiers changed from: package-private */
    public MediaStoreFileLoaderResult getFilesAndFoldersInFolder(Context context, File externalStorageDirectory, String folder, FileOrderingFilter fileOrderingFilter, Uri contentUri, MimeTypeMap mimeTypeMap, MediaMetadataRetriever mediaMetadataRetriever) {
        Cursor cursor = getFilesCursor(context, externalStorageDirectory, folder, fileOrderingFilter, contentUri);
        if (cursor == null) {
            return new MediaStoreFileLoaderResult(new ArrayList(), null);
        }
        return new MediaStoreFileLoaderResult(getFiles(cursor, contentUri, mimeTypeMap, 0, true, mediaMetadataRetriever), null);
    }

    public MediaStoreFileLoaderResult getFiles(Context context, MediaStoreFileLoaderRequest mediaStoreFileLoaderRequest, MediaMetadataRetriever mediaMetadataRetriever) {
        switch (mediaStoreFileLoaderRequest.type) {
            case FILES_AND_FOLDERS_IN_FOLDER:
                return getFilesAndFoldersInFolder(context, Environment.getExternalStorageDirectory(), mediaStoreFileLoaderRequest.folder, mediaStoreFileLoaderRequest.fileOrderingFilter, MediaStore.Files.getContentUri("external"), MimeTypeMap.getSingleton(), mediaMetadataRetriever);
            case FILES_IN_MEDIA_STORE:
                return getFilesInMediaStore(context, mediaStoreFileLoaderRequest.fileCategoryFilter, mediaStoreFileLoaderRequest.fileOrderingFilter, mediaStoreFileLoaderRequest.offset, mediaStoreFileLoaderRequest.limit, MediaStore.Files.getContentUri("external"), MimeTypeMap.getSingleton(), mediaStoreFileLoaderRequest.loadedFilesCount, mediaMetadataRetriever);
            default:
                throw new IllegalStateException("Invalid request type");
        }
    }
}
