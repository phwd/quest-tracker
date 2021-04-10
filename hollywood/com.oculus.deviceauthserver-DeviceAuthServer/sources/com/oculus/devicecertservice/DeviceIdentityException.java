package com.oculus.devicecertservice;

public class DeviceIdentityException extends Exception {
    public DeviceIdentityException(String message) {
        super(message);
    }

    public DeviceIdentityException(Throwable cause) {
        super(cause);
    }
}
