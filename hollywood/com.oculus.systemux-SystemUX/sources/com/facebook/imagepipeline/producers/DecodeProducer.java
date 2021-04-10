package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.os.Build;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.producers.JobScheduler;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.DownsampleUtil;
import com.facebook.imageutils.BitmapUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class DecodeProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_BITMAP_BYTES = "byteCount";
    public static final String EXTRA_BITMAP_SIZE = "bitmapSize";
    public static final String EXTRA_HAS_GOOD_QUALITY = "hasGoodQuality";
    public static final String EXTRA_IMAGE_FORMAT_NAME = "imageFormat";
    public static final String EXTRA_IS_FINAL = "isFinal";
    private static final int MAX_BITMAP_SIZE = 104857600;
    public static final String PRODUCER_NAME = "DecodeProducer";
    public static final String REQUESTED_IMAGE_SIZE = "requestedImageSize";
    public static final String SAMPLE_SIZE = "sampleSize";
    private final ByteArrayPool mByteArrayPool;
    private final CloseableReferenceFactory mCloseableReferenceFactory;
    private final boolean mDecodeCancellationEnabled;
    private final boolean mDownsampleEnabled;
    private final boolean mDownsampleEnabledForNetwork;
    private final Executor mExecutor;
    private final ImageDecoder mImageDecoder;
    private final Producer<EncodedImage> mInputProducer;
    private final int mMaxBitmapSize;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    @Nullable
    private final Runnable mReclaimMemoryRunnable;
    private final Supplier<Boolean> mRecoverFromDecoderOOM;

    public DecodeProducer(ByteArrayPool byteArrayPool, Executor executor, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean z, boolean z2, boolean z3, Producer<EncodedImage> producer, int i, CloseableReferenceFactory closeableReferenceFactory, @Nullable Runnable runnable, Supplier<Boolean> supplier) {
        this.mByteArrayPool = (ByteArrayPool) Preconditions.checkNotNull(byteArrayPool);
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mImageDecoder = (ImageDecoder) Preconditions.checkNotNull(imageDecoder);
        this.mProgressiveJpegConfig = (ProgressiveJpegConfig) Preconditions.checkNotNull(progressiveJpegConfig);
        this.mDownsampleEnabled = z;
        this.mDownsampleEnabledForNetwork = z2;
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mDecodeCancellationEnabled = z3;
        this.mMaxBitmapSize = i;
        this.mCloseableReferenceFactory = closeableReferenceFactory;
        this.mReclaimMemoryRunnable = runnable;
        this.mRecoverFromDecoderOOM = supplier;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Consumer<EncodedImage> consumer2;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("DecodeProducer#produceResults");
            }
            if (!UriUtil.isNetworkUri(producerContext.getImageRequest().getSourceUri())) {
                consumer2 = new LocalImagesProgressiveDecoder(consumer, producerContext, this.mDecodeCancellationEnabled, this.mMaxBitmapSize);
            } else {
                consumer2 = new NetworkImagesProgressiveDecoder(consumer, producerContext, new ProgressiveJpegParser(this.mByteArrayPool), this.mProgressiveJpegConfig, this.mDecodeCancellationEnabled, this.mMaxBitmapSize);
            }
            this.mInputProducer.produceResults(consumer2, producerContext);
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    private abstract class ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>> {
        private static final int DECODE_EXCEPTION_MESSAGE_NUM_HEADER_BYTES = 10;
        private final String TAG = "ProgressiveDecoder";
        private final ImageDecodeOptions mImageDecodeOptions;
        @GuardedBy("this")
        private boolean mIsFinished;
        private final JobScheduler mJobScheduler;
        private final ProducerContext mProducerContext;
        private final ProducerListener2 mProducerListener;

        /* access modifiers changed from: protected */
        public abstract int getIntermediateImageEndOffset(EncodedImage encodedImage);

        /* access modifiers changed from: protected */
        public abstract QualityInfo getQualityInfo();

        public ProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext, final boolean z, final int i) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mProducerListener = producerContext.getProducerListener();
            this.mImageDecodeOptions = producerContext.getImageRequest().getImageDecodeOptions();
            this.mIsFinished = false;
            this.mJobScheduler = new JobScheduler(DecodeProducer.this.mExecutor, new JobScheduler.JobRunnable(DecodeProducer.this) {
                /* class com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.AnonymousClass1 */

                @Override // com.facebook.imagepipeline.producers.JobScheduler.JobRunnable
                public void run(EncodedImage encodedImage, int i) {
                    if (encodedImage != null) {
                        ProgressiveDecoder.this.mProducerContext.setExtra("image_format", encodedImage.getImageFormat().getName());
                        if (DecodeProducer.this.mDownsampleEnabled || !BaseConsumer.statusHasFlag(i, 16)) {
                            ImageRequest imageRequest = producerContext.getImageRequest();
                            if (DecodeProducer.this.mDownsampleEnabledForNetwork || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                                encodedImage.setSampleSize(DownsampleUtil.determineSampleSize(imageRequest.getRotationOptions(), imageRequest.getResizeOptions(), encodedImage, i));
                            }
                        }
                        if (producerContext.getImagePipelineConfig().getExperiments().shouldDownsampleIfLargeBitmap()) {
                            ProgressiveDecoder.this.maybeIncreaseSampleSize(encodedImage);
                        }
                        ProgressiveDecoder.this.doDecode(encodedImage, i);
                    }
                }
            }, this.mImageDecodeOptions.minDecodeIntervalMs);
            this.mProducerContext.addCallbacks(new BaseProducerContextCallbacks(DecodeProducer.this) {
                /* class com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.AnonymousClass2 */

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onIsIntermediateResultExpectedChanged() {
                    if (ProgressiveDecoder.this.mProducerContext.isIntermediateResultExpected()) {
                        ProgressiveDecoder.this.mJobScheduler.scheduleJob();
                    }
                }

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    if (z) {
                        ProgressiveDecoder.this.handleCancellation();
                    }
                }
            });
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void maybeIncreaseSampleSize(EncodedImage encodedImage) {
            if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                encodedImage.setSampleSize(DownsampleUtil.determineSampleSizeJPEG(encodedImage, BitmapUtil.getPixelSizeForBitmapConfig(this.mImageDecodeOptions.bitmapConfig), DecodeProducer.MAX_BITMAP_SIZE));
            }
        }

        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            boolean isTracing;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("DecodeProducer#onNewResultImpl");
                }
                boolean isLast = isLast(i);
                if (isLast) {
                    if (encodedImage == null) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                        if (!isTracing) {
                            return;
                        }
                        return;
                    } else if (!encodedImage.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    }
                }
                if (updateDecodeJob(encodedImage, i)) {
                    boolean statusHasFlag = statusHasFlag(i, 4);
                    if (isLast || statusHasFlag || this.mProducerContext.isIntermediateResultExpected()) {
                        this.mJobScheduler.scheduleJob();
                    }
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } else if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } finally {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer, com.facebook.imagepipeline.producers.DelegatingConsumer
        public void onProgressUpdateImpl(float f) {
            super.onProgressUpdateImpl(f * 0.99f);
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer, com.facebook.imagepipeline.producers.DelegatingConsumer
        public void onFailureImpl(Throwable th) {
            handleError(th);
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer, com.facebook.imagepipeline.producers.DelegatingConsumer
        public void onCancellationImpl() {
            handleCancellation();
        }

        /* access modifiers changed from: protected */
        public boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            return this.mJobScheduler.updateJob(encodedImage, i);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00b8 A[Catch:{ all -> 0x013c }] */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x00d4  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void doDecode(com.facebook.imagepipeline.image.EncodedImage r21, int r22) {
            /*
            // Method dump skipped, instructions count: 322
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.doDecode(com.facebook.imagepipeline.image.EncodedImage, int):void");
        }

        private CloseableImage internalDecode(EncodedImage encodedImage, int i, QualityInfo qualityInfo) {
            boolean z = DecodeProducer.this.mReclaimMemoryRunnable != null && ((Boolean) DecodeProducer.this.mRecoverFromDecoderOOM.get()).booleanValue();
            try {
                return DecodeProducer.this.mImageDecoder.decode(encodedImage, i, qualityInfo, this.mImageDecodeOptions);
            } catch (OutOfMemoryError e) {
                if (z) {
                    DecodeProducer.this.mReclaimMemoryRunnable.run();
                    System.gc();
                    return DecodeProducer.this.mImageDecoder.decode(encodedImage, i, qualityInfo, this.mImageDecodeOptions);
                }
                throw e;
            }
        }

        private void setImageExtras(EncodedImage encodedImage, CloseableImage closeableImage) {
            Bitmap.Config config;
            this.mProducerContext.setExtra(ProducerContext.ExtraKeys.ENCODED_WIDTH, Integer.valueOf(encodedImage.getWidth()));
            this.mProducerContext.setExtra(ProducerContext.ExtraKeys.ENCODED_HEIGHT, Integer.valueOf(encodedImage.getHeight()));
            this.mProducerContext.setExtra(ProducerContext.ExtraKeys.ENCODED_SIZE, Integer.valueOf(encodedImage.getSize()));
            if (closeableImage instanceof CloseableBitmap) {
                Bitmap underlyingBitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
                if (underlyingBitmap == null) {
                    config = null;
                } else {
                    config = underlyingBitmap.getConfig();
                }
                this.mProducerContext.setExtra("bitmap_config", String.valueOf(config));
            }
            if (closeableImage != null) {
                closeableImage.setImageExtras(this.mProducerContext.getExtras());
            }
        }

        @Nullable
        private Map<String, String> getExtraMap(@Nullable CloseableImage closeableImage, long j, QualityInfo qualityInfo, boolean z, String str, String str2, String str3, String str4) {
            if (!this.mProducerListener.requiresExtraMap(this.mProducerContext, DecodeProducer.PRODUCER_NAME)) {
                return null;
            }
            String valueOf = String.valueOf(j);
            String valueOf2 = String.valueOf(qualityInfo.isOfGoodEnoughQuality());
            String valueOf3 = String.valueOf(z);
            if (closeableImage instanceof CloseableStaticBitmap) {
                Bitmap underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap();
                HashMap hashMap = new HashMap(8);
                hashMap.put(DecodeProducer.EXTRA_BITMAP_SIZE, underlyingBitmap.getWidth() + "x" + underlyingBitmap.getHeight());
                hashMap.put("queueTime", valueOf);
                hashMap.put(DecodeProducer.EXTRA_HAS_GOOD_QUALITY, valueOf2);
                hashMap.put(DecodeProducer.EXTRA_IS_FINAL, valueOf3);
                hashMap.put("encodedImageSize", str2);
                hashMap.put(DecodeProducer.EXTRA_IMAGE_FORMAT_NAME, str);
                hashMap.put(DecodeProducer.REQUESTED_IMAGE_SIZE, str3);
                hashMap.put(DecodeProducer.SAMPLE_SIZE, str4);
                if (Build.VERSION.SDK_INT >= 12) {
                    hashMap.put(DecodeProducer.EXTRA_BITMAP_BYTES, underlyingBitmap.getByteCount() + "");
                }
                return ImmutableMap.copyOf(hashMap);
            }
            HashMap hashMap2 = new HashMap(7);
            hashMap2.put("queueTime", valueOf);
            hashMap2.put(DecodeProducer.EXTRA_HAS_GOOD_QUALITY, valueOf2);
            hashMap2.put(DecodeProducer.EXTRA_IS_FINAL, valueOf3);
            hashMap2.put("encodedImageSize", str2);
            hashMap2.put(DecodeProducer.EXTRA_IMAGE_FORMAT_NAME, str);
            hashMap2.put(DecodeProducer.REQUESTED_IMAGE_SIZE, str3);
            hashMap2.put(DecodeProducer.SAMPLE_SIZE, str4);
            return ImmutableMap.copyOf(hashMap2);
        }

        private synchronized boolean isFinished() {
            return this.mIsFinished;
        }

        private void maybeFinish(boolean z) {
            synchronized (this) {
                if (z) {
                    if (!this.mIsFinished) {
                        getConsumer().onProgressUpdate(1.0f);
                        this.mIsFinished = true;
                        this.mJobScheduler.clearJob();
                    }
                }
            }
        }

        private void handleResult(CloseableImage closeableImage, int i) {
            CloseableReference create = DecodeProducer.this.mCloseableReferenceFactory.create(closeableImage);
            try {
                maybeFinish(isLast(i));
                getConsumer().onNewResult(create, i);
            } finally {
                CloseableReference.closeSafely(create);
            }
        }

        private void handleError(Throwable th) {
            maybeFinish(true);
            getConsumer().onFailure(th);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void handleCancellation() {
            maybeFinish(true);
            getConsumer().onCancellation();
        }
    }

    private class LocalImagesProgressiveDecoder extends ProgressiveDecoder {
        public LocalImagesProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, boolean z, int i) {
            super(consumer, producerContext, z, i);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            if (isNotLast(i)) {
                return false;
            }
            return super.updateDecodeJob(encodedImage, i);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return encodedImage.getSize();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public QualityInfo getQualityInfo() {
            return ImmutableQualityInfo.of(0, false, false);
        }
    }

    private class NetworkImagesProgressiveDecoder extends ProgressiveDecoder {
        private int mLastScheduledScanNumber = 0;
        private final ProgressiveJpegConfig mProgressiveJpegConfig;
        private final ProgressiveJpegParser mProgressiveJpegParser;

        public NetworkImagesProgressiveDecoder(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, ProgressiveJpegParser progressiveJpegParser, ProgressiveJpegConfig progressiveJpegConfig, boolean z, int i) {
            super(consumer, producerContext, z, i);
            this.mProgressiveJpegParser = (ProgressiveJpegParser) Preconditions.checkNotNull(progressiveJpegParser);
            this.mProgressiveJpegConfig = (ProgressiveJpegConfig) Preconditions.checkNotNull(progressiveJpegConfig);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            boolean updateDecodeJob = super.updateDecodeJob(encodedImage, i);
            if ((isNotLast(i) || statusHasFlag(i, 8)) && !statusHasFlag(i, 4) && EncodedImage.isValid(encodedImage) && encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                if (!this.mProgressiveJpegParser.parseMoreData(encodedImage)) {
                    return false;
                }
                int bestScanNumber = this.mProgressiveJpegParser.getBestScanNumber();
                if (bestScanNumber <= this.mLastScheduledScanNumber) {
                    return false;
                }
                if (bestScanNumber < this.mProgressiveJpegConfig.getNextScanNumberToDecode(this.mLastScheduledScanNumber) && !this.mProgressiveJpegParser.isEndMarkerRead()) {
                    return false;
                }
                this.mLastScheduledScanNumber = bestScanNumber;
            }
            return updateDecodeJob;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            return this.mProgressiveJpegParser.getBestScanEndOffset();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        public QualityInfo getQualityInfo() {
            return this.mProgressiveJpegConfig.getQualityInfo(this.mProgressiveJpegParser.getBestScanNumber());
        }
    }
}
