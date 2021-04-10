package com.facebook.imagepipeline.producers;

import java.util.Map;
import javax.annotation.Nullable;

public class InternalProducerListener implements ProducerListener2 {
    private final ProducerListener mProducerListener;
    @Nullable
    private final ProducerListener2 mProducerListener2;

    public InternalProducerListener(ProducerListener producerListener, @Nullable ProducerListener2 producerListener2) {
        this.mProducerListener = producerListener;
        this.mProducerListener2 = producerListener2;
    }

    public ProducerListener getProducerListener() {
        return this.mProducerListener;
    }

    @Nullable
    public ProducerListener2 getProducerListener2() {
        return this.mProducerListener2;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerStart(ProducerContext producerContext, String str) {
        ProducerListener producerListener = this.mProducerListener;
        if (producerListener != null) {
            producerListener.onProducerStart(producerContext.getId(), str);
        }
        ProducerListener2 producerListener2 = this.mProducerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerStart(producerContext, str);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerEvent(ProducerContext producerContext, String str, String str2) {
        ProducerListener producerListener = this.mProducerListener;
        if (producerListener != null) {
            producerListener.onProducerEvent(producerContext.getId(), str, str2);
        }
        ProducerListener2 producerListener2 = this.mProducerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerEvent(producerContext, str, str2);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithSuccess(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        ProducerListener producerListener = this.mProducerListener;
        if (producerListener != null) {
            producerListener.onProducerFinishWithSuccess(producerContext.getId(), str, map);
        }
        ProducerListener2 producerListener2 = this.mProducerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerFinishWithSuccess(producerContext, str, map);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithFailure(ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
        ProducerListener producerListener = this.mProducerListener;
        if (producerListener != null) {
            producerListener.onProducerFinishWithFailure(producerContext.getId(), str, th, map);
        }
        ProducerListener2 producerListener2 = this.mProducerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerFinishWithFailure(producerContext, str, th, map);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithCancellation(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        ProducerListener producerListener = this.mProducerListener;
        if (producerListener != null) {
            producerListener.onProducerFinishWithCancellation(producerContext.getId(), str, map);
        }
        ProducerListener2 producerListener2 = this.mProducerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerFinishWithCancellation(producerContext, str, map);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onUltimateProducerReached(ProducerContext producerContext, String str, boolean z) {
        ProducerListener producerListener = this.mProducerListener;
        if (producerListener != null) {
            producerListener.onUltimateProducerReached(producerContext.getId(), str, z);
        }
        ProducerListener2 producerListener2 = this.mProducerListener2;
        if (producerListener2 != null) {
            producerListener2.onUltimateProducerReached(producerContext, str, z);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public boolean requiresExtraMap(ProducerContext producerContext, String str) {
        ProducerListener2 producerListener2;
        ProducerListener producerListener = this.mProducerListener;
        boolean requiresExtraMap = producerListener != null ? producerListener.requiresExtraMap(producerContext.getId()) : false;
        return (requiresExtraMap || (producerListener2 = this.mProducerListener2) == null) ? requiresExtraMap : producerListener2.requiresExtraMap(producerContext, str);
    }
}
