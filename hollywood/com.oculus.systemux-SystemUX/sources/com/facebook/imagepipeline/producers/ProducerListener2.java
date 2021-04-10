package com.facebook.imagepipeline.producers;

import androidx.annotation.NonNull;
import java.util.Map;
import javax.annotation.Nullable;

public interface ProducerListener2 {
    void onProducerEvent(@NonNull ProducerContext producerContext, @NonNull String str, @NonNull String str2);

    void onProducerFinishWithCancellation(@NonNull ProducerContext producerContext, @NonNull String str, @Nullable Map<String, String> map);

    void onProducerFinishWithFailure(@NonNull ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map);

    void onProducerFinishWithSuccess(@NonNull ProducerContext producerContext, @NonNull String str, @Nullable Map<String, String> map);

    void onProducerStart(@NonNull ProducerContext producerContext, @NonNull String str);

    void onUltimateProducerReached(@NonNull ProducerContext producerContext, @NonNull String str, boolean z);

    boolean requiresExtraMap(@NonNull ProducerContext producerContext, @NonNull String str);
}
