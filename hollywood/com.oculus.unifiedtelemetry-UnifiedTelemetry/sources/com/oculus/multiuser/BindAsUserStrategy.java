package com.oculus.multiuser;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.UserHandle;
import com.oculus.binder.BindingStrategy;

public final class BindAsUserStrategy implements BindingStrategy {
    public final UserHandle mUser;

    @Override // com.oculus.binder.BindingStrategy
    public final boolean A1U(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        return context.bindServiceAsUser(intent, serviceConnection, i, this.mUser);
    }

    public BindAsUserStrategy(UserHandle userHandle) {
        this.mUser = userHandle;
    }
}
