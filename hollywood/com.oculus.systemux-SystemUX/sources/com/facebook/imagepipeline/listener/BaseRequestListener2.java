package com.facebook.imagepipeline.listener;

import androidx.annotation.NonNull;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Map;
import javax.annotation.Nullable;

public class BaseRequestListener2 implements RequestListener2 {
    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerEvent(@NonNull ProducerContext producerContext, @NonNull String str, @NonNull String str2) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithCancellation(@NonNull ProducerContext producerContext, @NonNull String str, @Nullable Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithFailure(@NonNull ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithSuccess(@NonNull ProducerContext producerContext, @NonNull String str, @Nullable Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerStart(@NonNull ProducerContext producerContext, @NonNull String str) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestCancellation(@NonNull ProducerContext producerContext) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestFailure(@NonNull ProducerContext producerContext, Throwable th) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestStart(@NonNull ProducerContext producerContext) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestSuccess(@NonNull ProducerContext producerContext) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onUltimateProducerReached(@NonNull ProducerContext producerContext, @NonNull String str, boolean z) {
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public boolean requiresExtraMap(@NonNull ProducerContext producerContext, @NonNull String str) {
        return false;
    }
}
