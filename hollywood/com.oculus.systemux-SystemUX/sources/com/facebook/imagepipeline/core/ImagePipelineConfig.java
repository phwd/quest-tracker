package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.bitmaps.HoneycombBitmapCreator;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BitmapMemoryCacheTrimStrategy;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.core.ImagePipelineExperiments;
import com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker;
import com.facebook.imagepipeline.debug.NoOpCloseableReferenceLeakTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class ImagePipelineConfig {
    private static DefaultImageRequestConfig sDefaultImageRequestConfig = new DefaultImageRequestConfig();
    @Nullable
    private final MemoryCache<CacheKey, CloseableImage> mBitmapCache;
    private final Bitmap.Config mBitmapConfig;
    private final CountingMemoryCache.EntryStateObserver<CacheKey> mBitmapMemoryCacheEntryStateObserver;
    private final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private final MemoryCache.CacheTrimStrategy mBitmapMemoryCacheTrimStrategy;
    private final CacheKeyFactory mCacheKeyFactory;
    @Nullable
    private final CallerContextVerifier mCallerContextVerifier;
    private final CloseableReferenceLeakTracker mCloseableReferenceLeakTracker;
    private final Context mContext;
    private final boolean mDiskCacheEnabled;
    private final boolean mDownsampleEnabled;
    @Nullable
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private final ExecutorSupplier mExecutorSupplier;
    private final FileCacheFactory mFileCacheFactory;
    private final int mHttpNetworkTimeout;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    @Nullable
    private final ImageDecoder mImageDecoder;
    @Nullable
    private final ImageDecoderConfig mImageDecoderConfig;
    private final ImagePipelineExperiments mImagePipelineExperiments;
    @Nullable
    private final ImageTranscoderFactory mImageTranscoderFactory;
    @Nullable
    private final Integer mImageTranscoderType;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final DiskCacheConfig mMainDiskCacheConfig;
    private final int mMemoryChunkType;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final NetworkFetcher mNetworkFetcher;
    @Nullable
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PoolFactory mPoolFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final Set<RequestListener2> mRequestListener2s;
    private final Set<RequestListener> mRequestListeners;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final DiskCacheConfig mSmallImageDiskCacheConfig;

    private ImagePipelineConfig(Builder builder) {
        Supplier<MemoryCacheParams> supplier;
        MemoryCache.CacheTrimStrategy cacheTrimStrategy;
        CacheKeyFactory cacheKeyFactory;
        FileCacheFactory fileCacheFactory;
        Supplier<MemoryCacheParams> supplier2;
        ImageCacheStatsTracker imageCacheStatsTracker;
        Supplier<Boolean> supplier3;
        DiskCacheConfig diskCacheConfig;
        MemoryTrimmableRegistry memoryTrimmableRegistry;
        int i;
        NetworkFetcher networkFetcher;
        PoolFactory poolFactory;
        ProgressiveJpegConfig progressiveJpegConfig;
        Set<RequestListener> set;
        Set<RequestListener2> set2;
        DiskCacheConfig diskCacheConfig2;
        ExecutorSupplier executorSupplier;
        WebpBitmapFactory loadWebpBitmapFactoryIfExists;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipelineConfig()");
        }
        this.mImagePipelineExperiments = builder.mExperimentsBuilder.build();
        if (builder.mBitmapMemoryCacheParamsSupplier == null) {
            supplier = new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager) builder.mContext.getSystemService("activity"));
        } else {
            supplier = builder.mBitmapMemoryCacheParamsSupplier;
        }
        this.mBitmapMemoryCacheParamsSupplier = supplier;
        if (builder.mBitmapMemoryCacheTrimStrategy == null) {
            cacheTrimStrategy = new BitmapMemoryCacheTrimStrategy();
        } else {
            cacheTrimStrategy = builder.mBitmapMemoryCacheTrimStrategy;
        }
        this.mBitmapMemoryCacheTrimStrategy = cacheTrimStrategy;
        this.mBitmapMemoryCacheEntryStateObserver = builder.mBitmapMemoryCacheEntryStateObserver;
        this.mBitmapConfig = builder.mBitmapConfig == null ? Bitmap.Config.ARGB_8888 : builder.mBitmapConfig;
        if (builder.mCacheKeyFactory == null) {
            cacheKeyFactory = DefaultCacheKeyFactory.getInstance();
        } else {
            cacheKeyFactory = builder.mCacheKeyFactory;
        }
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mContext = (Context) Preconditions.checkNotNull(builder.mContext);
        if (builder.mFileCacheFactory == null) {
            fileCacheFactory = new DiskStorageCacheFactory(new DynamicDefaultDiskStorageFactory());
        } else {
            fileCacheFactory = builder.mFileCacheFactory;
        }
        this.mFileCacheFactory = fileCacheFactory;
        this.mDownsampleEnabled = builder.mDownsampleEnabled;
        if (builder.mEncodedMemoryCacheParamsSupplier == null) {
            supplier2 = new DefaultEncodedMemoryCacheParamsSupplier();
        } else {
            supplier2 = builder.mEncodedMemoryCacheParamsSupplier;
        }
        this.mEncodedMemoryCacheParamsSupplier = supplier2;
        if (builder.mImageCacheStatsTracker == null) {
            imageCacheStatsTracker = NoOpImageCacheStatsTracker.getInstance();
        } else {
            imageCacheStatsTracker = builder.mImageCacheStatsTracker;
        }
        this.mImageCacheStatsTracker = imageCacheStatsTracker;
        this.mImageDecoder = builder.mImageDecoder;
        this.mImageTranscoderFactory = getImageTranscoderFactory(builder);
        this.mImageTranscoderType = builder.mImageTranscoderType;
        if (builder.mIsPrefetchEnabledSupplier == null) {
            supplier3 = new Supplier<Boolean>() {
                /* class com.facebook.imagepipeline.core.ImagePipelineConfig.AnonymousClass1 */

                @Override // com.facebook.common.internal.Supplier
                public Boolean get() {
                    return true;
                }
            };
        } else {
            supplier3 = builder.mIsPrefetchEnabledSupplier;
        }
        this.mIsPrefetchEnabledSupplier = supplier3;
        if (builder.mMainDiskCacheConfig == null) {
            diskCacheConfig = getDefaultMainDiskCacheConfig(builder.mContext);
        } else {
            diskCacheConfig = builder.mMainDiskCacheConfig;
        }
        this.mMainDiskCacheConfig = diskCacheConfig;
        if (builder.mMemoryTrimmableRegistry == null) {
            memoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        } else {
            memoryTrimmableRegistry = builder.mMemoryTrimmableRegistry;
        }
        this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
        this.mMemoryChunkType = getMemoryChunkType(builder, this.mImagePipelineExperiments);
        if (builder.mHttpConnectionTimeout < 0) {
            i = 30000;
        } else {
            i = builder.mHttpConnectionTimeout;
        }
        this.mHttpNetworkTimeout = i;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipelineConfig->mNetworkFetcher");
        }
        if (builder.mNetworkFetcher == null) {
            networkFetcher = new HttpUrlConnectionNetworkFetcher(this.mHttpNetworkTimeout);
        } else {
            networkFetcher = builder.mNetworkFetcher;
        }
        this.mNetworkFetcher = networkFetcher;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        this.mPlatformBitmapFactory = builder.mPlatformBitmapFactory;
        if (builder.mPoolFactory == null) {
            poolFactory = new PoolFactory(PoolConfig.newBuilder().build());
        } else {
            poolFactory = builder.mPoolFactory;
        }
        this.mPoolFactory = poolFactory;
        if (builder.mProgressiveJpegConfig == null) {
            progressiveJpegConfig = new SimpleProgressiveJpegConfig();
        } else {
            progressiveJpegConfig = builder.mProgressiveJpegConfig;
        }
        this.mProgressiveJpegConfig = progressiveJpegConfig;
        if (builder.mRequestListeners == null) {
            set = new HashSet<>();
        } else {
            set = builder.mRequestListeners;
        }
        this.mRequestListeners = set;
        if (builder.mRequestListener2s == null) {
            set2 = new HashSet<>();
        } else {
            set2 = builder.mRequestListener2s;
        }
        this.mRequestListener2s = set2;
        this.mResizeAndRotateEnabledForNetwork = builder.mResizeAndRotateEnabledForNetwork;
        if (builder.mSmallImageDiskCacheConfig == null) {
            diskCacheConfig2 = this.mMainDiskCacheConfig;
        } else {
            diskCacheConfig2 = builder.mSmallImageDiskCacheConfig;
        }
        this.mSmallImageDiskCacheConfig = diskCacheConfig2;
        this.mImageDecoderConfig = builder.mImageDecoderConfig;
        int flexByteArrayPoolMaxNumThreads = this.mPoolFactory.getFlexByteArrayPoolMaxNumThreads();
        if (builder.mExecutorSupplier == null) {
            executorSupplier = new DefaultExecutorSupplier(flexByteArrayPoolMaxNumThreads);
        } else {
            executorSupplier = builder.mExecutorSupplier;
        }
        this.mExecutorSupplier = executorSupplier;
        this.mDiskCacheEnabled = builder.mDiskCacheEnabled;
        this.mCallerContextVerifier = builder.mCallerContextVerifier;
        this.mCloseableReferenceLeakTracker = builder.mCloseableReferenceLeakTracker;
        this.mBitmapCache = builder.mBitmapMemoryCache;
        this.mEncodedMemoryCache = builder.mEncodedMemoryCache;
        WebpBitmapFactory webpBitmapFactory = this.mImagePipelineExperiments.getWebpBitmapFactory();
        if (webpBitmapFactory != null) {
            setWebpBitmapFactory(webpBitmapFactory, this.mImagePipelineExperiments, new HoneycombBitmapCreator(getPoolFactory()));
        } else if (this.mImagePipelineExperiments.isWebpSupportEnabled() && WebpSupportStatus.sIsWebpSupportRequired && (loadWebpBitmapFactoryIfExists = WebpSupportStatus.loadWebpBitmapFactoryIfExists()) != null) {
            setWebpBitmapFactory(loadWebpBitmapFactoryIfExists, this.mImagePipelineExperiments, new HoneycombBitmapCreator(getPoolFactory()));
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    private static void setWebpBitmapFactory(WebpBitmapFactory webpBitmapFactory, ImagePipelineExperiments imagePipelineExperiments, BitmapCreator bitmapCreator) {
        WebpSupportStatus.sWebpBitmapFactory = webpBitmapFactory;
        WebpBitmapFactory.WebpErrorLogger webpErrorLogger = imagePipelineExperiments.getWebpErrorLogger();
        if (webpErrorLogger != null) {
            webpBitmapFactory.setWebpErrorLogger(webpErrorLogger);
        }
        if (bitmapCreator != null) {
            webpBitmapFactory.setBitmapCreator(bitmapCreator);
        }
    }

    private static DiskCacheConfig getDefaultMainDiskCacheConfig(Context context) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("DiskCacheConfig.getDefaultMainDiskCacheConfig");
            }
            return DiskCacheConfig.newBuilder(context).build();
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    @VisibleForTesting
    static void resetDefaultRequestConfig() {
        sDefaultImageRequestConfig = new DefaultImageRequestConfig();
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier() {
        return this.mBitmapMemoryCacheParamsSupplier;
    }

    public MemoryCache.CacheTrimStrategy getBitmapMemoryCacheTrimStrategy() {
        return this.mBitmapMemoryCacheTrimStrategy;
    }

    public CountingMemoryCache.EntryStateObserver<CacheKey> getBitmapMemoryCacheEntryStateObserver() {
        return this.mBitmapMemoryCacheEntryStateObserver;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public Context getContext() {
        return this.mContext;
    }

    public static DefaultImageRequestConfig getDefaultImageRequestConfig() {
        return sDefaultImageRequestConfig;
    }

    public FileCacheFactory getFileCacheFactory() {
        return this.mFileCacheFactory;
    }

    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }

    public boolean isDiskCacheEnabled() {
        return this.mDiskCacheEnabled;
    }

    public Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier() {
        return this.mEncodedMemoryCacheParamsSupplier;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return this.mExecutorSupplier;
    }

    public ImageCacheStatsTracker getImageCacheStatsTracker() {
        return this.mImageCacheStatsTracker;
    }

    @Nullable
    public ImageDecoder getImageDecoder() {
        return this.mImageDecoder;
    }

    @Nullable
    public ImageTranscoderFactory getImageTranscoderFactory() {
        return this.mImageTranscoderFactory;
    }

    @Nullable
    public Integer getImageTranscoderType() {
        return this.mImageTranscoderType;
    }

    public Supplier<Boolean> getIsPrefetchEnabledSupplier() {
        return this.mIsPrefetchEnabledSupplier;
    }

    public DiskCacheConfig getMainDiskCacheConfig() {
        return this.mMainDiskCacheConfig;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public int getMemoryChunkType() {
        return this.mMemoryChunkType;
    }

    public NetworkFetcher getNetworkFetcher() {
        return this.mNetworkFetcher;
    }

    @Nullable
    public PlatformBitmapFactory getPlatformBitmapFactory() {
        return this.mPlatformBitmapFactory;
    }

    public PoolFactory getPoolFactory() {
        return this.mPoolFactory;
    }

    public ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.mProgressiveJpegConfig;
    }

    public Set<RequestListener> getRequestListeners() {
        return Collections.unmodifiableSet(this.mRequestListeners);
    }

    public Set<RequestListener2> getRequestListener2s() {
        return Collections.unmodifiableSet(this.mRequestListener2s);
    }

    public boolean isResizeAndRotateEnabledForNetwork() {
        return this.mResizeAndRotateEnabledForNetwork;
    }

    public DiskCacheConfig getSmallImageDiskCacheConfig() {
        return this.mSmallImageDiskCacheConfig;
    }

    @Nullable
    public ImageDecoderConfig getImageDecoderConfig() {
        return this.mImageDecoderConfig;
    }

    @Nullable
    public CallerContextVerifier getCallerContextVerifier() {
        return this.mCallerContextVerifier;
    }

    public ImagePipelineExperiments getExperiments() {
        return this.mImagePipelineExperiments;
    }

    public CloseableReferenceLeakTracker getCloseableReferenceLeakTracker() {
        return this.mCloseableReferenceLeakTracker;
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    @Nullable
    private static ImageTranscoderFactory getImageTranscoderFactory(Builder builder) {
        if (builder.mImageTranscoderFactory != null && builder.mImageTranscoderType != null) {
            throw new IllegalStateException("You can't define a custom ImageTranscoderFactory and provide an ImageTranscoderType");
        } else if (builder.mImageTranscoderFactory != null) {
            return builder.mImageTranscoderFactory;
        } else {
            return null;
        }
    }

    private static int getMemoryChunkType(Builder builder, ImagePipelineExperiments imagePipelineExperiments) {
        if (builder.mMemoryChunkType != null) {
            return builder.mMemoryChunkType.intValue();
        }
        if (imagePipelineExperiments.getMemoryType() == 2 && Build.VERSION.SDK_INT >= 27) {
            return 2;
        }
        if (imagePipelineExperiments.getMemoryType() == 1) {
            return 1;
        }
        if (imagePipelineExperiments.getMemoryType() == 0) {
        }
        return 0;
    }

    @Nullable
    public MemoryCache<CacheKey, CloseableImage> getBitmapCacheOverride() {
        return this.mBitmapCache;
    }

    @Nullable
    public MemoryCache<CacheKey, PooledByteBuffer> getEncodedMemoryCacheOverride() {
        return this.mEncodedMemoryCache;
    }

    public static class DefaultImageRequestConfig {
        private boolean mProgressiveRenderingEnabled;

        private DefaultImageRequestConfig() {
            this.mProgressiveRenderingEnabled = false;
        }

        public void setProgressiveRenderingEnabled(boolean z) {
            this.mProgressiveRenderingEnabled = z;
        }

        public boolean isProgressiveRenderingEnabled() {
            return this.mProgressiveRenderingEnabled;
        }
    }

    public static class Builder {
        private Bitmap.Config mBitmapConfig;
        @Nullable
        private MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
        private CountingMemoryCache.EntryStateObserver<CacheKey> mBitmapMemoryCacheEntryStateObserver;
        private Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
        private MemoryCache.CacheTrimStrategy mBitmapMemoryCacheTrimStrategy;
        private CacheKeyFactory mCacheKeyFactory;
        private CallerContextVerifier mCallerContextVerifier;
        private CloseableReferenceLeakTracker mCloseableReferenceLeakTracker;
        private final Context mContext;
        private boolean mDiskCacheEnabled;
        private boolean mDownsampleEnabled;
        @Nullable
        private MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
        private Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
        private ExecutorSupplier mExecutorSupplier;
        private final ImagePipelineExperiments.Builder mExperimentsBuilder;
        private FileCacheFactory mFileCacheFactory;
        private int mHttpConnectionTimeout;
        private ImageCacheStatsTracker mImageCacheStatsTracker;
        private ImageDecoder mImageDecoder;
        private ImageDecoderConfig mImageDecoderConfig;
        private ImageTranscoderFactory mImageTranscoderFactory;
        @Nullable
        private Integer mImageTranscoderType;
        private Supplier<Boolean> mIsPrefetchEnabledSupplier;
        private DiskCacheConfig mMainDiskCacheConfig;
        @Nullable
        private Integer mMemoryChunkType;
        private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        private NetworkFetcher mNetworkFetcher;
        private PlatformBitmapFactory mPlatformBitmapFactory;
        private PoolFactory mPoolFactory;
        private ProgressiveJpegConfig mProgressiveJpegConfig;
        private Set<RequestListener2> mRequestListener2s;
        private Set<RequestListener> mRequestListeners;
        private boolean mResizeAndRotateEnabledForNetwork;
        private DiskCacheConfig mSmallImageDiskCacheConfig;

        private Builder(Context context) {
            this.mDownsampleEnabled = false;
            this.mImageTranscoderType = null;
            this.mMemoryChunkType = null;
            this.mResizeAndRotateEnabledForNetwork = true;
            this.mHttpConnectionTimeout = -1;
            this.mExperimentsBuilder = new ImagePipelineExperiments.Builder(this);
            this.mDiskCacheEnabled = true;
            this.mCloseableReferenceLeakTracker = new NoOpCloseableReferenceLeakTracker();
            this.mContext = (Context) Preconditions.checkNotNull(context);
        }

        public Builder setBitmapsConfig(Bitmap.Config config) {
            this.mBitmapConfig = config;
            return this;
        }

        public Builder setBitmapMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> supplier) {
            this.mBitmapMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(supplier);
            return this;
        }

        public Builder setBitmapMemoryCacheEntryStateObserver(CountingMemoryCache.EntryStateObserver<CacheKey> entryStateObserver) {
            this.mBitmapMemoryCacheEntryStateObserver = entryStateObserver;
            return this;
        }

        public Builder setBitmapMemoryCacheTrimStrategy(MemoryCache.CacheTrimStrategy cacheTrimStrategy) {
            this.mBitmapMemoryCacheTrimStrategy = cacheTrimStrategy;
            return this;
        }

        public Builder setCacheKeyFactory(CacheKeyFactory cacheKeyFactory) {
            this.mCacheKeyFactory = cacheKeyFactory;
            return this;
        }

        public Builder setHttpConnectionTimeout(int i) {
            this.mHttpConnectionTimeout = i;
            return this;
        }

        public Builder setFileCacheFactory(FileCacheFactory fileCacheFactory) {
            this.mFileCacheFactory = fileCacheFactory;
            return this;
        }

        public boolean isDownsampleEnabled() {
            return this.mDownsampleEnabled;
        }

        public Builder setDownsampleEnabled(boolean z) {
            this.mDownsampleEnabled = z;
            return this;
        }

        public boolean isDiskCacheEnabled() {
            return this.mDiskCacheEnabled;
        }

        public Builder setDiskCacheEnabled(boolean z) {
            this.mDiskCacheEnabled = z;
            return this;
        }

        public Builder setEncodedMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> supplier) {
            this.mEncodedMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(supplier);
            return this;
        }

        public Builder setExecutorSupplier(ExecutorSupplier executorSupplier) {
            this.mExecutorSupplier = executorSupplier;
            return this;
        }

        public Builder setImageCacheStatsTracker(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.mImageCacheStatsTracker = imageCacheStatsTracker;
            return this;
        }

        public Builder setImageDecoder(ImageDecoder imageDecoder) {
            this.mImageDecoder = imageDecoder;
            return this;
        }

        @Nullable
        public Integer getImageTranscoderType() {
            return this.mImageTranscoderType;
        }

        public Builder setImageTranscoderType(int i) {
            this.mImageTranscoderType = Integer.valueOf(i);
            return this;
        }

        public Builder setImageTranscoderFactory(ImageTranscoderFactory imageTranscoderFactory) {
            this.mImageTranscoderFactory = imageTranscoderFactory;
            return this;
        }

        public Builder setIsPrefetchEnabledSupplier(Supplier<Boolean> supplier) {
            this.mIsPrefetchEnabledSupplier = supplier;
            return this;
        }

        public Builder setMainDiskCacheConfig(DiskCacheConfig diskCacheConfig) {
            this.mMainDiskCacheConfig = diskCacheConfig;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
            this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
            return this;
        }

        @Nullable
        public Integer getMemoryChunkType() {
            return this.mMemoryChunkType;
        }

        public Builder setMemoryChunkType(int i) {
            this.mMemoryChunkType = Integer.valueOf(i);
            return this;
        }

        public Builder setNetworkFetcher(NetworkFetcher networkFetcher) {
            this.mNetworkFetcher = networkFetcher;
            return this;
        }

        public Builder setPlatformBitmapFactory(PlatformBitmapFactory platformBitmapFactory) {
            this.mPlatformBitmapFactory = platformBitmapFactory;
            return this;
        }

        public Builder setPoolFactory(PoolFactory poolFactory) {
            this.mPoolFactory = poolFactory;
            return this;
        }

        public Builder setProgressiveJpegConfig(ProgressiveJpegConfig progressiveJpegConfig) {
            this.mProgressiveJpegConfig = progressiveJpegConfig;
            return this;
        }

        public Builder setRequestListeners(Set<RequestListener> set) {
            this.mRequestListeners = set;
            return this;
        }

        public Builder setRequestListener2s(Set<RequestListener2> set) {
            this.mRequestListener2s = set;
            return this;
        }

        public Builder setResizeAndRotateEnabledForNetwork(boolean z) {
            this.mResizeAndRotateEnabledForNetwork = z;
            return this;
        }

        public Builder setSmallImageDiskCacheConfig(DiskCacheConfig diskCacheConfig) {
            this.mSmallImageDiskCacheConfig = diskCacheConfig;
            return this;
        }

        public Builder setImageDecoderConfig(ImageDecoderConfig imageDecoderConfig) {
            this.mImageDecoderConfig = imageDecoderConfig;
            return this;
        }

        public Builder setCallerContextVerifier(CallerContextVerifier callerContextVerifier) {
            this.mCallerContextVerifier = callerContextVerifier;
            return this;
        }

        public Builder setCloseableReferenceLeakTracker(CloseableReferenceLeakTracker closeableReferenceLeakTracker) {
            this.mCloseableReferenceLeakTracker = closeableReferenceLeakTracker;
            return this;
        }

        public Builder setBitmapMemoryCache(@Nullable MemoryCache<CacheKey, CloseableImage> memoryCache) {
            this.mBitmapMemoryCache = memoryCache;
            return this;
        }

        public Builder setEncodedMemoryCache(@Nullable MemoryCache<CacheKey, PooledByteBuffer> memoryCache) {
            this.mEncodedMemoryCache = memoryCache;
            return this;
        }

        public ImagePipelineExperiments.Builder experiment() {
            return this.mExperimentsBuilder;
        }

        public ImagePipelineConfig build() {
            return new ImagePipelineConfig(this);
        }
    }
}
