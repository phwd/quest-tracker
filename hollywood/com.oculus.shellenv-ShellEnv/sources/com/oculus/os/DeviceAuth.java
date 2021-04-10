package com.oculus.os;

import android.content.Context;
import java.io.IOException;

public final class DeviceAuth {

    public static final class BackendException extends IOException {
        BackendException(Exception exc) {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class DeviceIdentityException extends IOException {
        DeviceIdentityException(String str) {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class NetworkException extends IOException {
        NetworkException(Exception exc) {
            throw new RuntimeException("Stub!");
        }
    }

    public DeviceAuth(Context context) {
        throw new RuntimeException("Stub!");
    }

    public final DeviceAuthToken fetchToken(String str) {
        throw new RuntimeException("Stub!");
    }
}
