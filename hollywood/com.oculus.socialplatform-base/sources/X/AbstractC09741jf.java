package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1jf  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09741jf<T> extends AnonymousClass0IL<T> {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.StatefulProducerRunnable";
    public final Consumer<T> A00;
    public final C10161kv A01;
    public final AnonymousClass1l6 A02;
    public final String A03;

    @Override // X.AnonymousClass0IL
    public final void A01() {
        AnonymousClass1l6 r3 = this.A02;
        C10161kv r2 = this.A01;
        String str = this.A03;
        r3.A9I(r2, str);
        r3.A7Y(r2, str, null);
        this.A00.A04();
    }

    @Override // X.AnonymousClass0IL
    public void A03(Exception exc) {
        AnonymousClass1l6 r3 = this.A02;
        C10161kv r2 = this.A01;
        String str = this.A03;
        r3.A9I(r2, str);
        r3.A7a(r2, str, exc, null);
        this.A00.A08(exc);
    }

    @Override // X.AnonymousClass0IL
    public final void A04(T t) {
        if (this instanceof AnonymousClass1l3) {
            return;
        }
        if (!(this instanceof C09591iv)) {
            AnonymousClass0PZ.A04(t);
        } else {
            AbstractC00820Ju.A03(t);
        }
    }

    @Override // X.AnonymousClass0IL
    public void A05(T t) {
        Map<String, String> map;
        String valueOf;
        AnonymousClass1l6 r4 = this.A02;
        C10161kv r3 = this.A01;
        String str = this.A03;
        if (r4.A9I(r3, str)) {
            if (this instanceof C09591iv) {
                boolean z = false;
                if (t != null) {
                    z = true;
                }
                valueOf = String.valueOf(z);
            } else if (this instanceof C09541ip) {
                boolean z2 = false;
                if (t != null) {
                    z2 = true;
                }
                valueOf = Boolean.toString(z2);
            }
            map = C00690Id.A00("createdThumbnail", valueOf);
            r4.A7c(r3, str, map);
            this.A00.A07(t, 1);
        }
        map = null;
        r4.A7c(r3, str, map);
        this.A00.A07(t, 1);
    }

    public AbstractC09741jf(Consumer<T> consumer, AnonymousClass1l6 r2, ProducerContext producerContext, String str) {
        this.A00 = consumer;
        this.A02 = r2;
        this.A03 = str;
        this.A01 = producerContext;
        r2.A7e(producerContext, str);
    }
}
