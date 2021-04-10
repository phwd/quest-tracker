package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableSet;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.EncodedImageOrigin;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class BaseProducerContext implements ProducerContext {
    public static final Set<String> INITIAL_KEYS = ImmutableSet.of("id", ProducerContext.ExtraKeys.SOURCE_URI);
    private static final String ORIGIN_SUBCATEGORY_DEFAULT = "default";
    @GuardedBy("this")
    private final List<ProducerContextCallbacks> mCallbacks;
    private final Object mCallerContext;
    private EncodedImageOrigin mEncodedImageOrigin;
    private final Map<String, Object> mExtras;
    private final String mId;
    private final ImagePipelineConfig mImagePipelineConfig;
    private final ImageRequest mImageRequest;
    @GuardedBy("this")
    private boolean mIsCancelled;
    @GuardedBy("this")
    private boolean mIsIntermediateResultExpected;
    @GuardedBy("this")
    private boolean mIsPrefetch;
    private final ImageRequest.RequestLevel mLowestPermittedRequestLevel;
    @GuardedBy("this")
    private Priority mPriority;
    private final ProducerListener2 mProducerListener;
    @Nullable
    private final String mUiComponentId;

    public BaseProducerContext(ImageRequest imageRequest, String str, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfig imagePipelineConfig) {
        this(imageRequest, str, null, producerListener2, obj, requestLevel, z, z2, priority, imagePipelineConfig);
    }

    public BaseProducerContext(ImageRequest imageRequest, String str, @Nullable String str2, ProducerListener2 producerListener2, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority, ImagePipelineConfig imagePipelineConfig) {
        this.mEncodedImageOrigin = EncodedImageOrigin.NOT_SET;
        this.mImageRequest = imageRequest;
        this.mId = str;
        this.mExtras = new HashMap();
        this.mExtras.put("id", this.mId);
        this.mExtras.put(ProducerContext.ExtraKeys.SOURCE_URI, imageRequest == null ? "null-request" : imageRequest.getSourceUri());
        this.mUiComponentId = str2;
        this.mProducerListener = producerListener2;
        this.mCallerContext = obj;
        this.mLowestPermittedRequestLevel = requestLevel;
        this.mIsPrefetch = z;
        this.mPriority = priority;
        this.mIsIntermediateResultExpected = z2;
        this.mIsCancelled = false;
        this.mCallbacks = new ArrayList();
        this.mImagePipelineConfig = imagePipelineConfig;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImageRequest getImageRequest() {
        return this.mImageRequest;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public String getId() {
        return this.mId;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    @Nullable
    public String getUiComponentId() {
        return this.mUiComponentId;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ProducerListener2 getProducerListener() {
        return this.mProducerListener;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImageRequest.RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized boolean isPrefetch() {
        return this.mIsPrefetch;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized Priority getPriority() {
        return this.mPriority;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized boolean isIntermediateResultExpected() {
        return this.mIsIntermediateResultExpected;
    }

    public synchronized boolean isCancelled() {
        return this.mIsCancelled;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void addCallbacks(ProducerContextCallbacks producerContextCallbacks) {
        boolean z;
        synchronized (this) {
            this.mCallbacks.add(producerContextCallbacks);
            z = this.mIsCancelled;
        }
        if (z) {
            producerContextCallbacks.onCancellationRequested();
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImagePipelineConfig getImagePipelineConfig() {
        return this.mImagePipelineConfig;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public EncodedImageOrigin getEncodedImageOrigin() {
        return this.mEncodedImageOrigin;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void setEncodedImageOrigin(EncodedImageOrigin encodedImageOrigin) {
        this.mEncodedImageOrigin = encodedImageOrigin;
    }

    public void cancel() {
        callOnCancellationRequested(cancelNoCallbacks());
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> setIsPrefetchNoCallbacks(boolean z) {
        if (z == this.mIsPrefetch) {
            return null;
        }
        this.mIsPrefetch = z;
        return new ArrayList(this.mCallbacks);
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> setPriorityNoCallbacks(Priority priority) {
        if (priority == this.mPriority) {
            return null;
        }
        this.mPriority = priority;
        return new ArrayList(this.mCallbacks);
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> setIsIntermediateResultExpectedNoCallbacks(boolean z) {
        if (z == this.mIsIntermediateResultExpected) {
            return null;
        }
        this.mIsIntermediateResultExpected = z;
        return new ArrayList(this.mCallbacks);
    }

    @Nullable
    public synchronized List<ProducerContextCallbacks> cancelNoCallbacks() {
        if (this.mIsCancelled) {
            return null;
        }
        this.mIsCancelled = true;
        return new ArrayList(this.mCallbacks);
    }

    public static void callOnCancellationRequested(@Nullable List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (ProducerContextCallbacks producerContextCallbacks : list) {
                producerContextCallbacks.onCancellationRequested();
            }
        }
    }

    public static void callOnIsPrefetchChanged(@Nullable List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (ProducerContextCallbacks producerContextCallbacks : list) {
                producerContextCallbacks.onIsPrefetchChanged();
            }
        }
    }

    public static void callOnIsIntermediateResultExpectedChanged(@Nullable List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (ProducerContextCallbacks producerContextCallbacks : list) {
                producerContextCallbacks.onIsIntermediateResultExpectedChanged();
            }
        }
    }

    public static void callOnPriorityChanged(@Nullable List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (ProducerContextCallbacks producerContextCallbacks : list) {
                producerContextCallbacks.onPriorityChanged();
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void setExtra(String str, @Nullable Object obj) {
        if (!INITIAL_KEYS.contains(str)) {
            this.mExtras.put(str, obj);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void putExtras(@Nullable Map<String, ?> map) {
        if (map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                setExtra(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    @Nullable
    public <T> T getExtra(String str) {
        return (T) this.mExtras.get(str);
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    @Nullable
    public <E> E getExtra(String str, E e) {
        E e2 = (E) this.mExtras.get(str);
        return e2 == null ? e : e2;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void putOriginExtra(@Nullable String str, @Nullable String str2) {
        this.mExtras.put("origin", str);
        this.mExtras.put(ProducerContext.ExtraKeys.ORIGIN_SUBCATEGORY, str2);
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void putOriginExtra(@Nullable String str) {
        putOriginExtra(str, ORIGIN_SUBCATEGORY_DEFAULT);
    }
}
