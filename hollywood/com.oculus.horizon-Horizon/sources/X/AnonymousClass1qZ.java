package X;

import android.util.Pair;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Iterator;

/* renamed from: X.1qZ  reason: invalid class name */
public class AnonymousClass1qZ extends AnonymousClass1qD<T> {
    public final /* synthetic */ C09931qV A00;

    public AnonymousClass1qZ(C09931qV r1) {
        this.A00 = r1;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1qD
    public final void A04(float f) {
        Throwable th;
        try {
            AnonymousClass1zo.A00();
            C09931qV r1 = this.A00;
            synchronized (r1) {
                try {
                    if (r1.A03 == this) {
                        r1.A00 = f;
                        Iterator<Pair<Consumer<T>, ProducerContext>> it = r1.A06.iterator();
                        while (it.hasNext()) {
                            Pair<Consumer<T>, ProducerContext> next = it.next();
                            synchronized (next) {
                                try {
                                    ((AnonymousClass1qD) next.first).A05(f);
                                } catch (Throwable th2) {
                                    th = th2;
                                    throw th;
                                }
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    throw th;
                }
            }
            AnonymousClass1zo.A00();
        } catch (Throwable th4) {
            AnonymousClass1zo.A00();
            throw th4;
        }
    }
}
