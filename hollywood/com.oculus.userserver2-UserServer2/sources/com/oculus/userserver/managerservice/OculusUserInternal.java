package com.oculus.userserver.managerservice;

import com.oculus.userserver.api.user.OculusUser;
import javax.annotation.Nullable;

public final class OculusUserInternal {
    @Nullable
    public final String mAccountId;
    public final OculusUser mOculusUser;
    @Nullable
    public final String mSyncId;

    public OculusUserInternal(OculusUser oculusUser, @Nullable String str, @Nullable String str2) {
        this.mOculusUser = oculusUser;
        this.mAccountId = str;
        this.mSyncId = str2;
    }
}
