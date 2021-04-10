package com.oculus.auth.components;

import android.os.Bundle;
import com.google.common.collect.ImmutableList;
import com.oculus.auth.service.contract.AuthTwoFactorMethod;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;

public final class TwoFactorLoginRequiredError extends MarshallableError {
    @Nullable
    public final ImmutableList<AuthTwoFactorMethod> mMethods;
    public final String mNonce;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TwoFactorLoginRequiredError(com.oculus.http.core.base.ApiError r4, java.lang.String r5, @javax.annotation.Nullable java.util.ArrayList<com.oculus.horizon.api.twofac.TwoFactorMethod> r6) {
        /*
            r3 = this;
            r1 = 0
            r2 = r1
            com.oculus.http.core.base.ApiError$FBApiErrorResponse r0 = r4.fbApiErrorResponse
            if (r0 == 0) goto L_0x000c
            com.oculus.http.core.base.ApiError$FBApiErrorResponse$Error r0 = r0.error
            if (r0 == 0) goto L_0x000c
            java.lang.String r2 = r0.error_user_title
        L_0x000c:
            java.lang.String r1 = r4.A00(r1)
            r0 = -8
            r3.<init>(r0, r2, r1)
            r3.mNonce = r5
            com.google.common.collect.ImmutableList r0 = com.oculus.auth.api.AuthDataHelper.convertTwoFactorMethods(r6)
            r3.mMethods = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.auth.components.TwoFactorLoginRequiredError.<init>(com.oculus.http.core.base.ApiError, java.lang.String, java.util.ArrayList):void");
    }

    @Override // com.oculus.auth.components.MarshallableError
    public Bundle marshal() {
        Bundle marshal = super.marshal();
        marshal.putString("nonce", this.mNonce);
        ImmutableList<AuthTwoFactorMethod> immutableList = this.mMethods;
        if (immutableList != null) {
            marshal.putByteArray(ServiceContract.EXTRA_TWO_FACTOR_METHODS, AuthTwoFactorMethod.marshallParcelableList(immutableList));
        }
        return marshal;
    }
}
