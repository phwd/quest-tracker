package com.oculus.localfilemanager;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.debug.log.BLog;
import com.oculus.localfilemanager.FileModel;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

public class MediaStoreFileLoader {
    private static final String ALL_FILTER_QUERY = "_data NOT LIKE '%/Oculus/Avatars%' AND _data NOT LIKE '%/Android/data%' AND _data NOT LIKE '%/Android/obb%' AND _data NOT LIKE '%/DCIM/.thumbnails%'";
    private static final String DOWNLOAD_FILTER_QUERY = ("_data LIKE '%" + Environment.DIRECTORY_DOWNLOADS + "%' ");
    private static final String IMAGE_FILTER_QUERY = "media_type = 1";
    private static final String LIMIT_QUERY_PARAM = "limit";
    private static final String MEDIA_FILTER_QUERY = "media_type = 1 OR media_type = 3";
    private static final String[] METADATA_PROJECTIONS = {"_data", "media_type", "_size", "height", "width", "date_added", "mime_type"};
    private static final String TAG = "MediaStoreFileLoader";
    private static final String VIDEO_FILTER_QUERY = "media_type = 3";

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

    private static boolean isKnownFileType(String str, MimeTypeMap mimeTypeMap) {
        if (str.contains(".")) {
            return mimeTypeMap.hasExtension(str.substring(str.lastIndexOf(46) + 1));
        }
        return false;
    }

    public static class MediaStoreFileLoaderRequest {
        public final FileCategoryFilter fileCategoryFilter;
        public final FileOrderingFilter fileOrderingFilter;
        @Nullable
        public final String folder;
        public final int limit;
        public final boolean loadedAll;
        public final int loadedFilesCount;
        public final int offset;
        final MediaStoreFileLoaderRequestType type;

        /* synthetic */ MediaStoreFileLoaderRequest(int i, int i2, FileOrderingFilter fileOrderingFilter2, FileCategoryFilter fileCategoryFilter2, boolean z, int i3, String str, MediaStoreFileLoaderRequestType mediaStoreFileLoaderRequestType, AnonymousClass1 r9) {
            this(i, i2, fileOrderingFilter2, fileCategoryFilter2, z, i3, str, mediaStoreFileLoaderRequestType);
        }

        private MediaStoreFileLoaderRequest(int i, int i2, FileOrderingFilter fileOrderingFilter2, FileCategoryFilter fileCategoryFilter2, boolean z, int i3, @Nullable String str, MediaStoreFileLoaderRequestType mediaStoreFileLoaderRequestType) {
            this.offset = i;
            this.limit = i2;
            this.fileOrderingFilter = fileOrderingFilter2;
            this.fileCategoryFilter = fileCategoryFilter2;
            this.loadedAll = z;
            this.loadedFilesCount = i3;
            this.folder = str;
            this.type = mediaStoreFileLoaderRequestType;
        }

        public static MediaStoreFileLoaderRequest create(int i, FileOrderingFilter fileOrderingFilter2, FileCategoryFilter fileCategoryFilter2) {
            return new MediaStoreFileLoaderRequest(0, i, fileOrderingFilter2, fileCategoryFilter2, false, 0, null, MediaStoreFileLoaderRequestType.FILES_IN_MEDIA_STORE);
        }

        public static MediaStoreFileLoaderRequest create(@Nullable String str, FileOrderingFilter fileOrderingFilter2) {
            return new MediaStoreFileLoaderRequest(0, 0, fileOrderingFilter2, FileCategoryFilter.ALL, false, 0, str, MediaStoreFileLoaderRequestType.FILES_AND_FOLDERS_IN_FOLDER);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public static class HiddenFileFilter implements FileFilter {
        HiddenFileFilter() {
        }

        public boolean accept(File file) {
            return !file.isHidden();
        }
    }

    public static class MediaStoreFileLoaderResult {
        public final ArrayList<FileModel.FileData> files;
        @Nullable
        public final MediaStoreFileLoaderRequest nextMediaStoreFileLoaderRequest;

        public MediaStoreFileLoaderResult(ArrayList<FileModel.FileData> arrayList, @Nullable MediaStoreFileLoaderRequest mediaStoreFileLoaderRequest) {
            this.files = arrayList;
            this.nextMediaStoreFileLoaderRequest = mediaStoreFileLoaderRequest;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
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

    @VisibleForTesting
    static FileModel.FileType getFileType(int i, MimeTypeMap mimeTypeMap, String str) {
        if (i == 3) {
            return FileModel.FileType.VIDEO;
        }
        if (i == 1) {
            return FileModel.FileType.IMAGE;
        }
        return isKnownFileType(str, mimeTypeMap) ? FileModel.FileType.KNOWN : FileModel.FileType.UNKNOWN;
    }

    @Nullable
    private static String getFileFilterQueryFromCategory(FileCategoryFilter fileCategoryFilter) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$localfilemanager$MediaStoreFileLoader$FileCategoryFilter[fileCategoryFilter.ordinal()];
        if (i == 1) {
            return ALL_FILTER_QUERY;
        }
        if (i == 2) {
            return DOWNLOAD_FILTER_QUERY;
        }
        if (i == 3) {
            return IMAGE_FILTER_QUERY;
        }
        if (i == 4) {
            return VIDEO_FILTER_QUERY;
        }
        if (i != 5) {
            return null;
        }
        return MEDIA_FILTER_QUERY;
    }

    @Nullable
    private static String getFileSortingQueryFromOrdering(FileOrderingFilter fileOrderingFilter) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$localfilemanager$MediaStoreFileLoader$FileOrderingFilter[fileOrderingFilter.ordinal()];
        if (i == 1) {
            return "date_added DESC";
        }
        if (i == 2) {
            return "date_added ASC";
        }
        if (i == 3) {
            return "_size DESC";
        }
        if (i != 4) {
            return null;
        }
        return "_size ASC";
    }

    private static Uri getContentUriWithPagination(Uri uri, int i, int i2) {
        Uri.Builder buildUpon = uri.buildUpon();
        return buildUpon.appendQueryParameter(LIMIT_QUERY_PARAM, String.valueOf(i) + "," + String.valueOf(i2)).build();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public FileModel.FileData getFileData(File file, boolean z, Cursor cursor, CursorIndices cursorIndices, int i, MimeTypeMap mimeTypeMap, MediaMetadataRetriever mediaMetadataRetriever) {
        if (file == null || !file.exists()) {
            return null;
        }
        boolean isDirectory = file.isDirectory();
        if (isDirectory && !z) {
            return null;
        }
        try {
            String canonicalPath = file.getCanonicalPath();
            int i2 = cursor.getInt(cursorIndices.dateAdded);
            if (isDirectory) {
                int i3 = 0;
                File[] listFiles = file.listFiles(new HiddenFileFilter());
                if (listFiles != null) {
                    i3 = listFiles.length;
                }
                return FileModel.FileData.createFolder(canonicalPath, i2, new FileModel.FolderMetadata(i3), i);
            }
            long j = cursor.getLong(cursorIndices.size);
            int i4 = cursor.getInt(cursorIndices.height);
            int i5 = cursor.getInt(cursorIndices.width);
            FileModel.FileType fileType = getFileType(cursor.getInt(cursorIndices.mediaType), mimeTypeMap, canonicalPath);
            String string = cursor.getString(cursorIndices.mimeType);
            long j2 = 0;
            if (fileType == FileModel.FileType.VIDEO) {
                try {
                    mediaMetadataRetriever.setDataSource(canonicalPath);
                    String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
                    if (extractMetadata != null) {
                        j2 = Long.parseLong(extractMetadata);
                    }
                } catch (IllegalArgumentException e) {
                    BLog.e(TAG, "Unable to retrieve the video metadata", e);
                }
            }
            return FileModel.FileData.createFile(canonicalPath, i2, new FileModel.FileMetadata(j, i5, i4, fileType, string, j2), i);
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public Cursor getFilesCursor(Context context, FileCategoryFilter fileCategoryFilter, FileOrderingFilter fileOrderingFilter, int i, int i2, Uri uri) {
        return context.getContentResolver().query(getContentUriWithPagination(uri, i, i2), METADATA_PROJECTIONS, getFileFilterQueryFromCategory(fileCategoryFilter), null, getFileSortingQueryFromOrdering(fileOrderingFilter));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @VisibleForTesting
    public Cursor getFilesCursor(Context context, File file, @Nullable String str, FileOrderingFilter fileOrderingFilter, Uri uri) {
        try {
            StringBuilder sb = new StringBuilder();
            if (str == null || str.equals("")) {
                str = file.getCanonicalPath();
            }
            sb.append(str);
            sb.append("/");
            String sb2 = sb.toString();
            ContentResolver contentResolver = context.getContentResolver();
            String[] strArr = METADATA_PROJECTIONS;
            return contentResolver.query(uri, strArr, "_data LIKE '" + sb2 + "%' AND " + "_data" + " NOT LIKE '" + sb2 + "%/%'", null, getFileSortingQueryFromOrdering(fileOrderingFilter));
        } catch (IOException e) {
            BLog.e(TAG, "couldn't get external storage canonical path", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ArrayList<FileModel.FileData> getFiles(Cursor cursor, Uri uri, MimeTypeMap mimeTypeMap, int i, boolean z, MediaMetadataRetriever mediaMetadataRetriever) {
        FileModel.FileData fileData;
        CursorIndices cursorIndices = new CursorIndices(cursor);
        ArrayList<FileModel.FileData> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String string = cursor.getString(cursorIndices.path);
            if (!(string == null || (fileData = getFileData(new File(string), z, cursor, cursorIndices, i + arrayList.size(), mimeTypeMap, mediaMetadataRetriever)) == null)) {
                arrayList.add(fileData);
            }
        }
        cursor.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public MediaStoreFileLoaderResult getFilesInMediaStore(Context context, FileCategoryFilter fileCategoryFilter, FileOrderingFilter fileOrderingFilter, int i, int i2, Uri uri, MimeTypeMap mimeTypeMap, int i3, MediaMetadataRetriever mediaMetadataRetriever) {
        ArrayList arrayList = new ArrayList();
        int i4 = i;
        while (arrayList.size() < i2) {
            Cursor filesCursor = getFilesCursor(context, fileCategoryFilter, fileOrderingFilter, i4, i2, uri);
            if (filesCursor == null) {
                return new MediaStoreFileLoaderResult(arrayList, new MediaStoreFileLoaderRequest(i4, i2, fileOrderingFilter, fileCategoryFilter, false, arrayList.size(), null, MediaStoreFileLoaderRequestType.FILES_IN_MEDIA_STORE, null));
            }
            if (filesCursor.getCount() == 0) {
                return new MediaStoreFileLoaderResult(arrayList, null);
            }
            arrayList.addAll(getFiles(filesCursor, uri, mimeTypeMap, i3 + arrayList.size(), false, mediaMetadataRetriever));
            i4 += i2;
        }
        return new MediaStoreFileLoaderResult(arrayList, new MediaStoreFileLoaderRequest(i4, i2, fileOrderingFilter, fileCategoryFilter, false, arrayList.size(), null, MediaStoreFileLoaderRequestType.FILES_IN_MEDIA_STORE, null));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public MediaStoreFileLoaderResult getFilesAndFoldersInFolder(Context context, File file, @Nullable String str, FileOrderingFilter fileOrderingFilter, Uri uri, MimeTypeMap mimeTypeMap, MediaMetadataRetriever mediaMetadataRetriever) {
        Cursor filesCursor = getFilesCursor(context, file, str, fileOrderingFilter, uri);
        if (filesCursor == null) {
            return new MediaStoreFileLoaderResult(new ArrayList(), null);
        }
        return new MediaStoreFileLoaderResult(getFiles(filesCursor, uri, mimeTypeMap, 0, true, mediaMetadataRetriever), null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.localfilemanager.MediaStoreFileLoader$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$localfilemanager$MediaStoreFileLoader$FileCategoryFilter = new int[FileCategoryFilter.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$localfilemanager$MediaStoreFileLoader$FileOrderingFilter = new int[FileOrderingFilter.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$localfilemanager$MediaStoreFileLoader$MediaStoreFileLoaderRequestType = new int[MediaStoreFileLoaderRequestType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|(2:15|16)|17|(2:19|20)|21|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|12|13|15|16|17|(2:19|20)|21|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|9|10|11|12|13|15|16|17|19|20|21|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|5|6|7|9|10|11|12|13|15|16|17|19|20|21|23|24|25|26|27|28|29|30|31|32|34) */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0065 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        static {
            /*
            // Method dump skipped, instructions count: 143
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.localfilemanager.MediaStoreFileLoader.AnonymousClass1.<clinit>():void");
        }
    }

    @VisibleForTesting
    public MediaStoreFileLoaderResult getFiles(Context context, MediaStoreFileLoaderRequest mediaStoreFileLoaderRequest, MediaMetadataRetriever mediaMetadataRetriever) {
        int i = AnonymousClass1.$SwitchMap$com$oculus$localfilemanager$MediaStoreFileLoader$MediaStoreFileLoaderRequestType[mediaStoreFileLoaderRequest.type.ordinal()];
        if (i == 1) {
            return getFilesAndFoldersInFolder(context, Environment.getExternalStorageDirectory(), mediaStoreFileLoaderRequest.folder, mediaStoreFileLoaderRequest.fileOrderingFilter, MediaStore.Files.getContentUri("external"), MimeTypeMap.getSingleton(), mediaMetadataRetriever);
        }
        if (i == 2) {
            return getFilesInMediaStore(context, mediaStoreFileLoaderRequest.fileCategoryFilter, mediaStoreFileLoaderRequest.fileOrderingFilter, mediaStoreFileLoaderRequest.offset, mediaStoreFileLoaderRequest.limit, MediaStore.Files.getContentUri("external"), MimeTypeMap.getSingleton(), mediaStoreFileLoaderRequest.loadedFilesCount, mediaMetadataRetriever);
        }
        throw new IllegalStateException("Invalid request type");
    }
}
