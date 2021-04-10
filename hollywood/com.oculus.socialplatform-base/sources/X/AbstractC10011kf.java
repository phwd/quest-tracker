package X;

import android.util.Pair;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1kf  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC10011kf<T> {
    public boolean A00 = false;

    /* JADX INFO: finally extract failed */
    public final synchronized void A04() {
        if (!this.A00) {
            this.A00 = true;
            try {
                if (this instanceof C10181ky) {
                    AnonymousClass1kx<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer = (C10181ky) this;
                    try {
                        C01060Pq.A00();
                        C10171kw r2 = forwardingConsumer.A00;
                        synchronized (r2) {
                            if (r2.A03 == forwardingConsumer) {
                                r2.A03 = null;
                                r2.A02 = null;
                                C10171kw.A05(r2.A04);
                                r2.A04 = null;
                                C10171kw.A04(r2, TriState.UNSET);
                            }
                        }
                        C01060Pq.A00();
                    } catch (Throwable th) {
                        C01060Pq.A00();
                        throw th;
                    }
                } else if (!(this instanceof AbstractC09791jm)) {
                    AbstractC10121kr r1 = ((AnonymousClass1l0) this).A00;
                    synchronized (r1) {
                        C00740Ii.A03(r1.A04());
                    }
                } else {
                    AbstractC09791jm r12 = (AbstractC09791jm) this;
                    if (r12 instanceof C10061kk) {
                        C10061kk r13 = (C10061kk) r12;
                        ((AbstractC09791jm) r13).A00.A04();
                        C10061kk.A00(r13);
                    } else if (!(r12 instanceof AbstractC09701ja)) {
                        r12.A00.A04();
                    } else {
                        AbstractC09701ja r14 = (AbstractC09701ja) r12;
                        AbstractC09701ja.A01(r14, true);
                        ((AbstractC09791jm) r14).A00.A04();
                    }
                }
            } catch (Exception e) {
                A03(e);
            }
        }
    }

    public void A05(float f) {
    }

    public final synchronized void A06(float f) {
        if (!this.A00) {
            try {
                A05(f);
            } catch (Exception e) {
                A03(e);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for r0v40, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX DEBUG: Multi-variable search result rejected for r1v22, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v163, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0348, code lost:
        if (r1.contains(java.lang.Integer.valueOf(r13.A00)) != false) goto L_0x0356;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0354, code lost:
        if (r8.canResize(r13, r7, null) != false) goto L_0x0356;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0135, code lost:
        if (((int) (((float) r7) * 1.3333334f)) >= 2048) goto L_0x0137;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void A07(@javax.annotation.Nullable T r13, int r14) {
        /*
        // Method dump skipped, instructions count: 1149
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC10011kf.A07(java.lang.Object, int):void");
    }

    /* JADX INFO: finally extract failed */
    public final synchronized void A08(Throwable th) {
        if (!this.A00) {
            this.A00 = true;
            try {
                if (this instanceof C10181ky) {
                    AnonymousClass1kx<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer = (C10181ky) this;
                    try {
                        C01060Pq.A00();
                        AnonymousClass1kx<K, T>.Multiplexer multiplexer = forwardingConsumer.A00;
                        synchronized (multiplexer) {
                            if (multiplexer.A03 == forwardingConsumer) {
                                CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> copyOnWriteArraySet = multiplexer.A06;
                                Iterator<Pair<Consumer<T>, ProducerContext>> it = copyOnWriteArraySet.iterator();
                                copyOnWriteArraySet.clear();
                                AnonymousClass1kx r5 = multiplexer.A07;
                                r5.A00(multiplexer.A05, multiplexer);
                                C10171kw.A05(multiplexer.A04);
                                multiplexer.A04 = null;
                                while (it.hasNext()) {
                                    Pair<Consumer<T>, ProducerContext> next = it.next();
                                    synchronized (next) {
                                        ProducerContext producerContext = (C10161kv) next.second;
                                        producerContext.A05.A7a(producerContext, r5.A02, th, null);
                                        ((AbstractC10011kf) next.first).A08(th);
                                    }
                                }
                            }
                        }
                        C01060Pq.A00();
                    } catch (Throwable th2) {
                        C01060Pq.A00();
                        throw th2;
                    }
                } else if (!(this instanceof AbstractC09791jm)) {
                    AbstractC10121kr r2 = ((AnonymousClass1l0) this).A00;
                    AnonymousClass1l1 r1 = r2.A01;
                    if (r2.A07(th, r1.A0C)) {
                        r2.A00.A7q(r1, th);
                    }
                } else {
                    AbstractC09791jm r3 = (AbstractC09791jm) this;
                    if (r3 instanceof C10061kk) {
                        C10061kk r32 = (C10061kk) r3;
                        ((AbstractC09791jm) r32).A00.A08(th);
                        C10061kk.A00(r32);
                    } else if (r3 instanceof AbstractC09701ja) {
                        AbstractC09701ja r33 = (AbstractC09701ja) r3;
                        AbstractC09701ja.A01(r33, true);
                        ((AbstractC09791jm) r33).A00.A08(th);
                    } else if (!(r3 instanceof AnonymousClass1jI)) {
                        r3.A00.A08(th);
                    } else {
                        AnonymousClass1jI r34 = (AnonymousClass1jI) r3;
                        r34.A01.A00.A8d(((AbstractC09791jm) r34).A00, r34.A00);
                    }
                }
            } catch (Exception e) {
                A03(e);
            }
        }
    }

    private final void A03(Exception exc) {
        Class<?> cls = getClass();
        AnonymousClass0J6 r1 = AnonymousClass0J5.A00;
        if (r1.A64(6)) {
            r1.ABN(cls.getSimpleName(), "unhandled exception", exc);
        }
    }
}
