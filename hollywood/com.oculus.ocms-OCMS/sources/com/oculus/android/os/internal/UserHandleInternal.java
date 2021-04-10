package com.oculus.android.os.internal;

import android.os.UserHandle;
import com.facebook.infer.annotation.SuppressLint;

public class UserHandleInternal {
    @SuppressLint({"ImprovedNewApi"})
    public UserHandle getAll() {
        return UserHandle.ALL;
    }

    @SuppressLint({"ImprovedNewApi"})
    public UserHandle getSystem() {
        return UserHandle.SYSTEM;
    }

    @SuppressLint({"InstanceMethodCanBeStatic"})
    public UserHandle of(int i) {
        return UserHandle.of(i);
    }

    @SuppressLint({"InstanceMethodCanBeStatic"})
    public int getIdentifier(UserHandle userHandle) {
        return userHandle.getIdentifier();
    }

    @SuppressLint({"InstanceMethodCanBeStatic"})
    public int getAppId(int i) {
        return UserHandle.getAppId(i);
    }
}
