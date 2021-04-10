package com.oculus.userserver.api;

import android.annotation.SuppressLint;

@SuppressLint({"CheckSerializableClass"})
public final class UserAlreadyOnDeviceException extends Exception {
    UserAlreadyOnDeviceException(String str) {
        super(str);
    }
}
