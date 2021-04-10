package com.oculus.localmedia;

import X.AnonymousClass006;
import X.AnonymousClass04U;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.localmedia.FolderItem;
import com.oculus.localmedia.MediaItem;
import com.oculus.localmedia.MediaQuery;
import com.oculus.localmedia.MediaUtils;
import com.oculus.localmedia.filters.ExcludeFolderFilter;
import com.oculus.localmedia.filters.FolderFilter;
import com.oculus.localmedia.filters.MediaItemFilter;
import com.oculus.localmedia.filters.VisibilityFilter;
import com.oculus.localmedia.metadata.CachedMetadataManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class LocalMediaManager {
    public static final String DEFAULT_VISIBILITY_FILTER = "visibility=public";
    public static final String DOWNLOAD_FOLDER;
    public static final String DOWNLOAD_FOLDER_NAME = "My Downloads";
    public static final String EMPTY_FOLDER_FILTER = "folder!=empty";
    public static final boolean INCLUDE_DOWNLOAD_FOLDER = true;
    public static final boolean INCLUDE_HIDDEN_FOLDERS = false;
    public static final String PARENT_FOLDER_NAME = "..";
    public static final String PROTOCOL_VERSION = "1.0";
    public static final String ROOT_ALBUM_NAME = "Root";
    public static final String SDCARD_FOLDER = MediaProviderUtils.getExternalStorageDirectory();
    public static final String SDCARD_FOLDER_NAME = "SD Card";
    public static final String STORAGE_FOLDER;
    public static final String STORAGE_FOLDER_NAME = "Internal Storage";
    public static final String TAG = "LocalMediaManager";
    public static Context mStaticContext;
    public static long sAppPtr = 0;
    public static LocalMediaManager sLocalMediaManager = null;
    public static int sRequestId = 0;
    public String mAppVersion = null;
    public LocalMediaConfig mConfig = null;
    public Context mContext;
    public ImageMediaProvider mImageMediaProvider = null;
    public ContentObserver mImageObserver = null;
    public boolean mIsStandalone = false;
    public UncategorizedMediaProvider mUncategorizedMediaProvider = null;
    public ContentObserver mUncategorizedObserver = null;
    public VideoMediaProvider mVideoMediaProvider = null;
    public ContentObserver mVideoObserver = null;

    public interface Callback<T> {
        void onComplete(T t);
    }

    public static File getFileForOperation(MediaItem mediaItem) {
        String str;
        if (mediaItem != null && mediaItem.isLocal() && (str = mediaItem.mFilePath) != null && !str.isEmpty()) {
            File file = new File(str);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    private synchronized void initMediaProviders(int i, boolean z) {
        UncategorizedMediaProvider uncategorizedMediaProvider;
        ImageMediaProvider imageMediaProvider;
        VideoMediaProvider videoMediaProvider;
        boolean z2 = false;
        if (MediaType.FOLDER.getValue() == i) {
            z2 = true;
        }
        if ((MediaType.VIDEO.match(i) || z2) && ((videoMediaProvider = this.mVideoMediaProvider) == null || videoMediaProvider.mHasChanged || z)) {
            this.mVideoMediaProvider = new VideoMediaProvider(this.mContext, this.mConfig);
        }
        if ((MediaType.IMAGE.match(i) || z2) && ((imageMediaProvider = this.mImageMediaProvider) == null || imageMediaProvider.mHasChanged || z)) {
            this.mImageMediaProvider = new ImageMediaProvider(this.mContext, this.mConfig);
        }
        if (MediaType.UNCATEGORIZED.match(i) && ((uncategorizedMediaProvider = this.mUncategorizedMediaProvider) == null || uncategorizedMediaProvider.mHasChanged || z)) {
            this.mUncategorizedMediaProvider = new UncategorizedMediaProvider(this.mContext);
        }
    }

    public static native void nativeInit();

    public static native void onCheckPermissionComplete(int i, boolean z, long j);

    public static native void onCropComplete(int i, String str, long j);

    public static native void onDeleteComplete(int i, boolean z, long j);

    public static native void onGetKeyframeComplete(int i, String str, long j);

    public static native void onGetThumbnailComplete(int i, String str, long j);

    public static native void onLocalMediaChanged(long j);

    public static native void onMoveComplete(int i, boolean z, long j);

    public static native void onQueryComplete(int i, String str, long j);

    public static native void onRefreshComplete(int i, long j);

    public static native void onTrimComplete(int i, String str, long j);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean checkReadWritePermission() {
        if (AnonymousClass04U.A00(this.mContext, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        return false;
    }

    private List<FolderItem> getFolderItems(MediaQuery mediaQuery, boolean z) {
        String name;
        String str;
        List<MediaSmartQueryType> foldersForPath;
        ArrayList arrayList = new ArrayList();
        if (!mediaQuery.isSmartQuery() || (foldersForPath = MediaSmartQueryUtils.getFoldersForPath(mediaQuery.Path)) == null) {
            if (MediaType.FOLDER.match(mediaQuery.Types)) {
                String str2 = mediaQuery.Path;
                if (str2 == null) {
                    for (String str3 : FolderMediaCountRegistry.getSingleton().getFoldersWithMedia(mediaQuery.Types)) {
                        File file = new File(str3);
                        if (file.isDirectory() && !file.getName().startsWith(".")) {
                            if (file.getAbsolutePath().equalsIgnoreCase(STORAGE_FOLDER)) {
                                name = ROOT_ALBUM_NAME;
                            } else {
                                name = file.getName();
                            }
                            arrayList.add(createFolderItem(name, file.getAbsolutePath(), file.lastModified()));
                        }
                    }
                } else if (str2.equalsIgnoreCase("") || str2.equalsIgnoreCase("/")) {
                    arrayList.add(createFolderItem(STORAGE_FOLDER_NAME, STORAGE_FOLDER));
                    String str4 = SDCARD_FOLDER;
                    if (str4 != null) {
                        arrayList.add(createFolderItem(SDCARD_FOLDER_NAME, str4));
                    }
                    arrayList.add(createFolderItem(DOWNLOAD_FOLDER_NAME, DOWNLOAD_FOLDER));
                } else {
                    File[] listFiles = new File(str2).listFiles();
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            if (file2.isDirectory() && !file2.getName().startsWith(".") && ((str = mediaQuery.Filters) == null || str.indexOf(EMPTY_FOLDER_FILTER) <= 0 || FolderMediaCountRegistry.getSingleton().get(mediaQuery.Path, mediaQuery.Types).mAggregatedCount != 0)) {
                                arrayList.add(createFolderItem(file2.getName(), file2.getAbsolutePath(), file2.lastModified()));
                            }
                        }
                    }
                }
            }
            if (!z) {
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    FolderItem folderItem = (FolderItem) it.next();
                    if (!MediaProviderUtils.isPrivateFolder(folderItem.mPath)) {
                        arrayList2.add(folderItem);
                    }
                }
                return arrayList2;
            }
        } else {
            for (MediaSmartQueryType mediaSmartQueryType : foldersForPath) {
                String obj = mediaSmartQueryType.toString();
                arrayList.add(createFolderItem(obj, AnonymousClass006.A07("*", obj)));
            }
        }
        return arrayList;
    }

    private ArrayList<MediaItem> getMediaItemsByType(int i) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        if (MediaType.VIDEO.match(i)) {
            arrayList.addAll(this.mVideoMediaProvider.mItems);
        }
        if (MediaType.IMAGE.match(i)) {
            arrayList.addAll(this.mImageMediaProvider.mItems);
        }
        if (MediaType.UNCATEGORIZED.match(i)) {
            arrayList.addAll(this.mUncategorizedMediaProvider.mItems);
        }
        return arrayList;
    }

    public static synchronized int getNextRequestId() {
        int i;
        synchronized (LocalMediaManager.class) {
            i = sRequestId + 1;
            sRequestId = i;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        if (r2 == false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.oculus.localmedia.filters.MediaItemFilter> getQueryFilters(java.lang.String r5) {
        /*
            r4 = this;
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r2 = 0
            if (r5 == 0) goto L_0x0028
            java.lang.String r0 = ","
            java.util.StringTokenizer r1 = new java.util.StringTokenizer
            r1.<init>(r5, r0)
        L_0x000f:
            boolean r0 = r1.hasMoreTokens()
            if (r0 == 0) goto L_0x0026
            java.lang.String r0 = r1.nextToken()
            com.oculus.localmedia.filters.MediaItemFilter r0 = com.oculus.localmedia.filters.MediaItemFilterFactory.create(r0)
            if (r0 == 0) goto L_0x000f
            r3.add(r0)
            boolean r0 = r0 instanceof com.oculus.localmedia.filters.VisibilityFilter
            r2 = r2 | r0
            goto L_0x000f
        L_0x0026:
            if (r2 != 0) goto L_0x0031
        L_0x0028:
            java.lang.String r0 = "visibility=public"
            com.oculus.localmedia.filters.MediaItemFilter r0 = com.oculus.localmedia.filters.MediaItemFilterFactory.create(r0)
            r3.add(r0)
        L_0x0031:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.localmedia.LocalMediaManager.getQueryFilters(java.lang.String):java.util.List");
    }

    public static String getServerInfo() {
        LocalMediaManager localMediaManager = sLocalMediaManager;
        if (localMediaManager != null) {
            return String.format("Android %d/MediaServer %s/Build %s", Integer.valueOf(Build.VERSION.SDK_INT), "1.0", localMediaManager.mAppVersion);
        }
        return null;
    }

    public static boolean isStandaloneDevice() {
        LocalMediaManager localMediaManager = sLocalMediaManager;
        if (localMediaManager == null) {
            return false;
        }
        return localMediaManager.mIsStandalone;
    }

    private MediaResponse queryInternal(MediaQuery mediaQuery) throws Exception {
        initMediaProviders(mediaQuery.Types, false);
        if (getMediaItemsByType(mediaQuery.Types).size() == 0 && !checkReadWritePermission()) {
            throw new SecurityException("android.permission.READ_EXTERNAL_STORAGE permission missing");
        } else if (MediaProviderUtils.isFile(mediaQuery.Path)) {
            return queryMediaByFilePath(mediaQuery);
        } else {
            String str = mediaQuery.MediaId;
            if (str == null || str.isEmpty()) {
                return queryMedia(mediaQuery);
            }
            return queryMediaByMediaId(mediaQuery);
        }
    }

    private MediaResponse queryMediaByFilePath(MediaQuery mediaQuery) {
        MediaResponse mediaResponse = new MediaResponse(mediaQuery.QueryId);
        ArrayList<MediaItem> mediaItemsByType = getMediaItemsByType(mediaQuery.Types);
        if (mediaQuery.Path != null) {
            Iterator<MediaItem> it = mediaItemsByType.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (mediaQuery.Path.equalsIgnoreCase(next.mFilePath)) {
                    next.prepare(this.mContext, this.mConfig, mediaQuery);
                    mediaResponse.addMediaItem(next);
                    return mediaResponse;
                }
            }
        }
        throw new IllegalArgumentException(AnonymousClass006.A07("Incorrect local file specified, path=", mediaQuery.Path));
    }

    private MediaResponse queryMediaByMediaId(MediaQuery mediaQuery) {
        MediaResponse mediaResponse = new MediaResponse(mediaQuery.QueryId);
        ArrayList<MediaItem> mediaItemsByType = getMediaItemsByType(mediaQuery.Types);
        String str = mediaQuery.MediaId;
        if (str != null && !str.isEmpty()) {
            long parseLong = Long.parseLong(str, 10);
            Iterator<MediaItem> it = mediaItemsByType.iterator();
            while (it.hasNext()) {
                MediaItem next = it.next();
                if (parseLong == next.getId()) {
                    next.prepare(this.mContext, this.mConfig, mediaQuery, true);
                    mediaResponse.addMediaItem(next);
                    return mediaResponse;
                }
            }
        }
        throw new IllegalArgumentException(AnonymousClass006.A07("Incorrect local file specified, mediaId=", mediaQuery.MediaId));
    }

    public static void refresh(int i) {
        if (!sLocalMediaManager.checkReadWritePermission()) {
            Log.w(TAG, "Missing storage permissions, skipping refresh.");
        } else {
            sLocalMediaManager.initMediaProviders(i, true);
        }
    }

    private void startObservingMedia() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.mImageObserver);
        contentResolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.mVideoObserver);
    }

    public static void stop() {
        LocalMediaManager localMediaManager = sLocalMediaManager;
        if (localMediaManager != null) {
            localMediaManager.stopObservingMedia();
        }
    }

    private void stopObservingMedia() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.unregisterContentObserver(this.mImageObserver);
        contentResolver.unregisterContentObserver(this.mVideoObserver);
    }

    public MediaResponse queryMedia(MediaQuery mediaQuery) {
        int i;
        String str;
        String str2 = null;
        if (mediaQuery.DepthLevel < 0) {
            return null;
        }
        MediaResponse mediaResponse = new MediaResponse(mediaQuery.QueryId);
        if (mediaQuery.isSmartQuery()) {
            MediaSmartQueryUtils.rewrite(mediaQuery);
        }
        List<MediaItemFilter> queryFilters = getQueryFilters(mediaQuery.Filters);
        if (!TextUtils.isEmpty(mediaQuery.Path)) {
            if (TextUtils.equals(mediaQuery.Path, "/virtual/sideloaded")) {
                mediaQuery.Path = "/";
                queryFilters.add(new ExcludeFolderFilter("/storage/emulated/0/Oculus/"));
            } else {
                queryFilters.add(new FolderFilter(mediaQuery.Path));
            }
        }
        boolean filtersIncludePrivate = filtersIncludePrivate(queryFilters);
        List<MediaItem> mediaItems = getMediaItems(mediaQuery.Types, queryFilters);
        List<FolderItem> folderItems = getFolderItems(mediaQuery, filtersIncludePrivate);
        FolderItem.sort(folderItems, mediaQuery.Sort, mediaQuery.IsSortAscending);
        MediaItem.sort(mediaItems, mediaQuery.Sort, mediaQuery.IsSortAscending);
        String str3 = mediaQuery.Cursor;
        boolean z = false;
        if (str3 != null) {
            i = Integer.parseInt(str3, 10);
        } else {
            i = 0;
        }
        int size = mediaItems.size() + folderItems.size();
        int i2 = mediaQuery.Size;
        int max = Math.max(Math.min(i2 + i, size) - 1, 0);
        if (i <= max) {
            int i3 = i;
            while (i3 <= max && i3 < size) {
                if (i3 < folderItems.size()) {
                    FolderItem folderItem = folderItems.get(i3);
                    folderItem.prepare(this, mediaQuery);
                    mediaResponse.addFolderItem(folderItem);
                } else {
                    MediaItem mediaItem = mediaItems.get(i3 - folderItems.size());
                    mediaItem.prepare(this.mContext, this.mConfig, mediaQuery);
                    mediaResponse.addMediaItem(mediaItem);
                }
                i3++;
            }
            if (max < size - 1) {
                z = true;
            }
            if (i > 0) {
                str = String.valueOf(i);
            } else {
                str = null;
            }
            if (z) {
                str2 = String.valueOf(max + 1);
            }
            mediaResponse.paginationData = new MediaPagination(size, str, str2);
            return mediaResponse;
        }
        throw new IndexOutOfBoundsException(AnonymousClass006.A0A("Incorrect pagination data, index=", mediaQuery.Cursor, " size=", i2));
    }

    static {
        try {
            nativeInit();
        } catch (UnsatisfiedLinkError unused) {
            Log.w(TAG, "Could invoke nativeInit() method, only needed by VrShell panel apps.");
        }
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        STORAGE_FOLDER = absolutePath;
        DOWNLOAD_FOLDER = AnonymousClass006.A07(absolutePath, "/Download");
    }

    public LocalMediaManager(Context context) {
        this.mContext = context;
        this.mImageObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass1 */

            public void onChange(boolean z) {
                LocalMediaManager.onLocalMediaChanged(LocalMediaManager.sAppPtr);
                ImageMediaProvider imageMediaProvider = LocalMediaManager.this.mImageMediaProvider;
                if (imageMediaProvider != null) {
                    imageMediaProvider.mHasChanged = true;
                }
            }
        };
        this.mVideoObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass2 */

            public void onChange(boolean z) {
                LocalMediaManager.onLocalMediaChanged(LocalMediaManager.sAppPtr);
                VideoMediaProvider videoMediaProvider = LocalMediaManager.this.mVideoMediaProvider;
                if (videoMediaProvider != null) {
                    videoMediaProvider.mHasChanged = true;
                }
            }
        };
        this.mUncategorizedObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass3 */

            public void onChange(boolean z) {
                LocalMediaManager.onLocalMediaChanged(LocalMediaManager.sAppPtr);
                UncategorizedMediaProvider uncategorizedMediaProvider = LocalMediaManager.this.mUncategorizedMediaProvider;
                if (uncategorizedMediaProvider != null) {
                    uncategorizedMediaProvider.mHasChanged = true;
                }
            }
        };
        try {
            this.mAppVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            this.mIsStandalone = context.getPackageManager().hasSystemFeature("oculus.hardware.standalone_vr");
        } catch (Exception unused) {
        }
    }

    public static int cropAsync(final String str, final int i, final int i2, final int i3, final int i4, final int i5, final long j) {
        final int nextRequestId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass9 */

            public void run() {
                String str;
                try {
                    MediaUtils.MediaEditResult cropImage = MediaUtils.cropImage(str, i, i2, i3, i4, i5, LocalMediaManager.mStaticContext);
                    str = new JSONObject().put("error", cropImage.error).put("dstFilePath", cropImage.dstFilePath).toString();
                } catch (Exception e) {
                    MediaResponse mediaResponse = new MediaResponse(Integer.toString(nextRequestId));
                    mediaResponse.setError(e.getMessage());
                    str = mediaResponse.toString();
                    Log.e(LocalMediaManager.TAG, "Error processing crop source file.", e);
                }
                LocalMediaManager.onCropComplete(nextRequestId, str, j);
            }
        }).start();
        return nextRequestId;
    }

    public static int deleteAsync(final String str, final long j) {
        final int nextRequestId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass7 */

            public void run() {
                String str;
                String str2;
                boolean z;
                MediaItem build = MediaItem.Builder.fromJson(str).build();
                String str3 = build.mFilePath;
                File fileForOperation = LocalMediaManager.getFileForOperation(build);
                if (fileForOperation == null) {
                    str = LocalMediaManager.TAG;
                    str2 = "Failed to get a valid file for delete operation";
                    Log.e(str, str2);
                    z = false;
                } else if (fileForOperation.delete()) {
                    z = true;
                    LocalMediaManager localMediaManager = LocalMediaManager.sLocalMediaManager;
                    if (localMediaManager != null && build.deleteMedia(localMediaManager.mContext.getContentResolver()) == 0) {
                        Log.e(LocalMediaManager.TAG, AnonymousClass006.A07("File failed to delete media store entry for path:", str3));
                    }
                } else {
                    str = LocalMediaManager.TAG;
                    str2 = AnonymousClass006.A07("File failed to delete for path:", str3);
                    Log.e(str, str2);
                    z = false;
                }
                LocalMediaManager.onDeleteComplete(nextRequestId, z, j);
            }
        }).start();
        return nextRequestId;
    }

    private boolean filtersIncludePrivate(List<MediaItemFilter> list) {
        for (MediaItemFilter mediaItemFilter : list) {
            if (mediaItemFilter instanceof VisibilityFilter) {
                return ((VisibilityFilter) mediaItemFilter).includePrivate();
            }
        }
        return false;
    }

    public static int getKeyframeAsync(final String str, final long j) {
        final int nextRequestId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass11 */

            public void run() {
                String str;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("result", MediaUtils.getKeyFrameTimestamps(str));
                    str = jSONObject.toString();
                } catch (Exception e) {
                    MediaResponse mediaResponse = new MediaResponse(Integer.toString(nextRequestId));
                    mediaResponse.setError(e.getMessage());
                    str = mediaResponse.toString();
                    Log.e(LocalMediaManager.TAG, "Error getting video keyframes.", e);
                }
                LocalMediaManager.onGetKeyframeComplete(nextRequestId, str, j);
            }
        }).start();
        return nextRequestId;
    }

    private List<MediaItem> getMediaItems(int i, List<MediaItemFilter> list) {
        ArrayList<MediaItem> mediaItemsByType = getMediaItemsByType(i);
        if (list == null || list.size() == 0) {
            return mediaItemsByType;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<MediaItem> it = mediaItemsByType.iterator();
        while (it.hasNext()) {
            MediaItem next = it.next();
            Iterator<MediaItemFilter> it2 = list.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (!it2.next().match(next)) {
                        break;
                    }
                } else {
                    arrayList.add(next);
                    break;
                }
            }
        }
        return arrayList;
    }

    public static int getThumbnailAsync(final String str, final int i, final long j) {
        final int nextRequestId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass8 */

            public void run() {
                String str;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("result", MediaUtils.getThumbnail(str, i));
                    str = jSONObject.toString();
                } catch (Exception e) {
                    MediaResponse mediaResponse = new MediaResponse(Integer.toString(nextRequestId));
                    mediaResponse.setError(e.getMessage());
                    str = mediaResponse.toString();
                    Log.e(LocalMediaManager.TAG, "Error getting video thumbnail.", e);
                }
                LocalMediaManager.onGetThumbnailComplete(nextRequestId, str, j);
            }
        }).start();
        return nextRequestId;
    }

    public static int isReadWritePermission(final long j) {
        final int nextRequestId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass14 */

            public void run() {
                LocalMediaManager.onCheckPermissionComplete(nextRequestId, LocalMediaManager.sLocalMediaManager.checkReadWritePermission(), j);
            }
        }).start();
        return nextRequestId;
    }

    public static int moveAsync(final String str, final String str2, final long j) {
        final int nextRequestId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass6 */

            public void run() {
                String str;
                String str2;
                boolean z;
                MediaItem build = MediaItem.Builder.fromJson(str).build();
                String str3 = build.mFilePath;
                File fileForOperation = LocalMediaManager.getFileForOperation(build);
                if (fileForOperation == null) {
                    str = LocalMediaManager.TAG;
                    str2 = "Failed to get a valid file for move operation";
                    Log.e(str, str2);
                    z = false;
                } else if (fileForOperation.renameTo(new File(str2))) {
                    z = true;
                    LocalMediaManager localMediaManager = LocalMediaManager.sLocalMediaManager;
                    if (localMediaManager != null && build.moveMedia(localMediaManager.mContext.getContentResolver(), str2) == 0) {
                        Log.e(LocalMediaManager.TAG, AnonymousClass006.A0C("File failed to update old media store entry at path '", str3, "' to path '", str2, "'"));
                    }
                } else {
                    str = LocalMediaManager.TAG;
                    str2 = AnonymousClass006.A0C("File failed to move from path '", str3, "' to path '", str2, "'");
                    Log.e(str, str2);
                    z = false;
                }
                LocalMediaManager.onMoveComplete(nextRequestId, z, j);
            }
        }).start();
        return nextRequestId;
    }

    public static int trimAsync(final String str, final int i, final int i2, final long j) {
        final int nextRequestId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass10 */

            public void run() {
                String str;
                try {
                    MediaUtils.MediaEditResult trimVideo = MediaUtils.trimVideo(str, i, i2, LocalMediaManager.mStaticContext);
                    str = new JSONObject().put("error", trimVideo.error).put("dstFilePath", trimVideo.dstFilePath).toString();
                } catch (Exception e) {
                    MediaResponse mediaResponse = new MediaResponse(Integer.toString(nextRequestId));
                    mediaResponse.setError(e.getMessage());
                    str = mediaResponse.toString();
                    Log.e(LocalMediaManager.TAG, "Error processing trim source file.", e);
                }
                LocalMediaManager.onTrimComplete(nextRequestId, str, j);
            }
        }).start();
        return nextRequestId;
    }

    public static void setAppPointer(long j) {
        sAppPtr = j;
    }

    private void setConfig(LocalMediaConfig localMediaConfig) {
        this.mConfig = localMediaConfig;
    }

    private FolderItem createFolderItem(String str, String str2) {
        return createFolderItem(str, str2, 0);
    }

    private FolderItem createFolderItem(String str, String str2, long j) {
        FolderItem.Builder builder = new FolderItem.Builder();
        builder.mName = str;
        builder.mPath = str2;
        builder.mDateAdded = j;
        return builder.build();
    }

    public static void init(Context context) {
        mStaticContext = context;
        if (sLocalMediaManager == null) {
            sLocalMediaManager = new LocalMediaManager(context);
            CachedMetadataManager.init(context);
        }
    }

    public static void init(Context context, int i) {
        init(context, i, null);
    }

    public static void init(Context context, int i, LocalMediaConfig localMediaConfig) {
        init(context);
        LocalMediaManager localMediaManager = sLocalMediaManager;
        localMediaManager.mConfig = localMediaConfig;
        localMediaManager.initMediaProviders(i, false);
    }

    public static MediaResponse query(MediaQuery mediaQuery) throws Exception {
        mediaQuery.toString();
        System.currentTimeMillis();
        MediaResponse queryInternal = sLocalMediaManager.queryInternal(mediaQuery);
        System.currentTimeMillis();
        return queryInternal;
    }

    public static String query(String str, String str2) {
        MediaResponse mediaResponse;
        try {
            MediaQuery.Builder fromJson = MediaQuery.Builder.fromJson(str);
            fromJson.ExternalHost = str2;
            MediaQuery build = fromJson.build();
            String str3 = build.QueryId;
            return query(build).toString();
        } catch (SecurityException e) {
            mediaResponse = new MediaResponse("");
            String message = e.getMessage();
            MediaErrorCode mediaErrorCode = MediaErrorCode.READ_PERMISSION;
            mediaResponse.error = message;
            mediaResponse.errorCode = mediaErrorCode;
            return mediaResponse.toString();
        } catch (Throwable th) {
            mediaResponse = new MediaResponse("");
            mediaResponse.setError(th.getMessage());
            return mediaResponse.toString();
        }
    }

    public static int queryAsync(String str, final long j) {
        final int nextRequestId = getNextRequestId();
        queryAsync(str, new Callback<String>() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass5 */

            public void onComplete(String str) {
                LocalMediaManager.onQueryComplete(nextRequestId, str, j);
            }
        });
        return nextRequestId;
    }

    public static void queryAsync(final String str, final Callback<String> callback) {
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass13 */

            public void run() {
                callback.onComplete(LocalMediaManager.query(str, null));
            }
        }).start();
    }

    public static int refreshAsync(String str, final long j) {
        final int nextRequestId = getNextRequestId();
        refreshAsync(str, new Callback<String>() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass4 */

            public void onComplete(String str) {
                LocalMediaManager.onRefreshComplete(nextRequestId, j);
            }
        });
        return nextRequestId;
    }

    public static void refreshAsync(final String str, final Callback<String> callback) {
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass12 */

            public void run() {
                if (LocalMediaManager.sLocalMediaManager != null) {
                    try {
                        LocalMediaManager.refresh(MediaQueryUtils.parseMediaTypes(str));
                        callback.onComplete(null);
                    } catch (Exception unused) {
                    }
                }
            }
        }).start();
    }

    public static void start() {
        LocalMediaManager localMediaManager = sLocalMediaManager;
        if (localMediaManager != null) {
            localMediaManager.startObservingMedia();
        }
    }

    public static void start(long j) {
        sAppPtr = j;
        start();
    }
}
