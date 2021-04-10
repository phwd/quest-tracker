package com.oculus.horizon.accountlinking.google.interceptor;

import X.AbstractC06640p5;
import X.AbstractC08210wB;
import X.AbstractC08380wS;
import X.AbstractC08390wT;
import X.AnonymousClass0B3;
import X.AnonymousClass0J2;
import X.AnonymousClass0Mx;
import X.AnonymousClass0QC;
import X.C08220wC;
import X.C08230wD;
import X.C08370wR;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.linkedaccounts.database.LinkedAccountsSQLiteHelper;
import com.oculus.horizon.linkedaccounts.model.ServiceProvider;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_linkedaccounts_database_LinkedAccountsSQLiteHelper_ULSEP_BINDING_ID"})
public class GoogleAuthorizationInterceptor implements AbstractC08380wS {
    public static final Set<String> ERRORS_UNAUTHORIZED = new HashSet(Arrays.asList("invalid_grant", "invalid_token"));
    public AnonymousClass0QC _UL_mInjectionContext;

    @Inject
    public GoogleAuthorizationInterceptor(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }

    @Override // X.AbstractC08380wS
    public final C08220wC intercept(AbstractC08390wT r9) throws IOException {
        boolean z;
        C08220wC A7Z = r9.A7Z(r9.A8H());
        AbstractC08210wB r4 = A7Z.A0B;
        String A01 = r4.A01();
        try {
            z = ERRORS_UNAUTHORIZED.contains(new JSONObject(A01).optString("error"));
        } catch (JSONException unused) {
            z = false;
        }
        if (z) {
            ((LinkedAccountsSQLiteHelper) AnonymousClass0J2.A03(0, 293, this._UL_mInjectionContext)).A00(ServiceProvider.GOOGLE);
        }
        C08230wD r5 = new C08230wD(A7Z);
        C08370wR A02 = r4.A02();
        byte[] bytes = A01.getBytes();
        AnonymousClass0B3 r3 = new AnonymousClass0B3();
        r3.A0I(bytes);
        r5.A0B = new AnonymousClass0Mx(A02, (long) bytes.length, r3);
        return r5.A00();
    }
}
