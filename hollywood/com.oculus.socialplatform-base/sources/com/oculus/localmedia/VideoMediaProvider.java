package com.oculus.localmedia;

import X.AnonymousClass006;
import android.content.Context;
import android.util.Log;
import com.oculus.localmedia.MediaItem;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class VideoMediaProvider {
    public static final String CINEMAGRAPH_FILENAME = "cinemagraph.mp4";
    public static final String DOWNLOADED_MOVIES_PATH = "Download/oculus_downloaded_videos";
    public static final String[] VIDEO_THUMBNAIL_PROJECTION = {"video_id", "_data"};
    public static MimeTypeHelper sMimeTypeHelper;
    public final String[] VIDEO_PROJECTION = {"_id", "duration", "mime_type", "date_added", "datetaken", "_data", "width", "height", "_size"};
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
        mimeTypeHelper.registerMimeType("video/mp4", "mp4");
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

    private void addVideosFromPath(ArrayList<MediaItem> arrayList, File file, boolean z) {
        if (!(file == null || !file.isDirectory() || file.listFiles() == null)) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (!file2.isDirectory() || !z) {
                    String obj = file2.getAbsoluteFile().toString();
                    if (obj.toLowerCase().indexOf(CINEMAGRAPH_FILENAME) <= 0) {
                        boolean isPrivateFile = MediaProviderUtils.isPrivateFile(obj);
                        if (sMimeTypeHelper.isSupportedMediaFile(obj)) {
                            addVideoMediaItem(arrayList, 0, obj, null, file2.lastModified(), file2.length(), 0, 0, 0, null, isPrivateFile);
                        }
                    }
                } else {
                    addVideosFromPath(arrayList, file2, true);
                }
            }
        }
    }

    public static boolean isSupportedMimeType(String str) {
        return sMimeTypeHelper.isSupportedMimeType(str);
    }

    private void scanHiddenFolders(ArrayList<MediaItem> arrayList) {
        try {
            Iterator<String> it = MediaProviderUtils.getHiddenFolders(this.mContext).iterator();
            while (it.hasNext()) {
                addVideosFromPath(arrayList, new File(it.next()), false);
            }
        } catch (Exception unused) {
        }
    }

    private ArrayList<MediaItem> scanMedia() {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        System.currentTimeMillis();
        scanMediaStore(arrayList);
        System.currentTimeMillis();
        arrayList.size();
        System.currentTimeMillis();
        scanExternalStorage(arrayList);
        System.currentTimeMillis();
        arrayList.size();
        System.currentTimeMillis();
        scanHiddenFolders(arrayList);
        System.currentTimeMillis();
        arrayList.size();
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0105, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0108, code lost:
        if (r4 == null) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x010a, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x010d, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x010e, code lost:
        if (r4 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[ExcHandler: Exception (unused java.lang.Exception), SYNTHETIC, Splitter:B:6:0x0053] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void scanMediaStore(java.util.ArrayList<com.oculus.localmedia.MediaItem> r26) {
        /*
        // Method dump skipped, instructions count: 276
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.VideoMediaProvider.scanMediaStore(java.util.ArrayList):void");
    }

    public VideoMediaProvider(Context context, LocalMediaConfig localMediaConfig) {
        this.mContext = context;
        this.mLocalMediaConfig = localMediaConfig;
        this.mFolderMediaCountRegistry = FolderMediaCountRegistry.getSingleton();
        ArrayList<MediaItem> scanMedia = scanMedia();
        this.mItems = scanMedia;
        this.mFolderMediaCountRegistry.index(MediaType.VIDEO, scanMedia);
    }

    private void scanExternalStorage(ArrayList<MediaItem> arrayList) {
        try {
            String externalStorageDirectory = MediaProviderUtils.getExternalStorageDirectory();
            if (externalStorageDirectory != null && !externalStorageDirectory.isEmpty()) {
                addVideosFromPath(arrayList, new File(externalStorageDirectory), true);
            }
        } catch (Exception unused) {
        }
    }

    public ArrayList<MediaItem> getItems() {
        return this.mItems;
    }

    public boolean hasChanged() {
        return this.mHasChanged;
    }

    private void addVideoMediaItem(ArrayList<MediaItem> arrayList, int i, String str, String str2, long j, long j2, int i2, int i3, int i4, String str3, boolean z) {
        String str4;
        String str5;
        Iterator<MediaItem> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().mFilePath.equalsIgnoreCase(str)) {
                return;
            }
        }
        LocalMediaConfig localMediaConfig = this.mLocalMediaConfig;
        if (localMediaConfig == null || localMediaConfig.isSupportedPath(str)) {
            if (!new File(str).isFile()) {
                str4 = LocalMediaManager.TAG;
                str5 = "Skipping missing media item for : ";
            } else {
                if (str3 == null) {
                    str3 = sMimeTypeHelper.getMimeTypeFromFileExtension(str);
                }
                MediaItem.Builder builder = new MediaItem.Builder();
                builder.mType = MediaType.VIDEO;
                builder.mInternalId = i;
                builder.mDurationSecs = i2;
                builder.mDateAdded = j;
                builder.mFileSize = j2;
                builder.mFilePath = str;
                builder.mThumbnailFilePath = str2;
                builder.mWidth = i3;
                builder.mHeight = i4;
                builder.mMimeType = str3;
                builder.mIsPrivate = z;
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
}
