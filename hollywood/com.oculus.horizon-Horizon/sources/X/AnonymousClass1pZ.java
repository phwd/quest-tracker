package X;

import java.util.Map;
import java.util.concurrent.CancellationException;
import retrofit.Endpoints;

/* renamed from: X.1pZ  reason: invalid class name */
public class AnonymousClass1pZ implements AnonymousClass0D4<AnonymousClass1qQ, Void> {
    public final /* synthetic */ AnonymousClass1qD A00;
    public final /* synthetic */ AnonymousClass1qU A01;
    public final /* synthetic */ AnonymousClass1pa A02;
    public final /* synthetic */ AnonymousClass1qE A03;

    public AnonymousClass1pZ(AnonymousClass1pa r1, AnonymousClass1qE r2, AnonymousClass1qU r3, AnonymousClass1qD r4) {
        this.A02 = r1;
        this.A03 = r2;
        this.A01 = r3;
        this.A00 = r4;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0DC] */
    @Override // X.AnonymousClass0D4
    public final Void then(AnonymousClass0DC<AnonymousClass1qQ> r11) throws Exception {
        boolean z;
        Map<String, String> A002;
        Map<String, String> A012;
        if (r11.A0I() || (r11.A0K() && (r11.A0F() instanceof CancellationException))) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.A03.A6V(this.A01, "DiskCacheProducer", null);
            this.A00.A03();
            return null;
        } else if (r11.A0K()) {
            AnonymousClass1qE r1 = this.A03;
            AnonymousClass1qU r2 = this.A01;
            r1.A6X(r2, "DiskCacheProducer", r11.A0F(), null);
            this.A02.A00.A7a(this.A00, r2);
            return null;
        } else {
            AnonymousClass1qQ A0G = r11.A0G();
            if (A0G != null) {
                AnonymousClass1qE r9 = this.A03;
                AnonymousClass1qU r8 = this.A01;
                int A07 = A0G.A07();
                if (!r9.A8K(r8, "DiskCacheProducer")) {
                    A012 = null;
                } else {
                    A012 = AnonymousClass0KP.A01("cached_value_found", String.valueOf(true), "encodedImageSize", String.valueOf(A07));
                }
                r9.A6Z(r8, "DiskCacheProducer", A012);
                r9.A77(r8, "DiskCacheProducer", true);
                r8.A06("disk", Endpoints.DEFAULT_NAME);
                AnonymousClass1qD r12 = this.A00;
                r12.A05(1.0f);
                r12.A06(A0G, 1);
                A0G.close();
                return null;
            }
            AnonymousClass1qE r4 = this.A03;
            AnonymousClass1qU r3 = this.A01;
            if (!r4.A8K(r3, "DiskCacheProducer")) {
                A002 = null;
            } else {
                A002 = AnonymousClass0KP.A00("cached_value_found", String.valueOf(false));
            }
            r4.A6Z(r3, "DiskCacheProducer", A002);
            this.A02.A00.A7a(this.A00, r3);
            return null;
        }
    }
}
