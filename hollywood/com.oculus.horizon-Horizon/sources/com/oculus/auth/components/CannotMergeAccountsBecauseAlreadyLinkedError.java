package com.oculus.auth.components;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.auth.api.LoginWithFbAuthResponse;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class CannotMergeAccountsBecauseAlreadyLinkedError extends MarshallableError {
    public final LoginWithFbAuthResponse.LoginSession mLoginSession;

    public CannotMergeAccountsBecauseAlreadyLinkedError(LoginWithFbAuthResponse.LoginSession loginSession) {
        super(-19);
        this.mLoginSession = loginSession;
    }

    @Override // com.oculus.auth.components.MarshallableError
    public Bundle marshal() {
        Bundle marshal = super.marshal();
        marshal.putString("access_token", this.mLoginSession.accessToken);
        marshal.putString("user_id", this.mLoginSession.userId);
        return marshal;
    }
}
