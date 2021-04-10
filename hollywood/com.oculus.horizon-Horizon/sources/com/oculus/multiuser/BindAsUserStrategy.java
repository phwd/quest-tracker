package com.oculus.multiuser;

import android.os.UserHandle;
import com.oculus.binder.BindingStrategy;

public final class BindAsUserStrategy implements BindingStrategy {
    public final UserHandle mUser;
}
