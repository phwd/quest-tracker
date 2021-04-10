package X;

import android.util.Pair;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1qD  reason: invalid class name */
public abstract class AnonymousClass1qD<T> {
    public boolean A00 = false;

    public final synchronized void A03() {
        Throwable th;
        if (!this.A00) {
            this.A00 = true;
            try {
                if (this instanceof AnonymousClass1qZ) {
                    AnonymousClass1qW<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer = (AnonymousClass1qZ) this;
                    try {
                        AnonymousClass1zo.A00();
                        C09931qV r2 = forwardingConsumer.A00;
                        synchronized (r2) {
                            if (r2.A03 == forwardingConsumer) {
                                r2.A03 = null;
                                r2.A02 = null;
                                C09931qV.A05(r2.A04);
                                r2.A04 = null;
                                C09931qV.A04(r2, TriState.UNSET);
                            }
                        }
                        AnonymousClass1zo.A00();
                    } catch (Throwable th2) {
                        th = th2;
                        AnonymousClass1zo.A00();
                        throw th;
                    }
                } else if (!(this instanceof AnonymousClass1rX)) {
                    AnonymousClass1r0 r1 = ((AnonymousClass1rC) this).A00;
                    synchronized (r1) {
                        try {
                            AnonymousClass0KU.A03(r1.A05);
                        } catch (Throwable th3) {
                            th = th3;
                            throw th;
                        }
                    }
                } else {
                    AnonymousClass1rX r12 = (AnonymousClass1rX) this;
                    if (r12 instanceof AnonymousClass1qB) {
                        AnonymousClass1qB r13 = (AnonymousClass1qB) r12;
                        ((AnonymousClass1rX) r13).A00.A03();
                        AnonymousClass1qB.A00(r13);
                    } else if (!(r12 instanceof AnonymousClass1qN)) {
                        r12.A00.A03();
                    } else {
                        AnonymousClass1qN r14 = (AnonymousClass1qN) r12;
                        AnonymousClass1qN.A01(r14, true);
                        ((AnonymousClass1rX) r14).A00.A03();
                    }
                }
            } catch (Exception e) {
                A01(e);
            }
        }
    }

    public void A04(float f) {
    }

    public final synchronized void A05(float f) {
        if (!this.A00) {
            try {
                A04(f);
            } catch (Exception e) {
                A01(e);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final synchronized void A07(Throwable th) {
        Throwable th2;
        if (!this.A00) {
            this.A00 = true;
            try {
                if (this instanceof AnonymousClass1qZ) {
                    AnonymousClass1qW<K, T>.Multiplexer.ForwardingConsumer forwardingConsumer = (AnonymousClass1qZ) this;
                    try {
                        AnonymousClass1zo.A00();
                        AnonymousClass1qW<K, T>.Multiplexer multiplexer = forwardingConsumer.A00;
                        synchronized (multiplexer) {
                            try {
                                if (multiplexer.A03 == forwardingConsumer) {
                                    CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> copyOnWriteArraySet = multiplexer.A06;
                                    Iterator<Pair<Consumer<T>, ProducerContext>> it = copyOnWriteArraySet.iterator();
                                    copyOnWriteArraySet.clear();
                                    AnonymousClass1qW r5 = multiplexer.A07;
                                    r5.A00(multiplexer.A05, multiplexer);
                                    C09931qV.A05(multiplexer.A04);
                                    multiplexer.A04 = null;
                                    while (it.hasNext()) {
                                        Pair<Consumer<T>, ProducerContext> next = it.next();
                                        synchronized (next) {
                                            try {
                                                ProducerContext producerContext = (AnonymousClass1qU) next.second;
                                                producerContext.A05.A6X(producerContext, r5.A02, th, null);
                                                ((AnonymousClass1qD) next.first).A07(th);
                                            } catch (Throwable th3) {
                                                th2 = th3;
                                                throw th2;
                                            }
                                        }
                                    }
                                }
                            } catch (Throwable th4) {
                                th2 = th4;
                                throw th2;
                            }
                        }
                        AnonymousClass1zo.A00();
                    } catch (Throwable th5) {
                        AnonymousClass1zo.A00();
                        throw th5;
                    }
                } else if (!(this instanceof AnonymousClass1rX)) {
                    AnonymousClass1r0 r2 = ((AnonymousClass1rC) this).A00;
                    AnonymousClass1rN r1 = r2.A01;
                    if (r2.A08(th, r1.A0C)) {
                        r2.A00.A6l(r1, th);
                    }
                } else {
                    AnonymousClass1rX r3 = (AnonymousClass1rX) this;
                    if (r3 instanceof AnonymousClass1qB) {
                        AnonymousClass1qB r32 = (AnonymousClass1qB) r3;
                        ((AnonymousClass1rX) r32).A00.A07(th);
                        AnonymousClass1qB.A00(r32);
                    } else if (r3 instanceof AnonymousClass1qN) {
                        AnonymousClass1qN r33 = (AnonymousClass1qN) r3;
                        AnonymousClass1qN.A01(r33, true);
                        ((AnonymousClass1rX) r33).A00.A07(th);
                    } else if (!(r3 instanceof C09881pu)) {
                        r3.A00.A07(th);
                    } else {
                        C09881pu r34 = (C09881pu) r3;
                        r34.A01.A00.A7a(((AnonymousClass1rX) r34).A00, r34.A00);
                    }
                }
            } catch (Exception e) {
                A01(e);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for r0v40, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX DEBUG: Multi-variable search result rejected for r1v22, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v153, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v164, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v166, resolved type: com.facebook.imagepipeline.producers.Consumer<O> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x014b, code lost:
        if (((int) (((float) r8) * 1.3333334f)) >= 2048) goto L_0x014d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01a9, code lost:
        if (r10.A0A != null) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x036a, code lost:
        if (r1.contains(java.lang.Integer.valueOf(r10.A00)) != false) goto L_0x0378;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x0376, code lost:
        if (r11.canResize(r10, r8, null) != false) goto L_0x0378;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0101, code lost:
        if (r3 == null) goto L_0x0498;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void A06(@javax.annotation.Nullable T r18, int r19) {
        /*
        // Method dump skipped, instructions count: 1191
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qD.A06(java.lang.Object, int):void");
    }

    private final void A01(Exception exc) {
        Class<?> cls = getClass();
        AbstractC01090Kc r1 = C01080Kb.A00;
        if (r1.A54(6)) {
            r1.AAL(cls.getSimpleName(), "unhandled exception", exc);
        }
    }
}
