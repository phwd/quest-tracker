package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Closeables;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public abstract class LocalFetchProducer implements Producer<EncodedImage> {
    private final Executor mExecutor;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* access modifiers changed from: protected */
    public abstract EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException;

    /* access modifiers changed from: protected */
    public abstract String getProducerName();

    protected LocalFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra("local", "fetch");
        final AnonymousClass1 r9 = new StatefulProducerRunnable<EncodedImage>(consumer, getProducerName(), producerListener, producerContext) {
            /* class com.facebook.imagepipeline.producers.LocalFetchProducer.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            @Nullable
            public EncodedImage getResult() throws Exception {
                EncodedImage encodedImage = LocalFetchProducer.this.getEncodedImage(imageRequest);
                if (encodedImage == null) {
                    producerListener.onUltimateProducerReached(producerContext, LocalFetchProducer.this.getProducerName(), false);
                    producerContext.putOriginExtra("local");
                    return null;
                }
                encodedImage.parseMetaData();
                producerListener.onUltimateProducerReached(producerContext, LocalFetchProducer.this.getProducerName(), true);
                producerContext.putOriginExtra("local");
                return encodedImage;
            }

            /* access modifiers changed from: protected */
            public void disposeResult(EncodedImage encodedImage) {
                EncodedImage.closeSafely(encodedImage);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            /* class com.facebook.imagepipeline.producers.LocalFetchProducer.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                r9.cancel();
            }
        });
        this.mExecutor.execute(r9);
    }

    /* access modifiers changed from: protected */
    public EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream, int i) throws IOException {
        CloseableReference closeableReference;
        if (i <= 0) {
            try {
                closeableReference = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream));
            } catch (Throwable th) {
                Closeables.closeQuietly(inputStream);
                CloseableReference.closeSafely((CloseableReference<?>) null);
                throw th;
            }
        } else {
            closeableReference = CloseableReference.of(this.mPooledByteBufferFactory.newByteBuffer(inputStream, i));
        }
        EncodedImage encodedImage = new EncodedImage(closeableReference);
        Closeables.closeQuietly(inputStream);
        CloseableReference.closeSafely(closeableReference);
        return encodedImage;
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(InputStream inputStream, int i) throws IOException {
        return getByteBufferBackedEncodedImage(inputStream, i);
    }
}
