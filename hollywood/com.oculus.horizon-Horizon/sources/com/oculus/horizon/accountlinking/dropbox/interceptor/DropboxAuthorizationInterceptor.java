package com.oculus.horizon.accountlinking.dropbox.interceptor;

import X.AbstractC06640p5;
import X.AnonymousClass117;
import X.C08220wC;
import X.C08330wN;
import X.C08340wO;
import android.content.ContentValues;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.oculus.horizon.linkedaccounts.database.LinkedAccountsSQLiteHelper;
import com.oculus.horizon.linkedaccounts.model.ServiceProvider;
import com.oculus.http.core.common.Authorization;
import com.oculus.http.core.interceptor.AuthorizationInterceptor;
import java.util.List;
import javax.annotation.Nullable;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_linkedaccounts_database_LinkedAccountsSQLiteHelper_ULSEP_BINDING_ID"})
public class DropboxAuthorizationInterceptor extends AuthorizationInterceptor {
    @Inject
    @Eager
    public final LinkedAccountsSQLiteHelper mLinkedAccountsSQLiteHelper;

    @Override // com.oculus.http.core.interceptor.AuthorizationInterceptor
    @Nullable
    public final C08340wO A03(C08330wN r5) {
        String asString;
        List<ContentValues> A02 = this.mLinkedAccountsSQLiteHelper.A02(ServiceProvider.DROPBOX);
        if (A02.isEmpty() || (asString = A02.get(0).getAsString("token")) == null) {
            return null;
        }
        C08340wO r2 = new C08340wO(r5);
        r2.A02("Authorization", Authorization.A00(asString));
        return r2;
    }

    @Override // com.oculus.http.core.interceptor.AuthorizationInterceptor
    public final void A04(C08220wC r5) {
        this.mLinkedAccountsSQLiteHelper.A00(ServiceProvider.DROPBOX);
    }

    @Inject
    public DropboxAuthorizationInterceptor(AbstractC06640p5 r2) {
        this.mLinkedAccountsSQLiteHelper = (LinkedAccountsSQLiteHelper) AnonymousClass117.A00(293, r2);
    }
}
