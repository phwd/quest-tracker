package com.oculus.tablet.utils;

import X.AnonymousClass006;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.tablet.cache.CacheItem;
import com.oculus.tablet.cache.DiskCache;
import com.oculus.tablet.utils.ImageLoader;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageLoader {
    public static final String CACHE_CONTROL_HEADER_NAME = "Cache-Control";
    public static final String CACHE_CONTROL_REGEX_PATTERN = "(max-age=)(\\d+)";
    public static final String CACHE_SUBDIRECTORY = "image_cache";
    public static final ImageLoader INSTANCE = new ImageLoader();
    public static final String TAG = LoggingUtil.tag(ImageLoader.class);
    public static DiskCache mOnDiskImageCache;
    public ImageDownloader mDownloader;
    public ConcurrentHashMap<String, ThreadExecutor.ConcurrentListenableScheduledFuture<Bitmap>> mFutureMap;
    public Object mLock;

    public interface ImageCallbacks {
        void onFailure(Throwable th);

        void onSuccess(Bitmap bitmap);
    }

    public interface ImageDownloader {
        CacheItem download(String str) throws IOException;
    }

    public interface MultiImageCallbacks {
        void onComplete(Map<String, Bitmap> map, Throwable th);

        void onFailImage(Throwable th, String str);

        void onLoadImage(String str, Bitmap bitmap);
    }

    public static /* synthetic */ Void lambda$loadImages$5(CountDownLatch countDownLatch, MultiImageCallbacks multiImageCallbacks, ConcurrentHashMap concurrentHashMap) throws Exception {
        InterruptedException e;
        try {
            countDownLatch.await();
            e = null;
        } catch (InterruptedException e2) {
            e = e2;
        }
        UiThreadExecutor.getInstance().execute(new Runnable(concurrentHashMap, e) {
            /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$mubhdCM_yiaKMblvFK1xuUtQRP42 */
            public final /* synthetic */ ConcurrentHashMap f$1;
            public final /* synthetic */ Throwable f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                ImageLoader.MultiImageCallbacks.this.onComplete(Collections.unmodifiableMap(this.f$1), this.f$2);
            }
        });
        return null;
    }

    public /* synthetic */ Bitmap lambda$loadImage$3$ImageLoader(String str, Semaphore semaphore, String str2, CacheItem cacheItem) throws Exception {
        try {
            CacheItem download = this.mDownloader.download(str);
            byte[] bArr = download.mBytes;
            semaphore.acquireUninterruptibly();
            synchronized (this.mLock) {
                this.mFutureMap.remove(str);
                mOnDiskImageCache.put(str2, download);
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } catch (MalformedURLException unused) {
            return null;
        } catch (IOException unused2) {
            synchronized (this.mLock) {
                this.mFutureMap.remove(str);
                if (cacheItem == null) {
                    return null;
                }
                byte[] bArr2 = cacheItem.mBytes;
                return BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length);
            }
        }
    }

    public void loadImage(String str, ImageCallbacks imageCallbacks) {
        synchronized (this.mLock) {
            try {
                String imagePath = getImagePath(str);
                CacheItem cacheItem = mOnDiskImageCache.get(imagePath);
                if (cacheItem == null || !(!cacheItem.isExpired())) {
                    ThreadExecutor.ConcurrentListenableScheduledFuture<Bitmap> concurrentListenableScheduledFuture = this.mFutureMap.get(str);
                    if (concurrentListenableScheduledFuture != null) {
                        attachListener(concurrentListenableScheduledFuture, imageCallbacks);
                    } else {
                        Semaphore semaphore = new Semaphore(0);
                        ThreadExecutor.ConcurrentListenableScheduledFuture<Bitmap> execute = ThreadExecutor.INSTANCE.execute(new Callable(str, semaphore, imagePath, cacheItem) {
                            /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$ZEBoI8pzSP50Gber5xUt7PPaSnE2 */
                            public final /* synthetic */ String f$1;
                            public final /* synthetic */ Semaphore f$2;
                            public final /* synthetic */ String f$3;
                            public final /* synthetic */ CacheItem f$4;

                            {
                                this.f$1 = r2;
                                this.f$2 = r3;
                                this.f$3 = r4;
                                this.f$4 = r5;
                            }

                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                return ImageLoader.this.lambda$loadImage$3$ImageLoader(this.f$1, this.f$2, this.f$3, this.f$4);
                            }
                        });
                        this.mFutureMap.put(str, execute);
                        semaphore.release();
                        attachListener(execute, imageCallbacks);
                    }
                } else {
                    byte[] bArr = cacheItem.mBytes;
                    UiThreadExecutor.getInstance().execute(new Runnable(BitmapFactory.decodeByteArray(bArr, 0, bArr.length)) {
                        /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$IVWKlZG7C5ALTeoWX3nsFboT5t42 */
                        public final /* synthetic */ Bitmap f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            ImageLoader.ImageCallbacks.this.onSuccess(this.f$1);
                        }
                    });
                }
            } catch (FileNotFoundException e) {
                Log.e(TAG, AnonymousClass006.A07("Failed to get image path for url: ", str));
                UiThreadExecutor.getInstance().execute(new Runnable(e) {
                    /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$_o9bOhfhhWhDdwhxmQDO8aIzTs2 */
                    public final /* synthetic */ FileNotFoundException f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ImageLoader.ImageCallbacks.this.onFailure(this.f$1);
                    }
                });
            }
        }
    }

    private void attachListener(ThreadExecutor.ConcurrentListenableScheduledFuture<Bitmap> concurrentListenableScheduledFuture, final ImageCallbacks imageCallbacks) {
        concurrentListenableScheduledFuture.listen(new ThreadExecutor.Listener<Bitmap>() {
            /* class com.oculus.tablet.utils.ImageLoader.AnonymousClass2 */

            public static /* synthetic */ void lambda$onSuccess$1(Bitmap bitmap, ImageCallbacks imageCallbacks) {
                if (bitmap == null) {
                    imageCallbacks.onFailure(new RuntimeException("Failed to load bitmap"));
                } else {
                    imageCallbacks.onSuccess(bitmap);
                }
            }

            @Override // com.oculus.vrshell.util.ThreadExecutor.Listener
            public void onFailure(Throwable th) {
                UiThreadExecutor.getInstance().execute(new Runnable(th) {
                    /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$2$vPVLH0Ywk9f7OgKQxjg0qCuBb_c2 */
                    public final /* synthetic */ Throwable f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ImageLoader.ImageCallbacks.this.onFailure(this.f$1);
                    }
                });
            }

            public void onSuccess(Bitmap bitmap) {
                UiThreadExecutor.getInstance().execute(new Runnable(bitmap, imageCallbacks) {
                    /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$2$z7r_fpIPs0MLK2548plc7uk_MA2 */
                    public final /* synthetic */ Bitmap f$0;
                    public final /* synthetic */ ImageLoader.ImageCallbacks f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        ImageLoader.AnonymousClass2.lambda$onSuccess$1(this.f$0, this.f$1);
                    }
                });
            }
        });
    }

    public static ImageLoader getInstance(Context context) {
        if (mOnDiskImageCache == null) {
            mOnDiskImageCache = new DiskCache(context, CACHE_SUBDIRECTORY);
        }
        return INSTANCE;
    }

    public static /* synthetic */ CacheItem lambda$new$0(Pattern pattern, String str) throws IOException {
        String group;
        URLConnection openConnection = new URL(str).openConnection();
        InputStream inputStream = openConnection.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        CacheItem cacheItem = new CacheItem(byteArrayOutputStream.toByteArray());
        Matcher matcher = pattern.matcher(openConnection.getHeaderField(CACHE_CONTROL_HEADER_NAME));
        if (matcher.find() && (group = matcher.group(2)) != null) {
            cacheItem.mExpirationTimeSeconds = (System.currentTimeMillis() / 1000) + Long.parseLong(group);
        }
        return cacheItem;
    }

    public void loadImages(List<String> list, final MultiImageCallbacks multiImageCallbacks) {
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        for (final String str : list) {
            loadImage(str, new ImageCallbacks() {
                /* class com.oculus.tablet.utils.ImageLoader.AnonymousClass1 */

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onFailure(Throwable th) {
                    multiImageCallbacks.onFailImage(th, str);
                    countDownLatch.countDown();
                }

                @Override // com.oculus.tablet.utils.ImageLoader.ImageCallbacks
                public void onSuccess(Bitmap bitmap) {
                    concurrentHashMap.put(str, bitmap);
                    multiImageCallbacks.onLoadImage(str, bitmap);
                    countDownLatch.countDown();
                }
            });
        }
        ThreadExecutor.INSTANCE.execute(new Callable(countDownLatch, multiImageCallbacks, concurrentHashMap) {
            /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$n2MgbEVgJtC86e8s0TZPPE6cU2 */
            public final /* synthetic */ CountDownLatch f$0;
            public final /* synthetic */ ImageLoader.MultiImageCallbacks f$1;
            public final /* synthetic */ ConcurrentHashMap f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ImageLoader.lambda$loadImages$5(this.f$0, this.f$1, this.f$2);
            }
        });
    }

    private String getImagePath(String str) throws FileNotFoundException {
        return String.valueOf(getUrlWithoutQueryParameters(str).hashCode());
    }

    private String getUrlWithoutQueryParameters(String str) {
        Uri parse = Uri.parse(str);
        return new Uri.Builder().scheme(parse.getScheme()).authority(parse.getAuthority()).appendPath(parse.getEncodedPath()).build().toString();
    }

    public ImageLoader() {
        this.mFutureMap = new ConcurrentHashMap<>();
        this.mLock = new Object();
        this.mDownloader = new ImageDownloader(Pattern.compile(CACHE_CONTROL_REGEX_PATTERN)) {
            /* class com.oculus.tablet.utils.$$Lambda$ImageLoader$qpP5bk5Tfl4smCcpFaVhHjSJbyg2 */
            public final /* synthetic */ Pattern f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.oculus.tablet.utils.ImageLoader.ImageDownloader
            public final CacheItem download(String str) {
                return ImageLoader.lambda$new$0(this.f$0, str);
            }
        };
    }

    @VisibleForTesting
    public ImageLoader(ImageDownloader imageDownloader, DiskCache diskCache) {
        this.mFutureMap = new ConcurrentHashMap<>();
        this.mLock = new Object();
        this.mDownloader = imageDownloader;
        mOnDiskImageCache = diskCache;
    }
}
