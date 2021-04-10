package com.oculus.companion.server;

import com.google.protobuf.Internal;

public enum Protocol$HeadsetState implements Internal.EnumLite {
    HEADSET_MOUNTED(0),
    HEADSET_UNMOUNTED(1),
    STANDBY(2),
    STARTING(3),
    STOPPED(4),
    WAITING_FOR_SLEEP_MSG(5);
    
    private static final Internal.EnumLiteMap<Protocol$HeadsetState> internalValueMap = new Internal.EnumLiteMap<Protocol$HeadsetState>() {
        /* class com.oculus.companion.server.Protocol$HeadsetState.AnonymousClass1 */
    };
    private final int value;

    public final int getNumber() {
        return this.value;
    }

    public static Protocol$HeadsetState forNumber(int i) {
        if (i == 0) {
            return HEADSET_MOUNTED;
        }
        if (i == 1) {
            return HEADSET_UNMOUNTED;
        }
        if (i == 2) {
            return STANDBY;
        }
        if (i == 3) {
            return STARTING;
        }
        if (i == 4) {
            return STOPPED;
        }
        if (i != 5) {
            return null;
        }
        return WAITING_FOR_SLEEP_MSG;
    }

    private Protocol$HeadsetState(int i) {
        this.value = i;
    }
}
