package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1pm  reason: invalid class name */
public abstract class AnonymousClass1pm<T> extends AnonymousClass1lM<T> {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.StatefulProducerRunnable";
    public final Consumer<T> A00;
    public final AnonymousClass1qU A01;
    public final AnonymousClass1qE A02;
    public final String A03;

    @Override // X.AnonymousClass1lM
    public void A01(Exception exc) {
        AnonymousClass1qE r3 = this.A02;
        AnonymousClass1qU r2 = this.A01;
        String str = this.A03;
        r3.A8K(r2, str);
        r3.A6X(r2, str, exc, null);
        this.A00.A07(exc);
    }

    @Override // X.AnonymousClass1lM
    public final void A02(T t) {
        if (this instanceof AnonymousClass1rS) {
            return;
        }
        if (!(this instanceof AnonymousClass1p1)) {
            AnonymousClass1qQ.A03(t);
            return;
        }
        T t2 = t;
        if (t2 != null) {
            t2.close();
        }
    }

    @Override // X.AnonymousClass1lM
    public void A03(T t) {
        Map<String, String> map;
        String valueOf;
        AnonymousClass1qE r4 = this.A02;
        AnonymousClass1qU r3 = this.A01;
        String str = this.A03;
        if (r4.A8K(r3, str)) {
            if (this instanceof AnonymousClass1p1) {
                boolean z = false;
                if (t != null) {
                    z = true;
                }
                valueOf = String.valueOf(z);
            } else if (this instanceof C09921qP) {
                boolean z2 = false;
                if (t != null) {
                    z2 = true;
                }
                valueOf = Boolean.toString(z2);
            }
            map = AnonymousClass0KP.A00("createdThumbnail", valueOf);
            r4.A6Z(r3, str, map);
            this.A00.A06(t, 1);
        }
        map = null;
        r4.A6Z(r3, str, map);
        this.A00.A06(t, 1);
    }

    public AnonymousClass1pm(Consumer<T> consumer, AnonymousClass1qE r2, ProducerContext producerContext, String str) {
        this.A00 = consumer;
        this.A02 = r2;
        this.A03 = str;
        this.A01 = producerContext;
        r2.A6b(producerContext, str);
    }
}
