package X;

import bolts.Continuation;
import bolts.Task;
import java.util.Map;
import java.util.concurrent.CancellationException;

/* renamed from: X.1kG  reason: invalid class name */
public class AnonymousClass1kG implements Continuation<AnonymousClass0PZ, Void> {
    public final /* synthetic */ AbstractC10011kf A00;
    public final /* synthetic */ C10161kv A01;
    public final /* synthetic */ AnonymousClass1kH A02;
    public final /* synthetic */ AnonymousClass1l6 A03;

    public AnonymousClass1kG(AnonymousClass1kH r1, AnonymousClass1l6 r2, C10161kv r3, AbstractC10011kf r4) {
        this.A02 = r1;
        this.A03 = r2;
        this.A01 = r3;
        this.A00 = r4;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [bolts.Task] */
    @Override // bolts.Continuation
    public final Void then(Task<AnonymousClass0PZ> task) throws Exception {
        boolean z;
        Map<String, String> A002;
        Map<String, String> A012;
        if (task.isCancelled() || (task.isFaulted() && (task.getError() instanceof CancellationException))) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.A03.A7Y(this.A01, "DiskCacheProducer", null);
            this.A00.A04();
            return null;
        } else if (task.isFaulted()) {
            AnonymousClass1l6 r1 = this.A03;
            C10161kv r2 = this.A01;
            r1.A7a(r2, "DiskCacheProducer", task.getError(), null);
            this.A02.A00.A8d(this.A00, r2);
            return null;
        } else {
            AnonymousClass0PZ result = task.getResult();
            if (result != null) {
                AnonymousClass1l6 r9 = this.A03;
                C10161kv r8 = this.A01;
                int A09 = result.A09();
                if (!r9.A9I(r8, "DiskCacheProducer")) {
                    A012 = null;
                } else {
                    A012 = C00690Id.A01("cached_value_found", String.valueOf(true), "encodedImageSize", String.valueOf(A09));
                }
                r9.A7c(r8, "DiskCacheProducer", A012);
                r9.A8F(r8, "DiskCacheProducer", true);
                r8.A06("disk", "default");
                AbstractC10011kf r12 = this.A00;
                r12.A06(1.0f);
                r12.A07(result, 1);
                result.close();
                return null;
            }
            AnonymousClass1l6 r4 = this.A03;
            C10161kv r3 = this.A01;
            if (!r4.A9I(r3, "DiskCacheProducer")) {
                A002 = null;
            } else {
                A002 = C00690Id.A00("cached_value_found", String.valueOf(false));
            }
            r4.A7c(r3, "DiskCacheProducer", A002);
            this.A02.A00.A8d(this.A00, r3);
            return null;
        }
    }
}
