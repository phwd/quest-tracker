package com.oculus.alpenglow.http;

import X.AbstractC02990bJ;
import X.AnonymousClass0P6;
import X.AnonymousClass0Pj;
import X.AnonymousClass0Qe;
import X.AnonymousClass0R7;
import X.AnonymousClass13m;
import com.facebook.graphservice.interfaces.TreeJsonSerializer;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_alpenglow_http_HttpMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_common_time_Clock_ULSEP_BINDING_ID"})
@ApplicationScoped
public class EnterpriseServerGraphQLService implements AnonymousClass0P6 {
    public static volatile EnterpriseServerGraphQLService _UL__ULSEP_com_oculus_alpenglow_http_EnterpriseServerGraphQLService_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;
    public final TreeJsonSerializer mSerializer = AnonymousClass0Pj.A01(null);

    @AutoGeneratedAccessMethod
    public static final EnterpriseServerGraphQLService A00(AbstractC02990bJ r1) {
        return (EnterpriseServerGraphQLService) AnonymousClass13m.A00(63, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final EnterpriseServerGraphQLService A01(AbstractC02990bJ r4) {
        if (_UL__ULSEP_com_oculus_alpenglow_http_EnterpriseServerGraphQLService_ULSEP_INSTANCE == null) {
            synchronized (EnterpriseServerGraphQLService.class) {
                AnonymousClass0Qe A00 = AnonymousClass0Qe.A00(_UL__ULSEP_com_oculus_alpenglow_http_EnterpriseServerGraphQLService_ULSEP_INSTANCE, r4);
                if (A00 != null) {
                    try {
                        _UL__ULSEP_com_oculus_alpenglow_http_EnterpriseServerGraphQLService_ULSEP_INSTANCE = new EnterpriseServerGraphQLService(r4.getApplicationInjector());
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_alpenglow_http_EnterpriseServerGraphQLService_ULSEP_INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00df A[SYNTHETIC, Splitter:B:45:0x00df] */
    /* JADX WARNING: Removed duplicated region for block: B:72:? A[SYNTHETIC] */
    @Override // X.AnonymousClass0P6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> void A2X(final X.AnonymousClass0P7<T> r12, final X.AnonymousClass0Cg<X.AnonymousClass0P8<T>> r13, final java.util.concurrent.Executor r14) {
        /*
        // Method dump skipped, instructions count: 325
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.alpenglow.http.EnterpriseServerGraphQLService.A2X(X.0P7, X.0Cg, java.util.concurrent.Executor):void");
    }

    @Inject
    public EnterpriseServerGraphQLService(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(2, r3);
    }
}