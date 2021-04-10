package com.oculus.os;

import android.content.Context;
import java.io.IOException;

public final class DeviceAuth {
    public DeviceAuth(Context context) {
        throw new RuntimeException("Stub!");
    }

    public DeviceAuthToken fetchToken(String applicationClientToken) throws BackendException, DeviceIdentityException, NetworkException, InterruptedException {
        throw new RuntimeException("Stub!");
    }

    public static final class BackendException extends IOException {
        BackendException(Exception cause) {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class DeviceIdentityException extends IOException {
        DeviceIdentityException(String message) {
            throw new RuntimeException("Stub!");
        }
    }

    public static final class NetworkException extends IOException {
        NetworkException(Exception cause) {
            throw new RuntimeException("Stub!");
        }
    }
}
