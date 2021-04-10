package com.oculus.localmedia;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.oculus.common.build.BuildConstants;
import com.oculus.localmedia.MediaItem;
import com.oculus.localmedia.MediaPagination;
import com.oculus.localmedia.MediaQuery;
import com.oculus.localmedia.MediaUtils;
import com.oculus.localmedia.filters.ExcludeFolderFilter;
import com.oculus.localmedia.filters.FolderFilter;
import com.oculus.localmedia.filters.MediaItemFilter;
import com.oculus.localmedia.filters.MediaItemFilterFactory;
import com.oculus.localmedia.filters.VisibilityFilter;
import com.oculus.localmedia.metadata.CachedMetadataManager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.json.JSONObject;

public class LocalMediaManager {
    public static final String DEFAULT_VISIBILITY_FILTER = "visibility=public";
    public static final String DOWNLOAD_FOLDER = (STORAGE_FOLDER + "/Download");
    public static final String DOWNLOAD_FOLDER_NAME = "My Downloads";
    public static final String EMPTY_FOLDER_FILTER = "folder!=empty";
    public static final boolean INCLUDE_DOWNLOAD_FOLDER = true;
    public static final boolean INCLUDE_HIDDEN_FOLDERS = false;
    public static final String PARENT_FOLDER_NAME = "..";
    public static final String PROTOCOL_VERSION = "1.0";
    public static final String ROOT_ALBUM_NAME = "Root";
    public static final String SDCARD_FOLDER = MediaProviderUtils.getExternalStorageDirectory();
    public static final String SDCARD_FOLDER_NAME = "SD Card";
    public static final String STORAGE_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String STORAGE_FOLDER_NAME = "Internal Storage";
    public static final String TAG = LocalMediaManager.class.getSimpleName();
    private static Context mStaticContext;
    private static long sAppPtr = 0;
    private static LocalMediaManager sLocalMediaManager = null;
    private static int sRequestId = 0;
    private String mAppVersion = null;
    private LocalMediaConfig mConfig = null;
    private Context mContext;
    private ImageMediaProvider mImageMediaProvider = null;
    private ContentObserver mImageObserver = null;
    private boolean mIsStandalone = false;
    private UncategorizedMediaProvider mUncategorizedMediaProvider = null;
    private ContentObserver mUncategorizedObserver = null;
    private VideoMediaProvider mVideoMediaProvider = null;
    private ContentObserver mVideoObserver = null;

    public interface Callback<T> {
        void onComplete(T t);
    }

    private static native void nativeInit();

    /* access modifiers changed from: private */
    public static native void onCheckPermissionComplete(int i, boolean z, long j);

    /* access modifiers changed from: private */
    public static native void onCropComplete(int i, String str, long j);

    /* access modifiers changed from: private */
    public static native void onDeleteComplete(int i, boolean z, long j);

    /* access modifiers changed from: private */
    public static native void onGetKeyframeComplete(int i, String str, long j);

    /* access modifiers changed from: private */
    public static native void onGetThumbnailComplete(int i, String str, long j);

    /* access modifiers changed from: private */
    public static native void onLocalMediaChanged(long j);

    /* access modifiers changed from: private */
    public static native void onMoveComplete(int i, boolean z, long j);

    /* access modifiers changed from: private */
    public static native void onQueryComplete(int i, String str, long j);

    /* access modifiers changed from: private */
    public static native void onRefreshComplete(int i, long j);

    /* access modifiers changed from: private */
    public static native void onTrimComplete(int i, String str, long j);

    static {
        try {
            nativeInit();
        } catch (UnsatisfiedLinkError e) {
            Log.w(TAG, "Could invoke nativeInit() method, only needed by VrShell panel apps.");
        }
    }

    private LocalMediaManager(Context context) {
        this.mContext = context;
        this.mImageObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass1 */

            public void onChange(boolean selfChange) {
                Log.d(LocalMediaManager.TAG, "Images has been changed.");
                LocalMediaManager.onLocalMediaChanged(LocalMediaManager.sAppPtr);
                if (LocalMediaManager.this.mImageMediaProvider != null) {
                    LocalMediaManager.this.mImageMediaProvider.onChange();
                }
            }
        };
        this.mVideoObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass2 */

            public void onChange(boolean selfChange) {
                Log.d(LocalMediaManager.TAG, "Videos has been changed.");
                LocalMediaManager.onLocalMediaChanged(LocalMediaManager.sAppPtr);
                if (LocalMediaManager.this.mVideoMediaProvider != null) {
                    LocalMediaManager.this.mVideoMediaProvider.onChange();
                }
            }
        };
        this.mUncategorizedObserver = new ContentObserver(new Handler()) {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass3 */

            public void onChange(boolean selfChange) {
                Log.d(LocalMediaManager.TAG, "Uncategorized items have been changed.");
                LocalMediaManager.onLocalMediaChanged(LocalMediaManager.sAppPtr);
                if (LocalMediaManager.this.mUncategorizedMediaProvider != null) {
                    LocalMediaManager.this.mUncategorizedMediaProvider.onChange();
                }
            }
        };
        try {
            this.mAppVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            this.mIsStandalone = context.getPackageManager().hasSystemFeature(BuildConstants.PACKAGE_NAME_OVR_STANDALONE);
        } catch (Exception e) {
            Log.d(TAG, "Could not get package manager", e);
        }
    }

    public static void init(Context context) {
        mStaticContext = context;
        if (sLocalMediaManager == null) {
            sLocalMediaManager = new LocalMediaManager(context);
            CachedMetadataManager.init(context);
        }
    }

    public static void init(Context context, int mediaTypes) {
        init(context, mediaTypes, null);
    }

    public static void init(Context context, int mediaTypes, LocalMediaConfig config) {
        init(context);
        sLocalMediaManager.setConfig(config);
        sLocalMediaManager.initMediaProviders(mediaTypes, false);
    }

    public static boolean isStandaloneDevice() {
        if (sLocalMediaManager == null) {
            return false;
        }
        return sLocalMediaManager.mIsStandalone;
    }

    public static String getServerInfo() {
        if (sLocalMediaManager == null) {
            return null;
        }
        return String.format("Android %d/MediaServer %s/Build %s", Integer.valueOf(Build.VERSION.SDK_INT), "1.0", sLocalMediaManager.mAppVersion);
    }

    public static void start() {
        if (sLocalMediaManager != null) {
            sLocalMediaManager.startObservingMedia();
        }
    }

    public static void start(long appPtr) {
        setAppPointer(appPtr);
        start();
    }

    public static void setAppPointer(long appPtr) {
        sAppPtr = appPtr;
    }

    public static void stop() {
        if (sLocalMediaManager != null) {
            sLocalMediaManager.stopObservingMedia();
        }
    }

    private void setConfig(LocalMediaConfig config) {
        this.mConfig = config;
    }

    private void startObservingMedia() {
        ContentResolver resolver = this.mContext.getContentResolver();
        resolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.mImageObserver);
        resolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, this.mVideoObserver);
    }

    private void stopObservingMedia() {
        ContentResolver resolver = this.mContext.getContentResolver();
        resolver.unregisterContentObserver(this.mImageObserver);
        resolver.unregisterContentObserver(this.mVideoObserver);
    }

    private synchronized void initMediaProviders(int mediaTypes, boolean force) {
        boolean includesAll = MediaType.FOLDER.getValue() == mediaTypes;
        if ((MediaType.VIDEO.match(mediaTypes) || includesAll) && (this.mVideoMediaProvider == null || this.mVideoMediaProvider.hasChanged() || force)) {
            this.mVideoMediaProvider = new VideoMediaProvider(this.mContext, this.mConfig);
        }
        if ((MediaType.IMAGE.match(mediaTypes) || includesAll) && (this.mImageMediaProvider == null || this.mImageMediaProvider.hasChanged() || force)) {
            this.mImageMediaProvider = new ImageMediaProvider(this.mContext, this.mConfig);
        }
        if (MediaType.UNCATEGORIZED.match(mediaTypes) && (this.mUncategorizedMediaProvider == null || this.mUncategorizedMediaProvider.hasChanged() || force)) {
            this.mUncategorizedMediaProvider = new UncategorizedMediaProvider(this.mContext);
        }
    }

    public static int refreshAsync(String mediaTypes, final long appPtr) {
        final int requestId = getNextRequestId();
        refreshAsync(mediaTypes, new Callback<String>() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass4 */

            public void onComplete(String result) {
                LocalMediaManager.onRefreshComplete(requestId, appPtr);
            }
        });
        return requestId;
    }

    public static int queryAsync(String jsonQuery, final long appPtr) {
        final int queryId = getNextRequestId();
        queryAsync(jsonQuery, new Callback<String>() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass5 */

            public void onComplete(String result) {
                LocalMediaManager.onQueryComplete(queryId, result, appPtr);
            }
        });
        return queryId;
    }

    public static int moveAsync(final String jsonMediaItem, final String newFilePath, final long appPtr) {
        final int queryId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass6 */

            public void run() {
                boolean success = false;
                MediaItem.builder();
                MediaItem item = MediaItem.Builder.fromJson(jsonMediaItem).build();
                String filePath = item.getFilePath();
                File file = LocalMediaManager.getFileForOperation(item);
                if (file == null) {
                    Log.e(LocalMediaManager.TAG, "Failed to get a valid file for move operation");
                } else if (file.renameTo(new File(newFilePath))) {
                    success = true;
                    if (LocalMediaManager.sLocalMediaManager != null && item.moveMedia(LocalMediaManager.sLocalMediaManager.mContext.getContentResolver(), newFilePath) == 0) {
                        Log.e(LocalMediaManager.TAG, "File failed to update old media store entry at path '" + filePath + "' to path '" + newFilePath + "'");
                    }
                } else {
                    Log.e(LocalMediaManager.TAG, "File failed to move from path '" + filePath + "' to path '" + newFilePath + "'");
                }
                LocalMediaManager.onMoveComplete(queryId, success, appPtr);
            }
        }).start();
        return queryId;
    }

    public static int deleteAsync(final String jsonMediaItem, final long appPtr) {
        final int queryId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass7 */

            public void run() {
                boolean success = false;
                MediaItem.builder();
                MediaItem item = MediaItem.Builder.fromJson(jsonMediaItem).build();
                String filePath = item.getFilePath();
                File file = LocalMediaManager.getFileForOperation(item);
                if (file == null) {
                    Log.e(LocalMediaManager.TAG, "Failed to get a valid file for delete operation");
                } else if (file.delete()) {
                    success = true;
                    if (LocalMediaManager.sLocalMediaManager != null && item.deleteMedia(LocalMediaManager.sLocalMediaManager.mContext.getContentResolver()) == 0) {
                        Log.e(LocalMediaManager.TAG, "File failed to delete media store entry for path:" + filePath);
                    }
                } else {
                    Log.e(LocalMediaManager.TAG, "File failed to delete for path:" + filePath);
                }
                LocalMediaManager.onDeleteComplete(queryId, success, appPtr);
            }
        }).start();
        return queryId;
    }

    public static int getThumbnailAsync(final String source, final int timeMs, final long appPtr) {
        final int queryId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass8 */

            public void run() {
                String result;
                try {
                    JSONObject obj = new JSONObject();
                    obj.put("result", MediaUtils.getThumbnail(source, timeMs));
                    result = obj.toString();
                    Log.d(LocalMediaManager.TAG, "Got thumbnail at time: " + Integer.toString(timeMs));
                } catch (Exception e) {
                    result = new MediaResponse(Integer.toString(queryId)).setError(e.getMessage()).toString();
                    Log.e(LocalMediaManager.TAG, "Error getting video thumbnail.", e);
                }
                LocalMediaManager.onGetThumbnailComplete(queryId, result, appPtr);
            }
        }).start();
        return queryId;
    }

    public static int cropAsync(final String source, final int startX, final int startY, final int width, final int height, final int angle, final long appPtr) {
        final int queryId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass9 */

            public void run() {
                String result;
                try {
                    MediaUtils.MediaEditResult cropResult = MediaUtils.cropImage(source, startX, startY, width, height, angle, LocalMediaManager.mStaticContext);
                    Log.d(LocalMediaManager.TAG, "Generated new cropped image: " + cropResult.getDstFilePath());
                    result = new JSONObject().put("error", cropResult.getError()).put("dstFilePath", cropResult.getDstFilePath()).toString();
                } catch (Exception e) {
                    result = new MediaResponse(Integer.toString(queryId)).setError(e.getMessage()).toString();
                    Log.e(LocalMediaManager.TAG, "Error processing crop source file.", e);
                }
                LocalMediaManager.onCropComplete(queryId, result.toString(), appPtr);
            }
        }).start();
        return queryId;
    }

    public static int trimAsync(final String source, final int startMs, final int endMs, final long appPtr) {
        final int queryId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass10 */

            public void run() {
                String result;
                try {
                    MediaUtils.MediaEditResult trimResult = MediaUtils.trimVideo(source, startMs, endMs, LocalMediaManager.mStaticContext);
                    Log.d(LocalMediaManager.TAG, "Generated new trimmed file: " + trimResult.getDstFilePath());
                    result = new JSONObject().put("error", trimResult.getError()).put("dstFilePath", trimResult.getDstFilePath()).toString();
                } catch (Exception e) {
                    result = new MediaResponse(Integer.toString(queryId)).setError(e.getMessage()).toString();
                    Log.e(LocalMediaManager.TAG, "Error processing trim source file.", e);
                }
                LocalMediaManager.onTrimComplete(queryId, result.toString(), appPtr);
            }
        }).start();
        return queryId;
    }

    public static int getKeyframeAsync(final String source, final long appPtr) {
        final int queryId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass11 */

            public void run() {
                String result;
                try {
                    Log.d(LocalMediaManager.TAG, "Getting keyframes for file " + source);
                    JSONObject obj = new JSONObject();
                    obj.put("result", MediaUtils.getKeyFrameTimestamps(source));
                    result = obj.toString();
                } catch (Exception e) {
                    result = new MediaResponse(Integer.toString(queryId)).setError(e.getMessage()).toString();
                    Log.e(LocalMediaManager.TAG, "Error getting video keyframes.", e);
                }
                LocalMediaManager.onGetKeyframeComplete(queryId, result, appPtr);
            }
        }).start();
        return queryId;
    }

    public static File getFileForOperation(MediaItem item) {
        if (item == null) {
            Log.d(TAG, "Trying to perform operation on invalid item");
            return null;
        } else if (!item.isLocal()) {
            Log.d(TAG, "Can only perform operations on local items, source=" + item.getSource());
            return null;
        } else {
            String filePath = item.getFilePath();
            if (filePath == null || filePath.isEmpty()) {
                Log.d(TAG, "Trying to perform operation on an item with a null file path");
                return null;
            }
            File file = new File(filePath);
            if (file.exists()) {
                return file;
            }
            Log.d(TAG, "File does not exist for path:" + filePath);
            return null;
        }
    }

    public static void refreshAsync(final String mediaTypes, final Callback<String> callback) {
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass12 */

            public void run() {
                if (LocalMediaManager.sLocalMediaManager != null) {
                    try {
                        LocalMediaManager.refresh(MediaQueryUtils.parseMediaTypes(mediaTypes));
                        callback.onComplete(null);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();
    }

    public static void queryAsync(final String jsonQuery, final Callback<String> callback) {
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass13 */

            public void run() {
                callback.onComplete(LocalMediaManager.query(jsonQuery, null));
            }
        }).start();
    }

    public static void refresh(int mediaTypes) {
        if (!sLocalMediaManager.checkReadWritePermission()) {
            Log.w(TAG, "Missing storage permissions, skipping refresh.");
        } else {
            sLocalMediaManager.initMediaProviders(mediaTypes, true);
        }
    }

    public static String query(String jsonQuery, String externalHost) {
        String queryId = "";
        try {
            MediaQuery.builder();
            MediaQuery mediaQuery = MediaQuery.Builder.fromJson(jsonQuery).setExternalHost(externalHost).build();
            queryId = mediaQuery.QueryId;
            return query(mediaQuery).toString();
        } catch (SecurityException ex) {
            Log.d(TAG, "Permission error when executing query :" + ex);
            return new MediaResponse(queryId).setError(ex.getMessage(), MediaErrorCode.READ_PERMISSION).toString();
        } catch (Throwable throwable) {
            Log.d(TAG, "Error occured while executing query :" + throwable);
            return new MediaResponse(queryId).setError(throwable.getMessage()).toString();
        }
    }

    public static MediaResponse query(MediaQuery query) throws Exception {
        Log.d(TAG, "Query : " + query.toString());
        long startTime = System.currentTimeMillis();
        MediaResponse response = sLocalMediaManager.queryInternal(query);
        Log.d(TAG, "Result (" + (System.currentTimeMillis() - startTime) + " msec)" + " : " + response);
        return response;
    }

    private MediaResponse queryInternal(MediaQuery query) throws Exception {
        initMediaProviders(query.Types, false);
        if (getMediaItemsByType(query.Types).size() == 0 && !checkReadWritePermission()) {
            throw new SecurityException("android.permission.READ_EXTERNAL_STORAGE permission missing");
        } else if (MediaProviderUtils.isFile(query.Path)) {
            return queryMediaByFilePath(query);
        } else {
            if (query.MediaId == null || query.MediaId.isEmpty()) {
                return queryMedia(query);
            }
            return queryMediaByMediaId(query);
        }
    }

    private MediaResponse queryMediaByFilePath(MediaQuery query) {
        MediaResponse response = new MediaResponse(query.QueryId);
        ArrayList<MediaItem> items = getMediaItemsByType(query.Types);
        if (query.Path != null) {
            Iterator<MediaItem> it = items.iterator();
            while (it.hasNext()) {
                MediaItem item = it.next();
                if (query.Path.equalsIgnoreCase(item.getFilePath())) {
                    item.prepare(this.mContext, this.mConfig, query);
                    response.addMediaItem(item);
                    return response;
                }
            }
        }
        throw new IllegalArgumentException("Incorrect local file specified, path=" + query.Path);
    }

    private MediaResponse queryMediaByMediaId(MediaQuery query) {
        MediaResponse response = new MediaResponse(query.QueryId);
        ArrayList<MediaItem> items = getMediaItemsByType(query.Types);
        if (query.MediaId != null && !query.MediaId.isEmpty()) {
            long mediaId = Long.parseLong(query.MediaId, 10);
            Iterator<MediaItem> it = items.iterator();
            while (it.hasNext()) {
                MediaItem item = it.next();
                if (mediaId == item.getId()) {
                    item.prepare(this.mContext, this.mConfig, query, true);
                    response.addMediaItem(item);
                    return response;
                }
            }
        }
        throw new IllegalArgumentException("Incorrect local file specified, mediaId=" + query.MediaId);
    }

    public MediaResponse queryMedia(MediaQuery query) {
        if (query.DepthLevel < 0) {
            return null;
        }
        MediaResponse response = new MediaResponse(query.QueryId);
        if (query.isSmartQuery()) {
            query = MediaSmartQueryUtils.rewrite(query);
        }
        List<MediaItemFilter> filters = getQueryFilters(query.Filters);
        if (!TextUtils.isEmpty(query.Path)) {
            if (TextUtils.equals(query.Path, "/virtual/sideloaded")) {
                query.Path = "/";
                filters.add(new ExcludeFolderFilter("/storage/emulated/0/Oculus/"));
            } else {
                filters.add(new FolderFilter(query.Path));
            }
        }
        boolean includePrivate = filtersIncludePrivate(filters);
        List<MediaItem> items = getMediaItems(query.Types, filters);
        List<FolderItem> folders = getFolderItems(query, includePrivate);
        FolderItem.sort(folders, query.Sort, query.IsSortAscending);
        MediaItem.sort(items, query.Sort, query.IsSortAscending);
        int index = query.Cursor != null ? Integer.parseInt(query.Cursor, 10) : 0;
        int totalCount = items.size() + folders.size();
        int lastIndex = Math.max(Math.min(query.Size + index, totalCount) - 1, 0);
        if (index > lastIndex) {
            throw new IndexOutOfBoundsException("Incorrect pagination data, index=" + query.Cursor + " size=" + query.Size);
        }
        int i = index;
        while (i <= lastIndex && i < totalCount) {
            if (i < folders.size()) {
                FolderItem folder = folders.get(i);
                folder.prepare(this, query);
                response.addFolderItem(folder);
            } else {
                MediaItem item = items.get(i - folders.size());
                item.prepare(this.mContext, this.mConfig, query);
                response.addMediaItem(item);
            }
            i++;
        }
        boolean hasNextPage = lastIndex < totalCount + -1;
        response.setPaginationData(new MediaPagination.Builder().setTotalCount(totalCount).setStartCursor(index > 0 ? String.valueOf(index) : null).setLastCursor(hasNextPage ? String.valueOf(lastIndex + 1) : null).build());
        return response;
    }

    private List<FolderItem> getFolderItems(MediaQuery query, boolean includePrivate) {
        String name;
        List<MediaSmartQueryType> types;
        List<FolderItem> folders = new ArrayList<>();
        if (!query.isSmartQuery() || (types = MediaSmartQueryUtils.getFoldersForPath(query.Path)) == null) {
            if (MediaType.FOLDER.match(query.Types)) {
                if (query.Path == null) {
                    for (String path : FolderMediaCountRegistry.getSingleton().getFoldersWithMedia(query.Types)) {
                        File file = new File(path);
                        if (file.isDirectory() && !file.getName().startsWith(".")) {
                            if (file.getAbsolutePath().equalsIgnoreCase(STORAGE_FOLDER)) {
                                name = ROOT_ALBUM_NAME;
                            } else {
                                name = file.getName();
                            }
                            folders.add(createFolderItem(name, file.getAbsolutePath(), file.lastModified()));
                        }
                    }
                } else if (query.Path.equalsIgnoreCase("") || query.Path.equalsIgnoreCase("/")) {
                    folders.add(createFolderItem(STORAGE_FOLDER_NAME, STORAGE_FOLDER));
                    if (SDCARD_FOLDER != null) {
                        folders.add(createFolderItem(SDCARD_FOLDER_NAME, SDCARD_FOLDER));
                    }
                    folders.add(createFolderItem(DOWNLOAD_FOLDER_NAME, DOWNLOAD_FOLDER));
                } else {
                    File[] fileList = new File(query.Path).listFiles();
                    if (fileList != null) {
                        int length = fileList.length;
                        for (int i = 0; i < length; i++) {
                            File file2 = fileList[i];
                            if (file2.isDirectory() && !file2.getName().startsWith(".") && (query.Filters == null || query.Filters.indexOf(EMPTY_FOLDER_FILTER) <= 0 || FolderMediaCountRegistry.getSingleton().get(query.Path, query.Types).getAggregatedCount() != 0)) {
                                folders.add(createFolderItem(file2.getName(), file2.getAbsolutePath(), file2.lastModified()));
                            }
                        }
                    }
                }
            }
            if (includePrivate) {
                return folders;
            }
            List<FolderItem> filteredFolders = new ArrayList<>();
            for (FolderItem item : folders) {
                if (!MediaProviderUtils.isPrivateFolder(item.getPath())) {
                    filteredFolders.add(item);
                }
            }
            return filteredFolders;
        }
        for (MediaSmartQueryType type : types) {
            folders.add(createFolderItem(type.toString(), "*" + type.toString()));
        }
        return folders;
    }

    private FolderItem createFolderItem(String name, String path) {
        return createFolderItem(name, path, 0);
    }

    private FolderItem createFolderItem(String name, String path, long dateAdded) {
        return FolderItem.builder().setName(name).setPath(path).setDateAdded(dateAdded).build();
    }

    private ArrayList<MediaItem> getMediaItemsByType(int types) {
        ArrayList<MediaItem> items = new ArrayList<>();
        if (MediaType.VIDEO.match(types)) {
            items.addAll(this.mVideoMediaProvider.getItems());
        }
        if (MediaType.IMAGE.match(types)) {
            items.addAll(this.mImageMediaProvider.getItems());
        }
        if (MediaType.UNCATEGORIZED.match(types)) {
            items.addAll(this.mUncategorizedMediaProvider.getItems());
        }
        return items;
    }

    private List<MediaItem> getMediaItems(int types, List<MediaItemFilter> filters) {
        ArrayList<MediaItem> items = getMediaItemsByType(types);
        if (filters == null || filters.size() == 0) {
            return items;
        }
        ArrayList<MediaItem> filteredItems = new ArrayList<>();
        Iterator<MediaItem> it = items.iterator();
        while (it.hasNext()) {
            MediaItem item = it.next();
            boolean filtered = false;
            Iterator<MediaItemFilter> it2 = filters.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (!it2.next().match(item)) {
                        filtered = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!filtered) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }

    private List<MediaItemFilter> getQueryFilters(String filters) {
        ArrayList<MediaItemFilter> filtersArray = new ArrayList<>();
        boolean visibilityFilterSet = false;
        if (filters != null) {
            StringTokenizer tokenizer = new StringTokenizer(filters, ",");
            while (tokenizer.hasMoreTokens()) {
                MediaItemFilter filter = MediaItemFilterFactory.create(tokenizer.nextToken());
                if (filter != null) {
                    filtersArray.add(filter);
                    visibilityFilterSet |= filter instanceof VisibilityFilter;
                }
            }
        }
        if (!visibilityFilterSet) {
            filtersArray.add(MediaItemFilterFactory.create(DEFAULT_VISIBILITY_FILTER));
        }
        return filtersArray;
    }

    private boolean filtersIncludePrivate(List<MediaItemFilter> filters) {
        for (MediaItemFilter filter : filters) {
            if (filter instanceof VisibilityFilter) {
                return ((VisibilityFilter) filter).includePrivate();
            }
        }
        return false;
    }

    public static int isReadWritePermission(final long appPtr) {
        final int queryId = getNextRequestId();
        new Thread(new Runnable() {
            /* class com.oculus.localmedia.LocalMediaManager.AnonymousClass14 */

            public void run() {
                LocalMediaManager.onCheckPermissionComplete(queryId, LocalMediaManager.sLocalMediaManager.checkReadWritePermission(), appPtr);
            }
        }).start();
        return queryId;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean checkReadWritePermission() {
        return PermissionChecker.checkSelfPermission(this.mContext, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private static synchronized int getNextRequestId() {
        int i;
        synchronized (LocalMediaManager.class) {
            i = sRequestId + 1;
            sRequestId = i;
        }
        return i;
    }
}
