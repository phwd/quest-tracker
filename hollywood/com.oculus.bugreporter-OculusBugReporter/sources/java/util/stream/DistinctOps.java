package java.util.stream;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.DistinctOps;
import java.util.stream.ReferencePipeline;
import java.util.stream.Sink;
import java.util.stream.StreamSpliterators;

final class DistinctOps {
    private DistinctOps() {
    }

    static <T> ReferencePipeline<T, T> makeRef(AbstractPipeline<?, T, ?> upstream) {
        return new ReferencePipeline.StatefulOp<T, T>(upstream, StreamShape.REFERENCE, StreamOpFlag.IS_DISTINCT | StreamOpFlag.NOT_SIZED) {
            /* class java.util.stream.DistinctOps.AnonymousClass1 */

            /* access modifiers changed from: package-private */
            public <P_IN> Node<T> reduce(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
                return Nodes.node((Collection) ReduceOps.makeRef($$Lambda$VQnU3Jki1RCSS5BYg_Kf6hQAY.INSTANCE, $$Lambda$zcFI7bYCRDtB1UMy72aPExbc6R4.INSTANCE, $$Lambda$r6LgDiay3Ow5w51ifJiV4dn8S84.INSTANCE).evaluateParallel(helper, spliterator));
            }

            @Override // java.util.stream.ReferencePipeline.StatefulOp, java.util.stream.AbstractPipeline
            public <P_IN> Node<T> opEvaluateParallel(PipelineHelper<T> helper, Spliterator<P_IN> spliterator, IntFunction<T[]> generator) {
                if (StreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
                    return helper.evaluate(spliterator, false, generator);
                }
                if (StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return reduce(helper, spliterator);
                }
                AtomicBoolean seenNull = new AtomicBoolean(false);
                ConcurrentHashMap<T, Boolean> map = new ConcurrentHashMap<>();
                ForEachOps.makeRef(new Consumer(map) {
                    /* class java.util.stream.$$Lambda$DistinctOps$1$y8chmSlaKpIKb_VPPBaXdCNT51c */
                    private final /* synthetic */ ConcurrentHashMap f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        DistinctOps.AnonymousClass1.lambda$opEvaluateParallel$0(AtomicBoolean.this, this.f$1, obj);
                    }
                }, false).evaluateParallel(helper, spliterator);
                Set<T> keys = map.keySet();
                if (seenNull.get()) {
                    keys = new HashSet<>(keys);
                    keys.add(null);
                }
                return Nodes.node(keys);
            }

            static /* synthetic */ void lambda$opEvaluateParallel$0(AtomicBoolean seenNull, ConcurrentHashMap map, Object t) {
                if (t == null) {
                    seenNull.set(true);
                } else {
                    map.putIfAbsent(t, Boolean.TRUE);
                }
            }

            @Override // java.util.stream.AbstractPipeline
            public <P_IN> Spliterator<T> opEvaluateParallelLazy(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
                    return helper.wrapSpliterator(spliterator);
                }
                if (StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
                    return reduce(helper, spliterator).spliterator();
                }
                return new StreamSpliterators.DistinctSpliterator(helper.wrapSpliterator(spliterator));
            }

            @Override // java.util.stream.AbstractPipeline
            public Sink<T> opWrapSink(int flags, Sink<T> sink) {
                Objects.requireNonNull(sink);
                if (StreamOpFlag.DISTINCT.isKnown(flags)) {
                    return sink;
                }
                return StreamOpFlag.SORTED.isKnown(flags) ? new Sink.ChainedReference<T, T>(sink) {
                    /* class java.util.stream.DistinctOps.AnonymousClass1.AnonymousClass1 */
                    T lastSeen;
                    boolean seenNull;

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void begin(long size) {
                        this.seenNull = false;
                        this.lastSeen = null;
                        this.downstream.begin(-1);
                    }

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void end() {
                        this.seenNull = false;
                        this.lastSeen = null;
                        this.downstream.end();
                    }

                    @Override // java.util.function.Consumer
                    public void accept(T t) {
                        if (t != null) {
                            T t2 = this.lastSeen;
                            if (t2 == null || !t.equals(t2)) {
                                Sink sink = this.downstream;
                                this.lastSeen = t;
                                sink.accept(t);
                            }
                        } else if (!this.seenNull) {
                            this.seenNull = true;
                            Sink sink2 = this.downstream;
                            this.lastSeen = null;
                            sink2.accept((Object) null);
                        }
                    }
                } : new Sink.ChainedReference<T, T>(sink) {
                    /* class java.util.stream.DistinctOps.AnonymousClass1.AnonymousClass2 */
                    Set<T> seen;

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void begin(long size) {
                        this.seen = new HashSet();
                        this.downstream.begin(-1);
                    }

                    @Override // java.util.stream.Sink.ChainedReference, java.util.stream.Sink
                    public void end() {
                        this.seen = null;
                        this.downstream.end();
                    }

                    @Override // java.util.function.Consumer
                    public void accept(T t) {
                        if (!this.seen.contains(t)) {
                            this.seen.add(t);
                            this.downstream.accept(t);
                        }
                    }
                };
            }
        };
    }
}
