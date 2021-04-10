package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import javax.annotation.Nullable;

public class ThreadHandoffProducer<T> implements Producer<T> {
    public static final String PRODUCER_NAME = "BackgroundThreadHandoffProducer";
    private final Producer<T> mInputProducer;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public ThreadHandoffProducer(Producer<T> producer, ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<T> consumer, final ProducerContext producerContext) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("ThreadHandoffProducer#produceResults");
            }
            final ProducerListener2 producerListener = producerContext.getProducerListener();
            final AnonymousClass1 r9 = new StatefulProducerRunnable<T>(PRODUCER_NAME, producerListener, producerContext, consumer) {
                /* class com.facebook.imagepipeline.producers.ThreadHandoffProducer.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
                public void disposeResult(T t) {
                }

                /* access modifiers changed from: protected */
                @Override // com.facebook.common.executors.StatefulRunnable
                @Nullable
                public T getResult() throws Exception {
                    return null;
                }

                /* access modifiers changed from: protected */
                @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
                public void onSuccess(T t) {
                    producerListener.onProducerFinishWithSuccess(producerContext, ThreadHandoffProducer.PRODUCER_NAME, null);
                    ThreadHandoffProducer.this.mInputProducer.produceResults(consumer, producerContext);
                }
            };
            producerContext.addCallbacks(new BaseProducerContextCallbacks() {
                /* class com.facebook.imagepipeline.producers.ThreadHandoffProducer.AnonymousClass2 */

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    r9.cancel();
                    ThreadHandoffProducer.this.mThreadHandoffProducerQueue.remove(r9);
                }
            });
            this.mThreadHandoffProducerQueue.addToQueueOrExecute(FrescoInstrumenter.decorateRunnable(r9, getInstrumentationTag(producerContext)));
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    @Nullable
    private static String getInstrumentationTag(ProducerContext producerContext) {
        if (!FrescoInstrumenter.isTracing()) {
            return null;
        }
        return "ThreadHandoffProducer_produceResults_" + producerContext.getId();
    }
}
