package X;

import android.util.Pair;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Iterator;

/* renamed from: X.1ky  reason: invalid class name and case insensitive filesystem */
public class C10181ky extends AbstractC10011kf<T> {
    public final /* synthetic */ C10171kw A00;

    public C10181ky(C10171kw r1) {
        this.A00 = r1;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC10011kf
    public final void A05(float f) {
        try {
            C01060Pq.A00();
            C10171kw r1 = this.A00;
            synchronized (r1) {
                if (r1.A03 == this) {
                    r1.A00 = f;
                    Iterator<Pair<Consumer<T>, ProducerContext>> it = r1.A06.iterator();
                    while (it.hasNext()) {
                        Pair<Consumer<T>, ProducerContext> next = it.next();
                        synchronized (next) {
                            ((AbstractC10011kf) next.first).A06(f);
                        }
                    }
                }
            }
            C01060Pq.A00();
        } catch (Throwable th) {
            C01060Pq.A00();
            throw th;
        }
    }
}
