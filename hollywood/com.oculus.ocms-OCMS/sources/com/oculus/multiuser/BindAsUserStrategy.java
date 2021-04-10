package com.oculus.multiuser;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.UserHandle;
import com.oculus.android.os.internal.ContextInternal;
import com.oculus.binder.BindingStrategy;

public final class BindAsUserStrategy implements BindingStrategy {
    private final UserHandle mUser;

    private BindAsUserStrategy(UserHandle userHandle) {
        this.mUser = userHandle;
    }

    public static BindAsUserStrategy of(UserHandle userHandle) {
        return new BindAsUserStrategy(userHandle);
    }

    @Override // com.oculus.binder.BindingStrategy
    public boolean bindService(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        return ContextInternal.bindServiceAsUser(context, intent, serviceConnection, i, this.mUser);
    }
}
