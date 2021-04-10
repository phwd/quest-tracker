package X;

import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@VisibleForTesting
/* renamed from: X.1qV  reason: invalid class name and case insensitive filesystem */
public class C09931qV {
    @GuardedBy("Multiplexer.this")
    public float A00;
    @GuardedBy("Multiplexer.this")
    public int A01;
    @GuardedBy("Multiplexer.this")
    @Nullable
    public AnonymousClass1qU A02;
    @GuardedBy("Multiplexer.this")
    @Nullable
    public AnonymousClass1qW<K, T>.Multiplexer.ForwardingConsumer A03;
    @GuardedBy("Multiplexer.this")
    @Nullable
    public T A04;
    public final K A05;
    public final CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> A06 = new CopyOnWriteArraySet<>();
    public final /* synthetic */ AnonymousClass1qW A07;

    private synchronized EnumC10221rl A00() {
        EnumC10221rl r3;
        EnumC10221rl r0;
        r3 = EnumC10221rl.LOW;
        Iterator<Pair<Consumer<T>, ProducerContext>> it = this.A06.iterator();
        while (it.hasNext()) {
            AnonymousClass1qU r1 = (AnonymousClass1qU) it.next().second;
            synchronized (r1) {
                r0 = r1.A00;
            }
            r3 = EnumC10221rl.getHigherPriority(r3, r0);
        }
        return r3;
    }

    /* JADX WARN: Incorrect args count in method signature: ()Ljava/util/List<Lcom/facebook/imagepipeline/producers/ProducerContextCallbacks;>; */
    @Nullable
    public static synchronized List A01(C09931qV r3) {
        ArrayList arrayList;
        synchronized (r3) {
            AnonymousClass1qU r2 = r3.A02;
            if (r2 == null) {
                return null;
            }
            boolean A062 = r3.A06();
            synchronized (r2) {
                if (A062 == r2.A02) {
                    arrayList = null;
                } else {
                    r2.A02 = A062;
                    arrayList = new ArrayList(r2.A0B);
                }
            }
            return arrayList;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: ()Ljava/util/List<Lcom/facebook/imagepipeline/producers/ProducerContextCallbacks;>; */
    @Nullable
    public static synchronized List A02(C09931qV r3) {
        ArrayList arrayList;
        synchronized (r3) {
            AnonymousClass1qU r2 = r3.A02;
            if (r2 == null) {
                return null;
            }
            boolean A072 = r3.A07();
            synchronized (r2) {
                if (A072 == r2.A03) {
                    arrayList = null;
                } else {
                    r2.A03 = A072;
                    arrayList = new ArrayList(r2.A0B);
                }
            }
            return arrayList;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: ()Ljava/util/List<Lcom/facebook/imagepipeline/producers/ProducerContextCallbacks;>; */
    @Nullable
    public static synchronized List A03(C09931qV r3) {
        ArrayList arrayList;
        synchronized (r3) {
            AnonymousClass1qU r2 = r3.A02;
            if (r2 == null) {
                arrayList = null;
            } else {
                EnumC10221rl A002 = r3.A00();
                synchronized (r2) {
                    if (A002 == r2.A00) {
                        arrayList = null;
                    } else {
                        r2.A00 = A002;
                        arrayList = new ArrayList(r2.A0B);
                    }
                }
            }
        }
        return arrayList;
    }

    public static void A04(C09931qV r12, TriState triState) {
        synchronized (r12) {
            boolean z = true;
            boolean z2 = false;
            if (r12.A02 == null) {
                z2 = true;
            }
            AnonymousClass0KU.A01(Boolean.valueOf(z2));
            if (r12.A03 != null) {
                z = false;
            }
            AnonymousClass0KU.A01(Boolean.valueOf(z));
            CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> copyOnWriteArraySet = r12.A06;
            if (copyOnWriteArraySet.isEmpty()) {
                r12.A07.A00(r12.A05, r12);
                return;
            }
            AnonymousClass1qU r0 = (AnonymousClass1qU) copyOnWriteArraySet.iterator().next().second;
            AnonymousClass1qU r1 = new AnonymousClass1qU(r0.A07, r0.A09, null, r0.A05, r0.A08, r0.A06, r12.A07(), r12.A06(), r12.A00(), r0.A04);
            r12.A02 = r1;
            r1.A07(r0.A0C);
            if (triState.isSet()) {
                r12.A02.A05("started_as_prefetch", Boolean.valueOf(triState.asBoolean()));
            }
            AnonymousClass1qZ r2 = new AnonymousClass1qZ(r12);
            r12.A03 = r2;
            r12.A07.A00.A7a(r2, r12.A02);
        }
    }

    private synchronized boolean A06() {
        boolean z;
        Iterator<Pair<Consumer<T>, ProducerContext>> it = this.A06.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((AnonymousClass1qU) it.next().second).A08()) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    private synchronized boolean A07() {
        boolean z;
        Iterator<Pair<Consumer<T>, ProducerContext>> it = this.A06.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!((AnonymousClass1qU) it.next().second).A09()) {
                    z = false;
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        return z;
    }

    public C09931qV(AnonymousClass1qW r2, K k) {
        this.A07 = r2;
        this.A05 = k;
    }

    public static void A05(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
