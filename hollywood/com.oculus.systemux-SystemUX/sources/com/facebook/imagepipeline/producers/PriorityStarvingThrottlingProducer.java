package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.Priority;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public class PriorityStarvingThrottlingProducer<T> implements Producer<T> {
    public static final String PRODUCER_NAME = "PriorityStarvingThrottlingProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;
    private final int mMaxSimultaneousRequests;
    @GuardedBy("this")
    private int mNumCurrentRequests = 0;
    @GuardedBy("this")
    private final Queue<Item<T>> mPendingRequests = new PriorityQueue(11, new PriorityComparator());

    static /* synthetic */ int access$210(PriorityStarvingThrottlingProducer priorityStarvingThrottlingProducer) {
        int i = priorityStarvingThrottlingProducer.mNumCurrentRequests;
        priorityStarvingThrottlingProducer.mNumCurrentRequests = i - 1;
        return i;
    }

    public PriorityStarvingThrottlingProducer(int i, Executor executor, Producer<T> producer) {
        this.mMaxSimultaneousRequests = i;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        boolean z;
        long nanoTime = System.nanoTime();
        producerContext.getProducerListener().onProducerStart(producerContext, PRODUCER_NAME);
        synchronized (this) {
            z = true;
            if (this.mNumCurrentRequests >= this.mMaxSimultaneousRequests) {
                this.mPendingRequests.add(new Item<>(consumer, producerContext, nanoTime));
            } else {
                this.mNumCurrentRequests++;
                z = false;
            }
        }
        if (!z) {
            produceResultsInternal(new Item<>(consumer, producerContext, nanoTime));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void produceResultsInternal(Item<T> item) {
        item.producerContext.getProducerListener().onProducerFinishWithSuccess(item.producerContext, PRODUCER_NAME, null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(item.consumer), item.producerContext);
    }

    /* access modifiers changed from: package-private */
    public static class Item<T> {
        final Consumer<T> consumer;
        final ProducerContext producerContext;
        final long time;

        Item(Consumer<T> consumer2, ProducerContext producerContext2, long j) {
            this.consumer = consumer2;
            this.producerContext = producerContext2;
            this.time = j;
        }
    }

    static class PriorityComparator<T> implements Comparator<Item<T>> {
        PriorityComparator() {
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return compare((Item) ((Item) obj), (Item) ((Item) obj2));
        }

        public int compare(Item<T> item, Item<T> item2) {
            Priority priority = item.producerContext.getPriority();
            Priority priority2 = item2.producerContext.getPriority();
            if (priority == priority2) {
                return Double.compare((double) item.time, (double) item2.time);
            }
            return priority.ordinal() > priority2.ordinal() ? -1 : 1;
        }
    }

    /* access modifiers changed from: private */
    public class ThrottlerConsumer extends DelegatingConsumer<T, T> {
        private ThrottlerConsumer(Consumer<T> consumer) {
            super(consumer);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(T t, int i) {
            getConsumer().onNewResult(t, i);
            if (isLast(i)) {
                onRequestFinished();
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer, com.facebook.imagepipeline.producers.DelegatingConsumer
        public void onFailureImpl(Throwable th) {
            getConsumer().onFailure(th);
            onRequestFinished();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer, com.facebook.imagepipeline.producers.DelegatingConsumer
        public void onCancellationImpl() {
            getConsumer().onCancellation();
            onRequestFinished();
        }

        private void onRequestFinished() {
            final Item item;
            synchronized (PriorityStarvingThrottlingProducer.this) {
                item = (Item) PriorityStarvingThrottlingProducer.this.mPendingRequests.poll();
                if (item == null) {
                    PriorityStarvingThrottlingProducer.access$210(PriorityStarvingThrottlingProducer.this);
                }
            }
            if (item != null) {
                PriorityStarvingThrottlingProducer.this.mExecutor.execute(new Runnable() {
                    /* class com.facebook.imagepipeline.producers.PriorityStarvingThrottlingProducer.ThrottlerConsumer.AnonymousClass1 */

                    public void run() {
                        PriorityStarvingThrottlingProducer.this.produceResultsInternal(item);
                    }
                });
            }
        }
    }
}
