package com.facebook.imagepipeline.core;

import android.content.Context;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;

public class ImagePipelineExperiments {
    private final int mBitmapCloseableRefType;
    private boolean mBitmapPrepareToDrawForPrefetch;
    private final int mBitmapPrepareToDrawMaxSizeBytes;
    private final int mBitmapPrepareToDrawMinSizeBytes;
    private final boolean mDecodeCancellationEnabled;
    private boolean mDownsampleIfLargeBitmap;
    private final boolean mDownscaleFrameToDrawableDimensions;
    private boolean mEncodedCacheEnabled;
    private final boolean mEnsureTranscoderLibraryLoaded;
    private final boolean mExperimentalThreadHandoffQueueEnabled;
    private final boolean mGingerbreadDecoderEnabled;
    private final boolean mIsDiskCacheProbingEnabled;
    private final boolean mIsEncodedMemoryCacheProbingEnabled;
    private boolean mKeepCancelledFetchAsLowPriority;
    private final Supplier<Boolean> mLazyDataSource;
    private final int mMaxBitmapSize;
    private final long mMemoryType;
    private final boolean mNativeCodeDisabled;
    private final boolean mPartialImageCachingEnabled;
    private final ProducerFactoryMethod mProducerFactoryMethod;
    private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    private final int mTrackedKeysSize;
    private final boolean mUseBitmapPrepareToDraw;
    private final boolean mUseDownsamplingRatioForResizing;
    private final WebpBitmapFactory mWebpBitmapFactory;
    private final WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;
    private final boolean mWebpSupportEnabled;

    public interface ProducerFactoryMethod {
        ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z4, int i3, CloseableReferenceFactory closeableReferenceFactory, boolean z5, int i4);
    }

    private ImagePipelineExperiments(Builder builder) {
        this.mWebpSupportEnabled = builder.mWebpSupportEnabled;
        this.mWebpErrorLogger = builder.mWebpErrorLogger;
        this.mDecodeCancellationEnabled = builder.mDecodeCancellationEnabled;
        this.mWebpBitmapFactory = builder.mWebpBitmapFactory;
        this.mUseDownsamplingRatioForResizing = builder.mUseDownsamplingRatioForResizing;
        this.mUseBitmapPrepareToDraw = builder.mUseBitmapPrepareToDraw;
        this.mBitmapPrepareToDrawMinSizeBytes = builder.mBitmapPrepareToDrawMinSizeBytes;
        this.mBitmapPrepareToDrawMaxSizeBytes = builder.mBitmapPrepareToDrawMaxSizeBytes;
        this.mBitmapPrepareToDrawForPrefetch = builder.mBitmapPrepareToDrawForPrefetch;
        this.mMaxBitmapSize = builder.mMaxBitmapSize;
        this.mNativeCodeDisabled = builder.mNativeCodeDisabled;
        this.mPartialImageCachingEnabled = builder.mPartialImageCachingEnabled;
        if (builder.mProducerFactoryMethod == null) {
            this.mProducerFactoryMethod = new DefaultProducerFactoryMethod();
        } else {
            this.mProducerFactoryMethod = builder.mProducerFactoryMethod;
        }
        this.mLazyDataSource = builder.mLazyDataSource;
        this.mGingerbreadDecoderEnabled = builder.mGingerbreadDecoderEnabled;
        this.mDownscaleFrameToDrawableDimensions = builder.mDownscaleFrameToDrawableDimensions;
        this.mBitmapCloseableRefType = builder.mBitmapCloseableRefType;
        this.mSuppressBitmapPrefetchingSupplier = builder.mSuppressBitmapPrefetchingSupplier;
        this.mExperimentalThreadHandoffQueueEnabled = builder.mExperimentalThreadHandoffQueueEnabled;
        this.mMemoryType = builder.mMemoryType;
        this.mKeepCancelledFetchAsLowPriority = builder.mKeepCancelledFetchAsLowPriority;
        this.mDownsampleIfLargeBitmap = builder.mDownsampleIfLargeBitmap;
        this.mEncodedCacheEnabled = builder.mEncodedCacheEnabled;
        this.mEnsureTranscoderLibraryLoaded = builder.mEnsureTranscoderLibraryLoaded;
        this.mIsEncodedMemoryCacheProbingEnabled = builder.mIsEncodedMemoryCacheProbingEnabled;
        this.mIsDiskCacheProbingEnabled = builder.mIsDiskCacheProbingEnabled;
        this.mTrackedKeysSize = builder.mTrackedKeysSize;
    }

    public boolean isEncodedCacheEnabled() {
        return this.mEncodedCacheEnabled;
    }

    public boolean shouldDownsampleIfLargeBitmap() {
        return this.mDownsampleIfLargeBitmap;
    }

    public boolean getUseDownsamplingRatioForResizing() {
        return this.mUseDownsamplingRatioForResizing;
    }

    public boolean isWebpSupportEnabled() {
        return this.mWebpSupportEnabled;
    }

    public boolean isDecodeCancellationEnabled() {
        return this.mDecodeCancellationEnabled;
    }

    public WebpBitmapFactory.WebpErrorLogger getWebpErrorLogger() {
        return this.mWebpErrorLogger;
    }

    public WebpBitmapFactory getWebpBitmapFactory() {
        return this.mWebpBitmapFactory;
    }

    public boolean getUseBitmapPrepareToDraw() {
        return this.mUseBitmapPrepareToDraw;
    }

    public int getBitmapPrepareToDrawMinSizeBytes() {
        return this.mBitmapPrepareToDrawMinSizeBytes;
    }

    public int getBitmapPrepareToDrawMaxSizeBytes() {
        return this.mBitmapPrepareToDrawMaxSizeBytes;
    }

    public boolean isNativeCodeDisabled() {
        return this.mNativeCodeDisabled;
    }

    public boolean isPartialImageCachingEnabled() {
        return this.mPartialImageCachingEnabled;
    }

    public ProducerFactoryMethod getProducerFactoryMethod() {
        return this.mProducerFactoryMethod;
    }

    public boolean isExperimentalThreadHandoffQueueEnabled() {
        return this.mExperimentalThreadHandoffQueueEnabled;
    }

    public static Builder newBuilder(ImagePipelineConfig.Builder builder) {
        return new Builder(builder);
    }

    public boolean getBitmapPrepareToDrawForPrefetch() {
        return this.mBitmapPrepareToDrawForPrefetch;
    }

    public int getMaxBitmapSize() {
        return this.mMaxBitmapSize;
    }

    public Supplier<Boolean> isLazyDataSource() {
        return this.mLazyDataSource;
    }

    public boolean isEnsureTranscoderLibraryLoaded() {
        return this.mEnsureTranscoderLibraryLoaded;
    }

    public boolean isDiskCacheProbingEnabled() {
        return this.mIsDiskCacheProbingEnabled;
    }

    public boolean isEncodedMemoryCacheProbingEnabled() {
        return this.mIsEncodedMemoryCacheProbingEnabled;
    }

    public boolean isGingerbreadDecoderEnabled() {
        return this.mGingerbreadDecoderEnabled;
    }

    public boolean shouldDownscaleFrameToDrawableDimensions() {
        return this.mDownscaleFrameToDrawableDimensions;
    }

    public int getBitmapCloseableRefType() {
        return this.mBitmapCloseableRefType;
    }

    public Supplier<Boolean> getSuppressBitmapPrefetchingSupplier() {
        return this.mSuppressBitmapPrefetchingSupplier;
    }

    public long getMemoryType() {
        return this.mMemoryType;
    }

    public int getTrackedKeysSize() {
        return this.mTrackedKeysSize;
    }

    public boolean shouldKeepCancelledFetchAsLowPriority() {
        return this.mKeepCancelledFetchAsLowPriority;
    }

    public static class Builder {
        public int mBitmapCloseableRefType;
        public boolean mBitmapPrepareToDrawForPrefetch = false;
        private int mBitmapPrepareToDrawMaxSizeBytes = 0;
        private int mBitmapPrepareToDrawMinSizeBytes = 0;
        private final ImagePipelineConfig.Builder mConfigBuilder;
        private boolean mDecodeCancellationEnabled = false;
        public boolean mDownsampleIfLargeBitmap;
        public boolean mDownscaleFrameToDrawableDimensions;
        public boolean mEncodedCacheEnabled = true;
        public boolean mEnsureTranscoderLibraryLoaded = true;
        public boolean mExperimentalThreadHandoffQueueEnabled;
        public boolean mGingerbreadDecoderEnabled;
        private boolean mIsDiskCacheProbingEnabled = false;
        private boolean mIsEncodedMemoryCacheProbingEnabled = false;
        private boolean mKeepCancelledFetchAsLowPriority;
        public Supplier<Boolean> mLazyDataSource;
        private int mMaxBitmapSize = 2048;
        public long mMemoryType = 0;
        private boolean mNativeCodeDisabled = false;
        private boolean mPartialImageCachingEnabled = false;
        private ProducerFactoryMethod mProducerFactoryMethod;
        public Supplier<Boolean> mSuppressBitmapPrefetchingSupplier = Suppliers.of(false);
        private int mTrackedKeysSize = 20;
        private boolean mUseBitmapPrepareToDraw = false;
        private boolean mUseDownsamplingRatioForResizing = false;
        private WebpBitmapFactory mWebpBitmapFactory;
        private WebpBitmapFactory.WebpErrorLogger mWebpErrorLogger;
        private boolean mWebpSupportEnabled = false;

        public Builder(ImagePipelineConfig.Builder builder) {
            this.mConfigBuilder = builder;
        }

        public ImagePipelineConfig.Builder setWebpSupportEnabled(boolean z) {
            this.mWebpSupportEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setUseDownsampligRatioForResizing(boolean z) {
            this.mUseDownsamplingRatioForResizing = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setPartialImageCachingEnabled(boolean z) {
            this.mPartialImageCachingEnabled = z;
            return this.mConfigBuilder;
        }

        public boolean isPartialImageCachingEnabled() {
            return this.mPartialImageCachingEnabled;
        }

        public ImagePipelineConfig.Builder setDecodeCancellationEnabled(boolean z) {
            this.mDecodeCancellationEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setWebpErrorLogger(WebpBitmapFactory.WebpErrorLogger webpErrorLogger) {
            this.mWebpErrorLogger = webpErrorLogger;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setWebpBitmapFactory(WebpBitmapFactory webpBitmapFactory) {
            this.mWebpBitmapFactory = webpBitmapFactory;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setBitmapPrepareToDraw(boolean z, int i, int i2, boolean z2) {
            this.mUseBitmapPrepareToDraw = z;
            this.mBitmapPrepareToDrawMinSizeBytes = i;
            this.mBitmapPrepareToDrawMaxSizeBytes = i2;
            this.mBitmapPrepareToDrawForPrefetch = z2;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setMaxBitmapSize(int i) {
            this.mMaxBitmapSize = i;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setNativeCodeDisabled(boolean z) {
            this.mNativeCodeDisabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setProducerFactoryMethod(ProducerFactoryMethod producerFactoryMethod) {
            this.mProducerFactoryMethod = producerFactoryMethod;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setLazyDataSource(Supplier<Boolean> supplier) {
            this.mLazyDataSource = supplier;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setGingerbreadDecoderEnabled(boolean z) {
            this.mGingerbreadDecoderEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setShouldDownscaleFrameToDrawableDimensions(boolean z) {
            this.mDownscaleFrameToDrawableDimensions = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setBitmapCloseableRefType(int i) {
            this.mBitmapCloseableRefType = i;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setSuppressBitmapPrefetchingSupplier(Supplier<Boolean> supplier) {
            this.mSuppressBitmapPrefetchingSupplier = supplier;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setExperimentalThreadHandoffQueueEnabled(boolean z) {
            this.mExperimentalThreadHandoffQueueEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setExperimentalMemoryType(long j) {
            this.mMemoryType = j;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setKeepCancelledFetchAsLowPriority(boolean z) {
            this.mKeepCancelledFetchAsLowPriority = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setDownsampleIfLargeBitmap(boolean z) {
            this.mDownsampleIfLargeBitmap = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setEncodedCacheEnabled(boolean z) {
            this.mEncodedCacheEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setEnsureTranscoderLibraryLoaded(boolean z) {
            this.mEnsureTranscoderLibraryLoaded = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setIsDiskCacheProbingEnabled(boolean z) {
            this.mIsDiskCacheProbingEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setIsEncodedMemoryCacheProbingEnabled(boolean z) {
            this.mIsEncodedMemoryCacheProbingEnabled = z;
            return this.mConfigBuilder;
        }

        public ImagePipelineConfig.Builder setTrackedKeysSize(int i) {
            this.mTrackedKeysSize = i;
            return this.mConfigBuilder;
        }

        public ImagePipelineExperiments build() {
            return new ImagePipelineExperiments(this);
        }
    }

    public static class DefaultProducerFactoryMethod implements ProducerFactoryMethod {
        @Override // com.facebook.imagepipeline.core.ImagePipelineExperiments.ProducerFactoryMethod
        public ProducerFactory createProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> memoryCache, MemoryCache<CacheKey, PooledByteBuffer> memoryCache2, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, int i, int i2, boolean z4, int i3, CloseableReferenceFactory closeableReferenceFactory, boolean z5, int i4) {
            return new ProducerFactory(context, byteArrayPool, imageDecoder, progressiveJpegConfig, z, z2, z3, executorSupplier, pooledByteBufferFactory, memoryCache, memoryCache2, bufferedDiskCache, bufferedDiskCache2, cacheKeyFactory, platformBitmapFactory, i, i2, z4, i3, closeableReferenceFactory, z5, i4);
        }
    }
}
