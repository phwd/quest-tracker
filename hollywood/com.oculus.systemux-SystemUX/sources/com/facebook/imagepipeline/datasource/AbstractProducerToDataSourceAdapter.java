package com.facebook.imagepipeline.datasource;

import com.facebook.common.internal.Preconditions;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.BaseConsumer;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.request.HasImageRequest;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class AbstractProducerToDataSourceAdapter<T> extends AbstractDataSource<T> implements HasImageRequest {
    private final RequestListener2 mRequestListener;
    private final SettableProducerContext mSettableProducerContext;

    protected AbstractProducerToDataSourceAdapter(Producer<T> producer, SettableProducerContext settableProducerContext, RequestListener2 requestListener2) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()");
        }
        this.mSettableProducerContext = settableProducerContext;
        this.mRequestListener = requestListener2;
        setInitialExtras();
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()->onRequestStart");
        }
        this.mRequestListener.onRequestStart(this.mSettableProducerContext);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("AbstractProducerToDataSourceAdapter()->produceResult");
        }
        producer.produceResults(createConsumer(), settableProducerContext);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    private Consumer<T> createConsumer() {
        return new BaseConsumer<T>() {
            /* class com.facebook.imagepipeline.datasource.AbstractProducerToDataSourceAdapter.AnonymousClass1 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onNewResultImpl(@Nullable T t, int i) {
                AbstractProducerToDataSourceAdapter abstractProducerToDataSourceAdapter = AbstractProducerToDataSourceAdapter.this;
                abstractProducerToDataSourceAdapter.onNewResultImpl(t, i, abstractProducerToDataSourceAdapter.mSettableProducerContext);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onFailureImpl(Throwable th) {
                AbstractProducerToDataSourceAdapter.this.onFailureImpl(th);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onCancellationImpl() {
                AbstractProducerToDataSourceAdapter.this.onCancellationImpl();
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.BaseConsumer
            public void onProgressUpdateImpl(float f) {
                AbstractProducerToDataSourceAdapter.this.setProgress(f);
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onNewResultImpl(@Nullable T t, int i, ProducerContext producerContext) {
        boolean isLast = BaseConsumer.isLast(i);
        if (super.setResult(t, isLast, getExtras(producerContext)) && isLast) {
            this.mRequestListener.onRequestSuccess(this.mSettableProducerContext);
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getExtras(ProducerContext producerContext) {
        return producerContext.getExtras();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onFailureImpl(Throwable th) {
        if (super.setFailure(th, getExtras(this.mSettableProducerContext))) {
            this.mRequestListener.onRequestFailure(this.mSettableProducerContext, th);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void onCancellationImpl() {
        Preconditions.checkState(isClosed());
    }

    @Override // com.facebook.imagepipeline.request.HasImageRequest
    public ImageRequest getImageRequest() {
        return this.mSettableProducerContext.getImageRequest();
    }

    @Override // com.facebook.datasource.DataSource, com.facebook.datasource.AbstractDataSource
    public boolean close() {
        if (!super.close()) {
            return false;
        }
        if (super.isFinished()) {
            return true;
        }
        this.mRequestListener.onRequestCancellation(this.mSettableProducerContext);
        this.mSettableProducerContext.cancel();
        return true;
    }

    private void setInitialExtras() {
        setExtras(this.mSettableProducerContext.getExtras());
    }
}
