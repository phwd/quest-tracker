package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.facebook.internal.WorkQueue;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader {
    public static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    public static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    public static WorkQueue cacheReadQueue = new WorkQueue(2);
    public static WorkQueue downloadQueue = new WorkQueue(8);
    public static Handler handler;
    public static final Map<RequestKey, DownloaderContext> pendingRequests = new HashMap();

    public static class CacheReadWorkItem implements Runnable {
        public boolean allowCachedRedirects;
        public Context context;
        public RequestKey key;

        public void run() {
            ImageDownloader.readFromCache(this.key, this.context, this.allowCachedRedirects);
        }

        public CacheReadWorkItem(Context context2, RequestKey requestKey, boolean z) {
            this.context = context2;
            this.key = requestKey;
            this.allowCachedRedirects = z;
        }
    }

    public static class DownloadImageWorkItem implements Runnable {
        public Context context;
        public RequestKey key;

        public void run() {
            ImageDownloader.download(this.key, this.context);
        }

        public DownloadImageWorkItem(Context context2, RequestKey requestKey) {
            this.context = context2;
            this.key = requestKey;
        }
    }

    public static class RequestKey {
        public static final int HASH_MULTIPLIER = 37;
        public static final int HASH_SEED = 29;
        public Object tag;
        public Uri uri;

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof RequestKey)) {
                return false;
            }
            RequestKey requestKey = (RequestKey) obj;
            return requestKey.uri == this.uri && requestKey.tag == this.tag;
        }

        public int hashCode() {
            return ((1073 + this.uri.hashCode()) * 37) + this.tag.hashCode();
        }

        public RequestKey(Uri uri2, Object obj) {
            this.uri = uri2;
            this.tag = obj;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009b, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009d, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009e, code lost:
        r3 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0014] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void download(com.facebook.internal.ImageDownloader.RequestKey r10, android.content.Context r11) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.download(com.facebook.internal.ImageDownloader$RequestKey, android.content.Context):void");
    }

    public static void readFromCache(RequestKey requestKey, Context context, boolean z) {
        InputStream cachedImageStream;
        Uri redirectedUri;
        boolean z2 = false;
        if (!z || (redirectedUri = UrlRedirectCache.getRedirectedUri(requestKey.uri)) == null || (cachedImageStream = ImageResponseCache.getCachedImageStream(redirectedUri, context)) == null) {
            cachedImageStream = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
            if (cachedImageStream == null) {
                DownloaderContext removePendingRequest = removePendingRequest(requestKey);
                if (removePendingRequest != null && !removePendingRequest.isCancelled) {
                    enqueueDownload(removePendingRequest.request, requestKey);
                    return;
                }
                return;
            }
        } else {
            z2 = true;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(cachedImageStream);
        Utility.closeQuietly(cachedImageStream);
        issueResponse(requestKey, null, decodeStream, z2);
    }

    public static boolean cancelRequest(ImageRequest imageRequest) {
        boolean z;
        RequestKey requestKey = new RequestKey(imageRequest.imageUri, imageRequest.callerTag);
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            z = true;
            if (downloaderContext == null) {
                z = false;
            } else if (downloaderContext.workItem.cancel()) {
                map.remove(requestKey);
            } else {
                downloaderContext.isCancelled = true;
            }
        }
        return z;
    }

    public static void downloadAsync(ImageRequest imageRequest) {
        if (imageRequest != null) {
            RequestKey requestKey = new RequestKey(imageRequest.imageUri, imageRequest.callerTag);
            Map<RequestKey, DownloaderContext> map = pendingRequests;
            synchronized (map) {
                DownloaderContext downloaderContext = map.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = imageRequest;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                } else {
                    enqueueCacheRead(imageRequest, requestKey, imageRequest.allowCachedRedirects);
                }
            }
        }
    }

    public static void enqueueCacheRead(ImageRequest imageRequest, RequestKey requestKey, boolean z) {
        enqueueRequest(imageRequest, requestKey, cacheReadQueue, new CacheReadWorkItem(imageRequest.context, requestKey, z));
    }

    public static void enqueueDownload(ImageRequest imageRequest, RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, downloadQueue, new DownloadImageWorkItem(imageRequest.context, requestKey));
    }

    public static void enqueueRequest(ImageRequest imageRequest, RequestKey requestKey, WorkQueue workQueue, Runnable runnable) {
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = imageRequest;
            map.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable, true);
        }
    }

    public static synchronized Handler getHandler() {
        Handler handler2;
        synchronized (ImageDownloader.class) {
            handler2 = handler;
            if (handler2 == null) {
                handler2 = new Handler(Looper.getMainLooper());
                handler = handler2;
            }
        }
        return handler2;
    }

    public static void prioritizeRequest(ImageRequest imageRequest) {
        RequestKey requestKey = new RequestKey(imageRequest.imageUri, imageRequest.callerTag);
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            DownloaderContext downloaderContext = map.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }

    public static DownloaderContext removePendingRequest(RequestKey requestKey) {
        DownloaderContext remove;
        Map<RequestKey, DownloaderContext> map = pendingRequests;
        synchronized (map) {
            remove = map.remove(requestKey);
        }
        return remove;
    }

    public static void clearCache(Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r2 = r1.request;
        r6 = r2.callback;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void issueResponse(com.facebook.internal.ImageDownloader.RequestKey r6, final java.lang.Exception r7, final android.graphics.Bitmap r8, final boolean r9) {
        /*
            com.facebook.internal.ImageDownloader$DownloaderContext r1 = removePendingRequest(r6)
            if (r1 == 0) goto L_0x001f
            boolean r0 = r1.isCancelled
            if (r0 != 0) goto L_0x001f
            com.facebook.internal.ImageRequest r2 = r1.request
            com.facebook.internal.ImageRequest$Callback r6 = r2.callback
            if (r6 == 0) goto L_0x001f
            android.os.Handler r0 = getHandler()
            r5 = r8
            r4 = r9
            r3 = r7
            com.facebook.internal.ImageDownloader$1 r1 = new com.facebook.internal.ImageDownloader$1
            r1.<init>(r2, r3, r4, r5, r6)
            r0.post(r1)
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ImageDownloader.issueResponse(com.facebook.internal.ImageDownloader$RequestKey, java.lang.Exception, android.graphics.Bitmap, boolean):void");
    }

    public static class DownloaderContext {
        public boolean isCancelled;
        public ImageRequest request;
        public WorkQueue.WorkItem workItem;

        public DownloaderContext() {
        }

        public /* synthetic */ DownloaderContext(AnonymousClass1 r1) {
        }
    }
}
