package com.oculus.userserver.api;

import android.annotation.SuppressLint;

@SuppressLint({"CheckSerializableClass"})
public final class AccountAlreadyOnDeviceException extends Exception {
    AccountAlreadyOnDeviceException(String str) {
        super(str);
    }
}
