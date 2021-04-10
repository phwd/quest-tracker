package com.oculus.android.os.internal;

import android.os.UserHandle;
import com.facebook.infer.annotation.SuppressLint;

public class UserHandleInternal {
    @SuppressLint({"InstanceMethodCanBeStatic"})
    public static final int A00(UserHandle userHandle) {
        return userHandle.getIdentifier();
    }

    @SuppressLint({"ImprovedNewApi"})
    public static final UserHandle A01() {
        return UserHandle.ALL;
    }

    @SuppressLint({"ImprovedNewApi"})
    public static final UserHandle A02() {
        return UserHandle.SYSTEM;
    }
}
